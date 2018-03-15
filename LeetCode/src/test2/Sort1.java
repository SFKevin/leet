package test2;

import org.junit.Test;

public class Sort1 {
	@Test
	public void test() {
		int[] a = { 13, 65, 97, 76, 38, 27, 49 };
		// bubbleSort(a);
		mergeSort(a, 0, a.length - 1);
		for (int i : a) {
			System.out.println(i);
		}
	}

	public static int leftChild(int i) {
		return 2 * i + 1;
	}

	public static void swap(int[] a, int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}

	public static void heapAdjust(int[] a, int i, int n) {
		int child = 0;
		int father;
		for (father = a[i]; leftChild(i) < n; i = child) {
			child = leftChild(child);
			if (child < n && a[child] < a[child + 1]) {
				child++;
			}
			if (father < a[child]) {
				a[i] = a[child];
			} else {
				break;
			}
		}
		a[i] = father;
	}

	public static void heapSort(int[] a) {
		int len = a.length;
		for (int i = len / 2 - 1; i >= 0; i--) {
			heapAdjust(a, i, len);
		}
		for (int i = len - 1; i >= 0; i--) {
			swap(a, 0, i);
			heapAdjust(a, 0, i - 1);
		}
	}

	public static void quickSort(int[] a, int low, int high) {
		if (low > high) {
			return;
		}
		int i = low;
		int j = high;
		int index = a[low];
		while (i < j) {
			while (i < j && a[j] > index) {
				j--;
			}
			if (i < j) {
				a[i++] = a[j];
			}
			while (i < j && a[i] < index) {
				i++;
			}
			if (i < j) {
				a[j--] = a[i];
			}
		}
		a[i] = index;
		quickSort(a, low, i - 1);
		quickSort(a, i + 1, high);
	}

	public static void insertSort(int[] a) {
		int temp;
		int len = a.length;
		for (int i = 1; i < len; i++) {
			int j = i;
			temp = a[i];
			while (j > 0 && temp < a[j - 1]) {
				a[j] = a[j - 1];
				j--;
			}
			a[j] = temp;
		}
	}

	public static void shellSort(int[] a) {
		int len = a.length;
		int temp;
		for (int h = len / 2; h > 0; h = h / 2) {
			for (int i = h; i < len; i++) {
				int j = i;
				temp = a[i];
				while (j > h && temp < a[j - h]) {
					a[j] = a[j - h];
					j -= h;
				}
				a[j] = temp;
			}
		}
	}

	public static void selectSort(int[] a) {
		int temp;
		int flag;
		int len = a.length;
		for (int i = 0; i < len; i++) {
			temp = a[i];
			flag = i;
			for (int j = i + 1; j < len; j++) {
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

	public static void bubbleSort(int[] a) {
		int len = a.length;
		int temp;
		for (int i = 0; i < len; i++) {
			for (int j = len - 1; j > i; j--) {
				if (a[j] < a[j - 1]) {
					temp = a[j];
					a[j] = a[j - 1];
					a[j - 1] = temp;
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
}
