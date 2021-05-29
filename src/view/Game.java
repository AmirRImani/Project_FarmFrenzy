package view;

import animals.domestics.Domestic;
import animals.wilds.Wilds;
import controller.*;
import factories.Factory;
import input.User;
import products.Product;
import vehicles.Truck;

import java.util.HashMap;
import java.util.HashSet;

public class Game {
    private int coin;
    private HashSet<Domestic> domestics;
    private HashSet<Factory> factories;
    private HashMap<Wilds,Integer> wildsPresent = new HashMap<>();
    private HashSet<Product> productsOnGround = new HashSet<>();
    private HashSet<Grass> grasses = new HashSet<>();
    private Well well;
    private Cage cage;
    private Warehouse warehouse;
    private Truck truck;
    private int startCoin;
    private int goldTime;
    private int map;
    private HashSet<Task> tasks;

    public Game(Level level, User user) {
        this.startCoin = level.getStartCoin() + user.getNumberOfCoins();
        user.clearCoins();
        this.goldTime = level.getGoldTime();
        this.tasks = level.getTasks();
        this.wildsPresent = level.getTimeOfWilds();
        //TODO
    }

}
