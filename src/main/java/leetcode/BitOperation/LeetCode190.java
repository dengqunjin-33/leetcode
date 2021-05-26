package leetcode.BitOperation;

public class LeetCode190 {

    //190. 颠倒二进制位
    public int reverseBits(int n) {
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                sum += 1 << (31 - i);
            }
            n = n >>> 1;
        }
        return sum;
    }

}
