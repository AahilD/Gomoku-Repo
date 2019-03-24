package controller;

import broker.Game;
import broker.IllegalMove;
import broker.WinAndLosses;
import gui.AlertsAndDialogs;
import gui.MainGUI;
import gui.PlayerRegistration;
import javafx.application.Application;
import javafx.application.Platform;

/**
 * @author manu
 * 
 *         this controller class will set up the game with the players after
 *         they enter their user name and click on "start game" it will then
 *         relay the game setup to the gui.
 * 
 */
public class PVPlayer extends GameController
{

    // TODO @Aahil fix everything that broke due to changes of implementation.
    /*
     * Refer to the GameController that this class extends
     */
    public static void initializeGame(String player1name, String player2name)
    {
	initializeGame(player1name, player2name, false);
    }

    public static void playMoveAt(int x, int y)
    {
	try
	{
		playMove(x, y);
		MainGUI.updateBoardSquareButton(x, y,
				game.getTurnPlayer().getPieceColour());
		MainGUI.updateTurnCount(game.incrementPlayerTurn());
	} catch (WinAndLosses wnl)
	{
		AlertsAndDialogs aad = new AlertsAndDialogs();
		if (aad.display_newRoundConfirmationAlert(
				game.getTurnPlayer().getUserName() + " wins!"))
			{
			    playAnotherRound();
		} else
		{
			resetGame();
			    // TODO @Aahil close the main window and go back to player
			    // registration.
		}
		
		/* start of Previous code
	    if (!playMove(x, y))
	    {
		MainGUI.updateBoardSquareButton(x, y,
			game.getTurnPlayer().getPieceColour());
		MainGUI.updateTurnCount(game.incrementPlayerTurn());
	    } else
	    {
		AlertsAndDialogs aad = new AlertsAndDialogs();
		if (aad.display_newRoundConfirmationAlert(
			game.getTurnPlayer().getUserName() + " wins!"))
		{
		    playAnotherRound();
		} else
		{
		    // TODO @Aahil close the main window and go back to player
		    // registration.
		}
	    }
	    * end of previous code
	    */
		
	} catch (IllegalMove e)
	{
	    e.printStackTrace();
	}
	// TODO @Ahil
	/*
	 * see the PVE controller as a reference point. won't be exactly the
	 * same since it is not playing against thae environment
	 */
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
    private static void playAnotherRound()
    {
	game = new Game(game);
	incrementRoundCount();
	MainGUI.resetBoard(setupGameBoard());
	MainGUI.updatePlayerStatsPanel(setupPlayerStats());
	MainGUI.updateTurnCount(game.getTurnCount());
    }

}
