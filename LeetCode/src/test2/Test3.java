package test2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Test3 {
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
		ListNode headLong = pHead1;
		ListNode headShort = pHead2;
		if (diff < 0) {
			headLong = pHead2;
			headShort = pHead1;
			diff = -diff;
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

	private int getListLength(ListNode pHead1) {
		int len = 0;
		ListNode temp = pHead1;
		while (temp != null) {
			temp = temp.next;

			len++;
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
		int len = array.length;
		if (len == 0) {
			return 0;
		}
		int firstK = getFirstK(array, k, 0, len - 1);
		int endK = getLastK(array, k, 0, len - 1);
		if (firstK == -1 || endK == -1) {
			return 0;
		}
		return endK - firstK + 1;
	}

	private int getLastK(int[] array, int k, int start, int end) {
		if (start > end) {
			return -1;
		}
		while (start <= end) {
			int mid = (start + end) >> 1;
			if (array[mid] > k) {
				end = mid - 1;
			} else if (array[mid] < k) {
				start = mid + 1;
			} else {
				if ((mid < array.length - 1 && array[mid + 1] != k)
						|| mid == array.length - 1) {
					return mid;
				} else {
					start = mid + 1;
				}
			}
		}
		return -1;
	}

	private int getFirstK(int[] array, int k, int start, int end) {
		if (start > end) {
			return -1;
		}
		while (start <= end) {
			int mid = (start + end) >> 1;
			if (array[mid] > k) {
				end = mid - 1;
			} else if (array[mid] < k) {
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

	/**
	 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
	 * 
	 * @param root
	 * @return
	 */
	public int TreeDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right));
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
		int diff = nRight - nLeft;
		if (diff > 1 || diff < -1) {
			return false;
		}
		return IsBalanced_Solution(root.left)
				&& IsBalanced_Solution(root.right);
	}

	/**
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
	 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，是的他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
	 * 
	 * @param array
	 * @param sum
	 * @return
	 */
	public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
		long product = Long.MAX_VALUE;
		int len = array.length;
		ArrayList<Integer> answer = new ArrayList<Integer>();
		if (len <= 1) {
			return answer;
		}
		int start = 0;
		int end = len - 1;
		int temp;
		while (start < end) {
			temp = array[start] + array[end];
			if (temp < sum) {
				start++;
			} else if (temp > sum) {
				end--;
			} else {
				if (product > array[start] * array[end]) {
					product = array[start] * array[end];
					answer.clear();
					answer.add(array[start]);
					answer.add(array[end]);
				}
				start++;
				end--;
			}
		}
		return answer;
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
		char[] chs = str.toCharArray();
		int len = chs.length;
		if (len <= 1) {
			return str;
		}
		reverse(chs, 0, n - 1);
		reverse(chs, n, len - 1);
		reverse(chs, 0, len - 1);
		return new String(chs);
	}

	public static void reverse(char[] chs, int start, int end) {
		char temp;
		while (start < end) {
			temp = chs[start];
			chs[start] = chs[end];
			chs[end] = temp;
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
		// str = str.trim();
		char[] chs = str.toCharArray();
		int len = chs.length;
		if (len <= 1) {
			return str;
		}
		int index = 0;
		for (int i = 0; i < len; i++) {
			if (chs[i] == ' ') {
				reverse(chs, index, i - 1);
				index = i + 1;
			} else if (i == len - 1) {
				reverse(chs, index, i);
			}
		}
		reverse(chs, 0, len - 1);
		return new String(chs);
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
		int min = 14;
		int max = -1;
		int len = numbers.length;
		if (len < 5) {
			return false;
		}
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < len; i++) {
			if (numbers[i] != 0) {
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
			num2 = (num1 & num2) << 1;
			num1 = temp;
		}
		return num1;

	}

	/**
	 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
	 * 
	 * @param str
	 * @return
	 */
	public int StrToInt(String str) {
		char[] chs = str.toCharArray();
		int len = chs.length;
		long sum = 0;
		for (int i = len - 1; i >= 0; i--) {
			if (chs[i] < '0' || chs[i] > '9') {
				if (i == 0 && chs[i] == '+') {
					return (int) sum;
				} else if (i == 0 && chs[i] == '-') {
					sum = 0 - sum;
					return (int) sum;
				}
				return 0;
			} else {
				sum += Math.pow(10, len - 1 - i) * (chs[i] - '0');
			}
		}
		return (int) sum;
	}
}
