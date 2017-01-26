public class MyArrayList {
    private Cow[] elems;
	private int size;

	public MyArrayList() {
		elems = new Cow[10];
		size = 0;
	}

	public MyArrayList(int capacity) {
		elems = new Cow[capacity];
		size = 0;
	}

	public void add(Cow c) {
		if(size >= elems.length){
			Cow[] larger = new Cow[elems.length*2];
			System.arraycopy(elems, 0, larger, 0, elems.length);
			elems = larger;
		}
		elems[size] = c;
		size++;
	}

	public int size() {
		return size;
	}

	public Cow get(int index) {
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		return elems[index];
	}

	public Cow remove(int index) {
		Cow c = get(index);
		for(int i = index; i < size-1; i++){
			elems[i] = elems[i+1];
		}
		if(size < elems.length/4 && elems.length > 5) {
			Cow[] larger = new Cow[elems.length/2];
			System.arraycopy(elems, 0, larger, 0, elems.length/2);
			elems = larger;
		}
		size--;
		return c;
	}

	public void add(int index, Cow c) {
		if(index < 0 || index > elems.length) {
			throw new IndexOutOfBoundsException();
		}
		if(size >= elems.length){
			Cow[] larger = new Cow[elems.length*2];
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
