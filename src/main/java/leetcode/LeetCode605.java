package leetcode;

public class LeetCode605 {

    //605. 种花问题
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int sum = 0;

        if (flowerbed.length == 1) {
            if (flowerbed[0] == 0) {
                ++sum;
            }
            return sum >= n;
        }

        if (0 == flowerbed[0] && flowerbed[1] == 0) {
            flowerbed[0] = 1;
            ++sum;
        }
        for (int i = 1; i < flowerbed.length - 1; i++) {
            if (0 == flowerbed[i - 1] && 0 == flowerbed[i + 1] && 0 == flowerbed[i]) {
                flowerbed[i] = 1;
                ++sum;
            }
        }

        if (0 == flowerbed[flowerbed.length - 1] && 0 == flowerbed[flowerbed.length - 2]) {
            ++sum;
        }
        return sum >= n;
    }

    public static void main(String[] args) {
        int[] x = {1, 0, 1, 0, 1, 0, 1};
        canPlaceFlowers(x, 1);
    }
}
