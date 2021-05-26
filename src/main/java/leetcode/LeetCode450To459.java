package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode450To459 {

    //454. 四数相加 II
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        Map<Integer, Integer> ab = new HashMap();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int tempSum = A[i] + B[j];
                Integer integer = ab.get(tempSum);
                if (null == integer) {
                    ab.put(tempSum, 1);
                } else {
                    ab.put(tempSum, integer + 1);
                }
            }
        }
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int tempSum = -(C[i] + D[j]);
                Integer integer = ab.get(tempSum);
                if (null == integer) {
                    continue;
                } else {
                    count = count + integer;
                }
            }
        }
        return count;
    }

    //455. 分发饼干
    public static int findContentChildren(int[] g, int[] s) {
        if (0 == g.length || 0 == s.length) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int sHigh = s.length - 1;
        int gHigh = g.length - 1;
        for (; ; ) {
            if (s[sHigh] >= g[gHigh]) {
                --sHigh;
                --gHigh;
                ++count;
            } else {
                --gHigh;
            }
            if (-1 == sHigh || -1 == gHigh) {
                return count;
            }
        }
    }

    //456.132模式
    public boolean find132pattern(int[] nums) {
        return true;
    }

    //459.大神解法 重复的子串
    public static boolean repeatedSubstringPattern(String s) {
        return (s + s).substring(1, s.length() * 2 - 1).contains(s);
    }

    public static void main(String[] args) {
        String s = "abaababaab";
        System.out.println(repeatedSubstringPattern(s));
    }
}
