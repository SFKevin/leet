package test2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Test4 {
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
		boolean[] k = new boolean[length];
		for (int i = 0; i < length; i++) {
			if (k[numbers[i]] == true) {
				duplication[0] = numbers[i];
				return true;
			}
			k[numbers[i]] = true;
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
		if (len <= 0) {
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
		int lenstr = str.length;
		int lenpattern = pattern.length;
		if (lenstr == 0) {
			if (lenpattern == 0 || (lenpattern == 2 && pattern[1] == '*')) {
				return true;
			} else {
				return false;
			}
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
		if (patternIndex < pattern.length - 1
				&& pattern[patternIndex + 1] == '*') {
			if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])
					|| (pattern[patternIndex] == '.' && strIndex != str.length)) {
				return matchCore(str, strIndex, pattern, patternIndex + 2)
						|| matchCore(str, strIndex + 1, pattern,
								patternIndex + 2)
						|| matchCore(str, strIndex + 1, pattern, patternIndex);
			} else {
				matchCore(str, strIndex, pattern, patternIndex + 2);
			}
		}
		if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])
				|| (strIndex != str.length && pattern[patternIndex] == '.')) {
			return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
		}
		return false;
	}

	/**
	 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"
	 * 都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNumeric(char[] str) {
		String string = new String(str);
		return string.matches("[\\+-]?[0-9]*(\\.[0-9]*)?([eE][\\+-]?[0-9]+)?");
	}

	/**
	 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
	 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
	 * 
	 * @param ch
	 */
	// Insert one char from stringstream
	private int[] occurence = new int[256];
	private int index;

	public Test4() {
		for (int i = 0; i < 256; i++) {
			occurence[i] = -1;
		}
	}

	public void Insert(char ch) {
		if (occurence[ch] == -1) {
			occurence[ch] = index;
		} else if (occurence[ch] >= 0) {
			occurence[ch] = -2;
		}
		index++;
	}

	// return the first appearence once char in current stringstream
	public char FirstAppearingOnce() {
		char ch = '\0';
		int minIndex = Integer.MAX_VALUE;
		for (int i = 0; i < 256; i++) {
			if (occurence[i] >= 0 && occurence[i] < minIndex) {
				ch = (char) i;
				minIndex = occurence[i];
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
		ListNode p1 = pHead;
		ListNode p2 = pHead;
		while (p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
			if (p1 == p2) {
				break;
			}
		}
		if (p2 == null || p2.next == null) {
			return null;
		}
		p1 = pHead;
		while (p1 != p2) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
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
				return proot;
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
		return Symmetrical(pRoot.left, pRoot.right);
	}

	private boolean Symmetrical(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left == null || right == null) {
			return false;
		}
		if (left.val == right.val) {
			return Symmetrical(left.left, right.right)
					&& Symmetrical(left.right, right.left);
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
		List<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (pRoot == null) {
			return (ArrayList<ArrayList<Integer>>) result;
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
						s1.push(s2.peek().right);
					}
					s2.pop();
				}
			}
			if (temp.size() > 0) {
				result.add(temp);
			}
		}
		return (ArrayList<ArrayList<Integer>>) result;
	}
}
