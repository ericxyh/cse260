//Yunhan (Eric) Xu
//111118171
//CSE260
//HW-4
import java.util.Arrays;
import java.util.TreeSet;
import java.io.*;
public class SyntaxHighlighting {
	
	private static final String[] key = new String[] {"abstract","continue", "for",
			"new",	"switch", "assert",	"default",	"goto",	"package",	"synchronized",
			"boolean",	"do",	"if",	"private",	"this",
			"break",	"double",	"implements",	"protected",	"throw",
			"byte",	"else",	"import",	"public",	"throws",
			"case",	"enum",	"instanceof",	"return",	"transient",
			"catch",	"extends",	"int", "short",	"try",
			"char",	"final",	"interface",	"static",	"void",
			"class",	"finally",	"long",	"strictfp", "volatile",
			"const",	"float",	"native",	"super",	"while"};
	private static final TreeSet<String> keyset = new TreeSet<>(Arrays.asList(key));
	
	
	public static File convert(File java) throws IOException {
		String name = java.getName();
		File css = new File(name.substring(0, name.indexOf(".")) + ".html");
		
		FileReader read = new FileReader(java);
		BufferedReader reader = new BufferedReader(read);
		FileWriter write = new FileWriter(css);
		BufferedWriter writer = new BufferedWriter(write);
		
		String line = reader.readLine();
		boolean longComment = line.contains("/*");
		writer.write("<!DOCTYPE html>" + "<html>" + "<body>");
		while (line != null) {
			if (longComment) {
				line = green(line);
				if (line.contains("*/"))
					longComment = false;
			}
			else if (line.contains("/*")) {
				longComment = true;
				line = green(line);
			}
			else if (line.contains("//")) {
				line = green(line);
			}
			else {
				line = blue(line);
				line = red(line);
			}
			writer.write(line + "<br />");
			line = reader.readLine();
		}
		
		writer.write("</body>" + "</html>");
		reader.close();
		writer.close();
		return css;
	}
	

	private static String red(String line) {
		if (line.contains(" ")) {
			String[] splitted = line.trim().split(" ");
			line = "";
			for (String string : splitted) {
				line = line + red(string) + " ";
			}
			return line;
		}
		else {
			if (keyset.contains(line))
				return "<span style='color:red'>" + line + "</span>";
			return line;
		}
	}


	private static String blue(String line) {
		line = blueString(line);
		line = blueOther(line);
		return line;
	}




	private static String blueOther(String line) {
		if (line.contains("<span style='color:blue'>")){
			int first = line.indexOf("<span style='color:blue'>");
			int second = first + line.substring(first).indexOf("</span>") + "</span>".length();
			return blueOther(line.substring(0, first)) + line.substring(first, second) + blueOther(line.substring(second));
		}
		else {
			line.replace("true", "<span style='color:blue'>true</span>");
			line.replace("false", "<span style='color:blue'>false</span>");
			line.replace("null", "<span style='color:blue'>null</span>");
			return line;
		}
	}


	private static String blueString(String line) {
		if (line.indexOf(34) != -1) {
			int first = line.indexOf((char)34);
			int second = first + 1 + line.substring(first + 1).indexOf(34);
			return line.substring(0, first) + "<span style='color:blue'>" + line.substring(first, second + 1) + "</span>" + blueString(line.substring(second + 1));
		}
		return line;
	}


	private static String green(String line) {
		return "<span style='color:green'>" + line + "</span>";
	}
	
	public static void main(String[] args) throws IOException {
		if (args.length != 0) {
			File in = new File(args[0]);
			convert(in);
		}
	}
	
}
