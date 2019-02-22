package broker;

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

	// round: integer
	private int currentRound;
	// TODO also add in turnCount to keep track of how many turns (total) in the
	// current round
	// this should get reset to 0 on a new round

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
		newGame();
	}

	/*
	 * TODO sorry I did not conceive the concept of this object all that well
	 * 
	 * 1) looks like the default constructor is in good shape 2) I don't think
	 * we need a copy constructor, I'm just commenting it out for now, don't
	 * remove it yet.
	 */

	/**
	 * TODO Dont have this as a constructor instead, call it public void
	 * newMatch() it should: 1) copy over current players (this is good, but
	 * Player Class needs a copy constructor now) 2) take in the current round
	 * count as a parameter and increment the roundCount 3) reset the board
	 * (this is good, but you are calling a method that is also reseting the
	 * roundCount) instead it should reset the turnCount once you've implemented
	 * this attribute
	 * 
	 * @param newGame
	 */
	public Game(Game newGame)
	{
		playerOne = new Player(playerOne);
		playerTwo = new Player(playerTwo);
		newGame();
	}

	/**
	 * TODO make this private
	 */
	public void newGame()
	{
		currentBoard = new Board();
		currentRound = 0;
	}

	// TODO make this private
	public void incrementRound()
	{
		currentRound += 1;
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
		boolean winning = currentBoard.hasWon(currentPlayer.getPiece());
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
		// TODO implement this method
		// should return a copy of the Player
		return null;
	}

	/**
	 * call this method to switch who's turn it is. and increment turnCount here
	 * instead.
	 * 
	 * 
	 */
	private void switchPlayerTurn()
	{
		// TODO implement this method
	}
}
