//Yunhan (Eric) Xu
//111118171
//CSE260
//HW-3
public class Max {

	public static <E extends Comparable<E>> E max(E[] list) {
		E max = list[0];
		for (E e : list) {
			if (e.compareTo(max) > 0) {
				max = e;
			}
		}
		return max;
	}
	
}
