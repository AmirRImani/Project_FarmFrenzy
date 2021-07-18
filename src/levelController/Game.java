package levelController;

import animals.Animal;
import animals.domestics.Domestic;
import animals.domestics.Domestics;
import animals.helpers.Cat;
import animals.helpers.Dog;
import animals.helpers.Helper;
import animals.helpers.Helpers;
import animals.wilds.Wild;
import animals.wilds.Wilds;
import entry.User;
import levelController.objects.*;
import levels.Level;
import products.Product;
import products.Products;
import sharedClasses.TimeProcessor;
import tasks.Task;
import view.GameView;
import workshops.Incubator;
import workshops.Workshop;
import workshops.Workshops;
import java.util.HashMap;
import java.util.HashSet;
import static entry.EnterProcess.logger;

public class Game {
    private User user;
    private Level level;
    private int coin;
    private HashSet<Domestic> domestics;
    private HashSet<Workshop> workshops;
    private Incubator incubator;
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

    public Level getLevel() { return level; }

    public User getUser() { return user; }

    public int getGoldTime() { return goldTime; }

    public int getCoin() { return coin; }

    public int getAward() { return award; }

    public HashSet<Workshop> getWorkshops() { return workshops; }

    public HashSet<Product> getProducts() { return productsOnGround; }

    public HashSet<Grass> getGrasses() { return grasses; }

    public HashSet<Task> getTasks() { return tasks; }

    public HashSet<Cage> getCages() { return cages; }

    public HashSet<Animal> getAnimals() {
        HashSet<Animal> animals = new HashSet<>(domestics);
        animals.addAll(helpers);
        animals.addAll(wilds);
        return animals;
    }

