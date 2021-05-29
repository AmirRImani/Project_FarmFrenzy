package sharedClasses;

public class TimeProcessor {
    public static int currentStep;

    private static TimeProcessor timeInstance;
    public static TimeProcessor getInstance(){
        if(timeInstance == null)
            timeInstance = new TimeProcessor();
        return timeInstance;
    }

    public void changeStep(){//TODO make change in one step  in order to skip two or more steps make a for loop in game and call this method in loop
        factoryProducts();
        domesticProducts();
        feedAnimals();
        appearWilds();
        disappearProducts();
        freeWilds();
        decreaseCageResist();
        //TODO
        //TODO after calling this method also check animal moves, dog attacks, cat catches, wild attacks, ...
    }

    private void decreaseCageResist() {

    }

    private void freeWilds() {

    }

    private void disappearProducts() {

    }

    private void appearWilds() {

    }

    private void feedAnimals() {

    }

    private void domesticProducts() {

    }

    private void factoryProducts() {

    }
}
