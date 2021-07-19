package view;

import entry.EnterProcess;
import entry.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.IOException;

public class Login {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private MediaPlayer mediaPlayer;

    @FXML
    Button btnExit, btnLogin;

    @FXML
    TextField txtUsername;

    @FXML
    PasswordField txtPassword;

    @FXML
    Label labelLogin;

    public void setInitial(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void login(javafx.event.ActionEvent actionEvent) throws IOException {
        EnterProcess enterProcess = new EnterProcess();
        User user = enterProcess.login(txtUsername.getText(), txtPassword.getText());
        if(txtUsername.getText().isEmpty())
            labelLogin.setText("Please enter your Username");
        else if(txtPassword.getText().isEmpty())
            labelLogin.setText("Please enter your Password");
        else if(user != null) {
            labelLogin.setText("Hello " + txtUsername.getText() + "\nWelcome to Sharif Farm");
            toMainPage(actionEvent, user);
        }
        else
            labelLogin.setText("Username or Password is incorrect");
    }

    private void toMainPage(ActionEvent actionEvent, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("levelChooserPage.fxml"));
        root = loader.load();

        LevelChooser levelChooser = loader.getController();
        levelChooser.setInitial(user, mediaPlayer);

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("entryPage.fxml"));
        root = loader.load();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        Entry entry = loader.getController();
        entry.setInitial(mediaPlayer);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
