package leetcode;

public class LeetCode30To39 {
    //34. 在排序数组中查找元素的第一个和最后一个位置
    public static int[] searchRange(int[] nums, int target) {
        if (nums.length == 1 && target == nums[0]) {
            int[] result = {0, 0};
            return result;
        }
        int low = 0;
        int high = nums.length - 1;
        int mid = high >> 1;
        int[] result = {-1, -1};
        //二分查找：for循环查找目标存不存在
        xx:
        for (; ; ) {
            if (low > high) {
                return result;
            }
            if (target > nums[mid]) {
                low = ++mid;
            } else if (target == nums[mid]) {
                result[0] = result[1] = mid;
                break xx;
            } else {
                high = --mid;
            }
            mid = (low + high) >> 1;
        }

        //存在->结束下标往右移
        for (int i = mid + 1; i <= high; i++) {
            if (target == nums[i]) {
                result[1] = i;
            } else {
                break;
            }
        }

        //存在->开始下标往左移
        for (int i = mid - 1; i >= low; i--) {
            if (target == nums[i]) {
                result[0] = i;
            } else {
                break;
            }
        }

        return result;
    }

    //35. 搜索插入位置
    public int searchInsert(int[] nums, int target) {
        //头尾判断
        if (target <= nums[0]) {
            return 0;
        }
        if (target == nums[nums.length - 1]) {
            return nums.length - 1;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }


        int low = 0;
        int high = nums.length;
        int mid = high >> 1;
        for (; ; ) {
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                low = ++mid;
            } else if (target < nums[mid]) {
                high = --mid;
            }

            if (low > high) {
                return low;
            }
        }
    }

    //36. 有效的数独
    public boolean isValidSudoku(char[][] board) {
        int[] row = new int[81];
        int[] col = new int[81];
        int[] box = new int[81];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (".".equals(board[i][j])) {
                    row[i * 9 + j] = -1;
                    col[j * 9 + i] = -1;
                }

            }
        }
        return true;
    }
}
