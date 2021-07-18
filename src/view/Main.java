package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       music();
        Image icon = new Image(getClass().getResourceAsStream("/images/iconsAndLabels/farmFrenzyIcon.jpg"));
        Parent root = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
        primaryStage.setTitle("Farm Frenzy - Sharif edition");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(icon);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            try {
                exit(primaryStage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }


    public static void main(String[] args) {
        launch(args);

    }

    public void exit(Stage stage) throws IOException {
        Alert aLert = new Alert(Alert.AlertType.NONE);

        aLert.setTitle("Exit");
        aLert.setHeaderText("You're about to exit the program");
        aLert.setContentText("Are you sure?");
        aLert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        if(aLert.showAndWait().get() == ButtonType.YES) {
            System.exit(1);
        }
    }


    MediaPlayer mediaPlayer1;
    public void music() {
        String path = getClass().getResource("/musics/MainMenu.mp3").getPath();
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer1 = new MediaPlayer(media);
        mediaPlayer1.setCycleCount(-1);
        mediaPlayer1.play();
    }

}
