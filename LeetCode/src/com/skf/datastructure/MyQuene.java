package com.skf.datastructure;

public class MyQuene<E> {
	private Node<E> head = null;
	private Node<E> tail = null;

	public boolean isEmpty() {
		return head == tail;
	}

	public void put(E data) {
		Node<E> newNode = new Node(data);
		if (head == null && tail == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
	}

	public E pop() {
		if (this.isEmpty()) {
			return null;
		}
		E data = head.data;
		head = head.next;
		return data;
	}

	public int size() {
		Node<E> tmp = head;
		int n = 0;
		while (tmp != null) {
			n++;
			tmp = tmp.next;
		}
		return n;
	}
}
