package part2.homework7.chat_server;

import part2.homework7.chat_server.service.UserService;
import part2.homework7.common.message.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static part2.homework7.common.constants.MessageConstants.REGEX;
import static part2.homework7.common.enums.Command.BROADCAST_MESSAGE;
import static part2.homework7.common.enums.Command.LIST_USERS;

public class Server {
    private static final int PORT = 8189;
    private List<Handler> handlers;
    private final String serverName = "SERVER";
    public String getServerName(){
        return serverName;
    }
    private UserService userService;

    public Server(UserService userService) {
        this.userService = userService;
        this.handlers = new ArrayList<>();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server start!");
            userService.start();
            while (true) {
                System.out.println("Waiting for connection......");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                Handler handler = new Handler(socket, this);
                handler.handle();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

    public void broadcast(String from, String message) {
        String msg = String.format("[%s]: %s", from, message);
        for (Handler handler : handlers) {
            handler.send(
                    new Message(from,
                            handler.getUserNick(),
                            msg,
                            BROADCAST_MESSAGE));
        }
    }

    public void personalMessage(Message message){
        for (Handler handler : handlers){
            if (handler.getUserNick().equals(message.getReciever_id()))
                handler.send(message);
        }
    }

    public UserService getUserService() {
        return userService;
    }
    
    public synchronized boolean isUserAlreadyOnline(String nick) {
        for (Handler handler : handlers) {
            if (handler.getUserNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }
    
    public synchronized void addHandler(Handler handler) {
        this.handlers.add(handler);
        sendContacts();
    }

    public synchronized void removeHandler(Handler handler) {
        this.handlers.remove(handler);
        sendContacts();
    }

    private void shutdown() {
        userService.stop();
    }

    private void sendContacts() {
       String contacts = handlers.stream()
                .map(Handler::getUserNick)
                .collect(Collectors.joining(REGEX));

        for (Handler handler : handlers) {
            handler.send(new Message(getServerName(),
                    handler.getUserNick(),
                    contacts,
                    LIST_USERS));
        }
    }
}
