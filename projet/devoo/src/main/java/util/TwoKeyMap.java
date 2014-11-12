package util;

import java.util.HashMap;
import java.util.Iterator;

/**
 * This is a HashMap where values are indexed by two keys.
 * 
 * @author Vadim Caen
 *
 * @param <K1> The type of the first key.
 * @param <K2> The type of the second key.
 * @param <V> The type of the value.
 */
public class TwoKeyMap<K1, K2, V> {
	
	private HashMap<K1, HashMap<K2, V>> map1 = new HashMap<K1, HashMap<K2,V>>();
	private int size = 0;
	

	/**
	 * Get the object indexed by the two keys.
	 * 
	 * @param key1 The first key.
	 * @param key2 The seconde key.
	 * @return The object indexed by key1 and key2 or null if there is no object.
	 */
	public V get(K1 key1, K2 key2) {
		if(map1.containsKey(key1)) {
			HashMap<K2, V> map2 = map1.get(key1);
			if(map2.containsKey(key2)) {
				return map2.get(key2);
			}
		}
		return null;
	}
	
	/**
	 * Add (or replace if the keys exist) the object value to the map.
	 * @param key1 The first key.
	 * @param key2 The second key.
	 * @param value The value to add.
	 */
	public void put(K1 key1, K2 key2, V value) {
		HashMap<K2, V> map2;
		if(map1.containsKey(key1)) {
			map2 = map1.get(key1);
			if(!map2.containsKey(key2)) {
				size++;
			}
		} else {
			map2 = new HashMap<K2, V>();
			map1.put(key1, map2);
			size++;
		}
		map2.put(key2, value);
	}
	
	/**
	 * Remove the value a
	 * @param key1
	 * @param key2
	 */
	public void remove(K1 key1, K2 key2) {
		HashMap<K2, V> map2;
		if(map1.containsKey(key1)) {
			map2 = map1.get(key1);
			if(map2.containsKey(key2)) {
				map2.remove(key2);
				if(map2.isEmpty()) {
					map1.remove(key1);
				}
				size--;
			}
		}
	}
	
	public boolean containsKeys(K1 key1, K2 key2) {
		if(!map1.containsKey(key1)) return false;
		HashMap<K2, V> map2 = map1.get(key1);
		return map2.containsKey(key2);
	}
	
	public int getSize() { return size; }

//	@Override
//	public Iterator<V> iterator() {}

//	private class TwoKeyMapIterator implements Iterator<V> {
//		
//		T
//
//		@Override
//		public boolean hasNext() {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public V next() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public void remove() {
//			// TODO Auto-generated method stub
//			
//		}
//		
//	}

	

}
