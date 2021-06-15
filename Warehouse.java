package controller;

import products.Product;

import java.util.HashMap;

public class Warehouse {
    private final int fullSpace = 30;
    private int remainedSpace;
    HashMap<Product,Integer> amountOfProduct;

    protected boolean store(Product product){
        return false;//TODO
    }
}
