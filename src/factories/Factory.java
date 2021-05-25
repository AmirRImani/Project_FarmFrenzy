package factories;

import products.Products;

public class Factory {
    //protected int levelUpPrice;
    //protected int currentLevel;
    protected String name;
    protected int costToBuild;
    protected int timeToProduce;
    protected Products neededProduct;
    protected Products producedProduct;

    public Factory(Factories factory) {
        this.name = factory.name();
        this.costToBuild = factory.getCost();
        this.timeToProduce = factory.getTime();
        this.neededProduct = factory.getNeededProduct();
        this.producedProduct = factory.getProducedProduct();
    }

    public void produce(){

    }

}
