package leetcode;

public class LeetCode387 {

    //387. 字符串中的第一个唯一字符
    public int firstUniqChar(String s) {
        if (s.length() == 0){
            return -1;
        }
        return -1;
    }

    //387. 字符串中的第一个唯一字符
    public int firstUniqChar2(String s) {
        if (s.length() == 0){
            return -1;
        }
        boolean[] flag = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            if(!flag[s.charAt(i) - 'a']){
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(i) == s.charAt(j)){
                        flag[s.charAt(i) - 'a'] = true;
                    }
                }
                if (!flag[s.charAt(i) - 'a']){
                    return i;
                }
            }
        }
        return -1;
    }
}
