import java.util.Scanner;

public class loginMenu{
    private String userName;
    private String password;
    public static Scanner scan = new Scanner(System.in);

    public void login(){
        mainMenu main = new mainMenu();
        System.out.println("Please input your user name:");
        userName = loginMenu.scan.nextLine();
        System.out.println("Please input your password:");
        password = loginMenu.scan.nextLine();
        System.out.println("Welcome to Kangrds!");
        main.menu();
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
}
