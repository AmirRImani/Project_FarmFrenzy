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
import input.User;
import products.Product;
import products.Products;
import sharedClasses.TimeProcessor;
import vehicles.Truck;
import workshops.Workshop;
import workshops.Workshops;

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
    private int award;
    private HashSet<Task> tasks;

    public int getGoldTime() { return goldTime; }

    public int getAward() { return award; }

    public Game(Level level, User user) {
        this.coin = level.getStartCoin() + user.getNumberOfCoins();
        user.clearCoins();
        this.goldTime = level.getGoldTime();
        this.award = level.getAward();
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
                    System.out.println("Workshop " + workshop.getName() + " started to produce");
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
        boolean found = false;
        HashSet<Wild> wildHashSet = new HashSet<>(wilds);
        if(x<1 || x>Board.COLUMN.getLength() || y<1 || y>Board.ROW.getLength()) {
            System.out.println("Coordinate is not on game board");
            logger.setUseParentHandlers(false);
            logger.info("Coordinate is not on game board!");
            return;
        }
        for (Wild wild : wildHashSet) {
            if(wild.getX() == x && wild.getY() == y){
                found = true;

                if(wild.isPrisoned()){
                    warehouse.addProduct(Products.valueOf("CAUGHT_" + wild.getName()), 1);
                    wilds.remove(wild);
                    System.out.println("Wild " +  wild.getName() + " on [" + wild.getX() + " " + wild.getY() + " ] has been caught");
                    logger.setUseParentHandlers(false);
                    logger.fine("Wild " +  wild.getName() + " on [" + wild.getX() + " " + wild.getY() + " ] has been caught");
                    return;
                }
                if(wild.isInCage()){
                    for (Cage cage : cages) {
                        if(cage.getX() == x && cage.getY() == y) {
                            if(cage.increaseTap()) {
                                wild.increaseTap();
                                System.out.println("Cage on [ " + cage.getX() + " " + cage.getY() + " ] resistance increased");
                                logger.setUseParentHandlers(false);
                                logger.fine("Cage on [ " + cage.getX() + " " + cage.getY() + " ] resistance increased");
                            } else {
                                System.out.println("Cage can't be used. You used it on this cage in this step");
                                logger.setUseParentHandlers(false);
                                logger.info("Cage can't be used. You used it on this cage in this step");
                            }
                            //TODO sout needed in method
                            return;
                        }
                    }
                } else{
                    Cage newCage = new Cage(wild);
                    wild.setCage(true);
                    cages.add(newCage);
                    wild.increaseTap();
                    System.out.println("New cage on " + wild.getX() + ", " + wild.getY());
                    logger.setUseParentHandlers(false);
                    logger.fine("New cage on " + wild.getX() + ", " + wild.getY());
                    return;
                }
            }
        }
        if(!found) {
            System.out.println("There isn't any wild animal in this coordinate");
            logger.setUseParentHandlers(false);
            logger.info("There isn't any wild animal in this coordinate");
        }
    }

    public boolean turn(int turnNumber) {
        boolean exit;
        TimeProcessor timeProcessor = TimeProcessor.getInstance();
        exit = timeProcessor.changeSteps(turnNumber,this);
        return exit;
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
                if(coin >= workshop.getCost()) {
                    Workshop workshop1 = new Workshop(workshop);
                    workshops.add(workshop1);
                    coin -= workshop.getCost();
                    System.out.println("Built successfully");
                    logger.setUseParentHandlers(false);
                    logger.info("This workshop is already built");
                    return;
                } else{
                    System.out.println("Not enough coin to build this workshop");
                    logger.setUseParentHandlers(false);
                    logger.info("Not enough coin to build this workshop!");
                    return;
                }
            }
        }
        System.out.println("Workshop is incorrect");
        logger.setUseParentHandlers(false);
        logger.info("Workshop is incorrect");
    }
    public void upgradeWorkshop(String workshopName) {
        for (Workshop workshop : workshops) {
            if (workshop.getName().equals(workshopName)) {
                if (coin >= workshop.getCostToUpgrade() && !workshop.maxLevel()) {
                    workshop.increaseLevel();
                    coin -= workshop.getCostToUpgrade();
                    System.out.println("Upgraded successfully");
                    return;
                }
            }
        }
        for (Workshops workshop1 : Workshops.values()) {
            if (workshop1.name().equals(workshopName)) {
                System.out.println("There isn't " + workshopName + " to upgrade");
                logger.setUseParentHandlers(false);
                logger.info("There isn't " + workshopName + " to upgrade");
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
                    System.out.println("Turn " + TimeProcessor.getInstance().currentStep + ": " + workshop.getName() + "'s work is done");
                    logger.setUseParentHandlers(false);
                    logger.info(workshop.getName() + "'s work is done");
                    //TODO change response or delete it
                    for (int i = 0; i < workshop.getAmountPro(); i++) {
                        productsOnGround.add(new Product(workshop.getProducedProduct()));
                    }

                }
            }
        }
    }

    public void domesticProducts() {
        for (Domestic domestic : domestics) {
            if(domestic.isProduced())
                productsOnGround.add(new Product(domestic.getProduct(),domestic.getX(),domestic.getY()));
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
            if(grass.getColumn() == domestic.getX() && grass.getRow() == domestic.getY()) {
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
            if(ints[i] == TimeProcessor.getInstance().currentStep)
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
                    cage.getWild().free();
                    System.out.println("Turn " + TimeProcessor.getInstance().currentStep + ": " + "Wild on " + cage.getX() + "," + cage.getY() + " was freed");
                    logger.setUseParentHandlers(false);
                    logger.info("Wild on " + cage.getX() + "," + cage.getY() + " was freed");
                }
            }
        }
    }

    public void decreaseCageResist() {
        HashSet<Cage> cageHashSet = new HashSet<>(cages);
        for (Cage cage : cageHashSet) {
            cage.getWild().decreaseTap();
            if(cage.decreaseTap()) {
                cages.remove(cage);
                cage.getWild().free();
            }
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
                    System.out.println("Turn " + TimeProcessor.getInstance().currentStep + ": " +"Dog on " + helper.getX() + "," + helper.getY() + " attacked a wild");
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
                System.out.println("Turn " + TimeProcessor.getInstance().currentStep + ": " +"Product on " + x + "," + y + " moved to warehouse by cat");
                logger.setUseParentHandlers(false);
                logger.info("Product on " + x + "," + y + " moved to warehouse by cat");
                productsOnGround.remove(product);
                warehouse.addProduct(Products.valueOf(product.getNameOfProduct()),1);//TODO
            }
        }
    }

    public void wildAttack() {
        for (Wild wild : wilds) {
            if(!wild.isInCage())
                onDome(wild.getX(), wild.getY());//TODO
        }
    }

    private void onDome(int x, int y) {
        HashSet<Domestic> domesticHashSet = new HashSet<>(domestics);
        for (Domestic domestic : domesticHashSet) {
            if(domestic.getX() == x && domestic.getY() == y){
                System.out.println("Turn " + TimeProcessor.getInstance().currentStep + ": " +"Wild on " + x + "," + y + " attacked a domestic");
                logger.setUseParentHandlers(false);
                logger.info("Wild on " + x + "," + y + " attacked a domestic");
                domestics.remove(domestic);
            }
        }
    }

    public void walk() {
        for (Domestic domestic : domestics){
            if(domestic.isHungry())
                domestic.hungryWalk(grasses);
            else
                domestic.walk();
        }
        for (Helper helper : helpers) {
            if(helper instanceof Cat)
                ((Cat) helper).findWalk(productsOnGround);
            else
                helper.walk();
        }
        for (Wild wild : wilds) {
            if(!wild.isPrisoned())
                wild.walk();
        }
    }

    public void showDetails(boolean print) {
        if(print) {
            System.out.println("TURN: " + TimeProcessor.currentStep);
            showGrass();
            for (Domestic domestic : domestics)
                System.out.println(domestic.getName() + " " + domestic.getHealth() + "% [" + domestic.getX() + " " + domestic.getY() + "]");
            for (Helper helper : helpers)
                System.out.println(helper.getName() + " [" + helper.getX() + " " + helper.getY() + "]");
            for (Wild wild : wilds)
                System.out.println(wild.getName() + " " + "Cage need: " + wild.getTapNeeded() + " " + " [" + wild.getX() + " " + wild.getY() + "]");
            for (Product product : productsOnGround)
                System.out.println(product.getNameOfProduct() + " [" + product.getX() + " " + product.getY() + "]");
            taskPrint();
        }
    }

    private void taskPrint() {
        for (Task task : tasks) {
            if (task.getType().equals("COIN"))
                System.out.println("COIN: " + coin + "/" + task.getTarget());
            else if (task.getType().equals("CATCH"))
                System.out.println(task.getTypeOfProduct().name() + " " + warehouse.amount(task.getTypeOfProduct()) + "/" + task.getTarget());
            else if (task.getType().equals("DOMESTIC"))
                System.out.println(task.getTypeOfDomestic().name() + " " + domeAmount(task.getTypeOfDomestic()) + "/" + task.getTarget());
        }
    }

    private void showGrass() {
        int[][] grass = new int[Board.ROW.getLength()][Board.COLUMN.getLength()];
        for (int i = 0; i < Board.ROW.getLength(); i++) {
            for (int j = 0; j < Board.COLUMN.getLength(); j++)
                grass[i][j] = 0;
        }
        for (Grass grass1 : grasses)
            grass[Board.ROW.getLength() - grass1.getRow()][grass1.getColumn()-1] ++;
        for (int i = 0; i < Board.ROW.getLength(); i++) {
            for (int j = 0; j < Board.COLUMN.getLength(); j++)
                System.out.print(grass[i][j] + "\t");
            System.out.println();
        }
        System.out.println();
    }

    public boolean checkWin() {
        //TODO if user did all tasks of level
        boolean win = true;
        for (Task task : tasks) {
            if (task.getType().equals("COIN")) {
                if (task.getTarget() != coin) {
                    win = false;
                    return win;
                }
            } else if (task.getType().equals("CATCH")) {
                if (task.getTarget() < warehouse.amount(task.getTypeOfProduct())) {
                    win = false;
                    return win;
                }
            } else if (task.getType().equals("DOMESTIC")) {
                if (task.getTarget() < domeAmount(task.getTypeOfDomestic())) {
                    win = false;
                    return win;
                }
            }
        }
        if(win)
            System.out.println("Turn " + TimeProcessor.getInstance().currentStep + ": " + "Tasks completed\nYou WON");
        return win;
    }

    private int domeAmount(Domestics typeOfDomestic) {
        String name = typeOfDomestic.name();
        int amount = 0;
        for (Domestic domestic : domestics) {
            if(domestic.getName().equals(name))
                amount ++;
        }
        return amount;
    }

    public void domeHealth() {
        for (Domestic domestic : domestics)
            domestic.tired();
    }


    public void domeDie() {
        HashSet<Domestic> domesticHashSet = new HashSet<>(domestics);
        for (Domestic domestic : domesticHashSet)
            if(domestic.getHealth() <= 0)
                domestics.remove(domestic);
    }


    public void transport() {
        int price = truck.finishTransport();
        if(price > 0) {
            System.out.println("Turn " + TimeProcessor.getInstance().currentStep + ": " +"Your products sold " + price + "$");
            coin += price;
        }
    }


    public void grassAlarm(boolean print) {
        if(print) {
            if (grasses.isEmpty())
                System.err.println("Not any grass on board");
        }
    }
}


