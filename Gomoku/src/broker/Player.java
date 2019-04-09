package broker;

import java.util.ArrayList;

/**
 * This class represents an object of type Player which will contain and
 * maintain all the relevant player profile.
 * 
 * @author GROUP 22 Code implementation by Leslie Nguyen
 * 
 */

public class Player
{
    // Players Name
    private String userName;

    // Colour of the players piece
    private char pieceColour;

    // The number of times the player wins
    private int winCount;

    // The number of times the player loses
    private int loseCount;

    // The number of times the player was in a draw
    private int drawCount;

    // constructors
    /**
     * This constructs a player with a user name and piece colour.
     * 
     * @param toName   The String name is given to the instance variable of
     *                 userName
     * @param toColour The value of the char is assigned to a new Piece through
     *                 the class Piece
     */
    public Player(String toName, char toColour)
    {
	setUserName(toName);
	setPieceColour(toColour);
	setWinCount(0);
	setLoseCount(0);
	setDrawCount(0);
    }

    /**
     * This constructs a copy of a player with the original player
     * 
     * @param toPlayer The toPlayer is the original Player object that is used
     *                 to create a copy
     */
    public Player(Player toPlayer)
    {
	setUserName(toPlayer.getUserName());
	setPieceColour(toPlayer.pieceColour);
	setWinCount(toPlayer.getWinCount());
	setLoseCount(toPlayer.getWinCount());
	setDrawCount(toPlayer.getDrawCount());
    }
    
    /**
     * This constructor creates a copy of Player but swaps their piece colour
     * to aid in swapping player turn
     *  
     * @param toPlayer			The player to copy
     * @param newPieceColour	The new piece colour
     */
    public Player(Player toPlayer, char newPieceColour)
    {
	setUserName(toPlayer.getUserName());
	setPieceColour(newPieceColour);
	setWinCount(toPlayer.getWinCount());
	setLoseCount(toPlayer.getWinCount());
	setDrawCount(toPlayer.getDrawCount());
    }

    /**
     * This getter returns the player string stored in the userName.
     * 
     * @return this player's user name
     */
    public String getUserName()
    {
	return userName;
    }

    /**
     * This getter returns the number stored in the winCount.
     * 
     * @return this player's count of winning games
     */
    public int getWinCount()
    {
	return winCount;
    }

    /**
     * This getter returns the number stored in the lostCount.
     * 
     * @return this player's counts of loses in games
     */
    public int getLoseCount()
    {
	return loseCount;
    }

    /**
     * This getter returns the number stored in the DrawCount.
     * 
     * @return this player's count of draws in games
     */
    public int getDrawCount()
    {
	return drawCount;
    }

    /**
     * This getter returns a copy of the piece stored in pieceColour.
     * 
     * @return this player's piece colour
     */
    public char getPieceColour()
    {
	return pieceColour;
    }

    /**
     * This method returns the pieceColour's full name as a string
     * 
     * @return full name of piece colour from character as String
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

    /**
     * This Setter sets the userName instance variable with a string.
     * 
     * @param userName The string is given to the instance variable of userName
     */
    public void setUserName(String userName)
    {
	this.userName = userName;
    }

    /**
     * This Setter sets the winCount instance variable with an integer.
     * 
     * @param winCount The integer amount of wins is given to the instance
     *                 variable of winCount
     */
    public void setWinCount(int winCount)
    {
	this.winCount = winCount;
    }

    /**
     * This setter sets the loseCount instance variable with an integer.
     * 
     * @param loseCount The integer amount of loses is given to the instance
     *                  variable of loseCount
     */
    public void setLoseCount(int loseCount)
    {
	this.loseCount = loseCount;
    }

    /**
     * This setter sets the drawCount instance variable with an integer.
     * 
     * @param drawCount The integer amount of draws is given to the instance
     *                  variable of drawCount
     */
    public void setDrawCount(int drawCount)
    {
	this.drawCount = drawCount;
    }

    /**
     * This setter sets the piece colour from a character representing that
     * colour
     * 
     * @param toColour The character representing the colour of a piece is given
     *                 to the instance variable pieceColour
     * 
     */
    private void setPieceColour(char toColour)
    {
	pieceColour = toColour;
    }

    /**
     * This method creates an ArrayList storing player statistics and
     * information.
     * 
     * @return This payer's Username, number of wins, number of loses, number of
     *         games resulting in a draw and piece colour in an ArrayList
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

    /**
     * This method increments the amount in winCount by one
     */
    public void incrementWinCount()
    {
	winCount++;
    }

    /**
     * This method increments the amount in loseCount by one
     */
    public void incrementLoseCount()
    {
	loseCount++;
    }

    /**
     * This method increments the amount in drawCount by one
     */
    public void incrementDrawCount()
    {
	drawCount++;
    }

}
