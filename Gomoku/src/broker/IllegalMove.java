package broker;

/**
 * This exception is to be thrown when there was an attempt by any
 * player to place a piece on a square that is already occupied.
 * 
 * @author GROUP 22
 *
 */
public class IllegalMove extends Exception
{
    private static final long serialVersionUID = 1L;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString()
    {
	return "This square is not playable (already occupied by a player)!!!";
    }
}
