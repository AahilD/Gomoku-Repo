package broker;

/**
 * @author GROUP 22
 *
 * This class will represent an object of type Piece
 * which will contain the necessary data for each piece
 * that will be played by either player.
 * 
 */
public class Piece
{
	private char blackOrWhite;
	
	//Consulctors
	public Piece() {
		
	}
	public Piece(Piece originalPiece) {
		blackOrWhite=originalPiece.getBlackOrWhite();
	}
	
	//Getter
	public char getBlackOrWhite() {
		return blackOrWhite;
	}
	
	//Setter
	public void setBlackOrWhite(char blackOrWhite) {
		this.blackOrWhite = blackOrWhite;
	}
}
