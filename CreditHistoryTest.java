import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;



/**
 * @author GROUP 22
 *
 *	This class will test the methods presented in the Credit History class.
 */

public class CreditHistoryTest {

	/**
	 * This test will test the method of addRating, ensures that the ratings are correctly added
	 * to the ArrayList.
	 */
	@Test
	public void testAddRating() {
		CreditHistory c = new CreditHistory();
		c.addRating(10);
		assertFalse(c.getRatings().contains(10));
		c.addRating(-10);
		assertFalse(c.getRatings().contains(-10));
		c.addRating(-5);
		assertTrue(c.getRatings().contains(-5));
		c.addRating(5);
		assertTrue(c.getRatings().contains(5));
	}
	
	/**
	 * This test will test getRatings. Will then verify that the list is in the 
	 * same order as it was inputtted.
	 */
	
	@Test
	public void test_getRatings() {
		CreditHistory c = new CreditHistory();
		ArrayList<Integer> l = new ArrayList<Integer>();
		c.addRating(1);
		c.addRating(2);
		c.addRating(-4);
		c.addRating(-1);
		l.add(1);
		l.add(2);
		l.add(-4);
		l.add(-1);
		assertTrue(l.equals(c.getRatings()));
	}

	/**
	 * This test will test trimRatings, ensures the list will have a max of 10 
	 * ratings inside the ArrayList.
	 */
	@Test
	public void test_trimRatings() {
		CreditHistory c = new CreditHistory();
		Integer[] res = {0,0,0,0,0,0,0,0,0,0};
		ArrayList<Integer> l = new ArrayList<Integer>();
		c.addRating(2);
		c.addRating(2);
		c.addRating(2);
		for (Integer r:res) {
			c.addRating(r);
			l.add(r);
		}
		c.trimRatings();
		int result = c.getRatings().size();
		assertEquals(10, result);
		assertTrue(l.equals(c.getRatings()));
	}
	
	/*
	 * This test will test numOfRatings, specificaly testing how many ratings are in the ArrayList.
	 */
	@Test
	public void test_numOfRatings() {
		CreditHistory c = new CreditHistory();
		Integer[] res = {0,0,0,0,0,0,0,0,0,0};
		ArrayList<Integer> l = new ArrayList<Integer>();
		c.addRating(2);
		c.addRating(2);
		c.addRating(2);
		for (Integer r:res) {
			c.addRating(r);
			l.add(r);
		}
		int result = c.numOfRatings();
		assertEquals(13, result);
		c.trimRatings();
		int r = c.numOfRatings();
		assertEquals(10, r);
	}
	
	/*
	 * The following tests will test the getCreditRating method with various ArrayList length. It will verify that 
	 * the expected credit rating matches the actual result. 
	 */
	
	//This will test for the credit rating with a list of 9 numbers.
	
	@Test
	public void test_getCreditRating_9() {
		CreditHistory c = new CreditHistory();
		c.addRating(4);
		c.addRating(3);
		c.addRating(0);
		c.addRating(-1);
		c.addRating(-4);
		c.addRating(5);
		c.addRating(5);
		c.addRating(3);
		c.addRating(3);
		double result = c.getCreditRating();
		assertEquals(2.267, result,0.001 );
	}
	
	//This will test for the credit rating with a list of 3 numbers
	@Test
	public void test_getCreditRating_3() {
		CreditHistory c = new CreditHistory();
		c.addRating(4);
		c.addRating(-1);
		c.addRating(3);
		double result = c.getCreditRating();
		assertEquals(1.833, result, 0.001);
	}
	
	//This will test for the credit rating with a list of 4 numbers
	@Test 
	public void test_getCreditRating_4() {
		CreditHistory c = new CreditHistory();
		c.addRating(5);
		c.addRating(3);
		c.addRating(2);
		c.addRating(1);
		double result = c.getCreditRating();
		assertEquals(2.625, result, 0.001);
	}
	
	//This will test for the credit rating with a list of 1 number
	@Test
	public void test_getCreditRating_1() {
		CreditHistory c = new CreditHistory();
		c.addRating(1);
		double result = c.getCreditRating();
		assertEquals(1.000, result, 0.001);
	}

	//This will test for the credit rating with a list of 10 numbers
	@Test
	public void test_getCreditRating_10() {
		CreditHistory c = new CreditHistory();
		c.addRating(4);
		c.addRating(3);
		c.addRating(0);
		c.addRating(-1);
		c.addRating(-4);
		c.addRating(5);
		c.addRating(5);
		c.addRating(3);
		c.addRating(3);
		c.addRating(2);
		double result = c.getCreditRating();
		assertEquals(2.440, result, 0.001);
		
	}
}
