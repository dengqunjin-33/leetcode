package leetcode;

public class LeetCode461To469 {

    //461 汉明距离
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int count = 0;
        for (int i = 31; i >= 0; i--) {
            if (1 == (z >> i & 1)) {
                count++;
            }
        }
        return count;
    }

    //463. 岛屿的周长
    public int islandPerimeter(int[][] grid) {
        int count = 0;//是陆地的个数
        int sum = 0;//消除的边界的个数
        int leftBoundary = -1;
        int topBoundary = -1;
        int rightBoundary = grid[0].length;
        int downBoundary = grid.length;
        for (int i = 0; i < downBoundary; i++) {
            for (int j = 0; j < rightBoundary; j++) {
                if (1 == grid[i][j]) {//是土地
                    ++count;
                    //判断四周是否是土地
                    int top = i - 1;
                    int down = i + 1;
                    int left = j - 1;
                    int right = j + 1;
                    if (topBoundary != top && 1 == grid[top][j]) {
                        sum++;
                    }

                    if (downBoundary != down && 1 == grid[down][j]) {
                        sum++;
                    }

                    if (leftBoundary != left && 1 == grid[i][left]) {
                        sum++;
                    }

                    if (rightBoundary != right && 1 == grid[i][right]) {
                        sum++;
                    }
                } else {
                    continue;
                }
            }
        }
        return 4 * count - sum;
    }

    //468. 验证IP地址
    public static String validIPAddress(String IP) {
        if (simpleValidIP(IP)) {
            return "Neither";
        } else {
            if (IP.contains(".")) {
                return validIPV4(IP);
            } else {
                return validIPV6(IP);
            }
        }
    }

    private static boolean simpleValidIP(String IP) {
        return IP.contains("::") || (IP.contains(".") && IP.contains(":"))
                || IP.startsWith(":") || IP.endsWith(":")
                || IP.startsWith(".") || IP.endsWith(".");
    }

    private static String validIPV6(String ip) {
        String[] split = ip.split(":");
        if (8 != split.length) {
            return "Neither";
        }
        for (String str : split) {
            if (str.length() <= 4) {
                char[] chars = str.toCharArray();
                for (char c : chars) {
                    if (charBetween(c, 48, 57) || charBetween(c, 65, 70) || charBetween(c, 97, 102)) {
                        continue;
                    } else {
                        return "Neither";
                    }
                }
            } else {
                return "Neither";
            }
        }
        return "IPv6";
    }

    private static boolean charBetween(char c, int begin, int end) {
        return (int) c >= begin && (int) c <= end;
    }

    private static String validIPV4(String ip) {
        String[] split = ip.split("\\.");
        if (4 != split.length) {
            return "Neither";
        }
        for (String str : split) {
            try {
                if ((str.length() > 1 && str.startsWith("0")) || Integer.parseInt(str) >>> 7 > 1) {
                    return "Neither";
                }
            } catch (Exception e) {
                return "Neither";
            }
        }
        return "IPv4";
    }


}
