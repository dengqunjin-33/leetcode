package leetcode;

public class LeetCode490To499 {
    //493. 翻转对
    public int reversePairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > (long) nums[j] << 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
