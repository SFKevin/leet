package com.skf.niuke;

import java.util.ArrayList;
import java.util.Set;
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
		slectSort(a);
		// mergeSort(a, 0, a.length - 1);
		for (int i : a) {
			System.out.println(i);
		}
	}

	public void reorderList(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode after = slow.next;
		slow.next = null;
		ListNode pre = null;
		while (after != null) {
			ListNode temp = after.next;
			after.next = pre;
			pre = after;
			after = temp;
		}
		ListNode first = head;
		after = pre;
		while (first != null && after != null) {
			ListNode ftemp = first.next;
			ListNode atemp = after.next;
			first.next = after;
			first = ftemp;
			after.next = first;
			after = atemp;
		}
	}

	public static void shellSort(int[] a) {
		int len = a.length;
		for (int h = len / 2; h > 0; h = h / 2) {
			for (int i = h; i < len; i++) {
				int j = i;
				int temp = a[i];
				while (j > h && a[j - h] > temp) {
					a[j] = a[j - h];
					j = j - h;
				}
				a[j] = temp;
			}
		}
	}

	public static void slectSort(int[] a) {
		int len = a.length;
		for (int i = 0; i < len; i++) {
			int j = i;
			int temp = a[i];
			while (j > 0 && a[j - 1] > temp) {
				a[j] = a[j - 1];
				j--;
			}
			a[j] = temp;
		}
	}

	public boolean hasCycle(ListNode head) {
		if (head == null) {
			return false;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<String> wordBreak(String s, Set<String> dict) {
		return null;
	}

}
