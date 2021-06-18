package leetcode.Strings;

import java.util.*;

public class LeetCodeStrings {

    //3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        boolean[] flag = new boolean[256];
        int maxLen = 0;
        for (int i = 0; i < chars.length; i++) {
            int temp = 0;
            for (int j = i; j < chars.length; j++) {
                if (flag[chars[j]]){
                    break;
                }
                else {
                    flag[chars[j]] = true;
                    ++temp;
                }
            }
            maxLen = Math.max(maxLen,temp);
            Arrays.fill(flag,false);
        }
        return maxLen;
    }

    //139. 单词拆分
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    //402. 移掉K位数字
    //给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
    //注意:
    //num 的长度小于 10002 且 ≥ k。
    //num 不会包含任何前导零。
    public static String removeKdigits(String num, int k) {
        if (num.length() <= k){
            return String.valueOf(0);
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while(k > 0 && !stack.isEmpty() && c < stack.peek()){
                stack.pop();
                k--;
            }
            if( c != '0' || !stack.isEmpty()){
                stack.push(c);
            }
        }

        while( k > 0 && !stack.isEmpty()){
            stack.pop();
            k--;
        }

        if (stack.size() == 0){
            return String.valueOf(0);
        }

        StringBuffer buffer = new StringBuffer();
        while(!stack.isEmpty()){
            buffer.append(stack.pop());
        }

        return buffer.reverse().toString();
    }

    public static void main(String[] args) {
        removeKdigits("10001",4);
    }
}
