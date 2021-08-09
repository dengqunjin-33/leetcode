package leetcode.Arrays;

import java.util.*;

/**
 * @author 86134
 */
public class LeetCode0to600 {
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

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：37.6 MB, 在所有 Java 提交中击败了90.32%的用户
    //33. 搜索旋转排序数组
    //整数数组 nums 按升序排列，数组中的值 互不相同 。
    //在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
    //给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
    public int search(int[] nums, int target) {
        for(int i = 0;i < nums.length; i++){
            if (target == nums[i]){
                return i;
            }
        }
        return -1;
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

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：38.7 MB, 在所有 Java 提交中击败了21.09%的用户
    //80. 删除有序数组中的重复项 II
    //给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
    //不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len < 3){
            return len;
        }
        int slow = 2;
        for (int i = 2 ; i < len; ++i){
            if (nums[i] != nums[slow - 2]){
                nums[slow] = nums[i];
                slow ++ ;
            }
        }
        return slow;
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

    //大佬的
    //218. 天际线问题
    //城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
    //每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
    //lefti 是第 i 座建筑物左边缘的 x 坐标。
    //righti 是第 i 座建筑物右边缘的 x 坐标。
    //heighti 是第 i 座建筑物的高度。
    //天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。
    //列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
    //注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；
    //三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
    public List<List<Integer>> getSkyline2(int[][] bs) {
        List<List<Integer>> ans = new ArrayList<>();

        // 预处理所有的点，为了方便排序，对于左端点，令高度为负；对于右端点令高度为正
        List<int[]> ps = new ArrayList<>();
        for (int[] b : bs) {
            int l = b[0], r = b[1], h = b[2];
            ps.add(new int[]{l, -h});
            ps.add(new int[]{r, h});
        }

        // 先按照横坐标进行排序
        // 如果横坐标相同，则按照左端点排序
        // 如果相同的左/右端点，则按照高度进行排序
        ps.sort((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        // 大根堆
        PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->b-a);
        int prev = 0;
        q.add(prev);
        for (int[] p : ps) {
            int point = p[0], height = p[1];
            if (height < 0) {
                // 如果是左端点，说明存在一条往右延伸的可记录的边，将高度存入优先队列
                q.add(-height);
            } else {
                // 如果是右端点，说明这条边结束了，将当前高度从队列中移除
                q.remove(height);
            }

            // 取出最高高度，如果当前不与前一矩形“上边”延展而来的那些边重合，则可以被记录
            int cur = q.peek();
            if (cur != prev) {
                List<Integer> list = new ArrayList<>();
                list.add(point);
                list.add(cur);
                ans.add(list);
                prev = cur;
            }
        }
        return ans;
    }

    //执行用时：3 ms, 在所有 Java 提交中击败了35.80%的用户
    //内存消耗：42.5 MB, 在所有 Java 提交中击败了27.10%的用户
    //229. 求众数 II
    //给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
    //进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
    public List<Integer> majorityElement(int[] nums) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        int len = nums.length / 3;
        int left = 0;
        int right = 0;
        while (right < nums.length){
            if (nums[left] != nums[right]){
                if (right - left > len){
                    res.add(nums[left]);
                }
                left = right;
            }
            ++right;
        }
        if (right - left > len){
            res.add(nums[left]);
        }
        return res;
    }

