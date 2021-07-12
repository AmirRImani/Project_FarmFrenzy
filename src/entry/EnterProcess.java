package entry;

import sharedClasses.FileOperator;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;


public class EnterProcess {
    public static Logger logger = Logger.getLogger("FarmFrenzy");
    private HashSet<User> users;


 FileHandler handler=null;
    {
        SimpleDateFormat format = new SimpleDateFormat("d-M_HH mm ss");
        String path = "resources\\log " + format.format(Calendar.getInstance().getTime()) + ".txt";
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


    public EnterProcess() {

        FileOperator fileOperator = new FileOperator("users.json");
        HashSet<String> hashSet = fileOperator.loadFile(fileOperator.getFile());
        if(!hashSet.isEmpty())
            users = typeChanger(hashSet);
        else
            users = new HashSet<>();
    }

    private HashSet<User> typeChanger(HashSet<String> hashSet) {
        HashSet<User> users = new HashSet<>();

        Pattern pattern = Pattern.compile("(\\{[^\\}]+\\})");
        Matcher matcher;
        Pattern patternName = Pattern.compile("userName=([^,]+)");
        Matcher matcherName;
        Pattern patternPassword = Pattern.compile("password=([^,]+)");
        Matcher matcherPassword;
        Pattern patternEmail = Pattern.compile("email=([^,]+)");
        Matcher matcherEmail;
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
                matcherEmail = patternEmail.matcher(matcher.group(i));
                matcherCoins = patternCoins.matcher(matcher.group(i));
                matcherUnlocked = patternUnlocked.matcher(matcher.group(i));

                matcherName.find();
                matcherPassword.find();
                matcherEmail.find();
                matcherCoins.find();
                matcherUnlocked.find();

                users.add(new User(matcherName.group(1), matcherPassword.group(1), matcherEmail.group(1),
                        Integer.parseInt(matcherCoins.group(1)), Integer.parseInt(matcherUnlocked.group(1))));
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

//    public void input(Scanner scanner){
//        String command;
//        User user;
//        boolean rightCommand = false;
//        while (!rightCommand) {
//            System.out.println("LOG IN or SIGNUP or EXIT: ");
//            command = scanner.nextLine();
//            logger.setUseParentHandlers(false);
//            logger.info("This file has made.");
//            if (command.toUpperCase().trim().equals("LOG IN")) {
//                logger.setUseParentHandlers(false);
//                logger.info("A user chose 'LOG IN'!");
//                user = login(scanner);
//                if(user != null) {
//                    userMenu(scanner, user);
//                    rightCommand = true;
//                }
//
//            } else if (command.toUpperCase().trim().equals("SIGNUP")) {
//                logger.setUseParentHandlers(false);
//                logger.info("A user chose 'SIGNUP'!");
//                user = signUp(scanner);
//                if(user != null) {
//                    userMenu(scanner, user);
//                    rightCommand = true;
//
//                }
//            }else if(command.toUpperCase().trim().equals("EXIT"))
//            {
//                logger.setUseParentHandlers(false);
//            logger.info("A user chose 'EXIT'!");
//                rightCommand = exit(scanner);}
//            else{
//                System.out.println("\nWrong command");
//                logger.setUseParentHandlers(false);
//            logger.info("Wrong command");}
//            if (!rightCommand)
//                System.out.println();
//        }
//    }

    public User login(String username, String password) {
        if (!userExistence(username, password)) {
            return null;
        } else
            return getUser(username);
    }

    private boolean userExistence(String username, String password) {
        for (User user : users) {
            if(user.getName().equals(username) && user.getPassword().equals(password))
                return true;
        }
        return false;
    }

    public User signup(String username, String password, String email){
        if(!userExist(username))
            return new User(username, password, email);
        return null;
    }

    private boolean userExist(String name) {
        if(users == null)
            users = new HashSet<User>();
        for (User user : users) {
            if (user.getName().equals(name))
                return true;
        }
        return false;
    }

    private User getUser(String name) {
        if(users == null)
            users = new HashSet<User>();
        for (User user : users) {
            if (user.getName().equals(name))
                return user;
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
        save();
    }

    public boolean validity(String username, String email) {
        if(userExist(username)){
            if(getUser(username).getEmail().equals(email))
                return true;
        }
        return false;
    }

    public void newPassword(String username, String password) {
        getUser(username).setPassword(password);
        save();
    }


//    private void userMenu(Scanner scanner, User user){
//        System.out.println("\nHello " + user.getName());
//        String command;
//        Pattern pattern = Pattern.compile("START (\\d+)");
//        Matcher matcher;
//        boolean rightCommand = false;
//        while (!rightCommand) {
//            System.out.println("START[level] or LOG OUT or SETTINGS or EXIT: ");
//            command = scanner.nextLine();
//            matcher = pattern.matcher(command.toUpperCase().trim());
//            if (matcher.find()) {
//                LevelsOperation levelsOperation = LevelsOperation.getInstance();
//                rightCommand = levelsOperation.getLevel(Integer.parseInt(matcher.group(1)), user, scanner);
//            } else if (command.toUpperCase().equals("LOG OUT")) {
//                System.out.println("\nLogout " + user.getName() + "\n");
//                logger.setUseParentHandlers(false);
//                logger.info("This user logged out!");
//                input(scanner);
//            } else if(command.toUpperCase().equals("SETTINGS")){
//                logger.setUseParentHandlers(false);
//                logger.info("This user chose 'SETTINGS' . ");
//                try {
//                    new Option();
//                } catch (IOException e) {
//
//                }
//                //TODO
//            } else if(command.toUpperCase().equals("EXIT"))
//            {
//                logger.setUseParentHandlers(false);
//            logger.info("This user chose 'EXIT' . ");
//                exit(scanner);}
//            else
//            {
//                System.out.println("\nWrong command");
//                logger.setUseParentHandlers(false);
//                logger.info("Wrong command!");
//            }
//            if(!rightCommand)
//                System.out.println();
//        }
//    }
//    private boolean exit(Scanner scanner){
//        System.out.println("Are you sure you want to exit? YES or NO");
//        String command = scanner.nextLine();
//        switch (command.toUpperCase().trim()){
//            case "YES":
//                logger.setUseParentHandlers(false);
//                logger.info("This user quited! ");
//                System.exit(1);
//                return true;
//            case "NO":
//                return false;
//            default:
//                System.out.println("\nWrong command");
//                return false;
//        }
//
//    }
}
