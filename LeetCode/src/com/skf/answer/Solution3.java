package com.skf.answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Solution3 {
	/**
	 * Given n, how many structurally unique BST's (binary search trees) that
	 * store values 1...n?
	 * 
	 * @param n
	 * @return
	 */
	public int numTrees(int n) {
		int[] G = new int[n + 1];
		G[0] = G[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				G[i] += G[j - 1] * G[i - j];
			}
		}
		return G[n];
	}

	/**
	 * 
	 113. Path Sum II
	 * 
	 * Given a binary tree and a sum, find all root-to-leaf paths where each
	 * path's sum equals the given sum. For example: Given the below binary tree
	 * and sum = 22,
	 * 
	 * 5 / \ 4 8 / / \ 11 13 4 / \ / \ 7 2 5 1
	 * 
	 * return
	 * 
	 * [ [5,4,11,2], [5,8,4,5] ]
	 * 
	 * 
	 * @param root
	 * @param sum
	 * @return
	 */
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> answer = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return answer;
		} else {
			list.add(root.val);
			if (sum == root.val && root.left == null && root.right == null) {
				answer.add(list);
				return answer;
			} else {
				sum = sum - root.val;
			}
		}
		findPath(0, root, sum, answer, list);
		return answer;
	}

	private void findPath(int temp, TreeNode root, int sum,
			List<List<Integer>> answer, List<Integer> list) {
		if (root.left == null && root.right == null && sum == temp) {
			answer.add(new ArrayList<Integer>(list));
			return;
		} else {
			if (root.left != null) {
				list.add(root.left.val);
				temp += root.left.val;
				findPath(temp, root.left, sum, answer, list);
				list.remove(list.size() - 1);
				temp -= root.left.val;
			}
			if (root.right != null) {
				list.add(root.right.val);
				temp += root.right.val;
				findPath(temp, root.right, sum, answer, list);
				list.remove(list.size() - 1);
				temp -= root.right.val;
			}
		}
	}

	/**
	 * 501. Find Mode in Binary Search Tree Given a binary search tree (BST)
	 * with duplicates, find all the mode(s) (the most frequently occurred
	 * element) in the given BST.
	 * 
	 * Assume a BST is defined as follows:
	 * 
	 * The left subtree of a node contains only nodes with keys less than or
	 * equal to the node's key. The right subtree of a node contains only nodes
	 * with keys greater than or equal to the node's key. Both the left and
	 * right subtrees must also be binary search trees.
	 * 
	 * For example: Given BST [1,null,2,2],
	 * 
	 * 1 \ 2 / 2
	 * 
	 * return [2].
	 * 
	 * Note: If a tree has more than one mode, you can return them in any order.
	 * 
	 * Follow up: Could you do that without using any extra space? (Assume that
	 * the implicit stack space incurred due to recursion does not count).
	 * 
	 * 
	 * @param root
	 * @return
	 */
	public int[] findMode(TreeNode root) {
		if (root == null) {
			return new int[0];
		}
		List<Integer> list = new ArrayList<Integer>();
		find(root, list);
		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); ++i)
			res[i] = list.get(i);
		return res;

	}

	int max = 0;
	Integer current = null;
	int count = 1;

	private void find(TreeNode root, List<Integer> list) {
		if (root == null) {
			return;
		}
		find(root.left, list);
		if (current != null) {
			if (root.val == current) {
				count++;
			} else {
				count = 1;
			}
		}
		if (count > max) {
			max = count;
			list.clear();
			list.add(root.val);
		} else if (count == max) {
			list.add(root.val);
		}
		current = root.val;
		find(root.right, list);
	}

	/**
	 * 71. Simplify Path Given an absolute path for a file (Unix-style),
	 * simplify it.
	 * 
	 * For example, path = "/home/", => "/home" path = "/a/./b/../../c/", =>
	 * "/c"
	 * 
	 * @param path
	 * @return
	 */
	public String simplifyPath(String path) {
		if (path == null) {
			return null;
		}
		Deque<String> stack = new LinkedList<String>();
		Set<String> skip = new HashSet<String>(Arrays.asList("..", ".", ""));
		for (String dir : path.split("/")) {
			if (dir.equals("..") && !stack.isEmpty()) {
				stack.pop();
			} else if (!skip.contains(dir)) {
				stack.push(dir);
			}
		}
		String res = "";
		for (String dir1 : stack)
			res = "/" + dir1 + res;
		return res.isEmpty() ? "/" : res;
	}

	/**
	 * 421. Maximum XOR of Two Numbers in an Array
	 * 
	 * @param nums
	 * @return
	 */
	public int findMaximumXOR(int[] nums) {
		int max = 0, mask = 0;
		for (int i = 31; i >= 0; i--) {
			mask = mask | (1 << i);
			Set<Integer> set = new HashSet<>();
			for (int num : nums) {
				set.add(num & mask);
			}
			int tmp = max | (1 << i);
			for (int prefix : set) {
				if (set.contains(tmp ^ prefix)) {
					max = tmp;
					break;
				}
			}
		}
		return max;
	}

	/**
	 * 648. Replace Words
	 * 
	 * @param dict
	 * @param sentence
	 * @return
	 */
	private String replaceWords(String[] tokens, TrieNode root) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String token : tokens) {
			stringBuilder.append(getShortestReplacement(token, root));
			stringBuilder.append(" ");
		}
		return stringBuilder.substring(0, stringBuilder.length() - 1);
	}

	private String getShortestReplacement(String token, final TrieNode root) {
		TrieNode temp = root;
		StringBuilder stringBuilder = new StringBuilder();
		for (char c : token.toCharArray()) {
			stringBuilder.append(c);
			if (temp.children[c - 'a'] != null) {
				if (temp.children[c - 'a'].isWord) {
					return stringBuilder.toString();
				}
				temp = temp.children[c - 'a'];
			} else {
				return token;
			}
		}
		return token;
	}

	private TrieNode buildTrie(List<String> dict) {
		TrieNode root = new TrieNode(' ');
		for (String word : dict) {
			TrieNode temp = root;
			for (char c : word.toCharArray()) {
				if (temp.children[c - 'a'] == null) {
					temp.children[c - 'a'] = new TrieNode(c);
				}
				temp = temp.children[c - 'a'];
			}
			temp.isWord = true;
		}
		return root;
	}

	public String replaceWords(List<String> dict, String sentence) {

		String[] tokens = sentence.split(" ");
		TrieNode trie = buildTrie(dict);
		return replaceWords(tokens, trie);
	}

	/**
	 * 167. Two Sum II - Input array is sorted
	 * 
	 * @param numbers
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] numbers, int target) {
		int len = numbers.length;
		int[] answer = new int[2];
		if (len <= 1) {
			return answer;
		}
		int start = 0;
		int end = len - 1;
		while (start < end) {
			if (numbers[start] + numbers[end] < target) {
				start++;
			} else if (numbers[start] + numbers[end] > target) {
				end--;
			} else {
				answer[0] = start + 1;
				answer[1] = end + 1;
				break;
			}
		}
		return answer;
	}

	/**
	 * 491. Increasing Subsequences
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> findSubsequences(int[] nums) {
		Set<List<Integer>> reSet = new HashSet<List<Integer>>();
		List<Integer> holder = new ArrayList<Integer>();
		findSeq(reSet, holder, 0, nums);
		List reList = new ArrayList(reSet);
		return reList;
	}

	private void findSeq(Set<List<Integer>> reSet, List<Integer> holder,
			int index, int[] nums) {
		if (holder.size() >= 2) {
			reSet.add(new ArrayList(holder));
		}
		for (int i = index; i < nums.length; i++) {
			if (holder.size() == 0 || holder.get(holder.size() - 1) <= nums[i]) {
				holder.add(nums[i]);
				findSeq(reSet, holder, i + 1, nums);
				holder.remove(holder.size() - 1);
			}
		}
	}

	/**
	 * 107. Binary Tree Level Order Traversal II Given a binary tree, return the
	 * bottom-up level order traversal of its nodes' values. (ie, from left to
	 * right, level by level from leaf to root).
	 * 
	 * @param root
	 * @return
	 */
	// public List<List<Integer>> levelOrderBottom(TreeNode root) {
	// List<List<Integer>> answerList = new ArrayList<List<Integer>>();
	// List<Integer> pathIntegers = new ArrayList<Integer>();
	// if (root == null) {
	// return answerList;
	// }
	// Queue<TreeNode> bfsQueue = new LinkedList<TreeNode>();
	// bfsQueue.add(root);
	// while (!bfsQueue.isEmpty()) {
	// root = bfsQueue.poll();
	// pathIntegers.add(root.val);
	// if (root.left != null) {
	// bfsQueue.add(root.left);
	// }
	// if (root.right != null) {
	// bfsQueue.add(root.right);
	// }
	// }
	// for (int i = pathIntegers.size() - 1; i > 0; i -= 2) {
	// answerList.add(Arrays.asList(pathIntegers.get(i - 1),
	// pathIntegers.get(i)));
	// }
	// if (pathIntegers.size() % 2 != 0) {
	// answerList.add(Arrays.asList(pathIntegers.get(0)));
	// }
	// return answerList;
	// }
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> answer = new ArrayList<List<Integer>>();
		levelMaker(answer, root, 0);
		return answer;
	}

	private void levelMaker(List<List<Integer>> answer, TreeNode root, int level) {
		if (root == null) {
			return;
		}
		if (level >= answer.size()) {
			answer.add(0, new LinkedList<Integer>());
		}
		levelMaker(answer, root.left, level + 1);
		levelMaker(answer, root.right, level + 1);
		answer.get(answer.size() - level - 1).add(root.val);
	}
}
