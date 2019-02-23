package controller;

import java.util.ArrayList;

import broker.Game;
import broker.Player;
import broker.Square;
import gui.MainGUI;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;

/**
 * @author manu
 * 
 *         this controller class will set up the game with the players after
 *         they enter their user name and click on "start game" it will then
 *         relay the game setup to the gui.
 *
 *
 *         TODO rename by refractoring the class to GameController or
 *         GameManager which ever you prefer (right click on "GameSetup" >
 *         refractor > rename ... don't just highlight and backspace)
 */
public class GameManager
{
	// TODO have a global variable of type Game
	// to store the current state of the game
	static Game game;
	static int roundCount;

	// TODO you will probably also need a variable to store the number of rounds
	// as this is not the responsibility of Game.java (I know it sounds counter
	// intuitive
	// we can fix the naming conventions later).
	// don't use conventional getters and setters GameSetup (Aka
	// GameManager/Controller) is not an Object
	// it will be processing and aggregating all the information until somone
	// tells it to endGame()
	// the endGame() method will terminate the entire program for now.
	// the controller
	//
	// we will have to discus the rest you and
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
		game = new Game(
				new Player(player1name, 'b'), new Player(player2name, 'w'));

		MainGUI.mainwindow(
				setupBoard(), setupPlayerStats(), roundCount,
				game.getTurnCount()
				);
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
		// TODO fix the following for loops to use the size of the table
		// rather than hardcoded number 19. I prefere ArrayLists, but oh well.
		for (int x = 0; x < 19; x++)
		{
			ArrayList<Button> column = new ArrayList<Button>();
			for (int y = 0; y < 19; y++)
			{
				Button button = new Button();
				Square sqr = game.getCurrentBoard().getBoard()[x][y];
				if (!sqr.isEmpty())
				{
					// TODO change the button background to the colour of the
					// player
				}
				column.add(button);
			}
			board.add(column);
		}
		return board;
	}

	public static void setupGame(String player1name, String player2name)
	{

	}

	// Created an ArrayList stats with player names and win/lose/draw counts
	// Blank lines included to make viewing easier
	private ArrayList<String> playerStats()
	{
		/*
		 * 
		 * ArrayList<String> stats = new ArrayList( "Player 1: " +
		 * player1name.getUsername(), "wins: " + player1name.getWinCount(),
		 * "loses: " + player1name.getLoseCount(), " ", "Player 2: " +
		 * player2name.getUsername(), "wins: " + player2name.getWinCount(),
		 * "loses: " + player2name.getLoseCount(), " ", "draws: " +
		 * player1name.getDrawCount());
		 */

		return null;

	}

	// Add win/lose/draw to players
	public static void updateStats()
	{

	}

	public static String playMove(int x, int y)
	{
		// the following three lines of code is just to show you how the information
		// is being processed when you do front end testing
		System.out.println("x: " + x);
		System.out.println("y: " + y);
		//TODO game should have a method to play the move
		// pass x and y through them game will work out who's turn it is
		// controller needs to find out who's turn it is before playing the move
		// as the turn will change as soon as the move has been made
		// you should return new Button with a bg colour/ image (what ever) that reflects
		// that players designated colour. don't worry about x and y on the return 
		// do not return a new button, I can't change the button itself
		// but you can return any property of button that can be set to change its physical appearance
		// you will have to look up how to do this and return the object with the desire value
		// If you decided on returning something other than string you may go ahead and make those changes in GUI
		// or flag me down so I can do it. either way. if you want to do it yourself go to
		// line 104-106 in MainGUI.java and change those lines to reflect your changes here.
		// for the gui is repsonsive as it is so you may play around with it. call me if you are stuck.
		// upon completeing this method you should have a better understanding about how
		// the information is being tossed around... I know at first everything is so confusing.
		return "P" + game.getTurnPlayer().getUserName().charAt(0);
	}

	// Reset board
	// Reset turn count to 0
	public static void playAnotherRound()
	{

	}

}
