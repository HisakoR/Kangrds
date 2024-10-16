package gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class mainController {
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

    public void timeGreeting(){
        int randomNum = (int)(Math.random()*10) + 3;
        LocalDateTime currentTime = LocalDateTime.now(); //获取时间
        int hour = currentTime.getHour();

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
}
