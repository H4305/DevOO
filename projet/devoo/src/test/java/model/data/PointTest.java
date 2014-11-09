package model.data;

import static org.junit.Assert.*;

import org.junit.Test;


public class PointTest {
	
	@Test
	public void testEqual() {
		Point p1 = new Point(1,1,1);
		Point p2 = new Point(1,2,1);
		Point p4 = new Point(1,1,1);
		assertEquals(p1, p1);
		assertTrue(p1.equals(p4));
		assertFalse(p1.equals(p2));
	}
	
	@Test
	public void testHashCode() {
		Point p1 = new Point(1,1,1);
		Point p2 = new Point(1,2,1);
		Point p4 = new Point(1,1,1);
		assertEquals(p1.hashCode(), p4.hashCode());
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}

}
