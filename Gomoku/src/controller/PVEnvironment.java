package controller;

import java.util.ArrayList;
import java.util.Random;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import broker.Game;
import broker.IllegalMove;
import broker.Square;
import broker.WinAndLosses;
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
    private static int environmentLevel;
    private static String ENVIRONMENT_USERNAME = "Big Blue";
    private static int count = 0;

    /**
     * If you are initializing a Player Versus Environment session, call this
     * method to setup the game for the first time. (To initialize a Player v
     * Player session, call the same method in the PvPController instead; do not
     * use the one in GameController).
     * 
     * @param player1name is a string value that represents the username for
     *                    player 1
     */
    public static void initializeGame(String player1name, int toLevel)
    {

	initializeGame(player1name, ENVIRONMENT_USERNAME, true);
	playerMoveHistory = new ArrayList<int[]>();
	environmentLevel = toLevel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see controller.GameController#playAnotherRound()
     */

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
	// get the current turn player piece colour
	// not used
	// char currentcolour = game.getTurnPlayer().getPieceColour();

	try
	{
	    tryToPlayMove(x, y);

	    // append the player's move to the list
	    addPlayerMoves(x, y);
	    // have the environment play it's turn.
	    environmentPlayTurn();
	} catch (WinAndLosses wnl)
	{
	    MainGUI.displayWinnerAndLoser(wnl.toString());
	} catch (IllegalMove e)
	{

	}
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
     * @throws WinAndLosses 
     * @throws IllegalMove 
     */
    public static void playMoveAtForTextBaseApplication(int x, int y) throws IllegalMove, WinAndLosses
    {
	// get the current turn player piece colour
	// not used
	// char currentcolour = game.getTurnPlayer().getPieceColour();
	tryToPlayMoveForTextBaseApplication(x, y);

	// append the player's move to the list
	System.out.println(x);
	System.out.println(y);
	addPlayerMoves(x, y);
	
	System.out.println(game.getTurnPlayer() + "'s turn");
	// have the environment play it's turn.
	environmentPlayTurn();
    }

    /**
     * This private method will determine which method will be executed to play
     * a move based on the level selected. This method acts like a switch board.
     */
    public static void environmentPlayTurn()
    {
	if (environmentLevel == 0)
	{
	    environment_lvl_zero();
	}
	if (environmentLevel == 1)
	{
	    environment_lvl_one();
	}
	if (environmentLevel == 2)
	{
	    environment_lvl_two();
	}
    }

    public static void playAnOtherRound()
    {

	game = new Game(game);
	incrementRoundCount();
	try {
	MainGUI.resetBoard(setupGameBoard());
	MainGUI.updatePlayerStatsPanel(setupPlayerStats());
	MainGUI.updateTurnCount(game.getTurnCount());
	}catch(Exception e)
	{
	    System.out.println("Begin Round #" + getRoundCount());
	    
	}

	if (game.getTurnPlayer().getUserName() == ENVIRONMENT_USERNAME)
	    environmentPlayTurn();
    }
    
    public static void playAnOtherRoundFortextBasedApplication()
    {

	game = new Game(game);
	incrementRoundCount();
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
	ArrayList<Square> availableSquareCoordinates = new ArrayList<Square>();
	Random r = new Random();

	for (Square[] row : game.getCurrentBoard().getBoard())
	{
	    for (Square sq : row)
	    {
		if (sq.isEmpty())
		{
		    availableSquareCoordinates.add(sq);
		}
	    }
	}

	if (!availableSquareCoordinates.isEmpty())
	{
	    Square sqr = availableSquareCoordinates
		    .get(r.nextInt(availableSquareCoordinates.size()));
	    if (!environmentPlayMoveAt(sqr.getX(), sqr.getY()))
	    {
		environment_lvl_zero(); // just a fail safe
	    }
	}

    }

    /**
     * @Pending This method contains the logic for environment level 1. Level 1
     *          will utilize an arraylist of player 1's move history, starting
     *          from the most recent move to find a place to place their token ,
     *          which should be in one of the surrounding boxes. If no spaces
     *          are found based on player 1's tokens, then environment level 0
     *          is implemented.
     */

    private static void environment_lvl_one()
    {
	boolean environmentMoveSuccesful = false;

	ArrayList<int[]> moves = getPlayerMoves();

	// in case the last move played has no empty square keep moving down the
	// list
	for (int i = moves.size() - 1; i >= 0 && !environmentMoveSuccesful; i--)
	{
	    int[] move = moves.get(i);

	    environmentMoveSuccesful = environmentPlayMoveAt(move[0] + 1,
		    move[1] + 1);

	    if (!environmentMoveSuccesful)
	    {
		environmentMoveSuccesful = environmentPlayMoveAt(move[0] - 1,
			move[1] + 1);
		if (!environmentMoveSuccesful)
		{
		    environmentMoveSuccesful = environmentPlayMoveAt(
			    move[0] + 1, move[1] - 1);
		    if (!environmentMoveSuccesful)
		    {
			environmentMoveSuccesful = environmentPlayMoveAt(
				move[0], move[1] + 1);
			if (!environmentMoveSuccesful)
			{
			    environmentMoveSuccesful = environmentPlayMoveAt(
				    move[0] + 1, move[1]);
			    if (!environmentMoveSuccesful)
			    {
				environmentMoveSuccesful = environmentPlayMoveAt(
					move[0] - 1, move[1]);
				if (!environmentMoveSuccesful)
				{
				    environmentMoveSuccesful = environmentPlayMoveAt(
					    move[0], move[1] - 1);
				    if (!environmentMoveSuccesful)
				    {
					environmentMoveSuccesful = environmentPlayMoveAt(
						move[0] - 1, move[1] - 1);
				    }
				}

			    }
			}
		    }
		}
	    }
	}
	// if all else fails have environment lvl zero find an empty square!
	if (!environmentMoveSuccesful)
	{
	    environment_lvl_zero();
	}
    }

    private static void environment_lvl_two()
    {
	boolean environmentMoveSuccesful = false;

	int aiPlayMoveAtx = 0;
	int aiPlayMoveAty = 0;

	ArrayList<int[]> moves = getPlayerMoves();

	int i = moves.size();

	// TODO @Aahil for the first move just call on environment level 1
	// don't duplicate code

	if (!environmentMoveSuccesful)
	{
	    if (count == 0)
	    {
		environment_lvl_one();
		count++;
	    }

	    else if (count < i)
	    {
		int[] secondLastMove = moves.get(count - 1);
		int[] lastMove = moves.get(count);

		// Horizontal Block
		if (secondLastMove[0] == lastMove[0])
		{
		    if (!environmentMoveSuccesful
			    && (secondLastMove[1] - lastMove[1] == 1))
		    {
			environmentMoveSuccesful = environmentPlayMoveAt(
				lastMove[0], lastMove[1] + 1);
			if (!environmentMoveSuccesful)
			{
			    environment_lvl_one();
			}
		    }

		    else if (!environmentMoveSuccesful
			    && (secondLastMove[1] - lastMove[1] == -1))
		    {
			environmentMoveSuccesful = environmentPlayMoveAt(
				lastMove[0], lastMove[1] + 1);
			if (!environmentMoveSuccesful)
			{
			    environment_lvl_one();
			}
		    }

		    else if (!environmentMoveSuccesful
			    && ((secondLastMove[1] - lastMove[1]) % 2 == 0)
			    && secondLastMove[1] > lastMove[1])
		    {
			aiPlayMoveAty = (secondLastMove[1] + lastMove[1]) / 2;
			environmentMoveSuccesful = environmentPlayMoveAt(
				lastMove[0], aiPlayMoveAty);
			if (!environmentMoveSuccesful)
			{
			    environment_lvl_one();
			}
		    }

		    else if (!environmentMoveSuccesful
			    && ((lastMove[1] - secondLastMove[1]) % 2 == 0))
		    {
			aiPlayMoveAty = (lastMove[1] + secondLastMove[1]) / 2;
			environmentMoveSuccesful = environmentPlayMoveAt(
				lastMove[0], aiPlayMoveAty);
			if (!environmentMoveSuccesful)
			{
			    environment_lvl_one();
			}
		    }

		    else if (!environmentMoveSuccesful
			    && ((secondLastMove[1] - lastMove[1]) % 2 != 0))
		    {
			aiPlayMoveAty = (secondLastMove[1] + lastMove[1] - 1)
				/ 2;
			environmentMoveSuccesful = environmentPlayMoveAt(
				lastMove[0], aiPlayMoveAty);
			if (!environmentMoveSuccesful)
			{
			    environment_lvl_one();
			}
		    }
		}

		// Vertical Block
		else if (secondLastMove[1] == lastMove[1])
		{
		    if (!environmentMoveSuccesful
			    && (secondLastMove[0] - lastMove[0] == 1))
		    {
			environmentMoveSuccesful = environmentPlayMoveAt(
				lastMove[0] + 1, lastMove[1]);
			if (!environmentMoveSuccesful)
			{
			    environment_lvl_one();
			}
		    }

		    else if (!environmentMoveSuccesful
			    && (secondLastMove[0] - lastMove[0] == -1))
		    {
			environmentMoveSuccesful = environmentPlayMoveAt(
				lastMove[0] + 1, lastMove[1]);
			if (!environmentMoveSuccesful)
			{
			    environment_lvl_one();
			}
		    }

		    else if (!environmentMoveSuccesful
			    && ((secondLastMove[0] - lastMove[0]) % 2 == 0)
			    && secondLastMove[0] > lastMove[0])
		    {
			aiPlayMoveAtx = (secondLastMove[0] + lastMove[0]) / 2;
			environmentMoveSuccesful = environmentPlayMoveAt(
				aiPlayMoveAtx, lastMove[1]);
			if (!environmentMoveSuccesful)
			{
			    environment_lvl_one();
			}
		    }

		    else if (!environmentMoveSuccesful
			    && ((lastMove[0] - secondLastMove[0]) % 2 == 0))
		    {
			aiPlayMoveAtx = (lastMove[0] + secondLastMove[0]) / 2;
			environmentMoveSuccesful = environmentPlayMoveAt(
				aiPlayMoveAtx, lastMove[1]);
			if (!environmentMoveSuccesful)
			{
			    environment_lvl_one();
			}
		    }

		    else if (!environmentMoveSuccesful
			    && ((secondLastMove[0] - lastMove[0]) % 2 != 0))
		    {
			aiPlayMoveAtx = (secondLastMove[0] + lastMove[0] - 1)
				/ 2;
			environmentMoveSuccesful = environmentPlayMoveAt(
				aiPlayMoveAtx, lastMove[1]);
			if (!environmentMoveSuccesful)
			{
			    environment_lvl_one();
			}
		    }
		}

		// Diagnal top left - bottom right
		else if (secondLastMove[0] - lastMove[0] == secondLastMove[1]
			- lastMove[1])
		{
		    if (!environmentMoveSuccesful
			    && secondLastMove[0] > lastMove[0])
		    {
			if (!environmentMoveSuccesful
				&& secondLastMove[0] - lastMove[0] == 1)
			{
			    environmentMoveSuccesful = environmentPlayMoveAt(
				    lastMove[0] - 1, lastMove[1] - 1);
			    if (!environmentMoveSuccesful)
			    {
				environment_lvl_one();
			    }
			}

			else if (!environmentMoveSuccesful
				&& (secondLastMove[0] - lastMove[0]) % 2 == 0)
			{
			    aiPlayMoveAtx = (secondLastMove[0] + lastMove[0])
				    / 2;
			    aiPlayMoveAty = (secondLastMove[1] + lastMove[1])
				    / 2;
			    environmentMoveSuccesful = environmentPlayMoveAt(
				    aiPlayMoveAtx, aiPlayMoveAty);
			    if (!environmentMoveSuccesful)
			    {
				environment_lvl_one();
			    }
			}

			else if (!environmentMoveSuccesful
				&& (secondLastMove[0] - lastMove[0]) % 2 != 0)
			{
			    aiPlayMoveAtx = (secondLastMove[0] + lastMove[0]
				    + 1) / 2;
			    aiPlayMoveAty = (secondLastMove[1] + lastMove[1]
				    + 1) / 2;
			    environmentMoveSuccesful = environmentPlayMoveAt(
				    aiPlayMoveAtx, aiPlayMoveAty);
			    if (!environmentMoveSuccesful)
			    {
				environment_lvl_one();
			    }
			}

		    }

		    else if (!environmentMoveSuccesful
			    && secondLastMove[0] < lastMove[0])
		    {
			if (!environmentMoveSuccesful
				&& secondLastMove[0] - lastMove[0] == -1)
			{
			    environmentMoveSuccesful = environmentPlayMoveAt(
				    lastMove[0] + 1, lastMove[1] + 1);
			    if (!environmentMoveSuccesful)
			    {
				environment_lvl_one();
			    }
			}

			else if (!environmentMoveSuccesful
				&& (lastMove[0] - secondLastMove[0]) % 2 == 0)
			{
			    aiPlayMoveAtx = (secondLastMove[0] + lastMove[0])
				    / 2;
			    aiPlayMoveAty = (secondLastMove[1] + lastMove[1])
				    / 2;
			    environmentMoveSuccesful = environmentPlayMoveAt(
				    aiPlayMoveAtx, aiPlayMoveAty);
			    if (!environmentMoveSuccesful)
			    {
				environment_lvl_one();
			    }
			}

			else if (!environmentMoveSuccesful
				&& (lastMove[0] - secondLastMove[0]) % 2 != 0)
			{
			    aiPlayMoveAtx = (secondLastMove[0] + lastMove[0]
				    + 1) / 2;
			    aiPlayMoveAty = (secondLastMove[1] + lastMove[1]
				    + 1) / 2;
			    environmentMoveSuccesful = environmentPlayMoveAt(
				    aiPlayMoveAtx, aiPlayMoveAty);
			    if (!environmentMoveSuccesful)
			    {
				environment_lvl_one();
			    }
			}

		    }
		}

		// Diagnal top right - bottom left
		else if (secondLastMove[0] - lastMove[0] != secondLastMove[1]
			- lastMove[1])
		{
		    if (!environmentMoveSuccesful
			    && secondLastMove[0] > lastMove[0])
		    {
			if (!environmentMoveSuccesful
				&& secondLastMove[0] - lastMove[0] == 1)
			{
			    environmentMoveSuccesful = environmentPlayMoveAt(
				    lastMove[0] - 1, lastMove[1] + 1);
			    if (!environmentMoveSuccesful)
			    {
				environment_lvl_one();
			    }
			}

			else if (!environmentMoveSuccesful
				&& (secondLastMove[0] - lastMove[0]) % 2 == 0)
			{
			    aiPlayMoveAtx = (secondLastMove[0] + lastMove[0])
				    / 2;
			    aiPlayMoveAty = (secondLastMove[1] + lastMove[1])
				    / 2;
			    environmentMoveSuccesful = environmentPlayMoveAt(
				    aiPlayMoveAtx, aiPlayMoveAty);
			    if (!environmentMoveSuccesful)
			    {
				environment_lvl_one();
			    }
			}

			else if (!environmentMoveSuccesful
				&& (lastMove[0] - secondLastMove[0]) % 2 != 0)
			{
			    aiPlayMoveAtx = (secondLastMove[0] + lastMove[0]
				    + 1) / 2;
			    aiPlayMoveAty = (secondLastMove[1] + lastMove[1]
				    - 1) / 2;
			    environmentMoveSuccesful = environmentPlayMoveAt(
				    aiPlayMoveAtx, aiPlayMoveAty);
			    if (!environmentMoveSuccesful)
			    {
				environment_lvl_one();
			    }
			}

		    }

		    else if (!environmentMoveSuccesful
			    && secondLastMove[0] < lastMove[0])
		    {

			if (!environmentMoveSuccesful
				&& secondLastMove[0] - lastMove[0] == 1)
			{
			    environmentMoveSuccesful = environmentPlayMoveAt(
				    lastMove[0] + 1, lastMove[1] - 1);
			    if (!environmentMoveSuccesful)
			    {
				environment_lvl_one();
			    }
			}

			else if (!environmentMoveSuccesful
				&& (lastMove[0] - secondLastMove[0]) % 2 == 0)
			{
			    aiPlayMoveAtx = (secondLastMove[0] + lastMove[0])
				    / 2;
			    aiPlayMoveAty = (secondLastMove[1] + lastMove[1])
				    / 2;
			    environmentMoveSuccesful = environmentPlayMoveAt(
				    aiPlayMoveAtx, aiPlayMoveAty);
			    if (!environmentMoveSuccesful)
			    {
				environment_lvl_one();
			    }
			}

			else if (!environmentMoveSuccesful
				&& (lastMove[0] - secondLastMove[0]) % 2 != 0)
			{
			    aiPlayMoveAtx = (secondLastMove[0] + lastMove[0]
				    + 1) / 2;
			    aiPlayMoveAty = (secondLastMove[1] + lastMove[1]
				    - 1) / 2;
			    environmentMoveSuccesful = environmentPlayMoveAt(
				    aiPlayMoveAtx, aiPlayMoveAty);
			    if (!environmentMoveSuccesful)
			    {
				environment_lvl_one();
			    }
			}

		    }

		}

		else if (!environmentMoveSuccesful)
		{
		    environment_lvl_one();
		}

		count++;
	    }
	}
    }

    private static void tryToPlayMove(int x, int y)
	    throws IllegalMove, WinAndLosses
    {
	playMove(x, y);
	try {
	MainGUI.updateBoardSquareButton(x, y,
		game.getTurnPlayer().getPieceColour());
	MainGUI.updateTurnCount(game.incrementPlayerTurn());
	}catch(ExceptionInInitializerError e)
	{
	    game.incrementPlayerTurn();
	    System.out.println("turn " + game.getTurnCount());
	    System.out.println(game.getTurnPlayer().getUserName() + "'s turn!");
	}catch (NoClassDefFoundError e) {
	    game.incrementPlayerTurn();
	    System.out.println("turn " + game.getTurnCount());
	    System.out.println(game.getTurnPlayer().getUserName() + "'s turn!");
	}
    }

    private static void tryToPlayMoveForTextBaseApplication(int x, int y)
	    throws IllegalMove, WinAndLosses
    {
	playMove(x, y);
	game.incrementPlayerTurn();
    }
    
    private static boolean environmentPlayMoveAt(int x, int y)
    {
	boolean moveSuccessful = false;

	try
	{
	    tryToPlayMove(x, y);
	    moveSuccessful = true;

	} catch (WinAndLosses wnl)
	{
	    AlertsAndDialogs aad = new AlertsAndDialogs();

	    if (aad.display_newRoundConfirmationAlert(wnl.toString()))
	    {
		playAnOtherRound();
	    } else
	    {
		// if they do not wish to play an other round just close the
		// application.
		try {
		MainGUI.closeApplication();
		}catch(Exception e)
		{
		    System.out.println("Goodbye!");
		}
	    }

	} catch (IllegalMove e)
	{
	    // do nothing here the environment will keep trying till
	    // moveSuccessful == true
	    // this is essentially
	}

	return moveSuccessful;
    }

    /**
     * Get the history of each move the user has made.
     * 
     * @return the playerMoves
     */
    public static ArrayList<int[]> getPlayerMoves()
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

    public static int[] getPlayersLastMove()
    {
	return playerMoveHistory.get(playerMoveHistory.size() - 1);
    }

    public static void initializeGameForTextBaseApplication(String username, int level)
    {
	game = new Game(username, "Big Blue");
	playerMoveHistory = new ArrayList<int[]>();
	environmentLevel = level;
	setRoundCount(0);
    }
}
