package test2;

import java.util.Stack;

import org.junit.Test;

public class Test1 {
	@Test
	public void test() {
		int[] array = { 1, 2, 3, 4, 5, 6, 7 };
		reOrderArray(array);
	}

	/**
	 * ����һ���������飬ʵ��һ�����������������������ֵ�˳��ʹ�����е�����λ�������ǰ�벿�֣����е�ż��λ��λ������ĺ�벿�֣�����֤������������
	 * ż����ż��֮������λ�ò��䡣
	 * 
	 * @param array
	 */
	public void reOrderArray(int[] array) {
		int len = array.length;
		int temp;
		int flag;
		for (int i = 0; i < len; i++) {
			if (array[i] % 2 == 1) {
				continue;
			} else {
				for (int j = i + 1; j < len; j++) {
					if (array[j] % 2 == 1) {
						flag = j;
						temp = array[j];
						while (j > i) {
							array[j] = array[j - 1];
							j--;
						}
						array[i] = temp;
						break;
					}
				}
			}
		}
	}

	/**
	 * ����һ����������������е�����k����㡣
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode FindKthToTail(ListNode head, int k) {
		if (head == null) {
			return null;
		}
		ListNode p1 = head;
		ListNode p2 = head;
		int i;
		for (i = 0; i < k && p2 != null; i++) {
			p2 = p2.next;
		}
		if (i <= k - 1) {
			return null;
		}
		while (p2 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
	}

	/**
	 * ����һ��������ת�����������������Ԫ�ء�
	 * 
	 * @param head
	 * @return
	 */
	public ListNode ReverseList(ListNode head) {
		Stack<Integer> stack = new Stack<Integer>();
		if (head == null) {
			return null;
		}
		ListNode p1 = head;
		while (p1 != null) {
			stack.add(p1.val);
			p1 = p1.next;
		}
		ListNode newHead = new ListNode(stack.pop());
		ListNode p2 = newHead;
		while (!stack.isEmpty()) {
			p2.next = new ListNode(stack.pop());
			p2 = p2.next;
		}
		return newHead;
	}
}
