package com.skf.tree;

public class others {
	public static String change_str(String str) {
		String resultString = ")";
		char[] ch = str.toCharArray();
		int bracket_num = 0;
		int i = 0;
		while (i < ch.length) {
			if (ch[i] == '(') {
				bracket_num++;
			} else if (ch[i] == ')') {
				if (bracket_num > 0) {
					bracket_num--;
				} else {
					System.out.println("Expression wrong!");
					return null;
				}
			} else if (ch[i] == ',') {
				resultString += ch[i++];
				continue;
			} else if (ch[i] >= '0' && ch[i] <= '9') {
				resultString += ch[i];
			} else {
				System.out.println("Expression wrong!");
				return null;
			}
			i++;
		}
		resultString += ')';
		return resultString;
	}

	public static int maxl(int a, int b) {
		long la = (long) a;
		long lb = (long) b;
		return (int) (la + lb + Math.abs(la - lb)) / 2;
	}

	public static int minl(int a, int b) {
		long la = (long) a;
		long lb = (long) b;
		return (int) (la + lb - Math.abs(la - lb)) / 2;
	}
}
