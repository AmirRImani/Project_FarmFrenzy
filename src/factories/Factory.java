package factories;

import products.Product;

import java.util.ArrayList;

public abstract class Factory {
    protected int levelUpPrice;
    protected int highestLevel;
    protected int currentLevel;
    protected ArrayList<Product> neededProducts = new ArrayList<>();
    protected ArrayList<Product> producedProducts = new ArrayList<>();

    public abstract void produce();
}
