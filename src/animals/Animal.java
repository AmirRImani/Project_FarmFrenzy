package animals;

import controller.Board;

import java.util.Random;

public class Animal {
    protected int row;
    protected int column;
    protected int speed;

    protected Animal(int speed) {
        Random random = new Random();
        this.row = random.nextInt(Board.ROW.getLength());
        this.column = random.nextInt(Board.COLUMN.getLength());
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

    protected void walk(){
        Directions direction;
        int boardRow = Board.ROW.getLength();
        int boardColumn = Board.COLUMN.getLength();
        //TODO
        while(this.column > boardColumn || this.column < 1 || this.row > boardRow || this.row < 1) {
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
