package animals;

import levelController.objects.Board;

import java.util.Random;

public class Animal {
    private int x;
    private int y;
    private int speed;

    public int getX() { return x; }

    public int getY() { return y; }

    protected Animal(int speed) {
        Random random = new Random();
        this.x = random.nextInt(Board.COLUMN.getLength()) + 1;
        this.y = random.nextInt(Board.ROW.getLength()) + 1;
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
                    this.y -= this.speed;
                    break;
                case DOWN:
                    this.y += this.speed;
                    break;
                case RIGHT:
                    this.x += this.speed;
                    break;
                case LEFT:
                    this.x -= this.speed;
                    break;
            }
            if(this.x > boardColumn || this.x < 1 || this.y > boardRow || this.y < 1)
                correct = false;
            else
                correct = true;
            if (this.x > boardColumn)
                this.x -= this.speed;
            else if (this.x < 1)
                this.x += this.speed;
            else if (this.y > boardColumn)
                this.y -= this.speed;
            else if (this.y < 1)
                this.y += this.speed;
        }
    }

    protected void hungryWalk(int grassX, int grassY) {
        double min = Math.sqrt(Math.pow((this.getX() - grassX),2) + Math.pow((this.getY() - grassY),2));
        double distance;

        if(min == 0)
            return;

        this.x += this.speed;
        distance = Math.sqrt(Math.pow((this.getX() - grassX),2) + Math.pow((this.getY() - grassY),2));
        if(distance < min)
            return;

        this.x -= this.speed;
        this.x -= this.speed;
        distance = Math.sqrt(Math.pow((this.getX() - grassX),2) + Math.pow((this.getY() - grassY),2));
        if (distance < min)
            return;

        this.x += this.speed;
        this.y += this.speed;
        distance = Math.sqrt(Math.pow((this.getX() - grassX),2) + Math.pow((this.getY() - grassY),2));
        if (distance < min)
            return;

        this.y -= this.speed;
        this.y -= this.speed;
        distance = Math.sqrt(Math.pow((this.getX() - grassX),2) + Math.pow((this.getY() - grassY),2));
        if (distance < min)
            return;
    }

    protected void findWalk(int productX, int productY) {
        double min = Math.sqrt(Math.pow((this.getX() - productX),2) + Math.pow((this.getY() - productY),2));
        double distance;

        if(min == 0)
            return;

        this.x += this.speed;
        distance = Math.sqrt(Math.pow((this.getX() - productX),2) + Math.pow((this.getY() - productY),2));
        if(distance < min)
            return;

        this.x -= this.speed;
        this.x -= this.speed;
        distance = Math.sqrt(Math.pow((this.getX() - productX),2) + Math.pow((this.getY() - productY),2));
        if (distance < min)
            return;

        this.x += this.speed;
        this.y += this.speed;
        distance = Math.sqrt(Math.pow((this.getX() - productX),2) + Math.pow((this.getY() - productY),2));
        if (distance < min)
            return;

        this.y -= this.speed;
        this.y -= this.speed;
        distance = Math.sqrt(Math.pow((this.getX() - productX),2) + Math.pow((this.getY() - productY),2));
        if (distance < min)
            return;
    }
}
