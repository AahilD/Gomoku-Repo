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
     * TODO @Leslie fix till all unit tests pass [in progress-LN]
     * 
     * @param char pieceColour
     * @return
     */
    public boolean verifyHorizontal(char pieceColour)
    {
	// five in a row flag
	boolean fiveInARow = false;
	// counter for consecutive same-colour-pieces found
	int i = 0;
	// row by row
	for (int row = 0; row < board.length && !fiveInARow; row++)
	{
	    // column by column
	    for (int col = 0; col < board[row].length && !fiveInARow; col++)
	    {
		if (board[row][col].getPlayer() != null && board[row][col]
			.getPlayer().getPieceColour() == pieceColour)
		{
		    i++;
		} else
		{
		    // If this square does not equal the the piece we are
		    // looking for
		    // reset i to 0
		    i = 0;
		}
		// check if i == 5
		if (i == 5)
		{
		    // if 5 in a row are found then set the flag to true
		    fiveInARow = true;
		}
	    }
	}
	return fiveInARow;
    }

    /**
     * TODO @Leslie fix till tests pass. [in progress-LN]
     * 
     * @param pieceColour
     * @return
     */
    @SuppressWarnings("unused")
    public boolean verifyVertical(char pieceColour)
    {	boolean fiveInARow=false;
    	for(int i=0; i<WIDTHLENGTH && !fiveInARow; i++) {
			int count=0;
			for(int x=0; x<HEIGHTLENGTH && !fiveInARow; x++) {
				if(board[x][i].getPlayer() !=null && board[x][i].getPlayer().getPieceColour()==pieceColour) {
					count++;
				}
				else {
					count=0;
				}
				if(count==5) {
					fiveInARow=true;
				}
			}
		}
    	return fiveInARow;
    }

    /**
     * TODO @Leslie fix till tests pass [in progress-LN]
     * 
     * @param pieceColour
     * @return
     */
    @SuppressWarnings("unused")
    public boolean verifyDiagonalLeft(char pieceColour)
    {	
    	boolean fiveInARow=false;
    	int counter=0;
    	for (int s=0; s<WIDTHLENGTH && !fiveInARow; s++) {
  		  for (int i=s; i>-1 && !fiveInARow; i--) {
  		      if(board[i][s-i].getPlayer() !=null && board[i][s-i].getPlayer().getPieceColour()==pieceColour) {
  		    	 counter++; 
  		      }
  		      else {
  		    	  counter=0;
  		      }
  		      if(counter==5) {
  		    	  fiveInARow=true;
  		      }
  		  }
  		}
  		
  		for (int s=1; s<WIDTHLENGTH && !fiveInARow; s++) {
  		  for (int i=3-1; i>=s; i--) {
  		      if(board[i][s+3-1-i].getPlayer() !=null && board[i][s+3-1-i].getPlayer().getPieceColour()==pieceColour) {
  		    	  counter++;
  		      }
  		      else {
  		    	  counter=0;
  		      }
  		      if(counter==5) {
  		    	  fiveInARow=true;
  		      }
  		  }
  		}
	return fiveInARow;
    }

    /**
     * TODO @Leslie fix till tests pass. [in progress-LN]
     * 
     * @param pieceColour
     * @return
     */
    @SuppressWarnings("unused")
    public boolean verifyDiagonalRight(char pieceColour)
    {	
    	boolean fiveInARow=false;
    	int counter=0;
    	for (int n = -WIDTHLENGTH; n <= WIDTHLENGTH && !fiveInARow; n++) {
            for(int i = 0; i < HEIGHTLENGTH; i++){
                if((i-n>=0)&&(i-n<HEIGHTLENGTH)) {
                    if(board[i][i-n].getPlayer() !=null && board[i][i-n].getPlayer().getPieceColour()==pieceColour) {
                    	counter++;	
                    }
                    else {
                    	counter=0;
                    }
                    if(counter==5) {
                    	fiveInARow=true;
                    }
                    
                }
            }
        }
	return fiveInARow;
    }

    /**
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
    /**
     * @deprecated This method is now deprecated, there is a bug that I need to
     *             find I will add a todo once I have identified the specific
     *             bug. I might even actually make JUnit tests to test them.
     * @param pieceColour
     * @return
     */
    public boolean gameOver(char pieceColour)
    {

	// TODO @Leslie
	/*
	 * remove comments once all the rest is complete remove @depprecated
	 * and @unused symbols
	 */

	// winining condition methods are depricated
	/*
	 * return verifyVertical(pieceColour) || verifyHorizontal(pieceColour)
	 * || verifyDiagonalLeft(pieceColour) ||
	 * verifyDiagonalRight(pieceColour) || boardFull();
	 */
	return false;
    }
}