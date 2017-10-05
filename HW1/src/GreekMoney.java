//Yunhan (Eric) Xu
//HW-1
//CSE260
public class GreekMoney {

	public static int howManyOboloi(int talents, int minae, int drachmae,int oboloi) {
		int answer = talents;
		answer *= 60;
		answer += minae;
		answer *= 70;
		answer += drachmae;
		answer *= 6;
		answer += oboloi;
		return answer;
	}
	
	public static void main(String[] args) {
		System.out.println("howManyOboloi(2, 32, 59, 3)");
		System.out.println(howManyOboloi(2, 32, 59, 3));
	}
	
}