    public Game(Level level, User user) {
        this.user = user;
        this.level = level;
        this.coin = level.getStartCoin() + user.getNumberOfCoins();
        user.clearCoins(); //TODO maybe shouldn't clear coins here
        this.goldTime = level.getGoldTime();
        this.award = level.getAward();
        this.tasks = level.getTasks();
        this.wildsAppearance = level.getTimeOfWilds();
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

    public Domestic buyDome(Domestics domestic) {
        if(this.coin >= domestic.getValue()) {
            Domestic domestic1 = new Domestic(domestic);
            domestics.add(domestic1);
            //System.out.println("Buying has been done");
            logger.setUseParentHandlers(false);
            logger.fine("Buying has been done!");
            this.coin -= domestic.getValue();
            return domestic1;
        } else
            //System.out.println("Not enough coin to buy");
            logger.setUseParentHandlers(false);
            logger.info("Not enough coin to buy!");
            return null;
    }

    public Helper buyHelper(Helpers helper) {
        if(this.coin >= helper.getValue()) {
            //System.out.println("Buying has been done");
            logger.setUseParentHandlers(false);
            logger.info("Buying has been done! ");

            this.coin -= helper.getValue();

            if (helper == Helpers.CAT) {
                Cat cat = new Cat();
                helpers.add(cat);
                return cat;
            } else if (helper == Helpers.DOG){
                Dog dog = new Dog();
                helpers.add(dog);
                return dog;
            }
        } else
            //System.out.println("Not enough coin to buy");
            logger.setUseParentHandlers(false);
            logger.info("Not enough coin to buy! ");
            return null;
    }


    //TODO make another function for pickup
//    public boolean pickup(int x, int y) {
//        boolean found = false;
//        boolean fullWarehouse = false;
//        HashSet<Product> products = new HashSet<>(productsOnGround);
//        if(x<1 || x> Board.COLUMN.getLength() || y<1 || y>Board.ROW.getLength()) {
//            System.out.println("Coordinate is not on game board");
//            logger.setUseParentHandlers(false);
//            logger.info("Coordinate is not on game board ");
//
//            return;
//        }
//        for (Product product : products) {
//            if(product.getX() == x && product.getY() == y){
//                found = true;
//                if(this.warehouse.addProduct(Products.valueOf(product.getNameOfProduct()), 1)) {
//                    this.productsOnGround.remove(product);
//                    fullWarehouse = false;
//                   // System.out.println("Product " + product.getNameOfProduct() + " transferred to warehouse");
//
//                } else
//                    fullWarehouse = true;
//            }
//        }
//        if(!found) {
//            System.out.println("There isn't any product on this coordinate");
//            logger.setUseParentHandlers(false);
//            logger.info("There isn't any product on this coordinate ");
//        } else if(fullWarehouse) {
//            System.out.println("There isn't enough space in warehouse");
//            logger.setUseParentHandlers(false);
//            logger.info("here isn't enough space in warehouse!");
//        }
//    }

    public boolean pickup(Product product) {
        if(this.warehouse.addProduct(Products.valueOf(product.getNameOfProduct()), 1)) {
            this.productsOnGround.remove(product);
            // System.out.println("Product " + product.getNameOfProduct() + " transferred to warehouse");
            logger.setUseParentHandlers(false);
            logger.info("There isn't any product on this coordinate ");
            return true;
        } else {
//            System.out.println("There isn't enough space in warehouse");
            logger.setUseParentHandlers(false);
            logger.info("There isn't enough space in warehouse!");
            return false;
        }
    }

    public Grass plant(int x, int y) { //TODO initial variables should be changed
//        if(x<1 || x>Board.COLUMN.getLength() || y<1 || y>Board.ROW.getLength()) {
//            System.out.println("Coordinate is not on game board");
//            logger.setUseParentHandlers(false);
//            logger.info("Coordinate is not on game board!");
//
//            return;
//        }
        if(this.well.putGrass()) {
            Grass grass = new Grass(x, y);
            grasses.add(grass);
            //System.out.println("Grass planted");
            logger.setUseParentHandlers(false);
            logger.info("Grass planted");
            return grass;
        } else
           // System.out.println("Water needed");
            logger.setUseParentHandlers(false);
            logger.info("Water needed");
            return null;
    }

    //TODO maybe this function should be written again
    public boolean work(Workshop workshop) { //TODO initial variables should be changed
        if (workshop.work(warehouse)) {
            logger.setUseParentHandlers(false);
            logger.fine("Workshop " + workshop.getName() + " started to produce");
            return true;
        }
        return false;
    }

    //TODO another function for cage
    public int cage(Wild wild) {
        if(wild.isInCage()){
            for (Cage cage : cages) {
                if(cage.getX() == wild.getX() && cage.getY() == wild.getY()) {
                    if(wild.isPrisoned()){
                        if(warehouse.addProduct(Products.valueOf("CAUGHT_" + wild.getName()), 1)) {
                            //System.out.println("Wild " + wild.getName() + " on [" + wild.getX() + " " + wild.getY() + " ] has been caught");
                            logger.setUseParentHandlers(false);
                            logger.fine("Wild " + wild.getName() + " on [" + wild.getX() + " " + wild.getY() + " ] has been caught");
                            return 0;
                        } else {
                            //System.out.println("Not enough space in warehouse");
                            return 1;
                        }
                    }

                    if(cage.increaseTap()) {
                        wild.increaseTap();
                        //System.out.println("Cage on [ " + cage.getX() + " " + cage.getY() + " ] resistance increased");
                        logger.setUseParentHandlers(false);
                        logger.fine("Cage on [ " + cage.getX() + " " + cage.getY() + " ] resistance increased");
                        return 2;
                    } else {
                        //System.out.println("Cage can't be used. You used it on this cage in this step");
                        logger.setUseParentHandlers(false);
                        logger.info("Cage can't be used. You used it on this cage in this step");
                        return 3;
                    }
                }
            }

        } else{
            Cage newCage = new Cage(wild);
            wild.setCage(true);
            cages.add(newCage);
            wild.increaseTap();
            //System.out.println("New cage on " + wild.getX() + ", " + wild.getY());
            logger.setUseParentHandlers(false);
            logger.fine("New cage on " + wild.getX() + ", " + wild.getY());
            return 4;
        }
        return 5;
    }

    public boolean turn(int turnNumber, GameView gameView) {
        boolean exit;
        TimeProcessor timeProcessor = TimeProcessor.getInstance();
        exit = timeProcessor.changeSteps(turnNumber,this, gameView);
        return exit;
        //TODO a path for exit after win in code should be added
    }

    public boolean truckLoad(Products product) {
        return this.warehouse.truckLoad(product, this.truck);
    }

    public boolean truckUnload(Products product) {
        return this.truck.unload(product, this.warehouse);
    }

    public Workshop build(Workshops workshop) {
        if(coin >= workshop.getCost()) {
            Workshop workshop1 = new Workshop(workshop);
            workshops.add(workshop1);
            coin -= workshop.getCost();
            //System.out.println("Built successfully");
            logger.setUseParentHandlers(false);
            logger.info("Built successfully");
            return workshop1;
        } else{
            //System.out.println("Not enough coin to build this workshop");
            logger.setUseParentHandlers(false);
            logger.info("Not enough coin to build this workshop!");
            return null;
        }
    }

    public Workshop upgradeWorkshop(Workshops workshop) {
        for (Workshop workshop1 : workshops) {
            if (workshop1.getName().equals(workshop.name())) {
                if (coin >= workshop1.getCostToUpgrade()) {
                    workshop1.increaseLevel();
                    coin -= workshop1.getCostToUpgrade();
                    //System.out.println("Upgraded successfully");
                    logger.setUseParentHandlers(false);
                    logger.info("Upgraded successfully");
                    return workshop1;
                }
            }
        }
        return null;
    }

    public boolean well() {
        if (this.well.water()) {
            //System.out.println("Watering well started");
            logger.setUseParentHandlers(false);
            logger.info("Watering well started.");
            return true;
        } else {
            //System.out.println("Can't start to water well");
            logger.setUseParentHandlers(false);
            logger.info("Can't start to water well");
            return false;
        }
    }

    public boolean truckGo() {
        if (this.truck.transport()) {
            //System.out.println("Transporting started");
            logger.setUseParentHandlers(false);
            logger.info("Transporting started");
            return true;
        } else {
           // System.out.println("Truck is on road");
            logger.setUseParentHandlers(false);
            logger.info("Truck is on road");
            return false;
        }
    }

    public void workshopProducts(GameView gameView) {
        for (Workshop workshop : workshops) {
            if (workshop.isBusy()) {
                if (workshop.isProduced()) {
                    //System.out.println("Turn " + TimeProcessor.getInstance().currentStep + ": " + workshop.getName() + "'s work is done");
                    logger.setUseParentHandlers(false);
                    logger.info(workshop.getName() + "'s work is done");

                    for (int i = 0; i < workshop.getAmountPro(); i++) {
                        Product product = new Product(workshop.getProducedProduct());
                        productsOnGround.add(product);
                        gameView.workshopProduct(workshop, product);
                    }
                }
            }
        }
        if (incubator != null) {
            if (incubator.isProduced()) {
                logger.setUseParentHandlers(false);
                logger.info("Incubator's work is done");

                //TODO add hen  show graphically
                for (int i = 0; i < incubator.getAmountPro(); i++)
                    domestics.add(new Domestic(Domestics.HEN));
            }
        }
    }

    public void domesticProducts(GameView gameView) {
        for (Domestic domestic : domestics) {
            if(domestic.isProduced()) {
                Product product = new Product(domestic.getProduct(),domestic.getX(),domestic.getY());
                productsOnGround.add(product);
                gameView.addProduct(product);
            }
        }
    }

    public void feedAnimals(GameView gameView) {
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
                gameView.feedAnimal(grass);
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

    public void appearWilds(GameView gameView) {
        for (Wilds wild : Wilds.values()) {
            if(wildsAppearance.containsKey(wild)) {
                Wild wild1 = timeWild(wild, wildsAppearance.get(wild));
                if (wild1 != null)
                    gameView.addAnimal(wild1);
            }
        }
    }

    private Wild timeWild(Wilds wild, int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            if(ints[i] == TimeProcessor.getInstance().currentStep) {
                Wild wild1 = new Wild(wild);
                wilds.add(wild1);
                return wild1;
            }
        }
        return null;
    }

    public void disappearProducts(GameView gameView) {
        HashSet<Product> products = new HashSet<>(productsOnGround);
        for (Product product : products) {
            if(product.spoil()) {
                productsOnGround.remove(product);
                gameView.disappearProduct(product);
            }
        }
    }

    public void freeWilds(GameView gameView) {
        HashSet<Cage> cageHashSet = new HashSet<>(cages);
        for (Cage cage : cageHashSet) {
            if(cage.isPrisoned()) {
                if (cage.free()) {
                    cages.remove(cage);
                    wilds.remove(cage.getWild());
                    cage.getWild().free();
                    gameView.freeWild(cage.getWild());
                    //System.out.println("Turn " + TimeProcessor.getInstance().currentStep + ": " + "Wild on " + cage.getX() + "," + cage.getY() + " was freed");
                    logger.setUseParentHandlers(false);
                    logger.info("Wild on " + cage.getX() + "," + cage.getY() + " was freed");
                }
            }
        }
    }

    public void decreaseCageResist(GameView gameView) { //TODO
        HashSet<Cage> cageHashSet = new HashSet<>(cages);
        for (Cage cage : cageHashSet) {
            cage.getWild().decreaseTap();
            gameView.decreaseCageResist(cage.getWild());
            if(cage.decreaseTap()) {
                cages.remove(cage);
                cage.getWild().free();
                gameView.freeWild(cage.getWild());
            }
        }
    }


    public void dogAttack(GameView gameView) {
        HashSet<Helper> helperHashSet = new HashSet<>(helpers);
        for (Helper helper : helperHashSet) {
            if(helper instanceof Dog){
                Wild wild = onWild((Dog)helper);
                if (wild != null)
                    gameView.dogAttack((Dog) helper, wild);
            }
        }
    }

    private Wild onWild(Dog dog) {
        HashSet<Wild> wildHashSet = new HashSet<>(wilds);
        for (Wild wild : wildHashSet) {
            if(!wild.isInCage()){
                if(wild.getX() == dog.getX() && wild.getY() == dog.getY()){
                    //System.out.println("Turn " + TimeProcessor.getInstance().currentStep + ": " +"Dog on " + helper.getX() + "," + helper.getY() + " attacked a wild");
                    logger.setUseParentHandlers(false);
                    logger.info("Dog on " + dog.getX() + "," + dog.getY() + " attacked a wild");
                    wilds.remove(wild);
                    helpers.remove(dog);
                    return wild;
                }
            }
        }
        return null;
    }

    public void catCatches(GameView gameView) {
        for (Helper helper : helpers) {
            if(helper instanceof Cat){
                Product product = onProduct(helper.getX(), helper.getY());
                if (product != null)
                    gameView.toWarehouse(product);
            }
        }
    }

    private Product onProduct(int x, int y) {
        HashSet<Product> products = new HashSet<>(productsOnGround);
        for (Product product : products) {
            if(product.getX() == x && product.getY() == y){
                //System.out.println("Turn " + TimeProcessor.getInstance().currentStep + ": " +"Product on " + x + "," + y + " moved to warehouse by cat");
                logger.setUseParentHandlers(false);
                logger.info("Product on " + x + "," + y + " moved to warehouse by cat");
                productsOnGround.remove(product);
                warehouse.addProduct(Products.valueOf(product.getNameOfProduct()),1);//TODO
                return product;
            }
        }
        return null;
    }

    public void wildAttack(GameView gameView) {
        for (Wild wild : wilds) {
            if(!wild.isInCage()) {
                Animal animal = onDome(wild.getX(), wild.getY());
                if (animal != null)
                    gameView.wildAttack(animal);
            }
        }
    }

    private Animal onDome(int x, int y) {
        HashSet<Domestic> domesticHashSet = new HashSet<>(domestics);
        HashSet<Cat> cats = new HashSet<>();
        for (Helper helper : helpers) {
            if (helper instanceof Cat)
                cats.add((Cat) helper);
        }


        for (Domestic domestic : domesticHashSet) {
            if(domestic.getX() == x && domestic.getY() == y){
                //System.out.println("Turn " + TimeProcessor.getInstance().currentStep + ": " +"Wild on " + x + "," + y + " attacked a domestic");
                logger.setUseParentHandlers(false);
                logger.info("Wild on " + x + "," + y + " attacked a domestic");
                domestics.remove(domestic);
                return domestic;
            }
        }
        for (Cat cat : cats) {
            if(cat.getX() == x && cat.getY() == y){
                //System.out.println("Turn " + TimeProcessor.getInstance().currentStep + ": " +"Wild on " + x + "," + y + " attacked a domestic");
                logger.setUseParentHandlers(false);
                logger.info("Wild on " + x + "," + y + " attacked a domestic");
                helpers.remove(cat);
                return cat;
            }
        }
        return null;
    }

    public void walk(GameView gameView) {
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

    public int domeAmount(Domestics typeOfDomestic) {
        String name = typeOfDomestic.name();
        int amount = 0;
        for (Domestic domestic : domestics) {
            if(domestic.getName().equals(name))
                amount ++;
        }
        return amount;
    }

    public int amountProduct(Products product) {
        return warehouse.amount(product);
    }

    public void domeHealth() {
        for (Domestic domestic : domestics)
            domestic.tired();
    }


    public void domeDie(GameView gameView) { //TODO
        HashSet<Domestic> domesticHashSet = new HashSet<>(domestics);
        for (Domestic domestic : domesticHashSet)
            if (domestic.getHealth() <= 0) {
                domestics.remove(domestic);
                gameView.domeDie(domestic);
            }
    }


    public boolean transportCompleted() {
        int price = truck.finishTransport();
        if(price > 0) {
            System.out.println("Turn " + TimeProcessor.getInstance().currentStep + ": " +"Your products sold " + price + "$");
            coin += price;
            return true;
        }
        return false;
    }


    public void grassAlarm(boolean print) {
        if(print) {
            if (grasses.isEmpty())
                System.err.println("Not any grass on board");
        }
    }

    public HashSet<Product> getWarehouseProducts() {
        HashSet<Product> products = new HashSet<>();
        HashMap<Products, Integer> productsHashMap = new HashMap<>(warehouse.getAmountOfProduct());
        for (Products products1 : productsHashMap.keySet()) {
            for (int i = 0; i < productsHashMap.get(products1); i++) {
                products.add(new Product(products1));
            }
        }
        return products;
    }

    public HashMap<Products,Integer> getWarehouseProducts(int x) {
        return new HashMap<>(warehouse.getAmountOfProduct());
    }

    public HashMap<Products, Integer> getTruckProducts() {
        return new HashMap<>(truck.getAmountOfProducts());
    }

    public boolean truckOnRoad() {
        return truck.onRoad();
    }

    public double wellProgress() {
        return well.progress();
    }

    public void wellCheck() {
        well.fullCheck();
    }

    public double truckProgress() {
        return truck.progress();
    }

    public Cage getCage(Wild wild) {
        for (Cage cage : cages) {
            if(cage.getWild() == wild)
                return cage;
        }
        return null;
    }

    public void removeWild(Wild wild) {
        wilds.remove(wild);
    }

    public void removeCage(Cage cage) {
        cages.remove(cage);
    }
}


