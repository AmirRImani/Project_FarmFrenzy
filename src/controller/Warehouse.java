package controller;

import products.Products;
import vehicles.Truck;

import java.util.HashMap;

public class Warehouse {
    private final int fullSpace = 30;
    private int remainedSpace;
    HashMap<Products,Integer> amountOfProduct;

    public Warehouse() {
        this.remainedSpace = fullSpace;
        this.amountOfProduct = new HashMap<>();
    }

    public int getSpace() { return remainedSpace; }

    public boolean addProduct(Products product, int amount) {
        int space = product.getSpace();
        if(remainedSpace >= space){
            if (amountOfProduct.containsKey(product))
                amountOfProduct.replace(product, amountOfProduct.get(product) + amount);
            else
                amountOfProduct.put(product, amount);
            this.remainedSpace -= space;
            return true;
        }
        return false;
    }

    public boolean enoughAmount(Products product, int amount) {
        if(!amountOfProduct.containsKey(product))
            return false;
        if(amountOfProduct.get(product) >= amount)
            return true;
        return false;
    }


    public boolean truckLoad(Products product, Truck truck) {
        if(amountOfProduct.containsKey(product)){
            int amount = truck.getCapacity() / product.getSpace();
            int availableAmount = amountOfProduct.get(product);
            int quantity = Math.min(amount, availableAmount);
            if(amount == 0) {
                System.out.println("Truck doesn't have enough space");
                return false;
            } else if(availableAmount == 0){
                System.out.println("This product isn't available");
                return false;
            } else{
                truck.load(product, quantity);
                amountOfProduct.replace(product, amountOfProduct.get(product) - quantity);
                System.out.println("Truck loaded " + quantity + " " + product.name());
                return true;
            }
        } else {
            System.out.println("This product isn't available");
            return false;
        }
    }

    public int amount(Products product){
        return amountOfProduct.get(product);
    }

}
