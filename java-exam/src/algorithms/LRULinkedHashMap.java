package algorithms;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU算法
 * Created by MelloChan on 2018/3/4.
 */
public class LRULinkedHashMap extends LinkedHashMap {
    private static final long serialVersionUID = 7912177866579640928L;

    private int capacity;

    LRULinkedHashMap(int capacity){
        super(16,0.75f,true);

        this.capacity=capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size()>capacity;
    }

    public static void main(String[] args) {
        Map<Integer,Integer>map=new LRULinkedHashMap(4);
        map.put(9,3);
        map.put(7,4);
        map.put(5,9);
        map.put(3,4);
        map.put(6,6);
        map.put(7,4);
        map.put(9,3);
        for (Integer i:
             map.keySet()) {
            System.out.println(i);
        }
    }
}
