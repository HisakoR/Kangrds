public class loginMenu{
    private String userName;
    private String password;

    public loginMenu(String name, String pass){
        this.userName = name;
        this.password = pass;
    }

    public void login(){
        mainMenu main = new mainMenu();
        main.menu();
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
}