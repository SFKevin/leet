package com.skf.leetcode;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Test;

public class test1 {
	/**
	 * 373. Find K Pairs with Smallest Sums
	 * 
	 * @param nums1
	 * @param nums2
	 * @param k
	 * @return
	 */
	// public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
	// int len1 = nums1.length;
	// int len2 = nums2.length;
	// List<int[]> answer = new ArrayList<int[]>();
	// if (len1 == 0 || len2 == 0) {
	// return answer;
	// }
	// boolean[][] flag = new boolean[len1][len2];
	// for (int i = 0; i < len1; i++) {
	// for (int j = 0; j < len2; j++) {
	// flag[i][j] = false;
	// }
	// }
	// if (len1 * len2 <= k) {
	// for (int i = 0; i < len1; i++) {
	// for (int j = 0; j < len2; j++) {
	// int[] temp = new int[2];
	// temp[0] = nums1[i];
	// temp[1] = nums2[j];
	// answer.add(temp);
	// }
	// }
	// } else {
	// int count = 0;
	// int i = 0;
	// int j = 0;
	// while (count < k && i < len1 && j < len2) {
	// int[] temp = new int[2];
	// temp[0] = nums1[i];
	// temp[1] = nums2[j];
	// flag[i][j] = true;
	// answer.add(temp);
	// count++;
	// if (i == len1 - 1) {
	// j++;
	// continue;
	// } else if (j == len2 - 1) {
	// i++;
	// j = 0;
	// continue;
	// } else {
	// if (nums1[i] + nums2[j + 1] > nums1[i + 1] + nums2[0]) {
	// i++;
	// j = 0;
	// continue;
	// } else {
	// j++;
	// continue;
	// }
	// }
	// }
	// }
	// return answer;
	// }
	public List<int[]> kSmallestPairs(final int[] nums1, final int[] nums2,
			int k) {
		int len1 = nums1.length;
		int len2 = nums2.length;
		List<int[]> answer = new ArrayList<int[]>();
		if (len1 == 0 || len2 == 0) {
			return answer;
		}
		PriorityQueue<int[]> queue = new PriorityQueue(len1 * len2,
				new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						return nums1[o1[0]] + nums2[o1[1]]
								- (nums1[o2[0]] + nums2[o2[1]]);
					}
				});
		queue.add(new int[] { 0, 0 });
		while (k > 0 && !queue.isEmpty()) {
			k--;
			int[] pair = queue.poll();
			int i = pair[0];
			int j = pair[1];
			answer.add(new int[] { nums1[i], nums2[j] });
			if (j + 1 < len2) {
				queue.offer(new int[] { i, j + 1 });
			}
			if (j == 0 && i + 1 < len1) {
				queue.offer(new int[] { i + 1, 0 });
			}
		}
		return answer;
	}

	public int compress(char[] chars) {
		if (chars == null || chars.length == 0)
			return 0;
		int point = 0;
		int i = 0;
		int j = 0;
		while (i < chars.length) {
			if (j == chars.length || chars[i] != chars[j]) {

				chars[point++] = chars[i];
				if (j - i <= 1) {
					i = j;
				} else {
					String num = Integer.toString(j - i);
					for (int m = 0; m < num.length(); m++) {
						chars[point++] = num.charAt(m);
					}
					i = j;
				}
			} else {
				j++;
			}
		}
		return point;
	}

	public int evalRPN(String[] tokens) {
		int len = tokens.length;
		if (len == 0 || len == 2) {
			throw new RuntimeException("error");
		} else if (len == 1) {
			return Integer.parseInt(tokens[0]);
		}
		int con = len;
		long answer = 0;
		while (con > 1) {
			for (int i = 0; i < len; i++) {
				if (tokens[i].equals("+") || tokens[i].equals("-")
						|| tokens[i].equals("*") || tokens[i].equals("/")) {
					String op1 = null;
					String op2 = null;
					String op3 = tokens[i];
					int k;
					for (k = i - 1; k >= 0; k--) {
						if (!tokens[k].equals("end")) {
							op1 = tokens[k];
							tokens[k] = "end";
							break;
						}
					}
					for (int j = k - 1; j >= 0; j--) {
						if (!tokens[j].equals("end")) {
							op2 = tokens[j];
							tokens[j] = "end";
							break;
						}
					}
					answer = calu(op1, op2, op3);
					tokens[i] = String.valueOf(answer);
					con = con - 2;
					break;
				}
			}
		}
		return (int) answer;
	}

	private int calu(String op1, String op2, String op3) {
		int num1 = Integer.parseInt(op2);
		int num2 = Integer.parseInt(op1);
		long res = 0;
		switch (op3) {
		case "+":
			res = num1 + num2;
			break;
		case "-":
			res = num1 - num2;
			break;
		case "*":
			res = num1 * num2;
			break;
		case "/":
			res = num1 / num2;
			break;
		default:
			break;
		}
		return (int) res;
	}

	public int run(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = run(root.left);
		int right = run(root.right);
		if (left == 0 || right == 0) {
			return 1 + left + right;
		}
		return Math.min(left, right) + 1;
	}

	public int maxPoints(Point[] points) {
		int len = points.length;
		if (len <= 2) {
			return len;
		}
		int answer = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (j == i) {
					continue;
				}
				int temp = 2;
				for (int k = 0; k < len; k++) {
					if (k == i || k == j) {
						continue;
					}
					if (isInOneLine(points[i], points[j], points[k])) {
						temp++;
					}
				}
				if (answer < temp) {
					answer = temp;
				}
			}
		}
		return answer;
	}

	public boolean isInOneLine(Point p1, Point p2, Point p3) {
		if (p1.x == p2.x) {
			return p3.x == p1.x;
		}
		if (p1.y == p2.y) {
			return p3.y == p1.y;
		}
		if (p3.x == p1.x && p3.y == p1.y) {
			return true;
		}
		double tan = ((double) (p1.y - p2.y)) / ((double) (p1.x - p2.x));
		double temp = ((double) (p1.y - p3.y)) / ((double) (p1.x - p3.x));
		return tan == temp;
	}

	@Test
	public void test() {
		char[] chs = { 'a', 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b',
				'b', 'b', 'b' };
		compress(chs);
	}
}
