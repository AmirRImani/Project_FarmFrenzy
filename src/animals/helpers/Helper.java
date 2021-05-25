package animals.helpers;

import animals.Animal;

public class Helper extends Animal {
    protected int value;


    protected Helper(int value, int speed) {
        super(speed);//TODO
        this.value = value;
    }
}
