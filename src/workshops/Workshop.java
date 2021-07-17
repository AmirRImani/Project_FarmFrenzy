package workshops;

import levelController.objects.Warehouse;
import products.Products;
import sharedClasses.TimeProcessor;

public class Workshop {
    private final int INITIAL_TIME_PRODUCT;
    private int currentLevel;
    private String name;
    private int costToBuild;
    private int timeToProduce;
    private Products neededProduct;
    private Products producedProduct;
    private int amountProduct;
    private int startTime;//TODO start to produce
    private int costToUpgrade;
    private boolean busy;

    public String getName() { return name; }

    public Products getProducedProduct() { return producedProduct; }

    public boolean isBusy() { return busy; }

    public int getCostToUpgrade() { return costToUpgrade * currentLevel; }

    public int getAmountPro() { return amountProduct; }

    public Workshop(Workshops workshop) {
        this.currentLevel = 1;
        this.name = workshop.name();
        this.costToBuild = workshop.getCost();
        this.INITIAL_TIME_PRODUCT = workshop.getTime();
        this.neededProduct = workshop.getNeededProduct();
        this.producedProduct = workshop.getProducedProduct();
        this.costToUpgrade = workshop.getCostToUpgrade();
        this.startTime = 0;
        this.busy = false;
    }

    public boolean work(Warehouse warehouse){
        if(busy){
            //System.out.println("Workshop is busy right now");
            return false;
        } else{
            //TODO factory level2 will be added
            int availableAmount = warehouse.amount(this.neededProduct);
            if(availableAmount <= 0) {
                //System.out.println("Not enough ingredients");
                return false;
            } else if(availableAmount >= this.currentLevel){
                //TODO
                warehouse.decreaseAmount(this.neededProduct, currentLevel);
                startTime = TimeProcessor.getInstance().currentStep;
                busy = true;
                amountProduct = currentLevel;
                timeToProduce = INITIAL_TIME_PRODUCT;
                return true;
            } else {
                //TODO
                warehouse.decreaseAmount(this.neededProduct, availableAmount);
                startTime = TimeProcessor.getInstance().currentStep;
                busy = true;
                amountProduct = availableAmount;
                timeToProduce = (int) Math.ceil(INITIAL_TIME_PRODUCT * amountProduct / (double) currentLevel);
                return true;
            }
        }
    }

    public boolean isProduced() {
        if(TimeProcessor.getInstance().currentStep >= startTime + timeToProduce){
            busy = false;
            return true;
        }
        return false;
    }

    public void increaseLevel() {
        currentLevel ++;
    }

    public boolean maxLevel() {
        if(currentLevel >= 2)
            return true;
        return false;
    }

    public int progress() {
       return (TimeProcessor.getInstance().currentStep - startTime)/timeToProduce;
    }
}
