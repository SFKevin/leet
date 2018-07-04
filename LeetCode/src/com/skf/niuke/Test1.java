package com.skf.niuke;

import java.util.Arrays;
import java.util.Set;

public class Test1 {
	public boolean wordBreak(String s, Set<String> dict) {
		int len = s.length();
		if (len <= 0) {
			return false;
		}
		boolean[] flags = new boolean[len + 1];
		flags[0] = true;
		for (int i = 1; i < len + 1; i++) {
			flags[i] = false;
		}
		for (int i = 1; i <= len; i++) {
			for (int j = 0; j < i; j++) {
				if (flags[j] && dict.contains(s.substring(j, i))) {
					flags[i] = true;
				}
			}
		}
		return flags[len];
	}

	public RandomListNode copyRandomList(RandomListNode head) {
		RandomListNode lhead = head;
		RandomListNode next = null;
		if (head == null) {
			return null;
		}
		while (lhead != null) {
			next = lhead.next;
			RandomListNode temp = new RandomListNode(lhead.label);
			temp.next = lhead.next;
			lhead.next = temp;
			lhead = next;
		}
		lhead = head;
		while (lhead != null && lhead.next != null) {
			if (lhead.random != null) {
				lhead.next.random = lhead.random.next;
			}
			lhead = lhead.next.next;
		}
		lhead = head;
		RandomListNode res = head.next;
		while (lhead.next != null) {
			RandomListNode tmp = lhead.next;
			lhead.next = lhead.next.next;
			lhead = tmp;
		}
		return res;
	}

	public static void merge(int[] a, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - p;
		int[] L = new int[n1];
		int[] R = new int[n2];
		int i, j, k;
		for (i = 0, k = p; i < n1; i++, k++) {
			L[i] = a[k];
		}
		for (j = 0, k = q + 1; j < n2; j++, k++) {
			R[j] = a[k];
		}
		for (i = 0, j = 0, k = p; i < n1 && j < n2; k++) {
			if (L[i] < R[j]) {
				a[k] = L[i];
				i++;
			} else {
				a[k] = R[j];
				j++;
			}
		}
		while (i < n1) {
			a[k++] = L[i++];
		}
		while (j < n2) {
			a[k++] = R[j++];
		}
	}

	public void mergeSort(int[] a, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(a, p, q);
			mergeSort(a, q + 1, r);
			merge(a, p, q, r);
		}
	}

	public static void insertSort(int[] a) {
		int len = a.length;
		int flag;
		int temp;
		for (int i = 0; i < len; i++) {
			flag = i;
			temp = a[i];
			for (int j = i + 1; j < len; j++) {
				if (temp > a[j]) {
					flag = j;
					temp = a[j];
				}
			}
			if (flag != i) {
				a[flag] = a[i];
				a[i] = temp;
			}
		}
	}

	public static void bubbleSort(int[] a) {
		int len = a.length;
		int temp;
		for (int i = 0; i < len; i++) {
			for (int j = len - 1; j > i; j--) {
				if (a[j] < a[j - 1]) {
					temp = a[j - 1];
					a[j - 1] = a[j];
					a[j] = temp;
				}
			}
		}

	}

	public int singleNumber(int[] A) {
		int result = 0;
		int len = A.length;
		for (int i = 0; i < 32; i++) {
			int bits = 0;
			for (int j = 0; j < len; j++) {
				bits += (A[j] >> i) & 1;
			}
			result |= (bits % 3) << i;
		}
		return result;
	}

	public int candy(int[] ratings) {
		int len = ratings.length;
		if (len <= 0) {
			return 0;
		}
		int[] num = new int[len];
		Arrays.fill(num, 1);
		for (int i = 1; i < len; i++) {
			if (ratings[i - 1] < ratings[i]) {
				num[i] = num[i - 1] + 1;
			}
		}
		int sum = 0;
		for (int i = len - 1; i > 0; i--) {
			sum += num[i];
			if (ratings[i] < ratings[i - 1] && num[i - 1] <= num[i]) {
				num[i - 1] = num[i] + 1;
			}
		}
		sum += num[0];
		return sum;
	}

	public int canCompleteCircuit(int[] gas, int[] cost) {
		int len = gas.length;
		int start = len - 1;
		int end = 0;
		int sum = gas[start] - cost[start];
		while (start > end) {
			if (sum >= 0) {
				sum += gas[end] - cost[end];
				end++;
			} else {
				start--;
				sum += gas[start] - cost[start];
			}
		}
		return sum >= 0 ? start : -1;
	}
}
