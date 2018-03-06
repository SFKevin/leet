package com.skf.answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class Solution {
	public static boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			HashSet<Character> rowSet = new HashSet<Character>();
			HashSet<Character> colSet = new HashSet<Character>();
			HashSet<Character> cubeSet = new HashSet<Character>();
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.' && !rowSet.add(board[i][j])) {
					return false;
				}

				if (board[j][i] != '.' && !colSet.add(board[j][i])) {
					return false;
				}

				int rowIndex = 3 * (i / 3);
				int colIndex = 3 * (i % 3);
				if (board[rowIndex + j / 3][colIndex + j % 3] != '.'
						&& !cubeSet.add(board[rowIndex + j / 3][colIndex + j
								% 3])) {
					return false;
				}
			}
		}
		return true;
	}

	public static void solveSudoku(char[][] board) {
		if (board == null || board.length == 0)
			return;
		solve(board);
	}

	private static boolean solve(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == '.') {
					for (char c = '1'; c <= '9'; c++) {
						if (isValid(board, i, j, c)) {
							board[i][j] = c;
							if (solve(board))
								return true;
							else
								board[i][j] = '.';
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isValid(char[][] board, int row, int col, char c) {
		for (int i = 0; i < 9; i++) {
			if (board[i][col] != '.' && board[i][col] == c)
				return false;
			if (board[row][i] != '.' && board[row][i] == c)
				return false;
			if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.'
					&& board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
				return false;
		}
		return true;
	}

	public static String countAndSay(int n) {
		if (n < 1)
			return null;
		String result = "1";
		for (int i = 2; i < n + 1; i++) {
			result = countAndSay(result);
		}
		return result;
	}

	private static String countAndSay(String result) {
		StringBuffer sbBuffer = new StringBuffer();
		int count = 1;
		for (int i = 1; i < result.length(); i++) {
			if (result.charAt(i) == result.charAt(i - 1)) {
				count++;
			} else {
				sbBuffer.append(count);
				sbBuffer.append(result.charAt(i - 1));
				count = 1;
			}
		}
		sbBuffer.append(count);
		sbBuffer.append(result.charAt(result.length() - 1));
		return sbBuffer.toString();
	}

	public static List<List<Integer>> combinationSum(int[] candidates,
			int target) {
		if (candidates.length == 0) {
			return null;
		}
		List<List<Integer>> answer = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);
		getResult(answer, new ArrayList<Integer>(), candidates, target, 0);
		return answer;
	}

	private static void getResult(List<List<Integer>> answer,
			List<Integer> arrayList, int[] candidates, int target, int start) {
		if (target > 0) {
			for (int i = start; i < candidates.length
					&& target >= candidates[i]; i++) {
				arrayList.add(candidates[i]);
				getResult(answer, arrayList, candidates,
						target - candidates[i], i);
				arrayList.remove(arrayList.size() - 1);
			}
		} else if (target == 0) {
			answer.add(new ArrayList<Integer>(arrayList));
		}
	}

	private static void getResult2(List<List<Integer>> answer,
			List<Integer> arrayList, int[] candidates, int target, int start) {
		if (target > 0) {
			for (int i = start; i < candidates.length
					&& target >= candidates[i]; i++) {
				if (i > start && candidates[i] == candidates[i - 1])
					continue;
				arrayList.add(candidates[i]);
				getResult2(answer, arrayList, candidates, target
						- candidates[i], i + 1);
				arrayList.remove(arrayList.size() - 1);
			}
		} else if (target == 0) {
			answer.add(new ArrayList<Integer>(arrayList));
		}
	}

	public static List<List<Integer>> combinationSum2(int[] candidates,
			int target) {
		if (candidates.length == 0) {
			return null;
		}
		List<List<Integer>> answer = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);
		getResult2(answer, new ArrayList<Integer>(), candidates, target, 0);
		return answer;

	}

	@Test
	public void test() {
		int[] a = { 10, 1, 2, 7, 6, 1, 5 };
		List<List<Integer>> tempList = combinationSum2(a, 8);
		List<Integer> temp = new ArrayList<Integer>();

		for (int i = 0; i < tempList.size(); i++) {
			temp = tempList.get(i);
			for (int j = 0; j < temp.size(); j++) {
				System.out.print(temp.get(j) + " ");
			}

			System.out.println();
		}
	}

	@Test
	public void combinationSum() {
		Set set1 = new HashSet<Integer>();
		Set set2 = new HashSet<Integer>();
		set1.add(1);
		set1.add(2);
		set2.add(2);
		set2.add(1);

		for (Object objec : set1) {
			int temp = (Integer) objec;
			System.out.println(temp);
		}
		for (Object objec : set2) {
			int temp = (Integer) objec;
			System.out.println(temp);
		}

		// return null;

	}

	private static void search(int i, List<List<Integer>> answer,
			int[] candidates, int target, Integer sum, List<Integer> list) {
		if (i > candidates.length) {
			return;
		} else {
			for (int j = 0; j < candidates.length; j++) {
				if (candidates[j] + sum == target) {
					list.add(candidates[j]);
					answer.add(list);
					list = new ArrayList<Integer>();
					sum = 0;
					return;
				} else if (candidates[j] + sum > target) {
					// list.clear();

					return;
				} else {
					sum = candidates[j] + sum;
					list.add(candidates[j]);
					search(i + 1, answer, candidates, target, sum, list);
				}
			}
		}
	}

	public int firstMissingPositive(int[] nums) {
		// int answer = 0;
		int start = 1;
		int k = 0;
		if (nums.length == 0) {
			return start;
		}
		Arrays.sort(nums);
		while (k < nums.length && nums[k] <= 0) {
			k++;
		}
		if (k == nums.length) {
			return start;
		} else {
			for (int i = k; i < nums.length; i++) {
				if (nums[i] == start) {
					start++;
					continue;
				}
			}
		}
		return start;
	}

	public int trap(int[] height) {
		int answer = 0;
		int left = 0;
		int rigth = height.length - 1;
		while (left < rigth) {
			int h = Math.min(height[left], height[rigth]);
			if (height[left] == h) {
				while (++left < rigth && height[left] < h) {
					answer += h - height[left];
				}
			} else {
				while (--rigth > left && height[rigth] < h) {
					answer += h - height[rigth];
				}

			}
		}
		return answer;
	}

	public String multiply(String num1, String num2) {
		StringBuffer sbBuffer = new StringBuffer();
		int m = num1.length();
		int n = num2.length();
		int[] answer = new int[m + n];
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				int result = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				int sum = result + answer[i + j + 1];
				answer[i + j] += sum / 10;
				answer[i + j + 1] = sum % 10;
			}
		}
		for (int p : answer) {
			if (!(sbBuffer.length() == 0 && p == 0)) {
				sbBuffer.append(p);
			}
		}
		return sbBuffer.length() == 0 ? "0" : sbBuffer.toString();
	}

	public boolean isMatch(String s, String p) {
		int sl = 0, pl = 0, match = 0, starId = -1;
		while (sl < s.length()) {
			if (pl < p.length()
					&& (p.charAt(pl) == '?' || s.charAt(sl) == p.charAt(pl))) {
				sl++;
				pl++;
			} else if (pl < p.length() && p.charAt(pl) == '*') {
				starId = pl;
				match = sl;
				pl++;
			} else if (starId != -1) {
				pl = starId + 1;
				match++;
				sl = match;
			} else {
				return false;
			}
		}

		while (pl < p.length() && p.charAt(pl) == '*') {
			pl++;
		}

		return pl == p.length();

	}

	public int jump(int[] nums) {
		int answer = 0;
		int curMax = 0;
		int curRh = 0;
		for (int i = 0; i < nums.length; i++) {
			if (curRh < i) {
				answer++;
				curRh = curMax;
			}
			curMax = Math.max(curMax, nums[i] + i);
		}
		return answer;

	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> answer = new ArrayList<List<Integer>>();
		find(0, answer, new ArrayList<Integer>(), nums);
		return answer;
	}

	private static void find(int i, List<List<Integer>> answer,
			ArrayList<Integer> list, int[] nums) {
		if (list.size() == nums.length) {
			answer.add(new ArrayList<Integer>(list));
			return;
		} else {
			for (int j = 0; j < nums.length; j++) {
				if (list.contains(nums[j])) {
					continue;
				}
				list.add(nums[j]);
				find(0, answer, list, nums);
				list.remove(list.size() - 1);
			}
		}

	}

	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> answer = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		findUnique(0, answer, new ArrayList<Integer>(), nums,
				new boolean[nums.length]);
		return answer;
	}

	private void findUnique(int i, List<List<Integer>> answer,
			ArrayList<Integer> arrayList, int[] nums, boolean[] used) {
		if (arrayList.size() == nums.length) {
			answer.add(new ArrayList<Integer>(arrayList));
			return;
		}
		for (int j = 0; j < nums.length; j++) {
			if (used[j] || j > 0 && nums[j] == nums[j - 1] && !used[j - 1]) {
				continue;
			}
			used[j] = true;
			arrayList.add(nums[j]);
			findUnique(0, answer, arrayList, nums, used);
			used[j] = false;
			arrayList.remove(arrayList.size() - 1);
		}
	}

	public void rotate(int[][] matrix) {
		int temp;
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - i; j++) {
				temp = matrix[i][j];
				matrix[i][j] = matrix[n - 1 - j][n - 1 - i];
				matrix[n - 1 - j][n - 1 - i] = temp;

			}
		}
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < n; j++) {
				temp = matrix[i][j];
				matrix[i][j] = matrix[n - 1 - i][j];
				matrix[n - 1 - i][j] = temp;
			}

		}
	}

	// public List<List<String>> groupAnagrams(String[] strs) {
	// Map<Character, Integer> map = new HashMap<Character, Integer>();
	// for (int i = 97; i <= 122; i++) {
	// map.put((char) i, i);
	// }
	// List<List<String>> answer = new ArrayList<List<String>>();
	// int size = strs.length;
	// Map<Long, List<String>> hashMap = new HashMap<Long, List<String>>();
	// for (int i = 0; i < size; i++) {
	// if (hashMap.containsKey(calculate(strs[i], map))) {
	// hashMap.get(calculate(strs[i], map)).add(strs[i]);
	// } else {
	//
	// hashMap.put(calculate(strs[i], map), new ArrayList<String>());
	// hashMap.get(calculate(strs[i], map)).add(strs[i]);
	// }
	// }
	// for (Map.Entry<Long, List<String>> entry : hashMap.entrySet()) {
	// answer.add(entry.getValue());
	// }
	// return answer;
	// }
	//
	// private long calculate(String string, Map<Character, Integer> map) {
	// long sum = 0;
	// for (int i = 0; i < string.length(); i++) {
	// sum += map.get(string.charAt(i));
	// }
	// return sum;
	// }
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String str : strs) {
			char[] ca = str.toCharArray();
			Arrays.sort(ca);
			String keyString = String.valueOf(ca);
			if (!map.containsKey(keyString)) {
				map.put(keyString, new ArrayList<String>());
			}
			map.get(keyString).add(str);
		}
		return new ArrayList<List<String>>(map.values());
	}

	public double myPow(double x, int n) {
		if (n == 0) {
			return 1;
		} else {
			if (n < 0) {
				if (n == Integer.MIN_VALUE) {
					return 1 / (myPow(x, Integer.MAX_VALUE) * x);
				} else {
					return 1 / myPow(x, -n);
				}
			} else {
				if (n % 2 == 0) {
					double temp = myPow(x, n >> 1);
					return temp * temp;
				} else {
					double temp = myPow(x, (n - 1) >> 1);
					return temp * temp * x;
				}
			}
		}

	}

	public static List<List<String>> solveNQueens(int n) {
		List<List<String>> answer = new ArrayList<List<String>>();
		int[][] a = new int[n + 1][n + 1];
		solve(a, answer, n, 1);
		return answer;
	}

	private static void solve(int[][] a, List<List<String>> answer, int n, int i) {
		if (i > n) {
			List<String> tempList = new ArrayList<String>();
			for (int k = 1; k <= n; k++) {
				StringBuffer tempBuffer = new StringBuffer();
				for (int h = 1; h <= n; h++) {
					if (a[k][h] != 0) {
						tempBuffer.append("Q");
					} else {
						tempBuffer.append(".");
					}
				}
				tempList.add(tempBuffer.toString());
			}
			answer.add(tempList);
			return;
		} else {
			for (int j = 1; j < n + 1; j++) {
				a[i][j] = 1;
				if (isInsert(a, i, j, n)) {
					a[i][0] = j;
					solve(a, answer, n, i + 1);
				}
				a[i][j] = 0;
			}
		}
	}

	private static boolean isInsert(int[][] a, int i, int j, int n) {
		int temp;
		for (int k = 1; k < i; k++) {
			temp = a[k][0];
			if (temp == j || Math.abs(k - i) == Math.abs(j - temp)) {
				return false;
			}
		}
		return true;
	}

	public int maxSubArray(int[] nums) {
		int length = nums.length;
		long[] d = new long[length];
		d[0] = nums[0];
		long max = d[0];
		for (int i = 1; i < length; i++) {
			d[i] = nums[i] + (d[i - 1] > 0 ? d[i - 1] : 0);
			max = Math.max(max, d[i]);
		}
		return (int) max;
	}

	public int uniquePaths(int m, int n) {
		int[][] grid = new int[m][n];
		for (int i = 0; i < m; i++) {
			grid[i][0] = 1;
		}
		for (int i = 0; i < n; i++) {
			grid[0][i] = 1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {

				grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
			}
		}
		return grid[m - 1][n - 1];

	}

	@Test
	public void test1() {
		solveNQueens(4);
	}
}
