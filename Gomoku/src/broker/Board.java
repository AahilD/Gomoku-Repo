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
	// Initializes a 2D array for the board //
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

	/**
	 * @return
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
	 * @param char pieceColour
	 * @return
	 */
	private boolean verifyHorizontal(char pieceColour)
	{
		// five in a row flag
		boolean fiveInARow = false;
		// counter for consecutive same-colour-pieces found
		int i = 0;
		// row by row
		for (int row = 0; row < board.length && !fiveInARow; row++)
		{
			// column by column 
			// TODO add condition to stop when you have spoted 5 in a row in the 
			// nested for loop as well
			for (int col = 0; col < board[row].length; col++)
			{
				// TODO Squre might need a copy constructor
				// Square thisSquare = new Square(board[x][y])
				// if square[x][y].getPlayer != null && thisSquare.getplayer.getcolour == pieceColour
				if (Square.getPlayer().getPieceColour() == pieceColour)
				{
					// if this square is the piece we are looking for
					// increment i
					i++;
				} else
				{
					// If this square does not equal the the piece we are looking for
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