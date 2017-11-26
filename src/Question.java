import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Question
{
    private String[] words = new String[5];             // Original Array of different questions
    private List<String> shuffled = new ArrayList<>();  // Shuffled list of questions that will be displayed in game

    Question()
    {
        words[0] = "NOT_SET";
        words[1] = "NOT_SET";
        words[2] = "NOT_SET";
        words[3] = "NOT_SET";
        words[4] = "NOT_SET";
    }

    Question(String questionWord, String firstWord, String secondWord, String thirdWord, String answer)
    {
        words[0] = questionWord;    // First is always the word in the question
        words[1] = firstWord;
        words[2] = secondWord;
        words[3] = thirdWord;
        words[4] = answer;          // Fourth is always the right answer
    }

    public String[] getWords()
    {
        return words;
    }

    public List getShuffled()
    {
        return shuffled;
    }

    // Displays the questions already randomized
    public String toString()
    {
        int[] solutionArray = {0, 1, 2, 3};

        // This method shuffles the integers from 0 to 3
        shuffleArray(solutionArray);

        String question = "\n\tNew word: " + words[0];

        for (int i = 0; i < solutionArray.length; i++)
        {
            switch (solutionArray[i])
            {
                case 0:
                    shuffled.add(words[1]); // Using a list makes this process very straight forward
                    question += ("\n" + (i + 1) + ". " + words[1]);
                    break;
                case 1:
                    shuffled.add(words[2]);
                    question += ("\n" + (i + 1) + ". " + words[2]);
                    break;
                case 2:
                    shuffled.add(words[3]);
                    question += ("\n" + (i + 1) + ". " + words[3]);
                    break;
                case 3:
                    shuffled.add(words[4]);
                    question += ("\n" + (i + 1) + ". " + words[4]);
                    break;
            }
        }

        question += "\n5. I’m not sure\n\n ";

        return question;
    }

    private static void shuffleArray(int[] ar) // Fisher–Yates shuffle
    {

        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

}