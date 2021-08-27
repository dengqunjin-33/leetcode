package leetcode.maths;

import java.util.Comparator;
import java.util.PriorityQueue;

//执行用时：88 ms, 在所有 Java 提交中击败了51.42%的用户
//内存消耗：58.6 MB, 在所有 Java 提交中击败了46.55%的用户
//295. 数据流的中位数
//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
//例如，
//[2,3,4] 的中位数是 3
//[2,3] 的中位数是 (2 + 3) / 2 = 2.5
//设计一个支持以下两种操作的数据结构：
//void addNum(int num) - 从数据流中添加一个整数到数据结构中。
//double findMedian() - 返回目前所有元素的中位数。
class MedianFinder {
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a,b) -> b - a);
        minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a));
    }

    public void addNum(int num) {
        if(minHeap.size() == 0 || minHeap.peek() <= num) {
            minHeap.add(num);
        } else {
            maxHeap.add(num);
        }
        if(minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.poll());
        } else if(maxHeap.size() - minHeap.size() > 1) {
            minHeap.add(maxHeap.poll());
        }
    }

    public double findMedian() {
        if (minHeap.isEmpty() && maxHeap.isEmpty()){
            return 0.00;
        }
        if(minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else if(minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        }
        return maxHeap.peek();
    }
}

