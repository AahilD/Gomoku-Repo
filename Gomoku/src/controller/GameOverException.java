package controller;
/**
 * I don't know what this is used for :)
 * 
 * @author
 *
 */
public class GameOverException extends Exception
{
    // serial version id
    private static final long serialVersionUID = 1L;

    // The user name of the winner
    private String winnerUsername;
    // the user name of the looser
    private String looserUsername;

    /**
     * This is the default constructor, with the required parameters to identify
     * the winner and looser.
     * 
     * @param winnerUname
     * @param looserUname
     */
    public GameOverException(String winnerUname, String looserUname)
    {
	setWinnerUsername(winnerUname);
	setLooserUsername(looserUname);
    }

    /**
     * Call this method to get the user name of the player who won.
     * 
     * @return the user name of the player who won
     */
    public String getWinnerUsername()
    {
	return winnerUsername;
    }

    /**
     * Call this method to get the username of the player who lost.
     * 
     * @return the user name of the player who lost
     */
    public String getLooserUsername()
    {
	return looserUsername;
    }

    /**
     * Call this method to set the user name of the player who won.
     * 
     * @param winnerUsername is the user name of the player who won.
     */
    private void setWinnerUsername(String winnerUsername)
    {
	this.winnerUsername = winnerUsername;
    }

    /**
     * Call this method to set the user name of the player who lost.
     * 
     * @param looserUsername is the user name of the player who lost.
     */
    private void setLooserUsername(String looserUsername)
    {
	this.looserUsername = looserUsername;
    }
}
