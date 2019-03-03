package controller;

import java.util.ArrayList;

import broker.Game;
import broker.IllegalMove;
import gui.MainGUI;
import javafx.scene.control.Button;

/**
 * @author manu
 * 
 *         this controller class will set up the game with the players after
 *         they enter their user name and click on "start game" it will then
 *         relay the game setup to the gui.
 * 
 */
public class PVPlayerContoller
{
    // TODO @Aahil make private variable private
    // TODO @Aahil remove roundCount (controllers will keep track of this)
    static Game game;
    static int roundCount;
    static int turnCount;

    /**
     * Call this method for the initial game setup. should be called by the GUI
     * once both players have entered their desired user name and have clicked
     * on "start game" button.
     * 
     * @param player1name is the value of player one's username
     * @param player2name is the value of plyaer two's username
     */
    public static void initializeGame(String player1name, String player2name)
    {
	roundCount = 0;
	game = new Game(player1name, player2name);
	MainGUI.initMainWindow(setupBoard(), setupPlayerStats(), roundCount,
		game.getTurnCount());
    }

    private static ArrayList<String> setupPlayerStats()
    {
	ArrayList<String> playerboardcontent = new ArrayList<String>();

	for (String line : game.getPlayerOne().getPlayerStats())
	    playerboardcontent.add(line);
	for (String line : game.getPlayerTwo().getPlayerStats())
	    playerboardcontent.add(line);

	return playerboardcontent;
    }

    private static ArrayList<ArrayList<Button>> setupBoard()
    {
	ArrayList<ArrayList<Button>> board = new ArrayList<ArrayList<Button>>();
	for (int x = 0; x < 19; x++)
	{
	    ArrayList<Button> column = new ArrayList<Button>();
	    for (int y = 0; y < 19; y++)
	    {
		column.add(new Button());
	    }
	    board.add(column);
	}
	return board;
    }

    /**
     * 1) this method needs to increment round count 2) use the constructor that
     * takes player objects but switch around p1 and p2 3) Set up the new board
     * and have the maingui update the board 4) set up the new player stats
     * panel and have the main gui update the player stats panel
     * 
     * Note: use the methods already implemented to help you code this I was
     * able to do it in 5 lines or so. perhaps implement a method to increment
     * round count
     */
    public static void settupNewRound()
    {
	// TODO @Aahil implement method as per javadoc above
    }

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

}
