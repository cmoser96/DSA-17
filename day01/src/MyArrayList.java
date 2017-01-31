public class MyArrayList<T> {
    private T[] elems;
	private int size;

	public MyArrayList() {
		elems = (T[])new Object[10];
		size = 0;
	}

	public MyArrayList(int capacity) {
		elems = (T[])new Object[capacity];
		size = 0;
	}

	public void add(T c) {
		if(size >= elems.length){
			T[] larger = (T[])new Object[elems.length*2];
			System.arraycopy(elems, 0, larger, 0, elems.length);
			elems = larger;
		}
		elems[size] = c;
		size++;
	}

	public int size() {
		return size;
	}

	public T get(int index) {
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		return elems[index];
	}

	public T remove(int index) {
		T c = get(index);
		for(int i = index; i < size-1; i++){
			elems[i] = elems[i+1];
		}
		if(size < elems.length/4 && elems.length > 5) {
			T[] larger = (T[])new Object[elems.length/2];
			System.arraycopy(elems, 0, larger, 0, elems.length/2);
			elems = larger;
		}
		size--;
		return c;
	}

	public void add(int index, T c) {
		if(index < 0 || index > elems.length) {
			throw new IndexOutOfBoundsException();
		}
		if(size >= elems.length){
			T[] larger = (T[])new Object[elems.length*2];
			System.arraycopy(elems, 0, larger, 0, elems.length);
			elems = larger;
		}
		for(int i = size; i >= index; i--){
			elems[i+1] = elems[i];
		}
		elems[index] = c;
		size++;
	}
}
