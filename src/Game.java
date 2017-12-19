import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game
{
    private static final int NUMBER_OF_QUESTIONS = 10;
    private int score = 0;
    private int answered = 0;
    private int skips = 0;

    public static void theGame(Player aPlayer)
    {
        int finalScore = 0;

        List randomQuestions = GameController.questionList;
        Game aGame = new Game();

        // This loop runs for 10 questions
        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++)
        {
            Random rnd = new Random();
            int index = rnd.nextInt(randomQuestions.size()); // We take 10 random questions from the list of questions
            Question aQuestion = (Question) randomQuestions.get(index);
            String[] answers = aQuestion.getWords();

            System.out.print(aQuestion.toString()); // This displays the question already shuffled


            finalScore = aGame.updateScore(userInput(), aQuestion, answers);

            // We delete the question we just selected so as to never pick the same question twice (even though unlikely)
            // This also demonstrates the advantage of using lists, as randomQuestions.size() changes in consequence
            randomQuestions.remove(index);
        }

        aPlayer.setScore(finalScore);
        aPlayer.addOneGame();
    }

    private static int userInput()
    {
        Scanner scan = new Scanner(System.in);

        int option = 10; // To keep the compiler happy

        // We check for a valid input

        try{
            while (option <= 0 || option > 5)
            {
                System.out.print("Please enter a number from 1 to 5: ");
                option = scan.nextInt();
            }
        }
        catch (InputMismatchException a)
        {
            System.out.print("Problem");
        }

        return option;
    }

    private int updateScore(int option, Question aQuestion, String[] answers)
    {
        if (!(option <= 0 || (option > 4))) // Otherwise user chooses 5
        {
            // We check that the string chosen in the shuffled list is the same as the correct string from the
            // original list answers[]
            if (aQuestion.getShuffled().get(option - 1).equals(answers[4]))
            {
                System.out.println("\n\nCorrect!");
                score++;
                answered++;
            }
            else
            {
                System.out.println("\n\nIncorrect!");
                answered++;
            }
        }
        else if (option == 5)
        {
            System.out.println("\n\nSkipped!");
            skips++;
        }

        System.out.println("\n\n\t" + answers[0] + " means " + answers[4]);
        roundSummary(score, skips, answered);

        return score;
    }

    private static void roundSummary(int score, int skips, int answered)
    {
        System.out.println("\n\nQuestions answered:     " + answered + "/" + NUMBER_OF_QUESTIONS);
        System.out.println("Questions skipped:       " + skips + "/" + NUMBER_OF_QUESTIONS);
        System.out.println("\n\nCurrent score:        " + score + "/" + NUMBER_OF_QUESTIONS);
        if(answered == NUMBER_OF_QUESTIONS) // This is only run for the last question
            System.out.print("\n\nGame over!");
        System.out.print("\n\nPress Enter to continue:");
        try
        {
            System.in.read();
        }
        catch(Exception a)
        {
            System.out.println("Could not read input.");
        }
    }

}