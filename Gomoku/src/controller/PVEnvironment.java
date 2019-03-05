package controller;

import java.util.ArrayList;
import java.util.Random;

import broker.Square; 

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
    private ArrayList<int[]> playerMoves = new ArrayList<int[]>();
    private String level; 

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
	playMove(x, y);
    }

    private void environmentPlayMove()
    {
	// TODO @Emily implement method as per instructions
	/*
	 * this method should check the level of difficulty and select the
	 * appropriate method to play a move you can use if statements or case
	 * statements or what ever you prefer. then you need to
	 * 
	 */
    	if (level == "zero")
    	{
    		environment_lvl_zero();
    	}
    	//if (level == "one")
    	//{
    		//environment_lvl_one();
    	//}
    	//if (level == "two")
    	//{
    		//environment_lvl_two();
    	//{
    }

    private void environment_lvl_zero()
    {
	// TODO @Pending review (not too sure which method to use to play the piece) 
	/*
	 * This method should randomly chose any available square on the board
	 * at random and play on that square. environment should always be
	 * Player 2.
	 */
    	Random rand = new Random();
    	int x = rand.nextInt(19);
    	int y = rand.nextInt(19); 
    	playMove(x,y); 
    
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

}
