package leetcode.Arrays;

import java.util.*;

public class LeetCodeArrays600later {

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

    //单调栈
    //执行用时：36 ms, 在所有 Java 提交中击败了38.16%的用户
    //内存消耗：47.5 MB, 在所有 Java 提交中击败了44.92%的用户
    //739. 每日温度
    //请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。
    public static int[] dailyTemperatures(int[] temperatures) {
        int [] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < temperatures.length;++i){
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                Integer peek = stack.pop();
                res[peek] = i - peek;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});
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