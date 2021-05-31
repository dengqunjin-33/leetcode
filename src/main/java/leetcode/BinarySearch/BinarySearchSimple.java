package leetcode.BinarySearch;

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

    //74. 搜索二维矩阵
    //编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
    //每行中的整数从左到右按升序排列。
    //每行的第一个整数大于前一行的最后一个整数。
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0;
        int right = row * col - 1;
        int mid = (right + left ) >> 1;
        while (left < right){
            if (target == matrix[mid / col][mid % col]){
                return true;
            }else if (target > matrix[mid / col][mid % col]){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
            mid = (right + left) >> 1;
        }
        return false;
    }

    //81. 搜索旋转排序数组 II
    //已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
    //在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
    //使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
    //例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
    //给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
    //如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
    public static boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        //寻找偏移量
        int offset = 0;
        if (nums[0] >= nums[right]){
            ++offset;
            for (int i = right; i > 0; i--) {
                if (nums[i] >= nums[i-1]){
                    ++offset;
                }else {
                    break;
                }
            }
        }
        offset = offset % nums.length;

        while (left <= right){
            int mid = right + left >> 1;
            if (nums[(mid + nums.length - offset) % nums.length] == target){
                return true;
            }else if (nums[(mid + nums.length - offset) % nums.length] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return false;
    }

    //153. 寻找旋转排序数组中的最小值
    //已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
    //若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
    //若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
    //注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
    //给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
    public int findMin(int[] nums) {
        if (nums[0] >= nums[nums.length - 1]){
            for (int i = nums.length - 1; i > 0; i--) {
                if (nums[i] < nums[i - 1]){
                    return nums[i];
                }
            }
        }
        return nums[0];
    }

    //162. 寻找峰值
    //峰值元素是指其值大于左右相邻值的元素。
    //给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
    //你可以假设 nums[-1] = nums[n] = -∞ 。
    public int findPeakElement(int[] nums) {
        if (nums.length == 1 || nums[0] > nums[1]){
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]){
            return nums.length - 1;
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //int [] x = {2,5,6,0,0,1,2};
        int [] x = {2,2,2,3,2,2,2};
        search(x,3);
    }
}
