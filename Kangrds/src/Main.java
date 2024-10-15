import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("大混战KC ~ v0.01");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    //主要方法
    public static void main(String[] args) {
        launch(args);
    }
}
