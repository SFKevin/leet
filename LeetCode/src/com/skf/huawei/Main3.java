package com.skf.huawei;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

public class Main3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		Stack<Integer> stack = new Stack<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		Set<Integer> set = new HashSet<Integer>();
		while (num > 0) {
			int tmp = num % 10;
			queue.add(tmp);
			num = num / 10;
		}
		int exp = 0;
		long sum = 0;
		while (!queue.isEmpty()) {
			int tmp = queue.poll();
			if (set.contains(tmp)) {
				continue;
			} else {
				stack.add(tmp);
				set.add(tmp);
			}
		}
		while (!stack.isEmpty()) {
			int tmp = stack.pop();
			sum += Math.pow(10, exp) * tmp;
			exp++;
		}
		System.out.print(sum);
	}

	@Test
	public void test() {
		int num = 2752771;
		Stack<Integer> stack = new Stack<Integer>();
		Set<Integer> set = new HashSet<Integer>();
		while (num > 0) {
			int tmp = num % 10;
			stack.push(tmp);
			num = num / 10;
		}
		int exp = 0;
		long sum = 0;
		while (!stack.isEmpty()) {
			int tmp = stack.pop();
			if (set.contains(tmp)) {
				continue;
			} else {
				set.add(tmp);
				sum += Math.pow(10, exp) * tmp;
				exp++;
			}
		}
		System.out.print(sum);
	}
}
