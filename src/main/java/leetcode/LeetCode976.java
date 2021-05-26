package leetcode;

import java.util.Arrays;

public class LeetCode976 {

    //三角形的最大周长
    public static int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i > 1; i--) {
            if ((A[i - 1] + A[i - 2]) > A[i]) {
                return A[i] + A[i - 1] + A[i - 2];
            }
        }
        return 0;
    }
}
