import java.lang.Comparable;

public class Player implements Comparable<Player>
{

    private String firstName, lastName, username, password;
    private int score, numberOfGames;

    Player()
    {
        firstName = "NOT_SET";
        lastName = "NOT_SET";
        username = "NOT_SET";
        password = "NOT_SET";
        score = 0;
        numberOfGames = 0;
    }

    Player(String aFirstName, String aLastName, String aUsername, String aPassword, int aScore, int aNumberOfGames)
    {
        firstName = aFirstName;
        lastName = aLastName;
        username = aUsername;
        password = aPassword;
        score = aScore;
        numberOfGames = aNumberOfGames;
    }

    public void setScore(int newScore)
    {
        score = score + newScore;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getScore()
    {
        return score;
    }

    public int getNumberOfGames()
    {
        return numberOfGames;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void addOneGame()
    {
        numberOfGames++;
    }

    // This is how the players will be printed to the file
    public String toString()
    {
        // Prints a backslash before commas to allow the user to use them (this is only used when printing to the file)
        String theUsername = username.replaceAll(",","\\\\,");


        return firstName + "," + lastName + "," + theUsername + "," + password + "," +
                numberOfGames + "," + score;

    }

    public boolean equals(Object otherObject) throws NullPointerException
    {
        if (otherObject == null)
        {
            return false;
        }

        if (getClass() != otherObject.getClass())
        {
            return false;
        }

        Player otherPlayer = (Player) otherObject;
        return (this.username.compareTo(otherPlayer.username) == 0 && (this.password.compareTo(otherPlayer.password) == 0));
    }

    @Override
    public int compareTo(Player aPlayer)
    {
        // In this overridden method we compare each player's win percentage

        if ((float) score/numberOfGames * 10 < (float) aPlayer.score/aPlayer.numberOfGames * 10)
        {
            return 1;
        }
        else if ((float) score/numberOfGames * 10 > (float) aPlayer.score/aPlayer.numberOfGames * 10)
        {
            return -1;
        }
        else
        {
            return 0;
        }

    }

}
