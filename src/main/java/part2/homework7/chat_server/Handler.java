package part2.homework7.chat_server;

import part2.homework7.chat_server.error.WrongCredentialsException;
import part2.homework7.common.message.Message;
import part2.homework7.common.model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static part2.homework7.common.constants.MessageConstants.REGEX;
import static part2.homework7.common.enums.Command.*;

public class Handler {
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Thread handlerThread;
    private Server server;
    private User user;

    public Handler(Socket socket, Server server) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Handler created");
        } catch (IOException e) {
            System.err.println("Connection problems with user: " + user);
        }
    }

    public void handle() {
        handlerThread = new Thread(() -> {
            authorize();
            System.out.println("Auth done");
            while (!Thread.currentThread().isInterrupted() && !socket.isClosed()) {
                try {
                    String message = in.readUTF();
                    parseMessage(message);
                } catch (IOException e) {
                    System.out.println("Connection broken with client: " + user.getNick());
                    server.removeHandler(this);
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        handlerThread.start();
    }

    private void parseMessage(String strMessage) {

        //Поменял логику обработки на JSON
        Message message = Message.getMessage(strMessage);

        switch (message.getCommand()) {
            case BROADCAST_MESSAGE -> server.broadcast(getUserNick(), message.getMessage());
            case PRIVATE_MESSAGE -> server.personalMessage(message);
            default -> System.out.println("Unknown message " + message);
        }
    }

    private void authorize() {
        System.out.println("Authorizing");

        try {
            while (!socket.isClosed()) {

                //doint unjson message;
                Message message = Message.getMessage(in.readUTF());

                if (message.getCommand() == AUTH_MESSAGE) {
                    String[] parsed = message.getMessage().split(REGEX);
                    String response = "";
                    String nickname = null;
                    String login = "";
                    String pass = "";

                    try {
                        login = parsed[0];
                        pass = parsed[1];
                        nickname = server.getUserService().authenticate(login, pass);
                    } catch (WrongCredentialsException e) {
                        response = e.getMessage();
                        System.out.println("Wrong credentials: " + parsed[1]);
                    }
                    
                    if (server.isUserAlreadyOnline(nickname)) {
                        response = "This client already connected";
                        System.out.println("Already connected");
                    }
                    
                    if (!response.equals("")) {
                        send(new Message(server.getServerName(),
                                "anonymous",
                                response,
                                ERROR_MESSAGE));
                    } else {
                        System.out.println("Auth ok");
                        this.user = new User(login, pass, nickname);

                        send(new Message(server.getServerName(),
                                user.getNick(),
                                "Welcome to server",
                                AUTH_OK));

                        server.addHandler(this);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Message message) {
        try {
            out.writeUTF(message.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Thread getHandlerThread() {
        return handlerThread;
    }

    public String getUserNick() {
        return user.getNick();
    }
}
