package broker;

/**
 * 
 * This exception stores who the winning and losing player
 * are in a single round
 *
 */
public class WinAndLosses extends Exception
{

    // Serial Version id
    private static final long serialVersionUID = 1L;

    // The Winner player object
    private Player winner;
    // The loser Player object
    private Player loser;

    /**
     * This is the default constructor, which stores which player won and which
     * one lost.
     * 
     * @param aWinner The winning player Player object.
     * @param aLoser  The loosing player Player object.
     */
    public WinAndLosses(Player aWinner, Player aLoser)
    {
	winner = new Player(aWinner);
	loser = new Player(aLoser);
    }

    /**
     * Return a copied reference to the winner.
     * 
     * @return Copy of Player object of the winner.
     */
    public Player getWinner()
    {
	return new Player(winner);
    }

    /**
     * Set who the winner is.
     * 
     * @param aPlayer Player object of winner.
     */
    public void setWinner(Player aPlayer)
    {
	winner = new Player(aPlayer);
    }

    /**
     * Returns a copied reference to the loser.
     * 
     * @return Copy of Player object for the loser.
     */
    public Player getLoser()
    {
	return new Player(loser);
    }

    /**
     * Set who the loser is
     * 
     * @param aPlayer Player object of loser
     */
    public void setLoser(Player aPlayer)
    {
	loser = new Player(aPlayer);
    }

    @Override
    /**
     * Creates a string of who won, and who lost.
     * 
     * @return String message.
     */
    public String toString()
    {
	return winner.getUserName() + " wins! Better luck next time "
		+ loser.getUserName();
    }
}
