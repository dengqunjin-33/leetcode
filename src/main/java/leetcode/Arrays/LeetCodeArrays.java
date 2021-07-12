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

//    public static void main(String[] args) {
//        firstMissingPositive2(new int[]{3,4,-1,1});
//    }

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

    public static void main(String[] args) {
        gameOfLife(new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}});
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

    //645. 错误的集合
    //集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
    //给定一个数组 nums 代表了集合 S 发生错误后的结果。
    //请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
    public static int[] findErrorNums(int[] nums) {
        int n = nums.length;
        boolean[] cnts = new boolean[n + 1];
        int sum = nums.length * (nums.length + 1) >> 1;
        int[] ans = new int[2];
        for (int x : nums) {
            if(cnts[x]){
                ans[0] = x;
            }else{
                sum -= x;
                cnts[x] = true;
            }
        }
        ans[1] = sum;
        return ans;
    }

    //645. 错误的集合
    //集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
    //给定一个数组 nums 代表了集合 S 发生错误后的结果。
    //请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
    public static int[] findErrorNums2(int[] nums) {
        int n = nums.length;
        boolean[] cnts = new boolean[n + 1];
        int[] ans = new int[2];
        for (int x : nums) {
            if(cnts[x]){
                ans[0] = x;
            }else{
                cnts[x] = true;
            }
        }
        for (int i = 1; i < n + 1; i++) {
            if (!cnts[i]){
                ans[1] = i;
            }
        }
        return ans;
    }

    //912. 排序数组
    //给你一个整数数组 nums，请你将该数组升序排列。
    public int[] sortArray(int[] nums) {
        quickNums(nums,0,nums.length - 1);
        return nums;
    }

    private void quickNums(int[] nums,int start,int end){
        if (start < end){
            int base = nums[start];
            int i = start;
            int j = end;
            while (i < j){
                while (i < j && base <= nums[j]){
                    --j;
                }
                nums[i] = nums[j];
                while (i < j && base >= nums[i]){
                    i++;
                }
                nums[j] = nums[i];
            }
            nums[i] = base;
            quickNums(nums, start, i);
            quickNums(nums, i + 1, end);
        }
    }

    //1292. 元素和小于等于阈值的正方形的最大边长
    //给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
    //请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
    public static int maxSideLength(int[][] mat, int threshold) {
        int res = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                res = Math.max(res,maxSideLength(mat,i,j,i,j,threshold,0));
            }
        }
        return res;
    }

    public static int maxSideLength(int[][] mat, int x1, int y1, int x2, int y2, int threshold, int sum) {
        if (x2 == mat.length || y2 == mat[0].length){
            return x2 - x1;
        }

        for (int i = x1; i <= x2; i++) {
            sum += mat[i][y2];
            if (sum > threshold){
                return x2 - x1;
            }
        }

        for (int i = y1; i < y2; i++) {
            sum += mat[x2][i];
            if (sum > threshold){
                return x2 - x1;
            }
        }

        return maxSideLength(mat,x1,y1,x2 + 1,y2 + 1,threshold,sum);
    }

    //暴力法 超时
    //1711. 大餐计数
    //大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
    //你可以搭配 任意 两道餐品做一顿大餐。
    //给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。
    //注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
    public int countPairs(int[] deliciousness) {
        long count = 0;
        for (int i = 0; i < deliciousness.length - 1; i++) {
            for (int j = i + 1; j < deliciousness.length; j++) {
                long temp = deliciousness[i] + deliciousness[j];
                if (Long.bitCount(temp) == 1){
                    ++count;
                }
            }
        }
        return (int) (count % 1000000007);
    }

    //优化  使用hash表
    //1711. 大餐计数
    //大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
    //你可以搭配 任意 两道餐品做一顿大餐。
    //给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。
    //注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
    public static int countPairs2(int[] deliciousness) {
        final int MOD = 1000000007;
        int maxVal = 0;
        for (int val : deliciousness) {
            maxVal = Math.max(maxVal, val);
        }
        int maxSum = maxVal * 2;
        maxSum = tableSizeFor(maxSum);
        int pairs = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int val : deliciousness) {
            for (int sum = maxSum; sum > 0; sum >>= 1) {
                if (sum - val < 0) {
                    break;
                }
                int count = map.getOrDefault(sum - val, 0);
                pairs = (pairs + count) % MOD;
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return pairs;
    }

    public static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }


}