package controller;

import java.util.ArrayList;
import broker.Game;
import broker.IllegalMove;
import gui.MainGUI;
import javafx.scene.control.Button;

public abstract class GameController
{

    // TODO @Aahil make private variable private
    // TODO @Aahil remove roundCount (controllers will keep track of this)
    private static Game game;
    private static int roundCount;
    
    public static void initializeGame(String player1name, String player2name, boolean isPVE)
    {

	setRoundCount(0);
	game = new Game(player1name, player2name);
	MainGUI.initMainWindow(setupEmptyBoard(), setupPlayerStats(), roundCount,
		game.getTurnCount(), isPVE);
    }
    
    public int getRoundCount()
    {
	return roundCount;
    }

    public Game getGame()
    {
	return game;
    }

    public void setGame(Game toGame)
    {
	game = toGame;
    }

    /**
     * @param roundCount
     */
    public static void setRoundCount(int toRoundCount)
    {
	roundCount = toRoundCount;
    }

    /**
     * Call this method to increment the value for round count.
     */
    public static void incrementRoundCount()
    {
	roundCount++;
    }

    /**
     * This method will be invoked by the gui to indicate to the controller that
     * the user wants to play an other round.
     */
    protected abstract void playAnotherRound();

    /**
     * This method will handle any request by the GUI to place a piece on the
     * board at the given coordinates.
     * 
     * @param x is a variable of type int that represents the row (x coordinate)
     *          of the square on the board.
     * @param y is a variable of type int that represents the column (y
     *          coordinate) of the square on the board.
     */
    public static void playMove(int x, int y)
    {
	// TODO @Aahil fix this method as per the following
		/*
		 * Instead of returning char and throwing GameOverException I would like
		 * it to: 1. call the appropriate update method in the gui by passing
		 * the x and y coordinates and the piececolour of the player that made
		 * the move if it is not the wining move. 2. if it is the wining move
		 * invoke the appropriate alert in the MainGUI class that will display
		 * the winer and loser and ask if they wish to play a new game, or not.
		 * 3. if it is an illegal move invoke the appropriate alert in the
		 * maingui class that will warn the user they have performed an illegal
		 * move.
		 * 
		 * Note: you should only increment turn count if the move is valid and
		 * not winning move. You may be required to set up as many methods to
		 * break down the code into simpler steps, or to perform seperate tasks
		 * under certain conditions. Everything you need on the GUI side is
		 * there. see the new class in the gui package called
		 * alertsanddialogs.java
		 */
		char currentcolour = game.getTurnPlayer().getPieceColour();

		try
		{
		    if (!game.makeMove(x, y))
		    {
			game.incrementPlayerTurn();

		    } else
		    {

		    }
		} catch (IllegalMove e)
		{

		    System.out.println(e.toString());
		}
    }
    
    /**
     * call this method to get an ArrayList of type <String> containing all the player stats
     * for player one and player two, aggregating them together in a single array. Player should have
     * a method that does this for each player. 
     * @return ArrayList of type <String> containing the player stats for both players
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
     * Call this method to setup a new empty 2D ArrayList of type <Button>.
     * Uses the dimentions of the board to setup the dimentions for this 2D array.
     * @return
     */
    public static ArrayList<ArrayList<Button>> setupEmptyBoard()
    {
	ArrayList<ArrayList<Button>> board = new ArrayList<ArrayList<Button>>();
	for (int x = 0; x < game.getCurrentBoard().getBoard().length; x++)
	{
	    ArrayList<Button> column = new ArrayList<Button>();
	    for (int y = 0; y < game.getCurrentBoard().getBoard()[x].length; y++)
	    {
		column.add(new Button());
	    }
	    board.add(column);
	}
	return board;
    }

}
