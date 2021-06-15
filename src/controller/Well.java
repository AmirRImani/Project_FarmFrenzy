package controller;

import sharedClasses.TimeProcessor;

public class Well {
    private final int highestAmount = 5;
    private final int timeToFull = 3;
    private float amountOfWater;
    private boolean startToWater;
    private int startTime;
    //TODO these methods can be used in sharedClasses.TimeProcessor


    public Well() {
        this.amountOfWater = this.highestAmount;
        this.startTime = 0;
    }

    private boolean isEmpty(){
        if(amountOfWater <= 0)
            return true;
        return false;
    }

    public boolean water(){
        //TODO first check isEmpty then water
        if(isEmpty() && !startToWater) {
            startTime = TimeProcessor.currentStep;
            startToWater = true;
            return true;
        } else if(amountOfWater >= 3) {
            startToWater = false;
            return false;
        } else
            return false;
    }

    public boolean putGrass(){
        if(!isEmpty()) {
            amountOfWater--;
            return true;
        }
        return false;
    }

}
