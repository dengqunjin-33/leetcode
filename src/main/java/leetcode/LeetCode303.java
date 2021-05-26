package leetcode;

//303. 区域和检索 - 数组不可变
public class LeetCode303 {
    private int[] nums;
    private int[] sums;

    public LeetCode303(int[] nums) {
        this.nums = nums;
        this.sums = new int[nums.length];
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp += nums[i];
            sums[i] = temp;
        }
    }

    public int sumRange(int i, int j) {
        if (nums.length == 0) {
            return 0;
        }
        if (i > 0) {
            return sums[j] - sums[i - 1];
        } else {
            return sums[j];
        }
    }

    public static void main(String[] args) {
        int[] x = {};
    }
}
