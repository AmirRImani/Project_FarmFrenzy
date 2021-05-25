package animals;

public enum Values {
    HEN(100),
    TURKEY(200),
    BUFFALO(400),
    DOG(100),
    CAT(150);

    private int value;

    Values(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
