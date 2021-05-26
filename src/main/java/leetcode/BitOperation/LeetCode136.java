package leetcode.BitOperation;

import java.util.Arrays;

public class LeetCode136 {
    //136. 只出现一次的数字 垃圾解法
    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    continue;
                }
                if (nums[i] != nums[j] && j - i == 1) {
                    return nums[i];
                }
                if (nums[i] != nums[j] && j - i > 1) {
                    i = j;
                    break;
                }
            }
        }
        return nums[nums.length - 1];
    }

    //136. 只出现一次的数字 高级解法
    public int singleNumber2(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }


    public static void main(String[] args) {
        int[] x = {4, 1, 2, 1, 2};
        singleNumber(x);
    }
}
