package your_code;

import ADTs.QueueADT;

/**
 * An implementation of the Queue interface.
 */
public class MyQueue implements QueueADT<Integer> {

    private Node head;
    private Node tail;
    private int size = 0;

    private class Node {
        Integer val;
        Node prev;
        Node next;

        private Node(Integer item, Node prev, Node next) {
            this.val = item;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public void enqueue(Integer item) {
        if (head == null){
            head = new Node(item, null, null);
            tail = head;
        }
        else{
            Node n = new Node(item, null, head);
            head.prev = n;
            head = n;
        }
        size++;
    }

    @Override
    public Integer dequeue() {
        Node n = tail;
        tail = tail.prev;
        size--;
        return n.val;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public Integer front() {
        return tail.val;
    }
}