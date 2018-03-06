package com.skf.shuzu;

import java.util.ArrayList;

public class test2 {
	public static int getMinAbsoluteValue(int[] a) {
		if (a == null) {
			return Integer.MIN_VALUE;
		}
		int len = a.length;
		if (len < 1) {
			return Integer.MIN_VALUE;
		}
		if (a[0] > 0) {
			return a[0];
		}
		if (a[len - 1] <= 0) {
			return a[len - 1];
		}
		int mid = 0;
		int begin = 0;
		int end = len - 1;
		while (true) {
			mid = begin + (end - begin) / 2;
			if (a[mid] == 0) {
				return 0;
			} else if (a[mid] > 0) {
				if (a[mid - 1] > 0) {
					end = mid - 1;
				} else if (a[mid - 1] == 0) {
					return 0;
				} else {
					break;
				}
			} else {
				if (a[mid + 1] < 0) {
					begin = mid + 1;
				} else if (a[mid + 1] == 0) {
					return 0;
				} else {
					break;
				}
			}
		}
		if (a[mid] > 0) {
			if (a[mid] < Math.abs(a[mid - 1])) {
				return a[mid];
			} else {
				return Math.abs(a[mid - 1]);
			}
		} else {
			if (Math.abs(a[mid]) < a[mid + 1]) {
				return Math.abs(a[mid]);
			} else {
				return a[mid + 1];
			}
		}
	}

	public static int min(int a, int b) {
		return a > b ? b : a;
	}

	public static int minDistance(int[] a, int n1, int n2) {
		if (a == null) {
			return Integer.MAX_VALUE;
		}
		int len = a.length;
		int n1_index = -1;
		int n2_index = -1;
		int min_dist = Integer.MAX_VALUE;
		for (int i = 0; i < len; i++) {
			if (a[i] == n1) {
				n1_index = i;
				if (n2_index >= 0) {
					min_dist = min(Math.abs(n1 - n2), min_dist);
				}
			}
			if (a[i] == n2) {
				n2_index = i;
				if (n1_index >= 0) {
					min_dist = min(Math.abs(n2 - n1), min_dist);
				}
			}
		}
		return min_dist;
	}

	public static int findIndex(int[] a, int t) {
		if (a == null) {
			return -1;
		}
		int len = a.length;
		int i = 0;
		while (i < len) {
			if (a[i] == t) {
				return i;
			} else {
				i += Math.abs(t - a[i]);
			}
		}
		return -1;
	}

	public static void findRightPlaceForMid(int[] a, int mid) {
		int len = a.length;
		int tmp;
		for (int i = mid; i < len - 1; i++) {
			if (a[i + 1] < a[i]) {
				tmp = a[i];
				a[i] = a[i + 1];
				a[i + 1] = a[i];
			}
		}
	}

	public static void sort(int[] a, int mid) {
		int tmp;
		for (int i = 0; i < mid; i++) {
			if (a[mid] < a[i]) {
				tmp = a[i];
				a[i] = a[mid];
				a[mid] = tmp;
				findRightPlaceForMid(a, mid);
			}
		}
	}

	public static ArrayList<Integer> mixed(int array1[], int array2[]) {
		ArrayList<Integer> mix = new ArrayList<Integer>();
		int i = 0;
		int j = 0;
		int n1 = array1.length;
		int n2 = array2.length;
		while (i < n1 && j < n2) {
			if (array1[i] == array2[j]) {
				mix.add(array1[i]);
				i++;
				j++;
			} else if (array1[i] > array2[j]) {
				j++;
			} else {
				i++;
			}
		}
		return mix;
	}

	public static Boolean isContinuous(int[] a) {
		int n = a.length;
		int min = -1;
		int max = -1;
		for (int i = 0; i < n; i++) {
			if (a[i] != 0) {
				if (min > a[i] || min == -1) {
					min = a[i];
				}
				if (max < a[i] || max == -1) {
					max = a[i];
				}
			}
		}
		if (max - min < 5) {
			return true;
		} else {
			return false;
		}
	}
}
