package com.skf.niuke;

import java.util.ArrayList;
import java.util.Stack;

import test4.ListNode;

public class Test {
	public ListNode insertionSortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode temp = new ListNode(0);
		while (head != null) {
			ListNode pre = temp;
			ListNode aft = temp.next;
			ListNode cur = head;
			head = head.next;
			while (aft != null && aft.val < cur.val) {
				pre = pre.next;
				aft = aft.next;
			}
			cur.next = aft;
			pre.next = cur;
		}
		return temp.next;
	}

	public ArrayList<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		Stack<Integer> stack2 = new Stack<Integer>();
		int flag = 1;
		while (root != null || !stack1.isEmpty()) {
			while (root != null) {
				stack1.push(root);
				stack2.push(0);
				root = root.left;
			}
			while (!stack1.isEmpty() && stack2.peek() == flag) {
				stack2.pop();
				list.add(stack1.pop().val);
			}
			if (!stack1.isEmpty()) {
				root = stack1.peek();
				stack2.pop();
				stack2.push(flag);
				root = root.right;
			}
		}
		return list;
	}

	public ArrayList<Integer> inOrder(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (root != null || !stack.isEmpty()) {
			stack.push(root);
			root = root.left;
		}
		if (!stack.isEmpty()) {
			root = stack.pop();
			list.add(root.val);
			root = root.right;
		}
		return list;
	}

	public ArrayList<Integer> preOrder(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				list.add(root.val);
				stack.push(root);
				root = root.left;
			}
		}
		if (!stack.isEmpty()) {
			root = stack.pop();
			root = root.right;
		}
		return list;
	}

	public ArrayList<Integer> preorderTraversal(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				list.add(root.val);
				stack.push(root);
				root = root.left;
			}
			if (!stack.isEmpty()) {
				root = stack.pop();
				root = root.right;
			}
		}
		return list;
	}

	public static void quickSort(int[] a, int low, int high) {
		if (low >= high) {
			return;
		}
		int index = a[low];
		int i = low;
		int j = high;
		while (i < j) {
			while (i < j && index < a[j]) {
				j--;
			}
			if (i < j) {
				a[i++] = a[j];
			}
			while (i < j && index > a[i]) {
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

	public static void heapAdjust(int[] a, int i, int n) {
		int father;
		int child = 0;
		for (father = a[i]; 2 * i + 1 < n; i = child) {
			child = 2 * i + 1;
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

	public void heapSort(int[] a) {
		int len = a.length;
		for (int i = len / 2 - 1; i >= 0; i--) {
			heapAdjust(a, i, len);
		}
		for (int i = len - 1; i > 0; i--) {
			swap(a, i, 0);
			heapAdjust(a, 0, i - 1);
		}
	}

	private void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	@org.junit.Test
	public void test() {
		int[] a = { 13, 65, 97, 76, 38, 27, 49 };
		heapSort(a);
		// mergeSort(a, 0, a.length - 1);
		for (int i : a) {
			System.out.println(i);
		}
	}
}
