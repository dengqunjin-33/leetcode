package leetcode.BitOperation;

public class LeetCode231 {

    //231. 2的幂
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & n - 1) == 0;
    }
}
