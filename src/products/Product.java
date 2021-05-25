package products;

public class Product {
    //TODO
    private String nameOfProduct;
    private int spaceNeeded;
    private int disappearTime;
    private int price;

    public Product(Products product) {
        this.nameOfProduct = product.name();
        this.spaceNeeded = product.getSpace();
        this.disappearTime = product.getDisappear();
        this.price = product.getPrice();
    }


}
