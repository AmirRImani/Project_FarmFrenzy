package workshops;

import products.Products;

public enum Workshops {
    MILL(150, 4, Products.EGG, Products.FLOOR),
    WEAVING(250, 5, Products.FEATHER, Products.FABRIC),
    MILK_PACKING(400, 6, Products.MILK, Products.POCKET_MILK),
    BAKERY(250, 5, Products.FLOOR, Products.BREAD),
    SEWING(400, 6, Products.FABRIC, Products.SHIRT),
    ICE_CREAM_SHOP(550, 7, Products.POCKET_MILK, Products.ICE_CREAM);

    private int costToBuild;
    private int timeToProduce;
    private Products neededProduct;
    private Products producedProduct;

    Workshops(int costToBuild, int timeToProduce, Products neededProduct, Products producedProduct){
       this.costToBuild = costToBuild;
       this.timeToProduce = timeToProduce;
       this.neededProduct = neededProduct;
       this.producedProduct = producedProduct;
    }

    public int getCost() { return costToBuild; }

    public int getTime() { return timeToProduce; }

    public Products getNeededProduct() { return neededProduct; }

    public Products getProducedProduct() { return producedProduct; }
}
