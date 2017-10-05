//Yunhan (Eric) Xu
//HW-1
//CSE260
public class RunLengthEncoding {
	
	public static String encode(String message, char delimiter) {
		String answer = "";
		int duplicate = 1;
		char inTrack = message.charAt(0);
		for (int i = 1; i < message.length(); i++) {
			char current = message.charAt(i);
			if ((int) (current) == (int) (inTrack))
				duplicate++;
			else {
				if (duplicate > 3) {
					answer = answer + delimiter + inTrack + duplicate;
				}
				else {
					for (int j = 0; j < duplicate; j++) {
						answer += inTrack;
					}
				}
				inTrack = current;
				duplicate = 1;
			}
		}
		if (duplicate > 3) {
			answer = answer + delimiter + inTrack + duplicate;
		}
		else {
			for (int j = 0; j < duplicate; j++) {
				answer += inTrack;
			}
		}
		return "Result: " + answer;
	}
	
	public static void main(String[] args) {
		System.out.println("encode(\"KKKKKKKKKKKKKBCCDDDDDDDDDDDDDDDKKKKKMNUUUGGGGG\", \'$\')");
		System.out.println(encode("KKKKKKKKKKKKKBCCDDDDDDDDDDDDDDDKKKKKMNUUUGGGGG", '$'));
		System.out.println("encode(\"XYZAAAAAAGGTCCCCCCTTTAAAAAAAAAAAAAAKK\", \'*\')");
		System.out.println(encode("XYZAAAAAAGGTCCCCCCTTTAAAAAAAAAAAAAAKK", '*'));
	}
}
