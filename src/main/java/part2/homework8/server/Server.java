package part2.homework8.server;

import part2.homework8.common.props.PropertyReader;
import part2.homework8.server.model.IntConnectionController;
import part2.homework8.server.model.impl.Connections;
import part2.homework8.server.model.impl.SocketConnection;
import part2.homework8.server.service.UserService;
import part2.homework8.server.service.impl.InnerProcedures;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static part2.homework8.common.constants.MessageConstants.REGEX;
import static part2.homework8.common.enums.Command.*;

public class Server {
    private final int port;
    private List<Handler> handlers;

    public IntConnectionController connections;

    private UserService userService;

    private InnerProcedures procedures;

    public Server(UserService userService) {
        this.userService = userService;
        this.handlers = new ArrayList<>();
        this.procedures = new InnerProcedures(this);
        this.connections = new Connections();
        procedures.startDisconnectDaemon();
        port = PropertyReader.getInstance().getPort();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server start!");
            userService.start();
            while (true) {
                System.out.println("Waiting for connection......");
                Socket socket = serverSocket.accept();
                connections.add(new SocketConnection(socket));
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
        String msg = BROADCAST_MESSAGE.getCommand() + REGEX + String.format("[%s]: %s", from, message);
        for (Handler handler : handlers) {
            handler.send(msg);
        }
    }

    public void privateMessage(String from, String to, String message) {
        String msg = PRIVATE_MESSAGE.getCommand() + REGEX + String.format("[%s][to %s]: %s", from, to, message);
        for (Handler handler : handlers) {
            if (handler.getUser().equals(from) || handler.getUser().equals(to)) {
                handler.send(msg);
            }
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public synchronized boolean isUserAlreadyOnline(String nick) {
        for (Handler handler : handlers) {
            if (handler.getUser().equals(nick)) {
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

    public synchronized void updateHandlerUsername() {
        sendContacts();
    }

    private void shutdown() {
        userService.stop();
    }

    private void sendContacts() {
        String contacts = handlers.stream()
                .map(Handler::getUser)
                .collect(Collectors.joining(REGEX));
        String msg = LIST_USERS.getCommand() + REGEX + contacts;

        for (Handler handler : handlers) {
            handler.send(msg);
        }
    }
}
