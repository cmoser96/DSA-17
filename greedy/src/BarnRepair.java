import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class BarnRepair {
    public static int solve(int M, int S, int C, int[] occupied) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        Arrays.sort(occupied);
        int len = occupied[C-1]-occupied[0]+1;
        for(int i=1; i<C; i++){
            int diff = occupied[i] - occupied[i-1] - 1;
            if(diff!=0){
                q.add(diff);
            }
        }
        for(int i=0; i<M-1; i++){
            if(q.isEmpty()){
                return len;
            }
            len -= q.poll();
        }
        System.out.println(len);
        return len;
    }
}
