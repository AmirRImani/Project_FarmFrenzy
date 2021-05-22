package input;

import controller.LevelsOperation;

import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    private HashSet<User> users;

    public void input(Scanner scanner){
        String command;
        boolean rightCommand = false;
        while (!rightCommand) {
            System.out.println("LOG IN or SIGNUP: ");
            command = scanner.nextLine();

            if (command.toUpperCase().equals("LOG IN"))
                rightCommand = login(scanner);
            else if (command.toUpperCase().equals("SIGNUP"))
                rightCommand = signUp(scanner);
            else
                System.out.println("\nWrong command");
            if (!rightCommand)
                System.out.println();
        }
    }

    private boolean login(Scanner scanner) {
        System.out.println("\nPlease enter your userName:");
        String name = scanner.nextLine();
        User user = getUser(name);
        if(user != null) {
            System.out.println("\nPlease enter your password");
            String password = scanner.next();
            if(user.getPassword().equals(password)) {
                System.out.println("\nHello " + user.getUserName());
                userMenu(scanner, user);
                return true;
            }
            else {
                System.out.println("Password is wrong");
                return false;
            }
        } else {
            System.out.println("This user name doesn't exist");
            return false;
        }

    }

    private boolean signUp(Scanner scanner){
        System.out.println("\nPlease enter your userName:");
        String name = scanner.nextLine();
        if(!userExist(name)) {
            System.out.println("\nPlease enter your password");
            String password = scanner.next();
            System.out.println("\nHello " + name);
            User user = new User(name, password);
            users.add(user);
            userMenu(scanner, user);
            return true;
        } else {
            System.out.println("\nThis user name already exists");
            return false;
        }
    }

    private boolean userExist(String name) {
        if(users == null)
            users = new HashSet<User>();
        for (User user : users) {
            if (user.getUserName().equals(name))
                return true;
        }
        return false;
    }

    private User getUser(String name) {
        if(users == null)
            users = new HashSet<User>();
        for (User user : users) {
            if (user.getUserName().equals(name))
                return user;
        }
        return null;
    }



    private void userMenu(Scanner scanner, User user){
        String command;
        Pattern pattern = Pattern.compile("START (\\d+)");
        Matcher matcher;
        boolean rightCommand = false;
        scanner.nextLine();
        while (!rightCommand) {
            System.out.println("START[level] or LOG OUT or SETTINGS: ");
            command = scanner.nextLine();
            matcher = pattern.matcher(command.toUpperCase());
            if (matcher.find()) {
                LevelsOperation levelsOperation = new LevelsOperation();
                rightCommand = levelsOperation.getLevel(Integer.parseInt(matcher.group(1)), user);
            } else if (command.toUpperCase().equals("LOG OUT")) {
                System.out.println("\nLogout " + user.getUserName() + "\n");
                input(scanner);
            } else if(command.toUpperCase().equals("SETTINGS")){
                new Option();
                //TODO
            } else
                System.out.println("\nWrong command");
            if(!rightCommand)
                System.out.println();
        }
    }
    private void exit(){
        //TODO  exit in commands
        System.exit(1);
    }
}
