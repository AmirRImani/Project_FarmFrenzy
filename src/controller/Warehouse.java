package controller;

import products.Product;

import java.util.HashMap;

public class Warehouse {
    int level;
    int highestSpace;
    int space;
    HashMap<Product,Integer> amountOfProduct;
}
