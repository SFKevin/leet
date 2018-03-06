package test;

import java.util.ArrayList;

import org.junit.Test;

public class Test2 {
	public static void findRightPlaceForMin(int[] a, int mid) {
		int len = a.length;
		for (int i = 0; i < mid; i++) {
			if (a[i] > a[mid]) {
				int temp = a[mid];
				for (int j = mid + 1; j < len; j++) {
					if (a[i] > a[j]) {
						a[j - 1] = a[j];
					} else {
						a[j - 1] = a[i];
						a[i] = temp;
						break;
					}
				}
			}
		}
	}

	public static ArrayList<Integer> mixed(int[] array1, int[] array2) {
		int n1 = array1.length;
		int n2 = array2.length;
		ArrayList<Integer> answer = new ArrayList<Integer>();
		if (n1 == 0 || n2 == 0) {
			return answer;
		}
		int i = 0;
		int j = 0;
		while (i < n1 && j < n2) {
			if (array1[i] == array2[j]) {
				answer.add(array1[i]);
				i++;
				j++;
			} else if (array1[i] < array2[j]) {
				i++;
			} else {
				j++;
			}
		}
		return answer;
	}

	public static Boolean isContinuous(int[] a) {
		int n = a.length;
		int min = -1;
		int max = -1;
		for (int i = 0; i < n; i++) {
			if (a[i] != 0) {
				if (min > a[i] || -1 == min) {
					min = a[i];
				}
				if (max < a[i] || -1 == max) {
					max = a[i];
				}
			}
		}
		return (max - min <= 4);
	}

	public static int max(int a, int b, int c) {
		int max = a < b ? b : a;
		max = max < c ? c : max;
		return max;
	}

	public static int min(int a, int b, int c) {
		int min = a > b ? b : a;
		min = min > c ? c : min;
		return min;
	}

	public static int solving(int[] a, int[] b, int[] c) {
		int len_a = a.length;
		int len_b = b.length;
		int len_c = c.length;
		int minDist = Integer.MAX_VALUE;
		for (int i = 0; i < len_a; i++) {
			for (int j = 0; j < len_b; j++) {
				for (int k = 0; k < len_c; k++) {
					int dist = max(Math.abs(a[i] - b[j]),
							Math.abs(a[i] - c[k]), Math.abs(b[j] - c[k]));
					if (minDist > dist) {
						minDist = dist;
					}
				}
			}
		}
		return minDist;
	}

	public static int minDistance(int[] a, int[] b, int[] c) {
		int alen = a.length;
		int blen = b.length;
		int clem = c.length;
		int curDist = 0;
		int min = 0;
		int minDist = Integer.MIN_VALUE;
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
				if (++i >= alen) {
					break;
				}
			} else if (min == b[j]) {
				if (++j >= blen) {
					break;
				}
			} else {
				if (++k > clem) {
					break;
				}
			}
		}
		return minDist;
	}

	@Test
	public void test() {
		int[] a = { 1, 5, 6, 7, 9, 2, 4, 8, 10, 13, 14 };
		findRightPlaceForMin(a, 5);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
}
