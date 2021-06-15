package leetcode;

public class LeetCode860To869 {

    //867.数组转置
    public int[][] transpose(int[][] A) {
        int[][] result = new int[A[0].length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                result[j][i] = A[i][j];
            }
        }
        return result;
    }

    //868.二进制间距
    public static int binaryGap(int n) {
        int min = 0;
        int max = 0;
        boolean flag = false;
        int instance = 0;
        for (int i = 31; i >= 0; i--) {
            if (1 == (n >>> i & 1)) {
                max = i;
                if (flag) {
                    instance = Math.max(instance, min - max);
                }
                min = i;
                flag = true;
            }
        }
        return instance;
    }

    //869.重新排序得到 2 的幂
    public static boolean reorderedPowerOf2(int N) {
        return false;
    }
}
