package leetcode.BitOperation;

public class LeetCode389 {
    //389. 找不同
    public char findTheDifference(String s, String t) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum ^= s.charAt(i);
            sum ^= t.charAt(i);
        }
        sum ^= t.charAt(s.length());
        return (char) sum;
    }
}
