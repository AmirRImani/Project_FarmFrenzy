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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import levelController.Game;
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

    @FXML
    ImageView imgTruck;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableWarehouse = new TableView();
        tableTruck = new TableView();

        TableColumn<ProductView, String> column1 = new TableColumn<>("Product Name");
        column1.setMinWidth(120);
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ProductView, String> column2 = new TableColumn<>("Price");
        column2.setMinWidth(120);
        column2.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<ProductView, String> column3 = new TableColumn<>("Amount");
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

        TableColumn<ProductView, String> column4 = new TableColumn<>("Product Name");
        column4.setMinWidth(120);
        column4.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ProductView, String> column5 = new TableColumn<>("Price");
        column5.setMinWidth(120);
        column5.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<ProductView, String> column6 = new TableColumn<>("Amount");
        column6.setMinWidth(60);
        column6.setCellValueFactory(new PropertyValueFactory<>("amount"));

        tableTruck.getColumns().addAll(column4, column5, column6);
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
        for (Products product : productsAmount.keySet()) {
            if (productsAmount.get(product) > 0)
                productViews.add(new ProductView(product, product.name(), product.getPrice(), productsAmount.get(product)));
        }
        return productViews;
    }

    private ObservableList<ProductView> getTruckProducts() {
        HashMap<Products,Integer> productsAmount = game.getTruckProducts();
        ObservableList<ProductView> productViews = FXCollections.observableArrayList();
        for (Products product : productsAmount.keySet()) {
            if (productsAmount.get(product) > 0)
                productViews.add(new ProductView(product, product.name(), product.getPrice(), productsAmount.get(product)));
        }
        return productViews;
    }


    public void setInitial(Game game) {
        this.game = game;
        setItems();
        //TODO
        if (game.truckOnRoad())
            imgTruck.setImage(new Image("/images/objects/empty2.png"));
    }

    private void setItems() {
        tableWarehouse.getItems().clear();
        tableWarehouse.setItems(getWarehouseProducts());
        tableTruck.getItems().clear();
        tableTruck.setItems(getTruckProducts());

//        ObservableList<ProductView> productViews = FXCollections.observableArrayList();
//        productViews.add(new ProductView(Products.BREAD, Products.BREAD.name(), Products.BREAD.getPrice(), 5));
//        tableWarehouse.setItems(productViews);
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
        Products product = ((ProductView) tableWarehouse.getSelectionModel().getSelectedItem()).getProduct();
        if (game.truckLoad(product)) {
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

    public void truckGo(ActionEvent actionEvent) {
        if (game.truckGo())
            imgTruck.setImage(new Image("/images/objects/empty.png"));
    }
}