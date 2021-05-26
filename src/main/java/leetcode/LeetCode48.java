package leetcode;

public class LeetCode48 {

    //48. 旋转图像
    public static void rotate(int[][] matrix) {
        int temp = matrix.length >> 1;
        for (int i = 0; i < temp; i++) {
            adjust(matrix,i);
        }
    }

    private static void adjust(int[][] matrix, int row){
        for (int i = row; i < matrix.length - row - 1; i++) {
            swapArray(matrix,row,i,i,matrix.length-1-row);
            swapArray(matrix,row,i,matrix.length-1-row,matrix.length-1-i);
            swapArray(matrix,row,i,matrix.length-1-i,row);
        }
    }

    private static void swapArray(int[][] matrix,int x1row, int x1col, int x2row, int x2col){
        matrix[x1row][x1col] ^= matrix[x2row][x2col];
        matrix[x2row][x2col] ^= matrix[x1row][x1col];
        matrix[x1row][x1col] ^= matrix[x2row][x2col];
    }

    public static void main(String[] args) {
        int [][] x = {{1,2,3},{4,5,6},{7,8,9}};
        rotate(x);
    }
}
