package leetcode.Arrays;

import java.util.Arrays;
import java.util.Random;

public class LeetCode528 {

    //执行用时：23 ms, 在所有 Java 提交中击败了99.11%的用户
    //内存消耗：43.4 MB, 在所有 Java 提交中击败了30.45%的用户
    //528. 按权重随机选择
    //给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写一个函数 pickIndex ，它可以随机地获取下标 i，选取下标 i 的概率与 w[i] 成正比。
    //例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
    //也就是说，选取下标 i 的概率为 w[i] / sum(w) 。
    class Solution {

        private Random random = new Random();

        int total;
        int[] w;
        int len;
        public Solution(int[] w) {
            len = w.length;
            for (int i = 1; i < len; i++) {
                w[i] += w[i - 1];
            }
            this.w = w;
            total = w[len - 1];
        }

        public int pickIndex() {
            int rand = random.nextInt(total) + 1;
            int left = 0;
            int right = len - 1;
            while (left < right){
                int mid = (left + right) >> 1;
                if (w[mid] == rand){
                    return mid;
                }else if (w[mid] > rand){
                    right = mid;
                }else {
                    left = mid + 1;
                }
            }
            return right;
        }
    }
}
