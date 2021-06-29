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

    //我自己的
    //41. 缺失的第一个正数
    //给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
    //请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == res){
                res ++;
            }else if (nums[i] > res){
                return res;
            }
        }
        return res;
    }

    //大佬的 哈希表思路
    //41. 缺失的第一个正数
    //给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
    //请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
    public static int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static void main(String[] args) {
        firstMissingPositive2(new int[]{3,4,-1,1});
    }

    //自己写的  很慢
    //73. 矩阵置零
    //给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
    //进阶：
    //一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
    //一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
    //你能想出一个仅使用常量空间的解决方案吗？
    public void setZeroes(int[][] matrix) {
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0){
                    colSet.add(j);
                    rowSet.add(i);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (rowSet.contains(i) || colSet.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    //大佬的思路
    //73. 矩阵置零
    //给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
    //进阶：
    //一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
    //一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
    //你能想出一个仅使用常量空间的解决方案吗？
    public void setZeroes2(int[][] matrix) {
        boolean col0_flag = false;
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                col0_flag = true;
            }
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0_flag) {
                matrix[i][0] = 0;
            }
        }
    }

    //154. 寻找旋转排序数组中的最小值 II
    //已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
    //若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
    //若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
    //注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
    //给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
    public int findMin(int[] nums) {
        if (nums.length == 1 || nums[0] < nums[nums.length - 1]){
            return nums[0];
        }
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] < nums[i - 1]){
                return nums[i];
            }
        }
        return Math.max(nums[1],nums[0]);
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

    //自己写的 超时了
    //327. 区间和的个数
    //给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
    //区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
    public int countRangeSum(int[] nums, int lower, int upper) {
        int res = 0;
        long [] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = nums[i] + sum[i];
        }
        for (int i = 0; i < sum.length - 1; i++) {
            for (int j = i + 1; j < sum.length; j++) {
                if (sum[j] - sum[i] >= lower && sum[j] - sum[i] <= upper){
                    ++res;
                }
            }
        }
        return res;
    }

    //329. 矩阵中的最长递增路径
    //给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
    //对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
    public static int longestIncreasingPath(int[][] matrix) {
        int res = 0;
        int[][] flag = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < flag.length; i++) {
            Arrays.fill(flag[i],-1);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res = Math.max(longestIncreasingPath(flag,matrix,i,j),res);
            }
        }
        return res;
    }

    public static int longestIncreasingPath(int[][] flag, int[][] matrix, int i, int j) {
        if (matrix[i][j] == -1){
            return 0;
        }
        if (flag[i][j] > 0){
            return flag[i][j];
        }
        int temp = matrix[i][j];

        //标志当前数已经被使用过了
        matrix[i][j] = -1;

        int index = 1;

        //往右边试探
        if (i + 1 < matrix.length && matrix[i + 1][j] > temp){
            index = Math.max(longestIncreasingPath(flag,matrix,i + 1,j) + 1,index);
        }

        //往左边试探
        if (i - 1 >= 0 && matrix[i - 1][j] > temp){
            index = Math.max(longestIncreasingPath(flag,matrix,i - 1,j) + 1,index);
        }

        //往上边试探
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > temp){
            index = Math.max(longestIncreasingPath(flag,matrix,i,j + 1) + 1,index);
        }

        //往下边试探
        if (j - 1 >= 0 && matrix[i][j - 1] > temp){
            index = Math.max(longestIncreasingPath(flag,matrix,i,j - 1) + 1,index);
        }

        matrix[i][j] = temp;
        return flag[i][j] = index;
    }

//    public static void main(String[] args) {
//        longestIncreasingPath(new int[][]{{9,9,4},{6,6,8},{2,1,1}});
//    }

    //540. 有序数组中的单一元素
    //给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        for (int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i] != nums[i + 1]){
                return nums[i];
            }
        }
        return nums[nums.length - 1];
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