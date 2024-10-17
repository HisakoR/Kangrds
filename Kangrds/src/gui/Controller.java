package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Controller {
    @FXML
    private TextField userField;
    @FXML
    private TextField passField;
    @FXML
    private Text errorInfo;
    @FXML
    private Text greetingMain;
    @FXML
    private Text subtitle;
    @FXML
    private Text userID;
    @FXML
    private ImageView userIcon;
    @FXML
    private Text userLevel;

    private ArrayList<String> morning = new ArrayList<String>(Arrays.asList("睡的如何？", "美好的一天开始了！", "今天也要努力哦！"));//早晨副标题合集
    private ArrayList<String> afternoon = new ArrayList<String>(Arrays.asList("午饭如何？", "伸个懒腰吧！", "下午也要努力哦！")); //下午副标题合集
    private ArrayList<String> evening = new ArrayList<String>(Arrays.asList("晚餐如何？", "休息一下吧！", "明天也要努力哦！")); //晚上副标题合集
    private ArrayList<String> midnight = new ArrayList<String>(Arrays.asList("zzz...", "早点休息吧...", "还不睡觉吗？")); //深夜副标题合集
    public Random rand = new Random();
    private static loginMenu user = new loginMenu();

    @FXML
    public void timeGreeting(){//控制问候标题和副标题
        int randomNum = rand.nextInt(3);//生成一个在[0,3)中的随机数
        LocalDateTime currentTime = LocalDateTime.now(); //获取时间
        int hour = currentTime.getHour();
        System.out.println("获取的随机数" + randomNum);
        System.out.println("获取的小时：" + hour);
        userID.setText(user.getUserName());
        System.out.println("用户名：" + user.getUserName());
        if (hour >= 6 && hour < 12) //六点到十一点为早晨
        {
            greetingMain.setText("早上好！");
            subtitle.setText(morning.get(randomNum));
        }
        else if (hour >= 12 && hour < 18) //十二点到五点为下午
        {
            greetingMain.setText("下午好！");
            subtitle.setText(afternoon.get(randomNum));
        }
        else if (hour >= 18 && hour < 23) //六点到十一点为晚上
        {
            greetingMain.setText("晚上好！");
            subtitle.setText(evening.get(randomNum));
        }
        else //除此之外算作深夜
        {
            greetingMain.setText("深夜了...");
            subtitle.setText(midnight.get(randomNum));
        }
    }
    @FXML
    public void showBattle(){

    }
    @FXML
    public void showDecking(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("decking.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void showModification(){

    }
    @FXML
    public void showSetting(){

    }
    @FXML
    public void saveData(MouseEvent event) throws IOException {
        boolean dataFound = false;//是否找到目标
        if ((!userField.getText().isEmpty() && !userField.getText().contains(" ")) && (!passField.getText().isEmpty() && !passField.getText().contains(" "))){//如果用户输入不为空
            for(int x = 0; x < loginMenu.findUsers().size(); x++){
                System.out.println("库用户名检测|" + loginMenu.findUsers().get(x).substring(0,loginMenu.findUsers().get(x).indexOf(",")));
                System.out.println("库用户密码检测|" + loginMenu.findUsers().get(x).substring(loginMenu.findUsers().get(x).indexOf(",") + 1));
                if (userField.getText().equals(loginMenu.findUsers().get(x).substring(0,loginMenu.findUsers().get(x).indexOf(",")))){//检测库中是否有目标用户名
                    if(passField.getText().equals(loginMenu.findUsers().get(x).substring(loginMenu.findUsers().get(x).indexOf(",") + 1))){//检测密码是否正确
                        dataFound = true;
                        user.setUser(userField.getText(), passField.getText());
                        System.out.println("检测完毕，此时的userName是：" + user.getUserName());
                        break;//直接跳出
                    }
                }
            }
            //找到目标后
            if(dataFound){
                System.out.println("用户输入: " + userField.getText());
                System.out.println("密码输入: " + passField.getText());
                //跳转到主界面场景
                Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
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