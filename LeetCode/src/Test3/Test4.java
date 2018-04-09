package Test3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Test4 {
	/**
	 * ����һ����������ÿ���ڵ����нڵ�ֵ���Լ�����ָ�룬һ��ָ����һ���ڵ㣬��һ������ָ��ָ������һ���ڵ㣩�����ؽ��Ϊ���ƺ��������head����
	 * ע�⣬���������벻Ҫ���ز����еĽڵ����ã�������������ֱ�ӷ��ؿգ�
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
			rightHead.right = pRootOfTree;
			pRootOfTree.left = rightHead;
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
	 * ��������һ�����ֳ��ֵĴ����������鳤�ȵ�һ�룬���ҳ�������֡���������һ������Ϊ9������{1,2,3,2,2,2,5,4,2}��
	 * ��������2�������г�����5�Σ��������鳤�ȵ�һ�룬������2����������������0��
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
	 * ����һ�����������飬����������������ƴ�������ų�һ��������ӡ��ƴ�ӳ���������������С��һ����������������{3��32��321}��
	 * ���ӡ���������������ųɵ���С����Ϊ321323��
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
