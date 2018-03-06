package test;

import java.util.Arrays;

import org.junit.Test;

public class CopyOfCopyOfTest3 {
	public void swap(char[] cArr, int front, int end) {
		while (front < end) {
			char temp = cArr[front];
			cArr[front] = cArr[end];
			cArr[end] = temp;
			front++;
			end--;
		}
	}

	public String swapWords(String s) {
		char[] cArr = s.toCharArray();
		swap(cArr, 0, cArr.length - 1);
		int begin = 0;
		for (int i = 1; i < cArr.length; i++) {
			if (cArr[i] == ' ') {
				swap(cArr, begin, i - 1);
				begin = i + 1;
			}
		}
		swap(cArr, begin, cArr.length - 1);
		return new String(cArr);
	}

	public static void compare(String s1, String s2) {
		byte[] b1 = s1.getBytes();
		byte[] b2 = s2.getBytes();
		Arrays.sort(b1);
		Arrays.sort(b2);
		s1 = new String(b1);
		s2 = new String(b2);
	}

	public static void compare1(String s1, String s2) {
		byte[] b1 = s1.getBytes();
		byte[] b2 = s2.getBytes();
		int[] bCount = new int[256];
		for (int i = 0; i < 256; i++) {
			bCount[i] = 0;
		}
		for (int i = 0; i < b1.length; i++) {
			bCount[b1[i] - '0']++;
		}
		for (int i = 0; i < b2.length; i++) {
			bCount[b2[i] - '0']--;
		}
		for (int i = 0; i < 256; i++) {
			if (bCount[i] != 0) {
				return;
			}
		}
	}

	public static String removeDup(String str) {
		char[] c = str.toCharArray();
		int len = c.length;
		for (int i = 0; i < len; i++) {
			if (c[i] == '\0') {
				continue;
			}
			for (int j = i + 1; j < len; j++) {
				if (c[j] == '\0') {
					continue;
				}
				if (c[j] == c[i]) {
					c[j] = '\0';
				}
			}
		}
		int l = 0;
		for (int i = 0; i < len; i++) {
			if (c[i] != '\0') {
				c[l++] = c[i];
			}
		}
		return new String(c);
	}

	public static int wordCount(String s) {
		int word = 0;
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				word = 0;
			} else {
				if (word == 0) {
					word = 1;
					count++;
				}
			}
		}
		return count;
	}

	public void backtrack(char[] c, int begin, StringBuffer sb) {
		if (begin >= c.length) {
			System.out.println(sb);
			return;
		}
		for (int i = 0; i < c.length; i++) {
			if (sb.toString().indexOf(c[i]) != -1) {
				continue;
			}
			sb.append(c[i]);
			backtrack(c, begin + 1, sb);
			sb.deleteCharAt(sb.length() - 1);
		}

	}

	@Test
	public void test() {
		char[] c = { 'a', 'b', 'c' };
		for (int i = 0; i <= c.length; i++) {
			backtrack(c, i, new StringBuffer());
		}
	}

	public static void combineRe(char[] c, int begin, int len, StringBuffer sb) {
		if (len == 0) {
			System.out.println(sb + " ");
			return;
		}
		if (begin == c.length) {
			return;
		}
		sb.append(c[begin]);
		combineRe(c, begin + 1, len - 1, sb);
		sb.deleteCharAt(sb.length() - 1);
		combineRe(c, begin + 1, len, sb);
	}

	public static void merge(int[] array, int p, int q, int r) {
		int i;
		int j;
		int k;
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] L = new int[n1];
		int[] R = new int[n2];
		for (i = 0, k = p; i < n1; i++, k++) {
			L[i] = array[k];
		}
		for (i = 0, k = q + 1; i < n2; i++, k++) {
			R[i] = array[k];
		}
		for (k = p, i = 0, j = 0; i < n1 && j < n2; k++) {
			if (L[i] > R[j]) {
				array[k] = L[i];
				i++;
			} else {
				array[k] = R[j];
				j++;
			}
		}
		if (i < n1) {
			for (j = i; j < n1; j++, k++) {
				array[k] = L[j];
			}
		}
		if (j < n2) {
			for (i = j; i < n2; i++, k++) {
				array[k] = R[i];
			}
		}

	}

	public static void mergeSort(int[] array, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(array, p, q);
			mergeSort(array, q + 1, r);
			merge(array, p, q, r);
		}
	}

	public static void shellSort(int[] array) {
		int length = array.length;
		int i;
		int j;
		int h;
		int temp;
		for (h = length / 2; h > 0; h = h / 2) {
			for (i = h; i < length; i++) {
				temp = array[i];
				for (j = i - h; j >= 0; j -= h) {
					if (temp < array[j]) {
						array[j + h] = array[j];
					} else {
						break;
					}
				}
				array[j + h] = temp;
			}
		}
	}

	public static void adjustHeap(int[] arr, int i, int length) {
		int temp = arr[i];
		for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
			if (k + 1 < length && arr[k] < arr[k + 1]) {
				k++;
			}
			if (arr[k] > temp) {
				arr[i] = arr[k];
				i = k;
			} else {
				break;
			}
		}
		arr[i] = temp;
	}

	public static void sort(int[] arr) {
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}
		for (int j = arr.length - 1; j > 0; j--) {

		}
	}
}
