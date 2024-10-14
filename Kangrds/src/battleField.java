import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class battleField extends Application {
    private card[][] field = new card[5][3];
    private card[] playerHandle = new card[8];
    private deck playerDeck;

    @Override
    public void start(Stage primaryStage) {
        //初始化程序
        Pane root = new Pane();//新操作面板
        Scene scene = new Scene(root, 500, 800);//大小
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
