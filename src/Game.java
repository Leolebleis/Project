import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game
{


    public static void theGame(Player aPlayer)
    {
        int score = 0; // Score of the player for this game
        int skips = 0;
        int answered = 0;

        List randomQuestions = GameController.questionList;
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < 10; i++)
        {
            Random rnd = new Random();
            int index = rnd.nextInt(randomQuestions.size()); // We take 10 random questions from the list of questions
            Question aQuestion = (Question) randomQuestions.get(index);
            String[] answers = aQuestion.getWords();

            System.out.print(aQuestion.toString()); // This displays the question already shuffled

            int option = 10;
            while (option <= 0 || option > 5) // We check for a valid input
            {
                System.out.print("Please enter a number from 1 to 5: ");
                while (!scan.hasNextInt())
                {
                    System.out.println("That's not a number!");
                    scan.next();
                }
                option = scan.nextInt();

                if (!(option <= 0 || (option > 4))) // Otherwise we get an error if user chooses 5
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
                        System.out.println("\n\nInorrect!");
                        answered++;
                    }
                }
                else
                {
                    System.out.println("\n\nSkipped!");
                    skips++;
                }
            }
            System.out.println("\n\n\t" + answers[0] + " means " + answers[4]);
            roundSummary(score, skips, answered);
            // We delete the question we just selected so as to never pick the same question twice (even though unlikely)
            // This also demonstrates the advantage of using lists, as randomQuestions.size() changes in consequence
            randomQuestions.remove(index);
        }

        aPlayer.setScore(score);
        aPlayer.addOneGame();
        System.out.print("Game over!");
        roundSummary(score, skips, answered);

    }

    private static void roundSummary(int score, int skips, int answered)
    {
        System.out.println("\n\nQuestions answered:     " + answered + "/10");
        System.out.println("Questions skipped:       " + skips + "/10");
        System.out.println("\n\nCurrent score:        " + score + "/10");
        System.out.println("\n\nPress Enter to continue:");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {

        }
    }

}