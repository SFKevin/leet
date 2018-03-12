package test;


public class Offer {
	public ListNode Merge(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		ListNode head;
		if (list1.val < list2.val) {
			head = new ListNode(list1.val);
			list1 = list1.next;
		} else {
			head = new ListNode(list2.val);
			list2 = list2.next;
		}
		ListNode temp = head;
		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				temp.next = new ListNode(list1.val);
				list1 = list1.next;
			} else {
				temp.next = new ListNode(list2.val);
				list2 = list2.next;
			}
			temp = temp.next;
		}
		while (list1 != null) {
			temp.next = new ListNode(list1.val);
			list1 = list1.next;
			temp = temp.next;
		}
		while (list2 != null) {
			temp.next = new ListNode(list2.val);
			list2 = list2.next;
			temp = temp.next;
		}
		return head;
	}

	// public ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
	// List<List<Integer>> answer = new ArrayList<List<Integer>>();
	// List<Integer> temp = new ArrayList<Integer>();
	// int tmp = 0;
	// for (int i = 0; i <= sum; i++) {
	// temp.add(i);
	// tmp += i;
	// if (tmp == sum) {
	// answer.add(new ArrayList<Integer>(temp));
	// temp.clear();
	// tmp = 0;
	// continue;
	// }
	// for (int j = i + 1; j <= sum; j++) {
	// temp.add(j);
	// tmp += j;
	// if (tmp > sum) {
	// temp.clear();
	// tmp = 0;
	// break;
	// } else if (tmp == sum) {
	// answer.add(new ArrayList<Integer>(temp));
	// temp.clear();
	// tmp = 0;
	// break;
	// }
	// }
	// }
	// return answer;
	// }
}
