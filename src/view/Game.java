package view;

import animals.domestics.Domestic;
import animals.domestics.Domestics;
import animals.helpers.Cat;
import animals.helpers.Dog;
import animals.helpers.Helper;
import animals.helpers.Helpers;
import animals.wilds.Wild;
import animals.wilds.Wilds;
import controller.*;
import workshops.Workshop;
import workshops.Workshops;
import input.User;
import products.Product;
import products.Products;
import sharedClasses.TimeProcessor;
import vehicles.Truck;

import java.util.HashMap;
import java.util.HashSet;

import static input.Menu.logger;

public class Game {
    private int coin;
    private HashSet<Domestic> domestics;
    private HashSet<Workshop> workshops;
    private HashMap<Wilds, int[]> wildsAppearance;
    private HashSet<Wild> wilds;
    private HashSet<Helper> helpers;
    private HashSet<Product> productsOnGround;
    private HashSet<Grass> grasses;
    private HashSet<Cage> cages;
    private Well well;
    private Warehouse warehouse;
    private Truck truck;
    private int goldTime;
    private int map;
    private HashSet<Task> tasks;

    public Game(Level level, User user) {
        this.coin = level.getStartCoin() + user.getNumberOfCoins();
        user.clearCoins();
        this.goldTime = level.getGoldTime();
        this.tasks = level.getTasks();
        this.wildsAppearance = level.getTimeOfWilds();
        //TODO
        this.well = new Well();
        this.warehouse = new Warehouse();
        this.truck = new Truck();
        this.cages = new HashSet<>();
        this.domestics = new HashSet<>();
        this.workshops = new HashSet<>();
        this.grasses = new HashSet<>();
        this.productsOnGround = new HashSet<>();
        this.wilds = new HashSet<>();
        this.helpers = new HashSet<>();
    }

    public void buyDome(Domestics domestic) {
        if(this.coin >= domestic.getValue()) {
            domestics.add(new Domestic(domestic));
            System.out.println("Buying has been done");
            logger.setUseParentHandlers(false);
            logger.fine("Buying has been done! ");
            this.coin -= domestic.getValue();
        } else
            System.out.println("Not enough coin to buy");
        logger.setUseParentHandlers(false);
        logger.info("Not enough coin to buy! ");
    }

    public void buyHelper(Helpers helper) {
        if(this.coin >= helper.getValue()) {
            helpers.add(new Helper(helper));
            System.out.println("Buying has been done");
            logger.setUseParentHandlers(false);
            logger.info("Buying has been done! ");

            this.coin -= helper.getValue();
        } else
            System.out.println("Not enough coin to buy");
        logger.setUseParentHandlers(false);
        logger.info("Not enough coin to buy! ");
    }


    public void pickup(int x, int y) {
        boolean found = false;
        boolean fullWarehouse = false;
        HashSet<Product> products = new HashSet<>(productsOnGround);
        if(x<1 || x>Board.COLUMN.getLength() || y<1 || y>Board.ROW.getLength()) {
            System.out.println("Coordinate is not on game board");
            logger.setUseParentHandlers(false);
            logger.info("Coordinate is not on game board ");

            return;
        }
        for (Product product : products) {
            if(product.getX() == x && product.getY() == y){
                found = true;
                if(this.warehouse.addProduct(Products.valueOf(product.getNameOfProduct()), 1)) {
                    this.productsOnGround.remove(product);
                    fullWarehouse = false;
                    System.out.println("Product " + product.getNameOfProduct() + " transferred to warehouse");
                } else
                    fullWarehouse = true;
            }
        }
        if(!found)
        {
            System.out.println("There isn't any product on this coordinate");
            logger.setUseParentHandlers(false);
            logger.info("There isn't any product on this coordinate ");
        }
        else if(fullWarehouse)
        {
            System.out.println("There isn't enough space in warehouse");
            logger.setUseParentHandlers(false);
            logger.info("here isn't enough space in warehouse!");
        }
    }

    public void plant(int x, int y) {
        if(x<1 || x>Board.COLUMN.getLength() || y<1 || y>Board.ROW.getLength()) {
            System.out.println("Coordinate is not on game board");
            logger.setUseParentHandlers(false);
            logger.info("Coordinate is not on game board!");

            return;
        }
        if(this.well.putGrass()) {
            grasses.add(new Grass(x, y));
            System.out.println("Grass planted");
            logger.setUseParentHandlers(false);
            logger.info("Grass planted");
        } else
            System.out.println("Water needed");
        logger.setUseParentHandlers(false);
        logger.info("Water needed");
    }

