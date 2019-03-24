package broker;

import java.util.ArrayList;

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

    /**
     * Default Constructor to create an empty Board
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
     * @return The array list forming the board.
     */
    public Square[][] getBoard()
    {
	return board;
    }

    /**
     * Checks the horizontals of the board for 5 pieces of the same colour in a
     * row, signifying a win
     * 
     * @param char pieceColour
     * @return True if there are five pieces of the same colour in a row
     */
    public boolean verifyHorizontal(char pieceColour)
    {
	int counter = 0;
	// emmanuels version
	// TODO EMILY
	/*
	 * - your code is in comments below my code - fix your code, you can use
	 * mine as an example - delete my code once you'r done - NOTE: my code
	 * has a bug see below for details.
	 * 
	 * Bug: in my code, I am using maxContiguousCount: int to store the
	 * maximum number of side-by-side pieces of same colour. Problem is: say
	 * there is 6+ in a row in one section of the line (vert, horiz, diag)
	 * and then 5 in a row on the same line, it does not validate the win.
	 * 
	 * e.g. [0][0], [0][1], [0][2], [0][3], [0][4], [0][5], empty, empty,
	 * [0][6], [0][7], [0][8], [0][9], [0][10] here we have 2 pieces and 3
	 * pieces conected in the midle to make 6 in arow (not a win all good)
	 * but on the same line (serated by one (or more) empty squares, you
	 * manage to place 5 in a row it will not be detected as a win.
	 */

	boolean win = false;

	for (int row = 0; row < HEIGHTLENGTH; row++)
	{
	    int maxContiguousCount = 0;
	    for (int col = 0; col < WIDTHLENGTH; col++)
	    {
		if (getBoard()[row][col].getPlayer() == null)
		{
		    // i think the problem is here
		    // possible solution would be to add an addition conditional
		    // statement to see if counter == 5
		    // if so: set win to true.
		    // DONT HESITATE TO CALL ME IF YOU NEED CLARIFCATION
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
	/*
	 * EMILY'S ORIGINAL CODE DO NOT REMOVE boolean fiveInARow = false; int i
	 * = 0; for (int row = 0; row < board.length && !fiveInARow; row++) {
	 * for (int col = 0; col < board[row].length && !fiveInARow; col++) { if
	 * (board[row][col].getPlayer() != null && board[row][col]
	 * .getPlayer().getPieceColour() == pieceColour) { i++; } else { i = 0;
	 * } if ((i == 5 && board[row][col + 1].getPlayer() != null) &&
	 * board[row][col + 1].getPlayer() .getPieceColour() != pieceColour) {
	 * fiveInARow = true; } } } return fiveInARow;
	 */
    }

    /**
     * Check the verticals in the board to determine if there are 5 pieces in a
     * row, signifying a win
     * 
     * @param char pieceColour
     * @return True if there are 5 pieces in a column as a row. Otherwise
     *         returns false
     */
    public boolean verifyVertical(char pieceColour)
    {
	int counter = 0;
	// emmanuels version

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

	/*
	 * Emily's code; DOD NOT DELETE!!!!!!!! for (int y = 0; y >=
	 * HEIGHTLENGTH; y++) { for (int x = 0; x >= WIDTHLENGTH; x++) { if
	 * (board[x][y].getPlayer().getPieceColour() == pieceColour &&
	 * (board[x][y].getPlayer() != null)) { counter++; } else { counter = 0;
	 * } if ((counter == 5 && board[x + 1][y].getPlayer() != null) &&
	 * board[x + 1][y].getPlayer() .getPieceColour() != pieceColour) {
	 * System.out.println("it caught the win!"); return true; } } }
	 */
	return win;
    }

    /**
     * Checks the diagonals of the board for 5 pieces in a row, signifying a win
     * 
     * @param char pieceColour
     * @return True if there are 5 pieces in a diagonal as a row. Otherwise
     *         returns false.
     */
    public boolean verifyDiagonalLeft(char pieceColour)
    {
	boolean win = false;

	// TODO Emily
	/*
	 * This one has an additional bug
	 * 
	 * on the corners (the first diagonal that has only 5 squares that cuts
	 * the accross the corner), the for loop is unable to check the next
	 * square, since there is no next square. therefore even if counter
	 * reaches == 5, the conditional statements that identify 5 pieces in
	 * row (no more no less) are being bypassed as a result.
	 * 
	 * you will need to find a way to set win to true if any of those
	 * corners have 5 in a row (2 corners in each diagonal for this one the
	 * coordinates are [0][14], [1][15], [2][16], [3][17], [4][18] the
	 * coordinates for the other corner are the same, just flip x and y
	 * around.
	 * 
	 * What ever you do for this method carry over the same logic into
	 * diagonal right I did not do anything for that method
	 */
	
	
	/*int counter = 0;
	for (int col = WIDTHLENGTH - 1; col >= 0; col--)
	{
	    int maxContiguousCount = 0;
	    
	    // copy col to col1 so we can increment it without messing up the
	    // forloop
	    int col1 = col;

	    // possible fix to second bug
	    if (counter == 5)
	    {
		win = true;
	    }
	    counter = 0;
	    for (int row = 0; row < HEIGHTLENGTH && col1 < WIDTHLENGTH; row++)
	    {

		if (getBoard()[row][col1].getPlayer() == null)
		{
		    if (maxContiguousCount < counter)
		    {
			maxContiguousCount = counter;
		    }

		    counter = 0;
		} else if (getBoard()[row][col1].getPlayer()
			.getPieceColour() == pieceColour)
		{
		    counter++;
		} else
		{
		    counter = 0;
		}

		col1++;
	    }

	    if (maxContiguousCount == 5)
	    {
		win = true;
	    }
	}

	return win;
	*/

	ArrayList<Character> listLeftDiagonal = new ArrayList<Character>();
	for( int k = 0 ; k < WIDTHLENGTH * 2 ; k++ ) {
        for( int j = 0 ; j <= k ; j++ ) {
            int i = k - j;
            if( i < WIDTHLENGTH && j < WIDTHLENGTH ) {
            	if(board[i][j].getPlayer()!=null){
            		listLeftDiagonal.add(board[i][j].getPlayer().getPieceColour());
            	}
            	else {
            		listLeftDiagonal.add('#');
            	}
            }
        }
        listLeftDiagonal.add('*');
    }
	
	
	int counter1=0;
	for(int i=0; i<399; i++) {
		if(listLeftDiagonal.get(i)==pieceColour) {
			counter1++;
			
		}
		else {
			counter1=0;
		}
		if(counter1==5 && listLeftDiagonal.get(i+1)!=pieceColour) {
				return true;
			}			
	}
	
	return win;
	
	
	
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
	return verifyVertical(pieceColour) || verifyHorizontal(pieceColour)
		|| verifyDiagonalLeft(pieceColour)
		|| verifyDiagonalRight(pieceColour);

	// NOT WORK
	// || boardFull();
    }
}