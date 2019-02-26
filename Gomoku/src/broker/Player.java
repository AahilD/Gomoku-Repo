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
	private char pieceColour;
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
	public Player(String toName, char toColour)
	{
		setUserName(toName);
		setPieceColour(toColour);
		//TODO use the setter methods to reduce code duplication
		// I would make all the setter methods private since we don't want to be able to make
		// any chages to Player after the instance has been initialized. be it through this constructor
		// or the copy constructor.
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
	 * 
	 * @return this player's counts of draws in games
	 */
	public int getDrawCount()
	{
		return drawCount;
	}

	/**
	 * This returns a copy of the piece stored in pieceColour.
	 * 
	 * @return this player's piece colour
	 */
	public char getPieceColour()
	{
		return pieceColour;
	}
	
	/**
	 * This returns the pieceColour's full name as a string 
	 */
	public String pieceColourToString()
	{
		String toString = "White";
		
		if (getPieceColour() == 'b')
		{
			toString = "Black";
		}
		
		return toString;
	}

	// Setter
	/**
	 * This sets the userName instance variable with a string.
	 * @param userName the string is given to the instance variable of userName
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * This sets the winCount instance variable with an integer.
	 * @param winCount the integer amount of wins is given to the 
	 * 					instance variable of winCount
	 */
	public void setWinCount(int winCount)
	{
		this.winCount = winCount;
	}

	/**
	 * This sets the loseCount instance variable with an integer.
	 * @param loseCount the integer amount of loses is given to the 
	 * 					instance variable of loseCount
	 */
	public void setLoseCount(int loseCount)
	{
		this.loseCount = loseCount;
	}

	/**
	 * This sets the drawCount instance variable with an integer.
	 * @param drawCount the integer amount if draws is given to the 
	 * 					instance variable of drawCount
	 */
	public void setDrawCount(int drawCount)
	{
		this.drawCount = drawCount;
	}

	/**
	 * This sets the piece instance variable with a copy of the original piece.
	 * @param inwardPiece the piece is given and a copy is made and placed into the instance 
	 * 					   variable of pieceColour
	 */
	private void setPieceColour(char toColour)
	{
		pieceColour = toColour;
	}

	/**
	 * This method creates an array storing player statistics and information.
	 * @return this payer's statistics and information
	 */
	public ArrayList<String> getPlayerStats()
	{
		ArrayList<String> pStats = new ArrayList<String>();
		pStats.add("Username: " + getUserName());
		pStats.add("Wins: " + getWinCount());
		pStats.add("Loses: " + getLoseCount());
		pStats.add("Draws: " + getDrawCount());
		pStats.add("Piece Colour: " + pieceColourToString());
		return pStats;
	}

	
	//Methods to increment the counts of win, lose, and draw amounts
	/**
	 * This method increments the amount in winCount by one. 
	 */
	public void incrementWinCount()
	{
		winCount++;
	}
	
	/**
	 * This method increments the amount in loseCount by one.
	 */
	public void incrementLoseCount()
	{
		loseCount++;
	}

	/**
	 * This method increments the amount in drawCount by one.
	 */
	public void incrementDrawCount()
	{
		drawCount++;
	}

}
