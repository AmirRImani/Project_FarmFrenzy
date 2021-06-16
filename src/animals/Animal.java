package animals;

import controller.Board;

import java.util.Random;

public class Animal {
    private int column;
    private int row;
    private int speed;

    public int getX() { return column; }

    public int getY() { return row; }

    protected Animal(int speed) {
        Random random = new Random();
        this.column = random.nextInt(Board.COLUMN.getLength()) + 1;
        this.row = random.nextInt(Board.ROW.getLength()) + 1;
        this.speed = speed;
    }

    private Directions randomWalk(){
        Random rand = new Random();
        int random = rand.nextInt(4);
        switch (random){
            case 0:
                return Directions.UP;
            case 1:
                return Directions.DOWN;
            case 2:
                return Directions.RIGHT;
            case 3:
                return Directions.LEFT;
            default:
                return Directions.UP;
        }
    }

    public void walk(){
        Directions direction;
        int boardRow = Board.ROW.getLength();
        int boardColumn = Board.COLUMN.getLength();
        boolean correct = false;
        //TODO
        while(!correct) {
            direction = randomWalk();
            switch (direction) {
                case UP:
                    this.column -= this.speed;
                    break;
                case DOWN:
                    this.column += this.speed;
                    break;
                case RIGHT:
                    this.row += this.speed;
                    break;
                case LEFT:
                    this.row -= this.speed;
                    break;
            }
            if(this.column > boardColumn || this.column < 1 || this.row > boardRow || this.row < 1)
                correct = false;
            else
                correct = true;
            if (this.column > boardColumn)
                this.column -= this.speed;
            else if (this.column < 1)
                this.column += this.speed;
            else if (this.row > boardColumn)
                this.row -= this.speed;
            else if (this.row < 1)
                this.row += this.speed;
        }
    }
}
