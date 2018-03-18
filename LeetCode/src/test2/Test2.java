package test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class Test2 {
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
			rightHead.left = pRootOfTree;
			pRootOfTree.right = rightHead;
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
		int len = str.length();
		ArrayList<String> answer = new ArrayList<String>();
		if (len <= 0) {
			return answer;
		}
		char[] string = str.toCharArray();
		Set<String> set = new HashSet<String>();
		back(set, string, 0, len);
		answer = new ArrayList<String>(set);
		Collections.sort(answer);
		return answer;
	}

	private void back(Set<String> set, char[] string, int start, int end) {
		if (start == end - 1) {
			String result = new String(string);
			set.add(result);
		} else {
			for (int i = start; i < end; i++) {
				if (i != start && string[start] == string[i]) {
					continue;
				}
				char temp = string[start];
				char temp2 = string[i];
				string[start] = temp2;
				string[i] = temp;
				back(set, string, start + 1, end);
				string[start] = temp;
				string[i] = temp2;
			}
		}
	}

	@Test
	public void test() {
		String str = "abc";
		ArrayList<String> ans = Permutation(str);
		for (String string : ans) {
			System.out.println(string);
		}
	}

	/**
	 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
	 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
	 * 
	 * @param array
	 * @return
	 */
	public int MoreThanHalfNum_Solution(int[] array) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < array.length; i++) {
			Integer temp = map.get(array[i]);
			if (temp == null) {
				map.put(array[i], 1);
				temp = 1;
			} else {
				map.put(array[i], temp + 1);
			}
			if (temp + 1 > array.length / 2) {
				return array[i];
			}
		}
		return 0;
	}

	/**
	 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
	 * 
	 * @param input
	 * @param k
	 * @return
	 */

	public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
		ArrayList<Integer> answer = new ArrayList<Integer>();
		int len = input.length;
		if (len < k) {
			return answer;
		}
		quickSort(input, 0, len - 1);
		for (int i = 0; i < k; i++) {
			answer.add(input[i]);
		}
		return answer;

	}

	private void quickSort(int[] a, int low, int high) {
		if (low >= high) {
			return;
		}
		int i = low;
		int j = high;
		int index = a[low];
		while (i < j) {
			while (i < j && index < a[j]) {
				j--;
			}
			if (i < j) {
				a[i++] = a[j];
			}
			while (i < j && index > a[i]) {
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
		int sum = array[0];
		int max = array[0];
		for (int i = 1; i < len; i++) {
			sum = Math.max(sum + array[i], array[i]);
			if (sum >= max) {
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
		int sum = 0;
		char[] ch = sb.toString().toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == '1') {
				sum++;
			}
		}
		return sum;
	}

	public int NumberOf1Between1AndN_Solution2(int n) {
		int count = 0;
		int temp;
		for (int i = 1; i <= n; i++) {
			temp = i;
			while (temp != 0) {
				if (temp % 10 == 1) {
					count++;
				}
				temp = temp / 10;
			}
		}
		return count++;
	}

	/**
	 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
	 * 则打印出这三个数字能排成的最小数字为321323。
	 * 
	 * @param numbers
	 * @return
	 */
	public String PrintMinNumber(int[] numbers) {
		StringBuffer sb = new StringBuffer();
		ArrayList<Integer> list = new ArrayList<Integer>();
		int len = numbers.length;
		for (int i = 0; i < len; i++) {
			list.add(numbers[i]);
		}
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				String s1 = o1 + "" + o2;
				String s2 = o2 + "" + o1;
				return s1.compareTo(s2);
			}
		});
		for (int s : list) {
			sb.append(String.valueOf(s));
		}
		return sb.toString();
	}

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
		return -1;
	}

	/**
	 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
	 * 并将P对1000000007取模的结果输出。 即输出P%1000000007
	 * 
	 * @param array
	 * @return
	 */
	public static long count = 0;

	public static void merge(int[] a, int low, int mid, int high) {
		int n1 = mid - low + 1;
		int n2 = high - mid;
		int[] L = new int[n1];
		int[] R = new int[n2];
		int i, j, k;
		for (i = 0, k = low; i < n1; k++, i++) {
			L[i] = a[k];
		}
		for (j = 0, k = mid + 1; j < n2; j++, k++) {
			R[j] = a[k];
		}
		for (i = 0, j = 0, k = low; i < n1 && j < n2; k++) {
			if (L[i] < R[j]) {
				a[k] = L[i++];
			} else {
				count += mid - i + 1;
				a[k] = R[j++];
			}
		}
		while (i < n1) {
			a[k] = L[i];
			i++;
			k++;
		}
		while (j < n2) {
			a[k] = R[j];
			k++;
			j++;
		}
	}

	@Test
	public void test1() {
		int[] a = { 1, 5, 3, 2, 6 };
		// bubbleSort(a);
		InversePairs(a);
		// mergeSort(a, 0, a.length - 1);
		// for (int i : a) {
		// System.out.println(i);
		// }
		System.out.println(count);
	}

	public static void mergeSort(int[] a, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			mergeSort(a, low, mid);
			mergeSort(a, mid + 1, high);
			merge(a, low, mid, high);
		}
	}

	public int InversePairs(int[] array) {
		mergeSort(array, 0, array.length - 1);
		return (int) (count % 1000000007);
	}
}
