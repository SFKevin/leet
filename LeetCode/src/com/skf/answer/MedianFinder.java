package com.skf.answer;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 295. Find Median from Data Stream Median is the middle value in an
 * ordered integer list. If the size of the list is even, there is no middle
 * value. So the median is the mean of the two middle value. Examples:
 * 
 * [2,3,4] , the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Design a data structure that supports the following two operations:
 * 
 * void addNum(int num) - Add a integer number from the data stream to the
 * data structure. double findMedian() - Return the median of all elements
 * so far.
 */
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder(); obj.addNum(num); double param_2 =
 * obj.findMedian();
 */
public class MedianFinder {
	private Queue<Long> small = new PriorityQueue<Long>();
	private Queue<Long> large = new PriorityQueue<Long>();

	public void addNum(int num) {
		large.add((long) num);
		small.add(-large.poll());
		if (large.size() < small.size()) {
			large.add(-small.poll());
		}
	}

	public double findMedian() {
		return large.size() > small.size() ? large.peek()
				: (large.peek() - small.peek()) / 2.0;
	}
}
