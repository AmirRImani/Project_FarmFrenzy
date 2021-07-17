package sharedClasses;

import levelController.Game;
import view.GameView;

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

    public boolean changeSteps(int step, Game game, GameView gameView){
        boolean last = false;
        for (int i = 0; i < step; i++) {
            if (i == step - 1)
                last = true;
            if(changeStep(game, gameView, last))
                return true;
        }
        return false;
    }

    private boolean changeStep(Game game, GameView gameView, boolean last){//TODO make change in one step  in order to skip two or more steps make a for loop in game and call this method in loop
        boolean exit;
        currentStep ++;
        game.walk(gameView);
        game.workshopProducts(gameView);
        game.domesticProducts(gameView);
        game.feedAnimals();
        game.domeHealth();
        game.domeDie(gameView);
        game.appearWilds(gameView);
        game.disappearProducts(gameView);
        game.freeWilds(gameView);
        game.decreaseCageResist(gameView);
//        game.dogAttack(gameView);
//        game.catCatches(gameView);
//        game.wildAttack(gameView);
        game.transportCompleted();
        game.grassAlarm(last);
        //game.showDetails(last);
        exit = game.checkWin();
        //TODO
        //TODO after calling this method also check animal moves, dog attacks, cat catches, wild attacks, ...
        return exit;

    }

}
