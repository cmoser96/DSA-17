import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UndirectedGraph implements Graph {

    private LinkedList<Integer> verts;
    private HashMap<Integer, LinkedList<Integer>> edges;
    private int numEdges;

    public UndirectedGraph(int n) {
        verts = new LinkedList<>();
        edges = new HashMap<>();
        numEdges = 0;

        for(int i=0; i<n; i++){
            verts.add(i);
            edges.put(i, new LinkedList<>());
        }
    }

    @Override
    public void addEdge(int v, int w) {
        edges.get(v).add(w);
        edges.get(w).add(v);
        numEdges++;
    }

    @Override
    public List<Integer> vertices() {
        return verts;
    }

    @Override
    public int numVertices() {
        return verts.size();
    }

    @Override
    public int numEdges() {
        return numEdges;
    }

    @Override
    public Iterable<Integer> getNeighbors(int v) {
        return edges.get(v);
    }

    @Override
    public boolean hasEdgeBetween(int v, int w) {
        return edges.get(v).contains(w);
    }

}
