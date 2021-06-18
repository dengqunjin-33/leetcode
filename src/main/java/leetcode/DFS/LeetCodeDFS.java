package leetcode.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCodeDFS {
    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    //98. 验证二叉搜索树
    //给定一个二叉树，判断其是否是一个有效的二叉搜索树。
    //假设一个二叉搜索树具有如下特征：
    //节点的左子树只包含小于当前节点的数。
    //节点的右子树只包含大于当前节点的数。
    //所有左子树和右子树自身必须也是二叉搜索树。
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }


    //695. 岛屿的最大面积
    //给定一个包含了一些 0 和 1 的非空二维数组 grid 。
    //一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
    //找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1){
                    max = Math.max(max,maxAreaOfIsland(grid,i,j));
                }
            }
        }
        return max;
    }

    public int maxAreaOfIsland(int[][] grid,int x,int y) {
        if (x < 0 || x == grid.length || y < 0 || y == grid[0].length || grid[x][y] != 1){
            return 0;
        }
        int count = 1;
        grid[x][y] = 2;
        count += maxAreaOfIsland(grid,x+1,y);
        count += maxAreaOfIsland(grid,x-1,y);
        count += maxAreaOfIsland(grid,x,y + 1);
        count += maxAreaOfIsland(grid,x,y - 1);
        return count;
    }
}
