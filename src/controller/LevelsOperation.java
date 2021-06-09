package controller;

import animals.domestics.Domestics;
import animals.wilds.Wilds;
import input.User;
import products.Products;
import sharedClasses.FileOperator;
import view.Game;
import view.Input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
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

        Pattern pattern = Pattern.compile("\\{(.+)\\}");
        Matcher matcher;
        Pattern patternNumber = Pattern.compile("number=([^,|\\.]+)");
        Matcher matcherNumber;
        Pattern patternTimeWilds = Pattern.compile("timeOfWilds=\\{([^\\}]+)\\}");
        Matcher matcherTimeWilds;
        Pattern patternWild = Pattern.compile("(\\w+)=");
        Matcher matcherWild;
        Pattern patternTime = Pattern.compile("(\\d+)\\.\\d+");
        Matcher matcherTime;
        Pattern patternTasks = Pattern.compile("tasks=\\[([^\\]]+)\\]");
        Matcher matcherTasks;
        Pattern patternType = Pattern.compile("type=(\\w+)");
        Matcher matcherType;
        Pattern patternProduct = Pattern.compile("typeOfProduct=(\\w+)");
        Matcher matcherProduct;
        Pattern patternDomestic = Pattern.compile("typeOfDomestic=(\\w+)");
        Matcher matcherDomestic;
        Pattern patternTarget = Pattern.compile("target=(\\d+)\\.\\d+");
        Matcher matcherTarget;
//        Pattern patternFactories = Pattern.compile("neededFactories=\\[([^\\]]+)\\]");
//        Matcher matcherFactories;
//        Pattern patternFactorLev = Pattern.compile("factoriesLevel=\\{([^\\}]+)\\}");
//        Matcher matcherFactorLev;
        Pattern patternGoldTime = Pattern.compile("goldTime=([^,|\\.]+)");
        Matcher matcherGoldTime;
        Pattern patternStartCoin = Pattern.compile("startCoin=([^,|\\.]+)");
        Matcher matcherStartCoin;
        Pattern patternAward = Pattern.compile("award=([^,|\\.]+)");
        Matcher matcherAward;

        String[] split = arrayList.toString().split("\\}, \\{n");

        for (String s : split) {
            HashMap<Wilds,Integer> timeOfWilds = new HashMap<>();
            HashSet<Task> tasks = new HashSet<>();
//            HashSet<Workshops> neededFactories = new HashSet<>();
//            HashMap<Workshops,Integer> factoriesLevel = new HashMap<>();
            matcher = pattern.matcher("}, {n" + s);
            matcher.find();

            for (int i = 0; i < matcher.groupCount(); i++) {
                matcherNumber = patternNumber.matcher(matcher.group(i));
                matcherTimeWilds = patternTimeWilds.matcher(matcher.group(i));
                matcherTasks = patternTasks.matcher(matcher.group(i) + "]");
//                matcherFactories = patternFactories.matcher(matcher.group(i));
//                matcherFactorLev = patternFactories.matcher(matcher.group(i));
                matcherGoldTime = patternGoldTime.matcher(matcher.group(i));
                matcherStartCoin = patternStartCoin.matcher(matcher.group(i));
                matcherAward = patternAward.matcher(matcher.group(i));

                matcherNumber.find();
                matcherTimeWilds.find();
                matcherTasks.find();
//                matcherFactories.find();
//                matcherFactorLev.find();
                matcherGoldTime.find();
                matcherStartCoin.find();
                matcherAward.find();

                String[] splitWilds = matcherTimeWilds.group(1).split(",");
                //TODO split of hashset and hashmap and loop
                for (String splitWild : splitWilds) {
                    matcherWild = patternWild.matcher(splitWild);
                    matcherTime = patternTime.matcher(splitWild);
                    matcherWild.find();
                    matcherTime.find();
                    timeOfWilds.put(Wilds.valueOf(matcherWild.group(1)), Integer.parseInt(matcherTime.group(1)));
                }

                String[] splitTasks = matcherTasks.group(1).split("},");
                for (String splitTask : splitTasks) {
                    matcherType = patternType.matcher(splitTask);
                    matcherDomestic = patternDomestic.matcher(splitTask);
                    matcherProduct = patternProduct.matcher(splitTask);
                    matcherTarget = patternTarget.matcher(splitTask);
                    matcherType.find();
                    matcherDomestic.find();
                    matcherProduct.find();
                    matcherTarget.find();
                    if(matcherType.group(1).equals("COIN"))
                        tasks.add(new Task(Tasks.valueOf("COIN"), Integer.parseInt(matcherTarget.group(1))));
                    else if(matcherType.group(1).equals("CATCH"))
                        tasks.add(new Task(Tasks.valueOf("CATCH"), Products.valueOf(matcherProduct.group(1)), Integer.parseInt(matcherTarget.group(1))));
                    else if(matcherType.group(1).equals("DOMESTIC"))
                        tasks.add(new Task(Tasks.valueOf("DOMESTIC"), Domestics.valueOf(matcherDomestic.group(1)), Integer.parseInt(matcherTarget.group(1))));
                }


                levels.add(new Level(Integer.parseInt(matcherNumber.group(1)), Integer.parseInt(matcherAward.group(1)),
                        Integer.parseInt(matcherGoldTime.group(1)), Integer.parseInt(matcherStartCoin.group(1)), timeOfWilds, tasks));
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

    public boolean getLevel(int level, User user, Scanner scanner){
        System.out.println();
        if(startLevelPossible(level, user)) {
            System.out.println("Level " + level + " started");
            startLevel(levels.get(level - 1), user, scanner);
            return true;
        } else {
            System.out.println("This level is locked for you");
            //TODO
            return false;
        }
    }


    public void startLevel(Level level, User user, Scanner scanner){
        //TODO
        HashSet<Task> tasks = level.getTasks();
        Game game = new Game(level, user);
        Input input = new Input();
        while(true){
            input.commandGetter(scanner, game);
        }
    }
}
