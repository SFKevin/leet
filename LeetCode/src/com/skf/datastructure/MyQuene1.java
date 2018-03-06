package com.skf.datastructure;

import java.util.LinkedList;

public class MyQuene1<E> {
	private LinkedList<E> list = new LinkedList<E>();
	private int size = 0;

	public synchronized void put(E e) {
		list.addLast(e);
		size++;
	}

	public synchronized E pop() {
		size--;
		return list.removeFirst();
	}

	public synchronized boolean isEmpty() {
		return size == 0;
	}

	public synchronized int size() {
		return size;
	}

}
