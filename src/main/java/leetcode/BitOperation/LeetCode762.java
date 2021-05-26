package leetcode.BitOperation;

public class LeetCode762 {

    //二进制表示中质数个计算置位
    //给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。
    public int countPrimeSetBits(int L, int R) {
        int ans = 0;
        for (int x = L; x <= R; ++x) {
            if (isSmallPrime(Integer.bitCount(x))) {
                ans++;
            }
        }
        return ans;
    }
    public boolean isSmallPrime(int x) {
        return (x == 2 || x == 3 || x == 5 || x == 7 ||
                x == 11 || x == 13 || x == 17 || x == 19);
    }
}
