package leetcode.sumPrefix;

import java.util.HashMap;
import java.util.Map;

public class LeetCodeSumPrefix {

    //自己写的(超时)
    //523. 连续的子数组和
    //给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
    //子数组大小 至少为 2 ，且
    //子数组元素总和为 k 的倍数。
    //如果存在，返回 true ；否则，返回 false 。
    //如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
    public boolean checkSubarraySum(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int target = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                target = (target + nums[j]) % k;
                if (target % k == 0){
                    return true;
                }
            }
        }
        return false;
    }

    //大佬写的:前缀和
    //523. 连续的子数组和
    //给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
    //子数组大小 至少为 2 ，且
    //子数组元素总和为 k 的倍数。
    //如果存在，返回 true ；否则，返回 false 。
    //如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
    public static boolean checkSubarraySum2(int[] nums, int k) {
        int m = nums.length;
        if (m < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < m; i++) {
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)) {
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }

    //525. 连续数组
    //给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
    public static int findMaxLength(int[] nums) {
        if (nums.length < 2){
            return 0;
        }
        int res = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int count = 0;
        map.put(0,-1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1){
                count ++ ;
            }
            if (nums[i] == 0){
                count -- ;
            }
            if (map.containsKey(count)){
                Integer preIndex = map.get(count);
                res = Math.max(res,i - preIndex);
            }else {
                map.put(count,i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        findMaxLength(new int[]{1,0});
    }
}
