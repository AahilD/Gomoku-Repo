package broker;

/**
 * @author GROUP 22 Code implemented by Emily Pang This class represents and
 *         Object of Type Board. An instance of this class will contain and
 *         maintain the state of the current game being played.
 * 
 */

public class Board
{
    // TODO @Steven Javadoc

    // Initializes a 2D array for the board //
    private Square[][] board;

    // TODO @Leslie, please make sure to use the following constants instead of
    // using magic numbers in any of the logic

    // board is always width of 19
    final int WIDTHLENGTH = 19;
    // board is always height of 19
    final int HEIGHTLENGTH = 19;

    public Board()
    {
	board = new Square[WIDTHLENGTH][HEIGHTLENGTH];
	for (int x = 0; x < board.length; x++)
	{
	    for (int y = 0; y < board[x].length; y++)
	    {
		board[x][y] = new Square(x, y);
	    }
	}
    }

    /**
     * @return
     */
    public Square[][] getBoard()
    {
	return board;
    }

    /**
     * 
     * @param char pieceColour
     * @return
     */
    public boolean verifyHorizontal(char pieceColour)
    {
	boolean fiveInARow = false;
	int i = 0;
	for (int row = 0; row < board.length && !fiveInARow; row++)
	{
	    for (int col = 0; col < board[row].length && !fiveInARow; col++)
	    {
		if (board[row][col].getPlayer() != null && board[row][col]
			.getPlayer().getPieceColour() == pieceColour)
		{
		    i++;
		} else
		{
		    i = 0;
		}
		if ((i == 5 && board[row][col + 1].getPlayer() != null)
			&& board[row][col + 1].getPlayer()
				.getPieceColour() != pieceColour)
		{
		    fiveInARow = true;
		}
	    }
	}
	return fiveInARow;
    }

    /**
     * 
     * @param pieceColour
     * @return
     */
    @SuppressWarnings("unused")
    public boolean verifyVertical(char pieceColour)
    {
	int counter = 0;
	// emmanuels version
	
	boolean win = false;
	
	for (int x = 0; x < WIDTHLENGTH; x ++)
	{
	    int maxContiguousCount = 0;
	    for (int y = 0; y < HEIGHTLENGTH; y ++)
	    {
		if(getBoard()[x][y].getPlayer() == null)
		{
		    if(maxContiguousCount < counter)
		    {
			maxContiguousCount = counter;
		    }
		    
		    counter = 0;
		}
		else if(getBoard()[x][y].getPlayer().getPieceColour() == pieceColour)
		{
		    counter ++;
		} else
		{
		    counter = 0;
		}
	    }
	    
	    if (maxContiguousCount == 5)
	    {
		win = true;
	    }
	}
	
	/* Emily's code;
	for (int y = 0; y >= HEIGHTLENGTH; y++)
	{
	    for (int x = 0; x >= WIDTHLENGTH; x++)
	    {
		if (board[x][y].getPlayer().getPieceColour() == pieceColour
			&& (board[x][y].getPlayer() != null))
		{
		    counter++;
		} else
		{
		    counter = 0;
		}
		if ((counter == 5 && board[x + 1][y].getPlayer() != null)
			&& board[x + 1][y].getPlayer()
				.getPieceColour() != pieceColour)
		{
		    System.out.println("it caught the win!");
		    return true;
		}
	    }
	}*/
	return win;
    }

    /**
     * 
     * @param pieceColour
     * @return
     */
    public boolean verifyDiagonalLeft(char pieceColour)
    {
	for (int n = -WIDTHLENGTH; n <= WIDTHLENGTH; n++)
	{
	    int counter2 = 0;
	    for (int i = 0; i < WIDTHLENGTH; i++)
	    {
		if ((i - n >= 0) && (i - n < WIDTHLENGTH))
		{
		    if (board[i][i - n].getPlayer() != null && board[i][i - n]
			    .getPlayer().getPieceColour() == pieceColour)
		    {
			counter2++;
			if ((counter2 == 5
				&& board[i + 1][i - n + 1].getPlayer() != null)
				&& board[i + 1][i - n + 1].getPlayer()
					.getPieceColour() != pieceColour)
			{
			    return true;
			}
		    } else
		    {
			counter2 = 0;
		    }
		}
	    }
	}
	return false;
    }

    /**
     * TODO @Leslie fix till tests pass. [Up for review-LN]
     * 
     * @param pieceColour
     * @return
     */
    public boolean verifyDiagonalRight(char pieceColour)
    {
	for (int k = 0; k < WIDTHLENGTH * 2; k++)
	{
	    int counter1 = 0;
	    for (int j = 0; j <= k; j++)
	    {
		int i = k - j;
		if (i < WIDTHLENGTH && j < WIDTHLENGTH)
		{
		    if (board[i][j].getPlayer() != null && board[i][j]
			    .getPlayer().getPieceColour() == pieceColour)
		    {
			counter1++;
			if ((counter1 == 5
				&& board[i - 1][j + 1].getPlayer() != null)
				&& board[i - 1][j + 1].getPlayer()
					.getPieceColour() != pieceColour)
			{
			    return true;
			}
		    } else
		    {
			counter1 = 0;
		    }
		}
	    }
	}
	return false;
    }

    /**
     * TODO I DONT THINK THIS METHOD WORKS
     * @return
     */
    @SuppressWarnings("unused")
    private boolean boardFull()
    {
	boolean full = true;
	for (int row = 0; row < 19 && full; row++)
	{
	    for (int column = 0; column < 19 && full; column++)
	    {
		if (board[row][column] == null)
		{
		    full = false;
		}
	    }
	}
	return full;
    }

    /**
     * check to see if any winning conditions have been met. This will check all
     * the columns, rows, and verticals if the given piece colour has won.
     * returns true if the 5 pieces of this colour exist in a row in any of
     * these directions.
     * 
     * @param pieceColour takes in the piece colour to check if it has won
     * @return true if game is over (win || draw); false if no winner yet
     */
    public boolean gameOver(char pieceColour)
    {
	return verifyVertical(pieceColour) 
		|| verifyHorizontal(pieceColour)
		|| verifyDiagonalLeft(pieceColour)
		|| verifyDiagonalRight(pieceColour);
		
		// NOT WORK
		//|| boardFull();
    }
}