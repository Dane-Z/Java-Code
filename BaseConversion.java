package proj_0_base_conversion;

public class BaseConversion {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Needs two command line arguments");
		} else {
			String arg1 = args[0];
			String arg2 = args[1];
			if (arg1.equals("to2")) { // sort of like arg1 == "to2"
				int n = Integer.valueOf(arg2);
				System.out.println(toBase2(n));
			} else if (arg1.equals(("to10"))) {
				System.out.println(toBase10(arg2));
			}
		}
	}

	public static String toBase2(int n) {
		String s = Integer.toString(n, 2);
		return s;
	}

	public static int toBase10(String s) {
		int n = Integer.parseInt(s, 2);
		return n;
	}
}
