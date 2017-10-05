//Yunhan (Eric) Xu
//111118171
//CSE260
//HW-3
public class GenericStackWithArray<E extends Comparable<E>> {
	private Object[] list = new Object[100];
	private int length = 0;
	
	public int getSize() {
		return length + 1;
	}
	
	public void checkLength() {
		if (list.length == length + 1) {
			Object[] temp = new Object[list.length * 2];
			for (int i = 0; i < list.length ; i++) {
				temp[i] = list[i];
			}
			list = temp;
		}
	}
	
	public E peek() {
		return (E)list[length];
	}
	
	public void push(E o) {
		list[length] = o;
		length++;
	}
	
	public E pop() {
		E o = (E)list[length];
		list[length] = null;
		length--;
		return o;
	}
	
	public boolean isEmpty() {
		return length == 0;
	}
	
	@Override // Java annotation: also used at compile time to
	public String toString() { // detect override errors
		String result = "stack:";
		for (int i = 0; i < length + 1 ; i ++){
			result += list[i].toString();
		}
		return result;
	}
}
