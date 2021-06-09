package controller;

import animals.domestics.Domestics;
import products.Products;

public class Task {
    Tasks type;
    Domestics typeOfDomestic;
    Products typeOfProduct;
    int target;

    public Task(Tasks type, int target) {
        this.type = type;
        this.target = target;
    }

    public Task(Tasks type, Domestics typeOfDomestic, int target) {
        this.type = type;
        this.typeOfDomestic = typeOfDomestic;
        this.target = target;
    }

    public Task(Tasks type, Products typeOdProduct, int target) {
        this.type = type;
        this.typeOfProduct = typeOdProduct;
        this.target = target;
    }
}
