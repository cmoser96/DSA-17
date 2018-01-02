import java.util.Arrays;

public class Trampolines {
    public static int trampoline(int[] nums) {
        int pos = 0;
        int jumps = 0;
        while(pos<nums.length){
            pos+=nums[max(Arrays.copyOfRange(nums, pos, pos+nums[pos]))];
            jumps++;
        }
        return jumps;
    }
    public static int max(int[] sub){
        int pos=0;
        if(sub.length == 1){
            return pos;
        }
        int max = sub[0];
        for(int i=1; i<sub.length; i++){
            if(sub[i]>=max){
                max = sub[i];
                pos = i;
            }
        }
        return pos;
    }
}