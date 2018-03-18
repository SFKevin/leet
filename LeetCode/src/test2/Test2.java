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
	 * ����һ�ö��������������ö���������ת����һ�������˫������Ҫ���ܴ����κ��µĽ�㣬ֻ�ܵ������н��ָ���ָ��
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
	 * ����һ���ַ���,���ֵ����ӡ�����ַ������ַ����������С����������ַ���abc,���ӡ�����ַ�a,b,c�������г����������ַ���abc,acb,
	 * bac,bca,cab��cba��
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
	 * ��������һ�����ֳ��ֵĴ����������鳤�ȵ�һ�룬���ҳ�������֡���������һ������Ϊ9������{1,2,3,2,2,2,5,4,2}��
	 * ��������2�������г�����5�Σ��������鳤�ȵ�һ�룬������2����������������0��
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
	 * ����n���������ҳ�������С��K��������������4,5,1,6,2,7,3,8��8�����֣�����С��4��������1,2,3,4,��
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
	 * HZż������Щרҵ������������Щ�Ǽ����רҵ��ͬѧ����������鿪����,���ַ�����:�ڹ��ϵ�һάģʽʶ����,������Ҫ��������������������,
	 * ������ȫΪ������ʱ��
	 * ,����ܺý��������,��������а�������,�Ƿ�Ӧ�ð���ĳ������,�������Աߵ��������ֲ����أ�����:{6,-3,-2,7,-15,
	 * 1,2,2},����������������Ϊ8(�ӵ�0����ʼ,����3��Ϊֹ)����᲻�ᱻ������ס��(�������ĳ���������1)
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
	 * ���1~13��������1���ֵĴ���,�����100~1300��������1���ֵĴ�����Ϊ�����ر�����һ��1~13�а���1��������1��10��11��12��13
	 * ��˹�����6��,���Ƕ��ں�����������û���ˡ�ACMerϣ�����ǰ����,������������ձ黯,���Ժܿ���������Ǹ�����������1���ֵĴ�����
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
	 * ����һ�����������飬����������������ƴ�������ų�һ��������ӡ��ƴ�ӳ���������������С��һ����������������{3��32��321}��
	 * ���ӡ���������������ųɵ���С����Ϊ321323��
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
		return -1;
	}

	/**
	 * �������е��������֣����ǰ��һ�����ִ��ں�������֣����������������һ������ԡ�����һ������,�����������е�����Ե�����P��
	 * ����P��1000000007ȡģ�Ľ������� �����P%1000000007
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
