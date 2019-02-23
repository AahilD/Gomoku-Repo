package broker;

public class IllegalMove extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalMove()
	{
		super("This square is not playable");
	}
}
