package entry;

public class User {
    private String userName;
    private String password;
    private String email;
    private int numberOfCoins;
    private int unlockedLevels;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return userName;
    }

    public String getPassword() { return password; }

    public String getEmail() { return email; }

    public int getNumberOfCoins() { return numberOfCoins; }

    public int getUnlockedLevels() { return unlockedLevels; }

    public User(String name, String password, String email) {
        this.userName = name;
        this.password = password;
        this.email = email;
        this.numberOfCoins = 0;
        this.unlockedLevels = 1;
    }

    public User() {

    }

    public User(String userName, String password, String email, int numberOfCoins, int unlockedLevels) {
        this.userName = userName;
        this.password = password;
        this.email = email;
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
