package leetcode.sorts;

import java.util.*;

public class LeetCodeSort {
    //56. 合并区间
    //以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
    //请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int l = intervals[i][0];
            int r = intervals[i][1];
            if (merged.size() == 0 || l > merged.get(merged.size() - 1)[1]){
                int[] x = {l,r};
                merged.add(x);
            }else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1],r);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    //75. 颜色分类
    //给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
    //此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
    public void sortColors(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == 0){
                    break;
                }else if (nums[i] > nums[j]){
                    nums[i] ^= nums[j];
                    nums[j] ^= nums[i];
                    nums[i] ^= nums[j];
                }
            }
        }
    }

    //922. 按奇偶排序数组 II
    //给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
    //对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
    public int[] sortArrayByParityII(int[] nums) {
        int odd = 1;
        int even = 0;
        while (odd < nums.length && even < nums.length){
            while (even < nums.length && nums[even] % 2 == 0){
                even += 2;
            }
            while (odd < nums.length && nums[odd] % 2 == 1){
                odd += 2;
            }
            if (odd < nums.length && even < nums.length){
                nums[odd] ^= nums[even];
                nums[even] ^= nums[odd];
                nums[odd] ^= nums[even];
            }
        }
        return nums;
    }

    //1356. 根据数字二进制下 1 的数目排序
    //给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
    //如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
    //请你返回排序后的数组。
    public static int[] sortByBits(int[] arr) {
        int[] arrSum = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrSum[i] = Integer.bitCount(arr[i]);
        }

        for(int i = 0;i < arrSum.length - 1;i++){
            for(int j = 0;j < arrSum.length - i - 1;j++){
                if(arrSum[j+1] < arrSum[j]){
                    swap(arr,j,j + 1);
                    swap(arrSum,j,j + 1);
                }
                if(arrSum[j+1] == arrSum[j] && arr[j] > arr[j+1]){
                    swap(arr,j,j + 1);
                    swap(arrSum,j,j + 1);
                }
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int j){
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }

    //1491. 去掉最低工资和最高工资后的工资平均值
    //给你一个整数数组 salary ，数组里每个数都是 唯一 的，其中 salary[i] 是第 i 个员工的工资。
    //请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。
    public double average(int[] salary) {
        if (salary.length <= 2){
            return 0;
        }
        int min = salary[0];
        int max = salary[0];
        int sum = salary[0];
        for (int i = 1; i < salary.length; i++) {
            sum += salary[i];
            if (salary[i] > max){
                max = salary[i];
            }
            if (salary[i] < min){
                min = salary[i];
            }
        }
        return (sum - max - min + 0.0) / (salary.length - 2);
    }

    //1502. 判断能否形成等差数列
    //给你一个数字数组 arr 。
    //如果一个数列中，任意相邻两项的差总等于同一个常数，那么这个数列就称为 等差数列 。
    //如果可以重新排列数组形成等差数列，请返回 true ；否则，返回 false 。
    public static boolean canMakeArithmeticProgression(int[] arr) {
        if (arr.length <= 2){
            return true;
        }
        sortQuick(arr,0,arr.length - 1);
        int diff = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != diff){
                return false;
            }
        }
        return true;
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

    //1387. 将整数按权重排序
    //我们将整数 x 的 权重 定义为按照下述规则将 x 变成 1 所需要的步数：
    //如果 x 是偶数，那么 x = x / 2
    //如果 x 是奇数，那么 x = 3 * x + 1
    //比方说，x=3 的权重为 7 。因为 3 需要 7 步变成 1 （3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1）。
    //给你三个整数 lo， hi 和 k 。你的任务是将区间 [lo, hi] 之间的整数按照它们的权重 升序排序 ，如果大于等于 2 个整数有 相同 的权重，那么按照数字自身的数值 升序排序 。
    //请你返回区间 [lo, hi] 之间的整数按权重排序后的第 k 个数。
    public static int getKth(int lo, int hi, int k) {
        if (k > hi - lo + 1){
            return -1;
        }
        Map<Integer,Integer> map = new HashMap<>();
        int[][] res = new int[hi - lo + 1][2];
        int index = 0;
        for (int i = lo; i <= hi; i++) {
            res[index ++ ] = new int[]{i,getSteps(map,i)};
        }
        Arrays.sort(res, (a, b) -> {
            if(a[1] == b[1]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        return res[k - 1][0];
    }

    public static int getSteps(Map<Integer, Integer> map, int i){
        if (map.containsKey(i)){
            return map.get(i);
        }
        List<Integer> list = new ArrayList();
        list.add(i);
        while (i != 1){
            if (i % 2 == 0){
                i = i >> 1;
            }else {
                i = 3 * i + 1;
            }
            if (map.containsKey(i)){
                return map.get(i) + list.size();
            }
            list.add(i);
        }

        for (int j = 0; j < list.size(); j++) {
            map.put(list.get(j),list.size() - j - 1);
        }
        return list.size() - 1;
    }


}
