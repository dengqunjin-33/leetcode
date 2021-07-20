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

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：38.7 MB, 在所有 Java 提交中击败了38.27%的用户
    //832. 翻转图像
    //给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
    //水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
    //反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
    public int[][] flipAndInvertImage(int[][] image) {
        int row = image.length;
        int col = image[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (image[i][col - 1 - j] == 0){
                    res[i][j] = 1;
                }
            }
        }
        return res;
    }

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：35.6 MB, 在所有 Java 提交中击败了67.72%的用户
    //836. 矩形重叠
    //矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。矩形的上下边平行于 x 轴，左右边平行于 y 轴。
    //如果相交的面积为 正 ，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
    //给出两个矩形 rec1 和 rec2 。如果它们重叠，返回 true；否则，返回 false 。
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if (rec1[0] == rec1[2] || rec1[1] == rec1[3] || rec2[0] == rec2[2] || rec2[1] == rec2[3]) {
            return false;
        }
        return !(rec1[2] <= rec2[0] ||
                rec1[3] <= rec2[1] ||
                rec1[0] >= rec2[2] ||
                rec1[1] >= rec2[3]);
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

    //二分法
    //执行用时：47 ms, 在所有 Java 提交中击败了88.87%的用户
    //内存消耗：55.9 MB, 在所有 Java 提交中击败了56.62%的用户
    //1818. 绝对差值和
    //给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
    //数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。
    //你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
    //在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。
    //|x| 定义为：
    //如果 x >= 0 ，值为 x ，或者
    //如果 x <= 0 ，值为 -x
    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n =nums1.length;
        if (1 == n){
            return Math.abs(nums1[0] - nums2[0]);
        }

        int[] sortNum1 = Arrays.copyOf(nums1, n);
        Arrays.sort(sortNum1);

        //计算能减去的最大差值
        int maxDiff = 0;
        int sum = 0;
        int MOD = 1000000007;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);

            sum += diff;
            sum %= MOD;

            int nearDiff = Integer.MAX_VALUE;
            int left = 0, right = n - 1;
            while (left <= right) {
                int mid = (left + right) >> 1;
                if (sortNum1[mid] < nums2[i]) {
                    nearDiff = Math.min( nearDiff,nums2[i] - sortNum1[mid]);
                    left = mid + 1;
                } else if (sortNum1[mid] > nums2[i]) {
                    nearDiff = Math.min(nearDiff,sortNum1[mid] - nums2[i]);
                    right = mid - 1;
                }else {
                    nearDiff = 0;
                    break;
                }
            }

            if (nearDiff < diff) {
                nearDiff %= MOD;
                maxDiff =  Math.max(maxDiff, diff - nearDiff);
            }
        }

        return (sum + MOD - maxDiff) % MOD;
    }

    //TreeSet
    //执行用时：156 ms, 在所有 Java 提交中击败了29.29%的用户
    //内存消耗：52.6 MB, 在所有 Java 提交中击败了90.34%的用户
    //1818. 绝对差值和
    //给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
    //数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。
    //你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
    //在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。
    //|x| 定义为：
    //如果 x >= 0 ，值为 x ，或者
    //如果 x <= 0 ，值为 -x
    public static int minAbsoluteSumDiff2(int[] nums1, int[] nums2) {
        int n =nums1.length;
        if (1 == n){
            return Math.abs(nums1[0] - nums2[0]);
        }

        TreeSet<Integer> set = new TreeSet<>();
        for (int k : nums1) {
            set.add(k);
        }

        //计算能减去的最大差值
        int maxDiff = 0;
        int sum = 0;
        int MOD = 1000000007;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);

            sum += diff;
            sum %= MOD;

            int nearDiff = Integer.MAX_VALUE;
            for (int j = nums2[i]; j <= nums2[i] + diff; j++) {
                if (set.contains(j)){
                    nearDiff = j - nums2[i];
                    break;
                }
            }

            for (int j = nums2[i]; j > nums2[i] - Math.min(nearDiff, diff); j--) {
                if (set.contains(j)){
                    nearDiff = nums2[i] - j;
                    break;
                }
            }

            if (nearDiff < diff) {
                maxDiff =  Math.max(maxDiff, diff - nearDiff);
            }
        }

        return (sum + 1000000007 - maxDiff) % 1000000007;
    }

    //暴力法 超时了
    //1838. 最高频元素的频数
    //元素的 频数 是该元素在一个数组中出现的次数。
    //给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
    //执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
    public int maxFrequency(int[] nums, int k) {
        if (1 == nums.length){
            return 1;
        }
        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int index = 1;
            int tempk = k;
            for (int j = i + 1; j < nums.length; j++) {
                int diff = nums[j] - nums[j - 1];
                tempk -= diff * index;
                if (tempk < 0){
                    max = Math.max(max,j - i);
                    break;
                }else if (j == nums.length - 1){
                    max = Math.max(max,j - i + 1);
                    break;
                }else {
                    ++index;
                }
            }
        }
        return max;
    }

    //大佬的  双指针
    //1838. 最高频元素的频数
    //元素的 频数 是该元素在一个数组中出现的次数。
    //给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
    //执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
    public int maxFrequency2(int[] nums, int k) {
        // 先升序
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        int cnt = 0;
        // 双指针遍历数组
        for(int i = 0, j = 0; i < n; i++) {
            // 指针区间数都变成当前位的数，加上所需的增加次数
            if(i > 0) {
                cnt += (i - j) * (nums[i] - nums[i - 1]);
            }
            // 如果所需的次数超过最大允许次数，则左侧边界开始向右，区间变小，所需次数也会变少
            while(cnt > k && i > j) {
                cnt -= (nums[i] - nums[j]);
                j ++;
            }
            // 取最大值
            res = Math.max(i - j + 1, res);
        }

        return res;
    }

    //执行用时：
    //3 ms, 在所有 Java 提交中击败了99.35%的用户
    //内存消耗：55.2 MB, 在所有 Java 提交中击败了82.88%的用户
    //1846. 减小和重新排列数组后的最大元素
    //给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：
    //arr 中 第一个 元素必须为 1 。
    //任意相邻两个元素的差的绝对值 小于等于 1 ，也就是说，对于任意的 1 <= i < arr.length （数组下标从 0 开始），都满足 abs(arr[i] - arr[i - 1]) <= 1 。abs(x) 为 x 的绝对值。
    //你可以执行以下 2 种操作任意次：
    //减小 arr 中任意元素的值，使其变为一个 更小的正整数 。
    //重新排列 arr 中的元素，你可以以任意顺序重新排列。
    //请你返回执行以上操作后，在满足前文所述的条件下，arr 中可能的 最大值 。
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int index = 1;
        for (int j : arr) {
            if (j > index){
                ++index;
            }
        }
        return index - 1;
    }

    //执行用时：3 ms, 在所有 Java 提交中击败了94.51%的用户
    //内存消耗：51.2 MB, 在所有 Java 提交中击败了44.51%的用户
    //1855. 下标对中的最大距离
    //给你两个 非递增 的整数数组 nums1 和 nums2 ，数组下标均 从 0 开始 计数。
    //下标对 (i, j) 中 0 <= i < nums1.length 且 0 <= j < nums2.length 。如果该下标对同时满足 i <= j 且 nums1[i] <= nums2[j] ，则称之为 有效 下标对，该下标对的 距离 为 j - i​​ 。​​
    //返回所有 有效 下标对 (i, j) 中的 最大距离 。如果不存在有效下标对，返回 0 。
    //一个数组 arr ，如果每个 1 <= i < arr.length 均有 arr[i-1] >= arr[i] 成立，那么该数组是一个 非递增 数组。
    public int maxDistance(int[] nums1, int[] nums2) {
        int count = 0;
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length){
            if (nums1[i] > nums2[j]){
                ++i;
            }else {
                count = Math.max(count,j - i);
                ++j;
            }
        }
        return count;
    }

    //1877. 数组中最大数对和的最小值
    //一个数对 (a,b) 的 数对和 等于 a + b 。最大数对和 是一个数对数组中最大的 数对和 。
    //比方说，如果我们有数对 (1,5) ，(2,3) 和 (4,4)，最大数对和 为 max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8 。
    //给你一个长度为 偶数 n 的数组 nums ，请你将 nums 中的元素分成 n / 2 个数对，使得：
    //nums 中每个元素 恰好 在 一个 数对中，且
    //最大数对和 的值 最小 。
    //请你在最优数对划分的方案下，返回最小的 最大数对和 。
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        int times = nums.length >> 1;
        int len = nums.length;
        for (int i = 0; i < times; i++) {
            res = Math.max(res,nums[i] + nums[len - 1 - i]);
        }
        return res;
    }
}