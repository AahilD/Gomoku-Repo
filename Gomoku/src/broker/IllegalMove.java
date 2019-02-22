package broker;

public class IllegalMove extends Exception
{
	public IllegalMove()
	{
		super("This square is not playable");
	}

}
