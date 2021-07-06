package leetcode.maths;

import java.util.*;

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

    //我的暴力解法  超时了
    //84. 柱状图中最大的矩形
    //给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    //求在该柱状图中，能够勾勒出来的矩形的最大面积。
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int temp = heights[i];
            int count = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (heights[j] >= temp){
                    ++count;
                }else {
                    break;
                }
            }
            for (int j = i + 1; j < heights.length; j++) {
                if (heights[j] >= temp){
                    ++count;
                }else {
                    break;
                }
            }
            res = Math.max(res,count * temp);
        }
        return res;
    }

    //大佬的单调栈
    //84. 柱状图中最大的矩形
    //给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    //求在该柱状图中，能够勾勒出来的矩形的最大面积。
    public static int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> mono_stack = new Stack<>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            //确定左边界
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        mono_stack.clear();

        for (int i = n - 1; i >= 0; --i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            //确定右边界
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

//    public static void main(String[] args) {
//        largestRectangleArea2(new int[]{2,1,5,6,2,3});
//    }


    //自己写的
    //149. 直线上最多的点数
    //给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
    public static int maxPoints(int[][] points) {
        if(points.length < 2){
            return points.length;
        }
        //y = kx + b
        Map<MyPoint, List<Integer>> map = new HashMap<>();
        Map<Integer,List<Integer>> lineY  = new HashMap<>();
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                //x = a的情况
                if (points[i][0]  == points[j][0]){
                    List<Integer> list;
                    if (lineY.containsKey(points[j][0])){
                        list = lineY.get(points[j][0]);
                        if (!list.contains(points[i][1])){
                            list.add(points[i][1]);
                        }
                        if (!list.contains(points[j][1])){
                            list.add(points[j][1]);
                        }
                    }else {
                        list = new ArrayList();
                        list.add(points[i][1]);
                        list.add(points[j][1]);
                        lineY.put(points[j][0],list);
                    }
                    max = Math.max(max,list.size());
                }else {
                    double k = (points[i][1] - points[j][1] + 0.0) / (points[i][0] - points[j][0]);
                    double b = points[i][1] - points[i][0] * k;
                    MyPoint point = new MyPoint(k,b);
                    List<Integer> list;
                    if (map.containsKey(point)){
                        list = map.get(point);
                        if (!list.contains(points[i][0])){
                            list.add(points[i][0]);
                        }
                        if (!list.contains(points[j][0])){
                            list.add(points[j][0]);
                        }
                    }else {
                        list = new ArrayList();
                        list.add(points[i][0]);
                        list.add(points[j][0]);
                        map.put(point,list);
                    }
                    max = Math.max(max,list.size());
                }
            }
        }

        return max;
    }

    //定义斜率结构
    static class MyPoint{
        double k;
        double b;

        public MyPoint(double b){
            this.b = b;
        }

        public MyPoint(double k,double b){
            this.k = k;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MyPoint myPoint = (MyPoint) o;
            return Double.compare(myPoint.k, k) == 0 && Double.compare(myPoint.b, b) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(k, b);
        }
    }

    //看了大佬的思路后自己写的
    //额 效率没有我上面高,因为字符串拼接很耗费性能
    //149. 直线上最多的点数
    //给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
    public static int maxPoints2(int[][] points) {
        if(points.length < 2){
            return points.length;
        }
        //y = kx + b
        Map<String, List<Integer>> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                //x = a的情况
                if (points[i][0]  == points[j][0]){
                    List<Integer> list;
                    if (map.containsKey("_" + points[j][0])){
                        list = map.get("_" + points[j][0]);
                        if (!list.contains(points[i][1])){
                            list.add(points[i][1]);
                        }
                        if (!list.contains(points[j][1])){
                            list.add(points[j][1]);
                        }
                    }else {
                        list = new ArrayList();
                        list.add(points[i][1]);
                        list.add(points[j][1]);
                        map.put("_" + points[j][0],list);
                    }
                    max = Math.max(max,list.size());
                }else {
                    double k = (points[i][1] - points[j][1] + 0.0) / (points[i][0] - points[j][0]);
                    double b = points[i][1] - points[i][0] * k;
                    List<Integer> list;
                    if (map.containsKey(k + "_" + b)){
                        list = map.get(k + "_" + b);
                        if (!list.contains(points[i][0])){
                            list.add(points[i][0]);
                        }
                        if (!list.contains(points[j][0])){
                            list.add(points[j][0]);
                        }
                    }else {
                        list = new ArrayList();
                        list.add(points[i][0]);
                        list.add(points[j][0]);
                        map.put(k + "_" + b,list);
                    }
                    max = Math.max(max,list.size());
                }
            }
        }

        return max;
    }

    //在大佬的基础上优化了一下
    //额 效率比我的效率高
    //149. 直线上最多的点数
    private int n;
    public int maxPoints3(int[][] points) {
        //长度小于等于2，直接返回
        n = points.length;
        if(n<=2){
            return n;
        }
        return process(points);
    }
    public int process(int[][] points){
        int res = 0;
        //遍历所有点
        for (int i = 0; i <n ; i++) {
            //当直线上的数大于n/2时，此直线上的数最多，没有其他直线的数会大于n/2
            //第i个点所在直线的数目res >= n-i时返回，从第i个节点出发最多找到n-i个点在同一直线上
            if(res > n/2 || res >= n-i){
                break;
            }
            Map<Integer,Integer> map = new HashMap<>();
            //该点直线上前面的点已经包含了后面的点，所以不用遍历前面的点
            for (int j = i+1; j <n ; j++) {
                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];
                if(dx == 0){
                    dy = 1;
                }else if(dy == 0){
                    dx = 1;
                }else {
                    //需要统一 -1/2和 1/-2的情况
                    if(dy < 0){
                        dx = -dx;
                        dy = -dy;
                    }
                    //求最简分式
                    int gcdXY = gcd(Math.abs(dx),Math.abs(dy));
                    dx /= gcdXY;
                    dy /= gcdXY;
                }
                int key = dx*931+dy*937;
                int value;
                if(null == map.get(key)){
                    value = 2;
                }else{
                    value = map.get(key) + 1;
                }
                map.put(key,value);
                res = Math.max(value,res);
            }
        }
        return res;
    }
    //求最大公因素
    public int gcd(int a,int b){
        return b == 0 ?a:gcd(b,a%b);
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

    //计数法  妙啊
    //869. 重新排序得到 2 的幂
    //给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
    //如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
    public boolean reorderedPowerOf2(int N) {
        int[] A = count(N);
        for (int i = 0; i < 31; ++i) {
            if (Arrays.equals(A, count(1 << i))) {
                return true;
            }
        }
        return false;
    }

    public int[] count(int N) {
        int[] ans = new int[10];
        while (N > 0) {
            ans[N % 10]++;
            N /= 10;
        }
        return ans;
    }

    //1344. 时钟指针的夹角
    //给你两个数 hour 和 minutes 。请你返回在时钟上，由给定时间的时针和分针组成的较小角的角度（60 单位制）。
    public static double angleClock(int hour, int minutes) {
        double our = 30 * minutes / 60.0 + hour * 30;
        double min = 6 * minutes;
        double c = Math.abs(min - our);
        return Math.min(c,360 - c);
    }

    public static void main(String[] args) {
        angleClock(3,15);
    }

}
