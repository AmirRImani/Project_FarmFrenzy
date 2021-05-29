package animals.wilds;

public enum Wilds {
    LION(1),
    BEAR(1),
    TIGER(2);

    private int speed;

    Wilds(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return speed;
    }
}
