package Test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Test2 {
	/**
	 * �������ö�����A��B���ж�B�ǲ���A���ӽṹ����ps������Լ��������������һ�������ӽṹ��
	 * 
	 * @param root1
	 * @param root2
	 * @return
	 */
	public boolean HasSubtree(TreeNode root1, TreeNode root2) {
		boolean result = false;
		if (root1 != null || root2 != null) {
			if (root1.val == root2.val) {
				result = isSubTree(root1, root2);
			}
			if (!result) {
				result = isSubTree(root1.left, root2);
			}
			if (!result) {
				result = isSubTree(root1.right, root2);
			}
		}
		return result;
	}

	private boolean isSubTree(TreeNode root1, TreeNode root2) {
		if (root2 != null && root1 == null) {
			return false;
		}
		if (root2 == null) {
			return true;
		}
		if (root1.val != root2.val) {
			return false;
		} else {
			return isSubTree(root1.left, root2.left)
					&& isSubTree(root1.right, root2.right);
		}
	}

	/**
	 * ���������Ķ�����������任ΪԴ�������ľ���
	 * 
	 * @param root
	 */
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

	/**
	 * 
	 * @param matrix
	 * @return
	 */
	public ArrayList<Integer> printMatrix(int[][] matrix) {
		ArrayList<Integer> answer = new ArrayList<Integer>();
		int rows = matrix.length;
		int cols = matrix[0].length;
		int top = 0;
		int left = 0;
		int right = cols - 1;
		int bottom = rows - 1;
		while (top <= bottom && left <= right) {
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
			top++;
			left++;
			right--;
			bottom--;
		}
		return answer;
	}

	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();

	public void push(int node) {
		stack1.push(node);
		if (stack2.isEmpty()) {
			stack2.push(node);
		} else {
			if (node < stack2.peek()) {
				stack2.push(node);
			}
		}
	}

	public void pop() {
		int temp = stack1.pop();
		if (temp == stack2.peek()) {
			stack2.pop();
		}
	}

	public int top() {
		return stack1.peek();
	}

	public int min() {
		return stack2.peek();
	}

	/**
	 * ���������������У���һ�����б�ʾջ��ѹ��˳�����жϵڶ��������Ƿ�Ϊ��ջ�ĵ���˳�򡣼���ѹ��ջ���������־�����ȡ���������1,2,3,4,5
	 * ��ĳջ��ѹ��˳��
	 * ������4��5,3,2,1�Ǹ�ѹջ���ж�Ӧ��һ���������У���4,3,5,1,2�Ͳ������Ǹ�ѹջ���еĵ������С���ע�⣺���������еĳ�������ȵģ�
	 * 
	 * @param pushA
	 * @param popA
	 * @return
	 */
	public boolean IsPopOrder(int[] pushA, int[] popA) {
		int len = pushA.length;
		int j = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < len; i++) {
			if (pushA[i] == popA[j]) {
				j++;
			} else {
				stack.push(pushA[i]);
			}
		}
		for (; j < len; j++) {
			if (stack.pop() != popA[j]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ����һ���������飬�жϸ������ǲ���ĳ�����������ĺ�������Ľ��������������Yes,�������No���������������������������ֶ�������ͬ��
	 * 
	 * @param sequence
	 * @return
	 */
	public boolean VerifySquenceOfBST(int[] sequence) {
		int len = sequence.length;
		if (len == 0) {
			return false;
		}
		if (len == 1) {
			return true;
		}
		return judge(sequence, 0, len - 1);
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
	 * ����һ�Ŷ�������һ����������ӡ���������н��ֵ�ĺ�Ϊ��������������·����·������Ϊ�����ĸ���㿪ʼ����һֱ��Ҷ����������Ľ���γ�һ��·��
	 * 
	 * @param root
	 * @param target
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
		List<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>();
		if (root == null) {
			return (ArrayList<ArrayList<Integer>>) answer;
		}
		find(root, answer, target, new ArrayList<Integer>());
		return (ArrayList<ArrayList<Integer>>) answer;
	}

	private void find(TreeNode root, List<ArrayList<Integer>> answer,
			int target, ArrayList<Integer> list) {
		if (root.left == null && root.right == null) {
			if (target == root.val) {
				list.add(root.val);
				answer.add(new ArrayList<Integer>(list));
				list.remove(list.size() - 1);
			}
		} else {
			if (target >= root.val) {
				target -= root.val;
				list.add(root.val);
				if (root.left != null) {

					find(root.left, answer, target, list);
				}
				if (root.right != null) {

					find(root.right, answer, target, list);
				}
				target += root.val;
				list.remove(list.size() - 1);
			}
		}
	}

	public static void meg1() {
		Scanner in = new Scanner(System.in);
		int N = 0;
		int k = 0;
		while (in.hasNextInt()) {
			N = in.nextInt();
			k = in.nextInt();
		}
		int count = 0;
		count = (N - k) * (N - k + 1) / 2;
		for (int i = N; i >= 2 * k + 1; i--) {
			for (int j = i - 1; j > k; j--) {
				if ((i % j) >= k) {
					count++;
				}
			}
		}
		System.out.println(count);
	}

	public static void meg() {
		Scanner in = new Scanner(System.in);
		int N = 0;
		int k = 0;
		while (in.hasNextInt()) {
			N = in.nextInt();
			k = in.nextInt();
		}
		int count = 0;

		for (int i = k; i <= N; i++) {
			for (int j = k; j <= N; j++) {
				if ((i % j) >= k) {
					count++;
				}
			}
		}
		System.out.println(count);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] di = new int[n];
		int[] pi = new int[n];
		int[] ai = new int[m];
		int[] res = new int[m];
		for (int i = 0; i < n; i++) {
			di[i] = sc.nextInt();
			pi[i] = sc.nextInt();
		}
		for (int i = 0; i < m; i++) {
			ai[i] = sc.nextInt();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (ai[j] == 0) {
					continue;
				}
				if (ai[j] >= di[i]) {
					res[j] += pi[i];
					ai[j] -= di[i];
					break;
				} else {
					res[j] += pi[i] * (ai[j] / di[i]);
					ai[j] = 0;
					di[i] -= ai[j];
				}
			}
		}
		for (int i = 0; i < m; i++) {

			System.out.println(res[i]);
		}
	}
}
