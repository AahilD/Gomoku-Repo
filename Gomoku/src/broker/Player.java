package broker;

/**
 * @author GROUP 22
 * Code implementation by Leslie Nguyen
 * This class represents an object of type Player
 * which will contain and maintain all the relevant
 * player profile.
 * 
 */

public class Player
{
	// TODO implement variables, constructor(s), getters, setters, and other methods
	private String userName;
	private Piece pieceColour;
	private int winCount;
	private int loseCount;
	private int drawCount;
	
	//constructors
	public Player(String name, char colour) {
		setUserName(name);
		setPieceColour(new Piece(colour));
		this.winCount=0;
		this.loseCount=0;
		this.drawCount=0;
	}
	
	//Getter
	public String getUserName() {
		return userName;
	}
	public int getWinCount() {
		return winCount;
	}
	public int getLoseCount() {
		return loseCount;
	}
	public int getDrawCount() {
		return drawCount;
	}
	public Piece getPiece() {
		Piece copyPiece = new Piece(pieceColour);
		return copyPiece;
	}
	
	
	//Setter
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}
	public void setLoseCount(int loseCount) {
		this.loseCount = loseCount;
	}
	public void setDrawCount(int drawCount) {
		this.drawCount = drawCount;
	}
	public void setPieceColour(Piece inwardPiece) {
		this.pieceColour = new Piece(inwardPiece);
	}
	
}
