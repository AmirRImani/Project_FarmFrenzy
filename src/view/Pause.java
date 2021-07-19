package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import levelController.Game;

import java.io.IOException;

public class Pause {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Game game;
    private MediaPlayer mediaPlayer;

    public void setInitial(Game game, MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        this.game = game;
    }

    public void resume(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gameViewPage.fxml"));
        root = loader.load();

        GameView gameView = loader.getController();
        gameView.setInitial(game, mediaPlayer);

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void restart(ActionEvent actionEvent) throws IOException {
        Alert aLert = new Alert(Alert.AlertType.NONE);
        aLert.setTitle("Restart");
        aLert.setHeaderText("You're about to restart the game");
        aLert.setContentText("Are you sure?");
        aLert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        if(aLert.showAndWait().get() == ButtonType.YES) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("gameViewPage.fxml"));
            root = loader.load();

            GameView gameView = loader.getController();
            gameView.setInitial(game.getLevel(), game.getUser(), mediaPlayer);

            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Alert aLert = new Alert(Alert.AlertType.NONE);
        aLert.setTitle("Back to Level Menu");
        aLert.setHeaderText("You're about to go to level menu");
        aLert.setContentText("Are you sure?");
        aLert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        if(aLert.showAndWait().get() == ButtonType.YES) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("levelChooserPage.fxml"));
            root = loader.load();

            LevelChooser levelChooser = loader.getController();
            levelChooser.setInitial(game.getUser(), mediaPlayer);

            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void toFirstPage(ActionEvent actionEvent) throws IOException {
        Alert aLert = new Alert(Alert.AlertType.NONE);
        aLert.setTitle("Back to Main Menu");
        aLert.setHeaderText("You're about to go to main menu");
        aLert.setContentText("Are you sure?");
        aLert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        if(aLert.showAndWait().get() == ButtonType.YES) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("entryPage.fxml"));
            root = loader.load();

            Entry entry = loader.getController();
            entry.setInitial( mediaPlayer);

            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void exit(ActionEvent actionEvent) {
        Alert aLert = new Alert(Alert.AlertType.NONE);
        aLert.setTitle("Exit");
        aLert.setHeaderText("You're about to exit the program");
        aLert.setContentText("Are you sure?");
        aLert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        if(aLert.showAndWait().get() == ButtonType.YES) {
            System.exit(1);
        }
    }
}
