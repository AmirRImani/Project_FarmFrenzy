package controller;

import animals.wilds.Wild;
import controller.Task;
import factories.Factory;

import java.util.HashMap;
import java.util.HashSet;

public class Level {
    private int number;
    private HashMap<Wild,Integer> timeOfWilds;
    private HashSet<Task> tasks;
    //TODO  make enum of names like factory names
    private HashSet<Factory> neededFactories;
    private HashMap<Factory, Integer> factoriesLevel;
    int goldTime;
    int startCoin;

    public HashSet<Task> getTasks() { return tasks; }

    public HashMap<Wild, Integer> getTimeOfWilds() { return timeOfWilds; }

    public HashSet<Factory> getNeededFactories() { return neededFactories; }

    public HashMap<Factory, Integer> getFactoriesLevel() { return factoriesLevel; }

    public int getGoldTime() { return goldTime; }

    public int getStartCoin() { return startCoin; }

}

