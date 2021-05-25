package animals;

public enum Speed {
    LION(1),
    BEAR(1),
    TIGER(2),
    DOG(1),
    CAT(1),
    HEN(1),
    TURKEY(1),
    BUFFALO(1);

    private int speed;

    Speed(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return speed;
    }
}
