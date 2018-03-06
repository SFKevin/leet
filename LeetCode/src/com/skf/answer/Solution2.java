package com.skf.answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class Solution2 {
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int[][] path = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (obstacleGrid[i][j] == 1) {
					path[i][j] = 0;
				} else if (i == 0 && j == 0) {
					path[i][j] = 1;
				} else {
					if (i == 0 && j > 0) {
						path[i][j] = path[i][j - 1];
					} else if (j == 0 && i > 0) {
						path[i][j] = path[i - 1][j];
					} else {
						path[i][j] = path[i - 1][j] + path[i][j - 1];
					}
				}
			}

		}
		return path[m - 1][n - 1];
	}

	public int minPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		long[][] path = new long[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					path[i][j] = grid[i][j];
				} else if (i == 0) {
					path[i][j] = path[i][j - 1] + grid[i][j];
				} else if (j == 0) {
					path[i][j] += path[i - 1][j] + grid[i][j];
				} else {
					path[i][j] = (path[i - 1][j] + grid[i][j]) < (path[i][j - 1] + grid[i][j]) ? (path[i - 1][j] + grid[i][j])
							: (path[i][j - 1] + grid[i][j]);
				}
			}
		}
		return (int) path[m - 1][n - 1];
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> answer = new ArrayList<Integer>();
		if (matrix.length == 0) {
			return answer;
		}
		int rowBegin = 0;
		int rowEnd = matrix.length - 1;
		int colBegin = 0;
		int colEnd = matrix[0].length - 1;
		while (rowBegin <= rowEnd && colBegin <= colEnd) {
			for (int j = colBegin; j <= colEnd; j++) {
				answer.add(matrix[rowBegin][j]);
			}
			rowBegin++;
			for (int j = rowBegin; j <= rowEnd; j++) {
				answer.add(matrix[j][colEnd]);
			}
			colEnd--;
			if (rowBegin <= rowEnd) {
				for (int j = colEnd; j >= colBegin; j--) {
					answer.add(matrix[rowEnd][j]);
				}
			}
			rowEnd--;
			if (colBegin <= colEnd) {
				for (int j = rowEnd; j >= rowBegin; j--) {
					answer.add(matrix[j][colBegin]);
				}
			}
			colBegin++;
		}
		return answer;
	}

	public boolean canJump(int[] nums) {
		int length = nums.length;
		int max = 0;
		for (int i = 0; i < length; i++) {
			if (i > max) {
				return false;
			}
			max = Math.max(nums[i] + i, max);
		}
		return true;
	}

	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> answer = new ArrayList<Interval>();
		int size = intervals.size();
		int[] start = new int[size];
		int[] end = new int[size];
		for (int i = 0; i < size; i++) {
			start[i] = intervals.get(i).start;
			end[i] = intervals.get(i).end;
		}
		Arrays.sort(start);
		Arrays.sort(end);
		int j = 0;
		for (int i = 0; i < size; i++) {
			if (i == size - 1 || start[i + 1] > end[i]) {
				answer.add(new Interval(start[j], end[i]));
				j = i + 1;
			}
		}
		return answer;

	}

	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> answer = new ArrayList<Interval>();
		intervals.add(newInterval);
		int size = intervals.size();
		int[] start = new int[size];
		int[] end = new int[size];
		for (int i = 0; i < size; i++) {
			start[i] = intervals.get(i).start;
			end[i] = intervals.get(i).end;
		}
		Arrays.sort(start);
		Arrays.sort(end);
		int j = 0;
		for (int i = 0; i < size; i++) {
			if (i == size - 1 || start[i + 1] > end[i]) {
				answer.add(new Interval(start[j], end[i]));
				j = i + 1;
			}
		}
		return answer;
	}

	public static int lengthOfLastWord(String s) {
		if (s.trim().length() == 0) {
			return 0;
		}
		int answer = 0;
		String[] string = s.trim().split(" ");
		return string[string.length - 1].length();
	}

	public int[][] generateMatrix(int n) {
		int[][] answer = new int[n][n];
		if (n == 0) {
			return answer;
		}
		int rowStart = 0;
		int rowEnd = n - 1;
		int colStart = 0;
		int colEnd = n - 1;
		int num = 1;
		while (rowStart <= rowEnd && colStart <= colEnd) {
			for (int i = colStart; i <= colEnd; i++) {
				answer[rowStart][i] = num++;
			}
			rowStart++;
			for (int i = rowStart; i <= rowEnd; i++) {
				answer[i][colEnd] = num++;
			}
			colEnd--;
			for (int i = colEnd; i >= colStart; i--) {
				answer[rowEnd][i] = num++;
			}
			rowEnd--;
			for (int i = rowEnd; i >= rowStart; i--) {
				answer[i][colStart] = num++;
			}
			colStart++;
		}

		return answer;

	}

	public String getPermutation(int n, int k) {
		List<String> list = new ArrayList<String>();
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 1; i <= n; i++) {
			sBuffer.append(String.valueOf(i));
		}
		dfs(0, n, list, new StringBuffer(), sBuffer.toString());
		return list.get(k - 1);

	}

	private void dfs(int i, int n, List<String> list,
			StringBuffer stringBuffer, String str) {
		if (i == n) {
			list.add(stringBuffer.toString());
			return;
		}
		for (int j = 0; j < n; j++) {
			if (stringBuffer.toString().indexOf(str.charAt(j)) == -1) {
				stringBuffer.append(str.charAt(j));
				dfs(i + 1, n, list, stringBuffer, str);
				stringBuffer.deleteCharAt(stringBuffer.length() - 1);
			}
		}
	}

	public String getPermutation2(int n, int k) {
		List<Integer> num = new LinkedList<Integer>();
		for (int i = 1; i <= n; i++) {
			num.add(i);
		}
		int[] fact = new int[n];
		fact[0] = 1;
		for (int i = 1; i < n; i++) {
			fact[i] = i * fact[i - 1];
		}
		k = k - 1;
		StringBuilder sbBuilder = new StringBuilder();
		for (int i = n; i > 0; i--) {
			int ind = k / fact[i - 1];
			k = k % fact[i - 1];
			sbBuilder.append(num.get(ind));
			num.remove(ind);
		}
		return sbBuilder.toString();
	}

	/**
	 * 538
	 * 
	 * @param word1
	 * @param word2
	 * @return Given two words word1 and word2, find the minimum number of steps
	 *         required to make word1 and word2 the same, where in each step you
	 *         can delete one character in either string.
	 * 
	 *         Example 1:
	 * 
	 *         Input: "sea", "eat" Output: 2 Explanation: You need one step to
	 *         make "sea" to "ea" and another step to make "eat" to "ea".
	 * 
	 *         Note:
	 * 
	 *         The length of given words won't exceed 500. Characters in given
	 *         words can only be lower-case letters.
	 */
	public int minDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		int k = 0;
		for (int i = 0; i < len1; i++) {
			if (word2.indexOf(word1.charAt(i)) != -1) {
				k = k + 1;
			}
		}
		return len1 + len2 - 2 * k;
	}

	public int minDistance2(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		int[][] state = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0) {
					state[i][j] = 0;
				} else {
					if (word1.charAt(i) == word2.charAt(j)) {
						state[i][j] = state[i - 1][j - 1] + 1;
					} else {
						state[i][j] = Math
								.max(state[i - 1][j], state[i][j - 1]);
					}
				}
			}
		}
		return m + n - state[m - 1][n - 1];

	}

	/**
	 * 543. Diameter of Binary Tree Given a binary tree, you need to compute the
	 * length of the diameter of the tree. The diameter of a binary tree is the
	 * length of the longest path between any two nodes in a tree. This path may
	 * or may not pass through the root.
	 * 
	 * Example: Given a binary tree
	 * 
	 * 1 / \ 2 3 / \ 4 5
	 * 
	 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
	 * 
	 * Note: The length of path between two nodes is represented by the number
	 * of edges between them.
	 * 
	 * @param root
	 * @return
	 */
	int max = 0;

	public int diameterOfBinaryTree(TreeNode root) {
		maxPath(root);
		return max;
	}

	private int maxPath(TreeNode root) {
		if (root == null)
			return 0;
		int left = maxPath(root.left);
		int right = maxPath(root.right);
		max = Math.max(max, left + right);
		return Math.max(left, right) + 1;
	}

	@Test
	public void test() {
		String string = getPermutation(9, 24);
		System.out.println(string);

	}
}
