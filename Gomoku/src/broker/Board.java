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
	
	/**
	 * TODO make sure this passes unit tests
	 * TODO javadoc properly
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
				// TODO Squre might need a copy constructor
				// Square thisSquare = new Square(board[x][y])
				// if square[x][y].getPlayer != null &&
				// thisSquare.getplayer.getcolour == pieceColour
				if (board[row][col].getPlayer() != null && board[row][col].getPlayer().getPieceColour() == pieceColour)
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

	@SuppressWarnings("unused")
	public boolean verifyVertical(char pieceColour)
	{
		// five in a row flag
		boolean fiveInARow = false;

		// counter for consecutive same color pieces found
		int i = 0;

		// row by row
		for (int r = 0; r < board.length && !fiveInARow; r++)
		{

			// column by column
			for (int c = 0; c < board[r].length && !fiveInARow; c++)
			{
				if (board[r][c].getPlayer().getPieceColour() == pieceColour)
				{
					i++;
				} else
				{
					// If this square does not equal the the piece we are
					// looking for
					// reset i to 0
					i = 0;
				}
			}
		}
		// check if i == 5
		if (i == 5)
		{
			// if 5 in a row are found then set the flag to true
			fiveInARow = true;
		} else
		{
			fiveInARow = false;
		}
		return fiveInARow;
	}

	@SuppressWarnings("unused")
	public boolean verifyDiagonalLeft(char pieceColour)
	{

		boolean fiveInARow = false;
		int counter = 0;

		for (int line = 1; line <= (WIDTHLENGTH + HEIGHTLENGTH - 1); line++)
		{
			int start_col = java.lang.Math.max(0, line - WIDTHLENGTH);

			int count = java.lang.Math.min(line, java.lang.Math
					.min((HEIGHTLENGTH - start_col), WIDTHLENGTH));
			for (int j = 0; j < count; j++)
			{
				if (board[java.lang.Math.min(WIDTHLENGTH, line) - j
						- 1][start_col + j].getPlayer()
								.getPieceColour() == pieceColour)
				{
					counter = counter + 1;

				} else
				{
					counter = 0;
				}
				if (counter >= 5)
				{
					fiveInARow = true;
					return fiveInARow;
				}
			}
		}

		return fiveInARow;
	}

	@SuppressWarnings("unused")
	public boolean verifyDiagonalRight(char pieceColour)
	{
		boolean fiveInARow = false;
		int counter = 0;
		for (int col = 0, row = 0; col < board.length
				&& row < board.length; col++, row++)
		{
			if (getBoard()[row][col].getPlayer()
					.getPieceColour() == pieceColour)
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
		// winining condition methods are depricated
		/*
		 * return verifyVertical(pieceColour) || verifyHorizontal(pieceColour)
		 * || verifyDiagonalLeft(pieceColour) ||
		 * verifyDiagonalRight(pieceColour) || boardFull();
		 */
		return false;
	}
}