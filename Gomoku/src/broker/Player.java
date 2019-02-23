package broker;

import java.util.ArrayList;

/**
 * @author GROUP 22 Code implementation by Leslie Nguyen This class represents
 *         an object of type Player which will contain and maintain all the
 *         relevant player profile.
 * 
 */

public class Player
{
	// TODO implement variables, constructor(s), getters, setters, and other
	private String userName;
	private Piece pieceColour;
	private int winCount;
	private int loseCount;
	private int drawCount;

	// constructors
	/**
	 * This constructs a player with a user name and piece colour.
	 * 
	 * @param name   the String name is given to the instance variable of
	 *               userName
	 * @param colour the value of the char is assigned to a new Piece through
	 *               the class Piece
	 */
	public Player(String name, char colour)
	{
		setUserName(name);
		setPieceColour(new Piece(colour));
		this.winCount = 0;
		this.loseCount = 0;
		this.drawCount = 0;
	}

	/**
	 * This constructs a copy of a player with the original player
	 * 
	 * @param toPlayer the to player is the original that is used to create a
	 *                 copy
	 */
	public Player(Player toPlayer)
	{
		setUserName(toPlayer.getUserName());
		// fix this line of code once it's implemented
		setPieceColour(toPlayer.pieceColour);
		setWinCount(toPlayer.getWinCount());
		setLoseCount(toPlayer.getWinCount());
		setDrawCount(toPlayer.getDrawCount());
	}

	/**
	 * This returns the player string stored in the userName.
	 * 
	 * @return this player's user name
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * This returns the number stored in the winCount.
	 * 
	 * @return this player's counts of won games
	 */
	public int getWinCount()
	{
		return winCount;
	}

	/**
	 * This returns the number stored in the lostCount.
	 * 
	 * @return this player's counts of loses in games
	 */
	public int getLoseCount()
	{
		return loseCount;
	}

	/**
	 * This returns the number stored in the DrawCount.
	 * @return
	 */
	public int getDrawCount()
	{
		return drawCount;
	}

	/**
	 * @return
	 */
	public Piece getPieceColour()
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

	public ArrayList<String> getPlayerStats()
	{
		ArrayList<String> pStats = new ArrayList<String>();
		pStats.add("Username: " + getUserName());
		pStats.add("Wins: " + getWinCount());
		pStats.add("Loses: " + getLoseCount());
		pStats.add("Draws: " + getDrawCount());
		// TODO fix the following line once you can
		pStats.add("Colour: " + pieceColour);
		return pStats;
	}

	/*
	 * Methods to increment the counts of win, lose, and draw amounts
	 */

	public void incrementWinCount()
	{
		winCount++;
	}

	public void incrementLoseCount()
	{
		loseCount++;
	}

	public void incrementDrawCount()
	{
		drawCount++;
	}

}
