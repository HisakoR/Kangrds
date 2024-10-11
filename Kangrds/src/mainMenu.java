import java.util.Scanner;

public class mainMenu {
    private Scanner player = new Scanner(System.in);
    private boolean isOn = true;
    private int input = 0;
    public boolean isExit(){
        return isOn;
    }
    public void menu(){
        while(isOn){
            System.out.println("=====================================");
            System.out.println("=                kangrds            =");
            System.out.println("=====================================");
            System.out.println("=[1]start                           =");
            System.out.println("=[2]setting                         =");
            System.out.println("=[3]exit                            =");
            System.out.println("=====================================");
            String inputGet = player.nextLine();
            try {
                input = Integer.parseInt(inputGet);
            }
            catch (NumberFormatException ignored) {

            }
            if(input == 1){
                //event
            }
            else if(input == 2){
                //event
            }
            else if(input == 3){
                isOn = false;
            }
            else {
                System.out.println("unknown command");
            }
        }
    }
}
