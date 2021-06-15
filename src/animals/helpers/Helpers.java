package animals.helpers;

public enum Helpers {
    DOG(100,1),
    CAT(150,1);

    private int value;
    private int speed;

    Helpers(int value, int speed){
        this.value = value;
        this.speed = speed;
    }

    public int getValue() { return value; }

    public int getSpeed(){ return speed; }
}
