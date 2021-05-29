package animals.domestics;

import animals.Animal;
import products.Products;

public class Domestic extends Animal {
    protected int value;
    protected final int fullHealth = 100;
    protected final int healthToEat = 50;
    protected final int healDecrease = 10;
    protected int health;
    protected boolean wantToEat;
    protected Products product;
    protected int timeToProduct;
    protected int startTime;//TODO start to produce

    protected Domestic(Domestics domestic) {
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
