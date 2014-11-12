package model.data;

import static org.junit.Assert.*;

import org.junit.Test;


public class PointTest {
	
	@Test
	public void testEqual() {
		Noeud p1 = new Noeud(1,1,1);
		Noeud p2 = new Noeud(1,2,1);
		Noeud p4 = new Noeud(1,1,1);
		assertEquals(p1, p1);
		assertTrue(p1.equals(p4));
		assertFalse(p1.equals(p2));
	}
	
	@Test
	public void testHashCode() {
		Noeud p1 = new Noeud(1,1,1);
		Noeud p2 = new Noeud(1,2,1);
		Noeud p4 = new Noeud(1,1,1);
		assertEquals(p1.hashCode(), p4.hashCode());
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}

}
