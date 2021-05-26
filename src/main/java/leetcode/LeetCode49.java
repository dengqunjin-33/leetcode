package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LeetCode49 {
    //49. 字母异位词分组
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new LinkedList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String s = Arrays.toString(c);
            if (map.containsKey(s)) {
                List set = map.get(s);
                set.add(str);
                map.put(s, set);
            } else {
                List<String> set = new LinkedList<>();
                set.add(str);
                map.put(s, set);
                result.add(set);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] s = {"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams(s);
    }
}
