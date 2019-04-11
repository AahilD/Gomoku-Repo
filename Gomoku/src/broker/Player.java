package broker;

import java.util.ArrayList;

/**
 * This class represents an object of type Player which will contain and
 * maintain all the relevant player profile info, such as name, piece colour,
 *  and the number of wins, losses, and draws the player has.
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
     * Construct a player with a user name and piece colour, and set wins, losses,
     * and draws to 0.
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
     * Construct a copy of a player with the original player.
     * 
     * @param toPlayer The Player object to create a copy of.
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
     * Construct a copy of Player but swaps the piece colour
     * to aid in swapping which player is player one.
     *  
     * @param toPlayer			The player to copy.
     * @param newPieceColour	The new piece colour.
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
     *Returns the player string stored in the userName.
     * 
     * @return This player's user name.
     */
    public String getUserName()
    {
	return userName;
    }

    /**
     * Returns the number stored in the winCount.
     * 
     * @return This player's count of winning games.
     */
    public int getWinCount()
    {
	return winCount;
    }

    /**
     * Returns the number stored in the lostCount.
     * 
     * @return This player's counts of loses in games.
     */
    public int getLoseCount()
    {
	return loseCount;
    }

    /**
     * Returns the number stored in the DrawCount.
     * 
     * @return This player's count of draws in games.
     */
    public int getDrawCount()
    {
	return drawCount;
    }

    /**
     * Returns a copy of the piece stored in pieceColour.
     * 
     * @return This player's piece colour.
     */
    public char getPieceColour()
    {
	return pieceColour;
    }

    /**
     * Returns the pieceColour's full name as a string. 'w' is White, and 'b' is Black.
     * 
     * @return full name of piece colour from character as String.
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
     * Sets the userName instance variable with a string.
     * 
     * @param userName A String that is the players user name.
     */
    public void setUserName(String userName)
    {
	this.userName = userName;
    }

    /**
     * Sets the winCount instance variable with an integer.
     * 
     * @param winCount An integer number of wins.
     */
    public void setWinCount(int winCount)
    {
	this.winCount = winCount;
    }

    /**
     * Sets the loseCount instance variable with an integer.
     * 
     * @param loseCount An integer number of losses.
     */
    public void setLoseCount(int loseCount)
    {
	this.loseCount = loseCount;
    }

    /**
     * Sets the drawCount instance variable with an integer.
     * 
     * @param drawCount An integer number of draws.
     */
    public void setDrawCount(int drawCount)
    {
	this.drawCount = drawCount;
    }

    /**
     * Sets the piece colour from a character representing that colour. 'w' is a White piece,
     * and 'b' is a Black piece.
     * 
     * @param toColour A char representing the colour of a piece.
     * 
     */
    private void setPieceColour(char toColour)
    {
	pieceColour = toColour;
    }

    /**
     * Create an ArrayList storing player Username, wins, losses, draws, and piece colour.
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
     * Increments the amount in winCount by one.
     */
    public void incrementWinCount()
    {
	winCount++;
    }

    /**
     * Increments the amount in loseCount by one.
     */
    public void incrementLoseCount()
    {
	loseCount++;
    }

    /**
     * Increments the amount in drawCount by one.
     */
    public void incrementDrawCount()
    {
	drawCount++;
    }

}
