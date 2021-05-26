package leetcode.Arrays;

public class LeetCode861 {
    //861. 翻转矩阵后的得分
    public static int matrixScore(int[][] A) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i][0] == 0) {
                for (int j = 0; j < A[0].length; j++) {
                    //使用行变换修正第一列，使第一列全为1，
                    A[i][j] = (A[i][j] + 1) % 2;
                }
            }
            //第一列的和为:A.length 个 (1 << A[0].length - 1)就是2的(A[0].length - 1)幂
            sum = A.length * (1 << A[0].length - 1);
        }

        for (int i = 1; i < A[0].length; i++) {
            //flag判断0的个数
            int flag = 0;
            for (int j = 0; j < A.length; j++) {
                if (A[j][i] == 0) {
                    ++flag;
                }
            }

            //如果0的个数比当列一半还要少,1的个数为A.length - flag
            //如果0的个数比当列一半还要多，1的个数为为flag
            //第一列的和为:A.length 个 (1 << A[0].length - 1)就是2的(A[0].length - 1)幂
            if (flag < (A.length + 1) >> 1) {
                sum += (A.length - flag) * (1 << (A[0].length - i - 1));
            } else {
                sum += flag * (1 << (A[0].length - i - 1));
            }
        }

        return sum;
    }

}
