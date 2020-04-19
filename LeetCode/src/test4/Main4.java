package test4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		System.out.println(restoreIpAddresses(string).size());
	}

	public static List<String> restoreIpAddresses(String s) {
		List<String> answer = new ArrayList<String>();
		restoreIpAddresses(s, answer, 0, "", 0);
		return answer;
	}

	private static void restoreIpAddresses(String s, List<String> answer, int idx, String restored, int count) {
		if (count > 4)
			return;
		if (count == 4 && idx == s.length()) {
			answer.add(restored);
		}
		for (int i = 1; i < 4; i++) {
			if (idx + i > s.length())
				break;
			String ip = s.substring(idx, idx + i);
			if ((ip.length() != 1 && ip.startsWith("0")) || (ip.length() == 3 && Integer.parseInt(ip) >= 256)) {
				continue;
			}
			restoreIpAddresses(s, answer, idx + i, restored + ip + (count == 3 ? "" : "."), count + 1);
		}
	}
}