package leetcode.Greedy;

public class LeetCode121 {
    //121. 买卖股票的最佳时机
    public static int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int buy = prices[0];
        int sell = prices[1];
        int max = Math.max(sell - buy, 0);
        for (int i = 1; i < prices.length - 1; i++) {
            if (buy > prices[i]) {
                buy = prices[i];
                sell = prices[i + 1];
            } else {
                sell = Math.max(sell, prices[i + 1]);
            }
            max = Math.max(sell - buy, max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] prices = {3, 2, 6, 5, 0, 3};
        maxProfit(prices);
    }
}
