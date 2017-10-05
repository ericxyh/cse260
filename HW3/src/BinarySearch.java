//Yunhan (Eric) Xu
//111118171
//CSE260
//HW-3
public class BinarySearch {

	public static<E extends Comparable<E>> int binarySearch(E[] list, E key) {
		int low = 0;
		int high = list.length - 1;
		int mid = high / 2;
		while (low <= high) {
			if (list[mid].compareTo(key) == 0) {
				return mid;
			}
			else if (list[mid].compareTo(key) > 0) {
				high = mid - 1;
			}
			else {
				low = mid + 1;
			}
			mid = (low + high) / 2;
		}
		if (list[mid].compareTo(key) == 0) {
			return mid;
		}
		return -1;
	}
	
}
