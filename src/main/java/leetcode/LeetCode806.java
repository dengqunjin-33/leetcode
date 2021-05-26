package leetcode;

public class LeetCode806 {
    //860. 柠檬水找零
    public static boolean lemonadeChange(int[] bills) {
        int sum = 0;
        int[] count = {0, 0};
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                sum += 5;
                count[0]++;
            } else if (10 == bills[i]) {
                if (sum >= 5 && count[0] >= 1) {
                    count[1]++;
                    count[0]--;
                    sum += 5;
                } else {
                    return false;
                }
            } else {
                if (sum >= 15) {
                    if (count[0] >= 1 && count[1] >= 1) {
                        count[0]--;
                        count[1]--;
                    } else if (count[0] >= 3) {
                        count[0] -= 3;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] x = {5, 5, 10, 10, 20};
        lemonadeChange(x);

    }
}
