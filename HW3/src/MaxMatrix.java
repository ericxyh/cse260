//Yunhan (Eric) Xu
//111118171
//CSE260
//HW-3
public class MaxMatrix {
	
	public static <E extends Comparable<E>> E max(E[][] list) {
		E max = list[0][0];
		for (E[] row : list) {
			for (E es : row) {
				if (es.compareTo(max) > 0) {
					max = es;
				}
			}
			
		}
		return max;
	}
	
}
