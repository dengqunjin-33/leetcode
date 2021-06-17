package leetcode.weekgame;

public class LeetCode239w {

    //1848. 到目标元素的最小距离
    //给你一个整数数组 nums （下标 从 0 开始 计数）以及两个整数 target 和 start ，请你找出一个下标 i ，满足 nums[i] == target 且 abs(i - start) 最小化 。注意：abs(x) 表示 x 的绝对值。
    //返回 abs(i - start) 。
    //题目数据保证 target 存在于 nums 中。
    public static int getMinDistance(int[] nums, int target, int start) {
        if (nums[start] == target){
            return 0;
        }

        int maxIndex = Math.max(start,nums.length - start);
        for (int i = 1; i <= maxIndex; i++) {
            if (start - i >= 0 && nums[start - i] == target){
                return i;
            }
            if (start + i < nums.length && nums[start + i] == target){
                return i;
            }
        }

        return 0;
    }

    //大佬的(为什么我就没想到呢。。。)
    //1849. 将字符串拆分为递减的连续值
    //给你一个仅由数字组成的字符串 s 。
    //请你判断能否将 s 拆分成两个或者多个 非空子字符串 ，使子字符串的 数值 按 降序 排列，且每两个 相邻子字符串 的数值之 差 等于 1 。
    //例如，字符串 s = "0090089" 可以拆分成 ["0090", "089"] ，数值为 [90,89] 。这些数值满足按降序排列，且相邻值相差 1 ，这种拆分方法可行。
    //另一个例子中，字符串 s = "001" 可以拆分成 ["0", "01"]、["00", "1"] 或 ["0", "0", "1"] 。然而，所有这些拆分方法都不可行，因为对应数值分别是 [0,1]、[0,1] 和 [0,0,1] ，都不满足按降序排列的要求。
    //如果可以按要求拆分 s ，返回 true ；否则，返回 false 。
    //子字符串 是字符串中的一个连续字符序列。
    public boolean splitString(String s) {
        long t = 0;     //枚举第一个数字的值，因为s长度为20，所以会超过int，要用long类型
        for (int i = 0; i < s.length() - 1; i++) {  //因为必须要分割成两个子串，所以最后一个字符不可能是组成第一个数字的字符，我们这里也是为了防止刚好20位导致long也会溢出的情况
            t = t * 10 + s.charAt(i) - '0'; //把当前字符加入到组成第一个数字的字符集中
            if(t > 10000000000L)    //如果t大于10^10那么后面最多还有9位数，所以不可能组成递减的连续值
            {
                return false;
            }
            if (dfs(s, t, i + 1))   //把t当作第一个数字，找寻后面递减的数
            {
                return true;
            }
        }
        return false;
    }

    //s要分割的字符串；pre前面一个数的值；k当前字符串已经用到了哪个位置
    private boolean dfs(String s, long pre, int k) {
        //代表能组成递减的连续值
        if (k == s.length()) {
            return true;
        }
        //枚举pre后面的一个数字的值
        long t = 0;
        //从第k个字符开始组成数字
        for (int i = k; i < s.length(); i++) {
            t = t * 10 + s.charAt(i) -'0';
            if(t > 10000000000L) {
                return false;
            }
            //如果前面一个数字和当前数组相差为1，则继续往下面寻找满足条件的数组
            if (pre - 1 == t && dfs(s, t, i + 1))
            {
                return true;
            }
            //当前组成的数大于前面的数表示不符合要求，直接返回false
            if (t >= pre)
            {
                return false;
            }
        }
        return false;
    }

}
