package view;

import animals.domestics.Domestics;
import animals.helpers.Helpers;
import animals.wilds.Wild;
import animals.wilds.Wilds;
import entry.User;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import levelController.Game;
import levels.Level;
import products.Product;
import products.Products;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameView implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Game game;

    @FXML
    AnchorPane gameBoard;

    @FXML
    VBox warehouse1, warehouse2, warehouse3, warehouse4, warehouse5;

    @FXML
    ImageView imgHen, imgTurkey, imgBuffalo, imgDog, imgCat, imgTurn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgHen.setOnMouseClicked(event -> {
            addHen(new ActionEvent());
        });
        imgTurkey.setOnMouseClicked(event -> {
            addTurkey(new ActionEvent());
        });
        imgBuffalo.setOnMouseClicked(event -> {
            addBuffalo(new ActionEvent());
        });
        imgDog.setOnMouseClicked(event -> {
            addDog(new ActionEvent());
        });
        imgCat.setOnMouseClicked(event -> {
            addCat(new ActionEvent());
        });
        imgTurn.setOnMouseClicked(event -> {
            turn(new ActionEvent());
        });
    }

    public void setInitial(Level level, User user) {
        game = new Game(level, user);
    }

    public void setInitial(Game game) {
        this.game = game;
    }

    public void addProduct(Product product) {
        ImageView image = new ImageView("/images/products/" + product.getNameOfProduct() + ".png");
        image.setLayoutX(40 + (product.getX()-1) * 80);
        image.setLayoutY(27.5 + (product.getY()-1) * 55);
        image.setFitWidth(40);
        image.setFitHeight(40);

        gameBoard.getChildren().add(image);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), image);
        transition.setToX(400);
        transition.setToY(450);
        transition.setCycleCount(1);

        image.setOnMouseClicked(event -> {
            if (game.pickup(product)) {
                transition.play();
                toWarehouse(product);
                gameBoard.getChildren().remove(image);
            }
        });
    }

    private void toWarehouse(Product product) {
        ImageView image = new ImageView("/images/warehouseItems/" + product.getNameOfProduct() + ".png");
        image.setFitWidth(24);
        image.setFitHeight(24);

        if (warehouse1.getChildren().size() < 3)
            warehouse1.getChildren().add(image);
        else if (warehouse2.getChildren().size() < 3)
            warehouse2.getChildren().add(image);
        else if (warehouse3.getChildren().size() < 3)
            warehouse3.getChildren().add(image);
        else if (warehouse4.getChildren().size() < 3)
            warehouse4.getChildren().add(image);
        else
            warehouse5.getChildren().add(image);
    }

    public void addHen(ActionEvent actionEvent) {
        if (game.buyDome(Domestics.HEN)) {
            //TODO graphical
            showAnimal("Hen");
        }
    }

    public void addTurkey(ActionEvent actionEvent) {
        if (game.buyDome(Domestics.TURKEY)) {
            //TODO graphical
            showAnimal("Turkey");
        }
    }

    private void truckLoad() {
        //TODO
    }

    private void truckUnLoad() {
        //TODO
    }

    private void truckGo() {
        //TODO
    }

    private void buildWorkshop() {
        //TODO
    }

    private void upgradeWorkshop() {
        //TODO
    }

    private void well() {
        //TODO
    }

    public void addBuffalo(ActionEvent actionEvent) {
        if (game.buyDome(Domestics.BUFFALO)) {
            //TODO graphical
            showAnimal("Buffalo");
        }
    }

    public void addDog(ActionEvent actionEvent) {
        if (game.buyHelper(Helpers.DOG)) {
            //TODO graphical
            showAnimal("Dog");
        }
    }

    public void addCat(ActionEvent actionEvent) {
        if (game.buyHelper(Helpers.CAT)) {
            //TODO graphical
            showAnimal("Cat");
        }
    }

    private void showAnimal(String animal) {
        ImageView imageView = new ImageView(new Image("/images/animals/" + animal /*...*/  ));//TODO
    }

//    private void addWild(String wildName) {
//        //TODO set on action about cage
//        Wild wild = game.addWild(Wilds.valueOf(wildName.toUpperCase()));
//        ImageView imageView = new ImageView(new Image("/images/animals/" + wildName /*...*/  ));
//        imageView.setOnMouseClicked(event -> {
//            //TODO
//            game.cage(wild);
//        });
//    }

    public void turn(ActionEvent actionEvent) {
        //TODO
        game.turn(1, this);
        //show();
    }



    public void plant(ActionEvent actionEvent) { //TODO by click on empty spaces of board

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
