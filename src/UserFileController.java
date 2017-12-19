import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserFileController {

    //-------------------------------------------------------------------
    // Returns the list of players registered as a List of player objects
    //-------------------------------------------------------------------
    public static ArrayList<Player> getUsers() //
    {
        Scanner inputStream = null;
        ArrayList<Player> playerArray = new ArrayList<>();

        try
        {
            inputStream = new Scanner(new FileInputStream("users.csv")).useDelimiter("");

            while(inputStream.hasNext())
            {
                String users = inputStream.nextLine();
                // We allow the user to use commas in their info by using a regex skipping a comma if there
                // are any backslash before it
                String[] userList = users.split("(?<!\\\\)" + Pattern.quote(","));

                // Deletes the backslashes before commas before storing the player in playerArray
                String username = userList[2].replaceAll("\\\\,",",");

                int score = Integer.parseInt(userList[4]);
                int numberOfGames = Integer.parseInt(userList[5]);
                playerArray.add(new Player(userList[0], userList[1], username, userList[3], numberOfGames, score));
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("The file users.csv could not be found.");
            System.exit(0);
        }
        inputStream.close();
        return playerArray;
    }

    //---------------------------------------
    // Prints the List of Players to the file
    //---------------------------------------
    public static void printToFile(ArrayList aList)
    {
        PrintWriter outputStream = null;

        try
        {
            outputStream
                    = new PrintWriter(new FileOutputStream("users.csv"));

            for (int i = 0; i != aList.size(); i++)
            {
                String player = aList.get(i).toString() + "\n";
                outputStream.append(player);
            }
            outputStream.flush();
            outputStream.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error opening the file users.csv");
            System.exit(0);
        }
    }

}
