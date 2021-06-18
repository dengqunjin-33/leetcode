package leetcode.Arrays;

import java.util.*;

public class LeetCodeArrays {

    //1. 两数之和
    //给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
    //你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
    //你可以按任意顺序返回答案。
    public static int[] twoSum(int[] nums, int target) {
        int [] result = {-1,-1};
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    //4. 寻找两个正序数组的中位数
    //给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int[] target = new int[len];
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < len; i++) {
            if (index1 < nums1.length && index2 < nums2.length){
                if (nums1[index1] > nums2[index2]){
                    target[i] = nums2[index2];
                    index2++;
                }else {
                    target[i] = nums1[index1];
                    index1++;
                }
                continue;
            }
            if (index1 >= nums1.length){
                target[i] = nums2[index2];
                index2++;
                continue;
            }
            if (index2 >= nums2.length){
                target[i] = nums1[index1];
                index1++;
            }
        }
        int mid = len + 1 >> 1;
        return len % 2 == 1 ? target[mid - 1] : (target[mid - 1] + target[mid]) / 2.0;
    }

    //11. 盛最多水的容器
    //给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    //说明：你不能倾斜容器。
    public static int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int temp = (j - i) * Math.min(height[i],height[j]);
                max = Math.max(max,temp);
            }
        }
        return max;
    }

    //双指针
    //11. 盛最多水的容器
    //给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    //说明：你不能倾斜容器。
    public int maxArea1(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }

    //215. 数组中的第K个最大元素
    //在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
    public static int findKthLargest(int[] nums, int k) {
        if (k > nums.length){
            return -1;
        }
        sortQuick(nums,0,nums.length - 1);
        return nums[nums.length - k];
    }

    public static void sortQuick(int[] arr, int start, int end){
        if (start > end){
            return;
        }
        int i = start;
        int j = end;
        int temp = arr[start];
        while (i != j){
            while (temp <= arr[j] && i < j){
                j --;
            }
            while (temp >= arr[i] && i < j){
                i ++;
            }

            if (i < j){
                arr[i] ^= arr[j];
                arr[j] ^= arr[i];
                arr[i] ^= arr[j];
            }

        }
        arr[start] = arr[i];
        arr[i] = temp;
        sortQuick(arr,start,i - 1);
        sortQuick(arr,i + 1, end);
    }

    //560. 和为K的子数组
    //给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int pre = 0;
        Map<Integer,Integer> map = new HashMap();
        map.put(0,1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (map.containsKey(pre - k)){
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }


}
