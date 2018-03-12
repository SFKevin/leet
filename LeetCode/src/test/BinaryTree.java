package test;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
	private Node root;

	public BinaryTree() {
		root = null;
	}

	public void insert(int data) {
		Node newNode = new Node(data);
		if (root == null) {
			root = newNode;
		} else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				if (data < current.data) {
					current = current.left;
					if (current == null) {
						parent.left = newNode;
						return;
					}
				} else {
					current = current.right;
					if (current == null) {
						parent.right = newNode;
						return;
					}
				}
			}
		}
	}

	public void buildTree(int[] data) {
		for (int i = 0; i < data.length; i++) {
			insert(data[i]);
		}
	}

	public void inOrder(Node localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.left);
			System.out.println(localRoot.data);
			inOrder(localRoot.right);
		}
	}

	public void preOrder(Node localRoot) {
		if (localRoot != null) {
			System.out.println(localRoot.data);
			preOrder(localRoot.left);
			preOrder(localRoot.right);
		}
	}

	public void postOrder(Node locaNode) {
		if (locaNode != null) {
			postOrder(locaNode.left);
			postOrder(locaNode.right);
			System.out.println(locaNode.data);
		}
	}

	public void layerTranverse() {
		if (this.root == null) {
			return;
		}
		Queue<Node> q = new LinkedList<Node>();
		q.add(this.root);
		while (!q.isEmpty()) {
			Node node = q.poll();
			System.out.println(node.data);
			if (node.left != null) {
				q.add(node.left);
			}
			if (node.right != null) {
				q.add(node.right);
			}

		}
	}

	public Node initTree(int[] preOrder, int start1, int end1, int[] inOrder,
			int start2, int end2) {
		if (start1 > end1 || start2 > end2) {
			return null;
		}
		int rootData = preOrder[start1];
		Node head = new Node(rootData);
		int rootIndex = findIndexInArray(inOrder, rootData, start2, end2);
		int offSet = rootIndex - start2 - 1;
		Node left = initTree(preOrder, start1 + 1, start1 + 1 + offSet,
				inOrder, start2, start2 + offSet);
		Node right = initTree(preOrder, start1 + offSet + 2, end1, inOrder,
				rootIndex + 1, end2);
		head.left = left;
		head.right = right;
		return head;

	}

	private int findIndexInArray(int[] a, int x, int start, int end) {
		for (int i = start; i <= end; i++) {
			if (a[i] == x) {
				return i;
			}
		}
		return -1;
	}

	private int maxLen = 0;

	public void findMaxDistance(Node root) {
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
			root.leftMaxDistance = Math.max(root.left.leftMaxDistance,
					root.left.rightMaxDistance) + 1;
		}
		if (root.right != null) {
			root.rightMaxDistance = Math.max(root.right.rightMaxDistance,
					root.right.leftMaxDistance) + 1;
		}
		if (root.leftMaxDistance + root.rightMaxDistance > maxLen) {
			maxLen = root.leftMaxDistance + root.rightMaxDistance;
		}
	}
}
