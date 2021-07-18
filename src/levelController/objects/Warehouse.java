package levelController.objects;

import products.Products;

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

    public HashMap<Products, Integer> getAmountOfProduct() { return amountOfProduct; }

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


    public boolean truckLoad(Products product, Truck truck) {
        int amount = truck.getCapacity() / product.getSpace();
        int availableAmount = amountOfProduct.get(product);
        int quantity = Math.min(amount, availableAmount);
        if(truck.onRoad()) {
            //System.out.println("Truck is on road");
            return false;
        } else if(amount == 0) {
            //System.out.println("Truck doesn't have enough space");
            return false;
        } else {
            truck.load(product, quantity);
            amountOfProduct.replace(product, amountOfProduct.get(product) - quantity);
            remainedSpace += quantity * product.getSpace();
            //System.out.println("Truck loaded " + quantity + " " + product.name());
            return true;
        }
    }

    public int amount(Products product){
        if(amountOfProduct.containsKey(product))
            return amountOfProduct.get(product);
        else
            return 0;
    }

    public void decreaseAmount(Products product, int amount){
        amountOfProduct.replace(product, amountOfProduct.get(product) - amount);
        remainedSpace += amount * product.getSpace();
    }

}
