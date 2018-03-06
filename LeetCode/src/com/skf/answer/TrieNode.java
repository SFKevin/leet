package com.skf.answer;

public class TrieNode {
	char val;
	TrieNode[] children;
	boolean isWord;

	public TrieNode(char val) {
		this.val = val;
		this.children = new TrieNode[26];
		this.isWord = false;
	}
}
