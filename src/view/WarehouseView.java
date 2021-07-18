package view;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import levelController.Game;
import products.Product;
import products.Products;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class WarehouseView implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private TableView tableWarehouse, tableTruck;
    private Game game;
    private Products productSelectedWarehouse, productSelectedTruck;

    @FXML
    AnchorPane anchorPane1, anchorPane2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableWarehouse = new TableView();
        tableTruck = new TableView();

        TableColumn<Product, String> column1 = new TableColumn<>("Product Name");
        column1.setMinWidth(120);
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, String> column2 = new TableColumn<>("Price");
        column2.setMinWidth(120);
        column2.setCellValueFactory(new PropertyValueFactory<>("price"));


        TableColumn<Product, String> column3 = new TableColumn<>("Amount");
        column3.setMinWidth(60);
        column3.setCellValueFactory(new PropertyValueFactory<>("amount"));

        tableWarehouse.getColumns().addAll(column1, column2, column3);
        tableWarehouse.setOpacity(0.75);
        tableWarehouse.setMaxHeight(600);

        anchorPane1.getChildren().add(tableWarehouse);

        TableView.TableViewSelectionModel selectionModel = tableWarehouse.getSelectionModel();
        ObservableList<ProductView> productViews = selectionModel.getSelectedItems();
        productViews.addListener(new ListChangeListener<ProductView>() {
            @Override
            public void onChanged(Change<? extends ProductView> change) {
                productSelectedWarehouse = productViews.get(0).getProduct();
            }
        });

        tableTruck.getColumns().addAll(column1, column2, column3);
        tableTruck.setOpacity(0.75);
        tableTruck.setMaxHeight(600);

        anchorPane2.getChildren().add(tableTruck);

        TableView.TableViewSelectionModel selectionModel2 = tableTruck.getSelectionModel();
        ObservableList<ProductView> productViews2 = selectionModel2.getSelectedItems();
        productViews2.addListener(new ListChangeListener<ProductView>() {
            @Override
            public void onChanged(Change<? extends ProductView> change) {
                productSelectedTruck = productViews2.get(0).getProduct();
            }
        });
    }

    private ObservableList<ProductView> getWarehouseProducts() {
        HashMap<Products,Integer> productsAmount = game.getWarehouseProducts(0);
        ObservableList<ProductView> productViews = FXCollections.observableArrayList();
        for (Products product : productsAmount.keySet())
            productViews.add(new ProductView(product, product.name(), product.getPrice(), productsAmount.get(product)));
        return productViews;
    }

    private ObservableList<ProductView> getTruckProducts() {
        HashMap<Products,Integer> productsAmount = game.getTruckProducts();
        ObservableList<ProductView> productViews = FXCollections.observableArrayList();
        for (Products product : productsAmount.keySet())
            productViews.add(new ProductView(product, product.name(), product.getPrice(), productsAmount.get(product)));
        for (ProductView productView : productViews) {
            System.out.println(productView);
        }
        return productViews;
    }


    public void setInitial(Game game) {
        this.game = game;
        setItems();
        //TODO
    }

    private void setItems() {
        tableWarehouse.getItems().clear();
        tableWarehouse.setItems(getWarehouseProducts());
        tableTruck.getItems().clear();
        tableTruck.setItems(getTruckProducts());

        for (Object item : tableWarehouse.getItems()) {
            System.out.println(item);
        }
    }


    public void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gameViewPage.fxml"));
        root = loader.load();

        GameView gameView = loader.getController();
        gameView.setInitial(game);

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void truckLoad(ActionEvent actionEvent) {
        if (game.truckLoad(productSelectedWarehouse)) {
            setItems();
            for (Object item : tableWarehouse.getItems()) {
                System.out.println(item);
            }
        }
    }

    public void truckUnload(ActionEvent actionEvent) {
        if (game.truckUnload(productSelectedTruck))
            setItems();
    }
}

class ProductView {
    private Products product;
    private String name;
    private int price;
    private int amount;

    @Override
    public String toString() {
        return "ProductView{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    public Products getProduct() { return product; }

    public String getName() { return name; }

    public int getPrice() { return price; }

    public int getAmount() { return amount; }

    public ProductView(Products product, String name, int price, int amount) {
        this.product = product;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}