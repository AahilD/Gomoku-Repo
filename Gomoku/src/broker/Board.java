package broker;

/**
 * @author GROUP 22 Code implemented by Emily Pang This class represents and
 *         Object of Type Board. An instance of this class will contain and
 *         maintain the state of the current game being played.
 * 
 */

public class Board
{
	// TODO Add javadoc
	// Initializes a 2D array for the board. Also initializes the value instance variables "WIDTHLENGTH" and "HEIGHTLENGTH".  //
	private Square[][] board;
	final int WIDTHLENGTH = 19;
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
	//Accessor (Getters)//

	/**
	 * This getter method pulls the board and returns it.
	 * 
	 * @return board
	 */
	public Square[][] getBoard()
	{
		return board;
	}

	/*
	 * TODO this method is big and complicated break it down by calling smaller
	 * private methods that will do one explicit thing for instance you should
	 * break it down into the following private methods private boolean
	 * verifyHorizontal() private boolean verifyVerticle() private boolean
	 * verifyLeftDiagonal() private boolean verifyRightDiagonal() you may even
	 * have to break down these methods to smaller methods
	 * 
	 * remember we do not want code duplication if you can make a re-usable
	 * method then feel free to do so.
	 * 
	 * think of every package as an anthology, every class as a chapter every
	 * method as a paragraph and every line as a sentence
	 * 
	 * other than that this looks great
	 */
	/**
	 * This checks if there is a Horizontal win based on the last piece played.
	 * 
	 * @param char pieceColour
	 * @return fiveInARow
	 */
	private boolean verifyHorizontal(char pieceColour)
	{
		boolean fiveInARow = false;
		int i = 0;
		for (int row = 0; row < board.length && !fiveInARow; row++)
		{
			for (int col = 0; col < board[row].length; col++)
			{
				if (Square.getPlayer().getPieceColour() == pieceColour)
				{
					i++;
				} else if (i >= 5)
				{
					fiveInARow = true;
				} else
				{
					fiveInARow = false;
					i = 0;
				}
			}
		}
		return fiveInARow;
	}
	
	/**
	 * This checks if there is a vertical win based on the last piece placed. 
	 * 
	 * @param pieceColour
	 * @return fiveInARow
	 */

	private boolean verifyVertical(char pieceColour)
	{
		boolean fiveInARow = false;
		int i = 0;
		for (int r = 0; r < board.length && !fiveInARow; r++)
		{
			for (int c = 0; c < board[r].length; c++)
			{
				if (Square.getPlayer().getPieceColour() == pieceColour)
				{
					i++;
				} else
				{
					i = 0;
				}
			}
		}
		if (i >= 5)
		{
			fiveInARow = true;
		} else
		{
			fiveInARow = false;
		}
		return fiveInARow;
	}
	
	/**
	 * This checks if there is a diagonal going to the left direction based on the last piece placed.
	 * @param pieceColour
	 * @return fiveInARow
	 */

	private boolean verifyDiagonalLeft(char pieceColour)
	{
		boolean fiveInARow = false;
		int counter = 0;
		for (int col = Square.getX(), row = Square.getY(); col < board.length
				&& row < board.length; col--, row--)
		{
			if (Square.getPlayer().getPieceColour() == pieceColour)
			{
				counter++;
			} else
			{
				counter = 0;
			}
		}
		if (counter >= 5)
		{
			fiveInARow = true;
		} else
		{
			fiveInARow = false;
		}
		return fiveInARow;
	}
	/**
	 * This checks if there is a diagonal in the right direction based on the last move played. 
	 * @param pieceColour
	 * @return fiveInARow
	 */

	private boolean verifyDiagonalRight(char pieceColour)
	{
		boolean fiveInARow = false;
		int counter = 0;
		for (int col = Square.getX(), row = Square.getY(); col < board.length
				&& row < board.length; col++, row++)
		{
			if (Square.getPlayer().getPieceColour() == pieceColour)
			{
				counter++;
			} else
			{
				counter = 0;
			}
		}
		if (counter >= 5)
		{
			fiveInARow = true;
		} else
		{
			fiveInARow = false;
		}
		return fiveInARow;
	}

	/**
	 * This checks if the board is full or not 
	 * 
	 * @return full 
	 */
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

	public boolean gameOver()
	{
		return verifyVertical('w') || verifyVertical('b')
				|| verifyHorizontal('w') || verifyHorizontal('b')
				|| verifyDiagonalLeft('w')
				|| verifyDiagonalLeft('b')
				|| verifyDiagonalRight('w')
				|| verifyDiagonalRight('b') || boardFull();
	}
}