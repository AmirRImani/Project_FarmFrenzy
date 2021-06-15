package controller;

import animals.wilds.Wilds;

import java.util.HashMap;
import java.util.HashSet;

public class Level {
    private int number;
    int goldTime;
    int award;
    int startCoin;
    private HashMap<Wilds,int[]> timeOfWilds;
    private HashSet<Task> tasks;
    //TODO  make enum of names like factory names
    //private HashSet<Workshops> neededFactories;
    //private HashMap<Workshops, Integer> factoriesLevel;



    public HashSet<Task> getTasks() { return tasks; }

    public HashMap<Wilds, int[]> getTimeOfWilds() { return timeOfWilds; }

    //public HashSet<Workshops> getNeededFactories() { return neededFactories; }

    //public HashMap<Workshops, Integer> getFactoriesLevel() { return factoriesLevel; }

    public int getGoldTime() { return goldTime; }

    public int getStartCoin() { return startCoin; }

    public Level(int number, int goldTime, int award, int startCoin, HashMap<Wilds, int[]> timeOfWilds, HashSet<Task> tasks/*, HashSet<Workshops> neededFactories, HashMap<Workshops, Integer> factoriesLevel*/) {
        this.number = number;
        this.goldTime = goldTime;
        this.award = award;
        this.startCoin = startCoin;
        this.timeOfWilds = timeOfWilds;
        this.tasks = tasks;
        //TODO
        //this.neededFactories = neededFactories;
        //this.factoriesLevel = factoriesLevel;

    }
}

