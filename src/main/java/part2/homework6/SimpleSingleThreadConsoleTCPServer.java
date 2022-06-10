package part2.homework6;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SimpleSingleThreadConsoleTCPServer {
    private static final int PORT = 8189;
    private DataInputStream in;
    private DataOutputStream out;
    private Thread serverThread;
    private ArrayList<ClientInt> clientList = new ArrayList<>();
    final Object lock = new Object();

    public static void main(String[] args) {
        new SimpleSingleThreadConsoleTCPServer().start();
    }

    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            startServerMessageToAll();

            //Ожидаем новые подключения
            while (true) {
                waitConnection(serverSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void shutdown() throws IOException {

        synchronized (lock) {
            if (serverThread != null && serverThread.isAlive()) {
                serverThread.interrupt();
            }

            while (clientList.size() > 0)
            {
                closeClient(clientList.get(0));
            }

        }

        System.out.println("Server stopped");
    }

    void closeClient(ClientInt client){
        clientList.remove(client);
        if (client.isAlive())
            client.close();
    }

    private void startServerMessageToAll() {
        serverThread = new Thread(() -> {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                while (!Thread.currentThread().isInterrupted()) {
                    String msg = br.readLine();
                    sendAll(null,msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
    }

    private void waitConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for connection....");
        var Client = CreateClient(serverSocket.accept());
        Client.sendMsg("Hello u are welcome");
        CreateClient(serverSocket.accept());
    }

    private ClientInt CreateClient(Socket socket) {
        int newNum;

        synchronized (lock) {
            if (serverThread != null &&
                    !serverThread.isAlive())
                return null;

            newNum = clientList.size() + 1;
        }

        Client client = null;
        try {
            client = new Client(socket, newNum);
            client.setListenThread(socketListen(client));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        synchronized (lock) {
            clientList.add(client);
        }
        return client;
    }

    Thread socketListen(ClientInt client) throws IOException {
        Thread listnThread = new Thread(() -> {

            while (client.isAlive()
                    && !Thread.currentThread().isInterrupted()) {

                String msg = null;
                try {
                    msg = client.getNextMessage();
                } catch (IOException e) {
                    client.close();
                    return;
                }

                if (msg.contains("/all"))
                    sendAll(client,"BROADCAST: " + msg.replace("/all", ""));
                else
                    client.sendMsg("ECHO: " + msg);
            }
        });
        listnThread.start();
        return listnThread;
    }


    void sendAll(ClientInt clientStarter,String msg) {
        if (clientStarter != null)
            System.out.printf("Client[%s]->BroadCast all [%s]\n", clientStarter.getId(), msg);
        else
            System.out.printf("Broadcasting [%s] \n", msg);

        for (ClientInt client : clientList) {
            if (clientStarter!= null &&
                    client.getId() == clientStarter.getId()){
                continue;
            }
            client.sendMsg(msg);
        }
    }
}

