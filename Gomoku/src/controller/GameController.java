package controller;

import java.util.ArrayList;

import broker.Game;
import broker.IllegalMove;
import broker.WinAndLosses;
import gui.MainGUI;
import javafx.application.Platform;
import javafx.scene.control.Button;
/**
 * This class controls the overall instance of A Gomoku game instance.
 * 
 * @author Aahil
 *
 */
public abstract class GameController
{
    protected static Game game;
    private static int roundCount;

    /**
     * Call this method to initialize the game controller. This method should
     * only be called on the initial game setup.
     * 
     * @param player1name Is a string value that represents the username for
     *                    player 1
     * @param player2name Is a string value that represents the username for
     *                    player 2
     * @param isPVE       Is a boolean that indicates if the controller should
     *                    enter PVE mode, if so boolean value should be true.
     *                    Otherwise false for PvP mode.
     */
    public static void initializeGame(String player1name, String player2name,
	    boolean isPVE)
    {
	setRoundCount(0);
	game = new Game(player1name, player2name);
	MainGUI.initMainWindow(setupGameBoard(), setupPlayerStats(), roundCount,
		game.getTurnCount(), isPVE);
    }

    /**
     * Call this method to get the current value for the number of rounds that
     * have been played. The round currently in session is not included in this
     * value.
     * 
     * @return roundCount	Type int representing the number of rounds that
     *        				have been played prior to the current round being played.
     */
    public static int getRoundCount()
    {
	return roundCount;
    }

    /**
     * Call this method to get the current instance of the game object.
     * 
     * @return game object instance of type Game.
     */
    public static Game getGame()
    {
	return game;
    }

    /**
     * Call this method to set an instance of Game object to this variable
     * called game.
     * 
     * @param toGame A reference to an instance of an object of type Game.
     */
    public void setGame(Game toGame)
    {
	game = toGame;
    }

    /**
     * Call this method to set the value for roundCount, which keeps tracks of
     * how many consecutive games the same two players have played against each
     * other.
     * 
     * @param toRoundCount Holds the value of type int of how many rounds the same
     *                   two players have played against each other.
     */
    public static void setRoundCount(int toRoundCount)
    {
	roundCount = toRoundCount;
    }

    /**
     * Call this method to increment the value for round count by +1, instead of
     * using the setter method every time.
     */
    public static void incrementRoundCount()
    {
	roundCount++;
    }

    /**
     * This method places a player's piece on a specified x,y-coordinate square.
     * 
     * @param x 			Is a variable of type int that represents the row (x coordinate)
     *         				of the square on the board.
     * @param y 			Is a variable of type int that represents the column (y
     *         				coordinate) of the square on the board.
     * 
     * @throws IllegalMove	If the square already has a player on it.
     * @throws WinAndLosses If move played results in a win.
     */
    public static void playMove(int x, int y) throws IllegalMove, WinAndLosses
    {
	try
	{
	    game.makeMove(x, y);
	} catch (WinAndLosses wnl)
	{
	    throw wnl;
	} catch (IllegalMove e)
	{
	    throw e;
	}
    }

    /**
     * Call this method to get an ArrayList of type String containing all the
     * player stats for player one and player two, aggregating them together in
     * a single array. Player should have a method that does this for each
     * player.
     * 
     * @return ArrayList of type String containing the player stats for both
     *         players.
     */
    public static ArrayList<String> setupPlayerStats()
    {
	ArrayList<String> playerboardcontent = new ArrayList<String>();

	for (String line : game.getPlayerOne().getPlayerStats())
	    playerboardcontent.add(line);
	for (String line : game.getPlayerTwo().getPlayerStats())
	    playerboardcontent.add(line);

	return playerboardcontent;
    }

    /**
     * Call this method to setup a new empty 2D ArrayList of type Button. Uses
     * the dimensions of the board to setup the dimensions for this 2D array.
     * 
     * @return	An ArrayList of Buttons matching the dimensions of the board.
     */
    public static ArrayList<ArrayList<Button>> setupGameBoard()
    {
	ArrayList<ArrayList<Button>> board = new ArrayList<ArrayList<Button>>();
	for (int x = 0; x < game.getCurrentBoard().getBoard().length; x++)
	{
	    ArrayList<Button> column = new ArrayList<Button>();
	    for (int y = 0; y < game.getCurrentBoard()
		    .getBoard()[x].length; y++)
	    {
		column.add(new Button());
	    }
	    board.add(column);
	}
	return board;
    }

    /**
     * Call this method to reset the board once the user(s) have confirmed to
     * play an other round
     */
    public static void resetGame()
    {
	Platform.exit();
    }

}
