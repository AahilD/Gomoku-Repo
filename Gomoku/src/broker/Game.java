package broker;

import java.util.ArrayList;

/**
 * @author GROUP22 created by Steven This class represents the current state of
 *         the game being played
 */
public class Game
{
	// TODO properly javadoc

	// playerOne: object<Player>
	private Player playerOne;
	// playerTwo: object<Player>
	private Player playerTwo;

	// turnCount: integer
	private int turnCount;

	// board: object<board>
	private Board currentBoard;

	/**
	 * @param gotPlayerOne
	 * @param gotPlayerTwo
	 */
	public Game(String gotPlayerOne, String gotPlayerTwo)
	{
		// playerOne: object<Player>
		playerOne = new Player(gotPlayerOne, 'w');
		// playerTwo: object<Player>
		playerTwo = new Player(gotPlayerTwo, 'b');
		resetRound();
	}

	/*
	 * TODO sorry I did not conceive the concept of this object all that well
	 * 
	 * 1) looks like the default constructor is in good shape 2) I don't think
	 * we need a copy constructor, I'm just commenting it out for now, don't
	 * remove it yet.
	 */

	/**
	 * TODO Don't have this as a constructor instead, call it public void
	 * newMatch() it should: 1) copy over current players (this is good, but
	 * Player Class needs a copy constructor now) 2) take in the current round
	 * count as a parameter and increment the roundCount 3) reset the board
	 * (this is good, but you are calling a method that is also reseting the
	 * roundCount) instead it should reset the turnCount once you've implemented
	 * this attribute
	 * 
	 */

	public Game()
	{
		playerOne = new Player(playerOne);
		playerTwo = new Player(playerTwo);
		resetRound();
	}

	/**
	 * @return the playerOne
	 */
	public Player getPlayerOne()
	{
		return new Player(playerTwo);
	}

	/**
	 * @return the playerTwo
	 */
	public Player getPlayerTwo()
	{
		return new Player(playerOne);
	}

	/**
	 * @return the turnCount
	 */
	public int getTurnCount()
	{
		return turnCount;
	}
	
	public Board getCurrentBoard()
	{
		return currentBoard;
	}
	
	/**
	 * @param playerOne the playerOne to set
	 */
	private void setPlayerOne(Player playerOne)
	{
		this.playerOne = playerOne;
	}

	/**
	 * @param playerTwo the playerTwo to set
	 */
	private void setPlayerTwo(Player playerTwo)
	{
		this.playerTwo = playerTwo;
	}

	/**
	 * @param turnCount the turnCount to set
	 */
	private void setTurnCount(int turnCount)
	{
		this.turnCount = turnCount;
	}
	
	public void setCurrentBoard(Board currentBoard)
	{
		this.currentBoard = currentBoard;
	}
	
	private void resetRound()
	{
		setCurrentBoard(new Board());
		turnCount = 0;
	}

	// TODO so make move needs to save the player to the square
	// I'm sorry this is confusing, at the last minute we decided to remove
	// the Piece object. so pass in the arguments: int xCoordinate, int
	// yCoordinate
	// so that board[x][y].setPlayer(getTurnPlayer):. (Note Square object will
	// have to reflect this chagne)
	// Check the attribute that will determine who's turn it is to save that
	// player to the square,
	// once the move has been made and it is not the winning move, change the
	// attribute to indicate it is
	// the other players turn.
	// on the board
	// Also you should increment turnCount.
	// makemove(Player): boolean
	// returns true if this move was the winning move
	public boolean makeMove(Player currentPlayer)
	{
		boolean winning = false;
		return winning;
	}

	/**
	 * * Note: an other way to go about it instead of using a boolean flag you
	 * could determine that: if turnCount == 0 it is p1's turn else if turnCount
	 * == 1 it is p2's turn else if turnCount is an even number it is p1's turn,
	 * else if turnCount is an odd number it is p2's turn
	 * 
	 * Call this method to get the player who's turn it is
	 * 
	 * @return
	 */
	public Player getTurnPlayer()
	{
		Player playerTurn = null;
		if (turnCount == 0 || turnCount%2 == 0){
			playerTurn = new Player(playerOne);
		} else if (turnCount%2 != 0){
			playerTurn = new Player(playerTwo);
		}
		return playerTurn;
	}

	//***
	private void incrementPlayerTurn()
	{
		turnCount += 0;
	}
}
