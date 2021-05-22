package input;

import java.util.ArrayList;

public class Menu {
    private ArrayList<User> users;
    private User user = new User();
    public void checkUser(String name){
        boolean isFound =false;
        for (User user1 : users) {
            if (user1.getName().equals(name)) {
                user = user1;
                isFound = true;
            }
        }
        if(!isFound)
            signUp(name);

    }
    public void signUp(String name){
        user = new User(name);
    }
    public void exit(){

    }
}
