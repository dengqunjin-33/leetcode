package leetcode.BitOperation;

public class LeetCode0504 {
    //面试题 05.04. 下一个数
    public static int[] findClosedNumbers(int num) {
        int[] target = {-1, -1};
        boolean minFlag = false;//标志位，用于标志最近小的数有没有计算过
        boolean maxFlag = false;//标志位，用于标志最近大的数有没有计算过
        int sum = 0;//1的个数
        int ans = 0;//num前n位二进制转换的值
        if ((num & 1) == 1) {
            ++sum;
            ans = 1;
        }
        for (int i = 1; i < 32; i++) {
            //如果最近小的数和最近大的数都计算过了，返回所得到的最近小和最近大的数
            if (minFlag && maxFlag) {
                return target;
            }

            int first = 1 << (i - 1);//用于计算num二进制第i-1位是0还是1
            int second = 1 << i;//用于计算num二进制第i位是0还是1

            if ((num & second) == second) {//第i位是1
                ++sum;
                ans += second;

                if (!minFlag && (num & first) == 0) { //判断第i位和第i-1位是不是10 是的话获取最近小的数
                    //清除前i位的1
                    target[1] = num - ans;

                    //n-1 到 n-1-sum位补1;
                    int temp = second >> 1;
                    int tempSum = 0;
                    for (int j = 0; j < sum; j++) {
                        tempSum += temp;
                        temp = temp >> 1;
                    }
                    target[1] = target[1] + tempSum;
                    minFlag = true;
                }
            } else {//第i位是0

                if (!maxFlag && (num & first) == first) {//判断第i位和第i-1位是不是01 是的话获取最近大的数
                    //清除前n位的1
                    target[0] = num - ans;

                    //给第i位补1，此时还有sum-1个1，给第0位到sum-1补1
                    int tempSum = second;
                    for (int j = 0; j < sum - 1; j++) {
                        tempSum += 1 << j;
                    }
                    target[0] = target[0] + tempSum;
                    maxFlag = true;
                }
            }
        }
        return target;
    }

    public static void main(String[] args) {
        findClosedNumbers(571603719);
    }
}
