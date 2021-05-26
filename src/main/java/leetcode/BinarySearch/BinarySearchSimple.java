package leetcode.BinarySearch;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchSimple {

    //69. x 的平方根
    //实现 int sqrt(int x) 函数。
    //计算并返回 x 的平方根，其中 x 是非负整数。
    //由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
    public int mySqrt(int x) {
        int s = x;
        if(x==0) {
            return 0;
        }
        return ((int)(sqrts(x, s)));
    }

    public double sqrts(double x,int s){
        double res = (x + s / x) / 2;
        if (res == x) {
            return x;
        } else {
            return sqrts(res,s);
        }
    }
}
