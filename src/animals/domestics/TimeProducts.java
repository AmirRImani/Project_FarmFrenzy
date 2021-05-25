package animals.domestics;

public enum TimeProducts {
    HEN(2),
    TURKEY(3),
    BUFFALO(5);


    private int time;

    TimeProducts(int time){
        this.time = time;
    }

    public int getTime(){
        return time;
    }
}
