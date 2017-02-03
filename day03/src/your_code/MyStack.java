package your_code;

import ADTs.StackADT;
import java.util.Stack;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private Node head;
    private int size = 0;
    private Stack<Integer> max = new Stack<Integer>();

    private class Node {
        Integer val;
        Node next;

        private Node(Integer e, Node next) {
            this.val = e;
            this.next = next;
        }
    }

    @Override
    public void push(Integer e) {
        if(head == null){
            head = new Node(e, null);
        }
        else{
            Node n = new Node(e, head);
            head = n;
        }
        if(max.isEmpty() || max.peek() <= e){
            max.push(e);
        }
        size++;
    }

    @Override
    public Integer pop() {
        Node n = head;
        head = n.next;
        if(n.val == max.peek()){
            max.pop();
        }
        size--;
        return n.val;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public Integer peek() {
        return head.val;
    }

    public Integer maxElement() {
        return max.pop();
    }
}
