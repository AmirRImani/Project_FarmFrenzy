package levels;

import animals.domestics.Domestics;
import animals.wilds.Wilds;
import products.Products;
import sharedClasses.FileOperator;
import tasks.Task;
import tasks.Tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LevelGenerator {
    public static void main(String[] args) {
        FileOperator fileOperator = new FileOperator("levels.json");

        HashMap<Wilds,int[]> timeWilds = new HashMap<>();
        HashSet<Task> tasks = new HashSet<>();
        ArrayList<Level> levels = new ArrayList<>();
        int[] times1 = {30,50};
        int[] times2 = {20,20};
        int[] times3 = {30};
        int[] times4 = {20,30,50};
        int[] times5 = {10,20,20};
        timeWilds.put(Wilds.BEAR,times1);
        timeWilds.put(Wilds.TIGER,times2);
        tasks.add(new Task(Tasks.DOMESTIC, Domestics.BUFFALO, 1));
        tasks.add(new Task(Tasks.COIN,1000));
        levels.add(new Level(1,180,150,400, timeWilds, tasks));
        HashSet<Task> tasks2 = new HashSet<>();
        HashMap<Wilds,int[]> timeWilds2 = new HashMap<>();
        timeWilds2.put(Wilds.LION,times3);
        timeWilds2.put(Wilds.BEAR,times4);
        timeWilds2.put(Wilds.TIGER,times5);
        tasks2.add(new Task(Tasks.CATCH, Products.FABRIC,20));
        tasks2.add(new Task(Tasks.COIN,1500));
        tasks2.add(new Task(Tasks.DOMESTIC, Domestics.BUFFALO, 5));
        levels.add(new Level(2,200,200,500, timeWilds2, tasks2));
        try {
            fileOperator.saveFile(fileOperator.getFile(),levels);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
