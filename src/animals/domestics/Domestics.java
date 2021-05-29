package animals.domestics;

import products.Products;

public enum Domestics {
    HEN(1, products.Products.EGG,2,1),
    TURKEY(1, products.Products.FEATHER,3,1),
    BUFFALO(1, products.Products.MILK,5,1);

    private int value;
    private Products product;
    private int timeToProduct;
    private int speed;

    Domestics(int value, products.Products product, int timeToProduct, int speed){
        this.value = value;
        this.product = product;
        this.timeToProduct = timeToProduct;
        this.speed = speed;
    }

    public int getValue() { return value; }

    public products.Products getProduct() { return product; }

    public int getTimeToProduct() { return timeToProduct; }

    public int getSpeed(){
        return speed;
    }
}