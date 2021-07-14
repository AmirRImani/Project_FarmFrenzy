package view;

import entry.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import levels.Level;
import levels.LevelsOperation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LevelChooser implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private User user;
    private int numberOfLevels;
    private int unlockedLevels;
    private Button[] buttons;

    @FXML
    AnchorPane anchorpane;

    public void setInitial(User user) {
        this.user = user;
        numberOfLevels = LevelsOperation.NUMBER_OF_LEVELS;
        unlockedLevels = user.getUnlockedLevels();

        buttons = new Button[numberOfLevels];
        for (int i = 0; i < numberOfLevels; i++) {
            buttons[i] = new Button();
            if(unlockedLevels >= i+1) {
                buttons[i].getStylesheets().add("/view/styles/buttonStyles/levelButtons/levelButton" + (i + 1) + ".css");
                int finalI = i;
                buttons[i].setOnAction(event -> {
                    try {
                        toLevel(event, user, finalI + 1);
                    } catch (IOException e) {

                    }
                });
            }
            else
                buttons[i].getStylesheets().add("/view/styles/buttonStyles/levelButtons/levelButtonLocked" + (i+1) + ".css");
            buttons[i].setPrefWidth(40);
            buttons[i].setPrefHeight(40);
            buttons[i].setLayoutX(50 + 100 * i);
            buttons[i].setLayoutY(430 - 30 * i);

            anchorpane.getChildren().add(buttons[i]);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


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

    public void back(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toLevel(ActionEvent actionEvent, User user, int levelNumber) throws IOException {
        Level level = LevelsOperation.getInstance().getLevel(levelNumber);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gamePage.fxml"));
        root = loader.load();

        GameView gameView = loader.getController();
        gameView.setInitial(level, user);

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
