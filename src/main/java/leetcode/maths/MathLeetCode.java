package leetcode.maths;

public class MathLeetCode {

    //7. 整数反转
    //给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
    //如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
    //假设环境不允许存储 64 位整数（有符号或无符号）。
    public int reverse(int x) {
        if (x < 10 && x > -10){
            return x;
        }
        int temp = x >= 0 ? x : -x;
        StringBuffer sb = new StringBuffer();
        boolean flag = true;
        while (temp != 0){
            if (!(temp % 10  == 0 && flag)){
                sb.append(temp % 10);
                flag = false;
            }
            temp /= 10;
        }
        try {
            int result =  Integer.parseInt(sb.toString());
            return  x >= 0 ? result : -result;
        }catch (Exception e){

        }
        return 0;
    }

    //9. 回文数
    //给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
    //回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
    public static boolean isPalindrome(int x) {
        if (x < 0){
            return false;
        }
        int result = 0;
        int temp = x;
        while (temp != 0){
            result = result * 10 + temp % 10;
            temp /= 10;
        }
        return result == x;
    }

    //367. 有效的完全平方数
    //给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
    //进阶：不要 使用任何内置的库函数，如  sqrt 。
    public static boolean isPerfectSquare(int num) {
        int left = 0;
        int right = (num + 1) >> 1;
        while (left <= right){
            int mid = (left + right) >> 1;
            long target = mid * mid;
            if (target == num){
                return true;
            }else if (target > num){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return false;
    }

    //牛顿迭代法
    //367. 有效的完全平方数
    //给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
    //进阶：不要 使用任何内置的库函数，如  sqrt 。
    public boolean isPerfectSquare1(int num) {
        if (num < 2) {
            return true;
        }
        long x = num / 2;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return (x * x == num);
    }

    public static void main(String[] args) {
        System.out.println(Math.sqrt(808201));
        isPerfectSquare(808201);
    }
}
