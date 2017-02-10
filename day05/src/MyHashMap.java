import java.util.*;

/**
 * Implementation of a HashMap using a collection of MyLinearMap and
 * resizing when there are too many or too few entries.
 *
 * @author downey
 * @param <K>
 * @param <V>
 *
 */
public class MyHashMap<K, V> implements Map<K, V> {

	// average number of entries per map before we grow the map
	private static final double ALPHA = 1.0;
	// average number of entries per map before we shrink the map
	private static final double BETA = .25;

	// resizing factor: (new size) = (old size) * (resize factor)
	private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;

	private static final int MIN_MAPS = 16;

	// list of maps
	protected List<MyLinearMap<K,V>> maps;
	private int size = 0;

	public MyHashMap() {
		makeMaps(MIN_MAPS);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Initialize maps
	 */
	protected void makeMaps(int size) {
		maps = new ArrayList<>();
		for(int i=0; i < Math.max(size, MIN_MAPS); i++){
			maps.add(new MyLinearMap<K, V>());
		}
	}

	protected MyLinearMap<K, V> chooseMap(Object key) {
		if(key==null){
			return maps.get(0);
		}
		return maps.get(key.hashCode()%maps.size());
	}

	@Override
	public boolean containsKey(Object key) {
		MyLinearMap<K, V> map = chooseMap(key);
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		for(MyLinearMap<K, V> map: maps){
			if(map.containsValue(value)){
				return true;
			}
		}
		return false;
	}

	protected void rehash(double growthFactor) {
		int map_size = (int)(maps.size()*growthFactor);
		if(growthFactor>1){
			while(maps.size()<map_size){
				maps.add(new MyLinearMap<K, V>());
			}
		}
		for(MyLinearMap<K, V> map: maps){
			Set<K> s = map.keySet();
			for (K key : s) {
				if(key !=null) {
					maps.get(key.hashCode() % maps.size()).put(key, map.remove(key));
				}
			}
		}
		if(growthFactor<1){
			while(maps.size()>map_size){
				maps.remove(maps.size()-1);
			}
		}
	}

	@Override
	public V get(Object key) {
		MyLinearMap<K,V> m = chooseMap(key);
		return m.get(key);
	}

	@Override
	public V put(K key, V value) {
		if(size>maps.size()*ALPHA){
			rehash(GROWTH_FACTOR);
		}
		MyLinearMap<K, V> map = chooseMap(key);
		size -= map.size();
		V val = map.put(key,value);
		size += map.size();
		return val;
	}

	@Override
	public V remove(Object key) {
		if(size<maps.size()*BETA && maps.size()*SHRINK_FACTOR>=MIN_MAPS){
			rehash(SHRINK_FACTOR);
		}
		MyLinearMap<K, V> map = chooseMap(key);
		size--;
		return map.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	public void clear() {
		for (int i=0; i<maps.size(); i++) {
			maps.get(i).clear();
		}
		size = 0;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<>();
		for (MyLinearMap<K,V> map : maps) {
			set.addAll(map.keySet());
		}
		return set;
	}

	@Override
	public Collection<V> values() {
		Collection<V> ll = new LinkedList<>();
		for (MyLinearMap<K,V> map : maps) {
			ll.addAll(map.values());
		}
		return ll;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K,V>> set = new HashSet<>();
		for (MyLinearMap<K,V> map : maps) {
			set.addAll(map.getEntries());
		}
		return set;
	}
}
