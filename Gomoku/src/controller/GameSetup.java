package controller;

/**
 * @author manu
 * 
 * this controller class will set up
 * the game with the players after they
 * enter their user name and click on "start game"
 * it will then relay the game setup to the gui.
 *
 */
public class GameSetup
{
	/**
	 * Call this method for the initial game setup.
	 * should be called by the GUI once both players
	 * have entered their desired user name and have clicked on
	 * "start game" button.
	 * @param player1name is the value of player one's username
	 * @param player2name is the value of plyaer two's username
	 */
	public static void setupGame(String player1name, String player2name)
	{
		// uncomment code below for you to see how the information is 
		//being sent from gui to controller
		
		//System.out.println(player1name);
		//System.out.println(player2name);
		
		// TODO initialize the board game
		public initializeGame(player1name, player2name) {
			Game game = new Game(player1name, player2name);
		}
		
		// TODO display information on respective GUI
		// you may break this up into several private methods
		// remember: 
		/*
		 * - player1 default colour will be white for now
		 * - player2 default colour will be black
		 * - use the broker objects to set up the game
		 * - send all info the mainWindow() method in MainGUI class
		 * - use the javadocs to understand how to setup the info
		 * - remember that controllers format the information and the gui
		 * - is simply supose to display it. for now you will have to 
		 * convert the ArrayList<ArrayList<square>> to ArrayList<ArrayList<Button>>
		 * for the player stats you will have to send an ArrayList of Strings for the
		 * player game stats: you can send what ever you want as I will no be doing any
		 * information checking, but each string will show up in the order you have them in the array
		 * so a good example would be to follow this order
		 * ArrayList<String> stats = new ArrayList(
		 * 		"Player 1: " + player1.getUsername(),
		 * 		"wins: " + player1.getWinCount(),
		 * 		"loses: " + player1.getLoseCount(),
		 * 		"draws: " + player1.getDrawCount(),
		 * 		"Player 2: " + ... etc);
		 * you have the freedom to set this up however you want just know each position
		 * on the array is a new line on the GUI.
		 * 
		 */
		
		// Created an ArrayList stats with player names and win/lose/draw counts
		// Blank lines included to make viewing easier
		private guiStats(player1name, player2name) {
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
		
		
		
	}
}
