package vehicles;

import controller.Warehouse;
import products.Products;
import sharedClasses.TimeProcessor;

import java.util.HashMap;
import java.util.Set;


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

    public int finishTransport(){
        if(TimeProcessor.currentStep >= startTime + TIME_OF_TRAVEL) {
            this.startToGo = false;
            amountOfProducts.clear();
            return price();
        } else
            return 0;
    }

    private int price() {
        int sum = 0;
        int quantity;
        Set<Products> products = amountOfProducts.keySet();
        Products[] products1 = products.toArray(new Products[0]);
        for (int i = 0; i < amountOfProducts.size(); i++) {
            quantity = amountOfProducts.get(products1[i]);
            sum += quantity;
        }
        return sum;
    }
}
