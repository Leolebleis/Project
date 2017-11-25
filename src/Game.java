import java.util.Random;
import java.util.Scanner;

public class Game
{
    static int score;

    public static void theGame()
    {
        List randomQuestions = GameController.questionList;
        Scanner keyboard = new Scanner(System.in);

        for (int i = 0; i < 10; i++)
        {
            Random rnd = new Random();
            int index = rnd.nextInt(randomQuestions.size());
            Question aQuestion = (Question) randomQuestions.get(index);
            String[] answers = aQuestion.getWords();

            System.out.print(aQuestion.toString());

            int option;
            do {
                System.out.println("Please enter a number from 1 to 5: ");
                while (!keyboard.hasNextInt()) {
                    System.out.println("That's not a number!");
                    keyboard.next(); // this is important!
                }
                option = keyboard.nextInt();

                if (option != 5) {
                    if (answers[option].equals(answers[4])) {
                        score++;
                    }
                }

            } while (option <= 0 || option > 5);
            System.out.println("\nCorrect answer: " + answers[4]);

            randomQuestions.remove(index); // to delete entry we received

        }
        System.out.print("Your score: " + score);
    }

}