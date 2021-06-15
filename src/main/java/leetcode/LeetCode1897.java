package leetcode;

public class LeetCode1897 {

    //1897. 重新分配字符使所有字符串都相等
    //给你一个字符串数组 words（下标 从 0 开始 计数）。
    //在一步操作中，需先选出两个 不同 下标 i 和 j，其中 words[i] 是一个非空字符串，接着将 words[i] 中的 任一 字符移动到 words[j] 中的 任一 位置上。
    //如果执行任意步操作可以使 words 中的每个字符串都相等，返回 true ；否则，返回 false 。
    public boolean makeEqual(String[] words) {
        int len = words.length;
        int[] charCount = new int[129];
        for (String word : words) {
            for (char c : word.toCharArray()) {
                charCount[c]++;
            }
        }
        for (int i : charCount) {
            if ( i % len != 0) {
                return false;
            }
        }
        return true;
    }
}
