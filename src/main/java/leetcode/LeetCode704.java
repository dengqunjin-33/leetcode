package leetcode;

public class LeetCode704 {

    //704. 二分查找
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int min = (low + high) >> 1;
        for (;;){
            if (low > high){
                return -1;
            }
            if (target == nums[min]){
                return min;
            }else if (target > nums[min]){
                low = min + 1;
            }else {
                high = min - 1;
            }
            min = (low + high) >> 1;
        }
    }
}
