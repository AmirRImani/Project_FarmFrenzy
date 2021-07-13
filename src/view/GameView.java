package view;

import entry.User;
import levelController.Game;
import levels.Level;

public class GameView {
    private Game game;

    public void setInitial(Level level, User user) {
        game = new Game(level, user);
    }
}
