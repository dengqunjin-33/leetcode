package leetcode.weekgame;

import java.util.Arrays;

public class LeetCode244w {

    //1886. 判断矩阵经轮转后是否一致
    //给你两个大小为 n x n 的二进制矩阵 mat 和 target 。
    //现 以 90 度顺时针轮转 矩阵 mat 中的元素 若干次 ，如果能够使 mat 与 target 一致，返回 true ；否则，返回 false 。
    public static boolean findRotation(int[][] mat, int[][] target) {
        int len = mat.length;
        if (len == 1){
            return mat[0][0] == target[0][0];
        }
        return rotation(mat,target,0) || rotation(mat,target,1) || rotation(mat,target,2) || rotation(mat,target,3);
    }

    public static boolean rotation(int[][] mat, int[][] target,int count) {
        int len = mat.length;
        for (int i = 0; i <  len + 1 >> 1; i++) {
            for (int j = i; j < len - i - 1; j++) {
                //旋转0°
                if (0 == count &&
                        (mat[i][j] != target[i][j]
                                || mat[j][len - i -1] != target[j][len - i -1]
                                || mat[len - i - 1][len - j - 1] != target[len - i - 1][len - j - 1]
                                || mat[len - j - 1][i] != target[len - j - 1][i])){
                    return false;
                }
                //旋转90°
                if (1 == count &&
                        (mat[i][j] != target[j][len - i -1]
                                || mat[j][len - i -1] != target[len - i - 1][len - j - 1]
                                || mat[len - i - 1][len - j - 1] != target[len - j - 1][i]
                                || mat[len - j - 1][i] != target[i][j])){
                    return false;
                }
                //旋转180°
                if (2 == count &&
                        (mat[i][j] != target[len - i - 1][len - j - 1]
                                || mat[j][len - i -1] != target[len - j - 1][i]
                                || mat[len - i - 1][len - j - 1] != target[i][j]
                                || mat[len - j - 1][i] != target[j][len - i -1])){
                    return false;
                }
                //旋转270°
                if (3 == count &&
                        (mat[i][j] != target[len - j - 1][i]
                                || mat[j][len - i -1] != target[i][j]
                                || mat[len - i - 1][len - j - 1] != target[j][len - i -1]
                                || mat[len - j - 1][i] != target[len - i - 1][len - j - 1])){
                    return false;
                }
            }
        }
        return true;
    }

    //大佬的
    //1887. 使数组元素相等的减少操作次数
    //给你一个整数数组 nums ，你的目标是令 nums 中的所有元素相等。完成一次减少操作需要遵照下面的几个步骤：
    //找出 nums 中的 最大 值。记这个值为 largest 并取其下标 i （下标从 0 开始计数）。如果有多个元素都是最大值，则取最小的 i 。
    //找出 nums 中的 下一个最大 值，这个值 严格小于 largest ，记为 nextLargest 。
    //将 nums[i] 减少到 nextLargest 。
    //返回使 nums 中的所有元素相等的操作次数。
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        // 总操作次数
        int res = 0;
        // 每个元素操作次数
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                count++;
            }
            res += count;
        }
        return res;
    }
}
