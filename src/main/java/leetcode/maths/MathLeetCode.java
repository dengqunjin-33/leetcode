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

    //223. 矩形面积
    //给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。
    //每个矩形由其 左下 顶点和 右上 顶点坐标表示：
    //第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
    //第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);
        if (bx1 >= ax2 || ax1 >= bx2 || ay1 >= by2 || by1 >= ay2){
            return area1 + area2;
        }
        int zx1,zy1,zx2,zy2;
        zx1 = Math.max(ax1, bx1);

        zx2 = Math.min(ax2, bx2);

        zy1 = Math.max(ay1, by1);

        zy2 = Math.min(ay2, by2);

        return area1 + area2 - (zx2 - zx1) * (zy2 - zy1);
    }

    //超时
    //319. 灯泡开关
    //初始时有 n 个灯泡处于关闭状态。
    //对某个灯泡切换开关意味着：如果灯泡状态为关闭，那该灯泡就会被开启；而灯泡状态为开启，那该灯泡就会被关闭。
    //第 1 轮，每个灯泡切换一次开关。即，打开所有的灯泡。
    //第 2 轮，每两个灯泡切换一次开关。 即，每两个灯泡关闭一个。
    //第 3 轮，每三个灯泡切换一次开关。
    //第 i 轮，每 i 个灯泡切换一次开关。 而第 n 轮，你只切换最后一个灯泡的开关。
    //找出 n 轮后有多少个亮着的灯泡。
    public static int bulbSwitch(int n) {
        boolean[] flag = new boolean[n + 1];
        for (int step = 1; step <= n; step++) {
            for (int i = 1; step * i <= n; i++) {
                flag[step * i] = !flag[step * i];
            }
        }
        int count = 0;
        for (int i = 0; i < flag.length; i++) {
            if (flag[i]){
                ++count;
            }
        }
        return count;
    }

    //大佬的
    //319. 灯泡开关
    //初始时有 n 个灯泡处于关闭状态。
    //对某个灯泡切换开关意味着：如果灯泡状态为关闭，那该灯泡就会被开启；而灯泡状态为开启，那该灯泡就会被关闭。
    //第 1 轮，每个灯泡切换一次开关。即，打开所有的灯泡。
    //第 2 轮，每两个灯泡切换一次开关。 即，每两个灯泡关闭一个。
    //第 3 轮，每三个灯泡切换一次开关。
    //第 i 轮，每 i 个灯泡切换一次开关。 而第 n 轮，你只切换最后一个灯泡的开关。
    //找出 n 轮后有多少个亮着的灯泡。
    public static int bulbSwitch2(int n) {
        return (int)Math.floor(Math.sqrt(n));
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

}
