package broker;

/**
 * @author Group 22 Code implemented by Steven Hamilton.
 * 
 *         This class is an object of type Game, which represents 
 *         the current state of the game being played
 */
public class Game
{
	// Player colour values
	private final char PLAYER_ONE_COLOUR_VALUE = 'w';
	private final char PLAYER_TWO_COLOUR_VALUE = 'b';

	// Players
	private Player playerOne;
	private Player playerTwo;

	// The current turn number
	private int turnCount;

	// The current board in play
	private Board currentBoard;

	/**
	 * This constructor sets each player, sets turn count to 0, and creates a new board
	 * 
	 * @param gotPlayerOne	Name of player one
	 * @param gotPlayerTwo	Name of player two
	 */
	public Game(String gotPlayerOne, String gotPlayerTwo)
	{
		playerOne = new Player(gotPlayerOne, PLAYER_ONE_COLOUR_VALUE);
		playerTwo = new Player(gotPlayerTwo, PLAYER_TWO_COLOUR_VALUE);
		resetRound();
	}

	/**
	 * Constructor to copy player data, reset the turn count to 0, and create a new board
	 * 
	 * @param p1	Player one to copy
	 * @param p2	Player two to copy
	 */
	public Game(Player p1, Player p2)
	{
		playerOne = new Player(p1);
		playerTwo = new Player(p2);
		resetRound();
	}

	/**
	 * Get reference to a copy of player one
	 * 
	 * @return	A copy reference of playerOne
	 */
	public Player getPlayerOne()
	{
		return new Player(playerOne);
	}

	/**
	 * Get reference to a copy of player two
	 * 
	 * @return 	A copy reference of playerTwo
	 */
	public Player getPlayerTwo()
	{
		return new Player(playerTwo);
	}

	/**
	 * Gets the turn count for the current round
	 * 
	 * @return	The current turn count
	 */
	public int getTurnCount()
	{
		return turnCount;
	}

	/**
	 * Gets the current board in play
	 * 
	 * @return 	reference to the current Board object in play
	 */
	public Board getCurrentBoard()
	{
		return currentBoard;
	}

	/**
	 * Sets player one
	 * 
	 * @param playerOne	The desired player object to set as player one
	 */
	public void setPlayerOne(Player playerOne)
	{
		this.playerOne = new Player(playerOne);
	}

	/**
	 * Sets player two
	 * 
	 * @param playerTwo	The desired player object to set as player two
	 */
	public void setPlayerTwo(Player playerTwo)
	{
		this.playerTwo = playerTwo;
	}

	/**
	 * Sets the current turn count
	 * 
	 * @param TurnCount the desired integer turn count to set
	 */
	public void setTurnCount(int turnCount)
	{
		this.turnCount = turnCount;
	}

	/**
	 * Set the current Board
	 * 
	 * @param CurrentBoard the desired board object to set
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
	 * Makes the players move by assigning the desired square on the board to a player if 
	 * there is not already another player assigned to it, and determines if the move 
	 * resulted in a win.
	 * 
	 * @param xCoordinate 	Horizontal placement
	 * @param yCoordinate 	Vertical placement
	 * @return 				False if move does not result in a win, True if move does result
	 *         				in a win
	 * @throws IllegalMove
	 */
	@SuppressWarnings("deprecation")
	public boolean makeMove(int x, int y) throws IllegalMove
	{
		boolean isWinningMove = false;

		/*
		 * Tries to assign player to desired square, but if another player already
		 * occupies that square, throws IllegalMove
		 */
		try
		{
			currentBoard.getBoard()[x][y].setPlayer(getTurnPlayer());
			if (currentBoard.gameOver(getTurnPlayer().getPieceColour()))
			{
				isWinningMove = true;
			}

		} catch (IllegalMove e)
		{
			throw e;
		}

		return isWinningMove;
	}

	/**
	 * Determine whose turn it is. 0 or an odd number is player ones turn, and an even number is
	 * player twos turn.
	 * 
	 * @return Copy of the player object of the player whose turn it is
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
	 * Increments the turn count by one
	 */
	public int incrementPlayerTurn()
	{
		turnCount += 1;
		return turnCount;
	}
}
