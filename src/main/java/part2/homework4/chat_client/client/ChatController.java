package part2.homework4.chat_client.client;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

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

    public void mockAction(ActionEvent actionEvent) {
        System.out.println("mock");
    }

    public void closeApplication(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void sendMessage(ActionEvent actionEvent) {
        String text = inputField.getText();
        if (text == null || text.isBlank()) {
            return;
        }

        String sendText = text;

        ObservableList<String> selectedContacts = contacts.getSelectionModel().getSelectedItems();
        if (!selectedContacts.isEmpty())
            if (selectedContacts.get(0).toLowerCase(Locale.ROOT).equals("send all"))
                sendText = "BroadCast: " + text;
            else
                sendText = String.format("[%s]: %s",selectedContacts.get(0) ,text);
        else
            sendText = "BroadCast: " + text;

        chatArea.appendText(sendText + System.lineSeparator());
        inputField.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> names = List.of("Vasya", "Masha", "Petya", "Valera", "Nastya", "Send all");
        contacts.setItems(FXCollections.observableList(names));
    }
}
