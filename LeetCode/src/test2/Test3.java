package test2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Test3 {
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
	 * ͳ��һ�����������������г��ֵĴ�����
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
	 * ����һ�ö����������������ȡ��Ӹ���㵽Ҷ������ξ����Ľ�㣨������Ҷ��㣩�γ�����һ��·�����·���ĳ���Ϊ������ȡ�
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
		int diff = nRight - nLeft;
		if (diff > 1 || diff < -1) {
			return false;
		}
		return IsBalanced_Solution(root.left)
				&& IsBalanced_Solution(root.right);
	}

	/**
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
	 * ����һ����������������һ������S���������в������������ǵ����ǵĺ�������S������ж�����ֵĺ͵���S������������ĳ˻���С�ġ�
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
	 * �����������һ����λָ�����ѭ�����ƣ�ROL���������и��򵥵����񣬾������ַ���ģ�����ָ���������������һ���������ַ�����S��
	 * �������ѭ������Kλ����������
	 * �����磬�ַ�����S=��abcXYZdef��,Ҫ�����ѭ������3λ��Ľ��������XYZdefabc�����ǲ��Ǻܼ򵥣�OK���㶨����
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
	 * ţ���������һ����Ա��Fish��ÿ���糿���ǻ�����һ��Ӣ����־��дЩ�����ڱ����ϡ�ͬ��Cat��Fishд�������ĸ���Ȥ����һ������Fish��������
	 * ����ȴ������������˼�����磬��student. a am I������������ʶ������һ�ԭ���Ѿ��ӵ��ʵ�˳��ת�ˣ���ȷ�ľ���Ӧ���ǡ�I am a
	 * student.����Cat��һһ�ķ�ת��Щ����˳��ɲ����У����ܰ�����ô��
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
	 * LL���������ر��,��Ϊ��ȥ����һ���˿���,���������Ȼ��2������,2��С��(һ����ԭ����54��^_^)...��������г����5����,
	 * �����Լ�������,�����ܲ��ܳ鵽˳��,����鵽�Ļ�,������ȥ��������Ʊ,�ٺ٣���������A,����3,С��,����,��Ƭ5��,��Oh My
	 * God!������˳��.....LL��������,��������,������\С
	 * �����Կ����κ�����,����A����1,JΪ11,QΪ12,KΪ13�������5���ƾͿ��Ա�ɡ�1,2,3,4,5��(��С���ֱ���2��4),��So
	 * Lucky!����LL����ȥ��������Ʊ���� ����,Ҫ����ʹ�������ģ������Ĺ���,Ȼ���������LL��������Ρ�Ϊ�˷������,�������Ϊ��С����0��
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
	 * ÿ����һ��ͯ��,ţ�Ͷ���׼��һЩС����ȥ�����¶�Ժ��С����,����������ˡ�HF��Ϊţ�͵�����Ԫ��,��ȻҲ׼����һЩС��Ϸ������,�и���Ϸ��������:
	 * ����,��С������Χ��һ����Ȧ��Ȼ��,�����ָ��һ����m,�ñ��Ϊ0��С���ѿ�ʼ������ÿ�κ���m-1���Ǹ�С����Ҫ���г��׸�,
	 * Ȼ���������Ʒ�����������ѡ����
	 * ,���Ҳ��ٻص�Ȧ��,��������һ��С���ѿ�ʼ,����0...m-1����....������ȥ....ֱ��ʣ�����һ��С����,���Բ��ñ���
	 * ,�����õ�ţ������ġ�����̽���ϡ���ذ�(��������Ŷ!!^_^)��������������,�ĸ�С���ѻ�õ������Ʒ�أ�(ע��С���ѵı���Ǵ�0��n-1)
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
	 * ��1+2+3+...+n��Ҫ����ʹ�ó˳�����for��while��if��else��switch��case�ȹؼ��ּ������ж���䣨A?B:C����
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
	 * дһ������������������֮�ͣ�Ҫ���ں������ڲ���ʹ��+��-��*��/����������š�
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
	 * ��һ���ַ���ת����һ��������Ҫ����ʹ���ַ���ת�������Ŀ⺯���� ��ֵΪ0�����ַ�������һ���Ϸ�����ֵ�򷵻�0
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
