public class Text {

	public static String getColor(int n) {
		String out = "\u001b[";

		if (n == 1) { out += "94"; }
		if (n == 2) { out += "92"; }
		if (n == 3) { out += "91"; }
		if (n == 4) { out += "34"; }
		if (n == 5) { out += "95"; }
		if (n == 6) { out += "96"; }
		if (n == 7) { out += "90"; }
		if (n == 8) { out += "1"; }

		return out + ";1m";
	}

}
