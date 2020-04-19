package com.skf.niuke;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		char[] arrays = str.toCharArray();
		int max = Integer.MIN_VALUE;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int start = 1;
		for (int i = 1; i <= arrays.length; i++) {
			char temp = arrays[i - 1];
			if (map.containsKey(temp) && map.get(temp) >= start) {
				start = map.get(temp) + 1;
				map.put(temp, i);
			} else {
				map.put(temp, i);
				max = Math.max(max, i - start + 1);
			}
		}
		System.out.println(max);
	}
}
