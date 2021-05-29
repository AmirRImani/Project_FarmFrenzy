package controller;

import animals.wilds.Wilds;
import factories.Factories;
import input.User;
import sharedClasses.FileOperator;
import view.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LevelsOperation {
    //TODO
    public static final int NUMBER_OF_LEVELS = 95;
    private ArrayList<Level> levels;

    private static LevelsOperation levelsInstance;
    public static LevelsOperation getInstance(){
        if(levelsInstance == null)
            levelsInstance = new LevelsOperation();
        return levelsInstance;
    }

    private LevelsOperation() {
        FileOperator fileOperator = new FileOperator("levels.json");
        ArrayList<String> arrayList = fileOperator.loadFile(fileOperator.getFile(),0);
        if(!arrayList.isEmpty()) levels = typeChanger(arrayList);
    }

    private ArrayList<Level> typeChanger(ArrayList<String> arrayList) {
        ArrayList<Level> levels = new ArrayList<>();
        System.out.println(arrayList.toString());
        HashMap<Wilds,Integer> timeOfWilds = new HashMap<>();
        HashSet<Factories> neededFactories = new HashSet<>();
        HashMap<Factories,Integer> factoriesLevel = new HashMap<>();

        //TODO
        Pattern pattern = Pattern.compile("(\\{[^\\}]+\\})");
        Matcher matcher;
        Pattern patternNumber = Pattern.compile("number=([^,|.]+)");
        Matcher matcherNumber;
        Pattern patternTimeWilds = Pattern.compile("timeOfWilds=\\{([^\\}]+)\\}");
        Matcher matcherTimeWilds;
        Pattern patternTasks = Pattern.compile("tasks=([^,|.]+)");//TODO
        Matcher matcherTasks;
        Pattern patternFactories = Pattern.compile("neededFactories=\\[([^\\]]+)\\]");
        Matcher matcherFactories;
        Pattern patternFactorLev = Pattern.compile("factoriesLevel=\\{([^\\}]+)\\}");
        Matcher matcherFactorLev;
        Pattern patternGoldTime = Pattern.compile("goldTime=([^,|.]+)");
        Matcher matcherGoldTime;
        Pattern patternStartCoin = Pattern.compile("startCoin=([^,|.]+)");
        Matcher matcherStartCoin;

        String[] split = arrayList.toString().split("},");

        for (String s : split) {
            matcher = pattern.matcher(s+"}");
            matcher.find();
            for (int i = 0; i < matcher.groupCount(); i++) {
                matcherNumber = patternNumber.matcher(matcher.group(i));
                matcherTimeWilds = patternTimeWilds.matcher(matcher.group(i));
                matcherTasks = patternTasks.matcher(matcher.group(i));
                matcherFactories = patternFactories.matcher(matcher.group(i));
                matcherFactorLev = patternFactories.matcher(matcher.group(i));
                matcherGoldTime = patternFactories.matcher(matcher.group(i));
                matcherStartCoin = patternFactories.matcher(matcher.group(i));

                matcherNumber.find();
                matcherTimeWilds.find();
                matcherTasks.find();
                matcherFactories.find();
                matcherFactorLev.find();
                matcherGoldTime.find();
                matcherStartCoin.find();

                //TODO split of hashset and hashmap and loop

//                levels.add(new User(matcherName.group(1),
//                        matcherPassword.group(1),
//                        Integer.parseInt(matcherCoins.group(1)),
//                        Integer.parseInt(matcherUnlocked.group(1))));
            }
        }
        return levels;
    }

    private void save(){
        FileOperator fileOperator = new FileOperator("levels.json");
        try {
            fileOperator.saveFile(fileOperator.getFile(),levels);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean startLevelPossible(int level, User user){
        if(user.getUnlockedLevels() >= level)
            return true;
        return false;
    }

    public boolean getLevel(int level, User user){
        System.out.println();
        if(startLevelPossible(level, user)) {
            System.out.println("Level " + level + " started");
            startLevel(levels.get(level - 1), user);
            return true;
        } else {
            System.out.println("This level is locked for you");
            //TODO
            return false;
        }
    }


    public void startLevel(Level level, User user){
        //TODO
        HashSet<Task> tasks = level.getTasks();
        Game game = new Game(level, user);
    }
}
