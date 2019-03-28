package controller;

import java.util.ArrayList;
import java.util.Random;

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
	char currentcolour = game.getTurnPlayer().getPieceColour();

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
     * @Pending Review 1. identify player's last move (x,y) 2. loop through each
     *          square next to the coordinate (above, top right, to the right,
     *          right bottom, below, left bottom, to the left, top left) until
     *          player == null 3. place token on the available square 4. if not
     *          available, try second last move 5. keep going through the entire
     *          list of player's move
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
		int[] x = moves.get(count);
		if (!environmentMoveSuccesful)
		{
		    environmentMoveSuccesful = environmentPlayMoveAt(x[0] + 1,
			    x[1] + 1);
		} else if (!environmentMoveSuccesful)
		{
		    environmentMoveSuccesful = environmentPlayMoveAt(x[0] - 1,
			    x[1] + 1);
		} else if (!environmentMoveSuccesful)
		{
		    environmentMoveSuccesful = environmentPlayMoveAt(x[0] + 1,
			    x[1] - 1);
		} else if (!environmentMoveSuccesful)
		{
		    environmentMoveSuccesful = environmentPlayMoveAt(x[0],
			    x[1] + 1);
		} else if (!environmentMoveSuccesful)
		{
		    environmentMoveSuccesful = environmentPlayMoveAt(x[0] + 1,
			    x[1]);
		} else if (!environmentMoveSuccesful)
		{
		    environmentMoveSuccesful = environmentPlayMoveAt(x[0] - 1,
			    x[1]);
		} else if (!environmentMoveSuccesful)
		{
		    environmentMoveSuccesful = environmentPlayMoveAt(x[0],
			    x[1] - 1);
		} else if (!environmentMoveSuccesful)
		{
		    environmentMoveSuccesful = environmentPlayMoveAt(x[0] - 1,
			    x[1] - 1);
		}
		count++;

	    }

	    else if (count < i)
	    {
		int[] secondLastMove = moves.get(count - 1);
		int[] lastMove = moves.get(count);

		// TODO @Aahil so the problem is...
		/*
		 * So this is my fault, I gave poor instructions to Emily you
		 * followed her same logic.
		 * 
		 * see level 1, its weird, but it works. you need nest if
		 * statement after nested if statement
		 * 
		 * i know. its weird... but actually if you can find a way to
		 * create a private method that will can do it recursively you
		 * may do so. up to you.
		 */

		// Horizontal Block
		if (secondLastMove[0] == lastMove[0])
		{
		    if (!environmentMoveSuccesful
			    && (secondLastMove[1] - lastMove[1] == 1))
		    {
			environmentMoveSuccesful = environmentPlayMoveAt(
				lastMove[0], lastMove[1] + 1);
		    }

		    else if (!environmentMoveSuccesful
			    && (secondLastMove[1] - lastMove[1] == -1))
		    {
			environmentMoveSuccesful = environmentPlayMoveAt(
				lastMove[0], lastMove[1] + 1);
		    }

		    else if (!environmentMoveSuccesful
			    && ((secondLastMove[1] - lastMove[1]) % 2 == 0)
			    && secondLastMove[1] > lastMove[1])
		    {
			aiPlayMoveAty = (secondLastMove[1] + lastMove[1]) / 2;
			environmentMoveSuccesful = environmentPlayMoveAt(
				lastMove[0], aiPlayMoveAty);
		    }

		    else if (!environmentMoveSuccesful
			    && ((lastMove[1] - secondLastMove[1]) % 2 == 0))
		    {
			aiPlayMoveAty = (lastMove[1] + secondLastMove[1]) / 2;
			environmentMoveSuccesful = environmentPlayMoveAt(
				lastMove[0], aiPlayMoveAty);
		    }

		    else if (!environmentMoveSuccesful
			    && ((secondLastMove[1] - lastMove[1]) % 2 != 0))
		    {
			aiPlayMoveAty = (secondLastMove[1] + lastMove[1] - 1)
				/ 2;
			environmentMoveSuccesful = environmentPlayMoveAt(
				lastMove[0], aiPlayMoveAty);
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
		    }

		    else if (!environmentMoveSuccesful
			    && (secondLastMove[0] - lastMove[0] == -1))
		    {
			environmentMoveSuccesful = environmentPlayMoveAt(
				lastMove[0] + 1, lastMove[1]);
		    }

		    else if (!environmentMoveSuccesful
			    && ((secondLastMove[0] - lastMove[0]) % 2 == 0)
			    && secondLastMove[0] > lastMove[0])
		    {
			aiPlayMoveAtx = (secondLastMove[0] + lastMove[0]) / 2;
			environmentMoveSuccesful = environmentPlayMoveAt(
				aiPlayMoveAtx, lastMove[1]);
		    }

		    else if (!environmentMoveSuccesful
			    && ((lastMove[0] - secondLastMove[0]) % 2 == 0))
		    {
			aiPlayMoveAtx = (lastMove[0] + secondLastMove[0]) / 2;
			environmentMoveSuccesful = environmentPlayMoveAt(
				aiPlayMoveAtx, lastMove[1]);
		    }

		    else if (!environmentMoveSuccesful
			    && ((secondLastMove[0] - lastMove[0]) % 2 != 0))
		    {
			aiPlayMoveAtx = (secondLastMove[0] + lastMove[0] - 1)
				/ 2;
			environmentMoveSuccesful = environmentPlayMoveAt(
				aiPlayMoveAtx, lastMove[1]);
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
			}

		    }
		}
		
		//TODO @Aahil you need a catch-all statement
		/*
		 * somewhere at teh verry end of your method here you need to see if environment still failed to play a move. 
		 * if so call env_lvl_1()
		 * let it do it's magic.
		 * 
		 * everywhere in your code that could run into a dead-end so to speak, add an else{} statement
		 * or whatever and call on the level bellow it to attempt to make a move.
		 * 
		 * you will see env_level_1() calls env_lvl_0() when lvl 1 logic fails to play a move.
		 * 
		 *  that way you keep going down the latter.
		 */
		count++;
	    }
	}
    }

    private static void tryToPlayMove(int x, int y)
	    throws IllegalMove, WinAndLosses
    {
	playMove(x, y);
	MainGUI.updateBoardSquareButton(x, y,
		game.getTurnPlayer().getPieceColour());
	MainGUI.updateTurnCount(game.incrementPlayerTurn());
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
		MainGUI.closeApplication();
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

}
