package sharedClasses;

import view.Game;

public class TimeProcessor {
    public static int currentStep;

    private static TimeProcessor timeInstance;
    public static TimeProcessor getInstance(){
        if(timeInstance == null) {
            timeInstance = new TimeProcessor();
            currentStep = 0;
        }
        return timeInstance;
    }

    public void changeSteps(int step, Game game){
        for (int i = 0; i < step; i++) {
            changeStep(game);
        }
    }

    private void changeStep(Game game){//TODO make change in one step  in order to skip two or more steps make a for loop in game and call this method in loop
        game.workshopProducts();
        game.domesticProducts();
        game.feedAnimals();
        game.appearWilds();
        game.disappearProducts();
        game.freeWilds();
        game.decreaseCageResist();
        game.dogAttack();
        game.catCatches();
        game.wildAttack();
        game.walk();
        game.showDetails();
        game.checkWin();
        //TODO
        //TODO after calling this method also check animal moves, dog attacks, cat catches, wild attacks, ...
        currentStep ++;
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

    private void workshopProducts() {

    }
}
