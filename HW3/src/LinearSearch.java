//Yunhan (Eric) Xu
//111118171
//CSE260
//HW-3
public class LinearSearch {
	
	public static<E extends Comparable<E>> int linearSearch(E[] list, E key) {
		for (int i = 0; i < list.length; i++) {
			if (list[i].equals(key)) {
				return i;
			}
			
		}
		return -1;
	}
	
}