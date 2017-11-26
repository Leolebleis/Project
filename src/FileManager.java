import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileManager
{
    //-------------------------------------------------------------------
    // Returns the list of players registered as a List of player objects
    //-------------------------------------------------------------------
    public static List<Player> getUsers() //
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

                String password = userList[3].replaceAll("\\\\,", ",");
                String username = userList[2].replaceAll("\\\\,",",");
                int score = Integer.parseInt(userList[4]);
                int numberOfGames = Integer.parseInt(userList[5]);
                playerArray.add(new Player(userList[0], userList[1], username, password, numberOfGames, score));
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
    public static void printToFile(List aList)
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

    //-----------------------------------------------------------------------
    // Returns the list of questions registered as a List of question objects
    //-----------------------------------------------------------------------
    public static List getQuestions()
    {
        Scanner inputStream = null;
        ArrayList<Question> questionsArray = new ArrayList<>();

        try
        {
            inputStream =
                    new Scanner(new FileInputStream("quiz.csv")).useDelimiter(",");

            while (inputStream.hasNextLine())
            {
                String lineQuestion = inputStream.nextLine();
                String[] splitQuestion = lineQuestion.split(",");
                questionsArray.add(new Question(splitQuestion[0], splitQuestion[1], splitQuestion[2],
                        splitQuestion[3], splitQuestion[4]));
            }
        }

        catch(FileNotFoundException e) {
            System.out.println("File \"quiz.csv\" was not found");
            System.exit(0);
        }

        return questionsArray;
    }
}
