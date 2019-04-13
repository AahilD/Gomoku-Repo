package controller;

import broker.Game;
import broker.IllegalMove;
import broker.WinAndLosses;
import gui.MainGUI;

/**
 * This controller class will set up the game with the players after
 * they enter their user name and click on "start game" it will then
 * relay the game setup to the gui.
 *         
 * @author manu
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
	    MainGUI.displayWinnerAndLoser(wnl.toString());

	} catch (IllegalMove e)
	{
	    e.printStackTrace();
	}

    }

    /**
     * Call this method to start a new round, creating a new board, updating player states,
     * and swapping who is player one and who is player two.
     */
    public static void playAnotherRound()
    {
	game = new Game(game);
	incrementRoundCount();
	MainGUI.resetBoard(setupGameBoard());
	MainGUI.updatePlayerStatsPanel(setupPlayerStats());
	MainGUI.updateRoundCount_ResetTurnCount(getRoundCount());
    }

}
