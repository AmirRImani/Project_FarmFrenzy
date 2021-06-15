package vehicles;

import controller.Warehouse;
import products.Products;
import sharedClasses.TimeProcessor;

import java.util.HashMap;


public class Truck {
    private final int CAPACITY = 15;
    private final int TIME_OF_TRAVEL = 10;
    private int remainedCapacity;
    private boolean startToGo;
    private int startTime; //TODO time when truck started to go
    private HashMap<Products,Integer> amountOfProducts;

    public int getCapacity() { return remainedCapacity; }

    public Truck() {
        this.remainedCapacity = CAPACITY;
        this.startToGo = false;
        this.startTime = 0;
        this.amountOfProducts = new HashMap<>();
    }

    public void load(Products product, int quantity) {
        this.remainedCapacity += quantity * product.getSpace();
        if(amountOfProducts.containsKey(product))
            amountOfProducts.replace(product, amountOfProducts.get(product) + quantity);
        else
            amountOfProducts.put(product, quantity);
    }

    public boolean unload(Products product, Warehouse warehouse) {
        if(amountOfProducts.containsKey(product)) {
            int amount = amountOfProducts.get(product);
            int availableAmount = warehouse.getSpace();
            int quantity = Math.min(amount, availableAmount);
            if(amount == 0) {
                System.out.println("This product isn't available");
                return false;
            } else if(availableAmount == 0){
                System.out.println("Warehouse doesn't have enough space");
                return false;
            } else{
                this.remainedCapacity -= quantity * product.getSpace();
                warehouse.addProduct(product, quantity);
                System.out.println("Truck unloaded " + quantity + " " + product.name());
                return true;
            }
        } else {
            System.out.println("Not Found");
            return false;
        }
    }

    public boolean transport() {
        if(startToGo)
            return false;
        else {
            this.startTime = TimeProcessor.currentStep;
            this.startToGo = true;
            return true;
        }
    }

    public void finishTransport(){
        this.startToGo = false;
        amountOfProducts.clear();
    }
}
