package gui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class loginMenu{
    private String userName;
    private String password;
    private static String routePath = "src/userInfo/user.udt";

    //对外方法
    public loginMenu(){

    }
    //udt文件目标，输出为arraylist
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
    //注册的方法
    public static void saveUser(String userName, String password){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(routePath, true))) {
            bw.newLine();        //添加换行符
            bw.write(userName + "," + password);
            System.out.println("用户信息已添加: " + userName + "," + password);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    //对外接口
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    public void setUser(String name, String pass){
        userName = name;
        password = pass;
    }
}