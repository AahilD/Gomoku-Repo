import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;



//GROUP 22

public class CreditHistoryTest {

	
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
	
	
	
	@Test
	public void testGetRatings() {
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

	@Test
	public void testTrimRatings() {
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
	
	@Test
	public void testNumOfRatings() {
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
	
	@Test
	public void testGetCreditRating_9() {
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
	
	@Test
	public void testGetCreditRating_3() {
		CreditHistory c = new CreditHistory();
		c.addRating(4);
		c.addRating(-1);
		c.addRating(3);
		double result = c.getCreditRating();
		assertEquals(1.833, result, 0.001);
	}
	
	@Test 
	public void testGetCreditRating_4() {
		CreditHistory c = new CreditHistory();
		c.addRating(5);
		c.addRating(3);
		c.addRating(2);
		c.addRating(1);
		double result = c.getCreditRating();
		assertEquals(2.625, result, 0.001);
	}
	
	@Test
	public void testGetCreditRating_1() {
		CreditHistory c = new CreditHistory();
		c.addRating(1);
		double result = c.getCreditRating();
		assertEquals(1.000, result, 0.001);
	}

	@Test
	public void testGetCreditRating_10() {
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
