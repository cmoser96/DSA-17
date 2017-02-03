package your_code;

import ADTs.StackADT;
import java.util.Stack;

public class PsetProblems {

    public static int longestValidSubstring(String s) {
        int maxlen = 0;

        for(int pos = 0; pos<s.length(); pos++) {
            int x = substringlen(s.substring(pos));
            if(x > maxlen){
                maxlen = x;
            }
        }
        return maxlen;
    }

    public static int substringlen(String s){
        int currlen = 0;
        Stack<Integer> st = new Stack<Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(0);
                currlen++;
            } else {
                try {
                    st.pop();
                    currlen++;
                } catch (Exception e) {
                    break;
                }
            }
        }
        if (st.isEmpty()) {
            return currlen;
        }
        else{
            return substringlen(s.substring(0,s.length()-1));
        }
    }

    public static StackADT<Integer> sortStackLimitedMemory(StackADT<Integer> s) {
        MyStack temp = new MyStack();
        int t;
        temp.push(s.pop());
        while(!s.isEmpty()){
            t = s.pop();
            if(temp.peek()>t){
                temp.push(t);
            }
            else{
                while(!temp.isEmpty() && temp.peek()<t){
                    s.push(temp.pop());
                }
                temp.push(t);
            }
        }
        return temp;
    }

}
