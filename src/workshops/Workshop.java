package workshops;

import controller.Warehouse;
import products.Products;
import sharedClasses.TimeProcessor;

public class Workshop {
    //protected int levelUpPrice;
    //protected int currentLevel;
    private String name;
    private int costToBuild;
    private int timeToProduce;
    private Products neededProduct;
    private Products producedProduct;
    private int startTime;//TODO start to produce
    private boolean busy;

    public String getName() { return name; }

    public Products getProducedProduct() { return producedProduct; }

    public boolean isBusy() { return busy; }

    public Workshop(Workshops workshop) {
        this.name = workshop.name();
        this.costToBuild = workshop.getCost();
        this.timeToProduce = workshop.getTime();
        this.neededProduct = workshop.getNeededProduct();
        this.producedProduct = workshop.getProducedProduct();
        this.startTime = 0;
        this.busy = false;
    }

    public boolean work(Warehouse warehouse){
        if(busy){
            System.out.println("Workshop is busy right now");
            return false;
        } else{
            //TODO factory level2 will be added
            if(warehouse.enoughAmount(this.neededProduct, 1)) {
                startTime = TimeProcessor.getInstance().currentStep;
                busy = true;
                return true;
            } else {
                System.out.println("Not enough ingredients");
                return false;
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
}
