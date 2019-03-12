package controller;

import java.util.ArrayList;
import java.util.Random;

import com.sun.media.jfxmediaimpl.platform.Platform;
import com.sun.webkit.ThemeClient;

import broker.Board;
import broker.Game;
import broker.IllegalMove;
import broker.Square;
import gui.AlertsAndDialogs;
import gui.MainGUI;

/**
 * @author GROUP 22
 *
 *         This controller will be used for managing the Player versus
 *         environment mode.
 * 
 */
public class PVEnvironment extends GameController
{

    private static ArrayList<int[]> playerMoveHistory;
    private static int level;
    private static String ENVIRONMENT_USERNAME = "Big Blue";

    /**
     * If you are initializing a Player Versus Environment session, call this
     * method to setup the game for the first time. (To initialize a Player v
     * Player session, call the same method in the PvPController instead; do not
     * use the one in GameController).
     * 
     * @param player1name is a string value that represents the username for
     *                    player 1
     */
    public static void initializeGame(String player1name)
    {

	initializeGame(player1name, ENVIRONMENT_USERNAME, true);
	// TODO @Anyone:
	/*
	 * once the level selector feature has been added in the player
	 * registration form, make sure to update this method to also pass in an
	 * argument int environmentLevel and initialize the value here.
	 */
	playerMoveHistory = new ArrayList<int[]>();
	level = 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see controller.GameController#playAnotherRound()
     */
    @Override
    protected void playAnotherRound()
    {
	// @Emily
	/*
	 * Us an alert to prompt the user to play an other round if so increment
	 * round count and set up a new game() use the constructor that will
	 * swap the piece colours. keep the same players though. I think you can
	 * just pass the players into the game constructor
	 */

	// print(?) a statement to ask to play another round - maybe use int to
	// indicate
	// because can't compare string accurately - mentioned in class, don't
	// know if I should make another
	// global variable
	// yes = 1
	// no = 2
	// if (yes)
	// roundcount++
	// newGame()
	// else
	// endgame()
    }

    /**
     * The GUI should call this method to play the move requested by the user.
     * then it will prompt the environment to make a move immediately afterwards
     * based on the level setting the user selected for this PVE session.
     * 
     * @param x is the (n)th row (starting at 0) of the selected square the user
     *          requested to place the piece on.
     * @param y is the (n)th column (starting at 0) of the selected square the
     *          user requested to place the piece on.
     */
    public static void playMoveAt(int x, int y)
    {
	char currentcolour = game.getTurnPlayer().getPieceColour();
	try
	{
	    if (!playMove(x, y))
	    {
		MainGUI.updateBoardSquareButton(x, y, currentcolour);
		MainGUI.updateTurnCount(game.incrementPlayerTurn());
		addPlayerMoves(x, y);
		environmentPlayTurn();
	    } else
	    {
		AlertsAndDialogs aad = new AlertsAndDialogs();
		if (aad.display_newRoundConfirmationAlert(
			"You Win, You must have cheated. :("))
		{
		    playAnOtherRound();
		} else
		{
		    // TODO Emmanuel figure out how to go back to player
		    // registration screen.
		}
	    }

	} catch (IllegalMove e)
	{

	}
    }

    /**
     * This private method will determine which method will be executed to play
     * a move based on the level selected. This method acts like a switch board.
     */
    public static void environmentPlayTurn()
    {
	if (level == 0)
	{
	    environment_lvl_zero();
	}
	// if (level == 1)
	// {
	// environment_lvl_one();
	// }
	// if (level == 2)
	// {
	// environment_lvl_two();
	// }
    }

    private static void playAnOtherRound()
    {

	game = new Game(game);
	incrementRoundCount();
	MainGUI.resetBoard(setupGameBoard());
	MainGUI.updatePlayerStatsPanel(setupPlayerStats());
	MainGUI.updateTurnCount(game.getTurnCount());
	if (game.getTurnPlayer().getUserName() == ENVIRONMENT_USERNAME)
	    environmentPlayTurn();
    }

    /**
     * This method contains the logic for environment level 0. Level 0 does not
     * attempt to play on any kind of strategy and attempts to play on random
     * coordinates. If the environment plays an illegal move it will simply
     * generate a new random location until it finally plays a succesful move.
     * 
     */
    private static void environment_lvl_zero()
    {
	Random rand = new Random();
	int x = -1;
	int y = -1;
	boolean environmentMoveSuccesful = false;
	do
	{
	    x = rand.nextInt(19);
	    y = rand.nextInt(19);
	    environmentMoveSuccesful = environmentPlayMoveAt(x, y);

	} while (!environmentMoveSuccesful);

	try
	{
	    Thread.sleep(1000);
	} catch (InterruptedException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	MainGUI.updateBoardSquareButton(x, y,
		game.getTurnPlayer().getPieceColour());
	MainGUI.updateTurnCount(game.incrementPlayerTurn());

    }

    private static boolean environmentPlayMoveAt(int x, int y)
    {
	boolean moveSuccessful = false;
	try
	{
	    if (!game.makeMove(x, y))
	    {

		moveSuccessful = true;

	    } else
	    {
		AlertsAndDialogs aad = new AlertsAndDialogs();
		aad.display_newRoundConfirmationAlert("You LOOSE!!! :P");
	    }

	} catch (IllegalMove e)
	{
	    moveSuccessful = false;
	}

	return moveSuccessful;
    }

    /**
     * Get the history of each move the user has made.
     * 
     * @return the playerMoves
     */
    public ArrayList<int[]> getPlayerMoves()
    {
	return playerMoveHistory;
    }

    /**
     * Once the player's move is successful, add the x and y coordinates to the
     * list.
     * 
     * @param playerMoves the playerMoves to set
     */
    public static void addPlayerMoves(int x, int y)
    {
	int[] xy = { x, y };
	playerMoveHistory.add(xy);
    }

    public int[] getPlayersLastMove()
    {
	return playerMoveHistory.get(playerMoveHistory.size() - 1);
    }

}
