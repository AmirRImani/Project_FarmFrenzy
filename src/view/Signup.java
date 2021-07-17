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
import javafx.stage.Stage;
import java.io.IOException;

public class Signup {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Button btnExit, btnSignup;

    @FXML
    TextField txtUsername, txtEmail;

    @FXML
    PasswordField txtPassword;

    @FXML
    Label labelSignup;

    public void signup(javafx.event.ActionEvent actionEvent) throws IOException {
        EnterProcess enterProcess = new EnterProcess();
        User user = enterProcess.signup(txtUsername.getText(), txtPassword.getText(), txtEmail.getText());
        if(txtUsername.getText().isEmpty())
            labelSignup.setText("Please enter your Username");
        else if(txtPassword.getText().isEmpty())
            labelSignup.setText("Please enter your Password");
        else if(txtEmail.getText().isEmpty())
            labelSignup.setText("Please enter your email address");
        else if(user != null) {
            labelSignup.setText("Hello " + txtUsername.getText() + "\nWelcome to Sharif Market");
            enterProcess.addUser(user);
            toMainPage(actionEvent, user);
        }
        else
            labelSignup.setText("This username already exists");
    }

    private void toMainPage(ActionEvent actionEvent, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("levelChooserPage.fxml"));
        root = loader.load();

        LevelChooser levelChooser = loader.getController();
        levelChooser.setInitial(user);

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
        root = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
