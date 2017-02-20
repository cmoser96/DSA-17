import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import redis.clients.jedis.Jedis;

public class WikiSearch {

    // map from URLs that contain the term(s) to relevance score
    private Map<String, Integer> map;

    public WikiSearch(Map<String, Integer> map) {
        this.map = map;
    }

    public Integer getRelevance(String url) {
        Integer relevance = map.get(url);
        return relevance==null ? 0: relevance;
    }

    // Prints the contents in order of term frequency.
    private  void print() {
        List<Entry<String, Integer>> entries = sort();
        for (Entry<String, Integer> entry: entries) {
            System.out.println(entry);
        }
    }

    // Computes the union of two search results.
    public WikiSearch or(WikiSearch that) {
        Set<String> keys = map.keySet();
        keys.addAll(that.map.keySet());
        return null;
    }

    // Computes the intersection of two search results.
    public WikiSearch and(WikiSearch that) {
        Set<String> keys = map.keySet();
        keys.retainAll(that.map.keySet());
        // TODO
        return null;
    }

    // Computes the intersection of two search results.
    public WikiSearch minus(WikiSearch that) {
        Set<String> keys = map.keySet();
        keys.removeAll(that.map.keySet());
        // TODO
        return null;
    }

    // Computes the relevance of a search with multiple terms.
    protected int totalRelevance(Integer rel1, Integer rel2) {
        // TODO
        return 0;
    }

    // Sort the results by relevance.
    public List<Entry<String, Integer>> sort() {
        List<Entry> entries = new ArrayList<>();
        for (String key: map.keySet()){
            getRelevance(key);
        }
        return null;
    }


    // Performs a search and make a WikiSearch object.
    public static WikiSearch search(String term, Index index) {
        // TODO: Use the index to get a map from URL to count

        // Fix this
        Map<String, Integer> map = index.getCounts(term);

        // Store the map locally in the WikiSearch
        return new WikiSearch(map);
    }

    // TODO: Choose an extension and add your methods here

    public static void main(String[] args) throws IOException {

        // make a Index
        Index index = new Index();
        index.connect();

        // search for the first term
        String term1 = "java";
        System.out.println("Query: " + term1);
        WikiSearch search1 = search(term1, index);
        search1.print();

        // search for the second term
        String term2 = "programming";
        System.out.println("Query: " + term2);
        WikiSearch search2 = search(term2, index);
        search2.print();

        // compute the intersection of the searches
        System.out.println("Query: " + term1 + " AND " + term2);
        WikiSearch intersection = search1.and(search2);
        intersection.print();
    }
}