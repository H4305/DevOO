package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import model.data.Point;

import org.junit.Test;

public class TwoKeyMapTest {
	

	@Test
	public void testGet() {
		TwoKeyMap<String, String, String> map = new TwoKeyMap<String, String, String>();
		map.put("a", "b", "x");
		map.put("a", "e", "y");
		map.put("a", "d", "z");
		assertEquals("x", map.get("a", "b"));
		map.put("a", "b", "d");
		assertEquals("d", map.get("a", "b"));
	}


	@Test
	public void testRemove() {
		TwoKeyMap<String, String, String> map = new TwoKeyMap<String, String, String>();
		assertEquals(0, map.getSize());
		map.put("a", "b", "x");
		assertEquals(1, map.getSize());
		map.remove("a", "b");
		assertEquals(0, map.getSize());
		map.put("a", "b", "y");
		assertEquals(1, map.getSize());
		assertEquals("y", map.get("a", "b"));
		assertEquals(null, map.get("lll", "lll"));
	}

	@Test
	public void testGetSize() {
		TwoKeyMap<String, String, String> map = new TwoKeyMap<String, String, String>();
		assertEquals(0, map.getSize());
		map.put("a", "b", "x");
		assertEquals(1, map.getSize());
		map.put("a", "b", "y");
		assertEquals(1, map.getSize());
		map.put("a", "c", "y");
		assertEquals(2, map.getSize());
	}
	
	@Test
	public void testContainsKey() {
		Point p1 = new Point(1, 1, 1);
		Point p2 = new Point(1, 2, 1);
		Point p3 = new Point(1, 1, 1);
		Point p4 = new Point(1, 2, 1);
		TwoKeyMap<Point, Point, String> map = new TwoKeyMap<Point, Point, String>();
		map.put(p1, p2, "a");
		assertEquals("a", map.get(p1, p2));
		assertEquals("a", map.get(p3, p4));
		assertTrue(map.containsKeys(p3, p4));
	}

}
