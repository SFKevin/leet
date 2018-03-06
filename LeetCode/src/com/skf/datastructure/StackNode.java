package com.skf.datastructure;

public class StackNode<E> {
	StackNode<E> next = null;
	E data;

	public StackNode(E e) {
		data = e;
	}
}
