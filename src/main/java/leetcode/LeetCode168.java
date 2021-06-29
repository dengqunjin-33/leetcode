package leetcode;

public class LeetCode168 {

    //168. Excel表列名称
    //给定一个正整数，返回它在 Excel 表中相对应的列名称。
    public static String convertToTitle(int columnNumber) {
        StringBuffer res = new StringBuffer();
        while (columnNumber != 0){
            columnNumber -= 1;
            int temp = columnNumber % 26;
            columnNumber /= 26;
            res.append((char) ('A' + temp));
        }
        return res.reverse().toString();
    }
}
