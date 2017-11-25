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

    public static List<Player> getUsers()
    {
        Scanner inputStream = null;
        ArrayList<Player> playerArray = new ArrayList<>();

        try
        {
            inputStream = new Scanner(new FileInputStream("users.csv")).useDelimiter("");

            while(inputStream.hasNext())
            {
                String users = inputStream.nextLine();
                String[] userList = users.split("(?<!\\\\)" + Pattern.quote(",")); // how do csv files work?
                playerArray.add(new Player(userList[0], userList[1], userList[2], userList[3]));
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

    //--------------------------------------------------------------------------
    //  Check whether a player is already registered
    //--------------------------------------------------------------------------


    public static void printToFile(List aList)
    {

        PrintWriter outputStream = null;

        try {
            outputStream
                    = new PrintWriter(new FileOutputStream("users.csv"));

            for(int i = 0; i != aList.size(); i++)
            {
                String player = aList.get(i).toString() + "\n";
                outputStream.append(player);
            }
            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file users.csv");
            System.exit(0);
        }
    }

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
