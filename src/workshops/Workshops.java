package workshops;

import products.Products;

public enum Workshops {
    MILL(150,200, 4, Products.EGG, Products.FLOOR),
    WEAVING(250,300, 5, Products.FEATHER, Products.FABRIC),
    MILK_PACKING(400,450, 6, Products.MILK, Products.POCKET_MILK),
    BAKERY(250,300, 5, Products.FLOOR, Products.BREAD),
    SEWING(400,450, 6, Products.FABRIC, Products.SHIRT),
    ICE_CREAM_SHOP(550,600, 7, Products.POCKET_MILK, Products.ICE_CREAM);

    private int costToBuild;
    private int costToUpgrade;;
    private int timeToProduce;
    private Products neededProduct;
    private Products producedProduct;
    private int level;

    Workshops(int costToBuild,int costToUpgrade, int timeToProduce, Products neededProduct, Products producedProduct){
       this.costToBuild = costToBuild;
       this.costToUpgrade=costToUpgrade;
       this.timeToProduce = timeToProduce;
       this.neededProduct = neededProduct;
       this.producedProduct = producedProduct;
       this.level=1;
    }

    public int getCost() { return costToBuild; }

    public int getCostToUpgrade() {
        return costToUpgrade;
    }

    public int getTime() { return timeToProduce; }

    public Products getNeededProduct() { return neededProduct; }

    public Products getProducedProduct() { return producedProduct; }

    public int getLevel() {
        return level;
    }
}
