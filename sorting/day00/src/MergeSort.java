import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     *
     * Best-case runtime: O(nlogn)
     * Worst-case runtime: O(nlogn)
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(n)
     */
    @Override
    public int[] sort(int[] array) {
        if(array.length <= 1){
            return array;
        }
        else{
            int[] left = new int[array.length/2+array.length%2];
            int[] right = new int[array.length/2];
            System.arraycopy(array, 0, left, 0, left.length);
            System.arraycopy(array, left.length, right, 0, right.length);
            return merge(sort(left), sort(right));
        }
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     */
    public int[] merge(int[] a, int[] b) {
        int[] array = new int[a.length +b.length];
        if(array.length<= INSERTION_THRESHOLD){
            System.arraycopy(a, 0, array, 0, a.length);
            System.arraycopy(b, 0, array, a.length, b.length);
            InsertionSort x = new InsertionSort();
            return x.sort(array);
        }
        else{
            int inda = 0;
            int indb = 0;
            for(int i=0; i<array.length; i++){
                if(inda > a.length-1){
                    array[i] = b[indb];
                    indb++;
                }
                else if(indb > b.length-1){
                    array[i] = a[inda];
                    inda++;
                }
                else if(a[inda] >= b[indb]){
                    array[i] = b[indb];
                    indb++;
                }
                else{
                    array[i] = a[inda];
                    inda++;
                }
            }
        }
        return array;
    }

}
