package Test3;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String str = sc.nextLine();
			String[] array = str.split(" ");
			int len = array.length;
			int[][] a = new int[len][len];
			for (int i = 0; i < len; i++) {
				a[0][i] = Integer.valueOf(array[i]);
			}
			for (int i = 1; i < len; i++) {
				String temp = sc.nextLine();
				String[] arr = temp.split(" ");
				for (int j = 0; j < len; j++) {
					a[i][j] = Integer.valueOf(arr[j]);
				}
			}

			solution(a);

			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len - 1; j++) {
					System.out.print(a[i][j]);
					System.out.print(" ");
				}
				if (i < len - 1)
					System.out.println(a[i][len - 1]);
				else
					System.out.print(a[i][len - 1]);

			}
		}
	}

	public static void solution(int[][] a) {
		int len = a.length;
		for (int i = 0; i < len; i++) {
			for (int j = i; j < a[0].length; j++) {
				int temp = 0;
				temp = a[i][j];
				a[i][j] = a[j][i];
				a[j][i] = temp;
			}
		}
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len / 2; j++) {
				int temp = 0;
				temp = a[i][j];
				a[i][j] = a[i][len - 1 - j];
				a[i][len - 1 - j] = temp;
			}
		}
	}
}
