package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

import broker.Board;
import broker.IllegalMove;
import broker.Player;

/**
 * These tests will ensure that win condition methods are working as they should
 * The ability for these tests to function as they should relly on the player class to be
 * fully functional. Make sure all tests for player class pass before running these ones.
 * 
 * @author manu
 *
 */
public class TestWinConditions
{
    private final static char PLAYER_0NE_PIECE_COLOUR = 'w';
    private final static char PLAYER_TWO_PIECE_COLOUR = 'b';
    
    private Board board;
    private static Player p1 = new Player("PLAYER ONE", PLAYER_0NE_PIECE_COLOUR);
    private static Player p2 = new Player("PLAYER TWO", PLAYER_TWO_PIECE_COLOUR);
    
    private void appendPiecesToBoard(Player p, ArrayList<int[]> coorList)
    {
	for (int index = 0; index < coorList.size(); index++)
	{
	    int x = coorList.get(index)[0];
	    int y = coorList.get(index)[1];
	    
	    try
	    {
		board.getBoard()[x][y].setPlayer(p);
	    } catch (IllegalMove e)
	    {
		e.printStackTrace();
	    }
	}
    }
    
    private void addPiecesToBoard(Player p, ArrayList<int[]> coorList)
    {
	board = new Board();
	for (int index = 0; index < coorList.size(); index++)
	{
	    int x = coorList.get(index)[0];
	    int y = coorList.get(index)[1];
	    
	    try
	    {
		board.getBoard()[x][y].setPlayer(p);
	    } catch (IllegalMove e)
	    {
		e.printStackTrace();
	    }
	}
    }
    
    private void addDifferentPiecesToBoard(ArrayList<int[]> coorList)
    {
	board = new Board();
	
	for (int index = 0; index < coorList.size(); index++)
	{
	    int x = coorList.get(index)[0];
	    int y = coorList.get(index)[1];
	    
	    try
	    {
		if(index % 2 == 0)
		    board.getBoard()[x][y].setPlayer(p1);
		else
		    board.getBoard()[x][y].setPlayer(p2);
	    } catch (IllegalMove e)
	    {
		e.printStackTrace();
	    }
	}
    }

    /**
     * place this method anywhere to display the full set of test values
     */
    @SuppressWarnings("unused")
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

