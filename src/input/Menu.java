package input;

import controller.LevelsOperation;
import sharedClasses.FileOperator;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Menu {
    public static Logger logger = Logger.getLogger("FarmFenzy");


 FileHandler handler=null;
    {
        SimpleDateFormat format = new SimpleDateFormat("d-M_HH mm ss");
        String path = "resource\\log " + format.format(Calendar.getInstance().getTime()) + ".txt";
        try {
            handler = new FileHandler(path);

            handler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    SimpleDateFormat logTime = new SimpleDateFormat(", dd/LLL/yyyy - HH:mm:ss");
                    Calendar cal = new GregorianCalendar();
                    cal.setTimeInMillis(record.getMillis());

                    return "["+record.getLevel()+"]"
                            + logTime.format(cal.getTime())
                            + ", "
                            + record.getMessage() + "\n";
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
       logger.addHandler(handler);
        logger.setLevel(Level.ALL);

    }
    class MyLevel extends Level {
        public final Level DISASTER = new MyLevel("DISASTER", Level.SEVERE.intValue() + 1);

        public MyLevel(String name, int value) {
            super(name, value);
        }
    }
    private HashSet<User> users;

    public Menu() {

        FileOperator fileOperator = new FileOperator("users.json");
        HashSet<String> hashSet = fileOperator.loadFile(fileOperator.getFile());
        if(!hashSet.isEmpty()) users = typeChanger(hashSet);
    }

    private HashSet<User> typeChanger(HashSet<String> hashSet) {
        HashSet<User> users = new HashSet<>();

        Pattern pattern = Pattern.compile("(\\{[^\\}]+\\})");
        Matcher matcher;
        Pattern patternName = Pattern.compile("userName=([^,]+)");
        Matcher matcherName;
        Pattern patternPassword = Pattern.compile("password=([^,]+)");
        Matcher matcherPassword;
        Pattern patternCoins = Pattern.compile("numberOfCoins=([^,|.]+)");
        Matcher matcherCoins;
        Pattern patternUnlocked = Pattern.compile("unlockedLevels=([^,|.]+)");
        Matcher matcherUnlocked;

        String[] split = hashSet.toString().split("},");

        for (String s : split) {
            matcher = pattern.matcher(s+"}");
            matcher.find();
            for (int i = 0; i < matcher.groupCount(); i++) {
                matcherName = patternName.matcher(matcher.group(i));
                matcherPassword = patternPassword.matcher(matcher.group(i));
                matcherCoins = patternCoins.matcher(matcher.group(i));
                matcherUnlocked = patternUnlocked.matcher(matcher.group(i));

                matcherName.find();
                matcherPassword.find();
                matcherCoins.find();
                matcherUnlocked.find();

                users.add(new User(matcherName.group(1),
                        matcherPassword.group(1),
                        Integer.parseInt(matcherCoins.group(1)),
                        Integer.parseInt(matcherUnlocked.group(1))));
            }
        }
        return users;
    }

    private void save(){
        FileOperator fileOperator = new FileOperator("users.json");
        try {
            fileOperator.saveFile(fileOperator.getFile(),users, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void input(Scanner scanner){

        //logger.addHandler(handler);
        String command;
        User user;
        boolean rightCommand = false;
        while (!rightCommand) {
            System.out.println("LOG IN or SIGNUP or EXIT: ");
            command = scanner.nextLine();

            if (command.toUpperCase().trim().equals("LOG IN")) {
                logger.setUseParentHandlers(false);
                logger.info("A user chose 'LOG IN'!");
                user = login(scanner);
                if(user != null) {
                    userMenu(scanner, user);
                    rightCommand = true;
                }

            } else if (command.toUpperCase().trim().equals("SIGNUP")) {
                logger.setUseParentHandlers(false);
                logger.info("A user chose 'SIGNUP'!");
                user = signUp(scanner);
                if(user != null) {
                    userMenu(scanner, user);
                    rightCommand = true;

                }
            }else if(command.toUpperCase().trim().equals("EXIT"))
            {
                logger.setUseParentHandlers(false);
            logger.info("A user chose 'EXIT'!");
                rightCommand = exit(scanner);}
            else{
                System.out.println("\nWrong command");
                logger.setUseParentHandlers(false);
            logger.info("Wrong command");}
            if (!rightCommand)
                System.out.println();
        }
    }

    private User login(Scanner scanner) {
        System.out.println("\nPlease enter your userName:");
        String name = scanner.nextLine();
        User user = getUser(name);
        logger.setUseParentHandlers(false);
        logger.info("A user entered his/her userName.");
        if(user != null) {
            System.out.println("\nPlease enter your password");
            String password = scanner.nextLine();
            logger.setUseParentHandlers(false);
            logger.info("A user entered his/her userName.");
            if(user.getPassword().equals(password)) {
                return user;
            }
            else {
                System.out.println("Password is wrong");
                logger.setUseParentHandlers(false);
                logger.info("Password is wrong!");
                return null;
            }
        } else {
            System.out.println("This user name doesn't exist");
            logger.setUseParentHandlers(false);
            logger.info("This user name doesn't exist!");
            return null;
        }

    }

    private User signUp(Scanner scanner){
        System.out.println("\nPlease enter your userName:");
        String name = scanner.nextLine();
        logger.setUseParentHandlers(false);
        logger.info("This user enterd his/her userName!");
        if(!userExist(name)) {
            System.out.println("\nPlease enter your password");
            String password = scanner.nextLine();
            logger.setUseParentHandlers(false);
            logger.info("This user enterd his/her password!");
            User user = new User(name, password);
            users.add(user);
            save();
            return user;
        } else {
            System.out.println("\nThis user name already exists");
            logger.setUseParentHandlers(false);
            logger.info("This user name already exists");
            return null;
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
        System.out.println("\nHello " + user.getUserName());
        String command;
        Pattern pattern = Pattern.compile("START (\\d+)");
        Matcher matcher;
        boolean rightCommand = false;
        while (!rightCommand) {
            System.out.println("START[level] or LOG OUT or SETTINGS or EXIT: ");
            command = scanner.nextLine();
            matcher = pattern.matcher(command.toUpperCase().trim());
            if (matcher.find()) {
                LevelsOperation levelsOperation = LevelsOperation.getInstance();
                rightCommand = levelsOperation.getLevel(Integer.parseInt(matcher.group(1)), user, scanner);
            } else if (command.toUpperCase().equals("LOG OUT")) {
                System.out.println("\nLogout " + user.getUserName() + "\n");
                logger.setUseParentHandlers(false);
                logger.info("This user loggerd out!");
                input(scanner);
            } else if(command.toUpperCase().equals("SETTINGS")){
                logger.setUseParentHandlers(false);
                logger.info("This user chose 'SETTINGS' . ");
                try {
                    new Option();
                } catch (IOException e) {

                }
                //TODO
            } else if(command.toUpperCase().equals("EXIT"))
            {
                logger.setUseParentHandlers(false);
            logger.info("This user chose 'EXIT' . ");
                exit(scanner);}
            else
            {
                System.out.println("\nWrong command");
                logger.setUseParentHandlers(false);
                logger.info("Wrong command!");
            }
            if(!rightCommand)
                System.out.println();
        }
    }
    private boolean exit(Scanner scanner){
        System.out.println("Are you sure you want to exit? YES or NO");
        String command = scanner.nextLine();
        switch (command.toUpperCase().trim()){
            case "YES":
                logger.setUseParentHandlers(false);
                logger.info("This user quited! ");
                System.exit(1);
                return true;
            case "NO":
                return false;
            default:
                System.out.println("\nWrong command");
                return false;
        }

    }
}
