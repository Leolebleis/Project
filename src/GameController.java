import java.util.List;
import java.util.Scanner;

public class GameController {

    // We set the lists public as they are constantly used throughout the program
    public static List playerList; // List containing player objects for each line in users.csv.
    public static List questionList; // List containing question objects for each line in quiz.csv
    private static final int minPasswordLength = 6; // Minimum password length, private

    // We decided to use lists instead of arrays because they are easier to deal with: they do not need to be
    // initialized, which is especially useful when appending the playerList

    public static void main(String[] args)
    {

        Player aPlayer = null;
        Misc aMisc = new Misc();
        // Stores both users and questions in lists at startup
        playerList = FileManager.getUsers();
        questionList = FileManager.getQuestions();

        // We use a String for the user input instead of a character because it is easier to deal with an empty input
        // from the user. We initialize it:

        String option = "";

        while (!option.equals("q")) // if the user input is q, the program stops
        {
            option = menu(); // We call the menu() method to take the user input

            switch (option)
            {
                case "l":
                    aPlayer = loginDetails(); // Stores a Player object returned from loginDetails into aPlayer
                    aMisc.login(aPlayer);
                    break;
                case "r":
                    aPlayer = registerDetails();
                    aMisc.register(aPlayer);
                    break;
                case "p":
                    aPlayer = aMisc.authGame(aPlayer);
                    break;
                case "a":
                    break;
                case "q":
                    break;
                default:
                    System.out.println("Unknown option");
                    break;
            }
        }

        FileManager.printToFile(playerList);   // Prints the updated playerList to the file at shutdown
        System.out.println("Bye, bye");
    }

    private static String menu() // returns a String that is stored in option
    {
        Scanner scan = new Scanner(System.in);

        String userInput = "";
        while (userInput.isEmpty()) // This covers the case where the user does not input anything
        {
            System.out.print("\n\\\\---------------------");
            System.out.print("\n\\\\ Welcome to The Game:");
            System.out.println("\n\\\\---------------------");

            System.out.println("\tLogin (L/l)");
            System.out.println("\tRegister (R/r)");
            System.out.println("\tPlay (P/p)");
            System.out.println("\tAbout (A/a)");
            System.out.println("\tQuit (Q/q)");

            System.out.print("\nPlease choose an option: ");
            userInput = scan.nextLine().toLowerCase();
        }

        userInput = userInput.toLowerCase().substring(0, 1); // .substring(0, 1) only takes the first letter
        return (userInput);
    }

    //--------------------------------------------------------------------------
    //  Inputs the details of a player passed as a parameter for logging in.
    //--------------------------------------------------------------------------
    private static Player loginDetails()
    {
        Scanner scan = new Scanner(System.in);

        String username = "";
        while (username.isEmpty())
        {
            System.out.print("\n\tEnter the player\'s username: ");
            username = scan.nextLine();
        }

        String password = "";
        while (password.isEmpty() || !passwordRestriction(password)) // checks if the 6 characters or more condition is met
        {
            System.out.print("\n\tEnter the player\'s password (6 characters or more): ");
            password = scan.next();
        }

        // The next loop passes a player object with first names and last names empty. This triggers only a part of
        // the checkRegistered method that is specific for logging in, as it is different when registering (we are
        // checking if both password and usernames match against only usernames when registering)

        if (Misc.checkRegistered(username, password) == null)
        {
            System.out.println("\nYou must register before you login.");
            return null;
        }
        else
        {
            Player thePlayer = Misc.checkRegistered(username, password);

            System.out.println("You have successfully logged in.\nPlayer: " + thePlayer.getFirstName() + " " + thePlayer.getLastName());
            System.out.println("Score: " + thePlayer.getScore());
            System.out.println("Number of games: " + thePlayer.getNumberOfGames());
            return thePlayer;
        }

    }

    //--------------------------------------------------------------------------
    //  Inputs the details of a player passed as a parameter for registration.
    //--------------------------------------------------------------------------
    private static Player registerDetails()
    {
        Scanner scan = new Scanner(System.in);

        String firstName = "";
        while (firstName.isEmpty())
        {
            System.out.print("\n\tEnter your first name: ");
            firstName = scan.nextLine();
        }

        String lastName = "";
        while (lastName.isEmpty())
        {
            System.out.print("\n\tEnter your last name: ");
            lastName = scan.nextLine();
        }

        String username = "";
        while (username.isEmpty())
        {
            System.out.print("\n\tEnter the player\'s login name: ");
            username = scan.nextLine();
        }

        String password = "";
        while (password.isEmpty() || !passwordRestriction(password))
        {
            System.out.print("\n\tEnter the player\'s password (6 characters or more): ");
            password = scan.next();
        }

        // This only triggers part of checkRegistered
        if (Misc.checkRegistered(username, "") == null)
        {
            System.out.print("\n\tThis username is already taken.");
            return null;
        }
        else
        {
            return new Player(firstName, lastName, username, password, 0, 0);
        }

    }

    private static boolean passwordRestriction(String password)
    {
        return password.length() >= minPasswordLength;
    }

}
