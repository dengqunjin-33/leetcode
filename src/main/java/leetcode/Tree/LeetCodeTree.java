package leetcode.Tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCodeTree {

    //94. 二叉树的中序遍历
    //给定一个二叉树的根节点 root ，返回它的 中序 遍历。
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root,list);
        return list;
    }

    public void inorderTraversal(TreeNode node , List<Integer> list) {
        if (null == node){
            return ;
        }
        inorderTraversal(node.left,list);
        list.add(node.val);
        inorderTraversal(node.right,list);
    }

    //100. 相同的树
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (null == q || null == p) {
            return false;
        } else if (q.val == p.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }

    //101. 对称二叉树
    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        } else {
            return isSymmetric(root.left, root.right);
        }
    }

    public boolean isSymmetric(TreeNode p, TreeNode q) {
        if (null == p && null == q) {
            return true;
        } else if (null == q || null == p) {
            return false;
        } else if (q.val == p.val) {
            return isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
        } else {
            return false;
        }
    }

    //102. 二叉树的层序遍历
    //给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }

        return ret;
    }

    //104. 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (null == root){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left,right) + 1;
    }

    //107. 二叉树的层序遍历 II
    //给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> addList = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (null == poll){
                    continue;
                }
                addList.add(poll.val);
                queue.offer(poll.left);
                queue.offer(poll.right);
            }
            if (addList.size() > 0){
                result.add(0,addList);
            }
        }
        return result;
    }

    //108. 将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    //110. 平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if (null == root){
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.right) && isBalanced(root.left);
    }

    public int height(TreeNode root){
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    //111. 二叉树的最小深度
    public int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int m1 = minDepth(root.left);
        int m2 = minDepth(root.right);
        //1.如果左孩子和右孩子有为空的情况，直接返回m1+m2+1
        //2.如果都不为空，返回较小深度+1
        return root.left == null || root.right == null ? m1 + m2 + 1 : Math.min(m1, m2);
    }

    //112. 路径总和
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (null == root || targetSum < 0){
            return false;
        }
        if (null == root.left && null == root.right){
            return targetSum == root.val;
        }
        return hasPathSum(root.left,targetSum - root.val) || hasPathSum(root.right,targetSum - root.val);
    }

    private TreeNode dfs(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        // 以升序数组的中间元素作为根节点 root。
        int mid = lo + (hi - lo) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        // 递归的构建 root 的左子树与右子树。
        root.left = dfs(nums, lo, mid - 1);
        root.right = dfs(nums, mid + 1, hi);
        return root;
    }

    //113. 路径总和 II
    //给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
    //叶子节点 是指没有子节点的节点。
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> target = new ArrayList<>();
        pathSum(root,targetSum,new ArrayList<Integer>(),target);
        return target;
    }

    public void pathSum(TreeNode node,int targetSum,List<Integer> list,List<List<Integer>> target){
        if (null == node){
            return ;
        }
        list.add(node.val);
        if (null == node.left && null == node.right && node.val == targetSum){
            target.add(list);
            return;
        }
        List<Integer> listLeft = new ArrayList<>();
        listLeft.addAll(list);
        pathSum(node.left,targetSum - node.val,listLeft,target);
        List<Integer> listRight = new ArrayList<>();
        listRight.addAll(list);
        pathSum(node.right,targetSum - node.val,listRight,target);
    }

    //114. 二叉树展开为链表
    //给你二叉树的根结点 root ，请你将它展开为一个单链表：
    //展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
    //展开后的单链表应该与二叉树 先序遍历 顺序相同。
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        flatten(root,list);
        for (int i = 1; i < list.size(); i++) {
            TreeNode pre = list.get(i - 1);
            TreeNode cur = list.get(i);
            pre.left = null;
            pre.right = cur;
        }
    }

    public void flatten(TreeNode node,List<TreeNode> list){
        if (null == node){
            return;
        }
        list.add(node);
        flatten(node.left,list);
        flatten(node.right,list);
    }

    //129. 求根节点到叶节点数字之和
    //给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
    //每条从根节点到叶节点的路径都代表一个数字：
    //例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
    //计算从根节点到叶节点生成的 所有数字之和 。
    //叶节点 是指没有子节点的节点。
    public int sumNumbers(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        sumNumbers(root,0,list);
        int target = 0;
        for (int i = 0; i < list.size(); i++) {
            target += list.get(i);
        }
        return target;
    }

    public void sumNumbers(TreeNode node,int num,List<Integer> list) {
        if (null == node){
            return;
        }
        int val = num * 10 + node.val;
        if (null == node.left && null == node.right){
            list.add(val);
            return;
        }
        sumNumbers(node.left,val,list);
        sumNumbers(node.right,val,list);
    }

    //144. 二叉树的前序遍历
    //给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversal(root,list);
        return list;
    }

    public void preorderTraversal(TreeNode root, List<Integer> list) {
        if (null == root){
            return;
        }
        list.add(root.val);
        preorderTraversal(root.left,list);
        preorderTraversal(root.right,list);
    }

    //145. 二叉树的后序遍历
    //给定一个二叉树，返回它的 后序 遍历。
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversal(root,list);
        return list;
    }

    public void postorderTraversal(TreeNode root, List<Integer> list) {
        if (null == root){
            return;
        }
        postorderTraversal(root.left,list);
        postorderTraversal(root.right,list);
        list.add(root.val);
    }

    //199. 二叉树的右视图
    //给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        rightSideView(root,list,0);
        return list;
    }

    public void rightSideView(TreeNode root,List<Integer> res,int dept) {
        if (null == root){
            return ;
        }
        if (dept == res.size()){
            res.add(root.val);
        }
        ++dept;
        rightSideView(root.right,res,dept);
        rightSideView(root.left,res,dept);
    }



    //872. 叶子相似的树
    //请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        leafSimilar(root1,list1);
        leafSimilar(root2,list2);
        if (list1.size() != list2.size()){
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))){
                return false;
            }
        }
        return true;
    }

    public void leafSimilar(TreeNode node, List<Integer> list) {
        if (null == node){
            return ;
        }
        if (node.left == null && node.right == null){
            list.add(node.val);
        }
        leafSimilar(node.left,list);
        leafSimilar(node.right,list);
    }


    //897. 递增顺序搜索树
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        midOrderRe(root,list);

        TreeNode resultNode = new TreeNode(-1);
        TreeNode currNode = resultNode;
        for (int value : list) {
            currNode.right = new TreeNode(value);
            currNode = currNode.right;
        }
        return resultNode.right;
    }

    public void midOrderRe(TreeNode biTree,List<Integer> list)
    {//中序遍历递归实现
        if(biTree == null) {
            return;
        } else
        {
            midOrderRe(biTree.left,list);
            list.add(biTree.val);
            midOrderRe(biTree.right,list);
        }
    }

    //938. 二叉搜索树的范围和
    //给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(null == root){
            return 0;
        }else {
            int left = rangeSumBST(root.left, low, high);
            int right = rangeSumBST(root.right, low, high);
            return root.val < low || root.val > high ?  left + right : root.val + left + right;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
