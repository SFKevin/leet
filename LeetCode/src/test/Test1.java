package test;

import java.util.Arrays;

import org.junit.Test;

public class Test1 {
	public static boolean isPower(int n) {
		if (n < 1) {
			return false;
		}
		int i = 1;
		while (i <= n) {
			if (i == n) {
				return true;
			}
			// i=i<<1; i+=1

			i <<= 1;
		}
		return false;
	}

	public static boolean isPower1(int n) {
		if (n < 1) {
			return false;
		}
		int m = n & (n - 1);
		return (m == 0);
	}

	public static int countOne(int n) {
		int count = 0;
		while (n > 0) {
			if ((n & 1) == 1) {
				count += 1;
			}
			n >>= 1;
		}
		return count;
	}

	public static void getMaxAndMin(int[] arr) {
		int len = arr.length;
		if (len < 1) {
			return;
		}
		int max = arr[0];
		int min = arr[0];
		for (int i = 1; i < len - 1; i++) {
			if (arr[i] >= arr[i + 1]) {
				if (arr[i] > max) {
					max = arr[i];
				}
				if (arr[i + 1] < min) {
					min = arr[i + 1];
				}
			} else {
				if (arr[i + 1] > max) {
					max = arr[i + 1];
				}
				if (arr[i] < min) {
					min = arr[i];
				}
			}
		}
	}

	public static int findSecMax(int data[]) {
		int count = data.length;
		if (count < 1) {
			return (Integer) null;
		}
		int max = data[0];
		int secmax = Integer.MIN_VALUE;
		for (int i = 1; i < count; i++) {
			if (data[i] > max) {
				max = data[i];
				secmax = max;
			} else {
				if (data[i] > secmax) {
					secmax = data[i];
				}
			}
		}
		return secmax;
	}

