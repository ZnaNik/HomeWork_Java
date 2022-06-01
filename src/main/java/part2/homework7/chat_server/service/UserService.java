package part2.homework7.chat_server.service;

import part2.homework7.common.model.User;

public interface UserService {
    void start();
    void stop();
    String authenticate(String login, String password);
    String changeNick(String login, String newNick);
    User createUser(String login, String password, String nick);
    void deleteUser(String login, String password);
    void changePassword(String login, String oldPassword, String newPassword);
}
