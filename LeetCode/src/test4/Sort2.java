package test4;

import java.util.Stack;

import org.junit.Test;

public class Sort2 {
	@Test
	public void test() {
		int[] a = { 13, 65, 97, 76, 38, 27, 49 };
		bubble(a);
		// mergeSort(a, 0, a.length - 1);
		for (int i : a) {
			System.out.println(i);
		}
	}

	public static void bubble(int[] a) {
		int len = a.length;
		int tmp = 0;
		for (int i = 0; i < len; i++) {
			for (int j = len - 1; j > i; j--) {
				if (a[j] < a[j - 1]) {
					tmp = a[j];
					a[j] = a[j - 1];
					a[j - 1] = tmp;
				}
			}
		}
	}

	public static void shellSort(int[] a) {
		int len = a.length;
		for (int h = len / 2; h > 0; h = h / 2) {
			for (int i = h; i < len; i++) {
				int tmp = a[i];
				int j = i;
				while (j > h && tmp < a[j - h]) {
					a[j] = a[j - h];
					j = j - h;
				}
				a[j] = tmp;
			}
		}
	}

	public static void insertSort(int[] a) {
		int len = a.length;
		int tmp;
		for (int i = 1; i < len; i++) {
			tmp = a[i];
			int j = i;
			while (j > 0 && tmp < a[j - 1]) {
				a[j] = a[j - 1];
				j--;
			}
			a[j] = tmp;
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
				a[k] = L[i];
				i++;
			} else {
				a[k] = R[j];
				j++;
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
		if (r > p) {
			int q = (p + r) / 2;
			mergeSort(a, p, q);
			mergeSort(a, q + 1, r);
			merge(a, p, q, r);
		}
	}

	public static void heapAdjust(int[] a, int i, int n) {
		int father;
		int child = 0;
		for (father = a[i]; leftChild(i) < n; i = child) {
			child = leftChild(i);
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

	private static int leftChild(int i) {
		return 2 * i + 1;
	}

	public static void swap(int[] a, int index1, int index2) {
		int tmp = a[index1];
		a[index1] = a[index2];
		a[index2] = tmp;
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

	public static void selectSort(int[] a) {
		int len = a.length;
		int tmp;
		int flag;
		for (int i = 0; i < len; i++) {
			tmp = a[i];
			flag = i;
			for (int j = i + 1; j < len; j++) {
				if (a[j] < tmp) {
					tmp = a[j];
					flag = j;
				}
			}
			if (flag != i) {
				a[flag] = a[i];
				a[i] = tmp;
			}
		}
	}

	public static void preOrder(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				System.out.println(root.val);
				stack.push(root);
				root = root.left;
			}
			if (!stack.isEmpty()) {
				root = stack.pop();
				root = root.right;
			}
		}
	}

	public static void inOrder(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			if (!stack.isEmpty()) {

				root = stack.pop();
				System.out.println(root.val);
				root = root.right;
			}
		}
	}

	public static void postOrder(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<Integer> stack2 = new Stack<Integer>();
		int flag = 1;
		while (root != null && !stack.isEmpty()) {
			while (root != null) {
				stack2.push(1);
				stack.push(root);
				root = root.left;
			}
			while (!stack.isEmpty() && stack2.peek() == flag) {
				System.out.println(stack.pop().val);
				stack2.pop();
			}
			while (!stack.isEmpty()) {
				stack2.pop();
				stack2.push(1);
				root = stack.pop();
				root = root.right;
			}
		}
	}
}
