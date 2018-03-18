package test2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test5 {
	/**
	 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
	 * 
	 * @param pRoot
	 * @return
	 */
	ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		List<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>();
		if (pRoot == null) {
			return (ArrayList<ArrayList<Integer>>) answer;
		}
		Queue<TreeNode> q1 = new LinkedList<TreeNode>();
		q1.add(pRoot);
		while (!q1.isEmpty()) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			int count = q1.size();
			for (int i = 0; i < count; i++) {
				if (q1.peek().left != null) {
					q1.add(q1.peek().left);
				}
				if (q1.peek().right != null) {
					q1.add(q1.peek().right);
				}
				temp.add(q1.poll().val);
			}
			answer.add(temp);
		}
		return (ArrayList<ArrayList<Integer>>) answer;
	}

	/**
	 * 请实现两个函数，分别用来序列化和反序列化二叉树
	 * 
	 * @param root
	 * @return
	 */
	public int index = -1;

	String Serialize(TreeNode root) {
		StringBuffer sb = new StringBuffer();
		if (root == null) {
			sb.append("#,");
			return sb.toString();
		}
		sb.append(root.val + ",");
		sb.append(Serialize(root.left));
		sb.append(Serialize(root.right));
		return sb.toString();
	}

	TreeNode Deserialize(String str) {
		index++;
		String[] DLRseq = str.split(",");
		TreeNode leave = null;
		if (!DLRseq[index].equals("#")) {
			leave = new TreeNode(Integer.valueOf(DLRseq[index]));
			leave.left = Deserialize(str);
			leave.right = Deserialize(str);
		}
		return leave;
	}

	/**
	 * 给定一颗二叉搜索树，请找出其中的第k大的结点。例如， 5 / \ 3 7 /\ /\ 2 4 6 8 中，按结点数值大小顺序第三个结点的值为4。
	 * 
	 * @param pRoot
	 * @param k
	 * @return
	 */
	TreeNode KthNode(TreeNode pRoot, int k) {
		if (k <= 0) {
			return null;
		}
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		inOrder(pRoot, list);
		if (k > list.size()) {
			return null;
		}
		return list.get(k);
	}

	private void inOrder(TreeNode pRoot, ArrayList<TreeNode> list) {
		if (pRoot != null) {
			inOrder(pRoot.left, list);
			list.add(pRoot);
			inOrder(pRoot.right, list);
		}
	}

	/**
	 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，
	 * 那么中位数就是所有数值排序之后中间两个数的平均值。
	 * 
	 * @param num
	 */
	int count;
	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11,
			new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o2.compareTo(o1);
				}
			});

	public void Insert(Integer num) {
		count++;
		if ((count & 1) == 0) {
			if (!maxHeap.isEmpty() && num < maxHeap.peek()) {
				maxHeap.offer(num);
				num = maxHeap.poll();
			}
			minHeap.offer(num);
		} else {
			if (!minHeap.isEmpty() && num > minHeap.peek()) {
				minHeap.offer(num);
				num = minHeap.poll();
			}
			maxHeap.offer(num);
		}
	}

	public Double GetMedian() {
		if (count == 0) {
			throw new RuntimeException();
		}
		double result;
		if ((count & 1) == 1) {
			result = maxHeap.peek();
		} else {
			result = (maxHeap.peek() + minHeap.peek()) / 2.0;
		}
		return result;
	}

	/**
	 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
	 * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
	 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
	 * {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
	 * 
	 * @param num
	 * @param size
	 * @return
	 */
	public ArrayList<Integer> maxInWindows(int[] num, int size) {
		int len = num.length;
		ArrayList<Integer> answer = new ArrayList<Integer>();
		if (len <= 0 || size <= 0) {
			return answer;
		}
		int begin;
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		for (int i = 0; i < len; i++) {
			begin = i - size + 1;
			if (q.isEmpty()) {
				q.add(i);
			} else if (begin > q.peekFirst()) {
				q.pollFirst();
			}
			while (!q.isEmpty() && num[q.peekLast()] <= num[i]) {
				q.pollLast();
			}
			q.add(i);
			if (begin >= 0) {
				answer.add(num[q.peekFirst()]);
			}
		}
		return answer;
	}

	/**
	 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上
	 * ，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如 a b c e s f c s a d e e
	 * 矩阵中包含一条字符串
	 * "bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
	 * 
	 * @param matrix
	 * @param rows
	 * @param cols
	 * @param str
	 * @return
	 */
	public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
		int[] flag = new int[matrix.length];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (helper(matrix, rows, cols, i, j, str, 0, flag)) {
					return true;
				}
			}
		}
		return false;

	}

	private boolean helper(char[] matrix, int rows, int cols, int i, int j,
			char[] str, int k, int[] flag) {
		int index = i * cols + j;
		if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k]
				|| flag[index] == 1) {
			return false;
		}
		if (k == str.length - 1) {
			return true;
		}
		flag[index] = 1;
		if (helper(matrix, rows, cols, i - 1, j, str, k + 1, flag)
				|| helper(matrix, rows, cols, i + 1, j, str, k + 1, flag)
				|| helper(matrix, rows, cols, i, j - 1, str, k + 1, flag)
				|| helper(matrix, rows, cols, i, j + 1, str, k + 1, flag)) {
			return true;
		}
		flag[index] = 0;
		return false;
	}

	/**
	 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
	 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 =
	 * 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
	 * 
	 * @param threshold
	 * @param rows
	 * @param cols
	 * @return
	 */
	public int movingCount(int threshold, int rows, int cols) {
		int[][] flag = new int[rows][cols];
		return find(0, 0, rows, cols, flag, threshold);
	}

	private int find(int i, int j, int rows, int cols, int[][] flag,
			int threshold) {
		if (i < 0 || i >= rows || j < 0 || j >= cols
				|| numSum(i) + numSum(j) > threshold || flag[i][j] == 1) {
			return 0;
		}
		flag[i][j] = 1;
		return find(i - 1, j, rows, cols, flag, threshold)
				+ find(i + 1, j, rows, cols, flag, threshold)
				+ find(i, j - 1, rows, cols, flag, threshold)
				+ find(i, j + 1, rows, cols, flag, threshold) + 1;
	}

	private int numSum(int i) {
		int sum = 0;
		while (i != 0) {
			sum += i % 10;
			i = i / 10;
		}
		return sum;
	}

}
