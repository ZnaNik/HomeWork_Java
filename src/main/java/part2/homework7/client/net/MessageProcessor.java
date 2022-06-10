package part2.homework7.client.net;

import part2.homework7.common.message.Message;

public interface MessageProcessor {
    void processMessage(Message message);
}
