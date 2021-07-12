package levelController.objects;

import animals.wilds.Wild;
import sharedClasses.TimeProcessor;

public class Cage {
    private Wild wild;
    private boolean prisoned;
    private final int MAXIMUM_FULL_CAGE = 5;
    private int numberOfTapNeed;
    private int numberOfTap;
    private int lastAttempt;//TODO to recognize whether user tapped in sequence
    private int startTimeComplete;//TODO start wild to be in cage completely

    public int getX(){ return wild.getX(); }

    public int getY(){ return wild.getY(); }

    public Wild getWild() { return wild; }

    public boolean isPrisoned() { return prisoned; }

    public Cage(Wild wild) {
        this.wild = wild;
        this.prisoned = false;
        this.numberOfTapNeed = wild.getTapNeeded();
        this.numberOfTap = 1;
        this.lastAttempt = TimeProcessor.getInstance().currentStep;
        this.startTimeComplete = 0;
    }

    public boolean increaseTap(){
        if(lastAttempt < TimeProcessor.getInstance().currentStep && !prisoned) {
            numberOfTap++;
            lastAttempt = TimeProcessor.getInstance().currentStep;
            if(numberOfTap >= numberOfTapNeed){
                startTimeComplete = TimeProcessor.getInstance().currentStep;
                prisoned = true;
                wild.prison(true);
            }
            return true;
        } else
            return false;
    }

    public boolean decreaseTap() {
        if(TimeProcessor.getInstance().currentStep - lastAttempt > 1) {
            numberOfTap --;
            if(numberOfTap <= 0) {
                wild.setCage(false);
                return true;
            }
        }
        return false;
    }

    private boolean inCage(Wild wild){
        //TODO
        return true;
    }

    public boolean free() {
        if(TimeProcessor.getInstance().currentStep >= startTimeComplete + MAXIMUM_FULL_CAGE)
            return true;
        return false;
    }


}
