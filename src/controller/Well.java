package controller;

import sharedClasses.TimeProcessor;
import view.Game;
public class Well {
    private final int HIGHEST_AMOUNT = 5;
    private final int TIME_TO_FULL = 3;
    private float amountOfWater;
    private boolean startToWater;
    private int startTime;
    //TODO these methods can be used in sharedClasses.TimeProcessor


    public Well() {
        this.amountOfWater = this.HIGHEST_AMOUNT;
        this.startToWater = false;
        this.startTime = 0;
    }

    private boolean isEmpty(){
        if(TimeProcessor.getInstance().currentStep >= startTime + TIME_TO_FULL && startToWater){
            amountOfWater = HIGHEST_AMOUNT;
            startToWater = false;
            return false;
        } else if(amountOfWater <= 0)
            return true;
        return false;
    }

    public boolean water(){
        //TODO first check isEmpty then water
        if(isEmpty() && !startToWater) {
            startTime = TimeProcessor.getInstance().currentStep;
            startToWater = true;
            return true;
        } else if(TimeProcessor.getInstance().currentStep >= startTime + TIME_TO_FULL && startToWater){
            amountOfWater = HIGHEST_AMOUNT;
            startToWater = false;
            return true;
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
