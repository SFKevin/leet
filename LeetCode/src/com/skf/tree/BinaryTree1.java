package com.skf.tree;

public class BinaryTree1 {
	private int maxLen = 0;

	private int max(int a, int b) {
		return a > b ? a : b;
	}

	public void findMaxDistance(Node1 root) {
		if (root == null) {
			return;
		}
		if (root.left == null) {
			root.leftMaxDistance = 0;
		}
		if (root.right == null) {
			root.rightMaxDistance = 0;
		}
		if (root.left != null) {
			findMaxDistance(root.left);
		}
		if (root.right != null) {
			findMaxDistance(root.right);
		}
		if (root.left != null) {
			root.leftMaxDistance = max(root.left.leftMaxDistance,
					root.left.rightMaxDistance) + 1;
		}
		if (root.right != null) {
			root.rightMaxDistance = max(root.right.rightMaxDistance,
					root.right.leftMaxDistance) + 1;
		}
		if (root.leftMaxDistance + root.rightMaxDistance > maxLen) {
			maxLen = root.leftMaxDistance + root.rightMaxDistance;
		}
	}
}
