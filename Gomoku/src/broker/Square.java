package broker;

/**
 * @author GROUP22
 *
 *         This class represents an Object of type square which will contain and
 *         maintain all the necessary information for each square on the board.
 */
public class Square extends Board
{
	// TODO implement variables, constructors, getters, setters, and other
	// methods.
	// TODO A square will hold a Piece object;
	// TODO the constructor(s) should initialize the value of piece to null
	// - the piece should not be set until a player has place one on the square

	// instance variable
	private char pieceColour;

	// Constructor
	public Square()
	{
	}

	public Square(char colour)
	{
		this.pieceColour = colour;
	}

	// Getter
	public char getPieceColour()
	{
		return pieceColour;
	}

	// Setter
	public void setPieceColour(char pieceColour)
	{
		this.pieceColour = pieceColour;
	}

	// Methods
	public boolean isEmpty(int row, int column)
	{
		if (board[row][column] == null)
		{
			return true;
		} else
			return false;
	}

	public Player playedby()
	{
		return null;
	}

	public void addPiece(Player m)
	{

	}
}
