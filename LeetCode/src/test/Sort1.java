package test;

import org.junit.Test;

public class Sort1 {
	@Test
	public void test() {
		int[] a = { 13, 65, 97, 76, 38, 27, 49 };
		heapSort(a);
		for (int i : a) {
			System.out.println(i);
		}
	}

	public static void selectSort(int[] a) {
		int len = a.length;
		int temp;
		int flag;
		for (int i = 0; i < len; i++) {
			temp = a[i];
			flag = i;
			for (int j = i + 1; j < len; j++) {
				if (temp > a[j]) {
					temp = a[j];
					flag = j;
				}
			}
			if (flag != i) {
				a[flag] = a[i];
				a[i] = temp;
			}
		}
	}

	public static void insertSort(int[] a) {
		int len = a.length;
		if (len <= 1) {
			return;
		}
		int temp;
		for (int i = 1; i < len; i++) {
			int j = i;
			temp = a[i];
			while (j >= 1 && a[j - 1] > temp) {
				a[j] = a[j - 1];
				j--;
			}
			a[j] = temp;
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

	public static void merge(int[] a, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
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
				a[k] = L[i++];
			} else {
				a[k] = R[j++];
			}
		}
		while (i < n1) {
			a[k++] = L[i++];
		}
		while (j < n2) {
			a[k++] = R[j++];
		}
	}

	public static void mergeSort(int[] a, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(a, p, q);
			mergeSort(a, q + 1, r);
			merge(a, p, q, r);
		}
	}

	public static void sort(int[] array, int low, int high) {
		if (low >= high) {
			return;
		}
		int i, j;
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
			while (i < j && array[i] <= index) {
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

	public static void sort1(int[] array, int low, int high) {
		if (low >= high) {
			return;
		}
		int i = low;
		int j = high;
		int index = array[i];
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
		sort1(array, low, i - 1);
		sort1(array, i + 1, high);
	}

	public static void shellSort(int[] array) {
		int len = array.length;
		int i, j;
		int h;
		int temp;
		for (h = len / 2; h > 0; h /= 2) {
			for (i = h; i < len; i++) {
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

	private static int leftChild(int b) {
		return 2 * b + 1;
	}

	public static void heapAdjust(int[] arr, int i, int n) {
		int child = 0;
		int father;
		for (father = arr[i]; leftChild(i) < n; i = child) {
			child = leftChild(i);
			if (child < n && arr[child] < arr[child + 1]) {
				child++;
			}
			if (father < arr[child]) {
				arr[i] = arr[child];
			} else {
				break;
			}
		}
		arr[i] = father;
	}

	private static void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	public static void heapSort(int[] arr) {
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			heapAdjust(arr, i, arr.length);
		}
		for (int i = arr.length - 1; i >= 0; i--) {
			swap(arr, 0, i);
			heapAdjust(arr, 0, i - 1);
		}
	}
}
