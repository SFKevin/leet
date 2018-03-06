package com.skf.datastructure;

public class MyStack1 {

	MyStack<Integer> elem;
	MyStack<Integer> min;

	public MyStack1() {
		elem = new MyStack<Integer>();
		min = new MyStack<Integer>();
	}

	public void push(int data) {
		elem.push(data);
		if (min.isEmpty()) {
			min.push(data);
		} else {
			if (data < min.peek()) {
				min.push(data);
			}
		}
	}

	public int pop() {
		int topData = elem.peek();
		elem.pop();
		if (topData == this.min()) {
			min.pop();
		}

		return topData;
	}

	private int min() {
		if (min.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			return min.peek();
		}
	}
}
