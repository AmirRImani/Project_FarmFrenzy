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
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
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
import workshops.Workshop;
import workshops.Workshops;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;

public class GameView implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final int WIDTH = 600;
    private final int HEIGHT = 440;
    private Game game;
    private HashMap<Animal, ImageView> animalsView;
    private HashMap<Product, ImageView> productsView;
    private HashMap<Wild, ImageView> cages;
    private HashMap<Grass, ImageView> grassViews;
    private HashSet<Workshop> workshops;

    @FXML
    AnchorPane gameBoard;

    @FXML
    VBox warehouse1, warehouse2, warehouse3, warehouse4, warehouse5;

    @FXML
    ImageView imgHen, imgTurkey, imgBuffalo, imgDog, imgCat, imgTurn, imgTruck, imgWell;

    @FXML
    ImageView mill, bakery, weaving, milkPacking, iceCream, sewing;

    @FXML
    Button buyMill, buyBakery, buyWeaving, buyMilkPacking, buyIceCream, buySewing;

    @FXML
    ProgressBar progressMill, progressBakery, progressWeaving, progressMilkPacking, progressIceCream, progressSewing, progressWell;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameBoard.setOnMouseClicked(event -> {
            if (!onProduct(event.getX(), event.getY()))
                addGrass(event.getX(), event.getY());
        });

        imgWell.setOnMouseClicked(event -> {
            well();
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

    private boolean onProduct(double X, double Y) {
        int x = Math.toIntExact(Math.round((X + WIDTH/(2 * Board.COLUMN.getLength()))/(WIDTH/Board.COLUMN.getLength()) + 1));
        int y = Math.toIntExact(Math.round((Y + HEIGHT/(2 * Board.ROW.getLength()))/(HEIGHT/Board.ROW.getLength()) + 1));
        System.out.println("Grass " + x + " " + y);

        for (Product product : productsView.keySet()) {
            System.out.println("Product " +product.getX() + " " + product.getY());
            if(product.getX() == x && product.getY() == y)
                return true;
        }
        return false;
    }

    private void workshopInitialize() {
        boolean[] found = new boolean[6];
        for (int i = 0; i < 6; i++) {
            found[i] = false;
        }
        for (Workshop workshop : workshops) {
            if (workshop.getName().equals(Workshops.MILL.name())) {
                found[0] = true;
                if (workshop.maxLevel())
                    buyMill.getStylesheets().add("/view/styles/buttonStyles/upgradeButtonOff.css");
                else {
                    buyMill.getStylesheets().add("/view/styles/buttonStyles/upgradeButton.css");
                    buyMill.setOnAction(event -> {
                        upgradeWorkshop(Workshops.MILL);
                    });
                } mill.setImage(new Image("/images/workshops/MILL.png"));
                mill.setOnMouseClicked(event -> {
                    game.work(workshop);
                });
            } else if (workshop.getName().equals(Workshops.BAKERY.name())) {
                found[1] = true;
                if (workshop.maxLevel())
                    buyBakery.getStylesheets().add("/view/styles/buttonStyles/upgradeButtonOff.css");
                else {
                    buyBakery.getStylesheets().add("/view/styles/buttonStyles/upgradeButton.css");
                    buyMill.setOnAction(event -> {
                        upgradeWorkshop(Workshops.BAKERY);
                    });
                } bakery.setImage(new Image("/images/workshops/BAKERY.png"));
                bakery.setOnMouseClicked(event -> {
                    game.work(workshop);
                });
            } else if (workshop.getName().equals(Workshops.WEAVING.name())) {
                found[2] = true;
                if (workshop.maxLevel())
                    buyWeaving.getStylesheets().add("/view/styles/buttonStyles/upgradeButtonOff.css");
                else {
                    buyWeaving.getStylesheets().add("/view/styles/buttonStyles/upgradeButton.css");
                    buyMill.setOnAction(event -> {
                        upgradeWorkshop(Workshops.WEAVING);
                    });
                } weaving.setImage(new Image("/images/workshops/WEAVING.png"));
                weaving.setOnMouseClicked(event -> {
                    game.work(workshop);
                });
            } else if (workshop.getName().equals(Workshops.MILK_PACKING.name())) {
                found[3] = true;
                if (workshop.maxLevel())
                    buyMilkPacking.getStylesheets().add("/view/styles/buttonStyles/upgradeButtonOff.css");
                else {
                    buyMilkPacking.getStylesheets().add("/view/styles/buttonStyles/upgradeButton.css");
                    buyMill.setOnAction(event -> {
                        upgradeWorkshop(Workshops.MILK_PACKING);
                    });
                } milkPacking.setImage(new Image("/images/workshops/MILK_PACKING.png"));
                milkPacking.setOnMouseClicked(event -> {
                    game.work(workshop);
                });
            } else if (workshop.getName().equals(Workshops.ICE_CREAM_SHOP.name())) {
                found[4] = true;
                if (workshop.maxLevel())
                    buyIceCream.getStylesheets().add("/view/styles/buttonStyles/upgradeButtonOff.css");
                else {
                    buyIceCream.getStylesheets().add("/view/styles/buttonStyles/upgradeButton.css");
                    buyMill.setOnAction(event -> {
                        upgradeWorkshop(Workshops.ICE_CREAM_SHOP);
                    });
                } iceCream.setImage(new Image("/images/workshops/ICE_CREAM_SHOP.png"));
                iceCream.setOnMouseClicked(event -> {
                    game.work(workshop);
                });
            } else if (workshop.getName().equals(Workshops.SEWING.name())) {
                found[5] = true;
                if (workshop.maxLevel())
                    buySewing.getStylesheets().add("/view/styles/buttonStyles/upgradeButtonOff.css");
                else {
                    buySewing.getStylesheets().add("/view/styles/buttonStyles/upgradeButton.css");
                    buyMill.setOnAction(event -> {
                        upgradeWorkshop(Workshops.SEWING);
                    });
                } sewing.setImage(new Image("/images/workshops/SEWING.png"));
                sewing.setOnMouseClicked(event -> {
                    game.work(workshop);
                });
            }
        }

        if (!found[0]) {
            buyMill.getStylesheets().add("/view/styles/buttonStyles/buyButton.css");
            buyMill.setOnAction(event -> {
                buildWorkshop(Workshops.MILL);
            });
            mill.setImage(new Image("/images/workshops/MILLoff.png"));
        }
        if (!found[1]) {
            buyBakery.getStylesheets().add("/view/styles/buttonStyles/buyButton.css");
            buyBakery.setOnAction(event -> {
                buildWorkshop(Workshops.BAKERY);
            });
            bakery.setImage(new Image("/images/workshops/BAKERYoff.png"));
        }
        if (!found[2]) {
            buyWeaving.getStylesheets().add("/view/styles/buttonStyles/buyButton.css");
            buyWeaving.setOnAction(event -> {
                buildWorkshop(Workshops.WEAVING);
            });
            weaving.setImage(new Image("/images/workshops/WEAVINGoff.png"));
        }
        if (!found[3]) {
            buyMilkPacking.getStylesheets().add("/view/styles/buttonStyles/buyButton.css");
            buyMilkPacking.setOnAction(event -> {
                buildWorkshop(Workshops.MILK_PACKING);
            });
            milkPacking.setImage(new Image("/images/workshops/MILK_PACKINGoff.png"));
        }
        if (!found[4]) {
            buyIceCream.getStylesheets().add("/view/styles/buttonStyles/buyButton.css");
            buyIceCream.setOnAction(event -> {
                buildWorkshop(Workshops.ICE_CREAM_SHOP);
            });
            iceCream.setImage(new Image("/images/workshops/ICE_CREAM_SHOPoff.png"));
        }
        if (!found[5]) {
            buySewing.getStylesheets().add("/view/styles/buttonStyles/buyButton.css");
            buySewing.setOnAction(event -> {
                buildWorkshop(Workshops.SEWING);
            });
            sewing.setImage(new Image("/images/workshops/SEWINGoff.png"));
        }
    }

    public void setInitial(Level level, User user) {
        game = new Game(level, user);
        workshops = game.getWorkshops();
        productsView = getProductsView();
        workshopInitialize();
        warehouseInitialize();
        getAnimalsView();
        cages = new HashMap<>(); //TODO
        initialGrass();

        progressWell.setProgress(game.wellProgress());

        if (game.truckOnRoad())
            imgTruck.setImage(new Image("/images/objects/empty2.png"));
    }

    public void setInitial(Game game) {
        this.game = game;
        workshops = game.getWorkshops();
        productsView = getProductsView();
        workshopInitialize();
        warehouseInitialize();
        getAnimalsView();
        cages = new HashMap<>(); //TODO
        initialGrass();

        progressWell.setProgress(game.wellProgress());

        if (game.truckOnRoad())
            imgTruck.setImage(new Image("/images/objects/empty2.png"));
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

    private void warehouseInitialize() {
        for (Product product : game.getWarehouseProducts()) {
            toWarehouse(product);
        }
    }


    public ImageView addProduct(Product product) {
        ImageView image = new ImageView("/images/products/" + product.getNameOfProduct() + ".png");
        image.setLayoutX(WIDTH/(2 * Board.COLUMN.getLength()) + (product.getX() - 1) * WIDTH/Board.COLUMN.getLength());
        image.setLayoutY(HEIGHT/(2 * Board.ROW.getLength()) + (product.getY() - 1) * HEIGHT/Board.ROW.getLength());
        image.setFitWidth(40);
        image.setFitHeight(40);

        if (productsView == null)
            productsView = new HashMap<>();

        productsView.put(product, image);
        gameBoard.getChildren().add(0,image);

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

        if (warehouse1.getChildren().size() < 4)
            warehouse1.getChildren().add(image);
        else if (warehouse2.getChildren().size() < 4)
            warehouse2.getChildren().add(image);
        else if (warehouse3.getChildren().size() < 4)
            warehouse3.getChildren().add(image);
        else if (warehouse4.getChildren().size() < 4)
            warehouse4.getChildren().add(image);
        else
            warehouse5.getChildren().add(image);

        if (productsView.containsKey(product)) {
            gameBoard.getChildren().remove(productsView.get(product));
            productsView.remove(product);
        }
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

    public void wildAttack(Animal animal) {
        ImageView image1 = animalsView.get(animal);
        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.5), image1);
        transition1.setToX(805);
        transition1.setCycleCount(1);

        transition1.play();

        gameBoard.getChildren().remove(image1);
        animalsView.remove(animal);
    }

    public void decreaseCageResist(Wild wild) {
        //TODO
    }


    private void buildWorkshop(Workshops workshop) {
        //TODO
        Workshop workshop1 = game.build(workshop);
        if (workshop1 != null) {
            builtWorkshop(workshop, workshop1);
        }
    }

    private void builtWorkshop(Workshops workshop, Workshop workshop1) {
        if (workshop == Workshops.MILL) {
            mill.setOnMouseClicked(event -> {
                game.work(workshop1);
            });
            buyMill.getStylesheets().add("/view/styles/buttonStyles/upgradeButton.css");
            buyMill.setOnAction(event -> {
                upgradeWorkshop(Workshops.MILL);
            });
            mill.setImage(new Image("/images/workshops/MILL.png"));
        } else if (workshop == Workshops.BAKERY) {
            bakery.setOnMouseClicked(event -> {
                game.work(workshop1);
            });
            buyBakery.getStylesheets().add("/view/styles/buttonStyles/upgradeButton.css");
            buyBakery.setOnAction(event -> {
                upgradeWorkshop(Workshops.BAKERY);
            });
            bakery.setImage(new Image("/images/workshops/BAKERY.png"));
        } else if (workshop == Workshops.WEAVING) {
            weaving.setOnMouseClicked(event -> {
                game.work(workshop1);
            });
            buyWeaving.getStylesheets().add("/view/styles/buttonStyles/upgradeButton.css");
            buyWeaving.setOnAction(event -> {
                upgradeWorkshop(Workshops.WEAVING);
            });
            weaving.setImage(new Image("/images/workshops/WEAVING.png"));
        } else if (workshop == Workshops.MILK_PACKING) {
            milkPacking.setOnMouseClicked(event -> {
                game.work(workshop1);
            });
            buyMilkPacking.getStylesheets().add("/view/styles/buttonStyles/upgradeButton.css");
            buyMilkPacking.setOnAction(event -> {
                upgradeWorkshop(Workshops.MILK_PACKING);
            });
            milkPacking.setImage(new Image("/images/workshops/MILK_PACKING.png"));
        } else if (workshop == Workshops.ICE_CREAM_SHOP) {
            iceCream.setOnMouseClicked(event -> {
                game.work(workshop1);
            });
            buyIceCream.getStylesheets().add("/view/styles/buttonStyles/upgradeButton.css");
            buyIceCream.setOnAction(event -> {
                upgradeWorkshop(Workshops.ICE_CREAM_SHOP);
            });
            iceCream.setImage(new Image("/images/workshops/ICE_CREAM_SHOP.png"));
        } else if (workshop == Workshops.SEWING) {
            sewing.setOnMouseClicked(event -> {
                game.work(workshop1);
            });
            buySewing.getStylesheets().add("/view/styles/buttonStyles/upgradeButton.css");
            buySewing.setOnAction(event -> {
                upgradeWorkshop(Workshops.SEWING);
            });
            sewing.setImage(new Image("/images/workshops/SEWING.png"));
        }
    }

    private void upgradeWorkshop(Workshops workshop) {
        //TODO
        if (game.upgradeWorkshop(workshop)) {

        }
    }

    private void well() {
        //TODO
        game.well();
    }


    public void turn(ActionEvent actionEvent) {
        //TODO
        game.turn(1, this);
        updateBoard();
        for (Workshop workshop : workshops) {
            progressWorkshop(workshop);
        }

        if (!game.truckOnRoad())
            imgTruck.setImage(new Image("/images/objects/truck2.png"));
    }

    private void progressWorkshop(Workshop workshop) {
        if (workshop.getName().equals(Workshops.MILL.name()) && workshop.isBusy())
            progressMill.setProgress(workshop.progress());
        else if (workshop.getName().equals(Workshops.BAKERY.name()) && workshop.isBusy())
            progressBakery.setProgress(workshop.progress());
        else if (workshop.getName().equals(Workshops.WEAVING.name()) && workshop.isBusy())
            progressWeaving.setProgress(workshop.progress());
        else if (workshop.getName().equals(Workshops.MILK_PACKING.name()) && workshop.isBusy())
            progressMilkPacking.setProgress(workshop.progress());
        else if (workshop.getName().equals(Workshops.ICE_CREAM_SHOP.name()) && workshop.isBusy())
            progressIceCream.setProgress(workshop.progress());
        else if (workshop.getName().equals(Workshops.SEWING.name()) && workshop.isBusy())
                progressSewing.setProgress(workshop.progress());
    }

    private void updateBoard() {
        for (Animal animal : animalsView.keySet()) {
            animalsView.get(animal).setLayoutX(WIDTH/(2 * Board.COLUMN.getLength()) + (animal.getX() - 1) * WIDTH/Board.COLUMN.getLength());
            animalsView.get(animal).setLayoutY(HEIGHT/(2 * Board.ROW.getLength()) + (animal.getY() - 1) * HEIGHT/Board.ROW.getLength());
        }

        progressWell.setProgress(game.wellProgress());
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

            progressWell.setProgress(game.wellProgress());
        }
    }

    public void feedAnimal(Grass grass) {
        gameBoard.getChildren().remove(grassViews.get(grass));
        grassViews.remove(grass);
    }


    @FXML
    private void warehouseOpen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("warehouseViewPage.fxml"));
        root = loader.load();

        WarehouseView warehouseView = loader.getController();
        warehouseView.setInitial(game);

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    public void workshopProduct(Workshop workshop, Product product) {
        addProduct(product);
        if (workshop.getName().equals(Workshops.MILL.name()) && workshop.isBusy())
            progressMill.setProgress(0);
        else if (workshop.getName().equals(Workshops.BAKERY.name()) && workshop.isBusy())
            progressBakery.setProgress(0);
        else if (workshop.getName().equals(Workshops.WEAVING.name()) && workshop.isBusy())
            progressWeaving.setProgress(0);
        else if (workshop.getName().equals(Workshops.MILK_PACKING.name()) && workshop.isBusy())
            progressMilkPacking.setProgress(0);
        else if (workshop.getName().equals(Workshops.ICE_CREAM_SHOP.name()) && workshop.isBusy())
            progressIceCream.setProgress(0);
        else if (workshop.getName().equals(Workshops.SEWING.name()) && workshop.isBusy())
            progressSewing.setProgress(0);
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