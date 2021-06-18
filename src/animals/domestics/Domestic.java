package animals.domestics;

import animals.Animal;
import products.Products;
import sharedClasses.TimeProcessor;

public class Domestic extends Animal {
    private String name;
    private int value;
    private final int FULL_HEALTH = 100;
    private final int HEALTH_TO_EAT = 50;
    private final int HEALTH_DECREASE = 10;
    private int health;
    private boolean wantToEat;
    private Products product;
    private int timeToProduce;
    private int startTime;//TODO start to produce

    public String getName() { return name; }

    public Products getProduct() { return product; }

    public int getHealth() { return health; }

    public Domestic(Domestics domestic) {
        super(domestic.getSpeed());//TODO
        this.name = domestic.name();
        this.value = domestic.getValue();
        this.health = 100;
        this.wantToEat = false;
        this.product = domestic.getProduct();
        this.timeToProduce = domestic.getTimeToProduce();
    }

    public void checkWantToEat(Domestic domestic){
        if(domestic.health < domestic.HEALTH_TO_EAT)
            wantToEat = true;
        else if(domestic.health >= domestic.FULL_HEALTH)
            wantToEat = false;
    }

    public void tired(){
        this.health -= this.HEALTH_DECREASE;
    }

    public boolean needToEat(){
        if(health <= HEALTH_TO_EAT)
            return true;
        return false;
    }

    public void killed(Domestic domestic){

    }

    public boolean isProduced() {
        if(TimeProcessor.getInstance().currentStep >= startTime + timeToProduce){
            startTime = TimeProcessor.getInstance().currentStep;
            return true;
        }
        return false;
    }

    public void eat() {
        health = FULL_HEALTH;
    }
}
