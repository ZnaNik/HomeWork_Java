package part2.homework7.client;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import part2.homework7.client.net.MessageProcessor;
import part2.homework7.client.net.NetworkService;
import part2.homework7.common.message.Message;
import part2.homework7.common.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static part2.homework7.common.constants.MessageConstants.REGEX;
import static part2.homework7.common.enums.Command.*;

public class ChatController implements Initializable, MessageProcessor {

    @FXML
    private VBox changeNickPanel;

    @FXML
    private TextField newNickField;

    @FXML
    private VBox changePasswordPanel;

    @FXML
    private PasswordField oldPassField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private VBox loginPanel;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private VBox mainPanel;

    @FXML
    private TextArea chatArea;

    @FXML
    private ListView<String> contacts;

    @FXML
    private TextField inputField;

    @FXML
    private Button btnSend;

    private NetworkService networkService;

    private User user;

    public void mockAction(ActionEvent actionEvent) {
        System.out.println("mock");
    }

    public void closeApplication(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void sendMessage(ActionEvent actionEvent) {
        try {
            String text = inputField.getText();
            if (text == null || text.isBlank()) {
                return;
            }
            String recipient = contacts.getSelectionModel().getSelectedItem();
            if (recipient.equals("ALL")) {
                networkService.sendMessage(new Message(user.getNick(),
                        null,
                        text,
                        BROADCAST_MESSAGE));
            }
            else if (!user.getNick().equals(recipient)){
                networkService.sendMessage(new Message(user.getNick(),
                        recipient,
                        text,
                        PRIVATE_MESSAGE));
             }
                inputField.clear();
        } catch (IOException e) {
            showError("NETWORK ERROR");
        }
    }

    private void showError(String error) {
        Alert alert = new Alert(
                Alert.AlertType.ERROR,
                error,
                ButtonType.CLOSE
        );
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        networkService = new NetworkService(this);
    }

    @Override
    public void processMessage(Message message) {
        System.out.println("processing message");
        Platform.runLater(() -> parseMessage(message));
    }

    private void parseMessage(Message message) {

        switch (message.getCommand()) {
            case AUTH_OK -> authOk(message);
            case ERROR_MESSAGE -> showError(message.getMessage());
            case LIST_USERS -> parseUsers(message);
            case PRIVATE_MESSAGE -> getPrivateMessage(message);
            default -> chatArea.appendText("[" + message.getOwner_id() + "]" +
                    message.getMessage() + System.lineSeparator());
        }
    }

    private void getPrivateMessage(Message message) {
        chatArea.appendText("PRIVATE FROM [" + message.getOwner_id() + "] "
                + message.getMessage()
                + System.lineSeparator());
    }

    private void parseUsers(Message message) {
        List<String> contact = new ArrayList<>(Arrays.asList(message.getMessage().split(REGEX)));
        contact.add(0, "ALL");
        contacts.setItems(FXCollections.observableList(contact));
    }

    private void authOk(Message message) {
        System.out.println("Auth ok");
        user.setNick(message.getReciever_id());
        loginPanel.setVisible(false);
        mainPanel.setVisible(true);
        Application.setTitle("Write smth: " + user.getNick());
    }

    public void sendChangeNick(ActionEvent actionEvent) {
//TODO
    }

    public void returnToChat(ActionEvent actionEvent) {
//TODO
    }

    public void sendChangePass(ActionEvent actionEvent) {
//TODO
    }

    public void sendAuth(ActionEvent actionEvent) {
        user = new User(loginField.getText(), passwordField.getText(), null);

        if (user.getPassword().isBlank() || user.getLogin().isBlank()) {
            return;
        }

        String msg = user.getLogin() + REGEX + user.getPassword();
        try {
            if (!networkService.isConnected()) {
                networkService.connect();
            }

            networkService.sendMessage(new Message(null,
                    null,
                    msg,
                    AUTH_MESSAGE));

        } catch (IOException e) {
            showError("Network error");
        }
    }
}
