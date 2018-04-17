package test4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

import test2.TreeLinkNode;

public class Test1 {
	@Test
	public void test() {
		int[] a = { 1, 3, 2, 6, 4 };
		isContinuous(a);
	}

	/**
	 * LL���������ر��,��Ϊ��ȥ����һ���˿���,���������Ȼ��2������,2��С��(һ����ԭ����54��^_^)...��������г����5����,
	 * �����Լ�������,�����ܲ��ܳ鵽˳��,����鵽�Ļ�,������ȥ��������Ʊ,�ٺ٣���������A,����3,С��,����,��Ƭ5��,��Oh My
	 * God!������˳��.....LL��������,��������,������\С
	 * �����Կ����κ�����,����A����1,JΪ11,QΪ12,KΪ13�������5���ƾͿ��Ա�ɡ�1,2,3,4,5��(��С���ֱ���2��4),��So
	 * Lucky!����LL����ȥ��������Ʊ���� ����,Ҫ����ʹ�������ģ������Ĺ���,Ȼ���������LL��������Ρ�Ϊ�˷������,�������Ϊ��С����0��
	 * 
	 * @param numbers
	 * @return
	 */
	public boolean isContinuous(int[] numbers) {
		int max = -1;
		int min = 14;
		int len = numbers.length;
		Set<Integer> set = new HashSet<Integer>();
		if (len < 5) {
			return false;
		}
		for (int i = 0; i < len; i++) {
			if (numbers[i] == 0) {
				continue;
			}
			if (set.contains(numbers[i])) {
				return false;
			} else {
				set.add(numbers[i]);
			}
			if (numbers[i] < min) {
				min = numbers[i];
			} else if (numbers[i] > max) {
				max = numbers[i];
			}
		}
		return ((max - min) <= 4);
	}

	/**
	 * ÿ����һ��ͯ��,ţ�Ͷ���׼��һЩС����ȥ�����¶�Ժ��С����,����������ˡ�HF��Ϊţ�͵�����Ԫ��,��ȻҲ׼����һЩС��Ϸ������,�и���Ϸ��������:
	 * ����,��С������Χ��һ����Ȧ��Ȼ��,�����ָ��һ����m,�ñ��Ϊ0��С���ѿ�ʼ������ÿ�κ���m-1���Ǹ�С����Ҫ���г��׸�,
	 * Ȼ���������Ʒ�����������ѡ����
	 * ,���Ҳ��ٻص�Ȧ��,��������һ��С���ѿ�ʼ,����0...m-1����....������ȥ....ֱ��ʣ�����һ��С����,���Բ��ñ���
	 * ,�����õ�ţ������ġ�����̽���ϡ���ذ�(��������Ŷ!!^_^)��������������,�ĸ�С���ѻ�õ������Ʒ�أ�(ע��С���ѵı���Ǵ�0��n-1)
	 * 
	 * @param n
	 * @param m
	 * @return
	 */
	public int LastRemaining_Solution(int n, int m) {
		if (n == 0) {
			return -1;
		}
		int s = 0;
		for (int i = 2; i <= n; i++) {
			s = (s + m) % i;
		}
		return s;
	}

	/**
	 * ��1+2+3+...+n��Ҫ����ʹ�ó˳�����for��while��if��else��switch��case�ȹؼ��ּ������ж���䣨A?B:C����
	 * 
	 * @param n
	 * @return
	 */
	public int Sum_Solution(int n) {
		try {
			int[] array = new int[n - 2];
			return n + Sum_Solution(n - 1);
		} catch (Exception e) {
			return 1;
		}
	}