    /**
     * Test verify horizontal detects a win
     * (5 in a row of same colour)
     */
    @Test
    public void testVerifyHorrizontal_1()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {5, 5}); // 1
	coorList.add(new int[] {5, 6}); // 2
	coorList.add(new int[] {5, 7}); // 3
	coorList.add(new int[] {5, 8}); // 4
	coorList.add(new int[] {5, 9}); // 5
	
	addPiecesToBoard(p1, coorList);
	
	String message = "VerifyHorizontal should have detected the win of 5 in a row";
	boolean condition = board.verifyHorizontal(p1.getPieceColour());
	
	assertTrue(message, condition);
    }

    /**
     * Test that anything over 5 in a row is NOT counted as a win
     */
    @Test
    public void testVerifyHorrizontal_2()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {5, 5}); // 1
	coorList.add(new int[] {5, 6}); // 2
	coorList.add(new int[] {5, 7}); // 3
	coorList.add(new int[] {5, 8}); // 4
	coorList.add(new int[] {5, 9}); // 5
	coorList.add(new int[] {5, 10}); // 6 !!!
	
	addPiecesToBoard(p1, coorList);
	
	String message = "VerifyHorizontal should not have detected a win of 6 in a row.";
	boolean condition = board.verifyHorizontal(p1.getPieceColour());

	assertFalse(message, condition);
    }

    /**
     * test that anything less than 5 is not a win
     */
    @Test
    public void testVerifyHorrizontal_3()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {5, 5}); // 1
	coorList.add(new int[] {5, 6}); // 2
	coorList.add(new int[] {5, 7}); // 3
	coorList.add(new int[] {5, 8}); // 4
	 // 5!!!!
	
	addPiecesToBoard(p1, coorList);

	String message = "VerrifyHorrizontal should not detect a win with only 4 in a row.";
	boolean condition = board.verifyHorizontal(p1.getPieceColour());

	assertFalse(message, condition);
    }

    /**
     * test that a 5 pieces separated by empty square is NOT a win
     */
    @Test
    public void testVerifyHorrizontal_4()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {5, 5}); // 1
	coorList.add(new int[] {5, 6}); // 2
	coorList.add(new int[] {5, 7}); // 3
	coorList.add(new int[] {5, 8}); // 4
	//skip square [5][9] !!!
	coorList.add(new int[] {5, 10}); // 5


	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Horrizontally detected a win where the 5 pieces where not contiguous.";
	boolean condition = board.verifyHorizontal(p1.getPieceColour());

	assertFalse(message, condition);
    }

    /**
     * test that a 5 pieces in a row NOT of the same colour is NOT a win
     */
    @Test
    public void testVerifyHorrizontal_5()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {5, 5}); // 1
	coorList.add(new int[] {5, 6}); // 2
	coorList.add(new int[] {5, 7}); // 3
	coorList.add(new int[] {5, 8}); // 4
	coorList.add(new int[] {5, 9}); // 5


	addDifferentPiecesToBoard(coorList); // add DIFFERENT pieces to board !!! alternate colours
	
	String message = "Verify Horizontal should NOT have detect a win with 5 pieces in a row of different colour";
	
	// p1
	boolean condition = board.verifyHorizontal(p1.getPieceColour());
	assertFalse(message, condition);
	
	// p2
	condition = board.verifyHorizontal(p2.getPieceColour());
	assertFalse(message, condition);
    }
    
    /**
     * same as testVerrifyHorrizontal_1
     */
    @Test
    public void testVerifyVeritcle_1()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {5, 5}); // 1
	coorList.add(new int[] {6, 5}); // 2
	coorList.add(new int[] {7, 5}); // 3
	coorList.add(new int[] {8, 5}); // 4
	coorList.add(new int[] {9, 5}); // 5
	
	addPiecesToBoard(p1, coorList);
	
	String message = "VerifyVertical should have detected a win from: [5][5] - [9][5];";
	boolean condition = board.verifyVertical(p1.getPieceColour());
	
	assertTrue(message, condition);
    }

    /**
     * same as testVerrifyHorrizontal_2
     */
    @Test
    public void testVerifyVeritcle_2()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {5, 5}); // 1
	coorList.add(new int[] {6, 5}); // 2
	coorList.add(new int[] {7, 5}); // 3
	coorList.add(new int[] {8, 5}); // 4
	coorList.add(new int[] {9, 5}); // 5
	coorList.add(new int[] {10, 5}); // 6 !!!
	
	addPiecesToBoard(p1, coorList);
	
	String message = "VerifyVertical detected a win from: [5][5] - [10][5], when it wasn't supose to.";
	boolean condition = board.verifyVertical(p1.getPieceColour());
	
	assertFalse(message, condition);
    }

    /**
     * same as testVerrifyHorrizontal_3
     */
    @Test
    public void testVerifyVerticle_3()
    {

	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {5, 5}); // 1
	coorList.add(new int[] {6, 5}); // 2
	coorList.add(new int[] {7, 5}); // 3
	coorList.add(new int[] {8, 5}); // 4
	 // 5 !!!
	
	addPiecesToBoard(p1, coorList);

	String message = "verrifyVerticle should NOT have detected a win with less than 5 pieces in a row.";
	boolean condition = board.verifyVertical(p1.getPieceColour());
	
	assertFalse(message, condition);
    }

    /**
     * same as testVerrifyHorrizontal_4
     */
    @Test
    public void testVerifyVerticle_4()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {5, 5}); // 1
	coorList.add(new int[] {6, 5}); // 2
	coorList.add(new int[] {7, 5}); // 3
	coorList.add(new int[] {8, 5}); // 4
	//skip square [9][5] !!!
	coorList.add(new int[] {10, 5}); // 5


	addPiecesToBoard(p1, coorList);
	
	String message = "VerifyVertical should NOT have detected a win with 5 pieces seperated by an empty square.";
	boolean condition = board.verifyVertical(p1.getPieceColour());
	
	assertFalse(message, condition);
    }

    /**
     * test that a 5 pieces in a row NOT of the same colour is NOT a win
     */
    @Test
    public void testVerifyVerticle_5()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {5, 5}); // 1
	coorList.add(new int[] {6, 5}); // 2
	coorList.add(new int[] {7, 5}); // 3
	coorList.add(new int[] {8, 5}); // 4
	coorList.add(new int[] {9, 5}); // 5


	addDifferentPiecesToBoard(coorList); //!!!
	
	String message = "Verify Horizontal should NOT have detect a win with 5 pieces in a row of different colour";
	
	// p1
	boolean condition = board.verifyHorizontal(p1.getPieceColour());
	assertFalse(message, condition);
	// p2
	condition = board.verifyHorizontal(p2.getPieceColour());
	assertFalse(message, condition);
    }
    
    /**
     * check if win is properly detected on the top right half of the board
     */
    @Test
    public void testVerifyDiagonalLeft_1()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {3, 5}); // 1
	coorList.add(new int[] {4, 6}); // 2
	coorList.add(new int[] {5, 7}); // 3
	coorList.add(new int[] {6, 8}); // 4
	coorList.add(new int[] {7, 9}); // 5
	
	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Diagonal left should have detected a win.";
	boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());
	
	assertTrue(message, condition);
    }

    /**
     * verify a win at the most top right corner where exactly 5 pieces fit.
     */
    @Test
    public void testVerifyDiagonalLeft_2()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {0, 14}); // 1
	coorList.add(new int[] {1, 15}); // 2
	coorList.add(new int[] {2, 16}); // 3
	coorList.add(new int[] {3, 17}); // 4
	coorList.add(new int[] {4, 18}); // 5
	
	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Diagonal left should have detected a win.";
	boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());
	
	assertTrue(message, condition);
    }

    /**
     * verify a win on the bottom left half of the board
     */
    @Test
    public void testVerifyDiagonalLeft_3()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {10, 4}); // 1
	coorList.add(new int[] {11, 5}); // 2
	coorList.add(new int[] {12, 6}); // 3
	coorList.add(new int[] {13, 7}); // 4
	coorList.add(new int[] {14, 8}); // 5
	
	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Diagonal left should have detected a win.";
	boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());
	
	assertTrue(message, condition);
    }

    /**
     * verify a win at the most bottom left corner where exactly 5 pieces fit.
     */
    @Test
    public void testVerifyDiagonalLeft_4()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {14, 0}); // 1
	coorList.add(new int[] {15, 1}); // 2
	coorList.add(new int[] {16, 2}); // 3
	coorList.add(new int[] {17, 3}); // 4
	coorList.add(new int[] {18, 4}); // 5

	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Diagonal left should have detected a win.";
	boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());
	
	assertTrue(message, condition);
    }
    
    /**
     * verify that anything over 5 in a row does not count as a win
     */
    @Test
    public void testVerifyDiagonalLeft_5()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {10, 4}); // 1
	coorList.add(new int[] {11, 5}); // 2
	coorList.add(new int[] {12, 6}); // 3
	coorList.add(new int[] {13, 7}); // 4
	coorList.add(new int[] {14, 8}); // 5
	coorList.add(new int[] {15, 9}); // 6 !!!
	
	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Diagonal left should NOT have detected a win with more than 5 in a row.";
	boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());
	
	assertFalse(message, condition);
    }

    /**
     * verify that anything less than 5 does NOT count as a win.
     */
    @Test
    public void testVerifyDiagonalLeft_6()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {4, 4}); // 1
	coorList.add(new int[] {5, 5}); // 2
	coorList.add(new int[] {6, 6}); // 3
	coorList.add(new int[] {7, 7}); // 4
	 // 5 !!!
	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Diagonal left should NOT have detected a win with less than 5 in a row.";
	boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());
	
	assertFalse(message, condition);
    }
    
    /**
     * verify that 5 not in a row does not count as a win
     */
    @Test
    public void testVerifyDiagonalLeft_7()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {4, 4}); // 1
	coorList.add(new int[] {5, 5}); // 2
	coorList.add(new int[] {6, 6}); // 3
	coorList.add(new int[] {7, 7}); // 4
	// skip square [8][8] !!!
	coorList.add(new int[] {9, 9}); // 5
	
	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Diagonal left should NOT have detected a win with 5 pieces seperated by an empty space.";
	boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());
	
	assertFalse(message, condition);
    }
    
    /**
     * verify that 5 in a row of different colour pieces does not count as a win
     */
    @Test
    public void testVerifyDiagonalLeft_8()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {4, 4}); // 1
	coorList.add(new int[] {5, 5}); // 2
	coorList.add(new int[] {6, 6}); // 3
	coorList.add(new int[] {7, 7}); // 4
	coorList.add(new int[] {8, 8}); // 5
	
	addDifferentPiecesToBoard(coorList); // !!!
	
	String message = "Verrify Diagonal left should NOT have detected a win with 5 pieces in a row of different colour.";
	
	// p1
	boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());
	assertFalse(message, condition);
	// p2
	condition = board.verifyDiagonalLeft(p2.getPieceColour());
	assertFalse(message, condition);
    }    
    /**
     * check if win is properly detected on the top left half of the board
     */
    @Test
    public void testVerifyDiagonalRight_1()
    {

	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {7, 5}); // 1
	coorList.add(new int[] {6, 6}); // 2
	coorList.add(new int[] {5, 7}); // 3
	coorList.add(new int[] {4, 8}); // 4
	coorList.add(new int[] {3, 9}); // 5
	
	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Diagonal left should have detected a win.";
	boolean condition = board.verifyDiagonalRight(p1.getPieceColour());
	
	assertTrue(message, condition);
    }

    /**
     * verify a win at the most top left corner where exactly 5 pieces fit.
     */
    @Test
    public void testVerifyDiagonalRight_2()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {4, 0}); // 1
	coorList.add(new int[] {3, 1}); // 2
	coorList.add(new int[] {2, 2}); // 3
	coorList.add(new int[] {1, 3}); // 4
	coorList.add(new int[] {0, 4}); // 5
	
	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Diagonal left should have detected a win.";
	boolean condition = board.verifyDiagonalRight(p1.getPieceColour());
	
	assertTrue(message, condition);
    }

    /**
     * verify a win on the bottom right half of the board
     */
    @Test
    public void testVerifyDiagonalRight_3()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {10, 14}); // 1
	coorList.add(new int[] {11, 13}); // 2
	coorList.add(new int[] {12, 12}); // 3
	coorList.add(new int[] {13, 11}); // 4
	coorList.add(new int[] {14, 10}); // 5
	
	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Diagonal left should have detected a win.";
	boolean condition = board.verifyDiagonalRight(p1.getPieceColour());
	
	assertTrue(message, condition);
    }

    /**
     * verify a win at the most bottom right corner where exactly 5 pieces fit.
     */
    @Test
    public void testVerifyDiagonalRight_4()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {14, 18}); // 1
	coorList.add(new int[] {15, 17}); // 2
	coorList.add(new int[] {16, 16}); // 3
	coorList.add(new int[] {17, 15}); // 4
	coorList.add(new int[] {18, 14}); // 5
	
	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Diagonal left should have detected a win.";
	boolean condition = board.verifyDiagonalRight(p1.getPieceColour());
	
	assertTrue(message, condition);
    }
    
    /**
     * verify that anything over 5 in a row does not count as a win
     */
    @Test
    public void testVerifyDiagonalRight_5()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {8, 10}); // 1
	coorList.add(new int[] {9, 9});  // 2
	coorList.add(new int[] {10, 8}); // 3
	coorList.add(new int[] {11, 7}); // 4
	coorList.add(new int[] {12, 6}); // 5
	coorList.add(new int[] {13, 5}); // 6 !!!
	
	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Diagonal left should NOT have detected a win with more than 5 in a row.";
	boolean condition = board.verifyDiagonalRight(p1.getPieceColour());
	
	assertFalse(message, condition);
    }

    /**
     * verify that anything less than 5 does NOT count as a win.
     */
    @Test
    public void testVerifyDiagonalRight_6()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {8, 10}); // 1
	coorList.add(new int[] {9, 9});  // 2
	coorList.add(new int[] {10, 8}); // 3
	coorList.add(new int[] {11, 7}); // 4
	 // 5 !!!
	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Diagonal left should NOT have detected a win with less than 5 in a row.";
	boolean condition = board.verifyDiagonalRight(p1.getPieceColour());
	
	assertFalse(message, condition);
    }
    
    /**
     * verify that 5 not in a row does not count as a win
     */
    @Test
    public void testVerifyDiagonalRight_7()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {8, 10}); // 1
	coorList.add(new int[] {9, 9});  // 2
	coorList.add(new int[] {10, 8}); // 3
	coorList.add(new int[] {11, 7}); // 4
	// skip square [12][6] !!!
	coorList.add(new int[] {13, 5}); // 5
	
	addPiecesToBoard(p1, coorList);
	
	String message = "Verrify Diagonal left should NOT have detected a win with 5 pieces seperated by an empty space.";
	boolean condition = board.verifyDiagonalRight(p1.getPieceColour());
	
	assertFalse(message, condition);
    }
    
    /**
     * verify that 5 different pieces in a row does not count as a win
     */
    @Test
    public void testVerifyDiagonalRight_8()
    {
	ArrayList<int[]> coorList = new ArrayList<int[]>();
	coorList.add(new int[] {8, 10}); // 1
	coorList.add(new int[] {9, 9});  // 2
	coorList.add(new int[] {10, 8}); // 3
	coorList.add(new int[] {11, 7}); // 4
	coorList.add(new int[] {12, 6}); // 5
	
	addDifferentPiecesToBoard(coorList); // !!!
	
	String message = "Verrify Diagonal left should NOT have detected a win with 5 pieces in a row of different colour.";
	
	// p1
	boolean condition = board.verifyDiagonalRight(p1.getPieceColour());
	assertFalse(message, condition);
	// p2
	condition = board.verifyDiagonalRight(p2.getPieceColour());
	assertFalse(message, condition);
    }
    
    /**
     * This test replicates a bug that was found during front end testing
     * 
     * see screenshot 001.png in 'res/bugscreenshot' folder
     */
    @Test
    public void testVerifyDiagonalLeft_9()
    {
	
	ArrayList<int[]> p1_coordinates = new ArrayList<int[]>();
	ArrayList<int[]> p2_coordinates = new ArrayList<int[]>();
	
	// sequence pieces
	p1_coordinates.add(new int[] {6, 10}); // 1 -- player one
	p1_coordinates.add(new int[] {7, 11});  // 2 -- player one
	p1_coordinates.add(new int[] {8, 12}); //3 -- player one
	p2_coordinates.add(new int[] {9, 13}); // 4 -- player two
	p1_coordinates.add(new int[] {11, 7}); // 5 -- player one
	
	//other pieces that where played
	p1_coordinates.add(new int[] {5, 16});
	p2_coordinates.add(new int[] {2, 2});
	p2_coordinates.add(new int[] {2, 17});
	p2_coordinates.add(new int[] {8, 6});
	
	addPiecesToBoard(p1, p1_coordinates);
	appendPiecesToBoard(p2, p2_coordinates);
	
	String msg = "A win was detected with a single black piece interupting the sequence of 5 white pieces.";
	boolean condition = board.verifyDiagonalLeft(p1.getPieceColour());
	
	assertFalse(msg, condition);
    }
}