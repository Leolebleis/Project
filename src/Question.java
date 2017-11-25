import java.util.Random;

public class Question
{
    private String[] words = new String[5];

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
        words[0] = questionWord;
        words[1] = firstWord;
        words[2] = secondWord;
        words[3] = thirdWord;
        words[4] = answer;
    }

    public String[] getWords()
    {
        return words;
    }

    public void setWords(String questionWord, String firstWord, String secondWord, String thirdWord, String answer)
    {
        words[0] = questionWord;
        words[1] = firstWord;
        words[2] = secondWord;
        words[3] = thirdWord;
        words[4] = answer;
    }

    public String toString() {
        int[] solutionArray = {0, 1, 2, 3};
        shuffleArray(solutionArray);
        String question = "New word: " + words[0];

        for (int i = 0; i < solutionArray.length; i++) {
            switch (solutionArray[i]) {
                case 0:
                    question += ("\n" + (i + 1) + ". " + words[1]);
                    break;
                case 1:
                    question += ("\n" + (i + 1) + ". " + words[2]);
                    break;
                case 2:
                    question += ("\n" + (i + 1) + ". " + words[3]);
                    break;
                case 3:
                    question += ("\n" + (i + 1) + ". " + words[4]);
                    break;
            }
        }

        question += "\n5. I’m not sure\n\n ";

        return question;
    }

    private static void shuffleArray(int[] ar) //Fisher–Yates shuffle//
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