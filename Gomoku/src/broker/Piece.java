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
	
	//Getter
	public char getBlackOrWhite() {
		return blackOrWhite;
	}
	
	//Setter
	public void setBlackOrWhite(char blackOrWhite) {
		this.blackOrWhite = blackOrWhite;
	}
}
