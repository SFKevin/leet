package com.skf.huawei;

import java.util.Scanner;
import java.util.Stack;

import org.junit.Test;

public class Main {
	// public static void mian(String[] args) {
	// Scanner scanner = new Scanner(System.in);
	// String line = scanner.nextLine();
	// String[] arr = line.split(" ");
	// System.out.println(arr[arr.length - 1].length());
	// }
	// public static void main(String[] args) {
	// Scanner scanner = new Scanner(System.in);
	// String line = scanner.nextLine();
	// String ch = scanner.nextLine();
	// line = line.toLowerCase();
	// ch = ch.toLowerCase();
	// int count = 0;
	// for (int i = 0; i < line.length(); i++) {
	// if (ch.equals(String.valueOf(line.charAt(i)))) {
	// count++;
	// }
	// }
	// System.out.println(count);
	// }
	// public static void main(String[] args) {
	// Scanner scanner = new Scanner(System.in);
	// while (scanner.hasNext()) {
	// int n = scanner.nextInt();
	// Set<Integer> set = new HashSet<Integer>();
	// for (int i = 0; i < n; i++) {
	// set.add(scanner.nextInt());
	// }
	// int len = set.size();
	// int[] arr = new int[len];
	// int i = 0;
	// for (Integer num : set) {
	// arr[i] = num;
	// i++;
	// }
	// quickSort(arr, 0, arr.length - 1);
	// for (int k = 0; k < arr.length; k++) {
	// System.out.println(arr[k]);
	// }
	// }
	// }
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			int len = str.length();
			if (len == 0) {
				continue;
			} else {
				int n = len / 8;
				int res = len % 8;
				for (int i = 0; i < n; i++) {
					System.out.println(str.substring(i * 8, (i + 1) * 8));
				}
				if (res > 0) {
					StringBuffer sb = new StringBuffer();
					sb.append(sb.append(str.substring(n * 8)));
					for (int j = sb.length(); j < 8; j++) {
						sb.append("0");
					}
					System.out.println(sb.toString());
				}
			}
		}

	}

	@Test
	public void test() {
		String str = "abc";
		int len = str.length();
		if (len == 0) {
			return;
		} else {
			int n = len / 8;
			int res = len % 8;
			for (int i = 0; i < n; i++) {
				System.out.println(str.substring(i * 8, (i + 1) * 8));
			}
			if (res > 0) {
				StringBuffer sb = new StringBuffer();
				sb.append(sb.append(str.substring(n * 8)));
				for (int j = sb.length(); j < 8; j++) {
					sb.append("0");
				}
				System.out.println(sb.toString());
			}
		}
	}

	public static void quickSort(int[] a, int low, int high) {
		if (low >= high) {
			return;
		}
		int i = low;
		int j = high;
		int index = a[i];
		while (i < j) {
			while (i < j && index < a[j]) {
				j--;
			}
			if (i < j) {
				a[i++] = a[j];
			}
			while (i < j && index > a[i]) {
				i++;
			}
			if (i < j) {
				a[j--] = a[i];
			}
		}
		a[i] = index;
		quickSort(a, low, i - 1);
		quickSort(a, i + 1, high);
	}

	public static void getLCS(String x, String y) {
		char[] s1 = x.toCharArray();
		char[] s2 = y.toCharArray();
		int len1 = x.length();
		int len2 = y.length();
		int[][] dp = new int[len1 + 1][len2 + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 0;
		}
		for (int j = 0; j < dp[0].length; j++) {
			dp[0][j] = 0;
		}
		for (int m = 1; m < dp.length; m++) {
			for (int n = 1; n < dp[m].length; n++) {
				if (s1[m - 1] == s2[n - 1]) {
					dp[m][n] = dp[m - 1][n - 1] + 1;
				} else {
					dp[m][n] = Math.max(dp[m - 1][n], dp[m][n - 1]);
				}
			}
		}
		Stack<Character> stack = new Stack<Character>();
		int i = s1.length - 1;
		int j = s2.length - 1;
		while (i >= 0 && j >= 0) {
			if (s1[i] == s2[j]) {
				stack.push(s1[i]);
				i--;
				j--;
			} else {
				if (dp[i + 1][j] > dp[i][j + 1]) {
					j--;
				} else {
					i--;
				}
			}
		}
	}

}
