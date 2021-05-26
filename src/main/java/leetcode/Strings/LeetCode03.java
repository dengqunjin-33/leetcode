package leetcode.Strings;

import java.util.Arrays;

public class LeetCode03 {

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

}
