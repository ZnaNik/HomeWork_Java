package part2.homework8.server.service.impl;

import part2.homework8.common.constants.DisconnectConstants;
import part2.homework8.server.Server;
import part2.homework8.server.model.IntConnection;

import java.util.concurrent.TimeUnit;

import static part2.homework8.common.constants.MessageConstants.REGEX;
import static part2.homework8.common.enums.Command.NOT_AUTHORISED;

public class InnerProcedures {

    private Thread disconnectDaemon;

    public InnerProcedures(Server server) {
        this.server = server;
    }

    Server server;

    public void startDisconnectDaemon() {
        if (disconnectDaemon != null) {
            if (!disconnectDaemon.isAlive()) {
                return;
            }
        }
        disconnectDaemon = new disconnectDaemon(server);
        disconnectDaemon.start();
    }

    public class disconnectDaemon extends Thread {
        Server server;

        public void run() {
            while (!isInterrupted()) {
                checkUsers();
                try {
                    sleep(TimeUnit.SECONDS.toMillis(DisconnectConstants.secForDisconect / 12));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.interrupt();
                }
            }
        }

        void checkUsers() {
            var serverConnections = server.connections.getConnections();

            for (int i = 0; i < serverConnections.size(); i++) {
                IntConnection connection = serverConnections.get(i);
                if (connection.isClosed())
                {
                    serverConnections.remove(connection);
                }
                else if (connection.isNeedDisconnect()) {
                    connection.send(NOT_AUTHORISED.getCommand() + REGEX + "GOOD LUCK");
                    serverConnections.remove(connection);
                }
            }
        }

        public disconnectDaemon(Server server) {
            this.server = server;
            setDaemon(true);
            setName(this.getClass().getName());
        }
    }
}
