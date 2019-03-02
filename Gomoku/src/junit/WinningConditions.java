package junit;

import static org.junit.Assert.*;

import java.util.Random;
import org.junit.Test;

import broker.Board;
import broker.IllegalMove;
import broker.Player;

public class WinningConditions
{
	private Board board = new Board();
	private static Player p1 = new Player("PLAYER ONE", 'w');
	private Random r = new Random();
	private int x;
	private int y;
	private int numberOfPieces;

	private void addPiecesHorizontally()
	{
		if (y <= 9)
			for (int i = 0; i < numberOfPieces; i++)
			{
				try
				{
					printCoordinate();
					board.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					System.out.println(e.toString());
				}
				y++;
			}
		else
			for (int i = 0; i < numberOfPieces; i++)
			{
				try
				{
					printCoordinate();
					board.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					System.out.println(e.toString());
				}
				y--;
			}
	}

	private void addPiecesVertically()
	{
		if (x <= 9)
			for (int i = 0; i < numberOfPieces; i++)
			{
				try
				{
					printCoordinate();
					board.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					System.out.println(e.toString());
				}
				x++;
			}
		else
			for (int i = 0; i < numberOfPieces; i++)
			{
				try
				{
					printCoordinate();
					board.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					System.out.println(e.toString());
				}
				x--;
			}
	}

	private void addPiecesDiagonalLeft()
	{
		if (x > 6 && y < 12)
			for (int i = 0; i < numberOfPieces; i++)
			{
				try
				{
					printCoordinate();
					board.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					System.out.println(e.toString());
				}
				x--;
				y++;
			}
		else
			for (int i = 0; i < numberOfPieces; i++)
			{
				try
				{
					printCoordinate();
					board.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					System.out.println(e.toString());
				}
				x++;
				y--;
			}
	}

	private void addPiecesDiagonalRight()
	{
		if (x < 12 && y < 12)
			for (int i = 0; i < numberOfPieces; i++)
			{
				try
				{
					printCoordinate();
					board.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					System.out.println(e.toString());
				}
				x++;
				y++;
			}
		else if (x > 7 && y > 7)
			for (int i = 0; i < numberOfPieces; i++)
			{
				try
				{
					printCoordinate();
					board.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					System.out.println(e.toString());
				}
				x--;
				y--;
			}
		else
			for (int i = 0; i < numberOfPieces; i++)
			{
				try
				{
					printCoordinate();
					board.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					System.out.println(e.toString());
				}
				x++;
				y++;
			}
	}

	private void printCoordinate()
	{
		if (x < 0 || x > 18)
			System.out.println(
					"If you see this message it's not your"
							+ " fault it's mine\nX out of bounds: " + x);
		if (y < 0 || y > 18)
			System.out.println("If you see this message it's"
					+ " not your fault, it's mine\nY out of " + "bounds: " + y);
	}

	private void printBoardToConsole()
	{
		for (int row = 0; row < 19; row++)
		{
			for (int col = 0; col < 19; col++)
			{
				if (board.getBoard()[row][col].playedby() != null)
				{
					System.out.print(board.getBoard()[row][col].playedby()
							.getPieceColour());
				} else
				{
					System.out.print("+");
				}
			}

			System.out.println("");
		}
	}

	@Test
	public void testVerrifyHorrizontal_1()
	{
		board = new Board();
		int y = r.nextInt(19);
		int x = r.nextInt(19);
		numberOfPieces = 5;

		String message = "VerifyHorizontal did not detect the win from: [" + x
				+ ", " + (y - 5) + "] to: [";

		addPiecesHorizontally();

		// setup test
		message += x + ", " + y + "]";
		boolean condition = board.verifyHorizontal(p1.getPieceColour());

		// test
		assertTrue(message, condition);
	}

	@Test
	public void testVerrifyHorrizontal_2()
	{
		board = new Board();
		y = r.nextInt(19);
		x = r.nextInt(19);
		numberOfPieces = 6;

		String message = "VerifyHorizontal detected a win for 6 in arow, a win can only be 5 in a row (no more no less): ["
				+ x + ", " + y + "] to: [";

		addPiecesHorizontally();

		message += x + ", " + y + "]";
		boolean condition = board.verifyHorizontal(p1.getPieceColour());

		assertFalse(message, condition);
	}

