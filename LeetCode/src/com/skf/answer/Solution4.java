package com.skf.answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

public class Solution4 {
	/**
	 * 343. Integer Break Given a positive integer n, break it into the sum of
	 * at least two positive integers and maximize the product of those
	 * integers. Return the maximum product you can get.
	 * 
	 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36
	 * (10 = 3 + 3 + 4).
	 * 
	 * Note: You may assume that n is not less than 2 and not larger than 58.
	 * 
	 * @param n
	 * @return
	 */
	public int integerBreak(int n) {
		int[] dp = new int[n + 1];
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				dp[i] = Math.max(dp[i],
						(Math.max(j, dp[j]) * Math.max(i - j, dp[i - j])));
			}
		}
		return dp[n];
	}

	/**
	 * 93. Restore IP Addresses Given a string containing only digits, restore
	 * it by returning all possible valid IP address combinations.
	 * 
	 * For example: Given "25525511135",
	 * 
	 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
	 * 
	 * @param s
	 * @return
	 */
	public List<String> restoreIpAddresses(String s) {
		List<String> answer = new ArrayList<String>();
		restoreIpAddresses(s, answer, 0, "", 0);
		return answer;

	}

	private void restoreIpAddresses(String s, List<String> answer, int idx,
			String restored, int count) {
		if (count > 4)
			return;
		if (count == 4 && idx == s.length()) {
			answer.add(restored);
		}
		for (int i = 1; i < 4; i++) {
			if (idx + i > s.length())
				break;
			String ip = s.substring(idx, idx + i);
			if ((ip.length() != 1 && ip.startsWith("0"))
					|| (ip.length() == 3 && Integer.parseInt(ip) >= 256)) {
				continue;
			}
			restoreIpAddresses(s, answer, idx + i, restored + ip
					+ (count == 3 ? "" : "."), count + 1);
		}
	}

	/**
	 * 87. Scramble String Given a string s1, we may represent it as a binary
	 * tree by partitioning it to two non-empty substrings recursively.
	 * 
	 * Below is one possible representation of s1 = "great":
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean isScramble(String s1, String s2) {
		if (s1.equals(s2)) {
			return true;
		}
		int[] letters = new int[26];
		for (int i = 0; i < s1.length(); i++) {
			letters[s1.charAt(i) - 'a']++;
			letters[s2.charAt(i) - 'a']--;
		}
		for (int i = 0; i < 26; i++) {
			if (letters[i] != 0) {
				return false;
			}
		}
		for (int i = 1; i < s1.length(); i++) {
			if (isScramble(s1.substring(0, i), s2.substring(0, i))
					&& isScramble(s1.substring(i), s2.substring(i))) {
				return true;
			}
			if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i))
					&& isScramble(s1.substring(i),
							s2.substring(0, s2.length() - i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 319. Bulb Switcher There are n bulbs that are initially off. You first
	 * turn on all the bulbs. Then, you turn off every second bulb. On the third
	 * round, you toggle every third bulb (turning on if it's off or turning off
	 * if it's on). For the ith round, you toggle every i bulb. For the nth
	 * round, you only toggle the last bulb. Find how many bulbs are on after n
	 * rounds.
	 * 
	 * @param n
	 * @return
	 */
	public static int bulbSwitch(int n) {
		// boolean[] bulbs = new boolean[n + 1];
		// int answer = 0;
		// for (int i = 0; i < bulbs.length; i++) {
		// bulbs[i] = false;
		// }
		// // if (n == 1) {
		// // return n;
		// // }
		// for (int i = 1; i <= n; i++) {
		// int j = i;
		// while (j < n + 1) {
		// bulbs[j] = !bulbs[j];
		// j = j + i;
		// }
		// }
		// // n = 0;
		// for (int i = 1; i < n + 1; i++) {
		// if (bulbs[i]) {
		// answer++;
		// }
		// }
		return (int) Math.sqrt(n);
	}

	/**
	 * 147. Insertion Sort List Sort a linked list using insertion sort.
	 * 
	 * @param head
	 * @return
	 */
	public ListNode insertionSortList(ListNode head) {
		// ListNode pre = head;
		// if (head != null) {
		// while (pre.next != null) {
		// if (pre.next.val >= pre.val) {
		// pre = pre.next;
		// } else {
		// ListNode temp = pre.next;
		// if (temp.val < head.val) {
		// pre.next = temp.next;
		// temp.next = head;
		// head = temp;
		// } else {
		// ListNode tempHead = head;
		// while ((tempHead.next != null)
		// && (temp.val >= tempHead.next.val)) {
		// tempHead = tempHead.next;
		// }
		// tempHead.next = temp;
		// temp.next = tempHead.next;
		// }
		// }
		// }
		// }
		// return head;
		if (head == null) {
			return head;
		}

		ListNode helper = new ListNode(0);
		ListNode cur = head;
		ListNode pre = helper;
		ListNode next = null;
		while (cur != null) {
			next = cur.next;
			while (pre.next != null && pre.next.val < cur.val) {
				pre = pre.next;
			}
			cur.next = pre.next;
			pre.next = cur;
			pre = helper;
			cur = next;
		}
		return helper.next;
	}

	/**
	 * 453. Minimum Moves to Equal Array Elements Given a non-empty integer
	 * array of size n, find the minimum number of moves required to make all
	 * array elements equal, where a move is incrementing n - 1 elements by 1.
	 * 
	 * @param nums
	 * @return
	 */
	public int minMoves(int[] nums) {
		int res = 0;
		int len = nums.length;
		Arrays.sort(nums);
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		return sum - nums[0] * len;
	}

	/**
	 * 207. Course Schedule
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[][] matrix = new int[numCourses][numCourses];
		int[] indegree = new int[numCourses];

		for (int i = 0; i < prerequisites.length; i++) {
			int ready = prerequisites[i][0];
			int pre = prerequisites[i][1];
			if (matrix[pre][ready] == 0) {
				indegree[ready]++;
			}
			matrix[pre][ready] = 1;
		}

		int count = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}

		while (!queue.isEmpty()) {
			int course = queue.poll();
			count++;
			for (int i = 0; i < numCourses; i++) {
				if (matrix[course][i] != 0) {
					if (--indegree[i] == 0) {
						queue.offer(i);
					}
				}
			}
		}
		return count == numCourses;
	}

	@Test
	public void test() {
		// String string = "123";
		// System.out.println(string.substring(0, 1));
		int[] nums = { 3, 2, 4, 1 };
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
	}
}
