public class Player
{

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int score;
    private int numberOfGames;

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
        System.out.println(GameController.playerList.get(0).toString());
    }

    public void addOneGame()
    {
        numberOfGames++;
        System.out.println(GameController.playerList.get(0).toString());
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

    // This is how the players will be printed to the file
    public String toString()
    {
        return firstName + "," + lastName + "," + username + "," + password + "," +
                numberOfGames + "," + score;
    }

    public boolean equals(Object otherObject) throws NullPointerException
    {
        if (otherObject == null)
            return false;

        if (getClass() != otherObject.getClass())
            return false;
        Player otherPlayer = (Player) otherObject;
        return (this.username.compareTo(otherPlayer.username) == 0 && (this.password.compareTo(otherPlayer.password) == 0));
    }

}
