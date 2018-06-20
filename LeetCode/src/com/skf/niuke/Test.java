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
}
