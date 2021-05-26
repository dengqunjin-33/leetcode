package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode290 {
    //290. 单词规律
    public boolean wordPattern(String pattern, String s) {
        char[] chars = pattern.toCharArray();
        String[] split = s.split(" ");
        if (chars.length != split.length) {
            return false;
        }
        String[] target = new String[26];
        List<String> list = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (null == target[chars[i] - 'a']) {
                if (!list.contains(split[i])) {
                    list.add(split[i]);
                    target[chars[i] - 'a'] = split[i];
                } else {
                    return false;
                }
            } else {
                if (!target[chars[i] - 'a'].equals(split[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
