package test4;

import java.util.ArrayList;
import java.util.List;

public class Test {
	/**
	 * ��ֻ��������2��3��5��������������Ugly Number��������6��8���ǳ�������14���ǣ���Ϊ����������7��
	 * ϰ�������ǰ�1�����ǵ�һ���������󰴴�С�����˳��ĵ�N��������
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
	 * ��һ���ַ���(1<=�ַ�������<=10000��ȫ������ĸ���)���ҵ���һ��ֻ����һ�ε��ַ�,����������λ��
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
	 * �������е��������֣����ǰ��һ�����ִ��ں�������֣����������������һ������ԡ�����һ������,�����������е�����Ե�����P��
	 * ����P��1000000007ȡģ�Ľ������� �����P%1000000007
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
	 * �������������ҳ����ǵĵ�һ��������㡣
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
	 * ͳ��һ�����������������г��ֵĴ�����
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
	 * ����һ�ö��������жϸö������Ƿ���ƽ���������
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
	 * //num1,num2�ֱ�Ϊ����Ϊ1�����顣�������� //��num1[0],num2[0]����Ϊ���ؽ��
	 * һ�����������������������֮�⣬���������ֶ����������Ρ���д�����ҳ�������ֻ����һ�ε����֡�
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
	 * С����ϲ����ѧ,��һ����������ѧ��ҵʱ,Ҫ������9~16�ĺ�,�����Ͼ�д������ȷ����100�����������������ڴ�,
	 * �����뾿���ж������������������еĺ�Ϊ100
	 * (���ٰ���������)��û���,���͵õ���һ������������Ϊ100������:18,19,20,21,22�����ڰ����⽻����
	 * ,���ܲ���Ҳ�ܿ���ҳ����к�ΪS��������������? Good Luck!
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
	 * ����һ����������������һ������S���������в������������ǵ����ǵĺ�������S������ж�����ֵĺ͵���S������������ĳ˻���С�ġ�
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
	 * �����������һ����λָ�����ѭ�����ƣ�ROL���������и��򵥵����񣬾������ַ���ģ�����ָ���������������һ���������ַ�����S��
	 * �������ѭ������Kλ����������
	 * �����磬�ַ�����S=��abcXYZdef��,Ҫ�����ѭ������3λ��Ľ��������XYZdefabc�����ǲ��Ǻܼ򵥣�OK���㶨����
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
	 * ţ���������һ����Ա��Fish��ÿ���糿���ǻ�����һ��Ӣ����־��дЩ�����ڱ����ϡ�ͬ��Cat��Fishд�������ĸ���Ȥ����һ������Fish��������
	 * ����ȴ������������˼�����磬��student. a am I������������ʶ������һ�ԭ���Ѿ��ӵ��ʵ�˳��ת�ˣ���ȷ�ľ���Ӧ���ǡ�I am a
	 * student.����Cat��һһ�ķ�ת��Щ����˳��ɲ����У����ܰ�����ô��
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
