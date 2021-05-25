package controller;

import input.User;
import view.Game;

import java.util.ArrayList;
import java.util.HashSet;

public class LevelsOperation {
    //TODO
    public static final int NUMBER_OF_LEVELS = 95;
    private ArrayList<Level> levels;

    private boolean startLevelPossible(int level, User user){
        //TODO
        return false;
    }

    public boolean getLevel(int level, User user){
        System.out.println();
        if(startLevelPossible(level, user)) {
            System.out.println("Level " + level + " started");
            startLevel(levels.get(level - 1));
            return true;
        } else {
            System.out.println("This level is locked for you");
            //TODO
            return false;
        }
    }


    public void startLevel(Level level){
        //TODO
        HashSet<Task> tasks = level.getTasks();
        Game game = new Game(level);
    }
}
