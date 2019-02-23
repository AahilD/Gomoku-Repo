package controller;

import java.util.ArrayList;

import broker.Game;
import broker.Square;
import gui.MainGUI;
import javafx.scene.control.Button;

/**
 * @author manu
 * 
 * this controller class will set up
 * the game with the players after they
 * enter their user name and click on "start game"
 * it will then relay the game setup to the gui.
 *
 *
 *	TODO rename by refractoring the class to GameController or GameManager
 *	which ever you prefer (right click on "GameSetup" > refractor > rename ... 
 *	don't just highlight and backspace)
 */
public class GameManager
{
	//TODO have a global variable of type Game
	// to store the current state of the game
	static Game game;
	static int roundCount;
	// TODO you will probably also need a variable to store the number of rounds
	// 		as this is not the responsibility of Game.java (I know it sounds counter intuitive
	//      we can fix the naming conventions later).
	// don't use conventional getters and setters GameSetup (Aka GameManager/Controller) is not an Object
	// it will be processing and aggregating all the information until somone tells it to endGame()
	// the endGame() method will terminate the entire program for now.
	// the controller
	// 
	// we will have to discus the rest you and 
	/**
	 * Call this method for the initial game setup.
	 * should be called by the GUI once both players
	 * have entered their desired user name and have clicked on
	 * "start game" button.
	 * @param player1name is the value of player one's username
	 * @param player2name is the value of plyaer two's username
	 */
	
	public static void initializeGame(String player1name, String player2name) 
	{
			roundCount = 0;
			MainGUI.mainwindow(
				setupBoard(),
				setupPlayerStats(),
				roundCount,
				game.getTurnCount()
				);
	}
	
	private static ArrayList<String> setupPlayerStats()
	{
		ArrayList<String> playerboardcontent = new ArrayList<String>();
		
		for (String line : game.getPlayerOne().getPlayerStats())
			playerboardcontent.add(line);
		for(String line : game.getPlayerTwo().getPlayerStats())
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
					//TODO change the button background to the colour of the player
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
		
		ArrayList<String> stats = new ArrayList(
			"Player 1: " + player1name.getUsername(),
			"wins: " + player1name.getWinCount(),
			"loses: " + player1name.getLoseCount(),
			" ",
			"Player 2: " + player2name.getUsername(),
			"wins: " + player2name.getWinCount(),
			"loses: " + player2name.getLoseCount(),
			" ",
			"draws: " + player1name.getDrawCount());
		*/
		
		return null;

	}
	
	//Add win/lose/draw to players
	public static void updateStats()
	{
		
	}	
	
	
	

	public static void playMove(int x, int y)
	{
		// TODO Auto-generated method stub

	}
	
	
	//Reset board
	//Reset turn count to 0
	public static void playAnotherRound() 
	{
		
		
		
	}
	
	
	
}
