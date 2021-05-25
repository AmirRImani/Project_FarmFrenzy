package controller;

public class Well {
    private final int highestAmount = 5;
    private final int timeToFull = 3;
    private int chronometer;
    private int amountOfWater;
    private boolean startToWater;

    private boolean isEmpty(){
        if(amountOfWater <= 0) {
            startToWater = true;
            chronometer = 0;
        }
        return false;
    }

    protected boolean water(){
        isEmpty();
        //TODO first check isEmpty then water
        if(startToWater) {
            chronometer++;
            return true;
        }
        if(chronometer >= 3) {
            startToWater = false;
            chronometer = 0;
            return false;
        }
        return false;
    }

    protected boolean putGrass(){
        if(!isEmpty()) {
            amountOfWater--;
            return true;
        }
        return false;
    }

}
