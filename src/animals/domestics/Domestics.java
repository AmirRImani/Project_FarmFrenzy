package animals.domestics;

import products.Products;

public enum Domestics {
    HEN(100, products.Products.EGG,2,1),
    TURKEY(200, products.Products.FEATHER,3,1),
    BUFFALO(400, products.Products.MILK,5,1);

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

    public int getTimeToProduce() { return timeToProduct; }

    public int getSpeed(){
        return speed;
    }
}