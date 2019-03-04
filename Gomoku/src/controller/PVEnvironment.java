package controller;

import java.util.ArrayList;
import java.util.Random;

import broker.IllegalMove;

/**
 * @author GROUP 22
 *
 *         This controller will be used for managing the Player versus
 *         environment mode.
 * 
 */
public class PVEnvironment extends GameController
{
    // TODO @Emily as per the following instructions
    /*
     * feel free to implement this class as you see fit. keep in mind:
     * 
     * 1. the environment needs to keep track of what level of difficulty has
     * been selected 2. the environment needs to keep track of each move the
     * player has played 3. depending on the difficulty level, you should call
     * the appropriate method that will play the move according to that
     * difficulty. You may need one method per difficulty. 4. the computer
     * should automatically play a move right after the player's turn was
     * succesfful. 5. for now you should try just implementing level 0 where the
     * computer selects an available square at random
     */
    private static ArrayList<int[]> playerMoves = new ArrayList<int[]>();
    private static ArrayList<int[]> environmentMoves = new ArrayList<int[]>();

    public static void initializeGame(String player1name, String player2name)
    {
	initializeGame(player1name, player2name, true);
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
	 * just plass the players into the game constructor
	 */

    }

    public static void playMoveAt(int x, int y)
    {
	// TODO @emily this method should follow the same logic as the pvp
	// environment
	/*
	 * only difference being that once player turn is over and still no
	 * winner you will call the environmentPlayMove() method to play the
	 * next move
	 * 
	 */
	try
	{
	    playMove(x, y);
	} catch (IllegalMove e)
	{
	    //call the proper methods to notify the user they have played an illegal move
	}

	// more logic here

	// once player's turn is done
	environmentPlayMove();
    }

    private static void environmentPlayMove()
    {
	// TODO @Emily implement method as per instructions
	/*
	 * this method should check the level of dificulty and select the
	 * appropriate method to play a move you can use if statements or case
	 * statements or what ever you prefer. then you need to
	 * 
	 */
	// replace the following level with the value that will be passed by
	// player registration (eventually)
	int level = 1;
	switch (level)
	{
	case 0:
	    environment_lvl_zero();
	case 1:
	    environment_lvl_one();
	    // etc
	}
    }

    private static void environment_lvl_zero()
    {
	// TODO @Emily implement level zero
	/*
	 * This method should randomly chose any available square on the board
	 * at random and play on that square. environment should always be
	 * Player 2.
	 */
    }

    private static void environment_lvl_one()
    {
	// First lets check if the player has made a move at all
	if (game.getTurnCount() == 0)
	    // we might as well just use the random feature of
	    // environment_lvl_zero
	    environment_lvl_zero();

	// on the second we can place our chip anywhere nearby
	else if (game.getTurnCount() == 1)
	{
	    int[] xy = getLastCoordinatesPlayed();
	    boolean success = false;
	    do
	    {

		int x = newRandomAdjecentPosition(xy[0]);
		int y = newRandomAdjecentPosition(xy[1]);

		try
		{
		    playMove(x, y);
		    updateGUI(x, y, game.getTurnPlayer().getPieceColour());
		} catch (IllegalMove e)
		{
		    success = false;
		}

	    } while (!success);
	} else
	{
	    // TODO I might have a few ideas for this
	    
	}

    }

    /**
     * Use this method to get the coordinates in reverse order. index 1 will be
     * the last item on the list
     * 
     * @param index of type int to indicate which position from the end of the
     *              list
     * @return primitive array of type int of the x and y coordinates at
     *         position [0] and [1] respectively.
     */
    private static int[] getCoordinatesAt(int index)
    {
	return playerMoves.get(playerMoves.size() - index);
    }

    private static void updateGUI(int x, int y, char pieceColour)
    {
	gui.updateBoardSquareButton(x, y, pieceColour);
	game.incrementPlayerTurn();
	gui.updateTurnCount(game.getTurnCount());
    }

    private static int newRandomAdjecentPosition(int coordinate)
    {
	int offset = 1;
	Random r = new Random();
	if (!r.nextBoolean())
	    offset *= - -1;

	return coordinate + offset;
    }

    private static int[] getLastCoordinatesPlayed()
    {
	return getCoordinatesAt(1);
    }

    /**
     * Get the history of each move the user has made.
     * 
     * @return the playerMoves
     */
    public ArrayList<int[]> getPlayerMoves()
    {
	return playerMoves;
    }

    /**
     * Once the player's move is successful, add the x and y coordinates to the
     * list.
     * 
     * @param playerMoves the playerMoves to set
     */
    public void addPlayerMoves(int x, int y)
    {
	int[] xy = { x, y };
	playerMoves.add(xy);
    }

    public ArrayList<int[]> getEnvironmentMoves()
    {
	return environmentMoves;
    }

    public void setEnvironmentMoves(ArrayList<int[]> environmentMoves)
    {
	this.environmentMoves = environmentMoves;
    }

}
