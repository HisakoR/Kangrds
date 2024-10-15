import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //初始化程序
        Pane root = new Pane();//新操作面板
        Scene scene = new Scene(root, 1280, 720);//大小
        primaryStage.setResizable(false);//不可调节大小
        //界面内容
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //主要方法
    public static void main(String[] args) {
        launch(args);
    }

}
