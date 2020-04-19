package com.skf.huawei;

import java.util.Scanner;
import java.util.Stack;

import org.junit.Test;

public class Main1 {
	// public static void main(String[] args) {
	// Scanner scanner = new Scanner(System.in);
	// while (scanner.hasNext()) {
	// String hex = scanner.nextLine();
	// int len = hex.length();
	// if (len <= 2) {
	// return;
	// }
	// char[] chs = hex.toCharArray();
	// Stack<Integer> stack = new Stack<Integer>();
	// for (int i = 2; i < len; i++) {
	// int temp = 0;
	// if (chs[i] >= '0' && chs[i] <= '9') {
	// temp = chs[i] - '0';
	// } else {
	// if (chs[i] >= 'a' && chs[i] <= 'z') {
	// temp = chs[i] - 87;
	// } else {
	// temp = chs[i] - 55;
	// }
	// }
	// stack.push(temp);
	// }
	// int sum = 0;
	// int exp = 0;
	// while (!stack.isEmpty()) {
	// sum += stack.pop() * Math.pow(10, exp);
	// exp++;
	// }
	// System.out.println(sum);
	// }
	// }
	public static void mian(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			long num = scanner.nextLong();
			if (num == 1) {
				continue;
			}
			for (long i = 2; i <= num; i++) {
				if (num % i == 0) {
					System.out.print(i + " ");
					num = num / i;
					i--;
				}
			}
			System.out.println();
		}
	}

	@Test
	public void test() {
		String hex = "0x76E";
		int len = hex.length();
		if (len <= 2) {
			return;
		}
		char[] chs = hex.toCharArray();
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 2; i < len; i++) {
			int temp = 0;
			if (chs[i] >= '0' && chs[i] <= '9') {
				temp = chs[i] - '0';
			} else {
				if (chs[i] >= 'a' && chs[i] <= 'z') {
					temp = chs[i] - 87;
				} else {
					temp = chs[i] - 55;
				}
			}
			stack.push(temp);
		}
		int sum = 0;
		int exp = 0;
		while (!stack.isEmpty()) {
			sum += stack.pop() * Math.pow(16, exp);
			exp++;
		}
		System.out.println(sum);
	}
}
