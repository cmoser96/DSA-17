import javafx.scene.paint.Stop;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Index {

	// Index: map of words to URL and their counts
	private Map<String, Set<TermCounter>> index = new HashMap<String, Set<TermCounter>>();

	public void add(String term, TermCounter tc) {
		StopWords badWords = new StopWords();
		try {
			if (!badWords.getStopWords().contains(term))
				if (!index.containsKey(term)) {
					index.put(term, new HashSet<>());
				}
			// if we're seeing a term for the first time, make a new Set
			// otherwise we can add the term to an existing Set
			index.get(term).add(tc);
		} catch (IOException e){
			System.out.println(e.toString());
		}
	}

	public Set<TermCounter> get(String term) {
		return index.get(term);
	}

	public void indexPage(String url, Elements paragraphs) {
		// make a TermCounter and count the terms in the paragraphs
		TermCounter tc = new TermCounter(url);
		tc.processElements(paragraphs);

		// for each term in the TermCounter, add the TermCounter to the index
		Set<String> keys = tc.keySet();
		for(String key:keys){
			this.add(key, tc);
		}
	}

	public void printIndex() {
		// loop through the search terms
		for (String term: keySet()) {
			System.out.println(term);

			// for each term, print the pages where it appears
			Set<TermCounter> tcs = get(term);
			for (TermCounter tc: tcs) {
				Integer count = tc.get(term);
				System.out.println("    " + tc.getLabel() + " " + count);
			}
		}
	}

	public Set<String> keySet() {
		return index.keySet();
	}

	public static void main(String[] args) throws IOException {

		WikiFetcher wf = new WikiFetcher();
		Index indexer = new Index();

		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		Elements paragraphs = wf.fetchWikipedia(url);
		indexer.indexPage(url, paragraphs);

		url = "https://en.wikipedia.org/wiki/Programming_language";
		paragraphs = wf.fetchWikipedia(url);
		indexer.indexPage(url, paragraphs);

		indexer.printIndex();
	}
}
