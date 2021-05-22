package controller;

import view.Game;

import java.util.ArrayList;
import java.util.HashSet;

public class LevelsOperation {
    public static final int NUMBER_OF_LEVELS = 95;
    private ArrayList<Level> levels;

    private boolean startLevelPossible(int level){
        //TODO
        return false;
    }

    private void getLevel(int level){
        if(startLevelPossible(level))
            startLevel(levels.get(level - 1));
        //TODO
    }


    public void startLevel(Level level){
        //TODO
        HashSet<Task> tasks = level.getTasks();
        Game game = new Game(level);
    }
}
