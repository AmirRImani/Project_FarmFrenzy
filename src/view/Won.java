package view;

import entry.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

public class Won {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private MediaPlayer mediaPlayer;
    private User user;

    public void setInitial(User user, MediaPlayer mediaPlayer) {
        this.user = user;
        this.mediaPlayer = mediaPlayer;
    }

    public void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("levelChooserPage.fxml"));
        root = loader.load();

        LevelChooser levelChooser = loader.getController();
        levelChooser.setInitial(user, mediaPlayer,0);

        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
