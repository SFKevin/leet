package test4;

import java.util.ArrayList;
import java.util.List;

public class Test {
	/**
	 * 把只包含因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含因子7。
	 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
	 * 
	 * @param index
	 * @return
	 */
	public int GetUglyNumber_Solution(int index) {
		if (index <= 0) {
			return 0;
		}
		if (index == 1) {
			return 1;
		}
		int[] k = new int[index];
		int t2 = 0;
		int t3 = 0;
		int t5 = 0;
		k[0] = 1;
		for (int i = 1; i < index; i++) {
			k[i] = Math.min(k[t2] * 2, Math.min(k[t3] * 3, k[t5] * 5));
			if (k[i] == k[t2] * 2) {
				t2++;
			}
			if (k[i] == k[t3] * 3) {
				t3++;
			}
			if (k[i] == k[t5] * 5) {
				t5++;
			}
		}
		return k[index - 1];
	}

	/**
	 * 在一个字符串(1<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置
	 * 
	 * @param str
	 * @return
	 */
	public int FirstNotRepeatingChar(String str) {
		int len = str.length();
		String temp;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);
			temp = str.substring(i + 1);
			if (temp.indexOf(ch) == -1 && sb.toString().indexOf(ch) == -1) {
				return i;
			} else {
				sb.append(ch);
			}
		}
		return 0;
	}

	/**
	 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
	 * 并将P对1000000007取模的结果输出。 即输出P%1000000007
	 * 
	 * @param array
	 * @return
	 */
	public static int reverseCount = 0;

	public int InversePairs(int[] array) {
		return 0;
	}

	public static void merge(int[] array, int begin, int mid, int end) {
		int i, j, k, n1, n2;
		n1 = mid - begin + 1;
		n2 = end - mid;
		int[] L = new int[n1];
		int[] R = new int[n2];
		for (i = 0, k = begin; i < n1; k++, i++) {
			L[i] = array[k];
		}
		for (j = 0, k = mid + 1; j < n2; k++, j++) {
			R[j] = array[k];
		}
		for (k = begin, i = 0, j = 0; i < n1 && j < n2; k++) {
			if (L[i] < R[j]) {
				array[k] = L[i++];
			} else {
				reverseCount += mid - i + 1;
				array[k] = R[j++];
			}
		}
		while (i < n1) {
			array[k++] = L[i++];
		}
		while (j < n2) {
			array[k++] = R[j++];
		}
	}

	public static void mergeSort(int[] a, int begin, int end) {
		if (begin < end) {
			int mid = (begin + end) / 2;
			mergeSort(a, begin, mid);
			mergeSort(a, mid + 1, end);
			merge(a, begin, mid, end);
		}
	}

	/**
	 * 输入两个链表，找出它们的第一个公共结点。
	 * 
	 * @param pHead1
	 * @param pHead2
	 * @return
	 */
	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		int len1 = getListLength(pHead1);
		int len2 = getListLength(pHead2);
		int diff = len1 - len2;
		ListNode headLong;
		ListNode headShort;
		if (diff < 0) {
			headLong = pHead2;
			headShort = pHead1;
			diff = -diff;
		} else {
			headLong = pHead1;
			headShort = pHead2;
		}
		for (int i = 0; i < diff; i++) {
			headLong = headLong.next;
		}
		while (headLong != null && headShort != null && headLong != headShort) {
			headLong = headLong.next;
			headShort = headShort.next;
		}
		return headLong;
	}

	public static int getListLength(ListNode head) {
		int len = 0;
		ListNode temp = head;
		while (temp != null) {
			len++;
			temp = temp.next;
		}
		return len;
	}

	/**
	 * 统计一个数字在排序数组中出现的次数。
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public int GetNumberOfK(int[] array, int k) {
		int count = 0;
		int len = array.length;
		if (array == null || len == 0) {
			return 0;
		}
		int begin = getFirstK(array, k, 0, len - 1);
		int end = getLastK(array, k, 0, len - 1);
		if (begin == -1 || end == -1) {
			return 0;
		}
		return end - begin + 1;
	}

	private int getLastK(int[] array, int k, int start, int end) {
		int len = array.length;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (k < array[mid]) {
				end = mid - 1;
			} else if (k > array[mid]) {
				start = mid + 1;
			} else {
				if ((mid < (len - 1) && array[mid + 1] != k) || mid == len - 1) {
					return mid;
				} else {
					start = mid + 1;
				}
			}
		}
		return -1;
	}

	private int getFirstK(int[] array, int k, int start, int end) {
		while (start <= end) {
			int mid = (start + end) / 2;
			if (k < array[mid]) {
				end = mid - 1;
			} else if (k > array[mid]) {
				start = mid + 1;
			} else {
				if ((mid > 0 && array[mid - 1] != k) || mid == 0) {
					return mid;
				} else {
					end = mid - 1;
				}
			}
		}
		return -1;
	}

	public int TreeDepth(TreeNode root) {
		if (root != null) {
			return Math
					.max(TreeDepth(root.left) + 1, TreeDepth(root.right) + 1);
		} else {

			return 0;
		}
	}

	/**
	 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
	 * 
	 * @param root
	 * @return
	 */
	public boolean IsBalanced_Solution(TreeNode root) {
		if (root == null) {
			return true;
		}
		int nLeft = TreeDepth(root.left);
		int nRight = TreeDepth(root.right);
		int diff = nLeft - nRight;
		if (diff > 1 || diff < -1) {
			return false;
		}
		return IsBalanced_Solution(root.left)
				&& IsBalanced_Solution(root.right);
	}

	/**
	 * //num1,num2分别为长度为1的数组。传出参数 //将num1[0],num2[0]设置为返回结果
	 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
	 * 
	 * @param array
	 * @param num1
	 * @param num2
	 */
	public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
		int len = array.length;
		if (len <= 1) {
			return;
		}
		int sum = 0;
		num1[0] = 0;
		num2[0] = 0;
		for (int i = 0; i < len; i++) {
			sum ^= array[i];
		}
		int index = 0;
		for (index = 0; index < 32; index++) {
			if ((sum & (1 << index)) != 0) {
				break;
			}
		}
		for (int i = 0; i < len; i++) {
			if ((array[i] & (1 << index)) != 0) {
				num1[0] ^= array[i];
			} else {
				num2[0] ^= array[i];
			}
		}
	}

	/**
	 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,
	 * 他在想究竟有多少种连续的正数序列的和为100
	 * (至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你
	 * ,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
	 * 
	 * @param sum
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
		ArrayList<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>();
		if (sum <= 1) {
			return answer;
		}
		int low = 1;
		int high = 2;
		int temp = 0;
		while (low != (sum + 1) / 2) {
			temp = sums(low, high);
			if (temp == sum) {
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				for (int i = low; i <= high; i++) {
					tmp.add(i);
				}
				answer.add(tmp);
				low++;
				high++;
			} else if (temp < sum) {
				high++;
			} else {
				low++;
			}
		}
		return answer;
	}

	private int sums(int low, int high) {
		int temp = 0;
		for (int i = low; i <= high; i++) {
			temp += i;
		}
		return temp;
	}

	@org.junit.Test
	public void test() {
		int[] a = { 1, 2, 4, 7, 11, 15 };
		ArrayList<Integer> list = FindNumbersWithSum(a, 15);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	/**
	 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，是的他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
	 * 
	 * @param array
	 * @param sum
	 * @return
	 */
	public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
		int len = array.length;
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (len <= 1) {
			return list;
		}
		int low = 0;
		int high = len - 1;
		long min = Long.MAX_VALUE;
		while (low < high) {
			int temp = array[low] + array[high];
			if (temp == sum) {
				if (array[low] * array[high] < min) {
					min = array[low] * array[high];
					list.clear();
					list.add(array[low]);
					list.add(array[high]);
				}
				low++;
				high--;
			} else if (temp < sum) {
				low++;
			} else {
				high--;
			}
		}
		return list;
	}

	/**
	 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
	 * 请你把其循环左移K位后的序列输出
	 * 。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
	 * 
	 * @param str
	 * @param n
	 * @return
	 */
	public String LeftRotateString(String str, int n) {
		char[] strs = str.toCharArray();
		int len = strs.length;
		if (len <= 1) {
			return str;
		}
		reverse(strs, 0, n - 1);
		reverse(strs, n, len - 1);
		reverse(strs, 0, len - 1);
		return new String(strs);
	}

	public void reverse(char[] strs, int start, int end) {
		char temp;
		while (start < end) {
			temp = strs[start];
			strs[start] = strs[end];
			strs[end] = temp;
			start++;
			end--;
		}
	}

	/**
	 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看
	 * ，但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a
	 * student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
	 * 
	 * @param str
	 * @return
	 */
	public String ReverseSentence(String str) {
		char[] strs = str.toCharArray();
		int len = strs.length;
		if (len <= 1) {
			return str;
		}
		int index = 0;
		for (int i = 0; i < len; i++) {
			if (strs[i] == ' ') {
				reverse(strs, index, i - 1);
				index = i + 1;
			} else if (i == len - 1) {
				reverse(strs, index, i);
			}
		}
		reverse(strs, 0, len - 1);
		return new String(strs);
	}

	public List<Integer> getPrimes(int n) {
		List<Integer> ret = new ArrayList<Integer>();
		int num = 3;
		if (n < 1) {
			return ret;
		}
		if (n == 1) {
			ret.add(2);
			return ret;
		}
		ret.add(2);
		int pc = 1;
		while (pc < n) {
			int flag = 0;
			for (int i = 2; i * i <= num; i++) {
				if (num % i == 0) {
					flag = 1;
				}
			}
			if (flag == 0) {
				pc++;
				ret.add(num);
			}
			num += 2;
		}
		return ret;
	}
}
