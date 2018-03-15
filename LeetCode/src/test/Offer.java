package test;

import java.util.ArrayList;
import java.util.Stack;

public class Offer {
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
		ListNode temp = head;
		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				temp.next = new ListNode(list1.val);
				list1 = list1.next;
			} else {
				temp.next = new ListNode(list2.val);
				list2 = list2.next;
			}
			temp = temp.next;
		}
		while (list1 != null) {
			temp.next = new ListNode(list1.val);
			list1 = list1.next;
			temp = temp.next;
		}
		while (list2 != null) {
			temp.next = new ListNode(list2.val);
			list2 = list2.next;
			temp = temp.next;
		}
		return head;
	}

	// public ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
	// List<List<Integer>> answer = new ArrayList<List<Integer>>();
	// List<Integer> temp = new ArrayList<Integer>();
	// int tmp = 0;
	// for (int i = 0; i <= sum; i++) {
	// temp.add(i);
	// tmp += i;
	// if (tmp == sum) {
	// answer.add(new ArrayList<Integer>(temp));
	// temp.clear();
	// tmp = 0;
	// continue;
	// }
	// for (int j = i + 1; j <= sum; j++) {
	// temp.add(j);
	// tmp += j;
	// if (tmp > sum) {
	// temp.clear();
	// tmp = 0;
	// break;
	// } else if (tmp == sum) {
	// answer.add(new ArrayList<Integer>(temp));
	// temp.clear();
	// tmp = 0;
	// break;
	// }
	// }
	// }
	// return answer;
	// }
	public boolean Find(int target, int[][] array) {
		int rows = array.length;
		int cols = array[0].length;
		int i = 0;
		int j = cols - 1;
		for (i = 0, j = cols - 1; i < rows && j >= 0;) {
			if (array[i][j] < target) {
				i++;
			} else if (array[i][j] == target) {
				return true;
			} else {
				j--;
			}
		}
		return false;
	}

	public String replaceSpace(StringBuffer str) {

		String string = str.toString().replaceAll("\\s", "%20");
		return string;
	}

	/**
	 * 输入一个链表，从尾到头打印链表每个节点的值
	 * 
	 * @param listNode
	 * @return
	 */
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		ArrayList<Integer> answer = new ArrayList<Integer>();
		stactPrint(listNode, answer);
		return answer;
	}

	private void stactPrint(ListNode listNode, ArrayList<Integer> answer) {
		if (listNode != null) {
			stactPrint(listNode.next, answer);
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
		return constructTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
	}

	private TreeNode constructTree(int[] pre, int start1, int end1, int[] in,
			int start2, int end2) {
		if (start1 > end1 || start2 > end2) {
			return null;
		}
		TreeNode root = new TreeNode(pre[start1]);
		int index = findIndex(in, pre[start1], start2, end2);
		int off = index - start2 - 1;
		TreeNode leftNode = constructTree(pre, start1 + 1, start1 + off + 1,
				in, start2, start2 + off);
		TreeNode rightNode = constructTree(pre, start1 + off + 2, end1, in,
				index + 1, end2);
		root.left = leftNode;
		root.right = rightNode;
		return root;
	}

	private int findIndex(int[] in, int x, int start2, int end2) {
		for (int i = start2; i <= end2; i++) {
			if (in[i] == x) {
				return i;
			}
		}
		return -1;
	}

	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();

	/**
	 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
	 * 
	 * @param node
	 */
	public void push(int node) {
		stack1.add(node);

	}

	public int pop() {
		int answer;
		while (!stack1.isEmpty()) {
			int temp = stack1.pop();
			stack2.add(temp);
		}
		answer = stack2.pop();
		while (!stack2.isEmpty()) {
			int temp = stack2.pop();
			stack1.add(temp);
		}
		return answer;
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
		int answer = 0;
		if (len == 0) {
			return 0;
		}
		for (int i = 1; i < len; i++) {
			if (array[i] < array[i - 1]) {
				answer = array[i];
			}
		}
		return answer;
	}

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
	 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
	 * 
	 * @param target
	 * @return
	 */
	public int RectCover(int target) {
		if (target == 0) {
			return 0;
		}
		if (target == 1) {
			return 1;
		}
		if (target == 2) {
			return 2;
		}
		return RectCover(target - 1) + RectCover(target - 2);

	}

	/**
	 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示
	 * 
	 * @param n
	 * @return
	 */
	public int NumberOf1(int n) {
		int count = 0;
		int flag = 1;
		while (flag != 0) {
			if ((n & flag) != 0) {
				count++;
			}
			flag = flag << 1;
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
		double res = 1;
		double curr = base;
		int n = exponent;
		if (n < 0) {
			if (base == 0) {
				throw new RuntimeException("分母不能为0");
			} else {
				exponent = -n;
			}
		} else if (n > 0) {
			exponent = n;
		} else {
			return 1;
		}
		while (exponent != 0) {
			if ((exponent & 1) == 1) {
				res *= curr;
			}
			curr *= curr;
			exponent >>= 1;
		}
		return n >= 0 ? res : 1 / res;
	}
}
