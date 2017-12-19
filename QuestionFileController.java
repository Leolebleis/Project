import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionFileController
{
    //-----------------------------------------------------------------------
    // Returns the list of questions registered as a List of question objects
    //-----------------------------------------------------------------------
    public static ArrayList<Question> getQuestions()
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