    //执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：49.2 MB, 在所有 Java 提交中击败了36.99%的用户
    //238. 除自身以外数组的乘积
    //给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int k = 1;
        for(int i = 0; i < res.length; i++){
            res[i] = k;
            k = k * nums[i]; // 此时数组存储的是除去当前元素左边的元素乘积
        }
        k = 1;
        for(int i = res.length - 1; i >= 0; i--){
            res[i] *= k; // k为该数右边的乘积。
            k *= nums[i]; // 此时数组等于左边的 * 该数右边的。
        }
        return res;
    }

    //使用list
    //执行用时：
    //348 ms, 在所有 Java 提交中击败了9.65%的用户
    //内存消耗：38.7 MB, 在所有 Java 提交中击败了29.40%的用户
    //260. 只出现一次的数字 III
    //给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
    //进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
    public int[] singleNumber(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int num : nums){
            if (!list.contains(num)){
                list.add(num);
            }else {
                list.remove(Integer.valueOf(num));
            }
        }
        int [] res = new int[2];
        res[0] = list.get(0);
        res[1] = list.get(1);
        return res;
    }

    //使用异或位运算
    //执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：38.6 MB, 在所有 Java 提交中击败了47.00%的用户
    //260. 只出现一次的数字 III
    //给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
    //进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
    public int[] singleNumber2(int[] nums) {
        int ret = 0;
        for (int n : nums) {
            ret ^= n;
        }
        int div = 1;
        while ((div & ret) == 0) {
            div <<= 1;
        }
        int a = 0, b = 0;
        for (int n : nums) {
            if ((div & n) != 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }

    //排序 + 二分法
    //执行用时：1 ms, 在所有 Java 提交中击败了85.63%的用户
    //内存消耗：36.2 MB, 在所有 Java 提交中击败了73.31%的用户
    //274. H 指数 I
    //给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
    //h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。且其余的 N - h 篇论文每篇被引用次数 不超过 h 次。
    //例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
    public int hIndexI1(int[] citations) {
        Arrays.sort(citations);
        int N = citations.length;
        int left = 0;
        int right = N - 1;
        while (left <= right){
            int mid = left + right >> 1;
            if (citations[mid] >= N - mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return N - left;
    }

    //直接二分法 优化
    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：36.3 MB, 在所有 Java 提交中击败了50.76%的用户
    //274. H 指数 I
    //给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
    //h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。且其余的 N - h 篇论文每篇被引用次数 不超过 h 次。
    //例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
    public int hIndexI2(int[] citations) {
        int n = citations.length;
        int l = 0, r = n;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(citations, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    private boolean check(int[] citations, int mid) {
        int ans = 0;
        for (int i : citations) {
            if (i >= mid && ++ans >= mid) {
                return true;
            }
        }
        return false;
    }

    //275. H 指数 II
    //给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照 升序排列 。编写一个方法，计算出研究者的 h 指数。
    //h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）"
    public int hIndex(int[] citations) {
        int N = citations.length;
        int left = 0;
        int right = N - 1;
        while (left <= right){
            int mid = left + right >> 1;
            if (citations[mid] >= N - mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return N - left;
    }

    //287. 寻找重复数
    //执行用时：1 ms, 在所有 Java 提交中击败了92.60%的用户
    //内存消耗：55.1 MB, 在所有 Java 提交中击败了29.68%的用户
    //给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
    //假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
    //你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
    public int findDuplicate(int[] nums) {
        boolean[] flag = new boolean[nums.length + 1];
        for (int num : nums) {
            if (flag[num]) {
                return num;
            } else {
                flag[num] = true;
            }
        }
        return -1;
    }

    //执行用时：
    //0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：36.8 MB, 在所有 Java 提交中击败了33.99%的用户
    //289. 生命游戏
    //根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
    //给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
    //如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
    //如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
    //如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
    //如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
    //下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。
    public static void gameOfLife(int[][] board) {
        boolean[][] flag = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int tempSum = sumSell(board,i - 1,j -1)
                        + sumSell(board,i - 1,j)
                        + sumSell(board,i - 1, j + 1)
                        + sumSell(board,i, j + 1)
                        + sumSell(board,i, j - 1)
                        + sumSell(board,i + 1, j + 1)
                        + sumSell(board,i + 1, j )
                        + sumSell(board,i + 1, j - 1);
                if (board[i][j] == 1){
                    flag[i][j] = tempSum == 2 || tempSum == 3;
                }else {
                    flag[i][j] = tempSum == 3;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = flag[i][j] ? 1 : 0;
            }
        }
    }

    private static int sumSell(int[][] board, int i, int j) {
        if (i < 0
                || i == board.length
                || j < 0
                || j == board[0].length
                || board[i][j] == 0){
            return 0;
        }else {
            return 1;
        }
    }

    //动态规划
    //执行用时：21 ms, 在所有 Java 提交中击败了61.57%的用户
    //内存消耗：36.2 MB, 在所有 Java 提交中击败了82.06%的用户
    //313. 超级丑数
    //超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
    //给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
    //题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
    public static int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int m = primes.length;
        int[] pointers = new int[m];
        int[] nums = new int[m];
        Arrays.fill(pointers, 1);
        for (int i = 2; i <= n; i++) {
            nums[0] = dp[pointers[0]] * primes[0];
            int minNum = nums[0];
            for (int j = 1; j < m; j++) {
                nums[j] = dp[pointers[j]] * primes[j];
                minNum = Math.min(minNum, nums[j]);
            }
            dp[i] = minNum;
            for (int j = 0; j < m; j++) {
                if (minNum == nums[j]) {
                    pointers[j]++;
                }
            }
        }
        return dp[n];
    }

    //优先队列
    //执行用时：477 ms, 在所有 Java 提交中击败了19.98%的用户
    //内存消耗：192.6 MB, 在所有 Java 提交中击败了5.11%的用户
    //313. 超级丑数
    //超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
    //给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
    //题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
    public int nthSuperUglyNumber2(int n, int[] primes) {
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> q = new PriorityQueue<>();
        q.add(1L);
        set.add(1L);
        while (n-- > 0) {
            long x = q.poll();
            if (n == 0) {
                return (int)x;
            }
            for (int k : primes) {
                if (!set.contains(k * x)) {
                    set.add(k * x);
                    q.add(k * x);
                }
            }
        }
        return -1; // never
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

    //执行用时：45 ms, 在所有 Java 提交中击败了5.05%的用户
    //内存消耗：43.6 MB, 在所有 Java 提交中击败了75.29%的用户
    //378. 有序矩阵中第 K 小的元素
    //给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
    //请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
    public static int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length;
        int[] index = new int[row];
        int res = 0;
        int minIndex = 0;
        while (k > 0){
            for (int i = 0; i < row; i++) {
                if (index[minIndex] == row && index[i] < row){
                    minIndex = i;
                }else if (index[minIndex] == row && index[i] == row){
                }else if (index[minIndex] < row && index[i] == row){
                }else if (matrix[minIndex][index[minIndex]] > matrix[i][index[i]]){
                    minIndex = i;
                }
            }
            res = matrix[minIndex][index[minIndex]];
            ++index[minIndex];
            k--;
        }
        return res;
    }

    //执行用时：3 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：47.3 MB, 在所有 Java 提交中击败了53.13%的用户
    //使用了额外的空间
    //442. 数组中重复的数据
    //给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
    //找到所有出现两次的元素。
    //你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        boolean[] flag = new boolean[nums.length + 1];
        for (int num : nums) {
            if (flag[num]){
                res.add(num);
            }else {
                flag[num] = true;
            }
        }
        return res;
    }

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：35.8 MB, 在所有 Java 提交中击败了72.09%的用户
    //457. 环形数组是否存在循环
    //存在一个不含 0 的 环形 数组 nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：
    //如果 nums[i] 是正数，向前 移动 nums[i] 步
    //如果 nums[i] 是负数，向后 移动 nums[i] 步
    //因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
    //数组中的 循环 由长度为 k 的下标序列 seq ：
    //遵循上述移动规则将导致重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
    //所有 nums[seq[j]] 应当不是 全正 就是 全负
    //k > 1
    //如果 nums 中存在循环，返回 true ；否则，返回 false 。
    int OFFSET = 100010;
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= OFFSET) {
                continue;
            }
            int cur = i, tag = OFFSET + i, last;
            boolean flag = nums[cur] > 0;
            while (true) {
                int next = ((cur + nums[cur]) % n + n ) % n;
                last = nums[cur];
                nums[cur] = tag;
                cur = next;
                if (cur == i) {
                    break;
                }
                if (nums[cur] >= OFFSET) {
                    break;
                }
                if (flag && nums[cur] < 0) {
                    break;
                }
                if (!flag && nums[cur] > 0) {
                    break;
                }
            }
            if (last % n != 0 && nums[cur] == tag) {
                return true;
            }
        }
        return false;
    }

    //执行用时：2 ms, 在所有 Java 提交中击败了98.34%的用户
    //内存消耗：40.7 MB, 在所有 Java 提交中击败了23.34%的用户
    //498. 对角线遍历
    //给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
    public int[] findDiagonalOrder(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[] res = new int[row * col];
        int power = row + col - 1;
        int index = 0;
        for (int i = 0; i < power; i++) {
            if (i % 2 == 0){
                int x;
                if (i < row){
                    x = i;
                }else {
                    x = row - 1;
                }
                int y = i - x;
                while (x >= 0 && x < row && y >=0 && y < col){
                    res[index] = mat[x][y];
                    ++index;
                    --x;
                    ++y;
                }
            }else {
                int y;
                if (i < col){
                    y = i;
                }else {
                    y = col - 1;
                }
                int x = i - y;
                while (x >= 0 && x < row && y >=0 && y < col){
                    res[index] = mat[x][y];
                    ++index;
                    ++x;
                    --y;
                }
            }
        }
        return res;
    }

    //两次单调栈
    //执行用时：5 ms, 在所有 Java 提交中击败了97.89%的用户
    //内存消耗：40.4 MB, 在所有 Java 提交中击败了21.56%的用户
    //503. 下一个更大元素 II
    //给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
    //数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                ret[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        for (int num : nums) {
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                ret[stack.pop()] = num;
            }
            if (1 == stack.size()) {
                break;
            }
        }
        return ret;
    }

    //使用流运算
    //执行用时：16 ms, 在所有 Java 提交中击败了20.00%的用户
    //内存消耗：40.2 MB, 在所有 Java 提交中击败了10.10%的用户
    //539. 最小时间差
    //给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
    public int findMinDifference(List<String> timePoints) {
        int len = timePoints.size();
        Integer[] times = timePoints.stream()
                .map(key -> Integer.parseInt(key.substring(0, 2)) * 60 + Integer.parseInt(key.substring(3)))
                .sorted()
                .toArray(Integer[]::new);
        int min = times[0] + 24 * 60 - times[len - 1];
        for (int i = 1; i < len; i++) {
            if (times[i].equals(times[i - 1])) {
                min = 0;
                break;
            } else {
                min = Math.min(times[i] - times[i - 1], min);

            }
        }
        return min;
    }

    //自己转换 快了6ms
    //执行用时：10 ms, 在所有 Java 提交中击败了32.26%的用户
    //内存消耗：38.3 MB, 在所有 Java 提交中击败了55.27%的用户
    //539. 最小时间差
    //给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
    public int findMinDifference2(List<String> timePoints) {
        int len = timePoints.size();
        Collections.sort(timePoints);
        int min = parseInt(timePoints.get(0))  + 1440 - parseInt(timePoints.get(len - 1));
        for (int i = 1; i < len; i++) {
            if (timePoints.get(i).equals(timePoints.get(i - 1))){
                min = 0;
                break;
            }else {
                int temp = parseInt(timePoints.get(i)) - parseInt(timePoints.get(i - 1));
                min = Math.min(temp,min);
            }
        }
        return min;
    }

    public int parseInt(String str){
        return ((str.charAt(0)-'0')*10 + (str.charAt(1)-'0'))*60 +
                (str.charAt(3)-'0')*10 + (str.charAt(4)-'0');
    }

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

    //执行用时：6 ms, 在所有 Java 提交中击败了60.08%的用户
    //内存消耗：39.8 MB, 在所有 Java 提交中击败了42.14%的用户
    //581. 最短无序连续子数组
    //给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
    //请你找出符合题意的 最短 子数组，并输出它的长度。
    public static int findUnsortedSubarray(int[] nums) {
        int[] copy = Arrays.copyOfRange(nums, 0, nums.length);
        Arrays.sort(nums);
        int len = nums.length;
        int[] res = {-1,-1};
        for (int i = 0; i < len; i++) {
            if (copy[i] != nums[i]){
                res[0] = i;
                break;
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            if (copy[i] != nums[i]){
                res[1] = i + 1;
                break;
            }
        }
        return res[1] - res[0];
    }

    //执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：39.6 MB, 在所有 Java 提交中击败了64.26%的用户
    //581. 最短无序连续子数组
    //给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
    //请你找出符合题意的 最短 子数组，并输出它的长度。
    public static int findUnsortedSubarray2(int[] nums) {
        int len = nums.length;

        if (len < 2){
            return 0;
        }
        int left = len - 1;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < len; i++) {
            if (nums[i] < nums[i - 1]){
                max = nums[i - 1];
                left = i - 1;
                break;
            }
        }
        if (max == Integer.MIN_VALUE){
            return 0;
        }
        int right = 0;
        int min = Integer.MAX_VALUE;
        for (int i = len - 1; i > 0; i--) {
            if (nums[i] < nums[i - 1]){
                min = nums[i];
                right = i;
                break;
            }
        }
        if (max == Integer.MAX_VALUE){
            return 0;
        }
        for (int i = left; i <= right; i++) {
            min = Math.min(nums[i],min);
            max = Math.max(nums[i],max);
        }
        for (int i = left; i >= 0; i--) {
            if (nums[i] > min){
                left = i;
            }else {
                break;
            }
        }
        for (int i = right; i < len; i++) {
            if (nums[i] < max){
                right = i;
            }else {
                break;
            }
        }

        return right - left + 1;
    }

}
