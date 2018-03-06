package com.skf.shuzu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Test {
	public static int maxSubArray(int arr[]) {
		int n = arr.length;
		int ThisSum = 0, MaxSum = 0, i, j, k;
		for (i = 0; i < n; i++) {
			for (j = i; j < n; j++) {
				ThisSum = 0;
				for (k = i; k < j; k++) {
					ThisSum += arr[k];
					if (ThisSum > MaxSum) {
						MaxSum = ThisSum;
					}
				}
			}
		}
		return MaxSum;
	}

	public static int maxSubArray2(int arr[]) {
		int len = arr.length;
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			int sum = 0;
			for (int j = i; j < len; j++) {
				sum += arr[j];
				if (sum > maxSum) {
					maxSum = sum;
				}
			}
		}
		return maxSum;
	}

	public static int max(int m, int n) {
		return m > n ? m : n;
	}

	public static int maxSubArray3(int arr[]) {
		int n = arr.length;
		int End[] = new int[n];
		int All[] = new int[n];
		// End[n - 1] = arr[n - 1];
		// All[n - 1] = arr[n - 1];
		End[0] = All[0] = arr[0];
		for (int i = 1; i < n; i++) {
			End[i] = max(End[i - 1] + arr[i], arr[i]);
			All[i] = max(End[i], All[i - 1]);
		}
		return All[n - 1];
	}

	public static int maxSunArray3(int arr[]) {
		int n = arr.length;
		int nAll = arr[0];
		int nEnd = arr[0];
		for (int i = 1; i < n; i++) {
			nEnd = max(nEnd + arr[i], arr[i]);
			nAll = max(nEnd, nAll);
		}
		return nAll;
	}

	private static int begin = 0;
	private static int end = 0;

	public static int maxSubArray4(int arr[]) {
		int maxSum = Integer.MIN_VALUE;
		int nSum = 0;
		int nStart = 0;
		for (int i = 0; i < arr.length; i++) {
			if (nSum < 0) {
				nSum = arr[i];
				nStart = i;
			} else {
				nSum += arr[i];
			}
			if (nSum > maxSum) {
				maxSum = nSum;
				begin = nStart;
				end = i;
			}
		}
		return maxSum;
	}

	public static int findMostFrequentInArray(int[] a) {
		int result = 0;
		int size = a.length;
		if (size == 0) {
			return Integer.MAX_VALUE;

		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < size; i++) {
			if (map.containsKey(a[i])) {
				map.put(a[i], map.get(a[i]) + 1);
			} else {
				map.put(a[i], 1);
			}
		}
		int most = 0;
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Entry) iterator.next();
			int key = (int) entry.getKey();
			int value = (int) entry.getValue();
			if (value > most) {
				result = key;
				most = value;
			}
		}
		return result;
	}

	public static void findSum(int[] a, int sum) {
		int len = a.length;
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len; j++) {
				if (a[i] + a[j] == sum) {
					System.out.println(a[i] + "," + a[j]);
				}
			}
		}
	}

	public static void findSum1(int[] a, int sum) {
		Arrays.sort(a);
		int begin = 0;
		int end = a.length - 1;
		while (begin < end) {
			if (a[begin] + a[end] < sum) {
				begin++;
			} else if (a[begin] + a[end] > sum) {
				end--;

			} else {
				System.out.println(a[begin] + "," + a[end]);
				begin++;
				end--;
			}
		}
	}

	public static void reverse(int a[], int b, int e) {
		for (; b < e; b++, e--) {
			int temp = a[e];
			a[e] = a[b];
			a[b] = temp;
		}
	}

	public static void shift_k(int[] a, int k) {
		int n = a.length;
		k = k % n;
		reverse(a, n - k, n - 1);
		reverse(a, 0, n - k - 1);
		reverse(a, 0, n - 1);
	}

	public static int quickSort(int array[], int low, int high, int k) {
		int i, j;
		int tmp;
		if (low > high) {
			return Integer.MIN_VALUE;
		}
		i = low + 1;
		j = high;

		tmp = array[i];
		while (i < j) {
			while (i < j && array[j] >= tmp) {
				j--;
			}
			if (i < j) {
				array[i++] = array[j];
			}
			while (i < j && array[i] < tmp) {
				i++;
			}
			if (i < j) {
				array[j--] = array[i];
			}
		}
		array[i] = tmp;
		if (i + 1 == k) {
			return tmp;
		} else if (i + 1 > k) {
			return quickSort(array, low, i - 1, k);

		} else {
			return quickSort(array, i + 1, high, k);
		}
	}

	public static int getKMin(int array[], int k) {
		if (array == null) {
			return Integer.MIN_VALUE;
		}
		if (array.length < k) {
			return Integer.MIN_VALUE;
		}
		return quickSort(array, 0, array.length - 1, k);
	}
}
