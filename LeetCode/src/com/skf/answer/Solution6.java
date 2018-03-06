package com.skf.answer;

public class Solution6 {
	public int lis(int[] A) {
		int len = A.length;
		int max = 0;
		int[] d = new int[len];
		for (int i = 0; i < len; i++) {
			d[i] = 1;
			for (int j = 0; j < i; j++) {
				if (A[j] <= A[i] && (d[j] + 1) >= d[i]) {
					d[i] = d[j] + 1;
				}
			}
			len = d[i] > len ? d[i] : len;
		}
		return len;
	}

	/*
	 * 516. Longest Palindromic Subsequence
	 */
	public int longestPalindromeSubseq(String s) {
		int[][] dp = new int[s.length()][s.length()];
		for (int i = s.length() - 1; i >= 0; i--) {
			dp[i][i] = 1;
			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(j) == s.charAt(i)) {
					dp[i][j] = dp[i + 1][j - 1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[0][s.length() - 1];
	}
}
