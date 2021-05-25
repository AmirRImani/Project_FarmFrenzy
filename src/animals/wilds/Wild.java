package animals.wilds;

import animals.Animal;

public class Wild extends Animal {
    protected boolean inCage;

    protected Wild(int speed) {
        super(speed);
        this.inCage = false;
    }


    protected void kill(){

    }
}
