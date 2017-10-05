//Yunhan (Eric) Xu
//111118171
//CSE260
//HW-3
public class GenericStackExtendsArrayList<E> extends java.util.ArrayList<E>{
	
	public E peek() {
		return get(size() - 1);
	}
	
	public void push(E o) {
		add(o);
	}
	
	public E pop() {
		return remove(size() - 1);
	}
	
}
