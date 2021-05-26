package leetcode.BitOperation;

public class LeetCode191 {

    //191. 位1的个数
    public int hammingWeight(int n) {
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                ++sum;
            }
            n = n >> 1;
        }
        return sum;
    }
}
