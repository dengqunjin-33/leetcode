package leetcode.Greedy;

import java.util.*;

public class LeetCodeGreedy {

    //执行用时：1 ms, 在所有 Java 提交中击败了51.71%的用户
    //内存消耗：37.1 MB, 在所有 Java 提交中击败了13.29%的用户
    //68. 文本左右对齐
    //给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
    //你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
    //要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
    //文本的最后一行应为左对齐，且单词之间不插入额外的空格。
    //说明:
    //单词是指由非空格字符组成的字符序列。
    //每个单词的长度大于 0，小于等于 maxWidth。
    //输入单词数组 words 至少包含一个单词。
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int curIndex = 0;
        int curLen = 0;
        int spaceTemp = 0;
        int count;
        int space;
        StringBuffer sb;
        for(int i = 0,end = words.length; i < end; ++i){
            if ((curLen + words[i].length()) > maxWidth){
                sb = new StringBuffer();
                count = i - curIndex - 1;
                if (0 == count){
                    sb.append(words[curIndex]);
                    for (int j = sb.length() + 1; j <= maxWidth; j++) {
                        sb.append(' ');
                    }
                }else {
                    int maxSpace = maxWidth - curLen + spaceTemp;
                    for (int j = curIndex; j < i; j++) {
                        sb.append(words[j]);
                        //贪心补最多的空格
                        space = (int) Math.ceil((0.0 + maxSpace) / count);
                        --count;
                        for (int k = 0; k < space; k++) {
                            sb.append(' ');
                        }
                        maxSpace -= space;
                    }
                }
                res.add(sb.toString());
                curLen = 0;
                curIndex = i;
                spaceTemp = 0;
            }

            curLen += words[i].length() + 1;
            ++spaceTemp;
        }

        //最后一行左对齐
        sb = new StringBuffer();
        for (int i = curIndex,end = words.length; i < end - 1; i++) {
            sb.append(words[i]).append(' ');
        }
        sb.append(words[words.length - 1]);
        while (sb.length() < maxWidth){
            sb.append(' ');
        }
        res.add(sb.toString());

