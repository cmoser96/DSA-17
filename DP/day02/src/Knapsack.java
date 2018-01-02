public class Knapsack {

    public static int maxValue(int knapsackSize, int[] S, int[] V) {
        int[][] DP = new int[V.length+1][knapsackSize+1];

        for(int i=0; i<=V.length; i++){
            for(int s=0; s<=knapsackSize; s++){
                if(i==0 || s==0){
                    DP[i][s] = 0;
                }
                else if(S[i-1] > s){
                    DP[i][s] = DP[i-1][s];
                }
                else{
                    DP[i][s] = Math.max(V[i-1] + DP[i-1][s-S[i-1]], DP[i-1][s]);
                }
            }
        }
        return DP[V.length][knapsackSize];
    }
}
