package animals.wilds;

import animals.Animal;
import controller.Cage;

public class Wild extends Animal {
    private boolean inCage;
    private boolean prisoned;
    private int tapNeeded;

    public boolean isInCage() { return inCage; }

    public int getTapNeeded() { return tapNeeded; }

    public void setCage(){
        this.inCage = true;
    }

    public Wild(Wilds wild) {
        super(wild.getSpeed());
        this.inCage = false;
        this.prisoned = false;
        this.tapNeeded = wild.getTapNeeded();
    }


    protected void kill(){

    }
}
