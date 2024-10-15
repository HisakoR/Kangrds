import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {
    @FXML
    private TextField userField;
    @FXML
    private TextField passField;

    @FXML
    void saveData(MouseEvent event) {
        loginMenu user = new loginMenu(userField.getText(), passField.getText());
        System.out.println(userField.getText() + passField.getText());
    }
}