    public void work(String nameOfWorkshop) {
        for (Workshop workshop : workshops) {
            if(workshop.getName().equals(nameOfWorkshop)){
                if(workshop.work(warehouse))
                    System.out.println("Workshop " + workshop.getName() + " started to produce" /* + workshop.getProduct().getName()*/);
                logger.setUseParentHandlers(false);
                logger.fine("Workshop " + workshop.getName() + " started to produce");//TODO
                return;
            }
        }
        System.out.println("Workshop is incorrect");
        logger.setUseParentHandlers(false);
        logger.info("Workshop is incorrect!");
    }

    public void cage(int x, int y) {
        if(x<1 || x>Board.COLUMN.getLength() || y<1 || y>Board.ROW.getLength()) {
            System.out.println("Coordinate is not on game board");
            logger.setUseParentHandlers(false);
            logger.info("Coordinate is not on game board!");
            return;
        }
        for (Wild wild : wilds) {
            if(wild.getX() == x && wild.getX() == y){
                System.out.println("Cage");
                logger.setUseParentHandlers(false);
                logger.info("Cage");
                if(wild.isInCage()){
                    for (Cage cage : cages) {
                        if(cage.getX() == x && cage.getY() == y) {
                            cage.increaseTap();
                            //TODO sout needed in method
                            return;
                        }
                    }
                } else{
                    Cage newCage = new Cage(wild);
                    wild.setCage(true);
                    cages.add(newCage);
                    System.out.println("New cage on " + wild.getX() + ", " + wild.getY());
                    logger.setUseParentHandlers(false);
                    logger.fine("New cage on " + wild.getX() + ", " + wild.getY());
                    return;
                }
            }
        }
        System.out.println("There isn't any wild animal in this coordinate");
        logger.setUseParentHandlers(false);
        logger.info("There isn't any wild animal in this coordinate");
    }

    public void turn(int turnNumber) {
        TimeProcessor timeProcessor = TimeProcessor.getInstance();
        timeProcessor.changeSteps(turnNumber,this);
    }

    public void truckLoad(String productName) {
        this.warehouse.truckLoad(Products.valueOf(productName), this.truck);
    }

    public void truckUnload(String productName) {
        this.truck.unload(Products.valueOf(productName), this.warehouse);
    }

    public void build(String workshopName) {
        for (Workshop workshop : workshops) {
            if(workshop.getName().equals(workshopName)){
                System.out.println("This workshop is already built");
                logger.setUseParentHandlers(false);
                logger.info("his workshop is already built");
                return;
            }
        }
        for (Workshops workshop : Workshops.values()){
            if(workshop.name().equals(workshopName)) {
                Workshop workshop1 = new Workshop(workshop);
                workshops.add(workshop1);
                System.out.println("Built successfully");
                logger.setUseParentHandlers(false);
                logger.info("his workshop is already built");
                return;
            }
        }
        System.out.println("Workshop is incorrect");
        logger.setUseParentHandlers(false);
        logger.info("Workshop is incorrect");
    }

    public void well() {
        if (this.well.water()) {
            System.out.println("Watering well started");
            logger.setUseParentHandlers(false);
            logger.info("Watering well started.");
        }
        else
        {
            System.out.println("Can't start to water well");
        logger.setUseParentHandlers(false);
        logger.info("Workshop is incorrect");
    }}

    public void truckGo() {
        if (this.truck.transport()) {
            System.out.println("Transporting started");
            logger.setUseParentHandlers(false);
            logger.info("Workshop is incorrect");
        } else
        {
            System.out.println("Truck is on road");
        logger.setUseParentHandlers(false);
        logger.info("Workshop is incorrect");
    }}

    public void workshopProducts() {
        for (Workshop workshop : workshops) {
            if (workshop.isBusy()) {
                if (workshop.isProduced()) {
                    System.out.println(workshop.getName() + "'s work is done");
                    logger.setUseParentHandlers(false);
                    logger.info(workshop.getName() + "'s work is done");
                    //TODO change response or delete it
                    productsOnGround.add(new Product(workshop.getProducedProduct()));
                }
            }
        }
    }

    public void domesticProducts() {
        for (Domestic domestic : domestics) {
            if(domestic.isProduced()){
                System.out.println();//TODO change response or delete it
                productsOnGround.add(new Product(domestic.getProduct(),domestic.getX(),domestic.getY()));
            }
        }
    }

