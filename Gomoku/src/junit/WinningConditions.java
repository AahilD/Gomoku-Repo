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

	@Test
	public void testVerrifyHorrizontal_1()
	{
		Random r = new Random();

		int y = r.nextInt(19);
		int x = r.nextInt(19);

		if (y <= 9)
			for (int i = 0; i < 5; i++)
			{
				try
				{
					board.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					// TODO Auto-generated catch block
					System.out.println(e.toString());
				}
				y++;
			}
		else
			for (int i = 0; i < 5; i++)
			{
				try
				{
					board.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					System.out.println(e.toString());
				}
				y--;
			}

		board.verifyHorizontal('w');
		assertTrue(
				"VerifyHorizontal did not detect the win from: [" + x + ", "
						+ (y - 5) + "] to: [" + x + ", " + y + "]",
				board.verifyHorizontal(p1.getPieceColour()));
	}

	@Test
	public void testVerrifyHorrizontal_2()
	{
		Board b = new Board();
		Random r = new Random();

		int y = r.nextInt(19);
		int x = r.nextInt(19);

		if (y <= 9)
			for (int i = 0; i < 6; i++)
			{
				try
				{
					b.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					// TODO Auto-generated catch block
					System.out.println(e.toString());
				}
				y++;
			}
		else
			for (int i = 0; i < 6; i++)
			{
				try
				{
					b.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					System.out.println(e.toString());
				}
				y--;
			}

		b.verifyHorizontal('w');
		assertFalse(
				"VerifyHorizontal detected a win for 6 in arow, a win can only be 5 in a row (no more no less): ["
						+ x + ", " + (y - 6) + "] to: [" + x + ", " + y + "]",
				b.verifyHorizontal(p1.getPieceColour()));
	}

	@Test
	public void testVerrifyHorrizontal_3()
	{
		Board b = new Board();
		Random r = new Random();

		int y = r.nextInt(19);
		int x = r.nextInt(19);

		if (y <= 9)
			for (int i = 0; i < 4; i++)
			{
				try
				{
					b.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					// TODO Auto-generated catch block
					System.out.println(e.toString());
				}
				y++;
			}
		else
			for (int i = 0; i < 4; i++)
			{
				try
				{
					b.getBoard()[x][y].setPlayer(p1);
				} catch (IllegalMove e)
				{
					System.out.println(e.toString());
				}
				y--;
			}

		b.verifyHorizontal('w');
		assertFalse(
				"VerifyHorizontal detected a win with less than 5 in a row: ["
						+ x + ", " + (y - 4) + "] to: [" + x + ", " + y + "]",
				b.verifyHorizontal(p1.getPieceColour()));
	}
}