package com.skf.huawei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class Main6 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			list.add(scanner.nextLine());
		}
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				char[] o1s = o1.toCharArray();
				char[] o2s = o2.toCharArray();
				int len1 = o1s.length;
				int len2 = o2s.length;
				int i = 0;
				for (i = 0; i < len1 && i < len2; i++) {
					if (o1s[i] != o2s[i]) {
						return o1s[i] - o2s[i];
					}
				}
				if (i == len1 && i == len2) {
					return 0;
				} else if (i == len1) {
					return 1;
				} else if (i == len2) {
					return -1;
				}
				return 0;
			}
		});
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	@Test
	public void test() {
		List<String> list = new ArrayList<String>();
		list.add("cap");
		list.add("to");
		list.add("cat");
		list.add("card");
		list.add("two");
		list.add("too");
		list.add("up");
		list.add("boat");
		list.add("boot");
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
