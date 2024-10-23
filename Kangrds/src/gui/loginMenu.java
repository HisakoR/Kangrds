package gui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class loginMenu{
    private String userID;
    private String userName;
    private String password;
    private String introduction;
    private String pathAvatar;
    private static int userLevel = 1;

    private static String routePath = "src/userInfo/user.udt";

    //对外方法
    public loginMenu(){
        //Constructor
    }

    //扫描udt文件目标，输出为arraylist
    public static ArrayList<String> findUsers(){
        int cal = 0;
        ArrayList<String> users = new ArrayList<String>();
        try (Scanner scanner = new Scanner(new File(routePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                users.add(line);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    //使用用户名搜索目标用户行，并按逗号分割输出为string数列
    public String[] userLine(String userName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(routePath));
        String[] userInfo = new String[6];
        String line;
        while ((line = reader.readLine()) != null) {
            userInfo = line.split(","); //按逗号分隔

            if (userInfo.length > 0 && userInfo[0].equals(userName)) {
                return userInfo;
            }
        }
        return userInfo;
    }

    //注册的方法
    public void saveUser(String userName, String password){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(routePath, true))) {
            bw.newLine();        //添加换行符
            bw.write(userName + "," + password + "," + createID() + "," + getUserLevel() + "," + "一把十个手" + "," + "src/userInfo/avatars/default.png");
            System.out.println("用户信息已添加: " + userName + "," + password);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建新id的方法
    public String createID() throws IOException {
        int num = 1;
        BufferedReader reader = new BufferedReader(new FileReader(routePath));
        String[] userInfo = new String[6];
        String line;
        while ((line = reader.readLine()) != null) {
            userInfo = line.split(","); //按逗号分隔
            if (userInfo.length > 0 && Integer.parseInt(userInfo[2]) == num) {
                num++;
            }
        }
        int cal = 0;
        if (num/10000 == 0){
            cal++;
            if (num/1000 == 0){
                cal++;
                if (num/100 == 0){
                    cal++;
                    if (num/10 == 0){
                        cal++;
                    }
                }
            }
        }
        String result = "";
        for (int x = 0; x < cal; x++){
            result = result + "0";
        }
        result = result + String.valueOf(num);
        return result;
    }

    //对外接口
    public String getUserName(){
        return userName;
    }
    //====别删====
    public String getPassword(){
        return password;
    }
    //==========
    public String getPathAvatar(){
        return pathAvatar;
    }
    public String getIntroduction(){
        return introduction;
    }
    public String getID(){
        return userID;
    }
    public int getLevel(){
        return userLevel;
    }

    public static String getUserLevel(){
        return String.valueOf(userLevel);
    }
    //输入用户行的string数列，整合内容并保存为当前用户
    public void setUser(String[] userLine){
        userName = userLine[0];
        password = userLine[1];
        userID = userLine[2];
        userLevel = Integer.parseInt(userLine[3]);
        introduction = userLine[4];
        pathAvatar = userLine[5];
        System.out.println("=====当前的用户=====");
        System.out.println("用户名|" + userLine[0]);
        System.out.println("密码|" + userLine[1]);
        System.out.println("ID|" + userLine[2]);
        System.out.println("等级|" + userLine[3]);
        System.out.println("简介|" + userLine[4]);
        System.out.println("头像位置|" + userLine[5]);
        System.out.println("=====当前的用户=====");
    }
}