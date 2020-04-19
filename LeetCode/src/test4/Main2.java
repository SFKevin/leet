package test4;

import java.util.Scanner;

public class Main2 {
	public static int number = 0;
	public static int count = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		if (string == null || string.length() < 4 || string.length() > 12) {
			System.out.println(0);
			return;
		}
		if (string.startsWith("0")) {
			System.out.println(0);
			return;
		}
		backtrack(string);
		System.out.println(number);
	}

	public static void backtrack(String ip) {
		if (ip == null || ip.length() == 0) {
			if (count == 4) {
				number++;
			}
			return;
		}
		for (int i = 0; i < 3 && i < ip.length(); i++) {
			String temp = ip.substring(0, i + 1);
			int n = Integer.parseInt(temp);
			if (0 <= n && n <= 255) {
				if (count >= 4)
					return;
				count++;
				backtrack(ip.substring(i + 1));
				count--;
			}
		}
	}
}
