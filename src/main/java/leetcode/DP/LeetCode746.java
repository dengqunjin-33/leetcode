package leetcode.DP;

public class LeetCode746 {

    //746. 使用最小花费爬楼梯
    public static int minCostClimbingStairs(int[] cost) {
        if (cost.length < 2){
            return 0;
        }
        if (cost.length < 3){
            return Math.min(cost[0],cost[1]);
        }
        for (int i = 2; i < cost.length; i++) {
            cost[i] += Math.min(cost[i - 1], cost[i - 2]);
        }
        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }

    public static void main(String[] args) {
        int [] cost = {0,1,1,0};
        minCostClimbingStairs(cost);
    }

}
