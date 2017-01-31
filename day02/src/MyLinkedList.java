public class MyLinkedList<T> {

	private Node head;
	private Node tail;
	private int size;

	private class Node {
		T val;
		Node prev;
		Node next;

		private Node(T d, Node prev, Node next) {
			this.val = d;
			this.prev = prev;
			this.next = next;
		}
	}

	public MyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(T c) {
		addLast(c);
	}

	public T pop() {
		return removeLast();
	}

	public void addLast(T c) {
		if(head==null){
			head = new Node(c, null, null);
			tail = head;
		}
		else{
			tail.next = new Node(c, tail, null);
			tail = tail.next;
		}
		size++;
	}

	public void addFirst(T c) {
		if(head==null){
			head = new Node(c, null, null);
			tail = head;
		}
		else{
			Node node = new Node(c, null, head);
			head.prev = node;
			head = node;
		}
		size++;
	}

	public T get(int index) {
		if(index<0 || index>=size){
			throw new IndexOutOfBoundsException();
		}
		Node node = head;
		for(int i=0; i<index;i++) {
			node = node.next;
		}
		return node.val;
	}

	public T remove(int index) {
		if(index<0 || index>size){
			throw new IndexOutOfBoundsException();
		}
		if(index==0){
			return removeFirst();
		}
		T c = get(index);
		Node node = head;
		if(node.next.next==null){
			node.next=null;
			size--;
			return c;
		}
		for(int i=0; i<index;i++) {
			node.next = node.next.next;
			node.next.prev = node;
		}
		size--;
		return c;
	}

	public T removeFirst() {
		T c = get(0);
		if(size==0){
			throw new IndexOutOfBoundsException();
		}
		head = head.next;
		size--;
		return c;
	}

	public T removeLast() {
		T c = get(size-1);
		if(size==0){
			throw new IndexOutOfBoundsException();
		}
		tail = tail.prev;
		size--;
		return c;
	}
}