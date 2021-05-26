package leetcode.DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoublePointerLeetCode {


    //15. 三数之和
    //给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
    //注意：答案中不可以包含重复的三元组。
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3){
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0){
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int L = i + 1;
            int R = nums.length - 1;
            while (L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if (sum < 0){
                    ++L;
                }else if (sum > 0){
                    --R;
                }else {
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) {
                        L++; // 去重
                    }
                    while (L<R && nums[R] == nums[R-1]) {
                        R--; // 去重
                    }
                    ++L;
                    --R;
                }
            }
        }
        return ans;
    }

    //27. 移除元素
    //给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
    //不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
    //元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        for (; right >= 0; right--) {
            if (nums[right] != val){
                break;
            }
        }

        while (left < right){
            if (nums[left] == val){
                nums[left] ^= nums[right];
                nums[right] ^= nums[left];
                nums[left] ^= nums[right];
                --right;
                for (; right >= 0; right--) {
                    if (nums[right] != val){
                        break;
                    }
                }
            }
            ++left;
        }

        return nums[left] == val ? left : left + 1;
    }

    //88. 合并两个有序数组
    //给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
    //初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，
    // 这样它就有足够的空间保存来自 nums2 的元素。
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0){
            return;
        }
        int left = 0;
        int right = m;
        for (int temp : nums2) {
            for (int j = left; j <= right; j++) {
                if (j == right){
                    nums1[right] = temp;
                    ++left;
                    ++right;
                    break;
                }
                if (nums1[j] > temp) {
                    System.arraycopy(nums1, j, nums1, j + 1, right - j);
                    nums1[j] = temp;
                    left = j+1;
                    ++right;
                    break;
                }
            }
        }
    }

    //125. 验证回文串
    //给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
    //说明：本题中，我们将空字符串定义为有效的回文串。
    public static boolean isPalindrome(String s) {
        if (s.length() < 2){
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right){
            if (!judgeChar(s.charAt(left))){
                ++left;
                continue;
            }
            if (!judgeChar(s.charAt(right))){
                --right;
                continue;
            }
            int tempL = s.charAt(left) > 90 ? s.charAt(left) - 32 : s.charAt(left);
            int tempR = s.charAt(right) > 90 ? s.charAt(right) - 32 : s.charAt(right);
            if (tempL != tempR){
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    public static boolean judgeChar(char c){
        return (c >= 97 && c <= 122) || (c >= 48 && c <= 57) || (c >= 65 && c <= 90);
    }

    //283. 移动零
    //给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    public static void moveZeroes(int[] nums) {
        if (nums.length < 2){
            return;
        }
        int slow = 0;
        int fast = 0;
        while (fast < nums.length){
            if (nums[slow] == 0 && nums[fast] !=0){
                nums[slow] ^= nums[fast];
                nums[fast] ^= nums[slow];
                nums[slow] ^= nums[fast];
                ++slow;
            }
            if (nums[slow] != 0 && nums[fast] !=0){
                ++slow;
            }
            ++fast;
        }
    }
}
