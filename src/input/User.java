package input;

import java.util.HashSet;

public class User {
    private String userName;
    private String password;
    private int numberOfCoins;
    private int unlockedLevels;
    //private Shop shop;

    public String getUserName() {
        return userName;
    }

    public String getPassword() { return password; }

    public int getNumberOfCoins() { return numberOfCoins; }

    public int getUnlockedLevels() { return unlockedLevels; }

    public User(String name, String password) {
        this.userName = name;
        this.password = password;
        this.numberOfCoins = 0;
        this.unlockedLevels = 1;
    }

    public User() {

    }

    public User(String userName, String password, int numberOfCoins, int unlockedLevels) {
        this.userName = userName;
        this.password = password;
        this.numberOfCoins = numberOfCoins;
        this.unlockedLevels = unlockedLevels;
    }

    public void clearCoins(){
        this.numberOfCoins = 0;
    }

    public void nextLevel() {
        unlockedLevels ++;
    }

    public void award(int award) {
        numberOfCoins += award;
    }
}
