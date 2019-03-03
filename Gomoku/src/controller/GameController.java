package controller;

import broker.Game;

public abstract class GameController
{

    // TODO @Aahil make private variable private
    // TODO @Aahil remove roundCount (controllers will keep track of this)
    private static Game game;
    private static int roundCount;

    public int getRoundCount()
    {
	return roundCount;
    }

    public Game getGame()
    {
	return game;
    }

    public void setGame(Game toGame)
    {
	game = toGame;
    }

    /**
     * @param roundCount
     */
    public static void setRoundCount(int toRoundCount)
    {
	roundCount = toRoundCount;
    }

    /**
     * Call this method to increment the value for round count.
     */
    public static void incrementRoundCount()
    {
	roundCount++;
    }

    /**
     * This method will be invoked by the gui to indicate to the controller that
     * the user wants to play an other round.
     */
    protected abstract void playAnotherRound();

    /**
     * This method will handle any request by the GUI to place a piece on the
     * board at the given coordinates.
     * 
     * @param x is a variable of type int that represents the row (x coordinate)
     *          of the square on the board.
     * @param y is a variable of type int that represents the column (y
     *          coordinate) of the square on the board.
     */
    protected abstract void playMove(int x, int y);

}
