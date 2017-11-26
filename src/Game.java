import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game
{
    private static int score; // Score of the player for this game

    public static Player theGame(Player aPlayer)
    {

        System.out.print(aPlayer.toString());
        List randomQuestions = GameController.questionList;
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < 10; i++)
        {
            Random rnd = new Random();
            int index = rnd.nextInt(randomQuestions.size());
            Question aQuestion = (Question) randomQuestions.get(index);
            String[] answers = aQuestion.getWords();

            System.out.print(aQuestion.toString());

            int option = 10;
            while (option <= 0 || option > 5)
            {
                System.out.print("Please enter a number from 1 to 5: ");
                while (!scan.hasNextInt())
                {
                    System.out.println("That's not a number!");
                    scan.next();
                }
                option = scan.nextInt();

                if (!(option <= 0 || (option > 4)))
                {
                    if (aQuestion.getShuffled().get(option-1).equals(answers[4]))
                    {
                        score++;
                    }
                }
            }

            System.out.println("\n\tCorrect answer: " + answers[4]);

            randomQuestions.remove(index); // to delete entry we received

        }

        aPlayer.setScore(score);
        aPlayer.addOneGame();
        System.out.print("\n\tYour score: " + score + "\n");
        System.out.print(aPlayer.toString());

        return aPlayer;

    }

}