import java.util.List;
import java.util.Scanner;

public class GameController {

    public static List playerList;
    public static List questionList;
    private static int minPasswordLength = 6;

    public static void main(String[] args)
    {

        Player aPlayer = null;
        Misc aMisc = new Misc();
        playerList = FileManager.getUsers();
        questionList = FileManager.getQuestions();

        char option = '*';

        while (option != 'q')
        {
            option = menu();

            switch(option)
            {
                case 'l':
                    aPlayer = loginDetails();
                    aMisc.login(aPlayer);
                    break;
                case 'r':
                    aPlayer = registerDetails();
                    aMisc.register(aPlayer);
                    break;
                case 'p':
                    aMisc.authGame();
                    break;
                case 'a':
                    break;
                case 'q':
                    break;
                default:
                    System.out.println("Unknown option");
                    break;
            }
        }

        FileManager.printToFile(playerList);   // Print playerList to file
        System.out.println("Bye, bye");
    }

    private static char menu()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("\n\\\\---------------------");
        System.out.print("\n\\\\ Welcome to The Game:");
        System.out.println("\n\\\\---------------------");

        System.out.println("\tLogin (L/l)");
        System.out.println("\tRegister (R/r)");
        System.out.println("\tPlay (P/p)");
        System.out.println("\tAbout (A/a)");
        System.out.println("\tQuit (Q/q)");

        System.out.print("\nPlease choose an option: ");

        return (scan.nextLine().toLowerCase().charAt(0));

    }

    //--------------------------------------------------------------------------
    //  Inputs the details of a player passed as a parameter.
    //--------------------------------------------------------------------------
    private static Player loginDetails()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("\n\tEnter the player\'s login name: ");
        String username = scan.nextLine();
        while (username.isEmpty())
        {
            System.out.print("\n\tEnter the player\'s login name: ");
            username = scan.nextLine();
        }

        System.out.print("\n\tEnter the player\'s password: ");
        String password = scan.next();
        while (password.isEmpty() || !passwordRestriction(password))
        {
            System.out.print("\n\tEnter the player\'s password (6 characters or more): ");
            password = scan.next();
        }

        if (!Misc.checkRegistered(new Player(null, null, username, password)))
        {
            System.out.println("\nYou must register before you login.");
            return new Player(null, null, null, null);
        }
        else
        {
            return new Player(null, null, username, password);
        }

    }

    private static Player registerDetails()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("\n\tEnter your first name: ");
        String firstName = scan.nextLine();
        while (firstName.isEmpty())
        {
            System.out.print("\n\tEnter your first name: ");
            firstName = scan.nextLine();
        }

        System.out.print("\n\tEnter your last name: ");
        String lastName = scan.nextLine();
        while (lastName.isEmpty())
        {
            System.out.print("\n\tEnter your last name: ");
            lastName = scan.nextLine();
        }

        System.out.print("\n\tEnter the player\'s login name: ");
        String username = scan.nextLine();
        while (username.isEmpty())
        {
            System.out.print("\n\tEnter the player\'s login name: ");
            username = scan.nextLine();
        }

        System.out.print("\n\tEnter the player\'s password: ");
        String password = scan.next();
        while (password.isEmpty() || !passwordRestriction(password))
        {
            System.out.print("\n\tEnter the player\'s password (6 characters or more): ");
            password = scan.next();
        }

        if(Misc.checkRegistered(new Player(null, null, username, null)))
        {
            System.out.print("\n\tThis username is already taken.");
            return null;
        }
        else
        {
            return new Player(firstName, lastName, username, password);
        }
    }

    public static boolean passwordRestriction(String password)
    {
        return password.length() >= minPasswordLength;
    }

}
