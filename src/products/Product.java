package products;

public class Product {
    //TODO
    protected String nameOfProduct;
    protected int spaceNeeded;
    protected int disappearTime;
    protected int price;
    protected int startTime;//TODO time when it's been made

    public Product(Products product) {
        this.nameOfProduct = product.name();
        this.spaceNeeded = product.getSpace();
        this.disappearTime = product.getDisappear();
        this.price = product.getPrice();
    }


}
