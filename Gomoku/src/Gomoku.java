import java.util.Scanner;

import broker.Game;
import broker.IllegalMove;
import broker.Player;
import broker.Square;
import broker.WinAndLosses;

/**
 * @author manu
 *
 *         Use this class and method to test your code
 */
public class Gomoku
{
    public static void main(String[] arg)
    {
	Game game;

	boolean isPVE = false;
	int pveLVL = 0;

	Scanner scan = new Scanner(System.in);

	System.out.print("Player 1, please enter deisred user name: ");
	String p1usrname = scan.next();

	System.out.print("Player 2, please enter desired user name: ");
	String p2username = scan.next();

	game = new Game(p1usrname, p2username);

	boolean gameover = false;
	boolean anotherRound = false;

	while (!gameover || anotherRound)
	{
	    printBoard(game);

	    String currentPlayer = game.getTurnPlayer().getUserName();

	    boolean turnDone = false;

	    do
	    {
		System.out.println(
			currentPlayer + " enter x coordinates to play move");
		int x = scan.nextInt();
		System.out.println(
			currentPlayer + " enter y coordinates to play move");
		int y = scan.nextInt();

		try
		{
		    game.makeMove(x, y);
		    turnDone = true;
		    game.incrementPlayerTurn();
		    
		} catch (IllegalMove e)
		{
		    System.out.println(e.toString());
		} catch (WinAndLosses e)
		{
		    printBoard(game);
		    System.out.println(e.getWinner().getUserName() + " wins!!!");
		    gameover = true;
		    turnDone = true;
		    System.out.println("do you wish to pay an other round [y/n]?");
		    char ans = scan.next().charAt(0);
		    
		    if(ans == 'y')
		    {
			game = new Game(game);
			anotherRound = true;
		    }else
		    {
			anotherRound = false;
		    }
		}
		
	    } while (!turnDone);
	    
	    System.out.println("Thank you for playing, " + p1usrname + " and " + p2username);

	}
    }
    
    private static void printBoard(Game game)
    {
	for (int x = 0; x < game.getCurrentBoard().getBoard().length; x++)
	    {
		for (int y = 0; y < game.getCurrentBoard()
			.getBoard()[x].length; y++)
		{
		    char sqrVal = '-';
		    if (!game.getCurrentBoard().getBoard()[x][y].isEmpty())
		    {
			sqrVal = game.getCurrentBoard().getBoard()[x][y]
				.playedBy().getPieceColour();
		    }

		    System.out.print(sqrVal);
		}
		System.out.print("\n");
	    }
    }

}
