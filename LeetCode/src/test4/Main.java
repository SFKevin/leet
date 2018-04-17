package test4;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = set(n);
		System.out.println(k);
	}

	public static int set(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1 || n == -1) {
			return 1;
		}
		if (n == 2 || n == -2) {
			return 2;
		}
		if (n % 2 == 0) {
			return (set(n / 2) + 1);
		} else {
			return (set(n - 1) > set(n + 1) ? set(n + 1) + 1 : set(n - 1) + 1);
		}
	}
}
