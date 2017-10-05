
//Yunhan (Eric) Xu
//HW-1
//CSE260
public class BaseToBase {
	private static final int CHAR_A = 65;
	public static String base2base(String n, int b1, int b2) {
		String heading = "Return: ";
		if (b1 == 10)
			return heading + fromTenBase(n, b2);
		else if (b2 == 10)
			return heading + toTenBase(n, b1);
		else
			return heading + fromTenBase(toTenBase(n, b1), b2);
	}
	
	public static String toTenBase(String n, int b) {
		int answer = 0;
		for (int i = 0; i < n.length(); i++) {
			int digit = (int) (n.charAt(i));
			if (digit >= CHAR_A)
				digit= 10 + (digit - CHAR_A);
			else
				digit = Integer.parseInt(n.substring(i,i+1));
			answer *= b;
			answer += digit;
		}
		return "" + answer;
	}
	
	public static String fromTenBase(String n, int b) {
		int intN = Integer.parseInt(n);
		String answer = "";
		while (intN > 0) {
			int digit = intN % b;
			intN /= b;
			if (digit >= 10)
				answer = "" + (char) (digit - 10 + CHAR_A) + answer;
			else
				answer = ""+ digit + answer;
		} 
		return answer;
	}
	
	public static void main(String[] args) {
		System.out.println("base2base(\"12345\", 6, 8)");
		System.out.println(base2base("12345", 6, 8));
		System.out.println("base2base(\"STONY\", 36, 19)");
		System.out.println(base2base("STONY", 36, 19));
		System.out.println("base2base(\"832K1\", 26, 15)");
		System.out.println(base2base("832K1", 26, 15));
	}
}