	@Test
	public void testVerrifyHorrizontal_3()
	{
		board = new Board();
		y = r.nextInt(19);
		x = r.nextInt(19);
		numberOfPieces = 4;

		String message = "VerifyHorizontal detected a win with less than 5 in a row: ["
				+ x + ", " + (y - 4) + "] to: [";

		addPiecesHorizontally();

		message += x + ", " + y + "]";
		boolean condition = board.verifyHorizontal(p1.getPieceColour());

		assertFalse(message, condition);
	}

	@Test
	public void testVerrifyHorrizontal_4()
	{
		board = new Board();
		y = r.nextInt(19);
		x = r.nextInt(19);
		numberOfPieces = 4;

		int diff = 0;
		if (y <= 9)
			diff++;
		else
			diff--;

		String message = "VerifyHorizontal detected a win where there where 5 piece horizontally, but not consecutively: ["
				+ x + ", " + y + "] to: [";

		addPiecesHorizontally();

		y += diff;

		// Add a gap between consecutive pieces

		addPiecesHorizontally();

		message += x + ", " + y + "]";

		boolean condition = board.verifyHorizontal(p1.getPieceColour());

		assertFalse(message, condition);
	}

	@Test
	public void testVerrifyVeritcle_1()
	{
		board = new Board();
		x = r.nextInt(19);
		y = r.nextInt(19);
		numberOfPieces = 5;

		// setup test
		String message = "VerifyVertical did not detect the win from: [" + x
				+ ", " + y + "] to:";

		addPiecesVertically();

		message += "[" + x + ", " + y + "]";
		boolean condition = board.verifyVertical(p1.getPieceColour());

		// test
		assertTrue(message, condition);
	}

	@Test
	public void testVerrifyVeritcle_2()
	{
		board = new Board();
		x = r.nextInt(19);
		y = r.nextInt(19);
		numberOfPieces = 4;

		// setup test
		String message = "VerifyVertical did not detect the win from: [" + x
				+ ", " + y + "] to: [";

		addPiecesVertically();

		message += x + ", " + y + "]";

		boolean condition = board.verifyVertical(p1.getPieceColour());

		// test
		assertFalse(message, condition);
	}

	@Test
	public void testVerrifyVerticle_3()
	{
		board = new Board();
		x = r.nextInt(19);
		y = r.nextInt(19);
		numberOfPieces = 6;

		// setup test
		String message = "VerifyVertical detected a win when there was 6 in a row (should only detect a win for 5 in a row): ["
				+ x + ", " + y + "] to: [";

		addPiecesVertically();

		message += x + ", " + y + "]";

		boolean condition = board.verifyVertical(p1.getPieceColour());

		// test
		assertFalse(message, condition);
	}

	@Test
	public void testVerrifyVerticle_4()
	{
		board = new Board();
		x = r.nextInt(19);
		y = r.nextInt(19);
		numberOfPieces = 4;

		int diff = 0;
		if (x <= 9)
			diff++;
		else
			diff--;

		// setup test
		String message = "VerifyVertical detected a win when not all 5 pieces where contiguous to each other ["
				+ x + ", " + y + "] to: [";

		addPiecesVertically();

		x += diff;

		addPiecesVertically();

		message += x + ", " + y + "]";

		boolean condition = board.verifyVertical(p1.getPieceColour());

		printBoardToConsole();

		// test
		assertFalse(message, condition);
	}

	@Test
	public void testVerrifyDiagonalLeft_1()
	{
		board = new Board();
		x = r.nextInt(19);
		if (x < 4)
			y = r.nextInt(15) + 4;
		else if (x > 14)
			y = r.nextInt(15);
		else
			y = r.nextInt(19);

		numberOfPieces = 5;

		// setup test
		String message = "VerifyDiagonalLeft did not detect the win from: [" + x
				+ ", " + y + "] to:";

		addPiecesDiagonalLeft();

		message += "[" + x + ", " + y + "]";
		boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());

