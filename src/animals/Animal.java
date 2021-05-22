package animals;

import java.util.Random;

public class Animal {
    protected int row;
    protected int column;
    protected int speed;

    protected char randomWalk(){
        Random rand = new Random();
        int random = rand.nextInt(4);
        switch (random){
            case 0:
                return 'U';
            case 1:
                return 'D';
            case 2:
                return 'R';
            case 3:
                return 'L';
        }
        return 0;
    }

    protected void walk(Animal animal, char direction){
        switch (direction){
            case 'U':
                animal.column -= animal.speed;
                break;
            case 'D':
                animal.column += animal.speed;
                break;
            case 'R':
                animal.row += animal.speed;
                break;
            case 'L':
                animal.row -= animal.speed;
                break;
        }
    }
}
