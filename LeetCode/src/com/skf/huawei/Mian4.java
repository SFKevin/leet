package com.skf.huawei;

import java.util.Scanner;

public class Mian4 {
	// public static void main(String[] args) {
	// Scanner scanner = new Scanner(System.in);
	// String string = scanner.nextLine();
	// char[] arr = string.toCharArray();
	// int len = arr.length;
	// Set<Character> set = new HashSet<Character>();
	// for (int i = 0; i < len; i++) {
	// if (arr[i] >= 0 && arr[i] <= 127) {
	// set.add(arr[i]);
	// }
	// }
	// System.out.println(set.size());
	// }
	// public static void main(String[] args) {
	// Scanner scanner = new Scanner(System.in);
	// int num = scanner.nextInt();
	// Queue<Integer> stack = new LinkedList<Integer>();
	// boolean is_neg;
	// if (num < 0) {
	// is_neg = true;
	// num = 0 - num;
	// } else {
	// is_neg = false;
	// }
	// while (num != 0) {
	// int tmp = num % 10;
	// stack.add(tmp);
	// num /= 10;
	// }
	// StringBuffer sb = new StringBuffer();
	// while (!stack.isEmpty()) {
	// int tmp = stack.poll();
	// sb.append(tmp);
	// }
	// if (is_neg) {
	// sb.append("-");
	// }
	// System.out.println(sb.toString());
	// }
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		char[] arr = string.toCharArray();
		int len = arr.length;
		int start = 0;
		int end = len - 1;
		while (start < end) {
			char tmp = arr[start];
			arr[start] = arr[end];
			arr[end] = tmp;
			start++;
			end--;
		}
		System.out.println(new String(arr));
	}
}
