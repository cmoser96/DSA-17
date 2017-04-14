public class MixingMilk {
    public static int solve(int M, int N, int[] units, int[] price) {
        int total = 0;
        while(M>0) {
            int min = 0;
            for (int i = 1; i < N; i++) {
                if(price[i]<price[min]){
                    min = i;
                }

                int purch;

                if(units[min]>M){
                    purch = M;
                }
                else{
                    purch = units[min];
                }

                total += purch * price[min];
                M -= purch;
                price[min] = Integer.MAX_VALUE;
            }
        }
        return total;
    }
}
