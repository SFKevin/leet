package test4;

import java.util.Scanner;

public class Main7 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = scanner.nextInt();
		}
		if (valid(array)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	public static boolean valid(int[] array) {
		int length = array.length;
		for (int i = 0; i < length; i++) {
			int size = getsize(array[i]);
			if (size == 0)
				continue;
			if (size == 1 || size + i > length || size > 4)
				return false;
			for (int j = 1; j < size; j++) {
				if ((array[i + j] & 0xC0) != 0x80)
					return false;
			}
			i += size - 1;
		}
		return true;
	}

	public static int getsize(int val) {
		int answer = 0;
		int number = 0x80;
		while ((number & val) != 0) {
			answer++;
			number = number >>> 1;
		}
		return answer;
	}
}
