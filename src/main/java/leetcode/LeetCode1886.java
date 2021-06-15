package leetcode;

public class LeetCode1886 {

    //1886. 判断矩阵经轮转后是否一致
    //给你两个大小为 n x n 的二进制矩阵 mat 和 target 。
    //现 以 90 度顺时针轮转 矩阵 mat 中的元素 若干次 ，如果能够使 mat 与 target 一致，返回 true ；否则，返回 false 。
    public static boolean findRotation(int[][] mat, int[][] target) {
        int len = mat.length;
        if (len == 1){
            return mat[0][0] == target[0][0];
        }
        return rotation(mat,target,0) || rotation(mat,target,1) || rotation(mat,target,2) || rotation(mat,target,3);
    }

    public static boolean rotation(int[][] mat, int[][] target,int count) {
        int len = mat.length;
        for (int i = 0; i <  len + 1 >> 1; i++) {
            for (int j = i; j < len - i - 1; j++) {
                //旋转0°
                if (0 == count &&
                        (mat[i][j] != target[i][j]
                                || mat[j][len - i -1] != target[j][len - i -1]
                                || mat[len - i - 1][len - j - 1] != target[len - i - 1][len - j - 1]
                                || mat[len - j - 1][i] != target[len - j - 1][i])){
                    return false;
                }
                //旋转90°
                if (1 == count &&
                        (mat[i][j] != target[j][len - i -1]
                                || mat[j][len - i -1] != target[len - i - 1][len - j - 1]
                                || mat[len - i - 1][len - j - 1] != target[len - j - 1][i]
                                || mat[len - j - 1][i] != target[i][j])){
                    return false;
                }
                //旋转180°
                if (2 == count &&
                        (mat[i][j] != target[len - i - 1][len - j - 1]
                                || mat[j][len - i -1] != target[len - j - 1][i]
                                || mat[len - i - 1][len - j - 1] != target[i][j]
                                || mat[len - j - 1][i] != target[j][len - i -1])){
                    return false;
                }
                //旋转270°
                if (3 == count &&
                        (mat[i][j] != target[len - j - 1][i]
                                || mat[j][len - i -1] != target[i][j]
                                || mat[len - i - 1][len - j - 1] != target[j][len - i -1]
                                || mat[len - j - 1][i] != target[len - i - 1][len - j - 1])){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int [][] x = {{1}};
        int [][] y = {{0}};
        findRotation(x,y);
    }
}
