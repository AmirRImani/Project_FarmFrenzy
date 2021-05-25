package animals.domestics;

import animals.Animal;
import animals.Speed;
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

    protected Domestic(int value, Products product, int timeToProduct, int speed) {
        super(speed);//TODO
        this.value = value;
        this.health = 100;
        this.wantToEat = false;
        this.product = product;
        this.timeToProduct = timeToProduct;
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
