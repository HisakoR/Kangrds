import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


public class Controller {
    @FXML
    private TextField userField;
    @FXML
    private TextField passField;
    @FXML
    private Text errorInfo;

    @FXML
    public void saveData(MouseEvent event){
        boolean dataFound = false;//是否找到目标
        if ((!userField.getText().isEmpty() && !userField.getText().contains(" ")) && (!passField.getText().isEmpty() && !passField.getText().contains(" "))){//如果用户输入不为空
            for(int x = 0; x < loginMenu.findUsers().size(); x++){
                System.out.println("库用户名检测|" + loginMenu.findUsers().get(x).substring(0,loginMenu.findUsers().get(x).indexOf(",")));
                System.out.println("库用户密码检测|" + loginMenu.findUsers().get(x).substring(loginMenu.findUsers().get(x).indexOf(",") + 1));
                if (userField.getText().equals(loginMenu.findUsers().get(x).substring(0,loginMenu.findUsers().get(x).indexOf(",")))){//检测库中是否有目标用户名
                    if(passField.getText().equals(loginMenu.findUsers().get(x).substring(loginMenu.findUsers().get(x).indexOf(",") + 1))){//检测密码是否正确
                        dataFound = true;
                        break;//直接跳出
                    }
                }
            }
            //找到目标后
            if(dataFound){
                loginMenu user = new loginMenu(userField.getText(), passField.getText());
                System.out.println("用户输入: " + userField.getText());
                System.out.println("密码输入: " + passField.getText());
                errorInfo.setText("欢迎！ " + userField.getText());
            }
            else{
                System.out.println("数据库无此用户或密码错误");
                errorInfo.setText("用户名或密码错误");
            }
        }
        else{
            System.out.println("并非输入");
            errorInfo.setText("并非输入");
        }
    }
    @FXML
    public void exit(MouseEvent event){
        System.out.println("进程结束");
        System.exit(0);
    }
    @FXML
    public void saveUser(MouseEvent event){
        if((userField.getText().isEmpty() || userField.getText().contains(" ")) || (passField.getText().isEmpty() || passField.getText().contains(" "))){
            System.out.println("并非输入");
            errorInfo.setText("并非输入");
        }
        else {
            boolean userIn = false;
            for(int x = 0; x < loginMenu.findUsers().size(); x++){
                System.out.println("库用户名检测|" + loginMenu.findUsers().get(x).substring(0,loginMenu.findUsers().get(x).indexOf(",")));
                if (userField.getText().equals(loginMenu.findUsers().get(x).substring(0,loginMenu.findUsers().get(x).indexOf(",")))){//检测库中是否有目标用户名
                    System.out.println("此账户名已使用");
                    errorInfo.setText("此账户名已使用");
                    userIn = false;
                    break;
                }
                else {
                    userIn = true;
                }
            }
            if(userIn){
                loginMenu.saveUser(userField.getText(), passField.getText());
                errorInfo.setText("注册成功");
                System.out.println("用户信息已录入");
            }
        }
    }
}