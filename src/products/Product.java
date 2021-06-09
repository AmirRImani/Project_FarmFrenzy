package products;

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
        this.x = x;
        this.y = y;
    }


}
