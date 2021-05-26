package leetcode.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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


}
