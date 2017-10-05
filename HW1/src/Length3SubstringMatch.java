//Yunhan (Eric) Xu
//HW-1
//CSE260
public class Length3SubstringMatch {
	public static int stringMatch3(String a, String b) {
		int count = 0;
		String shorter = "";
		if (a.length() < b.length()){
			shorter = a;
		}
		else {
			shorter = b;
		}
		for (int i = 0; i < shorter.length() - 2; i++) {
			if (a.substring(i, i + 3).equals( b.substring(i, i + 3) )) {
				count += 1;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println("stringMatch3(\"sdgndsgyudsaiufb\",\"sadufygyudsafbnsaicgaf\")");
		System.out.println("Number of matches returned is: " + stringMatch3("sdgndsgyudsaiufb","sadufygyudsafbnsaicgaf"));
		System.out.println("stringMatch3(\"banana\",\"bananas\")");
		System.out.println("Number of matches returned is: " + stringMatch3("banana","bananas"));
	}
}
