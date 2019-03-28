package jUnitTests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import broker.Player;

/**
 * will test the player class
 * @author manu
 *
 */
public class TestPlayerClass
{
    private final static String P1_USER_NAME = "username";
    private final static String P2_USER_NAME = "username";
    private final static char P1_PIECE_COLOUR = 'w';
    private final static String P1_PIECE_COLOUR_STRING = "White";
    private final static char P2_PIECE_COLOUR = 'b';
    private final static String P2_PIECE_COLOUR_STRING = "Black";
    
    // if you have changed the place holders in player class you must make
    // those changes reflect here in the tests
    private final static String UNAME_PLACEHOLDER = "Username: ";
    private final static String WINCOUNT_PLACEHOLDER = "Wins: ";
    private final static String LOOSECOUNT_PLACEHOLDER = "Loses: ";
    private final static String DRAWCOUNT_PLACEHOLDER = "Draws: ";
    private final static String PIECECOLOUR_PLACEHOLDER = "Piece Colour: ";
    
    @Test
    public void test_player_constructor()
    {
	Player p1 = new Player(P1_USER_NAME, P1_PIECE_COLOUR);
	Player p2 = new Player(P2_USER_NAME, P2_PIECE_COLOUR);
	
	// p1
	assertTrue(p1.getUserName() == P1_USER_NAME);
	assertTrue(p1.getPieceColour() == P1_PIECE_COLOUR);
	
	// p2
	assertTrue(p2.getUserName() == P2_USER_NAME);
	assertTrue(p2.getPieceColour() == P2_PIECE_COLOUR);
    }
    
    @Test
    public void test_player_copyConstructor()
    {
	Player p1 = new Player(P1_USER_NAME, P1_PIECE_COLOUR);
	Player p2 = new Player(p1);
	
	// p1
	assertTrue(p1.getUserName() == p2.getUserName());
	assertTrue(p1.getPieceColour() == p2.getPieceColour());
	assertTrue(p1.getWinCount() == p2.getWinCount());
	assertTrue(p1.getLoseCount() == p2.getLoseCount());
	assertTrue(p1.getDrawCount() == p2.getDrawCount());
	assertFalse(p1 == p2);
    }
    
    /**
     * if this test fails test_getPlayerStats will fail too.
     */
    @Test
    public void test_pieceColourToString()
    {
	Player p1 = new Player(P1_USER_NAME, P1_PIECE_COLOUR);
	Player p2 = new Player(P2_USER_NAME, P2_PIECE_COLOUR);
	
	assertTrue(p1.pieceColourToString() == P1_PIECE_COLOUR_STRING);
	assertTrue(p2.pieceColourToString() == P2_PIECE_COLOUR_STRING);
	
    }
    
    @Test
    public void test_getPlayerStats()
    {
	Player p = new Player(P1_USER_NAME, P1_PIECE_COLOUR);
	
	int win = 1;
	int lose = 2;
	int draw = 0;
	
	p.setWinCount(win);
	p.setLoseCount(lose);
	p.setDrawCount(draw);
	
	ArrayList<String> stats = p.getPlayerStats();
	
	assertTrue(stats.contains(UNAME_PLACEHOLDER + P1_USER_NAME));
	assertTrue(stats.contains(WINCOUNT_PLACEHOLDER + win));
	assertTrue(stats.contains(LOOSECOUNT_PLACEHOLDER + lose));
	assertTrue(stats.contains(DRAWCOUNT_PLACEHOLDER + draw));
	assertTrue(stats.contains(PIECECOLOUR_PLACEHOLDER + P1_PIECE_COLOUR_STRING));
	
    }
}
