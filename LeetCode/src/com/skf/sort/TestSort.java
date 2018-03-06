package com.skf.sort;

public class TestSort {

	public static void selectSort(int[] a) {
		int i, j;
		int temp = 0;
		int flag = 0;
		int n = a.length;
		for (i = 0; i < n; i++) {
			temp = a[i];
			flag = i;
			for (j = i + 1; j < n; j++) {
				if (a[j] < temp) {
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
		if (a != null) {
			for (int i = 1; i < a.length; i++) {
				int temp = a[i];
				int j = i;
				if (a[j - 1] > temp) {
					while (j >= 1 && a[j - 1] > temp) {
						a[j] = a[j - 1];
						j--;
					}
				}
				a[j] = temp;
			}
		}
	}

	public static void BubbleSort(int[] a) {
		int i, j;
		int len = a.length;
		int tmp;
		for (i = 0; i < len; ++i) {
			for (j = len - 1; j > i; --j) {
				if (a[j] < a[j - 1]) {
					tmp = a[j];
					a[j] = a[j - 1];
					a[j - 1] = tmp;

				}
			}
		}
	}

	public static void Merge(int array[], int p, int q, int r) {
		int i, j, k, n1, n2;
		n1 = q - p + 1;
		n2 = r - q;
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
			if (j < n2) {
				for (i = j; i < n2; i++, k++) {
					array[k] = R[i];
				}
			}
		}
	}

	public static void MergeSort(int array[], int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			MergeSort(array, p, q);
			MergeSort(array, q + 1, r);
			Merge(array, p, q, r);
		}
	}

	public static void sort(int array[], int low, int high) {
		int i, j;
		int index;
		if (low >= high) {
			return;
		}

		i = low;
		j = high;
		index = array[i];
		while (i < j) {
			while (i < j && array[j] > index) {
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

	public static void quickSort(int array[]) {
		sort(array, 0, array.length - 1);
	}

	public static void shellSort(int array[]) {
		int length = array.length;
		int i, j;
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

	public static void heapAdjust(int[] arr, int i, int n) {
		int child = 0;
		int father;
		for (father = arr[i]; leftChild(i) < n; i = child) {
			child = leftChild(i);
			if (child != n - 1 && arr[child] < arr[child + 1]) {
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

	private static int leftChild(int i) {
		return 2 * i + 1;
	}

	private static void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	public static void heapSort(int[] arr) {
		for (int i = arr.length / 2; i >= 0; i--) {
			heapAdjust(arr, i, arr.length);
		}

		for (int i = arr.length - 1; i > 0; i--) {
			swap(arr, 0, i);
			heapAdjust(arr, 0, i);
		}
	}
}
