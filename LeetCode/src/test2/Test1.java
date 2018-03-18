package test2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

public class Test1 {
	@Test
	public void test() {
		int[] array = { 1, 2, 3, 4, 5, 6, 7 };
		reOrderArray(array);
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
		int flag;
		for (int i = 0; i < len; i++) {
			if (array[i] % 2 == 1) {
				continue;
			} else {
				for (int j = i + 1; j < len; j++) {
					if (array[j] % 2 == 1) {
						flag = j;
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
		if (head == null) {
			return null;
		}
		ListNode p1 = head;
		ListNode p2 = head;
		int i;
		for (i = 0; i < k && p2 != null; i++) {
			p2 = p2.next;
		}
		if (i <= k - 1) {
			return null;
		}
		while (p2 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
	}

	/**
	 * 输入一个链表，反转链表后，输出链表的所有元素。
	 * 
	 * @param head
	 * @return
	 */
	public ListNode ReverseList(ListNode head) {
		Stack<Integer> stack = new Stack<Integer>();
		if (head == null) {
			return null;
		}
		ListNode p1 = head;
		while (p1 != null) {
			stack.add(p1.val);
			p1 = p1.next;
		}
		ListNode newHead = new ListNode(stack.pop());
		ListNode p2 = newHead;
		while (!stack.isEmpty()) {
			p2.next = new ListNode(stack.pop());
			p2 = p2.next;
		}
		return newHead;
	}

	/**
	 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
	 * 
	 * @param root1
	 * @param root2
	 * @return
	 */
	public boolean HasSubtree(TreeNode root1, TreeNode root2) {
		if (root2 == null) {
			return false;
		}
		if (root1 == null && root2 != null) {
			return false;
		}
		boolean result = false;
		if (root1.val == root2.val) {
			result = isSubTree(root1, root2);
		}
		return result || HasSubtree(root1.left, root2)
				|| HasSubtree(root1.right, root2);
	}

	private boolean isSubTree(TreeNode root1, TreeNode root2) {
		if (root2 == null) {
			return false;
		}
		if (root1 == null && root2 != null) {
			return false;
		}
		if (root1.val == root2.val) {
			return isSubTree(root1.left, root2.left)
					&& isSubTree(root1.right, root2.right);
		}
		return false;
	}

	private boolean DoesTree1HaveTree2(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 != null) {
			return false;
		}
		if (root2 == null) {
			return true;
		}
		if (root1.val != root2.val) {
			return false;
		}
		return DoesTree1HaveTree2(root1.left, root2.left)
				&& DoesTree1HaveTree2(root1.right, root2.right);
	}

	public void swap(TreeNode root) {
		if (root == null) {
			return;
		}
		TreeNode temp;
		if (root.left != null && root.right != null) {
			temp = root.left;
			root.left = root.right;
			root.right = temp;
			swap(root.left);
			swap(root.right);
		}
	}

	public void Mirror(TreeNode root) {
		if (root == null) {
			return;
		} else {
			TreeNode temp = root.left;
			root.left = root.right;
			root.right = temp;
			Mirror(root.left);
			Mirror(root.right);
		}
	}

	public ArrayList<Integer> printMatrix(int[][] matrix) {
		ArrayList<Integer> answer = new ArrayList<Integer>();
		int row = matrix.length;
		int col = matrix[0].length;
		if (row == 0 || col == 0) {
			return answer;
		}
		int left = 0;
		int top = 0;
		int bottom = row - 1;
		int right = col - 1;
		while (left <= right && top <= bottom) {
			for (int i = left; i <= right; i++) {
				answer.add(matrix[top][i]);
			}
			for (int i = top + 1; i <= bottom; i++) {
				answer.add(matrix[i][right]);
			}
			for (int i = right - 1; i >= left && bottom > top; i--) {
				answer.add(matrix[bottom][i]);
			}
			for (int i = bottom - 1; i > top && right > left; i--) {
				answer.add(matrix[i][left]);
			}
			left++;
			top++;
			bottom--;
			right--;
		}
		return answer;
	}

	/**
	 * 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
	 * 
	 * @param node
	 */
	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();

	public void push(int node) {
		stack1.push(node);
		if (stack2.isEmpty()) {
			stack2.push(node);
		} else if (node <= stack2.peek()) {
			stack2.push(node);
		}
	}

	public void pop() {
		if (stack1.peek() == stack2.peek()) {
			stack2.pop();
		}
		stack1.pop();
	}

	public int top() {
		return stack1.peek();
	}

	public int min() {
		return stack2.peek();
	}

	/**
	 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5
	 * 是某栈的压入顺序
	 * ，序列4，5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
	 * 
	 * @param pushA
	 * @param popA
	 * @return
	 */
	public boolean IsPopOrder(int[] pushA, int[] popA) {
		if (pushA.length <= 0 || popA.length <= 0) {
			return false;
		}
		Stack<Integer> stack = new Stack<Integer>();
		int j = 0;
		for (int i = 0; i < pushA.length; i++) {
			stack.push(pushA[i]);
			if (pushA[i] == popA[j]) {
				j++;
				if (j == popA.length - 1) {
					return true;
				}
				stack.pop();
			}
		}
		while (!stack.isEmpty()) {
			if (stack.pop() != popA[j]) {
				return false;
			} else {
				j++;
			}
		}
		return true;
	}

	/**
	 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
	 * 
	 * @param root
	 * @return
	 */
	public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
		if (root == null) {
			return null;
		}
		ArrayList<Integer> answer = new ArrayList<Integer>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			answer.add(node.val);
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		return answer;
	}

	/**
	 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同
	 * 
	 * @param sequence
	 * @return
	 */
	public boolean VerifySquenceOfBST(int[] sequence) {
		if (sequence.length == 0) {
			return false;
		}
		if (sequence.length == 1) {
			return true;
		}
		return judge(sequence, 0, sequence.length - 1);

	}

	private boolean judge(int[] a, int start, int root) {
		if (start >= root) {
			return true;
		}
		int i = root;
		while (i > start && a[i - 1] > a[root]) {
			i--;
		}
		for (int j = start; j < i; j++) {
			if (a[j] > a[root]) {
				return false;
			}
		}
		return judge(a, start, i - 1) && judge(a, i, root - 1);
	}

	/**
	 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
	 * 
	 * @param root
	 * @param target
	 * @return
	 */
	public ArrayList<List<Integer>> FindPath(TreeNode root, int target) {
		ArrayList<List<Integer>> answer = new ArrayList<List<Integer>>();
		bfs(root, answer, new ArrayList<Integer>(), target);
		return answer;
	}

	public static void bfs(TreeNode root, ArrayList<List<Integer>> answer,
			ArrayList<Integer> list, int target) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			target = target - root.val;
			if (target == 0) {
				list.add(root.val);
				answer.add(new ArrayList<Integer>(list));
				list.remove(list.size() - 1);
			}
			target += root.val;
		} else {
			if (target >= root.val) {
				target -= root.val;
				list.add(root.val);
				if (root.left != null) {
					bfs(root.left, answer, list, target);
				}
				if (root.right != null) {
					bfs(root.right, answer, list, target);
				}
				target += root.val;
				list.remove(list.size() - 1);
			}
		}
	}

	/**
	 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。（
	 * 注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
	 * 
	 * @param pHead
	 * @return
	 */
	public RandomListNode Clone(RandomListNode pHead) {
		if (pHead == null) {
			return null;
		}
		RandomListNode pCur = pHead;
		while (pCur != null) {
			RandomListNode node = new RandomListNode(pCur.label);
			node.next = pCur.next;
			pCur.next = node;
			pCur = node.next;
		}
		pCur = pHead;
		while (pCur != null) {
			if (pCur.random != null) {
				pCur.next.random = pCur.random.next;
			}
			pCur = pCur.next.next;
		}
		RandomListNode head = pHead.next;
		RandomListNode cur = head;
		pCur = pHead;
		while (pCur != null) {
			pCur.next = pCur.next.next;
			if (cur.next != null) {
				cur.next = cur.next.next;
			}
			cur = cur.next;
			pCur = pCur.next;
		}
		return head;
	}
}
