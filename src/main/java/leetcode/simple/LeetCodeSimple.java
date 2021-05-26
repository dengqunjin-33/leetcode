package leetcode.simple;

import java.util.HashSet;
import java.util.Set;

public class LeetCodeSimple {

   static class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }


   //203. 移除链表元素
   //给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
   public ListNode removeElements(ListNode head, int val) {
       ListNode temp = new ListNode(val - 1);
       ListNode pre = temp;
       pre.next = head;
       ListNode cur = pre.next;
       while (null != cur){
           if (cur.val == val){
               pre.next = cur.next;
           }else {
               pre = cur;
           }
           cur = pre.next;
       }
       return temp.next;
   }

   //219. 存在重复元素 II
    // 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
   public boolean containsNearbyDuplicate(int[] nums, int k) {
       for (int i = 0; i < nums.length-1; i++) {
           for (int j = i+1; j < nums.length; j++) {
               if (nums[i] == nums[j] && j - i <= k){
                   return true;
               }
           }
       }
       return false;
   }

    //219. 存在重复元素 II 第二种
    // 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
            if (i > k - 1){
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] x = {1,2,3,1,2,3};
        containsNearbyDuplicate2(x,2);
    }

}


