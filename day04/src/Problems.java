import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Problems {

    public static Map<Integer, Integer> getCountMap(int[] arr) {
        MyLinearMap<Integer, Integer> map = new MyLinearMap();
        for(int i=0; i<arr.length; i++){
            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);
            }
            else{
                map.put(arr[i], 1);
            }
        }
        return map;
    }

    public static List<Integer> removeKDigits(int[] num, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        int removes = 0;
        for(int i=0; i<num.length; i++){
            while(k>removes && list.size()>0 && num[i]<list.getLast()){
                list.removeLast();
                removes++;
            }
            if(list.size()+k<num.length){
                list.addLast(num[i]);
            }
        }
        return list;
    }

    public static int sumLists(Node<Integer> h1, Node<Integer> h2) {
        return getVal(h1)+getVal(h2);
    }
    public static int getVal(Node<Integer> h){
        int val=0;
        while(h!=null){
            val*=10;
            val+=h.data;
            h = h.next;
        }
        return val;
    }
}
