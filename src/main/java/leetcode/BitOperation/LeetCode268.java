package leetcode.BitOperation;

import java.util.Arrays;

public class LeetCode268 {

    //268. 丢失的数字 自己的--》低效
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        if (nums[0] != 0) {
            return 0;
        }
        if (nums[nums.length - 1] == (nums.length - 1)) {
            return nums.length;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] - nums[i] != 1) {
                return i + 1;
            }
        }
        return -1;
    }

    //别人的 位运算 高效
    public int missingNumber2(int[] nums) {
        int target = nums.length;
        for (int i = 0; i < nums.length; i++) {
            target ^= (i ^ nums[i]);
        }
        return target;
    }

    //别人的 求和公式 高效
    public int missingNumber3(int[] nums) {
        int target = nums.length * (nums.length + 1) >> 2;
        for (int i = 0; i < nums.length; i++) {
            target -= nums[i];
        }
        return target;
    }
}
