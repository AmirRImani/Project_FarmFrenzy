package controller;

public class Grass {
    private int row;
    private int column;

    public Grass(int row, int column) {
        this.row = row;
        this.column = column;
    }

    protected int getRow() { return row; }

    protected int getColumn() { return column; }


}
