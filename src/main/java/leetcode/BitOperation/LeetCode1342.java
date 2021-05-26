package leetcode.BitOperation;

public class LeetCode1342 {

    //1342. 将数字变成 0 的操作次数
    //给你一个非负整数 num ，请你返回将它变成 0 所需要的步数。 如果当前数字是偶数，你需要把它除以 2 ；否则，减去 1 。
    public static int numberOfSteps(int num) {
        int step = 0;
        while (num != 0){
            if ((1 & num) == 1){
                num -= 1;
            }else {
                num = num >> 1;
            }
            step ++;
        }
        return step;
    }

}
