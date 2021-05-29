package controller;

import animals.wilds.Wild;
import animals.wilds.Wilds;
import factories.Factories;

import java.util.HashMap;
import java.util.HashSet;

public class Level {
    private int number;
    private HashMap<Wilds,Integer> timeOfWilds;
    private HashSet<Task> tasks;
    //TODO  make enum of names like factory names
    private HashSet<Factories> neededFactories;
    private HashMap<Factories, Integer> factoriesLevel;
    int goldTime;
    int startCoin;

    public HashSet<Task> getTasks() { return tasks; }

    public HashMap<Wilds, Integer> getTimeOfWilds() { return timeOfWilds; }

    public HashSet<Factories> getNeededFactories() { return neededFactories; }

    public HashMap<Factories, Integer> getFactoriesLevel() { return factoriesLevel; }

    public int getGoldTime() { return goldTime; }

    public int getStartCoin() { return startCoin; }

    public Level(int number, HashMap<Wilds, Integer> timeOfWilds, HashSet<Task> tasks, HashSet<Factories> neededFactories, HashMap<Factories, Integer> factoriesLevel, int goldTime, int startCoin) {
        this.number = number;
        this.timeOfWilds = timeOfWilds;
        this.tasks = tasks;
        this.neededFactories = neededFactories;
        this.factoriesLevel = factoriesLevel;
        this.goldTime = goldTime;
        this.startCoin = startCoin;
    }
}

