package leetcode;

import java.util.Random;

public class demo {
    /**
     * 【笔试题】
     * 机试题目注意: 请不要轻视这题目，完成得高质量，绝对不容易。
     * 请独立完成，其他注意事项见下方 FAQ。
     * 请将完成的笔试题以“姓名-后端开发-完成所需时间“格式命名
     * 要求：以下题目答题是 30 分钟,旨在考察面试者多方面的能力。请尽可能多答，
     * 算法题可以任何编程语言的代码（欢迎加关键注释），也可以写出大概想法（得分会减少）。
     * 题目：我们做了一个活动，根据用户的积分来抽奖，用户的积分都保存在一个数组里面
     * arr = [20, 34, 160, 2…]，数组下标就是用户的 ID，则这里：
     * ID 为 0 的用户的积分是 arr[0] 等于 20 分。
     * ID 为 1 的用户的积分是 arr[1] 等于 34 分。
     * 请你设计一个抽奖算法，随机抽出一位中奖用户，要求积分越高中奖概率越高。
     * 返回值是中奖用户的 ID
     * PS: 1<= arr.length <= 50000 且 1<= arr[i] <= 50000
     * 代码写出算法，
     * 并分析其时间复杂度，
     * 为其编写尽量多 unit test。
     * FAQ：
     * 我可以上网吗？－－ 可以，make yourself comfortable。
     * 我可以问别人吗？ －－ 请独立完成，if you lie , we’ll know sooner or later。
     * 我超过 30 分钟怎么办？请尽量按时提交。如果超过 30 分钟，请标注下完成用时。
     * 我做不完怎么办？没关系请尽量按点顺序完成。
     * @param arr 中奖用户数组
     * @return 用户ID
     */
    public int luckDraw(int[] arr){
        //思路 前缀和 +  随机数 + 二分查找
        //时间复杂度：初始化的时间复杂度为 O(n)，每次选择的时间复杂度为 O(log n)其中 n 是数组 arr 的长度。
        //空间复杂度：O(n)，即为前缀和数组 res 需要使用的空间。
        int len = arr.length;
        if (len == 0){
            return -1;
        }

        long[] res = new long[len];
        res[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            res[i] = res[i - 1] + arr[i];
        }

        long rand = randomLong(0,res[len - 1]);

        return binarySearch(rand,res);
    }

    /**
     * 区间内随机值
     * @param start 开始
     * @param end 结束
     * @return 随机值
     */
    public long randomLong(long start,long end){
        Random random = new Random();
        double nextDouble = random.nextDouble() * (end - start) + 1;
        double floor = Math.floor(nextDouble);
        long rand = Double.doubleToLongBits(floor);
        return rand + start;
    }

    /**
     * 二分查找
     * @param rand 随机值
     * @param arr 数组
     * @return 下标索引
     */
    public int binarySearch(long rand,long[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right){
            int mid = (left + right) >> 1;
            if (arr[mid] == rand){
                return mid;
            }else if (arr[mid] > rand){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return right;
    }
}
