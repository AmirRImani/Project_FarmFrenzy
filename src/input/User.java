package input;

import controller.LevelsOperation;

import java.util.HashSet;

public class User {
    private String userName;
    private String password;
    private int numberOfStars;
    private HashSet<Integer> unlockedLevels;
    //private Shop shop;

    public String getUserName() {
        return userName;
    }

    public String getPassword() { return password; }

    public User(String name, String password) {
        this.userName = name;
        this.password = password;
        this.numberOfStars = 0;
    }

    public User() {

    }

}
