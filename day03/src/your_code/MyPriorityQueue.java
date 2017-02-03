package your_code;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

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

    public void enqueue(int item) {
        if(head == null){
            head = new Node(item, null, null);
            tail = head;
        }
        else{
            Node n = head;
            for(;n.val<item; n = n.next){
                if(n.next == null){
                    Node insert = new Node(item, n, null);
                    n.next = insert;
                    tail = insert;
                    size++;
                    return;
                }
            }
            Node insert = new Node(item, n, n.next);
            n.next.prev = insert;
            n.next = insert;
        }
        size++;
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        Node n = tail;
        tail = tail.prev;
        return n.val;
    }

}