package controller;

public class Grass {
    private int column;
    private int row;

    public Grass(int column, int row) {
        this.column = column;
        this.row = row;
    }

    protected int getColumn() { return column; }

    protected int getRow() { return row; }


}
