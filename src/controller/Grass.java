package controller;

import animals.domestics.Domestic;

import java.util.HashSet;

public class Grass {
    private HashSet<Domestic> needEatDome;
    private int column;
    private int row;

    public Grass(int column, int row) {
        this.needEatDome = new HashSet<>();
        this.column = column;
        this.row = row;
    }

    public int getColumn() { return column; }

    public int getRow() { return row; }


    public void addDome(Domestic domestic) {
        needEatDome.add(domestic);
    }

    public Domestic moreImportant() {
        int min = 100;
        Domestic dome = null;
        for (Domestic domestic : needEatDome) {
            if(domestic.getHealth() < min) {
                dome = domestic;
                min = domestic.getHealth();
            }
        }
        return dome;
    }
}
