package leetcode;

public class Leetcode170To179 {

    //171. Excel表列序号
    public static int titleToNumber(String s) {
        char[] charArray = s.toCharArray();
        int res = 0;
        for (int i = 0; i < charArray.length; i++) {
            res = res * 26 + (charArray[i] - 66);
        }
        return res;
    }

    //172. 阶乘后的零
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }

    //175. 组合两个表
    /**
     * select FirstName, LastName, City, State from Person p left join  Address a on p.PersonId = a.PersonId
     */

    //176.第二高的薪水
    /**
     * select IFNULL((select distinct(Salary)
     * from Employee
     * order by Salary desc
     * limit 1,1),null) as SecondHighestSalary
     */
}