        return res;
    }

    //121. 买卖股票的最佳时机
    public static int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int buy = prices[0];
        int sell = prices[1];
        int max = Math.max(sell - buy, 0);
        for (int i = 1; i < prices.length - 1; i++) {
            if (buy > prices[i]) {
                buy = prices[i];
                sell = prices[i + 1];
            } else {
                sell = Math.max(sell, prices[i + 1]);
            }
            max = Math.max(sell - buy, max);
        }
        return max;
    }

    //122. 买卖股票的最佳时机 II
    public int maxProfitII(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }

        int sum = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                sum += prices[i + 1] - prices[i];
            }
        }

        return sum;
    }

    //执行用时：5 ms, 在所有 Java 提交中击败了81.11%的用户
    //内存消耗：38.1 MB, 在所有 Java 提交中击败了36.60%的用户
    //179. 最大数
    //给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
    //注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
    public static String largestNumber(int[] nums) {
        if (1 == nums.length){
            return String.valueOf(nums[0]);
        }
        String [] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs,(a,b)-> (b + a).compareTo(a + b));
        StringBuffer resSb = new StringBuffer();
        for(String str : strs){
            resSb.append(str);
        }
        int index = 0;
        for (int i = 0; i < resSb.length(); i++) {
            if (resSb.charAt(i) == '0'){
                ++index;
            }else {
                break;
            }
        }
        String res = resSb.substring(index);
        return res.length() == 0 ? "0" : res;
    }

    //392. 判断子序列
    //给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
    //字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
    //（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
    //进阶：
    //如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
    public static boolean isSubsequence(String s, String t) {
        int sIndex = 0;
        int sLen = s.length();
        for (int i = 0; i < t.length(); i++) {
            if (sIndex >= sLen){
                break;
            }
            if (s.charAt(sIndex) == t.charAt(i)){
                sIndex++;
            }
        }
        return sIndex >= sLen;
    }

    //执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：36.6 MB, 在所有 Java 提交中击败了76.24%的用户
    //409. 最长回文串
    //给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
    //在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
    public int longestPalindrome(String s) {
        int[] counts = new int[128];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            ++counts[ch];
        }
        int sum = 0;
        for (int count : counts) {
            sum += count % 2 == 0 ? count : count - 1;
        }
        return sum == s.length() ? sum : sum + 1;
    }

    //执行用时：12 ms, 在所有 Java 提交中击败了98.90%的用户
    //内存消耗：40.7 MB, 在所有 Java 提交中击败了49.95%的用户
    //561. 数组拆分 I
    //给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
    //返回该 最大总和 。
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i+=2) {
            sum += nums[i];
        }
        return sum;
    }

    //502. IPO
    //执行用时：90 ms, 在所有 Java 提交中击败了38.37%的用户
    //内存消耗：59.2 MB, 在所有 Java 提交中击败了6.98%的用户
    //假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
    //给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
    //最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
    //总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
    //答案保证在 32 位有符号整数范围内。
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        if (k == 0){
            return w;
        }
        int n = profits.length;
        int[][] projects = new int[n][2];
        for(int i=0;i<n;i++){
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }
        Arrays.sort(projects, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a,b)->b-a);

        int i = 0;
        while (k-- > 0) {
            while (i < n && projects[i][0] <= w) {
                priorityQueue.add(projects[i++][1]);
            }
            if (priorityQueue.isEmpty()) {
                break;
            }
            w += priorityQueue.poll();
        }
        return w;
    }

    public static void main(String[] args) {
        findMaximizedCapital(2,0,new int[]{1,2,3},new int[]{0,1,1});
    }

    //执行用时：1 ms, 在所有 Java 提交中击败了53.14%的用户
    //内存消耗：35.2 MB, 在所有 Java 提交中击败了47.13%的用户
    //670. 最大交换
    //给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
    public static int maximumSwap(int num) {
        Stack<Integer> stack = new Stack<>();
        while (num != 0){
            stack.push(num % 10);
            num /= 10;
        }
        Integer[] integers = new Integer[stack.size()];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = stack.pop();
        }

        boolean flag = false;
        for (int i = 0; i < integers.length - 1; i++) {
            if (flag){
                break;
            }
            int index = -1;
            for (int j = i + 1; j < integers.length; j++) {
                if (integers[i] < integers[j]){
                    if (index == -1){
                        index = j;
                    }else {
                        if (integers[index] <= integers[j]){
                            index = j;
                        }
                    }
                }
            }
            if (-1 != index){
                integers[index] ^= integers[i];
                integers[i] ^= integers[index];
                integers[index] ^= integers[i];
                flag = true;
            }
        }
        int res = 0;
        for(Integer integer : integers){
            res = res * 10 + integer;
        }
        return res;
    }

    //执行用时：1 ms, 在所有 Java 提交中击败了45.65%的用户
    //内存消耗：36.4 MB, 在所有 Java 提交中击败了54.55%的用户
    //678. 有效的括号字符串
    //给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
    //任何左括号 ( 必须有相应的右括号 )。
    //任何右括号 ) 必须有相应的左括号 ( 。
    //左括号 ( 必须在对应的右括号之前 )。
    //* 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
    //一个空字符串也被视为有效字符串。
    public static boolean checkValidString(String s) {
        if (s.isEmpty()){
            return true;
        }
        char[] chars = s.toCharArray();
        //*的数量
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        for(int i = 0; i < chars.length; ++i){
            char ch = chars[i];
            if ('*' == ch){
                countStack.push(i);
            }else if ('(' == ch){
                stack.push(i);
            }else if (')' == ch){
                if (!stack.isEmpty()){
                    stack.pop();
                }else if (!countStack.isEmpty()){
                    countStack.pop();
                }else {
                    return false;
                }
            }
        }

        if (countStack.size() < stack.size()){
            return false;
        }
        while (!countStack.isEmpty() && !stack.isEmpty()) {
            if (countStack.peek() < stack.peek()) {
                return false;
            }
            countStack.pop();
            stack.pop();
        }
        return true;
    }

    //大佬的写法
    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：36.1 MB, 在所有 Java 提交中击败了91.21%的用户
    //678. 有效的括号字符串
    //给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
    //任何左括号 ( 必须有相应的右括号 )。
    //任何右括号 ) 必须有相应的左括号 ( 。
    //左括号 ( 必须在对应的右括号之前 )。
    //* 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
    //一个空字符串也被视为有效字符串。
    public static boolean checkValidString2(String s) {
        int left = 0, right = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            left += (s.charAt(i) == ')') ? -1 : 1;
            right += (s.charAt(n - i - 1) == '(') ? -1 : 1;
            if (left < 0 || right < 0) {
                return false;
            }
        }
        return true;
    }

    //执行用时：6 ms, 在所有 Java 提交中击败了97.06%的用户
    //内存消耗：39.1 MB, 在所有 Java 提交中击败了25.14%的用户
    //680. 验证回文字符串 Ⅱ
    //给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
    public boolean validPalindrome(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len >> 1; i++) {
            if (chars[i] != chars[len - i - 1]){
                int left = i;
                int right = len - i - 2;
                boolean flag1 = true;
                while (left < right){
                    if (chars[left] != chars[right]){
                        flag1 = false;
                        break;
                    }
                    ++left;
                    --right;
                }
                left = i + 1;
                right = len - i - 1;

                boolean flag2 = true;
                while (left < right){
                    if (chars[left] != chars[right]){
                        flag2 = false;
                        break;
                    }
                    ++left;
                    --right;
                }
                return flag1 || flag2;
            }
        }
        return true;
    }

    //执行用时：3 ms, 在所有 Java 提交中击败了71.06%的用户
    //内存消耗：37.9 MB, 在所有 Java 提交中击败了32.00%的用户
    //781. 森林中的兔子
    //森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。\
    public static int numRabbits(int[] answers) {
        if (0 == answers.length){
            return 0;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : answers){
            Integer value = map.getOrDefault(num, 0);
            map.put(num,value + 1);
        }
        Set<Integer> set = map.keySet();
        int sum = 0;
        for(Integer key : set){
            Integer count = map.get(key);
            int remainder = count % (key + 1);
            if (remainder != 0){
                count += key + 1 - remainder;
            }
            sum += count;
        }
        return sum;
    }


    //执行用时：1 ms, 在所有 Java 提交中击败了93.34%的用户
    //内存消耗：37.6 MB, 在所有 Java 提交中击败了91.90%的用户
    //781. 森林中的兔子
    //森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。\
    public static int numRabbits2(int[] answers) {
        if (0 == answers.length){
            return 0;
        }
        int[] nums = new int[1000];
        for (int num : answers){
            ++nums[num];
        }
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            if (nums[i] != 0){
                sum += nums[i] % (i + 1) == 0 ? nums[i] : nums[i] + i + 1 - nums[i] % (i + 1);
            }
        }
        return sum;
    }

    //执行用时：10 ms, 在所有 Java 提交中击败了90.36%的用户
    //内存消耗：38.9 MB, 在所有 Java 提交中击败了23.50%的用户
    //910. 最小差值 II
    //给你一个整数数组 A，对于每个整数 A[i]，可以选择 x = -K 或是 x = K （K 总是非负整数），并将 x 加到 A[i] 中。
    //在此过程之后，得到数组 B。
    //返回 B 的最大值和 B 的最小值之间可能存在的最小差值。
    public static int smallestRangeII(int[] nums, int k) {
        if (nums.length < 2){
            return 0;
        }
        Arrays.sort(nums);
        int res=nums[nums.length-1]-nums[0];
        //遍历求最小的最大值和最小值的差
        for(int i = 0;i < nums.length - 1;i++){
            int high = Math.max(nums[i] + k,nums[nums.length-1]-k);
            int low = Math.min(nums[i + 1] - k,nums[0] + k);
            res = Math.min(res,high-low);
        }
        //返回结果
        return res;
    }
}
