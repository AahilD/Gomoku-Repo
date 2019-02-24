package broker;

/**
 * @author Steven 
 * 			
 * 			This class represents the current state of
 *          the game being played
 */
public class Game
{
	// PLAYER_ONE_COLOUR_VALUE: character 
	private final char PLAYER_ONE_COLOUR_VALUE = 'w';
	// PLAYER_TWO_COLOUR_VALUE: character
	private final char PLAYER_TWO_COLOUR_VALUE = 'b';
	
	// playerOne: object<Player>
	private Player playerOne;
	// playerTwo: object<Player>
	private Player playerTwo;

	// turnCount: integer
	private int turnCount;

	// board: object<board>
	private Board currentBoard;

	/**
	 * @param gotPlayerOne Name of player one
	 * @param gotPlayerTwo Name of player two
	 */
	public Game(String gotPlayerOne, String gotPlayerTwo)
	{
		// playerOne: object<Player>
		playerOne = new Player(gotPlayerOne, PLAYER_ONE_COLOUR_VALUE);
		// playerTwo: object<Player>
		playerTwo = new Player(gotPlayerTwo, PLAYER_TWO_COLOUR_VALUE);
		resetRound();
	}

	/**
	 * Constructor to copy player data, and create a new board
	 * 
	 * @param p1 Object<Player> for player one
	 * @param p2 Object<Player> for player two
	 */
	public Game(Player p1, Player p2)
	{
		playerOne = new Player(p1);
		playerTwo = new Player(p2);
		resetRound();
	}

	/**
	 * Get object<Player> of player one
	 * 
	 * @return a copy of object<Player> playerOne
	 */
	public Player getPlayerOne()
	{
		return new Player(playerOne);
	}

	/**
	 * Get object<Player> of player two
	 * 
	 * @return a copy of Object<Player> playerTwo
	 */
	public Player getPlayerTwo()
	{
		return new Player(playerTwo);
	}

	/**
	 * Get the current turn Count
	 * 
	 * @return turnCount The number of the current turn
	 */
	public int getTurnCount()
	{
		return turnCount;
	}

	/**
	 * Get the current board in play
	 * 
	 * @return Object<Board> The current Board
	 */
	public Board getCurrentBoard()
	{
		return currentBoard;
	}

	/**
	 * Set player one
	 * 
	 * @param playerOne the object<Player> playerOne to set
	 */
	public void setPlayerOne(Player playerOne)
	{
		this.playerOne = new Player(playerOne);
	}

	/**
	 * Set player two
	 * 
	 * @param playerTwo the object<Player> playerTwo to set
	 */
	public void setPlayerTwo(Player playerTwo)
	{
		this.playerTwo = playerTwo;
	}

	/**
	 * Set the current turn count
	 * 
	 * @param turnCount the integer turnCount to set
	 */
	public void setTurnCount(int turnCount)
	{
		this.turnCount = turnCount;
	}

	/**
	 * Set the current Board
	 * 
	 * @param currentBoard the object<Board> to set
	 */
	public void setCurrentBoard(Board currentBoard)
	{
		this.currentBoard = currentBoard;
	}

	/**
	 * Creates a new empty board, and resets the turn count to 0
	 */
	private void resetRound()
	{
		setCurrentBoard(new Board());
		turnCount = 0;
	}

	/**
	 * 
	 * Places a chip on the board, and checks if it is a winning move
	 * 
	 * @param xCoordinate horizontal placement
	 * @param yCoordinate vertical placement
	 * @return			  false if move does not result in a win,
	 * 					  True if move does result in a win
	 */
	public boolean makeMove(int xCoordinate, int yCoordinate)
	{
		Square[][] currentArrayBoard = currentBoard.getBoard();
		// TODO instead of replaceing current square with new square
		// set the player value of the current square with the current turn player
		currentArrayBoard[xCoordinate][yCoordinate] = new Square(xCoordinate,
				yCoordinate);
		boolean winning = currentBoard.gameOver();
		incrementPlayerTurn();
		return winning;
	}

	/**
	 * Determine whose turn it is
	 * 
	 * @return copy of object<Player> of the player whose turn it is
	 */
	public Player getTurnPlayer()
	{
		Player playerTurn = null;
		if (turnCount == 0 || turnCount % 2 == 0)
		{
			playerTurn = new Player(playerOne);
		} else if (turnCount % 2 != 0)
		{
			playerTurn = new Player(playerTwo);
		}
		return playerTurn;
	}

	/**
	 * increases the integer turnCount
	 */
	private void incrementPlayerTurn()
	{
		turnCount += 1;
	}
}
