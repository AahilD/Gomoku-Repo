import java.util.Scanner;

import broker.Game;
import broker.IllegalMove;

/**
 * @author manu
 *
 *         Use this class and method to test your code
 */
public class Gomoku
{
    /**
     * This main method is only to provide you with a text based application for
     * testing purposes.
     * 
     * @param args
     *
    public static void main(String[] args)
    {
	Scanner in = new Scanner(System.in);
	int x = 0;
	int y = 0;
	boolean gameover = false;

	System.out.print("Player 1, please enter your username: ");
	String p1Uname = in.next();

	System.out.print("Player2, please enter your username: ");
	String p2Uname = in.next();

	Game game = new Game(p1Uname, p2Uname);

	do
	{
	    System.out.println("Turn: " + game.getTurnCount());
	    System.out.println("___________________");
	    for (int row = 0; row < game.getCurrentBoard()
		    .getBoard().length; row++)
	    {
		for (int col = 0; col < game.getCurrentBoard()
			.getBoard().length; col++)
		{
		    if (game.getCurrentBoard().getBoard()[row][col].isEmpty())
			System.out.print("|_|");
		    else
			System.out.print("|"
				+ game.getCurrentBoard().getBoard()[row][col]
					.playedBy().getPieceColour()
				+ "|");
		}
		System.out.println("");
	    }

	    System.out.println(
		    "Your turn, " + game.getTurnPlayer().getUserName());

	    do
	    {

		System.out.print("Please enter the x coordinate (1 - 19): ");
		x = in.nextInt();

		System.out.print("Please enter the y coordinate (1 - 19): ");
		y = in.nextInt();

	    } while (x > 0 && x < 20 && y > 0 && y < 20);

	    try
	    {
		gameover = game.makeMove(x + 1, y + 1);

		if (!gameover)
		    game.incrementPlayerTurn();

	    } catch (IllegalMove e)
	    {
		System.out
			.println("That was an Illegal Move, please try again.");
	    }

	} while (!gameover);

	System.out.println(game.getTurnPlayer().getUserName() + ", you win!");
    }*/
}
