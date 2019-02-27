package broker;

/**
 * @author GROUP22
 *
 *         This class represents an Object of type square which will contain and
 *         maintain all the necessary information for each square on the board.
 */
public class Square
{
	// TODO implement variables, constructors, getters, setters, and other
	// methods.
	// TODO the constructor(s) should initialize the value of Player to null
	// TODO the setter should throw an exception if the square.getPlayer() !=
	// null
	// TODO each square should have the x and y coordinates that it belongs to
	// TODO the x and y coordinates getters and setters should both be private
	// (no one should be able to change them)
	// TODO the default constructor must require x and y coordinates and set
	// Player to null by default

	// instance variable
	private static Player player;
	private static int x;
	private static int y;

	/**
	 * This is the default constructor and only constructor
	 * 
	 * @param toX
	 * @param toY
	 */

	// Constructor //
	public Square(int toX, int toY)
	{
		player = null;
		setX(toX);
		setY(toY);

	}
	
	public Square(Square b)
	{
		player = b.player;
		x=b.x;
		y=b.y;
	}

	// Setter
	/**
	 * Call this method to set player, player must be null, throws "illegal
	 * move" error if player is not null
	 * 
	 * @param m
	 */
	public void setPlayer(Player toPlayer) throws IllegalMove
	{
		if (isEmpty())
		{
			player = toPlayer;
		} else
		{
			throw new IllegalMove();
		}
	}
	
	private void setX(int xCoordinate)
	{
		x = xCoordinate;
	}

	private void setY(int yCoordinate)
	{
		y = yCoordinate;
	}

	// Getter
	public static int getX()
	{
		return x;
	}

	public static int getY()
	{
		return y;
	}

	protected static Player getPlayer()
	{
		return player;
	}

	// Methods
	public boolean isEmpty()
	{
		return player == null;
	}
	// TODO remove paramaters and just check to see if player == null
	// TODO return true if player is null

	// TODO you should be able to implement this method once the other changes
	// have been done

	public Player playedby()
	{
		return player;
	}

}
