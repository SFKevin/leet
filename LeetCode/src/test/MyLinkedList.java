package test;

import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.Test;

public class MyLinkedList {
	Node head = null;

	public void addNode(int d) {
		Node newNode = new Node(d);
		if (head == null) {
			head = newNode;
			return;
		}
		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = newNode;
	}

	public Boolean deleteNode(int index) {
		if (index < 1 || index > length()) {
			return false;
		}
		if (index == 1) {
			head = head.next;
			return true;
		}
		int i = 1;
		Node preNode = head;
		Node curNode = preNode.next;
		while (curNode != null) {
			if (i == index) {
				preNode.next = curNode.next;
				return true;
			}
			preNode = curNode;
			curNode = curNode.next;
			i++;
		}
		return false;
	}

	private int length() {
		int length = 0;
		Node temp = head;
		while (temp != null) {
			length++;
			temp = temp.next;
		}
		return length;
	}

	public Node orderList() {
		Node nextNode = null;
		int temp = 0;
		Node curNode = head;
		while (curNode.next != null) {
			nextNode = curNode.next;
			while (nextNode != null) {
				if (curNode.data > nextNode.data) {
					temp = curNode.data;
					curNode.data = nextNode.data;
					nextNode.data = temp;
				}
				nextNode = nextNode.next;
			}
			curNode = curNode.next;
		}
		return head;
	}

	public void deleteDuplecate(Node head) {
		Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
		Node temp = head;
		Node pre = null;
		while (temp != null) {
			if (table.containsKey(temp.data)) {
				pre.next = temp.next;
			} else {
				table.put(temp.data, 1);
				pre = temp;
			}
			temp = temp.next;
		}
	}

	public void deleteDuplecate1(Node head) {
		Node p = head;
		while (p != null) {
			Node q = p;
			while (q.next != null) {
				if (p.data == q.next.data) {
					q.next = q.next.next;
				} else {
					q = q.next;
				}
			}
			p = p.next;
		}
	}

	@Test
	public void tset() {
		int[][] matrix = { { 1, 2, 3, 4, 5 } };
		printMatrix(matrix);
	}

	public ArrayList<Integer> printMatrix(int[][] matrix) {
		ArrayList<Integer> answer = new ArrayList<Integer>();
		int row = matrix.length;
		int col = matrix[0].length;
		if (row == 0 || col == 0) {
			return answer;
		}
		int top = 0;
		int left = 0;
		int right = col - 1;
		int bottom = row - 1;
		while (left <= right && top <= bottom) {
			for (int i = left; i <= right; i++) {
				answer.add(matrix[top][i]);
			}
			for (int i = top + 1; i <= bottom; i++) {
				answer.add(matrix[i][right]);
			}
			for (int i = right - 1; i >= left && top < bottom; i--) {
				answer.add(matrix[bottom][i]);
			}
			for (int i = bottom - 1; i > top && right > left; i--) {
				answer.add(matrix[i][left]);
			}
			top++;
			left++;
			right--;
			bottom--;
		}
		return answer;
	}

	public Node findElem(Node head, int k) {
		if (k < 1 || k > this.length()) {
			return null;
		}
		Node p1 = head;
		Node p2 = head;
		for (int i = 0; i < k - 1; i++) {
			p1 = p1.next;
		}
		while (p1 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}

	public static void selectSort(int[] a) {
		int temp = 0;
		int flag = 0;
		int n = a.length;
		for (int i = 0; i < n; i++) {
			temp = a[i];
			flag = i;
			for (int j = i + 1; j < n; j++) {
				if (a[j] < temp) {
					temp = a[j];
					flag = j;
				}
			}
			if (flag != i) {
				a[flag] = a[i];
				a[i] = temp;
			}
		}
	}

	public static void insertSort(int[] a) {
		if (a != null) {
			for (int i = 1; i < a.length; i++) {
				int temp = a[i];
				int j = i;
				if (a[j - 1] > temp) {
					while (j >= 1 && a[j - 1] > temp) {
						a[j] = a[j - 1];
						j--;
					}
				}
				a[j] = temp;
			}
		}
	}

	public void maoSort(int[] a) {
		int temp;
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	public void ReverseIteratively(Node head) {
		Node pReversedHeadNode = head;
		Node pNode = head;
		Node pPrev = null;
		while (pNode != null) {
			Node pNext = pNode.next;
			if (pNext == null) {
				pReversedHeadNode = pNext;
			}
			pNext.next = pPrev;
			pPrev = pNode;
			pNode = pNext;
		}
		this.head = pReversedHeadNode;
	}

	public void printListReversely(Node pListHead) {
		if (pListHead != null) {
			printListReversely(pListHead.next);
			System.out.println(pListHead.data);
		}
	}

	public Node searchMid(Node head) {
		Node p = head;
		Node q = head;
		while (p != null && p.next != null && p.next.next != null) {
			p = p.next.next;
			q = q.next;
		}
		return q;
	}

	public boolean isLoop(Node head) {
		Node fast = head;
		Node slow = head;
		if (fast == null) {
			return false;
		}
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				return true;
			}
		}
		return !(fast == null || fast.next == null);
	}

	public boolean deleteNode(Node n) {
		if (n == null || n.next == null) {
			return false;
		}
		int temp = n.data;
		n.data = n.next.data;
		n.next.data = temp;
		n.next = n.next.next;
		return false;

	}

	public boolean isIntersect(Node h1, Node h2) {
		if (h1 == null || h2 == null) {
			return false;
		}
		Node tail1 = h1;
		Node tail2 = h2;
		while (tail1.next != null) {
			tail1 = tail1.next;
		}
		while (tail2.next != null) {
			tail2 = tail2.next;
		}

		return (tail1 == tail2);
	}
}
