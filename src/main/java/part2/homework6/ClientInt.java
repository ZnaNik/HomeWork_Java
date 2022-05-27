package part2.homework6;

import java.io.IOException;
import java.net.Socket;

public interface ClientInt {
    void sendMsg(String Message);

    int getId();

    void close();

    boolean isAlive();

    void setListenThread(Thread clientListeningThread);

    String getNextMessage() throws IOException;
}
