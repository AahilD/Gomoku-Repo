package broker;

/**
 * This class represents a single round instance for the game.
 *         
 * @author GROUP 22 Code implemented by Steven Hamilton.
 * 
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
     * new board.
     * 
     * @param gotPlayerOne  String name of player one.
     * @param gotPlayerTwo 	String name of player two.
     */
    public Game(String gotPlayerOne, String gotPlayerTwo)
    {
	playerOne = new Player(gotPlayerOne, PLAYER_ONE_COLOUR_VALUE);
	playerTwo = new Player(gotPlayerTwo, PLAYER_TWO_COLOUR_VALUE);
	resetRound();
    }

    /**
     * This Constructor copies player data, reset the turn count to 0, and
     * creates a new board. Use this copy constructor when the user(s) have
     * decided to play a new game. copy the old game into the new game, but
     * switch around player one to player two and player two to player one.
     * migrate all relevant info.
     * 
     * @param toGame The Game instance to copy.
     */
    public Game(Game toGame)
    {
	playerOne = new Player(toGame.getPlayerTwo(),
		PLAYER_ONE_COLOUR_VALUE);
	playerTwo = new Player(toGame.getPlayerOne(),
		PLAYER_TWO_COLOUR_VALUE);
	playerOne.setWinCount(toGame.getPlayerTwo().getWinCount());
	playerTwo.setWinCount(toGame.getPlayerOne().getWinCount());
	playerOne.setLoseCount(toGame.getPlayerTwo().getLoseCount());
	playerTwo.setLoseCount(toGame.getPlayerOne().getLoseCount());
	playerOne.setDrawCount(toGame.getPlayerTwo().getDrawCount());
	playerTwo.setDrawCount(toGame.getPlayerOne().getDrawCount());
	resetRound();
    }

    /**
     * Returns a copied reference to player one.
     *
     * @return A copy reference of playerOne.
     */
    public Player getPlayerOne()
    {
	return new Player(playerOne);
    }

    /**
     * Returns a copied reference to player two.
     * 
     * @return A copy reference of playerTwo.
     */
    public Player getPlayerTwo()
    {
	return new Player(playerTwo);
    }

    /**
     * Returns the turn count for the current round.
     * 
     * @return The current turn count.
     */
    public int getTurnCount()
    {
	return turnCount;
    }

    /**
     * Returns the current board in play.
     * 
     * @return reference to the current Board object in play.
     */
    public Board getCurrentBoard()
    {
	return currentBoard;
    }

    /**
     * Sets the player one to a Player object.
     * 
     * @param playerOne The desired player object to set as player one.
     */
    public void setPlayerOne(Player playerOne)
    {
	this.playerOne = new Player(playerOne);
    }

    /**
     * Sets the player two to a Player object.
     * 
     * @param playerTwo The desired player object to set as player two.
     */
    public void setPlayerTwo(Player playerTwo)
    {
	this.playerTwo = playerTwo;
    }

    /**
     * Sets the current turn count.
     * 
     * @param TurnCount The desired integer turn count to set.
     */
    @SuppressWarnings("unused")
    private void setTurnCount(int turnCount)
    {
	this.turnCount = turnCount;
    }
    
    /**
     * Sets the turn count to 0.
     */
    public void resetTurnCount()
    {
	this.turnCount = 0;
    }

    /**
     * Set the current Board.
     * 
     * @param currentBoard the desired board object to set.
     */
    public void setCurrentBoard(Board currentBoard)
    {
	this.currentBoard = currentBoard;
    }

    /**
     * Creates a new empty board, and resets the turn count to 0.
     */
    public void resetRound()
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
     * @param x 				Horizontal placement.
     * @param y 				Vertical placement.
     * 
     * @throws	IllegalMove		Thrown if Board.gameOver throws IllegalMove.
     * @throws	WinAndLosses	Thrown if move results in a win.
     */
    public void makeMove(int x, int y) throws IllegalMove, WinAndLosses
    {
	try
	{
	    // try to play the move the player requested
	    currentBoard.getBoard()[x][y].setPlayer(getTurnPlayer());

	    // Uncomment following line for debugging purposes ONLY
	    // System.out.println(getTurnPlayer().getUserName() + " payed on ["
	    // + x + "][" + y + "]");

	    // verify if this move was the wining move by invoking gameOver
	    // method.
	    if (currentBoard.gameOver(getTurnPlayer().getPieceColour()))
	    {
		// variable to store the winning player
		Player winner = null;
		// variable to store the losing player
		Player loser = null;

		// if winner is player one
		if (getTurnPlayer().getPieceColour() == playerOne
			.getPieceColour())
		{
		    // Adjust wincount, lose count, etc...
		    playerOne.incrementWinCount();
		    playerTwo.incrementLoseCount();
		    // store the players into their respective variables
		    winner = playerOne;
		    loser = playerTwo;
		} else
		{
		    // adjust wincount, etc...
		    playerTwo.incrementWinCount();
		    playerOne.incrementLoseCount();
		    // store the players into their respective variables
		    winner = playerTwo;
		    loser = playerOne;
		}
		// throw the win/loss exception with the respective winner and
		// loser
		throw new WinAndLosses(winner, loser);
	    }
	} catch (IllegalMove e)
	{
	    // if move was invalid throw the illegal move exception
	    throw e;
	}
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
	//Even turns is player one, odd is player 2
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
     * This method increments the turn count by one each time it is called, and returns
     * what the new turn number is.
     * 
     * @return turnCount	The current turn number.
     */
    public int incrementPlayerTurn()
    {
	turnCount++;
	return turnCount;
    }
}
