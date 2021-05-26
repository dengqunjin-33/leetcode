package leetcode;

public class LeetCode53 {
    //53. 最大子序和
    public int maxSubArray(int[] nums) {
        int target = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum < 0) {
                sum = 0;
                target = Math.max(target, nums[i]);
            } else {
                target = Math.max(target, sum);
            }
        }
        return target;
    }
}
