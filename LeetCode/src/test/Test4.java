package test;

import java.util.ArrayList;
import java.util.List;

public class Test4 {
	public int JumpFloor(int target) {
		if (target == 1) {
			return 1;
		}
		if (target == 2) {
			return 2;
		}

		return JumpFloor(target - 1) + JumpFloor(target - 2);
	}

	public int JumpFloorII(int target) {
		if (target == 0) {
			return 0;
		}
		if (target == 1) {
			return 1;
		}
		return 2 * JumpFloorII(target - 1);
	}

	/**
	 * 120. Triangle Given a triangle, find the minimum path sum from top to
	 * bottom. Each step you may move to adjacent numbers on the row below. [
	 * [2], [3,4], [6,5,7], [4,1,8,3] ]
	 * 
	 * @param triangle
	 * @return
	 */
	public int minimumTotal(List<List<Integer>> triangle) {
		if (triangle == null || triangle.size() < 1) {
			return 0;
		}
		int[][] minSum = new int[triangle.size()][];
		for (int i = 0; i < minSum.length; i++) {
			minSum[i] = new int[i + 1];
		}
		minSum[0][0] = triangle.get(0).get(0);
		for (int i = 1; i < minSum.length; i++) {
			List<Integer> line = triangle.get(i);
			for (int j = 0; j < minSum[i].length; j++) {
				if (j == 0) {
					minSum[i][0] = line.get(0) + minSum[i - 1][0];
				} else if (i == j) {
					minSum[i][j] = line.get(j) + minSum[i - 1][j - 1];
				} else if (j < i) {
					minSum[i][j] = line.get(j)
							+ Math.min(minSum[i - 1][j], minSum[i - 1][j - 1]);
				}
			}
		}
		int min = minSum[minSum.length - 1][0];
		int len = minSum[minSum.length - 1].length;
		for (int i = 1; i < len; i++) {
			if (min > minSum[len - 1][i]) {
				min = minSum[len - 1][i];
			}
		}
		return min;
	}

	/**
	 * 765. Couples Holding Hands N couples sit in 2N seats arranged in a row
	 * and want to hold hands. We want to know the minimum number of swaps so
	 * that every couple is sitting side by side. A swap consists of choosing
	 * any two people, then they stand up and switch seats.
	 * 
	 * The people and seats are represented by an integer from 0 to 2N-1, the
	 * couples are numbered in order, the first couple being (0, 1), the second
	 * couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
	 * 
	 * The couples' initial seating is given by row[i] being the value of the
	 * 
	 * @param row
	 * @return
	 */
	public int minSwapsCouples(int[] row) {
		int len = row.length;
		if (len % 2 == 1) {
			return -1;
		}
		int count = 0;
		int temp;
		for (int i = 0; i < len - 2; i += 2) {
			if (row[i] % 2 == 0) {
				if (row[i + 1] - row[i] != 1) {
					for (int j = i + 2; j < len; j++) {
						if (row[j] - row[i] == 1) {
							temp = row[i + 1];
							row[i + 1] = row[j];
							row[j] = temp;
							count++;
							break;
						}
					}
				}
			} else {
				if (row[i + 1] - row[i] != -1) {
					for (int j = i + 2; j < len; j++) {
						if (row[j] - row[i] == -1) {
							temp = row[i + 1];
							row[i + 1] = row[j];
							row[j] = temp;
							count++;
							break;
						}
					}
				}
			}
		}
		return count;
	}

	/**
	 * 655. Print Binary Tree Print a binary tree in an m*n 2D string array
	 * following these rules:
	 * 
	 * The row number m should be equal to the height of the given binary tree.
	 * The column number n should always be an odd number. The root node's value
	 * (in string format) should be put in the exactly middle of the first row
	 * it can be put. The column and the row where the root node belongs will
	 * separate the rest space into two parts (left-bottom part and right-bottom
	 * part). You should print the left subtree in the left-bottom part and
	 * print the right subtree in the right-bottom part. The left-bottom part
	 * and the right-bottom part should have the same size. Even if one subtree
	 * is none while the other is not, you don't need to print anything for the
	 * none subtree but still need to leave the space as large as that for the
	 * other subtree. However, if two subtrees are none, then you don't need to
	 * leave space for both of them. Each unused space should contain an empty
	 * string "". Print the subtrees following the same rules.
	 * 
	 * @param root
	 * @return
	 */
	public List<List<String>> printTree(TreeNode root) {
		List<List<String>> res = new ArrayList<List<String>>();
		int height = root == null ? 1 : getLayer(root);
		int rows = height;
		int colums = (int) (Math.pow(2, height) - 1);
		List<String> row = new ArrayList<String>();
		for (int i = 0; i < colums; i++) {
			row.add("");
		}
		for (int i = 0; i < rows; i++) {
			res.add(new ArrayList<String>(row));
		}
		populateRes(root, res, 0, rows, 0, colums - 1);
		return res;

	}

	private void populateRes(TreeNode root, List<List<String>> res, int row,
			int totalRows, int i, int j) {
		if (row == totalRows || root == null) {
			return;
		}
		res.get(row).set((i + j) / 2, Integer.toString(root.val));
		populateRes(root.left, res, row + 1, totalRows, i, (i + j) / 2 - 1);
		populateRes(root.right, res, row + 1, totalRows, (i + j) / 2 + 1, j);
	}

	private int getLayer(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(getLayer(root.left), getLayer(root.right));
	}
}
