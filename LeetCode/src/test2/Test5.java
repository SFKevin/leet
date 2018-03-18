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
	 * ���ϵ��°����ӡ��������ͬһ����������������ÿһ�����һ�С�
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
	 * ��ʵ�������������ֱ��������л��ͷ����л�������
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
	 * ����һ�Ŷ��������������ҳ����еĵ�k��Ľ�㡣���磬 5 / \ 3 7 /\ /\ 2 4 6 8 �У��������ֵ��С˳�����������ֵΪ4��
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
	 * ��εõ�һ���������е���λ����������������ж�����������ֵ����ô��λ������������ֵ����֮��λ���м����ֵ��������������ж���ż������ֵ��
	 * ��ô��λ������������ֵ����֮���м���������ƽ��ֵ��
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
	 * ����һ������ͻ������ڵĴ�С���ҳ����л�����������ֵ�����ֵ�����磬�����������{2,3,4,2,6,2,5,1}���������ڵĴ�С3��
	 * ��ôһ������6���������ڣ����ǵ����ֵ�ֱ�Ϊ{4,4,6,6,6,5}�� �������{2,3,4,2,6,2,5,1}�Ļ�������������6����
	 * {[2,3,4],2,6,2,5,1}�� {2,[3,4,2],6,2,5,1}�� {2,3,[4,2,6],2,5,1}��
	 * {2,3,4,[2,6,2],5,1}�� {2,3,4,2,[6,2,5],1}�� {2,3,4,2,6,[2,5,1]}��
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
	 * �����һ�������������ж���һ���������Ƿ����һ������ĳ�ַ��������ַ���·����·�����ԴӾ����е�����һ�����ӿ�ʼ��ÿһ�������ھ������������ң�����
	 * �������ƶ�һ�����ӡ����һ��·�������˾����е�ĳһ�����ӣ����·�������ٽ���ø��ӡ� ���� a b c e s f c s a d e e
	 * �����а���һ���ַ���
	 * "bcced"��·�������Ǿ����в�����"abcb"·������Ϊ�ַ����ĵ�һ���ַ�bռ���˾����еĵ�һ�еڶ�������֮��·�������ٴν���ø��ӡ�
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
	 * ������һ��m�к�n�еķ���һ�������˴�����0,0�ĸ��ӿ�ʼ�ƶ���ÿһ��ֻ�������ң��ϣ����ĸ������ƶ�һ��
	 * ���ǲ��ܽ�������������������λ֮�ʹ���k�ĸ��ӡ� ���磬��kΪ18ʱ���������ܹ����뷽��35,37������Ϊ3+5+3+7 =
	 * 18�����ǣ������ܽ��뷽��35,38������Ϊ3+5+3+8 = 19�����ʸû������ܹ��ﵽ���ٸ����ӣ�
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
