//Yunhan (Eric) Xu
//111118171
//CSE260
//HW-3
import java.util.ArrayList;
public class RemoveDuplicates {
	
	public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
		ArrayList<E> noDupe = new ArrayList<E>();
		for (E e : list) {
			if (! noDupe.contains(e)) {
				noDupe.add(e);
			}
		}
		return noDupe;
	}
	
}
