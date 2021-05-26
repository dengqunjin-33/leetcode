package leetcode;

import java.util.Arrays;

public class LeetCode760To769 {

    //767. 重构字符串
    public static String reorganizeString(String S) {
        char[] ch = S.toCharArray();
        int size = ch.length;
        int[] map = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};

        for (char c : ch) {
            map[c - 'a'] += 100;
        }

        Arrays.sort(map);
        int pos = 0;
        for (int i = map.length - 1; i >= 0; i--) {
            int m = map[i] / 100;//除数是频率
            if (m > (size + 1) / 2) {
                return "";
            }
            char c = (char) ('a' + map[i] % 100);//余数是下标
            for (int j = 0; j < m; j++) {
                if (pos >= size) {
                    pos = 1;//偶数结束
                }
                ch[pos] = c;
                pos += 2;
            }
        }
        return String.valueOf(ch);
    }

    public static void main(String[] args) {
        reorganizeString("aab");
    }
}
