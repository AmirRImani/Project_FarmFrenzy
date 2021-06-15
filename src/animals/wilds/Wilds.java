package animals.wilds;

public enum Wilds {
    LION(1, 3),
    BEAR(1, 3),
    TIGER(2, 4);

    private int speed;
    private int tapNeeded;

    Wilds(int speed, int tapNeeded){
        this.speed = speed;
        this.tapNeeded = tapNeeded;
    }

    public int getSpeed(){ return speed; }

    public int getTapNeeded() { return tapNeeded; }
}
