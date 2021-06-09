package controller;

import animals.wilds.Wild;
import animals.wilds.Wilds;
import factories.Factories;

import java.util.HashMap;
import java.util.HashSet;

public class Level {
    private int number;
    int goldTime;
    int startCoin;
    private HashMap<Wilds,Integer> timeOfWilds;
    private HashSet<Task> tasks;
    //TODO  make enum of names like factory names
    //private HashSet<Factories> neededFactories;
    //private HashMap<Factories, Integer> factoriesLevel;


    public HashSet<Task> getTasks() { return tasks; }

    public HashMap<Wilds, Integer> getTimeOfWilds() { return timeOfWilds; }

    //public HashSet<Factories> getNeededFactories() { return neededFactories; }

    //public HashMap<Factories, Integer> getFactoriesLevel() { return factoriesLevel; }

    public int getGoldTime() { return goldTime; }

    public int getStartCoin() { return startCoin; }

    public Level(int number, int goldTime, int startCoin, HashMap<Wilds, Integer> timeOfWilds, HashSet<Task> tasks/*, HashSet<Factories> neededFactories, HashMap<Factories, Integer> factoriesLevel*/) {
        this.number = number;
        this.goldTime = goldTime;
        this.startCoin = startCoin;
        this.timeOfWilds = timeOfWilds;
        this.tasks = tasks;
        //TODO
        //this.neededFactories = neededFactories;
        //this.factoriesLevel = factoriesLevel;

    }
}

