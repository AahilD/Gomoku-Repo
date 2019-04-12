package jUnitTests;

import static org.junit.Assert.assertTrue;


import org.junit.jupiter.api.Test;


import broker.Square;

class TestSquareClass {

	//testing constructor
	@Test
	void test_Square() {
		Square s = new Square(20,20);
		assertTrue(20==s.getX());
		assertTrue(20==s.getY());
	}
	
	//testing copy constructor
	@Test
	void test_square() {
		Square s = new Square (10,10);
		Square ss = new Square(s); 
		assertTrue(s.getX()==ss.getX());
		assertTrue(s.getY()==ss.getY());
	}
	
	

}


