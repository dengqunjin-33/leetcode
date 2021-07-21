package leetcode.stack;

import java.util.Stack;

/**
 * @author 86134
 */
public class LeetCodeStack {

    //执行用时：9 ms, 在所有 Java 提交中击败了90.83%的用户
    //内存消耗：45.6 MB, 在所有 Java 提交中击败了61.39%的用户
    //962. 最大宽度坡
    //给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
    //找出 A 中的坡的最大宽度，如果不存在，返回 0 。
    public int maxWidthRamp(int[] A) {
        int n = A.length;
        int maxWidth = 0;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0;i < n;i++) {
            if (stack.isEmpty() || A[stack.peek()] > A[i]) {
                stack.push(i);
            }
        }

        for (int i = n - 1;i > maxWidth;i--) {
            while (!stack.isEmpty() && A[stack.peek()] <= A[i]) {
                int pos = stack.pop();
                maxWidth = Math.max(maxWidth, i - pos);
            }
        }
        return maxWidth;
    }
}
