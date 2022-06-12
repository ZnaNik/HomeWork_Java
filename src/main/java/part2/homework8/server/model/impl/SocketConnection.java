package part2.homework8.server.model.impl;

import part2.homework8.common.constants.DisconnectConstants;
import part2.homework8.server.model.IntConnection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SocketConnection implements IntConnection {
    Socket socket;
    LocalDateTime connectionTime;

    public SocketConnection(Socket socket){
        this.socket = socket;
        connectionTime = LocalDateTime.now();
    }
    @Override
    public void close() {
        if (!socket.isClosed())
        {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Socket getSocket() {
        return socket;
    }

    @Override
    public boolean isClosed() {
        return socket.isClosed();
    }

    public boolean isNeedDisconnect() {
        if ((ChronoUnit.SECONDS.between(connectionTime, LocalDateTime.now()))
                >= DisconnectConstants.secForDisconect) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void send(String message) {
        try {
            new DataOutputStream(socket.getOutputStream()).writeUTF(message);
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }
    }

}
