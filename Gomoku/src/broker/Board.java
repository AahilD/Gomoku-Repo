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
	// TODO Add javadoc

	protected Square[][] board;

	public Board()
	{
		board = new Square[19][19];
	}

	
	/**
	 * @return
	 */
	public Square[][] getBoard()
	{
		return board;
	}
	
	
	/*
	 * TODO this method is big and complicated
	 * break it down by calling smaller private methods
	 * that will do one explicit thing
	 * for instance you should break it down into the following 
	 * private methods
	 * private boolean verifyHorizontal()
	 * private boolean verifyVerticle()
	 * private boolean verifyLeftDiagonal()
	 * private boolean verifyRightDiagonal()
	 * you may even have to break down these methods to smaller methods
	 * 
	 * remember we do not want code duplication if you can make a re-usable
	 * method then feel free to do so. 
	 * 
	 * think of every package as an anthology, 
	 * every class as a chapter
	 * every method as a paragraph
	 * and every line as a sentence
	 * 
	 * other than that this looks great
	 */
	/**
	 * @param piece
	 * @return
	 */
	public boolean hasWon(char piece)
	{
		boolean fiveInARow = false;
		// check rows and columns
		int count = 0;
		// Check horizontal
		for (int row = 0; row < board.length && !fiveInARow; row++)
		{
			for (int col = 0; col < board[row].length; col++)
			{
				if (board[row][col].getPieceColour() == piece)
				{
					count++;
				} else if (count >= 5)
				{
					fiveInARow = true;
				} else
				{
					fiveInARow = false;
					count = 0;
				}
			}
		}
		// Check Vertical
		for (int r = 0; r < board.length && !fiveInARow; r++)
		{
			for (int c = 0; c < board[r].length; c++)
			{
				if (board[r][c].getPieceColour() == piece)
				{
					count++;
				} else
				{
					count = 0;
				}
			}
		}
		if (count >= 5)
		{
			fiveInARow = true;
		} else
		{
			fiveInARow = false;
		}
		// Check Diagonal (Check in all 4 direction, and check up in the same
		// direction)
		// Diagonal Upwards
		for (int ro = 0; ro < board.length && !fiveInARow; ro++)
		{
			if (board[ro][ro + 1].getPieceColour() == piece)
			{
				count++;
			} else if (count >= 5)
			{
				fiveInARow = true;
			} else
			{
				fiveInARow = false;
				count = 0;
			}
			if (board[ro][ro - 1].getPieceColour() == piece)
			{
				count++;
			} else if (count >= 5)
			{
				fiveInARow = true;
			} else
			{
				fiveInARow = false;
				count = 0;
			}
		}
		// Diagonal Downwards//
		for (int co = 0; co < board.length && !fiveInARow; co++)
		{
			if (board[co + 1][co].getPieceColour() == piece)
			{
				count++;
			} else if (count >= 5)
			{
				fiveInARow = true;
			} else
			{
				fiveInARow = false;
			}
			if (board[co - 1][co].getPieceColour() == piece)
			{
				count++;
			} else if (count >= 5)
			{
				fiveInARow = true;
			} else
			{
				fiveInARow = false;
			}
		}

		return fiveInARow;
	}

	public boolean boardFull()
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
		return hasWon('w') || hasWon('b') || boardFull();
	}
}