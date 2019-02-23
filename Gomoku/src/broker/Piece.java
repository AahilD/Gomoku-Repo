package broker;

/**
 * @author GROUP 22 Code implementation by Leslie Nguyen This class will
 *         represent an object of type Piece which will contain the necessary
 *         data for each piece that will be played by either player.
 * 
 *         NOTE THAT WE WILL LEAVING THIS METHOD HERE, BUT WE WILL NOT BE USING
 *         IT UNTILL WE ACTUALLY SE A NEED FOR IT.
 * 
 */
public class Piece
{
	private char blackOrWhite;

	// Constructors
	/**
	 * This constructs a piece with default values.
	 */
	public Piece()
	{
	}
	
	/**
	 * This constructs a player by creating a copy of another piece.
	 * @param originalPiece is the original piece that will be copied
	 */
	public Piece(Piece originalPiece)
	{
		blackOrWhite = originalPiece.getBlackOrWhite();
	}

	/**
	 * This constructs a player with a char.
	 * @param letter this char given in letter is assigned 
	 * 				 into the blackOrWhit instance variable
	 */
	public Piece(char letter)
	{
		this.blackOrWhite = letter;
	}

	// Getter
	public char getBlackOrWhite()
	{
		return blackOrWhite;
	}

	// Setter
	public void setBlackOrWhite(char blackOrWhite)
	{
		this.blackOrWhite = blackOrWhite;
	}
}
