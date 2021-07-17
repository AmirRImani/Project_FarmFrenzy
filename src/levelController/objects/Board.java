package levelController.objects;

public enum Board {
    ROW(10),
    COLUMN(10);

    private int length;

    Board(int length){
        this.length = length;
    }

    public int getLength(){
        return length;
    }
}
