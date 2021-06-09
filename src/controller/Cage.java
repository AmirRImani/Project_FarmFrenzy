package controller;

import animals.wilds.Wild;
import sharedClasses.TimeProcessor;

public class Cage {
    private Wild wild;
    private boolean prisoned;
    private int numberOfTapNeed;
    private int numberOfTap;
    private int lastAttempt;//TODO to recognize whether user tapped in sequence
    private int startTimeComplete;//TODO start wild to be in cage completely

    public int getX(){ return wild.getX(); }

    public int getY(){ return wild.getY(); }

    public Cage(Wild wild) {
        this.wild = wild;
        this.prisoned = false;
        this.numberOfTapNeed = wild.getTapNeeded();
        this.numberOfTap = 1;
        this.lastAttempt = TimeProcessor.currentStep;
        this.startTimeComplete = 0;
    }

    public void increaseTap(){

    }

    public void decreaseTap(){

    }

    private boolean inCage(Wild wild){
        //TODO
        return true;
    }
}
