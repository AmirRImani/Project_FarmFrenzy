package sharedClasses;

import view.Game;

public class TimeProcessor {
    public static int currentStep;

    private static TimeProcessor timeInstance;
    public static TimeProcessor getInstance(){
        if(timeInstance == null) {
            timeInstance = new TimeProcessor();
            currentStep = 1;
        }
        return timeInstance;
    }

    public boolean changeSteps(int step, Game game){
        for (int i = 0; i < step; i++) {
            if(changeStep(game))
                return true;
        }
        return false;
    }

    private boolean changeStep(Game game){//TODO make change in one step  in order to skip two or more steps make a for loop in game and call this method in loop
        boolean exit;
        currentStep ++;
        game.walk();
        game.workshopProducts();
        game.domesticProducts();
        game.feedAnimals();
        game.domeHealth();
        game.domeDie();
        game.appearWilds();
        game.disappearProducts();
        game.freeWilds();
        game.decreaseCageResist();
        game.dogAttack();
        game.catCatches();
        game.wildAttack();
        game.transport();
        game.grassAlarm();
        game.showDetails();
        exit = game.checkWin();
        //TODO
        //TODO after calling this method also check animal moves, dog attacks, cat catches, wild attacks, ...
        return exit;

    }

}
