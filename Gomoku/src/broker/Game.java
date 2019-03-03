package broker;

/**
 * @author Group 22 Code implemented by Steven Hamilton.
 * 
 *         This class is an object of type Game, which represents the current
 *         state of the game being played
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
     * This constructor sets each player, sets turn count to 0, and creates a
     * new board
     * 
     * @param gotPlayerOne Name of player one
     * @param gotPlayerTwo Name of player two
     */
    public Game(String gotPlayerOne, String gotPlayerTwo)
    {
	playerOne = new Player(gotPlayerOne, PLAYER_ONE_COLOUR_VALUE);
	playerTwo = new Player(gotPlayerTwo, PLAYER_TWO_COLOUR_VALUE);
	resetRound();
    }

    /**
     * This Constructor copies player data, reset the turn count to 0, and
     * create a new board
     * 
     * @param p1 Player one to copy
     * @param p2 Player two to copy
     */
    public Game(Player p1, Player p2)
    {
    	playerOne = new Player(p1.getUserName(), p2.getPieceColour());
    	playerTwo = new Player(p2.getUserName(), p1.getPieceColour());
    	playerOne.setWinCount(p1.getWinCount());
    	playerTwo.setWinCount(p2.getWinCount());
    	playerOne.setLoseCount(p1.getLoseCount());
		playerTwo.setLoseCount(p2.getLoseCount());
		playerOne.setDrawCount(p1.getDrawCount());
		playerTwo.setDrawCount(p2.getDrawCount());
		resetRound();
    }

    /**
     * This getter returns a copy reference to player one
     * 
     * @return A copy reference of playerOne
     */
    public Player getPlayerOne()
    {
	return new Player(playerOne);
    }

    /**
     * This getter returns a copy reference to player two
     * 
     * @return A copy reference of playerTwo
     */
    public Player getPlayerTwo()
    {
	return new Player(playerTwo);
    }

    /**
     * This getter returns the turn count for the current round
     * 
     * @return The current turn count
     */
    public int getTurnCount()
    {
	return turnCount;
    }

    /**
     * This getter returns the current board in play
     * 
     * @return reference to the current Board object in play
     */
    public Board getCurrentBoard()
    {
	return currentBoard;
    }

    /**
     * This setter sets the Player object of player one
     * 
     * @param playerOne The desired player object to set as player one
     */
    public void setPlayerOne(Player playerOne)
    {
	this.playerOne = new Player(playerOne);
    }

    /**
     * This setter sets the Player object of player two
     * 
     * @param playerTwo The desired player object to set as player two
     */
    public void setPlayerTwo(Player playerTwo)
    {
	this.playerTwo = playerTwo;
    }

    /**
     * This setter sets the current turn count
     * 
     * @param TurnCount the desired integer turn count to set
     */
    public void setTurnCount(int turnCount)
    {
	this.turnCount = turnCount;
    }

    /**
     * This setter set the current Board
     * 
     * @param CurrentBoard the desired board object to set
     */
    public void setCurrentBoard(Board currentBoard)
    {
	this.currentBoard = currentBoard;
    }

    /**
     * This method creates a new empty board, and resets the turn count to 0
     */
    private void resetRound()
    {
	setCurrentBoard(new Board());
	turnCount = 0;
    }

    /**
     * 
     * This method makes the players desired move by assigning the desired
     * square on the board to a player if there is not already another player
     * assigned to it, and determines if the move resulted in a win.
     * 
     * @param xCoordinate Horizontal placement
     * @param yCoordinate Vertical placement
     * @return False if move does not result in a win, True if move does result
     *         in a win
     * @throws IllegalMove
     */
    @SuppressWarnings("deprecation")
    public boolean makeMove(int x, int y) throws IllegalMove
    {
	boolean isWinningMove = false;

	/*
	 * Tries to assign player to desired square, but if another player
	 * already occupies that square, throws IllegalMove
	 */
	try
	{
	    currentBoard.getBoard()[x][y].setPlayer(getTurnPlayer());
	    if (currentBoard.gameOver(getTurnPlayer().getPieceColour()))
	    {
		isWinningMove = true;

		/*
		 * TODO @Steven implement code as per the following
		 * instructions.
		 * 
		 * 1. Adjust player one's and two's stats (wins, loses, draws)
		 * 2. do NOT increment turnCount: this will meess up who played
		 * the winning move.
		 * 
		 * Note: Due to encapsulation you need conditional statements to
		 * identify if turn player is either player one or player two
		 * (perhaps you could use piece colour, or user name; if you use
		 * user name, player registration will have to make sure that
		 * the user name is unique for both players)
		 * 
		 * Also Note: we are not currently doing anything for the draw
		 * count, we can implement this later.
		 */
	    }

	} catch (IllegalMove e)
	{
	    throw e;
	}

	return isWinningMove;
    }

    /**
     * This method determines whose turn it is. 0 or an odd number is player
     * one's turn, and an even number is player two's turn.
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
     * This method increments the turn count by one each time it is called.
     */
    public int incrementPlayerTurn()
    {
	turnCount += 1;
	return turnCount;
    }
}
