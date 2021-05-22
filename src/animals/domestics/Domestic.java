package animals.domestics;

import animals.Animal;
import products.Product;

public class Domestic extends Animal {
    protected int amountOfFoodNeed;
    protected int fullFood;
    protected boolean wantToEat;
    protected int health;
    protected Product product;

    public void checkWantToEat(Domestic domestic){
        if(domestic.health < domestic.amountOfFoodNeed)
            wantToEat = true;
        else if(domestic.health >= domestic.fullFood)
            wantToEat = false;
    }
    public void eat(Domestic domestic){

    }

    public void killed(Domestic domestic){

    }
}
