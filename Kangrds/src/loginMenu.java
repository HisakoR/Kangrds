import java.util.Scanner;

public class loginMenu{
    private String userName;
    private String password;
    private boolean isOn = true;
    public static Scanner scan = new Scanner(System.in);
    private String input;

    public void login(){
        mainMenu main = new mainMenu();
        while(isOn){
            if (!main.isExit()){
                break;
            }
            System.out.println("Please input your user name:");
            userName = loginMenu.scan.nextLine();
            System.out.println("Please input your password:");
            password = loginMenu.scan.nextLine();
            System.out.println("Welcome to Kangrds!");
            main.menu();
        }
    }
}
