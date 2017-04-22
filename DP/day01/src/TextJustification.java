import java.util.ArrayList;
import java.util.List;
public class TextJustification {

    public static List<Integer> justifyText(String[] w, int m) {
        int N = w.length;
        int[] DP = new int[N+1];
        DP[N] = 0;
        int[] breaks = new int[N];
        breaks[N-1] = N;

        for(int i=N-1; i>=0; i--){
            int end = i+1;
            int lowest = Integer.MAX_VALUE;
            for(int j=0; j<=N; j++){
                int c = cost(w, i, j, m) + DP[j];
                if(c < lowest){
                    lowest = c;
                    end = j;
                }
            }
            DP[i] = lowest;
            breaks[i] = end;
        }

        int i = 0;
        ArrayList<Integer> b = new ArrayList<>();
        while (i < w.length) {
            b.add(i);
            i = breaks[i];
        }
        return b;
    }

    private static int cost(String[] w, int low, int high, int m){
        int length = high-low-1;
        for(int i=0; i<high; i++){
            length += w[i].length();
        }
        return length>m ? Integer.MAX_VALUE : (int)Math.pow(m-length, 3);
    }
}