	public static int maxSubArray(int arr[]) {
		int n = arr.length;
		int thisSum = 0;
		int maxSum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				for (int k = i; k <= j; k++) {
					thisSum += arr[k];
				}
				if (thisSum > maxSum) {
					maxSum = thisSum;
				}
			}
		}
		return maxSum;
	}

	public static int maxSub(int arr[]) {
		int n = arr.length;
		int[] end = new int[n];
		int[] all = new int[n];
		end[0] = all[0] = arr[0];
		for (int i = 1; i < n; i++) {
			end[i] = Math.max(end[i - 1] + arr[i], arr[i]);
			all[i] = Math.max(end[i], all[i - 1]);
		}
		return all[n - 1];
	}

	public void findSum(int[] a, int sum) {
		int len = a.length;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (a[i] + a[j] == 20) {
					System.out.println(a[i] + ',' + a[j]);
				}
			}
		}
	}

	public void fingSum1(int[] a, int sum) {
		Arrays.sort(a);
		int begin = 0;
		int end = a.length - 1;
		while (begin < end) {
			if (a[begin] + a[end] > 20) {
				end--;
			} else if (a[begin] + a[end] < 20) {
				begin++;
			} else {
				System.err.println(a[begin] + ',' + a[end]);
				begin++;
				end--;
			}
		}
	}

	public static void reverse(int a[], int b, int e) {
		int temp;
		for (; b < e; b++, e--) {
			temp = a[b];
			a[b] = a[e];
			a[e] = temp;
		}
	}

	public void shift_K(int a[], int k) {
		int n = a.length;
		k = k % n;
		reverse(a, 0, n - k - 1);
		reverse(a, n - k, n - 1);
		reverse(a, 0, n - 1);
	}

	public static void sort(int array[], int low, int high) {
		int i;
		int j;
		int index;
		i = low;
		j = high;
		index = array[i];
		while (i < j) {
			while (i < j && array[j] >= index) {
				j--;
			}
			if (i < j) {
				array[i++] = array[j];
			}
			while (i < j && array[i] < index) {
				i++;
			}
			if (i < j) {
				array[j--] = array[i];
			}
		}
		array[i] = index;
		sort(array, low, i - 1);
		sort(array, i + 1, high);
	}

	public static int findNotDouble(int a[]) {
		int n = a.length;
		int result = a[0];
		for (int i = 1; i < n; i++) {
			result ^= a[i];
		}
		return result;
	}

	public int findOnce(int[] a, int appearTimes) {
		int n = a.length;
		int[] bitCount = new int[32];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 32; j++) {
				bitCount[j] += ((a[i] >> j) & 1);
			}
		}
		int appearOnce = 0;
		for (int i = 0; i < 32; i++) {
			if (bitCount[i] % appearOnce != 0) {
				appearOnce += (1 << i);
			}
		}
		return appearOnce;
	}

	public int xor_findDup(int[] a) {
		int n = a.length;
		int temp1 = 0;
		int temp2 = 0;
		for (int i = 0; i < n - 1; i++) {
			temp1 += (i + 1);
			temp2 += a[i];
		}
		temp2 += a[n - 1];
		return (temp2 - temp1);
	}

	public int maxnum(int a[], int begin) {
		int len = a.length - begin;
		if (len == 1) {
			return a[begin];
		} else {
			return Math.max(a[begin], maxnum(a, begin + 1));
		}

	}

	public static int getMax(int[] a) {
		if (a == null) {
			return Integer.MIN_VALUE;
		}
		int len = a.length;
		int[] diff = new int[len];
		int[] max = new int[len];
		diff[0] = 0;
		max[0] = a[0];
		for (int i = 1; i < len; i++) {
			diff[i] = Math.max(diff[i - 1], max[i - 1] - a[i]);
			max[i] = Math.max(max[i - 1], a[i]);
		}
		return diff[len - 1];
	}

	public static int getMinABSValue(int[] a) {
		if (a == null) {
			return Integer.MIN_VALUE;
		}
		int len = a.length;
		if (len < 1) {
			return Integer.MIN_VALUE;
		}
		if (a[0] >= 0) {
			return a[0];
		}
		if (a[len - 1] < 0) {
			return a[len - 1];
		}
		int mid = 0;
		int begin = 0;
		int end = len - 1;
		int absMin = 0;
		while (true) {
			mid = (begin + end) / 2;
			if (a[mid] == 0) {
				return a[mid];
			} else if (a[mid] > 0) {
				if (a[mid - 1] > 0) {
					end = mid - 1;
				} else if (a[mid - 1] == 0) {
					return a[mid - 1];
				} else {
					return Math.min(Math.abs(a[mid - 1]), a[mid]);
				}
			} else {
				if (a[mid + 1] < 0) {
					begin = mid + 1;
				} else if (a[mid + 1] == 0) {
					return a[mid + 1];
				} else {
					return Math.min(Math.abs(a[mid]), a[mid + 1]);
				}
			}
		}
	}

	public static int minDistance(int[] a, int n1, int n2) {
		if (a == null) {
			return Integer.MAX_VALUE;
		}
		int len = a.length;
		int minValue = Integer.MAX_VALUE;
		int n1_index = -1;
		int n2_index = -1;
		for (int i = 0; i < len; i++) {
			if (a[i] == n1) {
				if (n1_index == -1) {
					n1_index = i;
				} else {
					if (n2_index == -1) {
						n1_index = i;
					} else {
						if (Math.abs(n2_index - n1_index) < minValue) {
							minValue = Math.abs(n2_index - n1_index);
							n1_index = i;
						}
					}
				}
			} else if (a[i] == n2) {
				if (n2_index == -1) {
					n2_index = i;
				} else {
					if (n1_index == -1) {
						n2_index = i;
					} else {
						if (Math.abs(n2_index - n1_index) < minValue) {
							minValue = Math.abs(n2_index - n1_index);
							n2_index = i;
						}
					}
				}
			}
		}
		return minValue;
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
				i = i + Math.abs(t - a[i]);
			}
		}
		return -1;
	}

	public static void insertSort(int[] a) {
		if (a == null) {
			return;
		}
		int len = a.length;
		for (int i = 1; i < len; i++) {
			int temp = a[i];
			int j = i - 1;
			while ((j >= 0) && (temp < a[j])) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = temp;
		}
	}

	@Test
	public void test2() {
		int[] a = { 4, 5, 6, 4, 7, 4, 6, 4, 7, 8, 5, 6, 4, 3, 10, 8 };
		insertSort(a);
		for (int i = 0; i < a.length; i++) {
			System.err.println(a[i]);
		}
	}
}
