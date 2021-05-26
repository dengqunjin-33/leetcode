package leetcode;

public class LeetCode747 {

    //747. 至少是其他数字两倍的最大数
    public int dominantIndex(int[] nums) {
        int [] target = {-1,-1,-1};
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target[0]){
                target[1] = target[0];
                target[0] = nums[i];
                target[2] = i;
            } else if (nums[i] >= target[1]){
                target[1] = nums[i];
            }
        }
        return target[0] >= 2 * target[1] ? target[2] : -1;
    }
}
