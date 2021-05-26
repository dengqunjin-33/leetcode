package leetcode.DP;

public class LeetCode70 {
    //70. 爬楼梯
    public int climbStairs(int n) {
        if (n < 1) {
            return -1;
        }
        if (n == 1 || n == 2) {
            return n;
        }

        int first = 1;
        int second = 2;
        int temp;
        for (int i = 3; i <= n; i++) {
            temp = first + second;
            first = second;
            second = temp;
        }

        return second;
    }
}
