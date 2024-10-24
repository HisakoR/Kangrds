package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//这是一串测试 This is a test statement

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

    @FXML
    private Text profileName;
    @FXML
    private Text profileID;
    @FXML
    private Text profileSign;
    @FXML
    private Text profileLevel;
    @FXML
    private ImageView profileIcon;

    private ArrayList<String> morning = new ArrayList<String>(Arrays.asList("睡的如何？", "美好的一天开始了！", "今天也要努力哦！"));//早晨副标题合集
    private ArrayList<String> afternoon = new ArrayList<String>(Arrays.asList("午饭如何？", "伸个懒腰吧！", "下午也要努力哦！")); //下午副标题合集
    private ArrayList<String> evening = new ArrayList<String>(Arrays.asList("晚餐如何？", "休息一下吧！", "明天也要努力哦！")); //晚上副标题合集
    private ArrayList<String> midnight = new ArrayList<String>(Arrays.asList("zzz...", "早点休息吧...", "还不睡觉吗？")); //深夜副标题合集
    public Random rand = new Random();
    private static loginMenu user = new loginMenu();

    //切换场景的方法，参数1鼠标事件，参数二场景路径
    public FXMLLoader setScene(MouseEvent event, String resource) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //返回loader以便使用controller
        return loader;
    }

    //设置标题的方法
    public void setGreeting(String greeting, String sub) {
        greetingMain.setText(greeting);
        subtitle.setText(sub);
    }
    @FXML
    public void timeGreeting() throws MalformedURLException {//控制问候标题和副标题
        int randomNum = rand.nextInt(3);//生成一个在[0,3)中的随机数
        LocalDateTime currentTime = LocalDateTime.now(); //获取时间
        int hour = currentTime.getHour();
        System.out.println("获取的随机数" + randomNum);
        System.out.println("获取的小时：" + hour);
        userID.setText(user.getUserName());
        //===*20241022添加可能有bug===
        System.out.println("检测用户图片路径：" + user.getPathAvatar());
        Image image = new Image("file:" + user.getPathAvatar());
        userIcon.setImage(image);
        //===========================
        userLevel.setText(user.getUserLevel());
        System.out.println("用户名：" + user.getUserName());
        if (hour >= 6 && hour < 12) {
            setGreeting("早上好！", morning.get(randomNum));
        }
        else if (hour >= 12 && hour < 18) {
            setGreeting("下午好！", afternoon.get(randomNum));
        }
        else if (hour >= 18 && hour < 23) {
            setGreeting("晚上好！", evening.get(randomNum));
        }
        else {
            setGreeting("深夜了...", midnight.get(randomNum));
        }
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
    public void showBattle(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("battle.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //设置用户资料界面
    @FXML
    public void profileController(){
        Image image = new Image("file:" + user.getPathAvatar());
        profileID.setText(user.getID());
        profileIcon.setImage(image);
        profileLevel.setText(String.valueOf(user.getLevel()));
        profileName.setText(user.getUserName());
        profileSign.setText(user.getIntroduction());
    }

    //=====20241023记得将其他界面格式替换为此方法中的实例以便维护=====
    @FXML
    public void showProfile(MouseEvent event) throws IOException {
        Controller controller = setScene(event, "profile.fxml").getController();
        controller.profileController();
    }
    //==========================================================

    @FXML
    public void showModification(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("modification.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void showSetting(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("setting.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void saveData(MouseEvent event) throws IOException {
        boolean dataFound = false;//是否找到目标
        if ((!userField.getText().isEmpty() && !userField.getText().contains(" ")) && (!passField.getText().isEmpty() && !passField.getText().contains(" "))){//如果用户输入不为空
            for(int x = 0; x < loginMenu.findUsers().size(); x++){
                System.out.println("库用户名检测|" + loginMenu.findUsers().get(x).substring(0,loginMenu.findUsers().get(x).indexOf(",")));
                if (userField.getText().equals(loginMenu.findUsers().get(x).substring(0,loginMenu.findUsers().get(x).indexOf(",")))){//检测库中是否有目标用户名
                    if(passField.getText().equals(user.userLine(userField.getText())[1])){//检测密码是否正确
                        System.out.println("库用户密码检测|" + user.userLine(userField.getText())[1]);
                        dataFound = true;
                        user.setUser(user.userLine(userField.getText()));
                        break;//直接跳出
                    }
                }
            }
            //找到目标后
            if(dataFound){
                System.out.println("用户输入: " + userField.getText());
                System.out.println("密码输入: " + passField.getText());

                //跳转到主界面场景
                FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
                Parent root = loader.load();
                Controller controller = loader.getController();//调取目标场景的controller的控制器
                controller.timeGreeting();//使用控制器调用timeGreeting
                mediaPlayer.playTarget("src/audio/theProcess.wav");

                //显示界面
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
                user.saveUser(userField.getText(), passField.getText());
                errorInfo.setText("注册成功");
                System.out.println("用户信息已录入");
            }
        }
    }
    //通用 - 返回主菜单的方法
    @FXML
    public void returnMain(MouseEvent event) throws IOException {
        System.out.println("返回已调用");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();//调取目标场景的controller的控制器
        controller.timeGreeting();//使用控制器调用timeGreeting

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}