package view;

import animals.Animal;
import animals.Directions;
import animals.domestics.Domestic;
import animals.domestics.Domestics;
import animals.helpers.Helper;
import animals.helpers.Helpers;
import animals.wilds.Wild;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;

public class GameView implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Game game;
    private HashMap<Animal, ImageView> animalsView;
    private HashMap<Product, ImageView> productsView;
    private HashMap<Wild, ImageView> cages;

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
        productsView = getProductsView();
        animalsView = getAnimalsView();
        cages = new HashMap<>(); //TODO
    }

    public void setInitial(Game game) {
        this.game = game;
        productsView = getProductsView();
        animalsView = getAnimalsView();
        cages = new HashMap<>(); //TODO
    }

    private HashMap<Product, ImageView> getProductsView() {
        HashSet<Product> products = game.getProducts();
        HashMap<Product, ImageView> productImageView = new HashMap<>();
        for (Product product : products)
            productImageView.put(product, addProduct(product));
        return productImageView;
    }

    private HashMap<Animal, ImageView> getAnimalsView() {
        HashSet<Animal> animals = game.getAnimals();
        HashMap<Animal, ImageView> animalImageView = new HashMap<>();
        for (Animal animal : animals)
            addAnimal(animal);
        return animalImageView;
    }


    public ImageView addProduct(Product product) {
        ImageView image = new ImageView("/images/products/" + product.getNameOfProduct() + ".png");
        image.setLayoutX(40 + (product.getX()-1) * 80);
        image.setLayoutY(27.5 + (product.getY()-1) * 55);
        image.setFitWidth(40);
        image.setFitHeight(40);

        gameBoard.getChildren().add(image);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), image);
        transition.setToX(400);
        transition.setToY(450);
        transition.setCycleCount(1);

        image.setOnMouseClicked(event -> {
            if (game.pickup(product)) {
                transition.play();
                toWarehouse(product);
                gameBoard.getChildren().remove(image);
                productsView.remove(product);
            }
        });

        return image;
    }

    public void disappearProduct(Product product) {
        gameBoard.getChildren().remove(productsView.get(product));
        productsView.remove(product);
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
        Domestic domestic = game.buyDome(Domestics.HEN);
        if (domestic != null) {
            //TODO graphical
            addAnimal(domestic);
        }
    }

    public void addTurkey(ActionEvent actionEvent) {
        Domestic domestic = game.buyDome(Domestics.TURKEY);
        if (domestic != null) {
            //TODO graphical
            addAnimal(domestic);
        }
    }

    public void addBuffalo(ActionEvent actionEvent) {
        Domestic domestic = game.buyDome(Domestics.BUFFALO);
        if (domestic != null) {
            //TODO graphical
            addAnimal(domestic);
        }
    }

    public void addDog(ActionEvent actionEvent) {
        Helper helper = game.buyHelper(Helpers.DOG);
        if (helper != null) {
            //TODO graphical
            addAnimal(helper);
        }
    }

    public void addCat(ActionEvent actionEvent) {
        Helper helper = game.buyHelper(Helpers.CAT);
        if (helper != null) {
            //TODO graphical
            addAnimal(helper);
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

    public ImageView addAnimal(Animal animal) {
        String name = new String();
        if (animal instanceof Domestic)
            name = ((Domestic) animal).getName();
        else if (animal instanceof Helper)
            name = ((Helper) animal).getName();
        else if (animal instanceof Wild)
            name = ((Wild) animal).getName();
        ImageView image = new ImageView(new Image("/images/animals/" + name + ".png"));//TODO
        image.setLayoutX(40 + (animal.getX()-1) * 80);
        image.setLayoutY(27.5 + (animal.getY()-1) * 55);
        image.setFitWidth(60);
        image.setFitHeight(60);

        animalsView.put(animal, image);
        gameBoard.getChildren().add(image);

        if (animal instanceof Wild) {
            image.setOnMouseClicked(event -> {
            //TODO
            int result = game.cage((Wild) animal);

            if (result == 0) {
                toWarehouse(new Product(Products.valueOf("CAUGHT_" + ((Wild) animal).getName())));
                gameBoard.getChildren().remove(animalsView.get(animal));
                animalsView.remove(animal);
            }
//            else if (result == 1) {
//
//            } else if (result >= 10) {
//                increaseCage(animal, result-10);
//            } else if (result == 3) {
//
//            } else if (result == 4) {
//                setCage(animal);
//            }

            });
        }
        return image;
    }

    public void domeDie(Domestic domestic) {
        gameBoard.getChildren().remove(animalsView.get(domestic));
        animalsView.remove(domestic);
    }

    public void freeWild(Wild wild) {
        ImageView image = animalsView.get(wild);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), image);
        transition.setToX(805);
        transition.setCycleCount(1);

        transition.play();

        gameBoard.getChildren().remove(image);
        animalsView.remove(wild);
    }

    public void decreaseCageResist(Wild wild) {
        //TODO
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
        updateBoard();
    }

    private void updateBoard() {
        for (Animal animal : animalsView.keySet()) {
            animalsView.get(animal).setLayoutX(40 + (animal.getX()-1) * 80);
            animalsView.get(animal).setLayoutY(27.5 + (animal.getY()-1) * 55);
        }
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

//    public void walk(Animal animal, Directions direction) {
//        if (direction != null) {
//            if (direction == Directions.RIGHT)
//                animalsView.get(animal).setX(80);
//            else if (direction == Directions.LEFT)
//                animalsView.get(animal).setX(-80);
//            else if (direction == Directions.DOWN)
//                animalsView.get(animal).setY(55);
//            else if (direction == Directions.UP)
//                animalsView.get(animal).setY(-55);
//        }
//    }
}
