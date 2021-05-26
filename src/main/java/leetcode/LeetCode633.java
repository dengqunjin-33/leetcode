package leetcode;

public class LeetCode633 {

    //633. 平方数之和
    //给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
    public static boolean judgeSquareSum(int c) {
        int left = 0;
        int right = (int) Math.sqrt(c);
        while(left <= right){
            int sum = left * left + right * right;
            if (sum == c){
                return true;
            }else if (sum > c){
                right--;
            }else {
                left++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        judgeSquareSum(2);
    }

}
