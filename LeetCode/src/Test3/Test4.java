package Test3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Test4 {
	/**
	 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。（
	 * 注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
	 * 
	 * @param pHead
	 * @return
	 */
	public RandomListNode Clone(RandomListNode pHead) {
		if (pHead == null) {
			return pHead;
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
		}
		RandomListNode head = pHead.next;
		RandomListNode cur = head;
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

	/**
	 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
	 * 
	 * @param pRootOfTree
	 * @return
	 */
	TreeNode leftHead = null;
	TreeNode rightHead = null;

	public TreeNode Convert(TreeNode pRootOfTree) {
		if (pRootOfTree == null) {
			return null;
		}
		Convert(pRootOfTree.left);
		if (rightHead == null) {
			leftHead = rightHead = pRootOfTree;
		} else {
			rightHead.right = pRootOfTree;
			pRootOfTree.left = rightHead;
			rightHead = pRootOfTree;
		}
		Convert(pRootOfTree.right);
		return leftHead;
	}

	/**
	 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,
	 * bac,bca,cab和cba。
	 * 
	 * @param str
	 * @return
	 */
	public ArrayList<String> Permutation(String str) {
		char[] chs = str.toCharArray();
		int len = chs.length;
		ArrayList<String> strs = new ArrayList<String>();
		back(chs, len, strs, 0);
		Collections.sort(strs);
		return strs;
	}

	private void back(char[] chs, int len, ArrayList<String> strs, int start) {
		if (start == len - 1) {
			strs.add(new String(chs));
		} else {
			for (int i = start; i < len; i++) {
				if (i != start && chs[start] == chs[i]) {
					continue;
				}
				char temp1 = chs[start];
				char temp2 = chs[i];
				chs[start] = temp2;
				chs[i] = temp1;
				back(chs, len, strs, start + 1);
				chs[start] = temp1;
				chs[i] = temp2;
			}
		}
	}

	/**
	 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
	 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
	 * 
	 * @param array
	 * @return
	 */
	public void quickSort(int[] a, int low, int high) {
		if (low > high) {
			return;
		}
		int i = low;
		int j = high;
		int index = a[low];
		while (i < j) {
			while (i < j && a[j] > index) {
				j--;
			}
			if (i < j) {
				a[i++] = a[j];
			}
			while (i < j && a[i] < index) {
				i++;
			}
			if (i < j) {
				a[j--] = a[i];
			}
		}
		a[i] = index;
		quickSort(a, low, i - 1);
		quickSort(a, i + 1, high);
	}

	public int MoreThanHalfNum_Solution(int[] array) {
		int len = array.length;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int arr : array) {
			Integer temp = map.get(arr);
			if (temp == null) {
				map.put(arr, 1);
				temp = 0;
			} else {
				map.put(arr, temp + 1);
			}
			if (temp + 1 > len / 2) {
				return arr;
			}
		}
		return 0;
		// quickSort(array, 0, len - 1);
		// if (len <= 0) {
		// return 0;
		// }
		// if (len == 1) {
		// return array[0];
		// }
		// if (len == 2) {
		// if (array[0] == array[1]) {
		// return array[0];
		// } else {
		// return 0;
		// }
		// }
		// int temp = array[len / 2];
		// int count = 0;
		// for (int i = 0; i < array.length; i++) {
		// if (temp == array[i]) {
		// count++;
		// }
		// }
		// if (count > len / 2) {
		// return temp;
		// } else {
		// return 0;
		// }
	}

	/**
	 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,
	 * 当向量全为正数的时候
	 * ,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,
	 * 1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。你会不会被他忽悠住？(子向量的长度至少是1)
	 * 
	 * @param array
	 * @return
	 */
	public int FindGreatestSumOfSubArray(int[] array) {
		int len = array.length;
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum = Math.max(sum + array[i], array[i]);
			if (sum > max) {
				max = sum;
			}
		}
		return max;
	}

	/**
	 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13
	 * 因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数。
	 * 
	 * @param n
	 * @return
	 */
	public int NumberOf1Between1AndN_Solution(int n) {
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= n; i++) {
			sb.append(String.valueOf(i));
		}
		char[] chs = sb.toString().toCharArray();
		int count = 0;
		for (char ch : chs) {
			if (ch == '1') {
				count++;
			}
		}

		return count;
	}

	/**
	 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
	 * 则打印出这三个数字能排成的最小数字为321323。
	 * 
	 * @param numbers
	 * @return
	 */
	public String PrintMinNumber(int[] numbers) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int num : numbers) {
			list.add(num);
		}
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				String s1 = o1 + "" + o2;
				String s2 = o2 + "" + o1;
				return s1.compareTo(s2);
			}
		});
		StringBuffer sb = new StringBuffer();
		for (int num : list) {
			sb.append(String.valueOf(num));
		}
		return sb.toString();
	}
}
