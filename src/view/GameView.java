package view;

import entry.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import levelController.Game;
import levels.Level;

import java.io.IOException;

public class GameView {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Game game;

    public void setInitial(Level level, User user) {
        game = new Game(level, user);
    }

    public void setInitial(Game game) {
        this.game = game;
    }

    public void pause(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("pausePage.fxml"));
        root = loader.load();

        Pause pause = loader.getController();
        pause.setInitial(game);

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
