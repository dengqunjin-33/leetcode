package leetcode.DP;

import java.util.ArrayList;
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
}
