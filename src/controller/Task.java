package controller;

import animals.domestics.Domestics;
import products.Products;

public class Task {
    String type;
    Domestics typeOfDomestic;
    Products typeOfProduct;
    int target;

    public String getType() { return type; }

    public Domestics getTypeOfDomestic() { return typeOfDomestic; }

    public Products getTypeOfProduct() { return typeOfProduct; }

    public int getTarget() { return target; }

    public Task(Tasks type, int target) { //COIN type
        this.type = type.name();
        this.target = target;
    }

    public Task(Tasks type, Domestics typeOfDomestic, int target) { //DOMESTIC type
        this.type = type.name();
        this.typeOfDomestic = typeOfDomestic;
        this.target = target;
    }

    public Task(Tasks type, Products typeOdProduct, int target) { //PRODUCT type
        this.type = type.name();
        this.typeOfProduct = typeOdProduct;
        this.target = target;
    }
}
