package broker;

/**
 * @author GROUP22
 *
 * This class represents an Object of type square which
 * will contain and maintain all the necessary information
 * for each square on the board.
 */
public class Square
{
	// TODO implement variables, constructors, getters, setters, and other methods.
	// TODO	A square will hold a Piece object;
	// TODO the constructor(s) should initialize the value of piece to null
	// 		- the piece should not be set until a player has place one on the square
	
	private char pieceColour;
	
	//Constructor
	public Square (char colour) {
		pieceColour = colour;
	}
	
	//Getter
	public char getPieceColour{
		return char; 
	}

	//Methods
	public boolean isEmpty(int row, int column){
		if (board[row][column]==null) {
			return true;
		}
		else
			return false
	}
	public Player playedby() {
		
	}
	public void addPiece(Player) {
		
	}
}
