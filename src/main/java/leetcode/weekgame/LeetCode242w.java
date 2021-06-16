package leetcode.weekgame;

import java.util.Arrays;

public class LeetCode242w {

    //1869. 哪种连续子字符串更长
    //给你一个二进制字符串 s 。如果字符串中由 1 组成的 最长 连续子字符串 严格长于 由 0 组成的 最长 连续子字符串，返回 true ；否则，返回 false 。
    //例如，s = "110100010" 中，由 1 组成的最长连续子字符串的长度是 2 ，由 0 组成的最长连续子字符串的长度是 3 。
    //注意，如果字符串中不存在 0 ，此时认为由 0 组成的最长连续子字符串的长度是 0 。字符串中不存在 1 的情况也适用此规则。
    public static boolean checkZeroOnes(String s) {
        int one = 0;
        int zero = 0;
        int count = 0;
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            if (!flag && s.charAt(i) == '1'){
                zero = Math.max(zero,count);
                flag = true;
                count = 0;
            }else if (flag && s.charAt(i) == '0'){
                one = Math.max(one,count);
                flag = false;
                count = 0;
            }
            ++count;
        }
        if (s.charAt(s.length() - 1) == '1'){
            one = Math.max(one,count);
        }else {
            zero = Math.max(zero,count);
        }
        return one > zero;
    }

    //1870. 准时到达的列车最小时速
    //给你一个浮点数 hour ，表示你到达办公室可用的总通勤时间。要到达办公室，你必须按给定次序乘坐 n 趟列车。
    //另给你一个长度为 n 的整数数组 dist ，其中 dist[i] 表示第 i 趟列车的行驶距离（单位是千米）。
    //每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间。
    //例如，第 1 趟列车需要 1.5 小时，那你必须再等待 0.5 小时，搭乘在第 2 小时发车的第 2 趟列车。
    //返回能满足你准时到达办公室所要求全部列车的 最小正整数 时速（单位：千米每小时），如果无法准时到达，则返回 -1 。
    //生成的测试用例保证答案不超过 107 ，且 hour 的 小数点后最多存在两位数字 。
    public int minSpeedOnTime(int[] dist, double hour) {
        if (dist.length > Math.ceil(hour)) {
            return -1;
        }
        // 搜索边界
        int left = 1, right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 如果以 mid 速度可达，那么就尝试减小速度
            if (check(dist, hour, mid)) {
                right = mid;
            }
            // 否则就需要加了
            else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] dist, double hour, int speed) {
        double cnt = 0.0;
        // 对除了最后一个站点以外的时间进行向上取整累加
        for (int i = 0; i < dist.length - 1; ++i) {
            // 除法的向上取整
            cnt += (dist[i] + speed - 1) / speed;
        }
        // 加上最后一个站点所需的时间
        cnt += (double) dist[dist.length - 1] / speed;
        return cnt <= hour;
    }

    //自己写的回溯算法
    //1871. 跳跃游戏 VII
    //给你一个下标从 0 开始的二进制字符串 s 和两个整数 minJump 和 maxJump 。一开始，你在下标 0 处，且该位置的值一定为 '0' 。当同时满足如下条件时，你可以从下标 i 移动到下标 j 处：
    //i + minJump <= j <= min(i + maxJump, s.length - 1) 且
    //s[j] == '0'.
    //如果你可以到达 s 的下标 s.length - 1 处，请你返回 true ，否则返回 false 。
    public static boolean canReach(String s, int minJump, int maxJump) {
        return canReach(s,minJump,maxJump,0);
    }

    public static boolean canReach(String s, int minJump, int maxJump,int index) {
        if (index == s.length() - 1){
            return s.charAt(index) == '0';
        }
        boolean flag = false;
        for (int i = index + minJump; i <= index + maxJump; i++) {
            if (i <= s.length() - 1 && s.charAt(i) == '0'){
                flag |= canReach(s,minJump,maxJump,i);
            }
            if (i >= s.length()){
                break;
            }
        }
        return flag;
    }

    //看别人的动态规划
    //1871. 跳跃游戏 VII
    //给你一个下标从 0 开始的二进制字符串 s 和两个整数 minJump 和 maxJump 。一开始，你在下标 0 处，且该位置的值一定为 '0' 。当同时满足如下条件时，你可以从下标 i 移动到下标 j 处：
    //i + minJump <= j <= min(i + maxJump, s.length - 1) 且
    //s[j] == '0'.
    //如果你可以到达 s 的下标 s.length - 1 处，请你返回 true ，否则返回 false 。
    public boolean canReach2(String s, int minJump, int maxJump) {
        //长度
        int len = s.length();
        //传唤为数组
        char[] chars = s.toCharArray();
        //记录某个点是否能达到 如果能到就是0 ,不能达到就是1
        int[] dp = new int[len + 1];
        //首先设置所有的点都不能达到
        Arrays.fill(dp,1);
        //但是chars[0] = '0' 那么第一个点一定是能达到的 那么设置为 0
        dp[1] = 0;
        //这个是前缀和的数组，记录的是dp这个数组的前缀和
        int[] bdp = new int[len + 1];
        //第一个一定是0
        bdp[1] = 0;
        //遍历数组
        for (int i = 2; i < len + 1 ; i++) {
            //选择chars[i - 1]为 0  通过判断这个点的 i - maxJump 到 i - minJump 点的和 是否比 这两个点的距离之和近
            //如果近就说明这里面至少存在一个0 那么就可以通过这个0 来到达chars[i - 1]这个点
            if (chars[i - 1] == '0') {
                if (i - minJump >= 1) {
                    int r = i - minJump;
                    int l = Math.max(i - maxJump, 1);
                    dp[i] = bdp[r] - bdp[l - 1] < r - l + 1 ? 0 : 1;
                }
            }
            //每次都维护前缀和数组,加入 0 或者 1
            bdp[i] += bdp[i - 1] + dp[i];
        }
        return dp[len] == 0;
    }
}
