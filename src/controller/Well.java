package controller;

import sharedClasses.TimeProcessor;

public class Well {
    private final int HIGHEST_AMOUNT = 5;
    private final int TIME_TO_FULL = 3;
    private float amountOfWater;
    private boolean startToWater;
    private int startTime;
    //TODO these methods can be used in sharedClasses.TimeProcessor


    public Well() {
        this.amountOfWater = this.HIGHEST_AMOUNT;
        this.startTime = 0;
    }

    private boolean isEmpty(){
        System.out.println(amountOfWater);
        if(amountOfWater <= 0)
            return true;
        return false;
    }

    public boolean water(){
        //TODO first check isEmpty then water
        if(isEmpty() && !startToWater) {
            System.out.println("1");
            startTime = TimeProcessor.currentStep;
            startToWater = true;
            return true;
        } else if(TimeProcessor.currentStep >= startTime + TIME_TO_FULL){
            System.out.println("2" + startTime);
            amountOfWater = HIGHEST_AMOUNT;
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
