package com.skf.huawei;

import java.util.Scanner;

public class Main5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String sent = scanner.nextLine();
		String[] array = sent.split(" ");
		int len = array.length;
		int start = 0;
		int end = len - 1;
		while (start < end) {
			String tmp = array[start];
			array[start] = array[end];
			array[end] = tmp;
			start++;
			end--;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			sb.append(array[i]);
			if (i != len - 1) {
				sb.append(" ");
			}
		}
		System.out.println(sb.toString());
	}

}
