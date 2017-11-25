import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game
{
    public static void Game() {
        List randomQuestions = GameController.questionList;
        Scanner keyboard = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            Random rnd = new Random();
            int index = rnd.nextInt(randomQuestions.size());
            System.out.print("\n" + randomQuestions.get(index).toString());
            int option;
            do {
                System.out.println("Please enter a number from 1 to 5: ");
                while (!keyboard.hasNextInt()) {
                    System.out.println("That's not a number!");
                    keyboard.next(); // this is important!
                }
                option = keyboard.nextInt();
            } while (option <= 0 || option > 5);

            randomQuestions.remove(index); // to delete entry we received
        }
    }

}