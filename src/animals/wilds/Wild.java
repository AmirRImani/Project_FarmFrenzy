package animals.wilds;

import animals.Animal;

public class Wild extends Animal {
    private String name;
    private boolean inCage;
    private boolean prisoned;
    private int tapNeeded;
    private int numberOfTap;

    public String getName() { return name; }

    public boolean isInCage() { return inCage; }

    public boolean isPrisoned() { return prisoned; }

    public int getTapNeeded() { return tapNeeded; }

    public int getNumberOfTap() { return numberOfTap; }

    public void setCage(boolean inCage){
        this.inCage = inCage;
    }

    public void prison(boolean prisoned) { this.prisoned = prisoned; }

    public Wild(Wilds wild) {
        super(wild.getSpeed());
        this.name = wild.name();
        this.inCage = false;
        this.prisoned = false;
        this.tapNeeded = wild.getTapNeeded();
        this.numberOfTap = 0;
    }

    public void increaseTap(){
        numberOfTap ++;
    }

    public void decreaseTap() {
        numberOfTap --;
    }

    public void free(){
        numberOfTap = 0;
    }
}
