package com.skf.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test3 {
	public static void CombineRecursiveImpl(char[] c, int begin, int len,
			StringBuffer sb) {
		if (len == 0) {
			System.out.println(sb + " ");
			return;
		}
		if (begin == c.length) {
			return;
		}
		sb.append(c[begin]);
		CombineRecursiveImpl(c, begin + 1, len - 1, sb);
		sb.deleteCharAt(sb.length() - 1);
		CombineRecursiveImpl(c, begin + 1, len, sb);
	}

	public static void Combine(char[] c) {
		if (c == null) {
			return;
		}
		int n = c.length;
		boolean used[] = new boolean[n];
		char[] cache = new char[n];
		int result = n;
		while (true) {
			int index = 0;
			while (used[index]) {
				used[index] = false;
				++result;
				if (++index == n) {
					return;
				}
			}
			used[index] = true;
			cache[--result] = c[index];

		}
	}

	/**
	 * 315. Count of Smaller Numbers After Self
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> countSmaller(int[] nums) {
		Integer[] answer = new Integer[nums.length];
		List<Integer> sortedIntegers = new ArrayList<Integer>();
		for (int i = nums.length - 1; i >= 0; i--) {
			int index = findIndex(sortedIntegers, nums[i]);
			answer[i] = index;
			sortedIntegers.add(index, nums[i]);
		}
		return Arrays.asList(answer);
	}

	private int findIndex(List<Integer> sorted, int target) {
		if (sorted.size() == 0)
			return 0;
		int start = 0;
		int end = sorted.size() - 1;
		if (sorted.get(end) < target) {
			return end + 1;
		}
		if (sorted.get(start) > target) {
			return 0;
		}
		while (start + 1 < end) {
			int mid = (start + end) / 2;
			if (sorted.get(mid) < target) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		if (sorted.get(start) >= target)
			return start;
		return end;
	}
}
