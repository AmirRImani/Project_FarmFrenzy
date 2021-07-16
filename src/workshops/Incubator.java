package workshops;

import animals.domestics.Domestics;
import levelController.objects.Warehouse;
import products.Products;
import sharedClasses.TimeProcessor;

public class Incubator {
    private final int INITIAL_TIME_PRODUCT = 5;
    private int currentLevel = 1;
    private int costToBuild = 400;
    private int timeToProduce;
    private Products neededProduct = Products.EGG;
    private Domestics producedProduct = Domestics.HEN;
    private int amountProduct;
    private int startTime = 0;//TODO start to produce
    private int costToUpgrade = 450;
    private boolean busy = false;

    public boolean isBusy() { return busy; }

    public int getCost() { return costToBuild; }

    public int getCostToUpgrade() { return costToUpgrade * currentLevel; }

    public int getAmountPro() { return amountProduct; }

    public boolean work(Warehouse warehouse){
        if(busy){
            return false;
        } else{
            //TODO factory level2 will be added
            int availableAmount = warehouse.amount(this.neededProduct);
            if(availableAmount <= 0) {
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
}
