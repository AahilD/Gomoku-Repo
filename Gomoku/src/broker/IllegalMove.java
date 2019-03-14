package broker;

public class IllegalMove extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#toString()
     */
    public String toString()
    {
	return "This square is not playable (already occupied by a player)!!!";
    }
}
