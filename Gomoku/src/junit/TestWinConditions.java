package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;
import org.junit.Test;

import broker.Board;
import broker.IllegalMove;
import broker.Player;

public class TestWinConditions
{
    private Board board = new Board();
    private static Player p1 = new Player("PLAYER ONE", 'w');
    private Random r = new Random();
    private int x;
    private int y;
    private int numberOfPieces;

    // TODO Emmanuel fix tests remove randomization feature
    
    private void addPiecesToBoard(ArrayList<int[]> coorList)
    {
	board = new Board();
	for (int index = 0; index < coorList.size(); index++)
	{
	    int x = coorList.get(index)[0];
	    int y = coorList.get(index)[1];
	    
	    try
	    {
		board.getBoard()[x][y].setPlayer(p1);
	    } catch (IllegalMove e)
	    {
		e.printStackTrace();
	    }
	}
    }
    
    private void printCoordinate()
    {
	if (x < 0 || x > 18)
	    System.out.println("If you see this message it's not your"
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
		if (board.getBoard()[row][col].playedBy() != null)
		{
		    System.out.print(board.getBoard()[row][col].playedBy()
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
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {5, 5});
	coorList.add(new int[] {5, 6});
	coorList.add(new int[] {5, 7});
	coorList.add(new int[] {5, 8});
	coorList.add(new int[] {5, 9});
	
	addPiecesToBoard(coorList);
	
	boolean condition = board.verifyHorizontal(p1.getPieceColour());

	// test
	assertTrue("VerifyHorizontal did not detect the win of 5 in a row", condition);
    }

    @Test
    public void testVerrifyHorrizontal_2()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {5, 5});
	coorList.add(new int[] {5, 6});
	coorList.add(new int[] {5, 7});
	coorList.add(new int[] {5, 8});
	coorList.add(new int[] {5, 9});
	coorList.add(new int[] {5, 10});
	
	addPiecesToBoard(coorList);
	
	boolean condition = board.verifyHorizontal(p1.getPieceColour());

	assertFalse("VerifyHorizontal should not have detected a win of 6 in a row.", condition);
    }

    @Test
    public void testVerrifyHorrizontal_3()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {5, 5});
	coorList.add(new int[] {5, 6});
	coorList.add(new int[] {5, 7});
	coorList.add(new int[] {5, 8});
	
	addPiecesToBoard(coorList);

	boolean condition = board.verifyHorizontal(p1.getPieceColour());

	assertFalse("VerrifyHorrizontal should not detect a win with only 4 in a row.", condition);
    }

    @Test
    public void testVerrifyHorrizontal_4()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {5, 5});
	coorList.add(new int[] {5, 6});
	coorList.add(new int[] {5, 7});
	coorList.add(new int[] {5, 8});
	coorList.add(new int[] {5, 10});


	addPiecesToBoard(coorList);
	
	boolean condition = board.verifyHorizontal(p1.getPieceColour());

	assertFalse("Verrify Horrizontally detected a win where the 5 pieces where not contiguous.", condition);
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

//	addPiecesVertically();

	message += "[" + x + ", " + y + "]";
	boolean condition = board.verifyVertical(p1.getPieceColour());

	// test
	//When it randomizes plays it will play 6 pieces and expect a win-LN
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

	//addPiecesVertically();

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

	//addPiecesVertically();

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

	//addPiecesVertically();

	x += diff;

	//addPiecesVertically();

	message += x + ", " + y + "]";

	boolean condition = board.verifyVertical(p1.getPieceColour());

	//THIS WAS GIVING ME ERRORS??? so I commented it out-LN
	//printBoardToConsole();

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

	//addPiecesDiagonalLeft();

	message += "[" + x + ", " + y + "]";
	boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());

	// test
	//When it randomizes plays it will play 6 pieces and expect a win-LN
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

	//addPiecesDiagonalLeft();

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

	//addPiecesDiagonalLeft();

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

	//addPiecesDiagonalLeft();

	x += diff;
	numberOfPieces = 1;

	//addPiecesDiagonalRight();

	message += x + ", " + y + "]";

	boolean condition = board.verifyDiagonalLeft((p1.getPieceColour()));

	//THIS WAS GIVING ME ERRORS??? so I commented it out-LN
	//printBoardToConsole();

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

	//addPiecesDiagonalRight();

	message += "[" + x + ", " + y + "]";
	boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());
	
	printBoardToConsole();
	
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

	//addPiecesDiagonalRight();

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

	//addPiecesDiagonalRight();

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

	//addPiecesDiagonalRight();

	x--;
	y++;
	numberOfPieces = 1;

	//addPiecesDiagonalRight();

	message += x + ", " + y + "]";

	boolean condition = board.verifyDiagonalRight((p1.getPieceColour()));

	// test
	assertFalse(message, condition);
    }
}