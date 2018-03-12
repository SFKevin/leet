package test;

public class Sort {
	public static void selectSort(int[] a) {
		int n = a.length;
		int temp;
		int flag;
		for (int i = 0; i < n; i++) {
			temp = a[i];
			flag = i;
			for (int j = i + 1; j < n; j++) {
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

	public static void merge(int[] array, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] L = new int[n1];
		int[] R = new int[n2];
		int k, i, j;
		for (i = 0, k = p; i < n1; i++, k++) {
			L[i] = array[k];
		}
		for (j = 0, k = q + 1; j < n2; j++, k++) {
			R[j] = array[k];
		}
		for (k = p, i = 0, j = 0; i < n1 && j < n2; k++) {
			if (L[i] < R[j]) {
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

	public static void sort(int[] array, int low, int high) {
		int i;
		int j;
		int index;
		if (low >= high) {
			return;
		}
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

	public static void shellSort(int[] array) {
		int length = array.length;
		int i, j;
		int h;
		int temp;
		for (h = length / 2; h > 0; h = h / 2) {
			for (i = h; i < length; i++) {
				temp = array[i];
				for (j = i - h; j >= 0; j -= h) {
					if (array[j] > temp) {
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

	private static int leftChild(int b) {
		return 2 * b + 1;
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
		for (int i = arr.length; i > 0; i--) {
			swap(arr, 0, i);
			heapAdjust(arr, 0, i);
		}
	}

}
