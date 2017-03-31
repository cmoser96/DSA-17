import java.util.*;

public class ShortestPath {

    public static List<Integer> shortestPath(Graph g, int v, int w) {
        LinkedList<Integer> path = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        while(!q.isEmpty()){
            int current = q.remove();
            for(Integer i : g.getNeighbors(current)){
                if(!map.containsKey(i)){
                    q.add(i);
                    map.put(i, current);
                }
                if(i == w){
                    path.add(w);
                    while(path.peekFirst()!=v){
                        path.addFirst(map.get(path.peekFirst()));
                    }
                    return path;
                }
            }
        }

        return null;
    }

    public static int distanceBetween(Graph g, int v, int w) {
        List<Integer> shortest = shortestPath(g, v, w);
        return shortest == null ? -1 : shortest.size()-1;
    }

}