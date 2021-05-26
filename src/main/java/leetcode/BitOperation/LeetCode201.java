package leetcode.BitOperation;

public class LeetCode201 {

    //201. 数字范围按位与
    public int rangeBitwiseAnd(int left, int right) {
        int zeros = 0;
        while (left != right) {
            zeros++;
            left >>>= 1;
            right >>>= 1;
        }
        //将 0 的个数空出来
        return left << zeros;
    }
}
