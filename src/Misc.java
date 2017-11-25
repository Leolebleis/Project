public class Misc {

    private Player currentPlayer = null;

    public static String clearScreen()
    {
        return "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    }

    public void login(Player aPlayer) {
        if (aPlayer.getUsername() != null && aPlayer.getPassword() != null) {
            if (currentPlayer != null) {
                System.out.println("\n" + currentPlayer.getUsername() +
                        " has already logged in.");
            } else // The player hasn't registered
            {
                currentPlayer = aPlayer;
            }
        } else {

        }
    }

    public void register(Player aPlayer)
    {
        if (aPlayer != null)
        {
            GameController.playerList.add(aPlayer);
        }
        else
        {

        }
    }

    public void authGame()
    {
        if (currentPlayer != null)
        {
            Game.theGame(); // Implementation starts here!

            // Test to print all the questions, which works
//            for(int i = 0; i != GameController.questionList.size(); i++)
//            {
//                Question anotherQuestion = (Question) GameController.questionList.get(i);
//                System.out.println(anotherQuestion.toStringShuffled());
//            }
        }
        else
        {
            System.out.println("\nYou need to log in first.");
        }
    }



    public static boolean checkRegistered(Player aPlayer)
    {
        boolean isRegistered = false;

        if (aPlayer.getPassword() == null) // Loop only activates if registering
        {
            for (int i = 0; i < GameController.playerList.size(); i++)
            {
                Player anotherPlayer = (Player) GameController.playerList.get(i);

                if (aPlayer.getUsername().equals(anotherPlayer.getUsername())) // We use the equals method in player to check if both username and password match
                {
                    isRegistered = true;
                    break;
                }
                else
                {
                    isRegistered = false;
                }

            }
        }

        else // Loop only activates if logging in
        {
            for (int i = 0; i < GameController.playerList.size(); i++)
            {
                Player anotherPlayer = (Player) GameController.playerList.get(i);

                if (aPlayer.getUsername().equals(anotherPlayer.getUsername()) && aPlayer.getPassword().equals(anotherPlayer.getPassword())) // We use the equals method in player to check if both username and password match
                {
                    isRegistered = true;
                    break;
                }
                else
                {
                    isRegistered = false;
                }
            }
        }

        return isRegistered;
    }

}
