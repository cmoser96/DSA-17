import java.util.*;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        int dist;
        int total = 0;
        for(int i=0; i<points.length; i++){
            for(int b=0; b<points.length; b++){
                dist = getSquaredDistance(points[i],points[b]);
                if(!map.containsKey(dist)){
                    map.put(dist, 0);
                }
                map.put(dist, map.get(dist)+1);
            }
            Set<Integer> k= map.keySet();
            for(Integer key: k) {
                total += map.get(key) * (map.get(key)-1);
            }
            map.clear();
        }
        return total;
    }

    private static int getSquaredDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];

        return dx*dx + dy*dy;
    }
}
