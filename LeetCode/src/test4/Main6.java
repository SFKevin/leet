package test4;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class Main6 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		int m = scanner.nextInt();
		scanner.nextLine();
		boolean[][] flag = new boolean[n + 1][n + 1];
		for (int i = 0; i < m; i++) {
			int key = scanner.nextInt();
			int value = scanner.nextInt();
			flag[key][value] = true;
		}
		if (m < n - 1) {
			System.out.println(0);
			return;
		}
		int count = 0;
		for (int i = 1; i <= n; i++) {
			HashSet<Integer> set = new HashSet<Integer>();
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(i);
			while (!stack.isEmpty()) {
				int size = stack.size();
				for (int h = 0; h < size; h++) {
					int temp = stack.pop();
					for (int j = 1; j <= n; j++) {
						if (j == i)
							continue;
						if (flag[j][temp] && !set.contains(j)) {
							set.add(j);
							stack.add(j);
						}
					}
				}
			}
			if (set.size() == n - 1) {
				count++;
			}
		}
		System.out.println(count);
	}

}
