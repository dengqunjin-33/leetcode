package leetcode;

public class LeetCode189 {

    //189. 旋转数组
    //给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
    //进阶：
    //尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
    //你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
    public void rotate(int[] nums, int k) {
        int offset = k % nums.length;
        reverse(nums,0,nums.length - 1);
        reverse(nums,0,offset - 1);
        reverse(nums,offset,nums.length - 1);
    }

    public void reverse(int[] nums,int start,int end){
        while (start < end){
            nums[start] ^= nums[end];
            nums[end] ^= nums[start];
            nums[start] ^= nums[end];
            start ++;
            end --;
        }
    }
}
