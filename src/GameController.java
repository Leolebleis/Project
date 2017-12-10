import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.Console;

public class GameController
{
    // We set the lists public as they are constantly used throughout the program
    public static List<Player> playerList; // List containing player objects for each line in users.csv.
    public static List<Question> questionList; // List containing question objects for each line in quiz.csv
    private static final int minPasswordLength = 6;

    public static void main(String[] args)
    {
        Player aPlayer = null;
        PlayerRestrictions aPlayerRestriction = new PlayerRestrictions();
        // Stores both users and questions in lists at startup
        playerList = FileManager.getUsers();
        questionList = FileManager.getQuestions();

        String option = ""; // To keep the compiler happy

        while (!option.equals("q"))
        {
            option = menu(); // We call the menu() method to take the user input

            switch (option)
            {
                case "l":
                    aPlayer = loginDetails(); // Stores the player if a match is found or null otherwise
                    aPlayerRestriction.login(aPlayer);
                    break;
                case "r":
                    aPlayer = registerDetails();
                    aPlayerRestriction.register(aPlayer);
                    break;
                case "p":
                    aPlayer = aPlayerRestriction.authGame(aPlayer);
                    break;
                case "b":
                    leaderBoard();
                    break;
                case "a":
                    aboutText();
                    break;
                case "q":
                    // Exits the game
                    break;
                default:
                    System.out.println("Unknown option");
                    break;
            }
        }

        FileManager.printToFile(playerList);   // Prints the updated playerList to the file at shutdown
        System.out.println("Bye, bye");
    }

    private static String menu()
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
            System.out.println("\tShow the Leader Board (B/b)");
            System.out.println("\tAbout (A/a)");
            System.out.println("\tQuit (Q/q)");

            System.out.print("\nPlease choose an option: ");
            userInput = scan.nextLine().toLowerCase();
        }

        userInput = userInput.toLowerCase().substring(0, 1); // .substring(0, 1) is the equivalent of charAt(0) for a String
        return (userInput);
    }

    //-----------------------------------------------------------------------
    //  Inputs the details of a player passed as a parameter for logging in.
    //-----------------------------------------------------------------------
    private static Player loginDetails()
    {
        Scanner scan = new Scanner(System.in);
        Console console = System.console();

        String username = "";
        while (username.isEmpty())
        {
            System.out.print("\n\tEnter the player\'s username: ");
            username = scan.nextLine();
        }

        String password = new String(console.readPassword("\n\tEnter the player\'s password (6 characters or more): "));

        while (password.isEmpty() || !passwordRestriction(password))
        {
            // readPassword() hides the input
            password = new String(console.readPassword("\n\tEnter the player\'s password (6 characters or more): "));
        }

        // This checks if the user exists or not by triggering only part of checkRegistered
        Player aPlayer = PlayerRestrictions.checkRegistered(username, password);

        if (aPlayer == null) // aPlayer is null if no match has been found
        {
            System.out.println("\nYou must register before you login.");
        }
        else
        {
            // Player managed to log in
        }
        return aPlayer;

    }

    //------------------------------------------------------------------------
    //  Inputs the details of a player passed as a parameter for registration.
    //------------------------------------------------------------------------
    private static Player registerDetails()
    {
        Scanner scan = new Scanner(System.in);
        Console console = System.console();

        String firstName = "";
        while (firstName.isEmpty() || firstName.contains(","))
        {
            System.out.print("\n\tEnter your first name: ");
            firstName = scan.nextLine();
        }

        String lastName = "";
        while (lastName.isEmpty() || lastName.contains(","))
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

        String password = new String(console.readPassword("\n\tEnter the player\'s password (6 characters or more): "));

        while (password.isEmpty() || !passwordRestriction(password))
        {
            // readPassword() hides the input
            password = new String(console.readPassword("\n\tEnter the player\'s password (6 characters or more): "));
        }

        // This only passes the username as only this needs to be unique when registering
        // In turn, it triggers only part of checkRegistered
        Player aPlayer = PlayerRestrictions.checkRegistered(username);

        if (aPlayer == null) // aPlayer is null if a username match has been found
        {
            System.out.print("\n\tThis username is already taken.");
            return null;
        }
        else
        {
            return new Player(firstName, lastName, username, PlayerRestrictions.hashPassword(password), 0, 0);
        }

    }

    private static void aboutText()
    {
        System.out.println("\n\tGAME INSTRUCTIONS:\n\n\tYou will be presented 10 questions, and for each of them you will" +
                "\n\tneed to choose the synonym of a word from a list displayed. " +
                "\n\tIf you select the right answer, you will gain 1 point. At the end" +
                "\n\t of the game, you will be able to see your score!");
        System.out.print("\nPress \"Enter\" when you are ready to play: ");
        System.out.print("\n\nPress Enter to continue:");

        try
        {
            System.in.read();
        }
        catch (Exception e)
        {
            System.out.println("Could not read input.");
        }
    }

    private static void leaderBoard()
    {
        List<Player> leaderBoard = playerList;

        System.out.println("\n\tLeader Board");
        System.out.println("\tShowing percentage of questions correct:");

        Collections.sort(leaderBoard); // We start by sorting the players in order of their score

        // We want to display the 3 best players, or fewer if there are fewer players registered
        for (int i = 0; i < ((leaderBoard.size() < 3) ? leaderBoard.size() : 3); i++)
        {
            Player aPlayer = leaderBoard.get(i);
            System.out.print("\n\t" + (i + 1) + ". " + aPlayer.getFirstName() + " " + aPlayer.getLastName() + " ");
            // If a Player has never played, the value goes to 0%
            System.out.printf("%.2f",((aPlayer.getNumberOfGames() == 0) ? (float) 0 : (float) aPlayer.getScore()/aPlayer.getNumberOfGames() * 10));
            System.out.println("%");
        }

        System.out.print("\n\nPress Enter to continue:");
        try
        {
            System.in.read();
        }
        catch (Exception e)
        {
            System.out.println("Could not read input.");
        }
    }

    private static boolean passwordRestriction(String password)
    {
        return password.length() >= minPasswordLength;
    }

}
