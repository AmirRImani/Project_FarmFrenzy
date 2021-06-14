package animals.wilds;

import animals.Animal;
import controller.Cage;

public class Wild extends Animal {
    private boolean inCage;
    private boolean prisoned;
    private int tapNeeded;

    public boolean isInCage() { return inCage; }

    public boolean isPrisoned() { return prisoned; }

    public int getTapNeeded() { return tapNeeded; }

    public void setCage(boolean inCage){
        this.inCage = inCage;
    }

    public void prison(boolean prisoned) { this.prisoned = prisoned; }

    public Wild(Wilds wild) {
        super(wild.getSpeed());
        this.inCage = false;
        this.prisoned = false;
        this.tapNeeded = wild.getTapNeeded();
    }


    protected void kill(){

    }
}
