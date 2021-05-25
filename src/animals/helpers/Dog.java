package animals.helpers;

import animals.Speed;
import animals.Values;
import animals.wilds.Wild;

import java.util.ArrayList;

public class Dog extends Helper {
    public Dog() {
        super(Values.DOG.getValue(), Speed.DOG.getSpeed());
    }

    protected void findWild(ArrayList<Wild> wilds){

    }
    protected void attackDefence(Wild wild){

    }
}
