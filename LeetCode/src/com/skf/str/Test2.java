package com.skf.str;

import java.util.HashSet;
import java.util.Set;

public class Test2 {
	public static int wordCount(String s) {
		int word = 0;
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				word = 0;
			} else if (word == 0) {
				word += 1;
				count += 1;
			}
		}
		return count;

	}

	private int[] numbers = new int[] { 1, 2, 2, 3, 4, 5 };
	private int n = numbers.length;
	private boolean[] visited = new boolean[n];
	private int[][] graph = new int[n][n];
	private String combination = "";

	public Set<String> getAllCombinations() {
		buildGraph();
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < n; i++) {
			this.depthFirstSearch(i, set);
		}
		return set;

	}

	private void depthFirstSearch(int start, Set<String> set) {
		visited[start] = true;
		combination = combination + numbers[start];
		if (combination.length() == n) {
			if (combination.indexOf("4") != 2) {
				set.add(combination);
			}
		}
		for (int j = 0; j < n; j++) {
			if (graph[start][j] == 1 && visited[j] == false) {
				depthFirstSearch(j, set);
			}
		}
		combination = combination.substring(0, combination.length() - 1);
		visited[start] = false;
	}

	private void buildGraph() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					graph[i][j] = 0;
				} else {
					graph[i][j] = 1;
				}
			}
		}
		graph[3][5] = 0;
		graph[5][3] = 0;
	}

}
