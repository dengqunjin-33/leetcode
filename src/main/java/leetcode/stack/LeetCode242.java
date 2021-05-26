package leetcode.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode242 {

    //242. 有效的字母异位词
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        for (int i = 0; i < s.length(); i++) {
            if (sChars[i] != tChars[i]){
                return false;
            }
        }
        return true;
    }

    //242. 有效的字母异位词
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            list.add(s.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            list.remove(t.charAt(i));
        }
        return list.isEmpty();
    }
}
