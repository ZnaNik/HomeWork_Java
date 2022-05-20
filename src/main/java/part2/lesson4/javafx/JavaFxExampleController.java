package part2.lesson4.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class JavaFxExampleController {
    @FXML
    public Button btnClick;

    @FXML
    public Label labelText;

    public void click(ActionEvent actionEvent) {
        System.out.println("Button clicked");
        labelText.setText("Hello JavaFX");
        btnClick.setText("Clicked");
        btnClick.setScaleX(2);
        btnClick.setScaleY(2);
    }
}
