package leetcode.weekgame;

public class LeetCode238w {

    //1837. K 进制表示下的各位数字总和
    //给你一个整数 n（10 进制）和一个基数 k ，请你将 n 从 10 进制表示转换为 k 进制表示，计算并返回转换后各位数字的 总和 。
    //转换后，各位数字应当视作是 10 进制数字，且它们的总和也应当按 10 进制表示返回。
    public static int sumBase(int n, int k) {
        int res = 0;
        while (n != 0){
            res += n % k;
            n /= k;
        }
        return res;
    }
}
