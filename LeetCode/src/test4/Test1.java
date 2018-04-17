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
	 * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,
	 * 想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My
	 * God!”不是顺子.....LL不高兴了,他想了想,决定大\小
	 * 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So
	 * Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何。为了方便起见,你可以认为大小王是0。
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
	 * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:
	 * 首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,
	 * 然后可以在礼品箱中任意的挑选礼物
	 * ,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演
	 * ,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
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
	 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
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
	 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
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
	 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
	 * 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
	 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
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
	 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-
	 * 1]*A[i+1]*...*A[n-1]。不能使用除法。
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
	 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
	 * 在本题中 ，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"
	 * 均不匹配
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
	 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
	 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
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
	 * 一个链表中包含环，请找出该链表的环的入口结点。
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
	 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5
	 * 处理后为 1->2->5
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
	 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
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
	 * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
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
	 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
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
	 * 给定一颗二叉搜索树，请找出其中的第k大的结点。例如， 5 / \ 3 7 /\ /\ 2 4 6 8 中，按结点数值大小顺序第三个结点的值为4。
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
