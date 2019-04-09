package broker;

import java.util.ArrayList;

/**
 *  This class represents an Object of Type Board and contains and maintains the state
 *  of the current game being played.
 *  
 * @author GROUP 22 Code implemented by Emily Pang
 * 
 */

public class Board
{
    // Initializes a 2D array for the board //
    private Square[][] board;

    // TODO @Leslie, please make sure to use the following constants instead of
    // using magic numbers in any of the logic

    // board is always width of 19
    final int WIDTHLENGTH = 19;
    // board is always height of 19
    final int HEIGHTLENGTH = 19;

    /**
     * This is the default constructor, which creates an empty 19 x 19 board
     * made from a 2d array
     */
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
     * This method returns a 2-D array of type square that
     * represents the board.
     * 
     * @return An array list forming the board.
     */
    public Square[][] getBoard()
    {
	return board;
    }

    /**
     * Checks the board in a horizontal direction for 5 pieces of the same colour in a
     * row; signifying a win
     * 
     * @param char pieceColour	The colour of the piece
     * @return 					True if there are five pieces of the 
     * 							same colour in a row
     */
    public boolean verifyHorizontal(char pieceColour)
    {
	// a variable to capture the number of pieces in line
	int counter = 0;
	// a variable to store the value of the condition for a victory
	boolean win = false;

	// iterate through the board row by row
	for (int row = 0; row < HEIGHTLENGTH; row++)
	{
	    // a variable to store the number of contiguous same colour pieces
	    int maxContiguousCount = 0;
	    // iterate through the board column by column
	    for (int col = 0; col < WIDTHLENGTH; col++)
	    {
		// check if the square at this position is occupied
		if (getBoard()[row][col].getPlayer() == null)
		{
		    // keep the bigger of the two counter values
		    if (maxContiguousCount < counter)
		    {
			maxContiguousCount = counter;
		    }

		    // reset the counter
		    counter = 0;
		} else if (getBoard()[row][col].getPlayer()
			.getPieceColour() == pieceColour)
		{
		    // increment if the current square has the piece colour we
		    // are looking for
		    counter++;
		} else
		{
		    // just in case the other conditions where not met for what
		    // ever reason
		    counter = 0;
		}
	    }

	    if (maxContiguousCount == 5)
	    {
		// if maxContiguousCount registered the value of 5 all systems
		// are go.
		win = true;
	    }
	}

	return win;
    }

    /**
     * Check the board in a vertical direction to determine if there are 5 pieces in a
     * row; signifying a win
     * 
     * @param char pieceColour  The Colour of the player's piece to be checked
     * @return 					True if there are exactly 5 pieces of the checked colour
     * 							in a column as a row; otherwise, returns false.
     */
    public boolean verifyVertical(char pieceColour)
    {
	
    /*
	 * This method follows the same logic as verifyHorizontal, with the
	 * exception that it scans the board column by column first and row by
	 * row in the nested for-loop
	 */

	int counter = 0;

	boolean win = false;

	for (int col = 0; col < HEIGHTLENGTH; col++)
	{
	    int maxContiguousCount = 0;
	    for (int row = 0; row < WIDTHLENGTH; row++)
	    {
		if (getBoard()[row][col].getPlayer() == null)
		{
		    if (maxContiguousCount < counter)
		    {
			maxContiguousCount = counter;
		    }

		    counter = 0;
		} else if (getBoard()[row][col].getPlayer()
			.getPieceColour() == pieceColour)
		{
		    counter++;
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
	return win;
    }

    /**
     * Checks the diagonals on a leftwards slant on the board for 5 pieces in a
     * row; signifying a win.
     * 
     * @param char pieceColour	The colour of the player's piece to be checked
     * @return 					True if there are exactly 5 pieces of the checked colour
     * 							in a diagonal as a row; otherwise, returns false.
     */
    public boolean verifyDiagonalLeft(char pieceColour)
    {
	/*
	 * in order to verify the diagonals it requires splitting up the board
	 * along the longest diagonal. Each outer for-loop will go through each
	 * half (triangle) of the board.
	 * 
	 */
	boolean win = false;
	ArrayList<Character> listLeftDiagonal = new ArrayList<Character>();
	for (int n = -WIDTHLENGTH; n <= HEIGHTLENGTH; n++)
	{
	    for (int i = 0; i < WIDTHLENGTH; i++)
	    {
		if ((i - n >= 0) && (i - n < HEIGHTLENGTH))
		{
		    if (board[i][i - n].getPlayer() != null)
		    {
			listLeftDiagonal.add(
				board[i][i - n].getPlayer().getPieceColour());
		    } else
		    {
			listLeftDiagonal.add('#');
		    }

		}
	    }
	    listLeftDiagonal.add('*');
	}

	int counter2 = 0;
	for (int i = 0; i < (WIDTHLENGTH * HEIGHTLENGTH)
		+ (WIDTHLENGTH * 2); i++)
	{
	    if (listLeftDiagonal.get(i) == pieceColour)
	    {
		counter2++;
	    } else
	    {
		counter2 = 0;
	    }
	    if (counter2 == 5 && listLeftDiagonal.get(i + 1) != pieceColour)
	    {
		win = true;
	    }
	}

	return win;
    }

    /**
     * Checks the diagonals on a rightwards slant on the board for 5 pieces in a
     * row; signifying a win.
     * 
     * @param char pieceColour	The colour of the players piece to be checked
     * @return					True if exactly 5 pieces of the checked colour
     * 							in a diagonal as a row; otherwise, returns false
     */
    public boolean verifyDiagonalRight(char pieceColour)
    {
	/*
	 * This method follows the same logic as verifyDiagonalLeft except here
	 * it checks diagonals in the mirrored direction.
	 */
	boolean win = false;
	ArrayList<Character> listRightDiagonal = new ArrayList<Character>();
	for (int k = 0; k < WIDTHLENGTH * 2; k++)
	{
	    for (int j = 0; j <= k; j++)
	    {
		int i = k - j;
		if (i < WIDTHLENGTH && j < WIDTHLENGTH)
		{
		    if (board[i][j].getPlayer() != null)
		    {
			listRightDiagonal
				.add(board[i][j].getPlayer().getPieceColour());
		    } else
		    {
			listRightDiagonal.add('#');
		    }
		}
	    }
	    listRightDiagonal.add('*');
	}

	int counter1 = 0;
	for (int i = 0; i < (WIDTHLENGTH * HEIGHTLENGTH)
		+ (WIDTHLENGTH * 2); i++)
	{
	    if (listRightDiagonal.get(i) == pieceColour)
	    {
		counter1++;

	    } else
	    {
		counter1 = 0;
	    }
	    if (counter1 == 5 && listRightDiagonal.get(i + 1) != pieceColour)
	    {
		win = true;
	    }
	}

	return win;
    }

    /**
     * TODO @EMMANUEL FIX THIS
     * 
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
     * Call this method to run each one of the verify methods in a single method
     * call. This method will true if any one of the verify conditions return
     * true. Takes in the piece colour to scan the board for.
     * 
     * Methods include: - verifyVertical - verifyHorizontal - verifyDiagonalLeft
     * - verifyDiagonalRight
     * 
     * @param pieceColour takes in the piece colour to check if it has won
     * @return 			  True if game is over (win || draw); otherwise, returns false
     */
    public boolean gameOver(char pieceColour)
    {
	return verifyVertical(pieceColour) || verifyHorizontal(pieceColour)
		|| verifyDiagonalLeft(pieceColour)
		|| verifyDiagonalRight(pieceColour);

	// NOT WORK
	// || boardFull();
    }
}