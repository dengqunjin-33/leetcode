package leetcode;

import java.util.Arrays;

public class LeetCode621To629 {

    /**
     * 621. 任务调度器
     * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
     * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     * 你需要计算完成所有任务所需要的 最短时间 。
     */
    public static int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }
        int[] x = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        for (char ch : tasks) {
            int y = (int) ch - 'A';
            x[y] += 100;
        }
        Arrays.sort(x);
        int[] target = new int[x[25] / 100];
        int sum = 0;
        int count = 0;
        for (int i = 24; i >= 0; i--) {
            if (x[i] / 100 == 0) {
                break;
            }
            for (int j = 0; j < x[i] / 100; j++) {
                if (target[j] == n) {
                    count++;
                } else {
                    if (j != target.length - 1) {
                        ++sum;
                    }
                    target[j]++;
                }

            }
        }
        return Math.max(target[target.length - 1] + 1 + Math.max(sum + count, (target.length - 1) * (n + 1)), tasks.length);
    }

    public static void main(String[] args) {
        //char [] x = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        char[] x = {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'};
        leastInterval(x, 2);
    }


}
