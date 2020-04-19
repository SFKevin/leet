package com.skf.niuke;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int k = scanner.nextInt();
		int left = 1;
		int right = m * n;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (cal(mid, n, m) < k) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.print(left);
	}

	private static int cal(int mid, int n, int m) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += (mid >= m * i) ? m : mid / i;
		}
		return sum;
	}
}
