package com.skf.shuzu;

public class SecondMax {
	public static int FindSecMax(int data[]) {
		int len = data.length;
		int maxnumber = data[0];
		int secmax = Integer.MIN_VALUE;
		for (int i = 1; i < len; i++) {
			if (data[i] > maxnumber) {
				secmax = maxnumber;
				maxnumber = data[i];
			} else {
				if (data[i] > secmax) {
					secmax = data[i];
				}
			}
		}
		return secmax;
	}
}
