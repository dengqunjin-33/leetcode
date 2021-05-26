package leetcode;

public class LeetCode376 {

    //376. 摆动序列
    public static int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        boolean firstFlag = true;
        int first = 1;

        boolean secondFlag = true;
        int second = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            int temp = nums[i] - nums[i + 1];
            if (firstFlag && temp > 0) {
                ++first;
                firstFlag = false;
            } else if (!firstFlag && temp < 0) {
                ++first;
                firstFlag = true;
            }

            if (secondFlag && temp < 0) {
                ++second;
                secondFlag = false;
            } else if (!secondFlag && temp > 0) {
                ++second;
                secondFlag = true;
            }
        }
        return Math.max(first, second);
    }

    public static void main(String[] args) {
        int[] x = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        wiggleMaxLength(x);
    }

}
