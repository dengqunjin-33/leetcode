package leetcode.Greedy;

public class LeetCode122 {
    //122. 买卖股票的最佳时机 II
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }

        int sum = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                sum += prices[i + 1] - prices[i];
            }
        }

        return sum;
    }
}
