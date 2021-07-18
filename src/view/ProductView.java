package view;

import products.Products;

public class ProductView {
    private Products product;
    private String name;
    private int price;
    private int amount;

    @Override
    public String toString() {
        return "ProductView{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    public void setName(String name) { this.name = name; }

    public void setPrice(int price) { this.price = price; }

    public void setAmount(int amount) { this.amount = amount; }

    public Products getProduct() { return product; }

    public String getName() { return name; }

    public int getPrice() { return price; }

    public int getAmount() { return amount; }

    public ProductView(Products product, String name, int price, int amount) {
        this.product = product;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}