		// test
		assertTrue(message, condition);
	}

	@Test
	public void testVerifyDiagonalLeft_2()
	{
		board = new Board();
		x = r.nextInt(19);
		if (x < 4)
			y = r.nextInt(15) + 4;
		else if (x > 14)
			y = r.nextInt(15);
		else
			y = r.nextInt(19);

		numberOfPieces = r.nextInt(4) + 1;

		// setup test
		String message = "VerifyDiagonalLeft did not detect the win from: [" + x
				+ ", " + y + "] to: [";

		addPiecesDiagonalLeft();

		message += x + ", " + y + "]";

		boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());

		// test
		assertFalse(message, condition);
	}

	@Test
	public void testVerifyDiagonalLeft_3()
	{
		board = new Board();
		x = r.nextInt(19);
		if (x < 4)
			y = r.nextInt(15) + 4;
		else if (x > 14)
			y = r.nextInt(15);
		else
			y = r.nextInt(19);

		numberOfPieces = 6;

		// setup test
		String message = "VerifyDiagonalLeft detected a win when there was 6 in a row (should only detect a win for 5 in a row): ["
				+ x + ", " + y + "] to: [";

		addPiecesDiagonalLeft();

		message += x + ", " + y + "]";

		boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());

		// test
		assertFalse(message, condition);
	}

	@Test
	public void testVerrifyDiagonalLeft_4()
	{
		board = new Board();
		x = 7;
		y = 7;

		numberOfPieces = 4;

		int diff = 0;
		if (x <= 9)
			diff++;
		else
			diff--;

		// setup test
		String message = "VerifyDiagonalLeft detected a win when not all 5 pieces where contiguous to each other ["
				+ x + ", " + y + "] to: [";

		addPiecesDiagonalLeft();

		x += diff;
		numberOfPieces = 1;

		addPiecesDiagonalRight();

		message += x + ", " + y + "]";

		boolean condition = board.verifyDiagonalLeft((p1.getPieceColour()));

		printBoardToConsole();

		// test
		assertFalse(message, condition);
	}

	@Test
	public void testVerrifyDiagonalRight_1()
	{
		board = new Board();
		x = r.nextInt(19);
		if (x < 4)
			y = r.nextInt(15) + 4;
		else if (x > 14)
			y = r.nextInt(15);
		else
			y = r.nextInt(19);

		numberOfPieces = 5;

		// setup test
		String message = "VerifyDiagonalRight did not detect the win from: ["
				+ x + ", " + y + "] to:";

		addPiecesDiagonalRight();

		message += "[" + x + ", " + y + "]";
		boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());

		// test
		assertTrue(message, condition);
	}

	@Test
	public void testVerifyDiagonalRight_2()
	{
		board = new Board();
		x = r.nextInt(19);
		if (x < 4)
			y = r.nextInt(15) + 4;
		else if (x > 14)
			y = r.nextInt(15);
		else
			y = r.nextInt(19);

		numberOfPieces = r.nextInt(4) + 1;

		// setup test
		String message = "VerifyDiagonalRight did not detect the win from: ["
				+ x + ", " + y + "] to: [";

		addPiecesDiagonalRight();

		message += x + ", " + y + "]";

		boolean condition = board.verifyDiagonalRight(p1.getPieceColour());

		// test
		assertFalse(message, condition);
	}

	@Test
	public void testVerifyDiagonalRight_3()
	{
		board = new Board();
		x = r.nextInt(18);
		if (x < 4)
			y = r.nextInt(15) + 4;
		else if (x > 14)
			y = r.nextInt(15);
		else
			y = r.nextInt(19);

		numberOfPieces = 6;

		// setup test
		String message = "VerifyDiagonalRight detected a win when there was 6 in a row (should only detect a win for 5 in a row): ["
				+ x + ", " + y + "] to: [";

		addPiecesDiagonalRight();

		message += x + ", " + y + "]";
		message += "";

		boolean condition = board.verifyDiagonalRight(p1.getPieceColour());

		// test
		assertFalse(message, condition);
	}

	@Test
	public void testVerrifyDiagonalRight_4()
	{
		board = new Board();
		x = 7;
		y = 7;

		numberOfPieces = 4;

		// setup test
		String message = "VerifyDiagonalRight detected a win when not all 5 pieces where contiguous to each other ["
				+ x + ", " + y + "] to: [";

		addPiecesDiagonalRight();

		x--;
		y++;
		numberOfPieces = 1;

		addPiecesDiagonalRight();

		message += x + ", " + y + "]";

		boolean condition = board.verifyDiagonalRight((p1.getPieceColour()));

		printBoardToConsole();

		// test
		assertFalse(message, condition);
	}
}