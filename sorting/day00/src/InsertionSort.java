
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * Best-case runtime: O(n^2)
     * Worst-case runtime: O(1)
     * Average-case runtime: O(n^2)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        for(int i=0; i<array.length; i++){
            int index = i;
            while(index>0 && array[index-1]>array[index]){
                int temp = array[index];
                array[index] = array[index-1];
                array[index-1] = temp;
                index -=1;
            }
        }
        return array;
    }
}
