package animals.domestics;

import animals.Animal;
import products.Products;

public class Domestic extends Animal {
    private int value;
    private final int fullHealth = 100;
    private final int healthToEat = 50;
    private final int healDecrease = 10;
    private int health;
    private boolean wantToEat;
    private Products product;
    private int timeToProduct;
    private int startTime;//TODO start to produce

    public Domestic(Domestics domestic) {
        super(domestic.getSpeed());//TODO
        this.value = domestic.getValue();
        this.health = 100;
        this.wantToEat = false;
        this.product = domestic.getProduct();
        this.timeToProduct = domestic.getTimeToProduct();
    }

    public void checkWantToEat(Domestic domestic){
        if(domestic.health < domestic.healthToEat)
            wantToEat = true;
        else if(domestic.health >= domestic.fullHealth)
            wantToEat = false;
    }

    public void tired(){
        this.health -= this.healDecrease;
    }

    public void eat(){

    }

    public void killed(Domestic domestic){

    }
}
