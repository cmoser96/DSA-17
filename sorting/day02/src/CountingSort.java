public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
     * Runtime: TODO
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int k = Integer.MIN_VALUE;
        for(int i=0; i<A.length; i++){
            if(A[i]>k){
                k=A[i];
            }
        }

        int[] counts = new int[k+1];
        for(int i=0; i<A.length; i++){
            counts[A[i]]++;
        }

        int i = 0;
        for(int j=0; j<=k;j++){
            while(counts[j]-->0){
                A[i++]=j;
            }
        }
    }
}
