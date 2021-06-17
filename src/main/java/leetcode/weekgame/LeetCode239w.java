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

    //todo
    //1849. 将字符串拆分为递减的连续值
    //给你一个仅由数字组成的字符串 s 。
    //请你判断能否将 s 拆分成两个或者多个 非空子字符串 ，使子字符串的 数值 按 降序 排列，且每两个 相邻子字符串 的数值之 差 等于 1 。
    //例如，字符串 s = "0090089" 可以拆分成 ["0090", "089"] ，数值为 [90,89] 。这些数值满足按降序排列，且相邻值相差 1 ，这种拆分方法可行。
    //另一个例子中，字符串 s = "001" 可以拆分成 ["0", "01"]、["00", "1"] 或 ["0", "0", "1"] 。然而，所有这些拆分方法都不可行，因为对应数值分别是 [0,1]、[0,1] 和 [0,0,1] ，都不满足按降序排列的要求。
    //如果可以按要求拆分 s ，返回 true ；否则，返回 false 。
    //子字符串 是字符串中的一个连续字符序列。
    public boolean splitString(String s) {
        return true;
    }

    //todo
    //1850. 邻位交换的最小次数
    //给你一个表示大整数的字符串 num ，和一个整数 k 。
    //如果某个整数是 num 中各位数字的一个 排列 且它的 值大于 num ，则称这个整数为 妙数 。可能存在很多妙数，但是只需要关注 值最小 的那些。
    //例如，num = "5489355142" ：
    //第 1 个最小妙数是 "5489355214"
    //第 2 个最小妙数是 "5489355241"
    //第 3 个最小妙数是 "5489355412"
    //第 4 个最小妙数是 "5489355421"
    //返回要得到第 k 个 最小妙数 需要对 num 执行的 相邻位数字交换的最小次数 。
    //测试用例是按存在第 k 个最小妙数而生成的。
    public int getMinSwaps(String num, int k) {
        return 1;
    }


}