	/**
	 * дһ������������������֮�ͣ�Ҫ���ں������ڲ���ʹ��+��-��*��/����������š�
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public int Add(int num1, int num2) {
		while (num2 != 0) {
			int temp = num1 ^ num2;
			num2 = (num1 ^ num2) << 1;
			num1 = temp;
		}
		return num1;
	}

	/**
	 * ��һ������Ϊn����������������ֶ���0��n-1�ķ�Χ�ڡ�
	 * ������ĳЩ�������ظ��ģ�����֪���м����������ظ��ġ�Ҳ��֪��ÿ�������ظ����Ρ����ҳ�����������һ���ظ������֡�
	 * ���磬������볤��Ϊ7������{2,3,1,0,2,5,3}����ô��Ӧ������ǵ�һ���ظ�������2��
	 * 
	 * @param numbers
	 * @param length
	 * @param duplication
	 * @return
	 */
	public boolean duplicate(int numbers[], int length, int[] duplication) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < length; i++) {
			if (set.contains(numbers[i])) {
				duplication[0] = numbers[i];
				return true;
			} else {
				set.add(numbers[i]);
			}
		}
		return false;
	}

	/**
	 * ����һ������A[0,1,...,n-1],�빹��һ������B[0,1,...,n-1],����B�е�Ԫ��B[i]=A[0]*A[1]*...*A[i-
	 * 1]*A[i+1]*...*A[n-1]������ʹ�ó�����
	 * 
	 * @param A
	 * @return
	 */
	public int[] multiply(int[] A) {
		int len = A.length;
		int[] B = new int[len];
		if (len < 0) {
			return B;
		}
		int temp = 1;
		B[0] = 1;
		for (int i = 1; i < len; i++) {
			B[i] = B[i - 1] * A[i - 1];
		}
		for (int j = len - 2; j >= 0; j--) {
			temp *= A[j + 1];
			B[j] *= temp;
		}
		return B;
	}

	/**
	 * ��ʵ��һ����������ƥ�����'.'��'*'��������ʽ��ģʽ�е��ַ�'.'��ʾ����һ���ַ�����'*'��ʾ��ǰ����ַ����Գ�������Σ�����0�Σ���
	 * �ڱ����� ��ƥ����ָ�ַ����������ַ�ƥ������ģʽ�����磬�ַ���"aaa"��ģʽ"a.a"��"ab*ac*a"ƥ�䣬������"aa.a"��"ab*a"
	 * ����ƥ��
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public boolean match(char[] str, char[] pattern) {
		if (str == null || pattern == null) {
			return false;
		}
		int strIndex = 0;
		int patternIndex = 0;
		return matchCore(str, strIndex, pattern, patternIndex);
	}

	private boolean matchCore(char[] str, int strIndex, char[] pattern,
			int patternIndex) {
		if (strIndex == str.length && patternIndex == pattern.length) {
			return true;
		}
		if (strIndex != str.length && patternIndex == pattern.length) {
			return false;
		}
		if (patternIndex + 1 < pattern.length
				&& pattern[patternIndex + 1] == '*') {
			if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])
					|| (pattern[patternIndex] == '.' && strIndex != str.length)) {
				return matchCore(str, strIndex, pattern, patternIndex + 2)
						|| matchCore(str, strIndex + 1, pattern,
								patternIndex + 2)
						|| matchCore(str, strIndex + 1, pattern, patternIndex);
			} else {
				return matchCore(str, strIndex, pattern, patternIndex + 2);
			}
		}
		if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])
				|| (pattern[patternIndex] == '.' && strIndex != str.length)) {
			return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
		}
		return false;
	}

	/*
	 * ��ʵ��һ�����������ҳ��ַ����е�һ��ֻ����һ�ε��ַ������磬�����ַ�����ֻ����ǰ�����ַ�"go"ʱ����һ��ֻ����һ�ε��ַ���"g"��
	 * ���Ӹ��ַ����ж���ǰ�����ַ���google"ʱ����һ��ֻ����һ�ε��ַ���"l"��
	 */
	// Insert one char from stringstream
	private int[] occurence = new int[256];
	private int index = 0;

	public Test1() {
		for (int i = 0; i < 256; i++) {
			occurence[i] = -1;
		}
	}

	public void Insert(char ch) {
		if (occurence[ch] == -1) {
			occurence[ch] = index;
		} else if (occurence[ch] > 0) {
			occurence[ch] = -2;
		}
		index++;
	}

	// return the first appearence once char in current stringstream
	public char FirstAppearingOnce() {
		char ch = '\0';
		int minIndex = Integer.MAX_VALUE;
		for (int i = 0; i < 256; i++) {
			if (occurence[i] > 0 && occurence[i] < minIndex) {
				ch = (char) i;
				minIndex = occurence[ch];
			}
		}
		if (ch == '\0') {
			return '#';
		}
		return ch;
	}

	/**
	 * һ�������а����������ҳ�������Ļ�����ڽ�㡣
	 * 
	 * @param pHead
	 * @return
	 */
	public ListNode EntryNodeOfLoop(ListNode pHead) {
		if (pHead == null || pHead.next == null) {
			return null;
		}
		ListNode slow = pHead.next;
		ListNode fast = pHead.next.next;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode p1 = pHead;
		while (p1 != slow) {
			p1 = p1.next;
			slow = slow.next;
		}
		return slow;
	}

	/**
	 * ��һ������������У������ظ��Ľ�㣬��ɾ�����������ظ��Ľ�㣬�ظ��Ľ�㲻��������������ͷָ�롣 ���磬����1->2->3->3->4->4->5
	 * �����Ϊ 1->2->5
	 * 
	 * @param pHead
	 * @return
	 */
	public ListNode deleteDuplication(ListNode pHead) {
		ListNode first = new ListNode(-1);
		ListNode last = first;
		first.next = pHead;
		ListNode p = pHead;
		while (p != null && p.next != null) {
			if (p.val == p.next.val) {
				int val = p.val;
				while (p != null && p.val == val) {
					p = p.next;
				}
				last.next = p;
			} else {
				last = p;
				p = p.next;
			}
		}
		return first.next;
	}

	/**
	 * ����һ�������������е�һ����㣬���ҳ��������˳�����һ����㲢�ҷ��ء�ע�⣬���еĽ�㲻�����������ӽ�㣬ͬʱ����ָ�򸸽���ָ�롣
	 * 
	 * @param pNode
	 * @return
	 */
	public TreeLinkNode GetNext(TreeLinkNode pNode) {
		if (pNode == null) {
			return null;
		}
		if (pNode.right != null) {
			pNode = pNode.right;
			while (pNode.left != null) {
				pNode = pNode.left;
			}
			return pNode;
		}
		while (pNode.next != null) {
			TreeLinkNode proot = pNode.next;
			if (proot.left == pNode) {
				return pNode;
			}
			pNode = pNode.next;
		}
		return null;
	}

	/**
	 * ��ʵ��һ�������������ж�һ�Ŷ������ǲ��ǶԳƵġ�ע�⣬���һ��������ͬ�˶������ľ�����ͬ���ģ�������Ϊ�ԳƵġ�
	 * 
	 * @param pRoot
	 * @return
	 */
	boolean isSymmetrical(TreeNode pRoot) {
		if (pRoot == null || (pRoot.left == null && pRoot.right == null)) {
			return true;
		}
		return symmetrical(pRoot.left, pRoot.right);
	}

	private boolean symmetrical(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left == null || right == null) {
			return false;
		}
		if (left.val == right.val) {
			return symmetrical(left.left, right.right)
					&& symmetrical(left.right, right.left);
		}
		return false;
	}

	/**
	 * ��ʵ��һ����������֮���δ�ӡ������������һ�а��մ����ҵ�˳���ӡ���ڶ��㰴�մ��������˳���ӡ�������а��մ����ҵ�˳���ӡ���������Դ����ơ�
	 * 
	 * @param pRoot
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (pRoot == null) {
			return result;
		}
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(pRoot.val);
		result.add(list);
		s1.push(pRoot);
		while (s1.isEmpty() || s2.isEmpty()) {
			if (s1.isEmpty() && s2.isEmpty()) {
				break;
			}
			ArrayList<Integer> temp = new ArrayList<Integer>();
			if (s2.isEmpty()) {
				while (!s1.isEmpty()) {
					if (s1.peek().right != null) {
						temp.add(s1.peek().right.val);
						s2.push(s1.peek().right);
					}
					if (s1.peek().left != null) {
						temp.add(s1.peek().left.val);
						s2.push(s1.peek().left);
					}
					s1.pop();
				}
			} else {
				while (!s2.isEmpty()) {
					if (s2.peek().left != null) {
						temp.add(s2.peek().left.val);
						s1.push(s2.peek().left);
					}
					if (s2.peek().right != null) {
						temp.add(s2.peek().right.val);
						s1.add(s2.peek().right);
					}
				}
			}
			if (temp.size() > 0) {
				result.add(temp);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param pRoot
	 * @return
	 */
	ArrayList<ArrayList<Integer>> Print1(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>();
		if (pRoot == null) {
			return answer;
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
		return answer;
	}

	/**
	 * ����һ�Ŷ��������������ҳ����еĵ�k��Ľ�㡣���磬 5 / \ 3 7 /\ /\ 2 4 6 8 �У��������ֵ��С˳�����������ֵΪ4��
	 * 
	 * @param pRoot
	 * @param k
	 * @return
	 */
	TreeNode KthNode(TreeNode pRoot, int k) {
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		inOrder(pRoot, list);
		if (k > list.size()) {
			return null;
		}
		return list.get(k - 1);
	}

	private void inOrder(TreeNode pRoot, ArrayList<TreeNode> list) {
		if (pRoot != null) {
			inOrder(pRoot.left, list);
			list.add(pRoot);
			inOrder(pRoot.right, list);
		}
	}

	int count = 0;
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
			return null;
		}
		double result;
		if ((count & 1) == 1) {
			result = maxHeap.peek();
		} else {
			result = (minHeap.peek() + maxHeap.peek()) / 2;
		}
		return result;
	}

}
