package com.skf.shuzu;

public class Test3 {
	public static int reverseCount(int a[]) {
		int count = 0;
		int len = a.length;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (a[i] > a[j]) {
					count++;
				}
			}
		}
		return count;
	}

	public static int max(int a, int b, int c) {
		int max = a < b ? b : a;
		max = max < c ? c : max;
		return max;
	}

	public static int min(int a, int b, int c) {
		int min = a < b ? a : b;
		min = min < c ? min : c;
		return min;
	}

	public static int Solvingciolence(int[] a, int[] b, int[] c) {
		int aLen = a.length;
		int bLen = b.length;
		int cLen = c.length;
		int minDist = max(Math.abs(a[0] - b[0]), Math.abs(a[0] - c[0]),
				Math.abs(b[0] - c[0]));
		int dist = 0;
		for (int i = 0; i < aLen; i++) {
			for (int j = 0; j < bLen; j++) {
				for (int k = 0; k < cLen; k++) {
					dist = max(Math.abs(a[i] - b[j]), Math.abs(a[i] - c[k]),
							Math.abs(b[j] - c[k]));
					if (minDist > dist) {
						minDist = dist;
					}
				}
			}
		}
		return minDist;
	}

	public static int minDistance(int[] a, int[] b, int[] c) {
		int aLen = a.length;
		int bLen = b.length;
		int cLen = c.length;
		int curDist = 0;
		int minDist = Integer.MAX_VALUE;
		int min = 0;
		int i = 0;
		int j = 0;
		int k = 0;
		while (true) {
			curDist = max(Math.abs(a[i] - b[j]), Math.abs(a[i] - c[k]),
					Math.abs(b[j] - c[k]));
			if (curDist < minDist) {
				minDist = curDist;
			}
			min = min(a[i], b[j], c[k]);
			if (min == a[i]) {
				if (++i >= aLen) {
					break;
				} else if (min == b[j]) {
					if (++j >= bLen) {
						break;
					}
				} else {
					if (++k > cLen) {
						break;
					}
				}
			}
		}
		return minDist;
	}

	/**
	 * 474. Ones and Zeroes
	 * 
	 * @param strs
	 * @param m
	 * @param n
	 * @return
	 */
	public int findMaxForm(String[] strs, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];
		for (String s : strs) {
			int[] counts = count(s);
			for (int i = m; i >= counts[0]; i--) {
				for (int j = n; j >= counts[1]; j--) {
					dp[i][j] = Math.max(1 + dp[i - counts[0]][j - counts[1]],
							dp[i][j]);
				}
			}
		}
		return dp[m][n];
	}

	private int[] count(String s) {
		int[] res = new int[2];
		for (int i = 0; i < s.length(); i++) {
			res[s.charAt(i) - '0']++;
		}
		return res;
	}

	/**
	 * 738. Monotone Increasing Digits
	 * 
	 * @param N
	 * @return
	 */
	public int monotoneIncreasingDigits(int N) {
		if (N <= 9) {
			return N;
		}
		char[] x = String.valueOf(N).toCharArray();
		int mark = x.length;
		for (int i = x.length - 1; i > 0; i--) {
			if (x[i] < x[i - 1]) {
				mark = i - 1;
				x[i - 1]--;
			}
		}
		for (int i = mark + 1; i < x.length; i++) {
			x[i] = '9';
		}
		return Integer.parseInt(new String(x));
	}

	/**
	 * 537. Complex Number Multiplication
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public String complexNumberMultiply(String a, String b) {
		int index_a = a.indexOf("+");
		int index_b = b.indexOf("+");
		int a_a = Integer.parseInt(a.substring(0, index_a));
		int a_b = Integer.parseInt(a.substring(index_a + 1, a.length() - 1));
		int b_c = Integer.parseInt(b.substring(0, index_b));
		int b_d = Integer.parseInt(b.substring(index_b + 1, b.length() - 1));
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(String.valueOf(a_a * b_c - a_b * b_d)).append("+");
		sBuffer.append(String.valueOf(a_a * b_d + a_b * b_c)).append("i");
		return sBuffer.toString();
	}
}
