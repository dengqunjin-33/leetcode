package leetcode.BingChaJi;

/**
 * 并查集
 */
public class LeetCodeBCJ {

    //200. 岛屿数量
    //给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    //岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    //此外，你可以假设该网格的四条边均被水包围。
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1'){
                    ++count;
                    numIslands(grid,i,j);
                }
            }
        }
        return count;
    }

    public void numIslands(char[][] grid,int i,int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length){
            return ;
        }
        if (grid[i][j] == '0'){
            return;
        }
        grid[i][j] = '0';
        numIslands(grid,i + 1 ,j);
        numIslands(grid,i - 1 ,j);
        numIslands(grid,i ,j + 1);
        numIslands(grid,i ,j - 1);
    }

}
