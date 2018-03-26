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
	 * ��һ����ά�����У�ÿһ�ж����մ����ҵ�����˳������ÿһ�ж����մ��ϵ��µ�����˳�����������һ������������������һ����ά�����һ��������
	 * �ж��������Ƿ��и�������
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
	 * ��ʵ��һ����������һ���ַ����еĿո��滻�ɡ�%20�������磬���ַ���ΪWe Are
	 * Happy.�򾭹��滻֮����ַ���ΪWe%20Are%20Happy��
	 * 
	 * @param str
	 * @return
	 */
	public String replaceSpace(StringBuffer str) {
		String res = str.toString().replaceAll("\\s", "%20");
		return res;
	}

	/**
	 * ����һ��������β��ͷ��ӡ����ÿ���ڵ��ֵ��
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
	 * ����ĳ��������ǰ���������������Ľ�������ؽ����ö����������������ǰ���������������Ľ���ж������ظ������֡���������ǰ���������{1,2,4,
	 * 7,3,5,6,8}�������������{4,7,2,1,5,3,8,6}�����ؽ������������ء�
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
	 * ������ջ��ʵ��һ�����У���ɶ��е�Push��Pop������ �����е�Ԫ��Ϊint���͡�
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
	 * ��һ�������ʼ�����ɸ�Ԫ�ذᵽ�����ĩβ�����ǳ�֮Ϊ�������ת�� ����һ���ǵݼ�����������һ����ת�������ת�������СԪ�ء�
	 * ��������{3,4,5,1,2}Ϊ{1,2,3,4,5}��һ����ת�����������СֵΪ1��
	 * NOTE������������Ԫ�ض�����0���������СΪ0���뷵��0��
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
	 * ��Ҷ�֪��쳲��������У�����Ҫ������һ������n���������쳲��������еĵ�n�
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
	 * һֻ����һ�ο�������1��̨�ף�Ҳ��������2���������������һ��n����̨���ܹ��ж�����������
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
	 * һֻ����һ�ο�������1��̨�ף�Ҳ��������2��������Ҳ��������n���������������һ��n����̨���ܹ��ж�����������
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
	 * ����һ��������������������Ʊ�ʾ��1�ĸ��������и����ò����ʾ��
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
	 * ����һ��double���͵ĸ�����base��int���͵�����exponent����base��exponent�η���
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
	 * ����һ���������飬ʵ��һ�����������������������ֵ�˳��ʹ�����е�����λ�������ǰ�벿�֣����е�ż��λ��λ������ĺ�벿�֣�����֤������������
	 * ż����ż��֮������λ�ò��䡣
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
	 * ����һ����������������е�����k����㡣
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
	 * ����һ��������ת�����������������Ԫ�ء�
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
	 * ���������������������������������ϳɺ��������Ȼ������Ҫ�ϳɺ���������㵥����������
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
