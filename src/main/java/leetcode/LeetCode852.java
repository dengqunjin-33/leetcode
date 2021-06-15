package leetcode;

public class LeetCode852 {

    //852. 山脉数组的峰顶索引
    //符合下列属性的数组 arr 称为 山脉数组 ：
    //arr.length >= 3
    //存在 i（0 < i < arr.length - 1）使得：
    //arr[0] < arr[1] < ... arr[i-1] < arr[i]
    //arr[i] > arr[i+1] > ... > arr[arr.length - 1]
    //给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
    public int peakIndexInMountainArray(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i-1] && arr[i] > arr[i + 1]){
                return i;
            }
        }
        return -1;
    }
}
