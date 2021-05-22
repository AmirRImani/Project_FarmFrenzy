package input;

import controller.Level;
import controller.LevelsOperation;
import controller.Shop;

import java.util.ArrayList;

public class User {
    private String name;
    private int numberOfStars;
    private ArrayList<Awards> awards;
    private ArrayList<Integer> unlockedLevels;
    private Shop shop;

    public String getName() {
        return name;
    }

    public User(String name) {
        this.name = name;
    }

    public User() {

    }
    public void levelMenu(){
        LevelsOperation levelsOperation = new LevelsOperation();
    }
}
