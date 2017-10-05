//Yunhan (Eric) Xu
//111118171
//CSE260
//HW-3
import java.util.ArrayList;
public class ShuffleArrayList {

	public static <E> void shuffle(ArrayList<E> list) {
		for (int i = 0; i < list.size(); i++) {
			int rand = (int) (Math.random() * list.size());
			E temp = list.get(i);
			list.set(i, list.get(rand));
			list.set(rand, temp);
		}
	}
	
}
