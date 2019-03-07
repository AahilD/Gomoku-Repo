package controller;

import java.util.ArrayList;
import java.util.Random;

import broker.Board;
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
    private int level;

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
	 * just pass the players into the game constructor
	 */

    	//print(?) a statement to ask to play another round  - maybe use int to indicate
    	//bc can't compare string accurately - mentioned in class
    	// yes = 1 
    	// no = 0
    	//if (yes)
    		//roundcount++ 
    		//newGame()
    	//else
    		//endgame() 
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
	// TODO @Emily yes of course I will have the gui pass in an int that
	// will exactly equal to level in the method name
	//note: make global variable
	// FYI: perhaps using swtich {case #: methodcall_lvl_#(); break; case:
	// #...} would make for cleaner coding style. If you are not familiar
	// with switchcases I will provde an example in comments. I don't want
	// to change your code, just change the reference value to be an int
	// instead of a string.

	/**
	 * switch case statement example
	 * 
	 * switch(lvl) { case 0: environment_lvl_zero(); break; case 1:
	 * environment_lvl_one(); break; case 2: environment_lvl_two(); break; }
	 * 
	 * Note: you can keep if statements if you so chose, I just thought I
	 * would share with you a neat alternative. :) you chose what you
	 * prefer.
	 * 
	 * Once you are done implementing this method remove any comments you no
	 * longer need.
	 */
	/*
	 * this method should check the level of difficulty and select the
	 * appropriate method to play a move you can use if statements or case
	 * statements or what ever you prefer. then you need to
	 * 
	 */
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
	// {
    }

    private void environment_lvl_zero()
    {
	// TODO @Emily yes playMove in the main controller is the correct
	// method.
	/*
	 * This method should randomly chose any available square on the board
	 * at random and play on that square. environment should always be
	 * Player 2.
	 */
	Random rand = new Random();
	int x = rand.nextInt(19);
	int y = rand.nextInt(19);
	// TODO @emily: Fix the followign.
	/**
	 * - you should not be making a static reference to Square - you need to
	 * getGame().getCurrentBoard() - once you have the board you can check
	 * to make sure your random coordinates target an available square to
	 * play. - once a random coordinate has succesffully found an empty
	 * square, it should attempt to play the move - to play the move you
	 * 
	 * Also: - isEmpty() is already returns a boolean checking to see if a
	 * boolean is true or false is redundant, therfore all you need to do is
	 * call the method. - you have an infinite loop here. Not sure why you
	 * are using a while loop. - you should not hard code the value 19.
	 * instead you should use the methods and variables already available to
	 * determine these dimenssions. - you
	 * 
	 * Note: you can add/remove all the methods in this class as you see fit
	 * to implement the logic. I think we should have one method that
	 * overrides playmove in the parent class that will invoke the
	 * environment to play a move. then there should be another private
	 * method for the environment to play a move (if you do this you should
	 * have this method make sure the square is available and play the move.
	 * you can throw exceptions or return booleans as you see fit. but make
	 * sure this method is reusable for the other levels keep difficulty
	 * logic in here)))
	 */

	Board b = getGame().getCurrentBoard();
	
	//if()
	//{
	  //  x = rand.nextInt(19);
	    //y = rand.nextInt(19);
	//}
	//if (Square.isEmpty() == true)
	//{
	   // playMove(x, y);
	//}

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
