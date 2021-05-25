package view;

import animals.domestics.Domestic;
import animals.wilds.Wild;
import controller.*;
import factories.Factory;
import products.Product;
import vehicles.Truck;

import java.util.HashSet;

public class Game {
    int coin;
    private HashSet<Domestic> domestics;
    private HashSet<Factory> factories;
    private HashSet<Wild> wildsPresent = new HashSet<>();
    private HashSet<Product> productsOnGround = new HashSet<>();
    private HashSet<Grass> grasses = new HashSet<>();
    private Well well;
    private Cage cage;
    private Warehouse warehouse;
    private Truck truck;
    private Helicopter helicopter;
    private int startCoin;
    private int goldTime;
    private int map;
    private HashSet<Task> tasks;

    public Game(Level level) {
        this.startCoin = level.getStartCoin();
        this.goldTime = level.getGoldTime();
        this.tasks = level.getTasks();
        //TODO
    }

}
