package broker;

/**
 * @author GROUP22 Code implemented by
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

	// * The player Stored in this square
	private Player player;

	// * The x and y coordinates of this Square
	private int x;
	private int y;

	/**
	 * This is the default constructor which sets the x and y coordinates of
	 * this square
	 * 
	 * @param toX The x coordinate
	 * @param toY The y coordinate
	 */
	public Square(int toX, int toY)
	{
		player = null;
		setX(toX);
		setY(toY);

	}

	/**
	 * This is a copy constructor that copies the Player and the x and y
	 * coordinate in toSquare
	 * 
	 * @param toSquare The Square object to copy
	 */
	public Square(Square toSquare)
	{
		/**
		 * Tries to setPlayer to desired Player object, and if the player
		 * variable in toSquare is null or there is already a player set to this
		 * square, player is not copied.
		 */
		try
		{
			setPlayer(new Player(toSquare.getPlayer()));
		} catch (NullPointerException npe)
		{
			System.out.println("going through line 50 of square.java");
		} catch (IllegalMove e)
		{
			System.out.println("going through line 53 of square.java");
		}
		setX(toSquare.getX());
		setY(toSquare.getY());
	}

	/**
	 * This method sets the player and checks if there is already a player
	 * stored in this square by checking if the instance variable player is
	 * null, and throws "illegal move" error if player is not null
	 * 
	 * @param toPlayer Player object to set to this square
	 * @throws IllegalMove
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

	/**
	 * This setter sets the x coordinate of this square
	 * 
	 * @param xCoordinate The x coordinate to set
	 */
	private void setX(int xCoordinate)
	{
		x = xCoordinate;
	}

	/**
	 * This setter sets the y coordinate of this square
	 * 
	 * @param yCoordinate The y coordinate to set
	 */
	private void setY(int yCoordinate)
	{
		y = yCoordinate;
	}

	/**
	 * Returns the x coordinate of this square
	 * 
	 * @return This square's x coordinate
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Returns the y coordinate of this square
	 * 
	 * @return This square's y coordinate
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * Returns reference to the Player object stored in this square
	 * 
	 * @return The Player object stored in the instance variable player
	 */
	protected Player getPlayer()
	{
		return player;
	}

	/**
	 * This methods checks to see if this square does not have a player set in
	 * it
	 * 
	 * @return True is there is no player stored in this square, returns false
	 *         if there is a player stored in this square
	 */
	public boolean isEmpty()
	{

		if (player == null)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * returns a reference of the Player object stored in this square
	 * 
	 * TODO @Emily
	 * 
	 * 1. refractor the name of this method to "playedBy" (right click > refractor > rename)
	 * 2. use the player copy constructor to encapsulate.
	 * 
	 * @return copy of the player object stored in this square
	 */
	public Player playedby()
	{
		return player;
	}

}
