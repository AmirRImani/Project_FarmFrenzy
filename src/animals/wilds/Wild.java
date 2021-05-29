package animals.wilds;

import animals.Animal;
import factories.Factories;

public class Wild extends Animal {
    protected boolean inCage;
    protected int startTime;//TODO start to be in cage

    protected Wild(int speed) {
        super(speed);
        this.inCage = false;
    }

    public Wild(Wilds wild) {
        super(wild.getSpeed());
        this.inCage = false;
    }

    protected void kill(){

    }
}
