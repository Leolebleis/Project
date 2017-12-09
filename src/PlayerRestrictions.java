import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PlayerRestrictions
{
    // currentPlayer != null when the player has logged in already
    private Player currentPlayer = null;


    public void login(Player aPlayer)
    {
        if (aPlayer != null)
        {
            if (currentPlayer != null)
            {
                System.out.println("\n" + currentPlayer.getUsername() +
                        " has already logged in.");
            }
            else // The player hasn't registered
            {
                currentPlayer = aPlayer;
                System.out.println("You have successfully logged in.\nPlayer: " + aPlayer.getFirstName() + " " + aPlayer.getLastName());
                System.out.println("Score: " + aPlayer.getScore());
                System.out.println("Number of games: " + aPlayer.getNumberOfGames());
            }
        }
        else
        {
            // Only activated if the user failed logging in
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
            // This is only activated if the user failed registering, in which case the info is not added to the list
        }
    }



    public Player authGame(Player aPlayer)
    {
        if (currentPlayer != null) // Launches the game only if the player has registered
        {
            Game.theGame(currentPlayer);
            return currentPlayer;
        }
        else
        {
            System.out.println("\nYou need to log in first.");
        }
        return aPlayer;
    }

    public static Player checkRegistered(String username) // Polymorphic method
    {
        Player aPlayer = new Player();

        for (int i = 0; i < GameController.playerList.size(); i++)
        {
            Player anotherPlayer = GameController.playerList.get(i);

            if (username.equals(anotherPlayer.getUsername())) // We use the equals method in player to check if both username and password match
            {
                aPlayer = null;
            }
            else
            {
                //
            }
        }
        return aPlayer;
    }

    public static Player checkRegistered(String username, String password) // Polymorphic method
    {
        Player aPlayer = new Player();

        for (int i = 0; i != GameController.playerList.size(); i++)
        {
            Player anotherPlayer = GameController.playerList.get(i);

            String hashedPassword = hashPassword(password);

            if (username.equals(anotherPlayer.getUsername()) && hashedPassword.equals(anotherPlayer.getPassword())) // We use the equals method in player to check if both username and password match
            {
                return anotherPlayer;
            }
            else
            {
                aPlayer = null;
            }
        }
        return aPlayer;

    }

    public static String hashPassword(String password)
    {
        try
        {
            byte[] bytesPass = password.getBytes("UTF-8");

            MessageDigest hashing = MessageDigest.getInstance("MD5");
            byte[] hashedPass = hashing.digest(bytesPass);

            return Base64.getEncoder().encodeToString(hashedPass);

        }
        catch (UnsupportedEncodingException | NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return "";
        }
    }


}
