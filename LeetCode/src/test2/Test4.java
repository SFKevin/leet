package test2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Test4 {
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
	 * ����һ������A[0,1,...,n-1],�빹��һ������B[0,1,...,n-1],����B�е�Ԫ��B[i]=A[0]*A[1]*...*A[i-
	 * 1]*A[i+1]*...*A[n-1]������ʹ�ó�����
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
	 * ��ʵ��һ����������ƥ�����'.'��'*'��������ʽ��ģʽ�е��ַ�'.'��ʾ����һ���ַ�����'*'��ʾ��ǰ����ַ����Գ�������Σ�����0�Σ���
	 * �ڱ����� ��ƥ����ָ�ַ����������ַ�ƥ������ģʽ�����磬�ַ���"aaa"��ģʽ"a.a"��"ab*ac*a"ƥ�䣬������"aa.a"��"ab*a"
	 * ����ƥ��
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
	 * ��ʵ��һ�����������ж��ַ����Ƿ��ʾ��ֵ������������С���������磬�ַ���"+100","5e2","-123","3.1416"��"-1E-16"
	 * ����ʾ��ֵ�� ����"12e","1a3.14","1.2.3","+-5"��"12e+4.3"�����ǡ�
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNumeric(char[] str) {
		String string = new String(str);
		return string.matches("[\\+-]?[0-9]*(\\.[0-9]*)?([eE][\\+-]?[0-9]+)?");
	}

	/**
	 * ��ʵ��һ�����������ҳ��ַ����е�һ��ֻ����һ�ε��ַ������磬�����ַ�����ֻ����ǰ�����ַ�"go"ʱ����һ��ֻ����һ�ε��ַ���"g"��
	 * ���Ӹ��ַ����ж���ǰ�����ַ���google"ʱ����һ��ֻ����һ�ε��ַ���"l"��
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
	 * һ�������а����������ҳ�������Ļ�����ڽ�㡣
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
				return proot;
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
	 * ��ʵ��һ����������֮���δ�ӡ������������һ�а��մ����ҵ�˳���ӡ���ڶ��㰴�մ��������˳���ӡ�������а��մ����ҵ�˳���ӡ���������Դ����ơ�
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
