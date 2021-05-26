package leetcode.Strings;

import java.util.*;

public class LeetCode139 {
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

    public static void main(String[] args) {
        //"catsandog"
        //["cats","dog","sand","and","cat"]
        String[] strs = {"cats", "dog", "sand", "and", "cat"};
        List<String> list = new ArrayList();
        Collections.addAll(list, strs);
        wordBreak("catcatssanddogand", list);
    }
}
