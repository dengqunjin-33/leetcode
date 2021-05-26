package leetcode.BitOperation;

public class LeetCode754 {

    //754. 到达终点数字
    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0;
        while (target > 0) {
            target -= ++k;
        }
        return target % 2 == 0 ? k : k + 1 + k%2;
    }
}
