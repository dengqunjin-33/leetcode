package leetcode;

public class LeetCode198 {

    //198. 打家劫舍
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int first = nums[0];
        int second = Math.max(nums[1], first);
        for (int i = 2; i < nums.length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    //198. 打家劫舍
    public int rob2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] temp = new int[nums.length];
        temp[0] = nums[0];
        temp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < nums.length; i++) {
            temp[i] = Math.max(nums[i] + temp[i - 2], temp[i - 1]);
        }
        return temp[nums.length - 1];
    }
}
