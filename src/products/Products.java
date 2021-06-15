package products;

public enum Products {
    EGG(1,4,15),
    FEATHER(1,4,20),
    MILK(1,4,25),
    FLOOR(2,5,40),
    FABRIC(2,5,50),
    POCKET_MILK(2,5,60),
    BREAD(4,6,80),
    SHIRT(4,6,100),
    ICE_CREAM(4,6,120),
    CAUGHT_LION(15,5,300),
    CAUGHT_BEAR(15,5,400),
    CAUGHT_TIGER(15,5,500);

    private int space;
    private int disappearTime;
    private int price;

    Products(int space, int disappearTime, int price){
        this.space = space;
        this.disappearTime = disappearTime;
        this.price = price;
    }

    public int getSpace(){ return space; }

    public int getDisappear() { return disappearTime; }

    public int getPrice() { return price; }
}
