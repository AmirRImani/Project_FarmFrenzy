package view;

import animals.domestics.Domestic;
import animals.domestics.Domestics;
import animals.helpers.Dog;
import animals.helpers.Helper;
import animals.helpers.Helpers;
import animals.wilds.Wild;
import animals.wilds.Wilds;
import controller.*;
import factories.Workshop;
import factories.Workshops;
import input.User;
import products.Product;
import products.Products;
import sharedClasses.TimeProcessor;
import vehicles.Truck;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

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
            this.coin -= domestic.getValue();
        } else
            System.out.println("Not enough coin to buy");
    }

    public void buyHelper(Helpers helper) {
        if(this.coin >= helper.getValue()) {
            helpers.add(new Helper(helper));
            System.out.println("Buying has been done");
            this.coin -= helper.getValue();
        } else
            System.out.println("Not enough coin to buy");
    }


    public void pickup(int x, int y) {
        boolean found = false;
        boolean fullWarehouse = false;
        HashSet<Product> products = new HashSet<>(productsOnGround);
        for (Product product : products) {
            if(product.getX() == x && product.getY() == y){
                found = true;
                if(this.warehouse.addProduct(Products.valueOf(product.getNameOfProduct()), 1)) {
                    this.productsOnGround.remove(product);
                    fullWarehouse = false;
                    System.out.println("Product " + product.getNameOfProduct() + "transferred to warehouse");
                } else
                    fullWarehouse = true;
            }
        }
        if(!found)
            System.out.println("There isn't any product on this coordinate");
        else if(!fullWarehouse)
            System.out.println("There isn't enough space in warehouse");
    }

    public void plant(int x, int y) {
        if(this.well.putGrass()) {
            grasses.add(new Grass(x, y));
            System.out.println("Grass planted");
        } else
            System.out.println("Water needed");
    }

    public void work(String nameOfWorkshop) {
        for (Workshop workshop : workshops) {
            if(workshop.getName().equals(nameOfWorkshop)){
                if(workshop.work(warehouse))
                    System.out.println("Workshop " + workshop.getName() + " started to produce" /* + workshop.getProduct().getName()*/);//TODO
                return;
            }
        }
        System.out.println("Workshop is incorrect");
    }

    public void cage(int x, int y) {
        for (Wild wild : wilds) {
            if(wild.getX() == x && wild.getX() == y){
                System.out.println("Cage");
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
                    return;
                }
            }
        }
        System.out.println("There isn't any wild animal in this coordinate");
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
                return;
            }
        }
        for (Workshops workshop : Workshops.values()){
            if(workshop.name().equals(workshopName)) {
                Workshop workshop1 = new Workshop(workshop);
                workshops.add(workshop1);
                System.out.println("Built successfully");
                return;
            }
        }
        System.out.println("Workshop is incorrect");
    }

    public void well() {
        if(this.well.water())
            System.out.println("Watering well started");
        else
            System.out.println("Can't start to water well");
    }

    public void truckGo() {
        if(this.truck.transport())
            System.out.println("Transporting started");
        else
            System.out.println("Truck is on road");
    }

    public void workshopProducts() {
        for (Workshop workshop : workshops) {
            if (workshop.isBusy()) {
                if (workshop.isProduced()) {
                    System.out.println(workshop.getName() + "'s work is done");//TODO change response or delete it
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
                    System.out.println("Wild in " + cage.getX() + "," + cage.getY() + " was freed");
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


//    public void dogAttack() {
//        for (Helper helper : helpers) {
//            if(helper instanceof Dog){
//                if(onWild((Dog) helper))
//            }
//        }
//    }
//
//    private boolean onWild(Dog dog) {
//        for (Wild wild : wilds) {
//            if(!wild.isInCage())
//
//        }
//    }
}
