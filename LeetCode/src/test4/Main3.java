package test4;

import java.util.Scanner;

public class Main3 {
	public static void main(String[] args) {
		int[][] path = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		Scanner scanner = new Scanner(System.in);
		int M = scanner.nextInt();
		scanner.nextLine();
		int[][] array = new int[M][M];
		boolean[][] flag = new boolean[M][M];
		int count = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++)
				array[i][j] = scanner.nextInt();
			scanner.nextLine();
		}
		for (int i = 0; i < M; i++)
			for (int j = 0; j < M; j++)
				if (!flag[i][j] && array[i][j] == 1) {
					back(array, flag, i, j, M, path);
					count++;
				}
		System.out.println(count);
	}

	private static void back(int[][] array, boolean[][] flag, int row, int col, int M, int[][] path) {
		if (row >= M || row < 0 || col >= M || col < 0)
			return;
		else if (array[row][col] == 0 || flag[row][col])
			return;
		else {
			flag[row][col] = true;
		}
		for (int i = 0; i < path.length; i++) {
			back(array, flag, row + path[i][0], col + path[i][1], M, path);
		}
	}
}
