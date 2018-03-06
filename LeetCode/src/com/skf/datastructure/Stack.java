package com.skf.datastructure;

public class Stack<E> {
	StackNode<E> top = null;

	public boolean isEmpty() {
		return top == null;
	}

	public void push(E data) {
		StackNode<E> newNode = new StackNode<E>(data);
		newNode.next = top;
		top = newNode;
	}

	public E pop() {
		if (isEmpty()) {
			return null;
		}
		E data = top.data;
		top = top.next;
		return data;
	}

	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return top.data;
	}
}
