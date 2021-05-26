package leetcode.BFS;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCodeBFS {

    //116. 填充每个节点的下一个右侧节点指针
    //给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
    //struct Node {
    //  int val;
    //  Node *left;
    //  Node *right;
    //  Node *next;
    //}
    //填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
    //初始状态下，所有 next 指针都被设置为 NULL。
    public Node connect(Node root) {
        if(null == root){
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            int index = 1;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (null != node.left){
                    queue.offer(node.left);
                }
                if (null != node.right){
                    queue.offer(node.right);
                }
                if (index == size){
                    node.next = null;
                }else {
                    node.next = queue.peek();
                    ++index;
                }
            }
        }
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
