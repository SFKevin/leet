package Test3;

import java.util.ArrayList;
import java.util.Stack;

public class Test1 {

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
				System.out.println(stack1.pop().val);
			}
			if (!stack1.isEmpty()) {
				stack2.pop();
				stack2.push(1);
				root = stack1.pop();
				root = root.right;
			}
		}
	}

	/**
	 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
	 * 判断数组中是否含有该整数。
	 * 
	 * @param target
	 * @param array
	 * @return
	 */
	public boolean Find(int target, int[][] array) {
		int rows = array.length;
		int cols = array[0].length;
		for (int i = rows - 1, j = 0; i >= 0 && j < cols;) {
			if (target < array[i][j]) {
				i--;
			} else if (target > array[i][j]) {
				j++;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are
	 * Happy.则经过替换之后的字符串为We%20Are%20Happy。
	 * 
	 * @param str
	 * @return
	 */
	public String replaceSpace(StringBuffer str) {
		String res = str.toString().replaceAll("\\s", "%20");
		return res;
	}

	/**
	 * 输入一个链表，从尾到头打印链表每个节点的值。
	 * 
	 * @param listNode
	 * @return
	 */
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		ArrayList<Integer> answer = new ArrayList<Integer>();
		print(listNode, answer);
		return answer;
	}

	private void print(ListNode listNode, ArrayList<Integer> answer) {
		if (listNode != null) {
			print(listNode.next, answer);
			answer.add(listNode.val);
		}
	}

	/**
	 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,
	 * 7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
	 * 
	 * @param pre
	 * @param in
	 * @return
	 */
	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		int len1 = pre.length;
		int len2 = in.length;
		TreeNode root = constractTree(pre, 0, len1 - 1, in, 0, len2 - 1);
		return root;
	}

	private TreeNode constractTree(int[] pre, int start1, int end1, int[] in,
			int start2, int end2) {
		if (start1 > end1 || start2 > end2) {
			return null;
		}
		TreeNode root = new TreeNode(pre[start1]);
		int index = findIndex(pre[start1], in, start2, end2);
		int offset = index - start2;
		TreeNode left = constractTree(pre, start1 + 1, start1 + offset, in,
				start2, start2 + offset - 1);
		TreeNode right = constractTree(pre, start1 + offset + 1, end1, in,
				start2 + offset + 1, end2);
		root.left = left;
		root.right = right;
		return root;
	}

	private int findIndex(int x, int[] in, int start2, int end2) {
		for (int i = start2; i <= end2; i++) {
			if (x == in[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
	 */
	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();

	public void push(int node) {
		stack1.push(node);
	}

	public int pop() {
		int temp;
		while (!stack1.isEmpty()) {
			temp = stack1.pop();
			stack2.push(temp);
		}
		temp = stack2.pop();
		while (!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}
		return temp;
	}

	/**
	 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
	 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
	 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
	 * 
	 * @param array
	 * @return
	 */
	public int minNumberInRotateArray(int[] array) {
		int len = array.length;
		if (len == 0) {
			return 0;
		}
		if (len == 1) {
			return array[0];
		}
		for (int i = 1; i < len; i++) {
			if (array[i] < array[i - 1]) {
				return array[i];
			}
		}
		return 0;
	}

	/**
	 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
	 * 
	 * n<=39
	 * 
	 * @param n
	 * @return
	 */
	public int Fibonacci(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return Fibonacci(n - 1) + Fibonacci(n - 2);
	}

	/**
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
	 * 
	 * @param target
	 * @return
	 */
	public int JumpFloor(int target) {
		if (target == 1) {
			return 1;
		}
		if (target == 2) {
			return 2;
		}
		return JumpFloor(target - 1) + JumpFloor(target - 2);
	}

	/**
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
	 * 
	 * @param target
	 * @return
	 */
	public int JumpFloorII(int target) {
		// if (target == 1) {
		// return 1;
		// }
		// if (target == 2) {
		// return 2;
		// }
		// return 2 * JumpFloorII(target - 1);
		int[] a = new int[target + 1];
		a[0] = 1;
		a[1] = 1;
		for (int i = 2; i <= target; i++) {
			a[i] = 0;
			for (int j = i - 1; j >= 0; j--) {
				a[i] += a[j];
			}
		}
		return a[target];
	}

	/**
	 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
	 * 
	 * @param n
	 * @return
	 */
	public int NumberOf1(int n) {
		int flag = 1;
		int count = 0;
		while (flag != 0) {
			if ((flag & n) != 0) {
				count += 1;
			}
			flag = (flag << 1);
		}
		return count;
	}

	/**
	 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
	 * 
	 * @param base
	 * @param exponent
	 * @return
	 */
	public double Power(double base, int exponent) {
		int n = exponent;
		double answer = 1.0;
		if (n < 0) {
			if (base == 0.0) {
				throw new RuntimeException();
			} else {
				n = -n;
			}
		} else if (n == 0) {
			return 1;
		}
		double cur = base;
		while (n > 0) {
			if (n % 2 == 1) {
				answer *= cur;
			}
			cur *= cur;
			n = n >> 1;

		}
		return exponent > 0 ? answer : 1 / answer;

	}

	/**
	 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，并保证奇数和奇数，
	 * 偶数和偶数之间的相对位置不变。
	 * 
	 * @param array
	 */
	public void reOrderArray(int[] array) {
		int len = array.length;
		int temp;
		for (int i = 0; i < len; i++) {
			if (array[i] % 2 == 1) {
				continue;
			} else {
				for (int j = i + 1; j < len; j++) {
					if (array[j] % 2 == 1) {
						temp = array[j];
						while (j > i) {
							array[j] = array[j - 1];
							j--;
						}
						array[i] = temp;
						break;
					}
				}
			}
		}
	}

	/**
	 * 输入一个链表，输出该链表中倒数第k个结点。
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode FindKthToTail(ListNode head, int k) {
		ListNode p1 = head;
		ListNode p2 = head;
		int i;
		for (i = 0; i < k && p1 != null; i++) {
			p1 = p1.next;
		}
		if (i <= k - 1) {
			return null;
		}
		while (p1 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}

	/**
	 * 输入一个链表，反转链表后，输出链表的所有元素。
	 * 
	 * @param head
	 * @return
	 */
	public ListNode ReverseList(ListNode head) {
		if (head == null) {
			return head;
		}
		Stack<Integer> stack = new Stack<Integer>();
		ListNode p1 = head;
		while (p1 != null) {
			stack.push(p1.val);
			p1 = p1.next;
		}
		ListNode head2 = new ListNode(stack.pop());
		ListNode p2 = head2;
		while (!stack.isEmpty()) {
			p2.next = new ListNode(stack.pop());
			p2 = p2.next;
		}
		return head2;
	}

	/**
	 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public ListNode Merge(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		ListNode head;
		if (list1.val < list2.val) {
			head = new ListNode(list1.val);
			list1 = list1.next;
		} else {
			head = new ListNode(list2.val);
			list2 = list2.next;
		}
		ListNode p1 = head;
		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				p1.next = new ListNode(list1.val);
				p1 = p1.next;
				list1 = list1.next;
			} else {
				p1.next = new ListNode(list2.val);
				p1 = p1.next;
				list2 = list2.next;
			}
		}
		while (list1 != null) {
			p1.next = new ListNode(list1.val);
			p1 = p1.next;
			list1 = list1.next;
		}
		while (list2 != null) {
			p1.next = new ListNode(list2.val);
			p1 = p1.next;
			list2 = list2.next;
		}
		return head;
	}
}
