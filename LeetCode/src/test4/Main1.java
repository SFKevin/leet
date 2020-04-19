package test4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			int n = scanner.nextInt();
			int[] I = new int[n];
			for (int i = 0; i < n; i++) {
				I[i] = scanner.nextInt();
			}
			int m = scanner.nextInt();
			Set<Integer> set = new HashSet<Integer>();
			for (int i = 0; i < m; i++) {
				set.add(scanner.nextInt());
			}
			List<Integer> list = new ArrayList<Integer>(set);
			Collections.sort(list);
			List<Map<Integer, Integer>> list_map = new ArrayList<Map<Integer, Integer>>();
			for (int num : list) {
				Map<Integer, Integer> map = new HashMap<Integer, Integer>();
				for (int i = 0; i < n; i++) {
					if (String.valueOf(I[i]).contains(String.valueOf(num))) {
						map.put(i, I[i]);
					}
				}
				list_map.add(map);
			}
			List<Integer> answer = new ArrayList<Integer>();
			for (int i = 0; i < list.size(); i++) {
				if (list_map.get(i).size() == 0) {
					continue;
				}
				answer.add(list.get(i));
				answer.add(list_map.get(i).size());
				for (Map.Entry<Integer, Integer> entry : list_map.get(i).entrySet()) {
					answer.add(entry.getKey());
					answer.add(entry.getValue());
				}
			}
			System.out.print(answer.size() + " ");
			for (int i = 0; i < answer.size(); i++) {
				System.out.print(answer.get(i) + " ");
			}
		}
	}
}
