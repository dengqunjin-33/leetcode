package leetcode.DP;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LeetCodeDp {

    //119. 杨辉三角 II
    //给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
    //在杨辉三角中，每个数是它左上方和右上方的数的和。
    public static List<Integer> getRow(int rowIndex) {
        int[] res = new int[rowIndex + 1];
        res[0] = 1;
        for (int i = 1; i <= rowIndex; ++i) {
            for (int j = i; j > 0; j--) {
                res[j] += res[j-1];
            }
        }
        return Arrays.stream(res).boxed().collect(Collectors.toList());
    }

    //自己写
    //120. 三角形最小路径和
    //给定一个三角形 triangle ，找出自顶向下的最小路径和。
    //每一步只能移动到下一行中相邻的结点上。
    //相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
    //也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        for (int i = size - 2; i >= 0; i--) {
            List<Integer> cur = triangle.get(i);
            List<Integer> pre = triangle.get(i + 1);
            for (int j = 0; j < cur.size(); j++) {
                Integer ij = cur.get(j);
                Integer ijMin = Math.min(ij + pre.get(j),ij + pre.get(j + 1));
                cur.set(j,ijMin);
            }
        }
        return triangle.get(0).get(0);
    }

    //大佬的思路
    //120. 三角形最小路径和
    //给定一个三角形 triangle ，找出自顶向下的最小路径和。
    //每一步只能移动到下一行中相邻的结点上。
    //相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
    //也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];//使用一个数组，妙啊
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    //198. 打家劫舍
    //你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
    //如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    //给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
    public int rob(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        if (nums.length == 2){
            return Math.max(nums[0],nums[1]);
        }
        int first = nums[0];
        int secend = Math.max(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = Math.max(nums[i] + first,secend);
            first = secend;
            secend = temp;
        }
        return secend;
    }

    //377. 组合总和 Ⅳ
    //给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
    //题目数据保证答案符合 32 位整数范围。
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    //413. 等差数列划分
    //如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3){
            return 0;
        }
        int[] dp = new int[nums.length];
        int sum = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]){
                dp[i] = dp[i - 1] + 1;
            }
            sum += dp[i];
        }
        return sum;
    }


    //518. 零钱兑换 II
    //给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。SSS
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