    public void feedAnimals() {
        for (Domestic domestic : domestics) {
            if(domestic.needToEat())
                onGrass(domestic);//TODO
        }

        HashSet<Grass> grassHashSet = new HashSet<>(grasses);

        for (Grass grass : grassHashSet) {
            Domestic dome = grass.moreImportant();
            if(dome != null) {
                dome.eat();
                grasses.remove(grass);
            }
        }
    }

    private boolean onGrass(Domestic domestic) {
        for (Grass grass : grasses) {
            if(grass.getColumn() == domestic.getY() && grass.getRow() == domestic.getY()) {
                grass.addDome(domestic);
                return true;
            }
        }
        return false;
    }

    public void appearWilds() {
        for (Wilds wild : Wilds.values()) {
            if(wildsAppearance.containsKey(wild)){
                timeWild(wild, wildsAppearance.get(wild));//TODO
            }
        }
    }

    private void timeWild(Wilds wild, int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            if(ints[i] == TimeProcessor.currentStep)
                wilds.add(new Wild(wild));
        }
    }

    public void disappearProducts() {
        HashSet<Product> products = new HashSet<>(productsOnGround);
        for (Product product : products) {
            if(product.spoil())
                productsOnGround.remove(product);
        }
    }

    public void freeWilds() {
        HashSet<Cage> cageHashSet = new HashSet<>(cages);
        for (Cage cage : cageHashSet) {
            if(cage.isPrisoned()) {
                if (cage.free()) {
                    cages.remove(cage);
                    wilds.remove(cage.getWild());
                    System.out.println("Wild on " + cage.getX() + "," + cage.getY() + " was freed");
                    logger.setUseParentHandlers(false);
                    logger.info("Wild on " + cage.getX() + "," + cage.getY() + " was freed");
                }
            }
        }
    }

    public void decreaseCageResist() {
        HashSet<Cage> cageHashSet = new HashSet<>(cages);
        for (Cage cage : cageHashSet) {
            if(cage.decreaseTap())
                cages.remove(cage);
        }
    }


    public void dogAttack() {
        HashSet<Helper> helperHashSet = new HashSet<>(helpers);
        for (Helper helper : helperHashSet) {
            if(helper instanceof Dog){
                onWild(helper);//TODO
            }
        }
    }

    private void onWild(Helper helper) {
        HashSet<Wild> wildHashSet = new HashSet<>(wilds);
        for (Wild wild : wildHashSet) {
            if(!wild.isInCage()){
                if(wild.getX() == helper.getX() && wild.getY() == helper.getY()){
                    System.out.println("Dog on " + helper.getX() + "," + helper.getY() + " attacked a wild");
                    logger.setUseParentHandlers(false);
                    logger.info("Dog on " + helper.getX() + "," + helper.getY() + " attacked a wild");
                    wilds.remove(wild);
                    helpers.remove(helper);
                }
            }
        }
    }

    public void catCatches() {
        for (Helper helper : helpers) {
            if(helper instanceof Cat){
                onProduct(helper.getX(), helper.getY());//TODO
            }
        }
    }

    private void onProduct(int x, int y) {
        HashSet<Product> products = new HashSet<>(productsOnGround);
        for (Product product : products) {
            if(product.getX() == x && product.getY() == y){
                System.out.println("Product on " + x + "," + y + " moved to warehouse by cat");
                logger.setUseParentHandlers(false);
                logger.info("Product on " + x + "," + y + " moved to warehouse by cat");
                productsOnGround.remove(product);
                warehouse.addProduct(Products.valueOf(product.getNameOfProduct()),1);//TODO
            }
        }
    }

    public void wildAttack() {
        for (Wild wild : wilds) {
            if(!wild.isInCage()){
                onDome(wild.getX(), wild.getY());//TODO
            }
        }
    }

    private void onDome(int x, int y) {
        HashSet<Domestic> domesticHashSet = new HashSet<>(domestics);
        for (Domestic domestic : domesticHashSet) {
            if(domestic.getX() == x && domestic.getY() == y){
                System.out.println("Wild on " + x + "," + y + " attacked a domestic");
                logger.setUseParentHandlers(false);
                logger.info("Wild on " + x + "," + y + " attacked a domestic");
                domestics.remove(domestic);
            }
        }
    }

    public void walk() {
        for (Domestic domestic : domestics)
            domestic.walk();
        for (Helper helper : helpers)
            helper.walk();
        for (Wild wild : wilds) {
            if(!wild.isPrisoned())
                wild.walk();
        }
    }

    public void showDetails() {
        //TODO to show information after time change
    }

    public void checkWin() {
        //TODO if user did all tasks of level
    }
}
