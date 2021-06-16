package products;

import controller.Board;
import sharedClasses.TimeProcessor;

import java.util.Random;

public class Product {
    //TODO
    private String nameOfProduct;
    private int spaceNeeded;
    private int disappearTime;
    private int price;
    private int startTime;//TODO time when it's been made
    private int x;
    private int y;

    public String getNameOfProduct() { return nameOfProduct; }

    public int getSpaceNeeded() { return spaceNeeded; }

    public int getX() { return x; }

    public int getY() { return y; }

    public Product(Products product, int x, int y) {
        this.nameOfProduct = product.name();
        this.spaceNeeded = product.getSpace();
        this.disappearTime = product.getDisappear();
        this.price = product.getPrice();
        this.startTime = TimeProcessor.currentStep;
        this.x = x;
        this.y = y;
    }

    public Product(Products product) {
        Random random = new Random();
        this.nameOfProduct = product.name();
        this.spaceNeeded = product.getSpace();
        this.disappearTime = product.getDisappear();
        this.price = product.getPrice();
        this.startTime = TimeProcessor.currentStep;
        this.x = random.nextInt(Board.COLUMN.getLength()) + 1;
        this.y = random.nextInt(Board.ROW.getLength()) + 1;
    }


    public boolean spoil() {
        if(TimeProcessor.currentStep >= startTime + disappearTime)
            return true;
        return false;
    }
}
