package com.skf.huawei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main2 {
	// public static void main(String[] args) {
	// Scanner scanner = new Scanner(System.in);
	// double input = scanner.nextDouble();
	// int temp = (int) input;
	// double frac = 10 * (input - temp);
	// int ret = frac < 5 ? temp : temp + 1;
	// System.out.println(ret);
	// }
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			int key = scanner.nextInt();
			int value = scanner.nextInt();
			if (map.containsKey(key)) {
				map.put(key, map.get(key) + value);
			} else {
				map.put(key, value);
			}
		}
		List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {

			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				int tmp1 = o1.getKey();
				int tmp2 = o2.getKey();
				return tmp1 - tmp2;
			}
		});
		for (Map.Entry<Integer, Integer> entry : list) {
			System.out.print(entry.getKey() + " ");
			System.out.println(entry.getValue());
		}
	}
}
