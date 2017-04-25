import java.util.LinkedList;
import java.util.List;

public class TextJustification {

    public static List<Integer> justifyText(String[] w, int m) {
        int N = w.length;
        double[] DP = new double[N+1];
        DP[N] = 0;
        int[] breaks = new int[N];
        breaks[N-1] = N;

        for(int i=w.length-1; i>=0; i--){
            int end = i+1;
            double lowest = Double.POSITIVE_INFINITY;
            for(int j=i+1; j<=w.length; j++){
                double c = cost(w, i, j, m) + DP[j];
                if(c < lowest){
                    lowest = c;
                    end = j;
                }
            }
            DP[i] = lowest;
            breaks[i] = end;
        }

        int i = 0;
        List<Integer> b = new LinkedList<>();
        while (i < N) {
            b.add(i);
            i = breaks[i];
        }
        return b;
    }

    private static double cost(String[] w, int low, int high, int m){
        int length = high-low-1;
        for(int i=low; i<high; i++){
            length += w[i].length();
        }
        if(length > m){
            return Double.POSITIVE_INFINITY;
        }
        return Math.pow(m-length, 3);
    }
}