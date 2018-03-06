package com.skf.answer;

import java.util.TreeSet;

import org.junit.Test;

public class Solution5 {
	/**
	 * 520. Detect Capital Given a word, you need to judge whether the usage of
	 * capitals in it is right or not.
	 * 
	 * We define the usage of capitals in a word to be right when one of the
	 * following cases holds:
	 * 
	 * All letters in this word are capitals, like "USA". All letters in this
	 * word are not capitals, like "leetcode". Only the first letter in this
	 * word is capital if it has more than one letter, like "Google".
	 * 
	 * Otherwise, we define that this word doesn't use capitals in a right way.
	 * 
	 * @param word
	 * @return
	 */
	public boolean detectCapitalUse(String word) {
		int len = word.length();
		if (len <= 0) {
			return false;
		}
		if (len == 1) {
			return true;
		}
		int k = 1;
		while (k < len) {
			if (word.charAt(0) >= 'a' && word.charAt(0) <= 'z') {
				if (word.charAt(k) >= 'a' && word.charAt(k) <= 'z') {
					k++;
				} else {
					return false;
				}
			} else {
				if (word.charAt(1) >= 'a' && word.charAt(1) <= 'z') {
					if (word.charAt(k) >= 'a' && word.charAt(k) <= 'z') {
						k++;
					} else {
						return false;
					}
				} else {
					if (word.charAt(k) >= 'A' && word.charAt(k) <= 'Z') {
						k++;
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 168. Excel Sheet Column Title
	 * 
	 * @param n
	 * @return
	 */
	public String convertToTitle(int n) {
		StringBuilder result = new StringBuilder();
		while (n > 0) {
			n--;
			result.insert(0, (char) ('A' + n % 26));
			n /= 26;
		}
		return result.toString();
	}

	/**
	 * 404. Sum of Left Leaves Find the sum of all left leaves in a given binary
	 * tree.
	 * 
	 * @param root
	 * @return
	 */
	public int sumOfLeftLeaves(TreeNode root) {
		if (root == null)
			return 0;
		int sum = 0;
		if (root.left != null) {
			if (root.left.left == null && root.left.right == null) {
				sum += root.left.val;
			} else {
				sum += sumOfLeftLeaves(root.left);
			}
		}
		sum += sumOfLeftLeaves(root.right);
		return sum;
	}

	/**
	 * 390. Elimination Game There is a list of sorted integers from 1 to n.
	 * Starting from left to right, remove the first number and every other
	 * number afterward until you reach the end of the list.
	 * 
	 * Repeat the previous step again, but this time from right to left, remove
	 * the right most number and every other number from the remaining numbers.
	 * 
	 * We keep repeating the steps again, alternating left to right and right to
	 * left, until a single number remains.
	 * 
	 * Find the last number that remains starting with a list of length n.
	 * 
	 * @param n
	 * @return
	 */
	public int lastRemaining(int n) {
		boolean left = true;
		int remaining = n;
		int step = 1;
		int head = 1;
		while (remaining > 1) {
			if (left || remaining % 2 == 1) {
				head = head + step;
			}
			remaining = remaining / 2;
			step *= 2;
			left = !left;
		}
		return head;
	}

	/**
	 * 363. Max Sum of Rectangle No Larger Than K Given a non-empty 2D matrix
	 * matrix and an integer k, find the max sum of a rectangle in the matrix
	 * such that its sum is no larger than k.
	 * 
	 * first consider the situation matrix is 1D we can save every sum of
	 * 0~i(0<=i<len) and binary search previous sum to find possible result for
	 * every index, time complexity is O(NlogN). so in 2D matrix, we can sum up
	 * all values from row i to row j and create a 1D array to use 1D array
	 * solution. If col number is less than row number, we can sum up all values
	 * from col i to col j then use 1D array solution.
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int maxSumSubmatrix(int[][] matrix, int target) {
		int row = matrix.length;
		if (row == 0)
			return 0;
		int col = matrix[0].length;
		int m = Math.min(row, col);
		int n = Math.max(row, col);
		// indicating sum up in every row or every column
		boolean colIsBig = col > row;
		int res = Integer.MIN_VALUE;
		for (int i = 0; i < m; i++) {
			int[] array = new int[n];
			// sum from row j to row i
			for (int j = i; j >= 0; j--) {
				int val = 0;
				TreeSet<Integer> set = new TreeSet<Integer>();
				set.add(0);
				// traverse every column/row and sum up
				for (int k = 0; k < n; k++) {
					array[k] = array[k]
							+ (colIsBig ? matrix[j][k] : matrix[k][j]);
					val = val + array[k];
					// use TreeMap to binary search previous sum to get possible
					// result
					Integer subres = set.ceiling(val - target);
					if (null != subres) {
						res = Math.max(res, val - subres);
					}
					set.add(val);
				}
			}
		}
		return res;
	}

	@Test
	public void test() {
		System.out.println(convertToTitle(52));
	}
}
