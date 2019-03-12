package controller;

import broker.IllegalMove;

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
	} catch (IllegalMove e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// TODO @Ahil
	/*
	 * see the PVE controller as a reference point. won't be exactly the
	 * same since it is not playing against the environment
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
    @Override
    protected void playAnotherRound()
    {
	// TODO @Aahil implement method as per javadoc above

    }

}
