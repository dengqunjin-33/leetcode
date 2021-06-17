package leetcode.weekgame;

import java.util.Stack;

public class LeetCode240w {

    //1854. 人口最多的年份
    //给你一个二维整数数组 logs ，其中每个 logs[i] = [birthi, deathi] 表示第 i 个人的出生和死亡年份。
    //年份 x 的 人口 定义为这一年期间活着的人的数目。第 i 个人被计入年份 x 的人口需要满足：x 在闭区间 [birthi, deathi - 1] 内。注意，人不应当计入他们死亡当年的人口中。
    //返回 人口最多 且 最早 的年份。
    public int maximumPopulation(int[][] logs) {
        int[] x = new int[101];
        for (int[] log : logs) {
            for (int j = log[0]; j < log[1]; j++) {
                x[j - 1950] += 1;
            }
        }

        int max = 0;
        int index = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] > max){
                max = x[i];
                index = i;
            }
        }
        return 1950 + index;
    }

    //自己得暴力解法（超时了）
    //1855. 下标对中的最大距离
    //给你两个 非递增 的整数数组 nums1和 nums2，数组下标均 从 0 开始 计数。
    //下标对 (i, j) 中 0 <= i < nums1.length 且 0 <= j < nums2.length 。如果该下标对同时满足 i <= j 且 nums1[i] <= nums2[j] ，则称之为 有效 下标对，该下标对的 距离 为 j - i 。
    //返回所有 有效 下标对 (i, j) 中的 最大距离 。如果不存在有效下标对，返回 0 。
    //一个数组 arr ，如果每个 1 <= i < arr.length 均有 arr[i-1] >= arr[i] 成立，那么该数组是一个 非递增 数组。
    public int maxDistance(int[] nums1, int[] nums2) {
        int index = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = i; j < nums2.length; j++) {
                if (nums2[j] < nums1[i]){
                    index = Math.max((j - i - 1),index);
                    break;
                }else if (j == nums2.length - 1){
                    index = Math.max((j - i ),index);
                }
            }
        }
        return index;
    }

    //别人得双指针
    //1855. 下标对中的最大距离
    //给你两个 非递增 的整数数组 nums1和 nums2，数组下标均 从 0 开始 计数。
    //下标对 (i, j) 中 0 <= i < nums1.length 且 0 <= j < nums2.length 。如果该下标对同时满足 i <= j 且 nums1[i] <= nums2[j] ，则称之为 有效 下标对，该下标对的 距离 为 j - i 。
    //返回所有 有效 下标对 (i, j) 中的 最大距离 。如果不存在有效下标对，返回 0 。
    //一个数组 arr ，如果每个 1 <= i < arr.length 均有 arr[i-1] >= arr[i] 成立，那么该数组是一个 非递增 数组。
    public int maxDistance2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int L = 0;
        int R = 0;
        while (L < len1 && R < len2) {
            if (nums1[L] > nums2[R]) {
                L++;
            }
            R++;
        }
        return Math.max(R - L - 1, 0);
    }

    //大佬的单调栈 + 前缀和
    //1856. 子数组最小乘积的最大值
    //一个数组的 最小乘积 定义为这个数组中 最小值 乘以 数组的 和 。
    //比方说，数组 [3,2,5] （最小值是 2）的最小乘积为 2 * (3+2+5) = 2 * 10 = 20 。
    //给你一个正整数数组 nums ，请你返回 nums 任意 非空子数组 的最小乘积 的 最大值 。由于答案可能很大，请你返回答案对  109 + 7 取余 的结果。
    //请注意，最小乘积的最大值考虑的是取余操作 之前 的结果。题目保证最小乘积的最大值在 不取余 的情况下可以用 64 位有符号整数 保存。
    //子数组 定义为一个数组的 连续 部分。
    public int maxSumMinProduct(int[] nums) {
        // 先计算前缀和
        long[] prefixNums = new long[nums.length];
        prefixNums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixNums[i] = prefixNums[i - 1] + nums[i];
        }

        Stack<Integer> upStack = new Stack<>();
        long max = 0;
        // 数组元素 下标 人单调递增栈
        for (int i = 0; i < nums.length; i++) {
            if (!upStack.isEmpty() && nums[upStack.peek()] > nums[i]) {
                // 每次出栈计算乘积
                while (!upStack.isEmpty() && nums[upStack.peek()] > nums[i]) {
                    int minIndex = upStack.pop();
                    int start = upStack.isEmpty() ? 0 : upStack.peek() + 1;
                    int end = i - 1;
                    max = Math.max(max, calcu(prefixNums, start, end, nums[minIndex]));
                }
            }
            upStack.push(i);
        }
        // 栈中剩余元素依次出栈，并计算乘积
        while (!upStack.isEmpty()) {
            int minIndex = upStack.pop();
            int end = nums.length - 1;
            int start = upStack.isEmpty() ? 0 : upStack.peek() + 1;
            max = Math.max(max, calcu(prefixNums, start, end, nums[minIndex]));
        }
        return (int) (max % 1000000007);
    }

    // 求乘积
    private long calcu(long[] prefixNums, int start, int end, int min) {
        long sum = start == 0 ?
                prefixNums[end] :
                prefixNums[end] - prefixNums[start - 1];
        return sum * min;
    }
}
