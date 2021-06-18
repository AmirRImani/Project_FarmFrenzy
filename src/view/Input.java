package view;

import animals.domestics.Domestics;
import animals.helpers.Helpers;
import input.User;
import products.Products;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {


    public boolean commandGetter(Scanner scanner, Game game, User user){
        boolean exit;
        String command;
        System.out.println("Please enter your command: ");
        command = scanner.nextLine();
        exit = commandRecognizer(command.toUpperCase().trim(), game, user);
        return exit;
    }

    protected boolean commandRecognizer(String command, Game game, User user){
        boolean exit = false;
        Pattern patternBuy = Pattern.compile("BUY (\\w+)");
        Matcher matcherBuy = patternBuy.matcher(command);
        Pattern patternPickup = Pattern.compile("PICKUP (\\d+) (\\d+)");
        Matcher matcherPickup = patternPickup.matcher(command);
        Pattern patternPlant = Pattern.compile("PLANT (\\d+) (\\d+)");
        Matcher matcherPlant = patternPlant.matcher(command);
        Pattern patternWork = Pattern.compile("WORK (\\w+)");
        Matcher matcherWork = patternWork.matcher(command);
        Pattern patternCage = Pattern.compile("CAGE (\\d+) (\\d+)");
        Matcher matcherCage = patternCage.matcher(command);
        Pattern patternTurn = Pattern.compile("TURN (\\d+)");
        Matcher matcherTurn = patternTurn.matcher(command);
        Pattern patternTruckLoad = Pattern.compile("TRUCK LOAD (\\w+)");
        Matcher matcherTruckLoad = patternTruckLoad.matcher(command);
        Pattern patternTruckUnload = Pattern.compile("TRUCK UNLOAD (\\w+)");
        Matcher matcherTruckUnload = patternTruckUnload.matcher(command);
        Pattern patternBuild = Pattern.compile("BUILD (\\w+)");
        Matcher matcherBuild = patternBuild.matcher(command);

        if(matcherBuy.find()) {
            for (Domestics domestic : Domestics.values()) {
                if (domestic.name().equals(matcherBuy.group(1))) {
                    game.buyDome(Domestics.valueOf(matcherBuy.group(1)));
                    return exit;
                }
            }
            for (Helpers helper : Helpers.values()) {
                if(helper.name().equals(matcherBuy.group(1))){
                    game.buyHelper(Helpers.valueOf(matcherBuy.group(1)));
                    return exit;
                }
            }
            System.out.println("Domestic name isn't correct");
        } else if(matcherPickup.find()) {
            game.pickup(Integer.parseInt(matcherPickup.group(1)), Integer.parseInt(matcherPickup.group(2)));
        } else if(matcherPlant.find()) {
            game.plant(Integer.parseInt(matcherPlant.group(1)), Integer.parseInt(matcherPlant.group(2)));
        } else if(matcherWork.find()){
            game.work(matcherWork.group(1));
        } else if(matcherCage.find()) {
            game.cage(Integer.parseInt(matcherCage.group(1)), Integer.parseInt(matcherCage.group(2)));
        } else if(matcherTurn.find()) {
            exit = game.turn(Integer.parseInt(matcherTurn.group(1)));
            if(exit)
                user.nextLevel();
        } else if(matcherTruckLoad.find()) {
            for (Products product : Products.values()) {
                if (product.name().equals(matcherTruckLoad.group(1))) {
                    game.truckLoad(matcherTruckLoad.group(1));
                    return exit;
                }
            }
            System.out.println("Product name isn't correct");
        } else if(matcherTruckUnload.find()) {
            for (Products product : Products.values()) {
                if (product.name().equals(matcherTruckUnload.group(1))) {
                    game.truckUnload(matcherTruckUnload.group(1));
                    return exit;
                }
            }
            System.out.println("Product name isn't correct");
        }else if(matcherBuild.find()) {
            game.build(matcherBuild.group(1));
        } else {
            switch (command) {
                case "WELL":
                    game.well();
                    break;
                case "TRUCK GO":
                    game.truckGo();
                    break;
                case "INQUIRY":
                    game.showDetails();
                    break;
                case "EXIT":
                    exit = true;
                    break;
                default:
                    System.out.println("Wrong command");
            }
        }
        return exit;
    }
}
