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
	//Initializes a 2D array for the board //
	private Square[][] board;

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
	 * @param piece
	 * @return
	 */
	private boolean verifyHorizontal(char piece)
	{
		boolean fiveInARow = false;
		int i = 0;
		for (int row = 0; row < board.length && !fiveInARow; row++)
		{
			for (int col = 0; col < board[row].length; col++)
			{
				if (Square.getPlayer().getPiece().getBlackOrWhite()==piece)
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

	private boolean verifyVertical(char piece)
	{
		boolean fiveInARow = false;
		int i = 0;
		for (int r = 0; r < board.length && !fiveInARow; r++)
		{
			for (int c = 0; c < board[r].length; c++)
			{
				if (Square.getPlayer().getPiece().getBlackOrWhite() == piece)
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

	private boolean verifyDiagonalLeft(char piece)
	{
		boolean fiveInARow = false;
		return fiveInARow;
	}

	private boolean verifyDiagonalRight(char piece)
	{
		boolean fiveInARow = false;
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
				|| verifyDiagonalLeft('w') || verifyDiagonalLeft('b')
				|| verifyDiagonalRight('w') || verifyDiagonalRight('b')
				|| boardFull();
	}
}