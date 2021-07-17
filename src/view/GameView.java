package view;

import animals.Animal;
import animals.domestics.Domestic;
import animals.domestics.Domestics;
import animals.helpers.Dog;
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
import levelController.objects.Board;
import levelController.objects.Grass;
import levels.Level;
import products.Product;
import products.Products;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;

public class GameView implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final int WIDTH = 480;
    private final int HEIGHT = 330;
    private Game game;
    private HashMap<Animal, ImageView> animalsView;
    private HashMap<Product, ImageView> productsView;
    private HashMap<Wild, ImageView> cages;
    private HashMap<Grass, ImageView> grassViews;

    @FXML
    AnchorPane gameBoard;

    @FXML
    VBox warehouse1, warehouse2, warehouse3, warehouse4, warehouse5;

    @FXML
    ImageView imgHen, imgTurkey, imgBuffalo, imgDog, imgCat, imgTurn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameBoard.setOnMouseClicked(event -> {
            addGrass(event.getX(), event.getY());
        });

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
        getAnimalsView();
        cages = new HashMap<>(); //TODO
        initialGrass();
    }

    public void setInitial(Game game) {
        this.game = game;
        productsView = getProductsView();
        getAnimalsView();
        cages = new HashMap<>(); //TODO
        initialGrass();
    }

    private HashMap<Product, ImageView> getProductsView() {
        HashSet<Product> products = game.getProducts();
        HashMap<Product, ImageView> productImageView = new HashMap<>();
        for (Product product : products)
            productImageView.put(product, addProduct(product));
        return productImageView;
    }

    private void getAnimalsView() {
        HashSet<Animal> animals = game.getAnimals();
        animalsView = new HashMap<>();
        for (Animal animal : animals)
            addAnimal(animal);
    }


    public ImageView addProduct(Product product) {
        ImageView image = new ImageView("/images/products/" + product.getNameOfProduct() + ".png");
        image.setLayoutX(WIDTH/(2 * Board.COLUMN.getLength()) + (product.getX() - 1) * WIDTH/Board.COLUMN.getLength());
        image.setLayoutY(HEIGHT/(2 * Board.ROW.getLength()) + (product.getY() - 1) * HEIGHT/Board.ROW.getLength());
        image.setFitWidth(40);
        image.setFitHeight(40);

        productsView.put(product, image);
        gameBoard.getChildren().add(image);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), image);
        transition.setToX(400);
        transition.setToY(450);
        transition.setCycleCount(1);

        image.setOnMouseClicked(event -> {
            if (game.pickup(product)) {
                transition.play();
                toWarehouse(product);
            }
        });

        return image;
    }

    public void disappearProduct(Product product) {
        gameBoard.getChildren().remove(productsView.get(product));
        productsView.remove(product);
    }

    public void toWarehouse(Product product) {
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

        gameBoard.getChildren().remove(productsView.get(product));
        productsView.remove(product);
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

    public ImageView addAnimal(Animal animal) {
        String name = new String();
        if (animal instanceof Domestic)
            name = ((Domestic) animal).getName();
        else if (animal instanceof Helper)
            name = ((Helper) animal).getName();
        else if (animal instanceof Wild)
            name = ((Wild) animal).getName();
        ImageView image = new ImageView(new Image("/images/animals/" + name + ".png"));//TODO
        System.out.println(animal.getX() + " " + animal.getY());
        System.out.print(WIDTH/(2 * Board.COLUMN.getLength()) + (animal.getX() - 1) * WIDTH/Board.COLUMN.getLength() + " ");
        System.out.println(HEIGHT/(2 * Board.ROW.getLength()) + (animal.getY() - 1) * HEIGHT/Board.ROW.getLength());
        image.setLayoutX(WIDTH/(2 * Board.COLUMN.getLength()) + (animal.getX() - 1) * WIDTH/Board.COLUMN.getLength());
        image.setLayoutY(HEIGHT/(2 * Board.ROW.getLength()) + (animal.getY() - 1) * HEIGHT/Board.ROW.getLength());
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

    public void plant(ActionEvent actionEvent) { //TODO by click on empty spaces of board

    }


    public void turn(ActionEvent actionEvent) {
        //TODO
        game.turn(1, this);
        updateBoard();
    }

    private void updateBoard() {
        for (Animal animal : animalsView.keySet()) {
            animalsView.get(animal).setLayoutX(WIDTH/(2 * Board.COLUMN.getLength()) + (animal.getX() - 1) * WIDTH/Board.COLUMN.getLength());
            animalsView.get(animal).setLayoutY(HEIGHT/(2 * Board.ROW.getLength()) + (animal.getY() - 1) * HEIGHT/Board.ROW.getLength());
        }
    }

    private void initialGrass() {
        grassViews = new HashMap<>();
        HashSet<Grass> grasses = game.getGrasses();
        for (Grass grass : grasses) {
            ImageView image = new ImageView(new Image("/images/objects/SINGLE_GRASS.png"));//TODO
            image.setLayoutX(WIDTH/(2 * Board.COLUMN.getLength()) + (grass.getColumn() - 1) * WIDTH/Board.COLUMN.getLength());
            image.setLayoutY(HEIGHT/(2 * Board.ROW.getLength()) + (grass.getRow() - 1) * HEIGHT/Board.ROW.getLength());
            image.setFitWidth(80);
            image.setFitHeight(80);

            grassViews.put(grass, image);
            gameBoard.getChildren().add(0,image);
        }
    }

    private void addGrass(double X, double Y) {
        int x = Math.toIntExact(Math.round((X + WIDTH/(2 * Board.COLUMN.getLength()))/(WIDTH/Board.COLUMN.getLength()) + 1));
        int y = Math.toIntExact(Math.round((Y + HEIGHT/(2 * Board.ROW.getLength()))/(HEIGHT/Board.ROW.getLength()) + 1));
        System.out.println(x + " " + y);
        Grass grass = game.plant(x,y);

        if (grass != null) {
            ImageView image = new ImageView(new Image("/images/objects/SINGLE_GRASS.png"));//TODO
            image.setLayoutX(WIDTH/(2 * Board.COLUMN.getLength()) + (grass.getColumn() - 1) * WIDTH/Board.COLUMN.getLength());
            image.setLayoutY(HEIGHT/(2 * Board.ROW.getLength()) + (grass.getRow() - 1) * HEIGHT/Board.ROW.getLength());
            image.setFitWidth(80);
            image.setFitHeight(80);

            grassViews.put(grass, image);
            gameBoard.getChildren().add(0, image);
        }
    }

    public void feedAnimal(Grass grass) {
        gameBoard.getChildren().remove(grassViews.get(grass));
        grassViews.remove(grass);
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

    public void dogAttack(Dog dog, Wild wild) {
        ImageView image1 = animalsView.get(dog);
        ImageView image2 = animalsView.get(wild);
        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.5), image1);
        transition1.setToX(805);
        transition1.setCycleCount(1);

        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(0.5), image2);
        transition2.setToX(805);
        transition2.setCycleCount(1);

        transition1.play();
        transition2.play();

        gameBoard.getChildren().remove(image1);
        gameBoard.getChildren().remove(image2);
        animalsView.remove(dog);
        animalsView.remove(wild);
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

//class GrassView {
//    private int column;
//    private int row;
//    private int quantity;
//
//    public GrassView(int column, int row, int quantity) {
//        this.column = column;
//        this.row = row;
//        this.quantity = quantity;
//    }
//}