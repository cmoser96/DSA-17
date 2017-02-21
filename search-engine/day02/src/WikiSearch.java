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
        Map<String, Integer> orMap = new HashMap();
        Set<String> keys = new HashSet(this.map.keySet());
        keys.addAll(that.map.keySet());
        for (String key: keys){
            int value = totalRelevance(this.getRelevance(key), that.getRelevance(key));
            orMap.put(key, value);
        }
        return new WikiSearch(orMap);
    }

    // Computes the intersection of two search results.
    public WikiSearch and(WikiSearch that) {
        Map<String, Integer> andMap = new HashMap();
        Set<String> keys = this.map.keySet();
        keys.retainAll(that.map.keySet());
        for (String key: keys){
            int value = totalRelevance(this.getRelevance(key),that.getRelevance(key));
            andMap.put(key, value);
        }
        return new WikiSearch(andMap);
    }

    // Computes the intersection of two search results.
    public WikiSearch minus(WikiSearch that) {
        Set<String> keys = that.map.keySet();
        for (String key: keys){
            if (this.map.containsKey(key)){
                this.map.remove(key);
            }
        }
        return new WikiSearch(this.map);
    }

    // Computes the relevance of a search with multiple terms.
    protected int totalRelevance(Integer rel1, Integer rel2) {
        if (rel1 == null){
            return rel2;
        }
        else if (rel2 == null){
            return rel1;
        } else{
            return rel1 + rel2;
        }
    }

    Comparator<Entry<String, Integer>> comparator = new Comparator<Entry<String, Integer>>() {
        @Override
        public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
            if(o1.getValue() < o2.getValue()){
                return -1;
            }
            else if (o1.getValue() > o2.getValue()){
                return 1;
            }
            return 0;
        }
    };

    // Sort the results by relevance.
    public List<Entry<String, Integer>> sort() {
        List<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>();
        for(Entry<String, Integer> entry: this.map.entrySet()){
            entries.add(entry);
        }
        Collections.sort(entries, comparator);
        return entries;
    }


    // Performs a search and make a WikiSearch object.
    public static WikiSearch search(String term, Index index) {
        Map<String, Integer> map = index.getCounts(term);

        // Store the map locally in the WikiSearch
        return new WikiSearch(map);
    }

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