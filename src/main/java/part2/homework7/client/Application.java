package part2.homework7.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    private static Stage stage;

    public static void run(String[] args) {
        launch(args);
    }

    public static void setTitle(String name){
        stage.setTitle(name);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/ChatWindow.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("testy chat");
        this.stage = primaryStage;
        primaryStage.show();
    }
}
