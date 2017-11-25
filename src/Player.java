public class Player
{

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    Player()
    {
        firstName = "NOT_SET";
        lastName = "NOT_SET";
        username = "NOT_SET";
        password = "NOT_SET";
    }

    Player(String aFirstName, String aLastName, String aUsername, String aPassword)
    {
        firstName = aFirstName;
        lastName = aLastName;
        username = aUsername;
        password = aPassword;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String toString()
    {
        return firstName + "," + lastName + "," + username + "," + password + ",";
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
