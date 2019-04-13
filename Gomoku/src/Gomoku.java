import java.util.Scanner;

import broker.Game;
import broker.IllegalMove;
import broker.Player;
import broker.Square;
import broker.WinAndLosses;
import controller.GameController;
import controller.PVEnvironment;

/**
 * @author manu
 *
 *         Use this class and method to test your code
 */
public class Gomoku
{
    public static void main(String[] arg)
    {
	boolean isPVE = false;
	int pveLVL = 0;

	Scanner scan = new Scanner(System.in);

	int mode = 0;
	do
	{
	    System.out.print(
		    "Enter \"1\" for 2Pplayer, \"2\" to play against computer: ");
	    try
	    {
		mode = scan.nextInt();
	    } catch (Exception e)
	    {
		mode = -1;
	    }

	    if (mode != 1 && mode != 2)
	    {
		System.out.println(
			"Invalid input, you must select mode 1 or 2 (only enter the number)");
	    }

	} while (mode != 1 && mode != 2);

	System.out.print("Player 1, please enter deisred user name: ");
	String p1usrname = scan.next();

	if (mode == 1)
	{

	    System.out.print("Player 2, please enter desired user name: ");
	    String p2username = scan.next();

	    playerVplayer(p1usrname, p2username);
	} else
	{
	    playerVenvironment(p1usrname);

	}
    }

    private static void playerVplayer(String p1uname, String p2uname)
    {
	Game game = new Game(p1uname, p2uname);
	Scanner scan = new Scanner(System.in);

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
		    System.out
			    .println(e.getWinner().getUserName() + " wins!!!");
		    gameover = true;
		    turnDone = true;
		    System.out.println(
			    "do you wish to pay an other round [y/n]?");
		    char ans = scan.next().charAt(0);

		    if (ans == 'y')
		    {
			game = new Game(game);
			anotherRound = true;
		    } else
		    {
			anotherRound = false;
		    }
		}

	    } while (!turnDone);

	    System.out.println(
		    "Thank you for playing, " + p1uname + " and " + p2uname);

	}

    }

    private static void playerVenvironment(String p1username)
    {
	// here i Only use PVE for environment logic

	Scanner scan = new Scanner(System.in);
	System.out.println("Please enter level of dificulty: easy > (0,1,2) < hard");
	int lvl = scan.nextInt();
	

	PVEnvironment.initializeGameForTextBaseApplication(p1username, lvl);

	boolean gameover = false;
	boolean anotherRound = false;

	while (!gameover || anotherRound)
	{
	    printBoard(PVEnvironment.getGame());

	    String currentPlayer = PVEnvironment.getGame().getTurnPlayer()
		    .getUserName();

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
		    PVEnvironment.playMoveAtForTextBaseApplication(x, y);
		    turnDone = true;
		} catch (IllegalMove e)
		{
		    System.out.println(e.toString());
		} catch (WinAndLosses e)
		{
		    printBoard(PVEnvironment.getGame());
		    System.out
			    .println(e.getWinner().getUserName() + " wins!!!");
		    gameover = true;
		    turnDone = true;
		    System.out.println(
			    "do you wish to pay an other round [y/n]?");
		    char ans = scan.next().charAt(0);

		    if (ans == 'y')
		    {
			PVEnvironment.playAnOtherRound();
			anotherRound = true;
		    } else
		    {
			anotherRound = false;
		    }
		}

	    } while (!turnDone);

	    System.out.println("Thank you for playing, " + p1username + ".");

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
		    sqrVal = game.getCurrentBoard().getBoard()[x][y].playedBy()
			    .getPieceColour();
		}

		System.out.print(sqrVal);
	    }
	    System.out.print("\n");
	}
    }

}
