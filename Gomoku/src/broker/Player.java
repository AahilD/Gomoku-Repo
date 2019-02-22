package broker;

/**
 * @author GROUP 22 Code implementation by Leslie Nguyen This class represents
 *         an object of type Player which will contain and maintain all the
 *         relevant player profile.
 * 
 */

public class Player
{
	// TODO implement variables, constructor(s), getters, setters, and other
	// methods to increment counts of won, lose, draw
	private String userName;
	private Piece pieceColour;
	private int winCount;
	private int loseCount;
	private int drawCount;

	// constructors
	public Player(String name, char colour)
	{
		setUserName(name);
		setPieceColour(new Piece(colour));
		this.winCount = 0;
		this.loseCount = 0;
		this.drawCount = 0;
	}

	/**
	 * @param toPlayer
	 */
	public Player(Player toPlayer)
	{
		// TODO implement copy constructor
	}

	/**
	 * @return
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * @return
	 */
	public int getWinCount()
	{
		return winCount;
	}

	/**
	 * @return
	 */
	public int getLoseCount()
	{
		return loseCount;
	}

	/**
	 * @return
	 */
	public int getDrawCount()
	{
		return drawCount;
	}

	/**
	 * @return
	 */
	public Piece getPiece()
	{
		Piece copyPiece = new Piece(pieceColour);
		return copyPiece;
	}

	// Setter
	/**
	 * @param userName
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * @param winCount
	 */
	public void setWinCount(int winCount)
	{
		this.winCount = winCount;
	}

	/**
	 * @param loseCount
	 */
	public void setLoseCount(int loseCount)
	{
		this.loseCount = loseCount;
	}

	/**
	 * @param drawCount
	 */
	public void setDrawCount(int drawCount)
	{
		this.drawCount = drawCount;
	}

	/**
	 * @param inwardPiece
	 */
	public void setPieceColour(Piece inwardPiece)
	{
		this.pieceColour = new Piece(inwardPiece);
	}
}
