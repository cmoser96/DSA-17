import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        for(int i=0; i<A.length; i++){
            A[i] += 100;
        }
        CountingSort.countingSort(A);
        for(int i=0; i<A.length; i++){
            A[i] -= 100;
        }
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        LinkedList<String>[] L = new LinkedList[26];
        for (int i = 0; i < 26; i++)
            L[i] = new LinkedList<>();
        for (String s : A) {
            int d = getNthCharacter(s, n);
            L[d].add(s);
        }
        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) {
            for(String i : list){
                A[j] = i;
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        for(int i=0; i<stringLength; i++){
            countingSortByCharacter(S, i);
        }
    }

}
