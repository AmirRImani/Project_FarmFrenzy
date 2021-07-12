package levelController.objects;

public enum Board {
    ROW(6),
    COLUMN(6);

    private int length;

    Board(int length){
        this.length = length;
    }

    public int getLength(){
        return length;
    }
}
