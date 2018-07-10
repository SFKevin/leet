package com.skf.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test2 {
	private Map<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		return clone(node);
	}

	private UndirectedGraphNode clone(UndirectedGraphNode node) {
		if (node == null) {
			return null;
		}
		if (map.containsKey(node.label)) {
			return map.get(node.label);
		}
		UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
		map.put(clone.label, clone);
		for (UndirectedGraphNode neghibor : node.neighbors) {
			clone.neighbors.add(clone(neghibor));
		}
		return clone;
	}

	public int minCut(String s) {
		int len = s.length();
		int[] dp = new int[len + 1];
		boolean[][] p = new boolean[len][len];
		dp[len] = -1;
		for (int i = len - 1; i >= 0; i--) {
			dp[i] = Integer.MAX_VALUE;
			for (int j = i; j < len; j++) {
				if (s.charAt(i) == s.charAt(j) && (j - i < 2 || p[i + 1][j - 1])) {
					p[i][j] = true;
					dp[i] = Math.min(dp[i], dp[j + 1] + 1);
				}
			}
		}
		return dp[0];
	}

	public ArrayList<ArrayList<String>> partition(String s) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		List<String> list = new ArrayList<String>();
		back(result, list, s);
		return result;
	}

	private void back(ArrayList<ArrayList<String>> result, List<String> list, String s) {
		if (s.length() == 0) {
			result.add(new ArrayList<String>(list));
			return;
		}
		for (int i = 1; i <= s.length(); i++) {
			if (!isPalindrome(s.substring(0, i))) {
				continue;
			}
			list.add(s.substring(0, i));
			back(result, list, s.substring(i));
			list.remove(list.size() - 1);
		}
	}

	private boolean isPalindrome(String s) {
		int i = 0, j = s.length() - 1;
		while (i < j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
}
