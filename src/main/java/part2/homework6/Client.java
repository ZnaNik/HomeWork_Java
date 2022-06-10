package part2.homework6;


import java.io.*;
import java.net.Socket;

public class Client implements ClientInt {
    private int id;
    private java.net.Socket socket;
    Thread clientListeningThread;
    DataInputStream in;
    DataOutputStream out;

    public Client(Socket socket, int id) throws IOException {
        this.id = id;
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
      }

    @Override
    public void sendMsg(String Message) {

        if (socket == null || socket.isClosed())
            return;

        try {
            out.writeUTF(Message);
            System.out.printf("Server > Client[%s] [%s]\n", getId(), Message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getId() {
        return id;
    }

    public void close() {
        try {
            if (!socket.isClosed() && socket != null)
                socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.printf("Client[%s] is closed\n", getId());
        }
    }

    public boolean isAlive() {
        if (socket == null)
            return false;

        if (socket.isClosed())
            return false;

        return true;
    }

    @Override
    public void setListenThread(Thread clientListeningThread) {
        this.clientListeningThread = clientListeningThread;
    }

    @Override
    public String getNextMessage() throws IOException {
        return in.readUTF();
    }


}