package util;

import static org.junit.Assert.*;

import java.lang.annotation.Target;

import org.junit.Before;
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

}
