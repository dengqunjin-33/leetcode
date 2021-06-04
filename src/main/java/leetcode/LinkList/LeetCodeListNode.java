package leetcode.LinkList;

import javax.xml.soap.Node;
import java.util.*;

public class LeetCodeListNode {

    public static void main(String[] args) {
        int[] x = { 2, 4, 3};
        int[] y = { 5, 6, 4};
        ListNode l1 = null;
        ListNode l2 = null;
        for (int i = x.length - 1; i >= 0; --i) {
            ListNode temp = new ListNode(x[i]);
            temp.next = l1;
            l1 = temp;
            temp = new ListNode(y[i]);
            temp.next = l2;
            l2 = temp;
        }
        addTwoNumbers(l1,l2);
    }

    //2. 两数相加
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode preNode = new ListNode(0);
        ListNode curNode = preNode;
        int sum;
        boolean flag = false;
        while (null != l1 || null != l2){
            if (null == l1){
                sum = l2.val;
            }else if (null == l2){
                sum = l1.val;
            }else {
                sum = l1.val + l2.val;
            }

            sum = flag ? ++sum : sum;

            if (sum >= 10){
                curNode.next = new ListNode(sum - 10);
                flag = true;
            }else {
                curNode.next = new ListNode(sum);
                flag = false;
            }

            if (null != l1){
                l1 = l1.next;
            }

            if (null != l2){
                l2 = l2.next;
            }

            curNode = curNode.next;
        }

        if (flag){
            curNode.next = new ListNode(1);
        }
        return preNode.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //19. 删除链表的倒数第N个节点 一趟扫描
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int index = 0;
        ListNode fast = head;
        ListNode slow = head;
        while (null != fast.next) {
            fast = fast.next;
            if (index == n) {
                slow = slow.next;
            } else {
                index++;
            }
        }
        if (index < n) {
            return head.next;
        }
        slow.next = slow.next.next;
        return head;
    }


    //21. 合并两个有序链表
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (null == l1 && null == l2) {
            return null;
        }
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }
        ListNode target = new ListNode(0);
        ListNode cur = target;
        while (true) {
            if (null == l1) {
                cur.next = l2;
                break;
            }
            if (null == l2) {
                cur.next = l1;
                break;
            }
            if (l1.val > l2.val) {
                cur.next = new ListNode(l2.val);
                cur = cur.next;
                l2 = l2.next;
            } else {
                cur.next = new ListNode(l1.val);
                cur = cur.next;
                l1 = l1.next;
            }
        }
        return target.next;
    }

    //24. 两两交换链表中的节点
    //给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
    //你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
    public ListNode swapPairs(ListNode head) {
        if (null == head || null == head.next){
            return head;
        }
        ListNode temp = new ListNode(-1);
        temp.next = head;

        ListNode pre = temp;
        ListNode cur = head;
        ListNode next = head.next;
        while (null != next){
            pre.next = next;
            cur.next = next.next;
            next.next = cur;
            pre = cur;
            cur = cur.next;
            if (null == cur){
                next = null;
            }else {
                next = cur.next;
            }
        }
        return temp.next;
    }

    //61. 旋转链表
    public ListNode rotateRight(ListNode head, int k) {
        if (null == head) {
            return head;
        }
        if (0 == k) {
            return head;
        }
        List<Integer> list = new ArrayList<>();
        while (null != head) {
            list.add(head.val);
            head = head.next;
        }
        for (int i = 1; i <= k % list.size(); i++) {
            int temp = list.remove(list.size() - 1);
            list.add(0, temp);
        }
        for (int i = list.size() - 1; i >= 0; --i) {
            ListNode temp = new ListNode(list.get(i));
            temp.next = head;
            head = temp;
        }
        return head;
    }

    //61. 旋转链表 解法二
    public static ListNode rotateRight2(ListNode head, int k) {
        if (null == head || 0 == k) {
            return head;
        }
        ListNode cur = head;
        int len = 1;
        while (null != cur.next) {
            cur = cur.next;
            ++len;
        }
        //头尾相连
        cur.next = head;
        k = len - (k % len);
        for (int i = 1; i <= k; i++) {
            cur = cur.next;
        }

        head = cur.next;
        cur.next = null;
        return head;
    }

    //82. 删除排序链表中的重复元素 II
    //存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
    //返回同样按升序排列的结果链表。
    public ListNode deleteDuplicates2(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode temp = new ListNode(Integer.MAX_VALUE);
        ListNode cur = temp;
        Set<Integer> set = new HashSet<>();
        while (null != head){
            if (set.contains(head.val)){
                head = head.next;
                continue;
            }
            ListNode next = head.next;
            if (null == next){
                cur.next = new ListNode(head.val);
                return temp.next;
            }
            if (head.val != next.val){
                cur.next = new ListNode(head.val);
                cur = cur.next;
                head = head.next;
            }else {
                set.add(head.val);
                head = head.next.next;
            }
        }
        return temp.next;
    }


    //83. 删除排序链表中的重复元素
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode cur = head;
        for (; ; ) {
            if (null == cur.next) {
                return head;
            }
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
    }

    //86. 分隔链表
    //给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
    //你应当 保留 两个分区中每个节点的初始相对位置。
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(-1);
        ListNode smallCur = small;

        ListNode big = new ListNode(-1);
        ListNode bigCur = big;
        while (head != null){
            if (head.val < x){
                smallCur.next = new ListNode(head.val);
                smallCur = smallCur.next;
            }else {
                bigCur.next = new ListNode(head.val);
                bigCur = bigCur.next;
            }
            head = head.next;
        }
        smallCur.next = big.next;
        return small.next;
    }

    //92. 反转链表 II
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) {
            return head;
        }
        List<Integer> list = new ArrayList<>(n);
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int left = m - 1;
        int right = n - 1;
        while (left < right) {
            int temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
            left++;
            right--;
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            ListNode temp = new ListNode(list.get(i));
            temp.next = head;
            head = temp;
        }

        return head;

    }

    //141. 环形链表
    public static boolean hasCycle(ListNode head) {
        if (null == head || null == head.next) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (null != fast && null != fast.next) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    //142. 环形链表 II
    //给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
    //为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (null != head){
            if (set.contains(head)){
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }

    //143. 重排链表
    public static void reorderList(ListNode head) {
        if (null == head || null == head.next) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        while (null != head) {
            list.add(head);
            head = head.next;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            ListNode temp = list.remove(right);
            list.add(left + 1, temp);
            left += 2;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).next = list.get(i + 1);
        }
        list.get(list.size() - 1).next = null;
    }

    //143. 重排链表 解法二
    public static void reorderList2(ListNode head) {
        if (null == head || null == head.next) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        while (null != head) {
            list.add(head);
            head = head.next;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            ListNode temp = list.get(left).next;
            list.get(left).next = list.get(right);
            list.get(right).next = temp;
            ++left;
            --right;
        }
        list.get(left).next = null;
    }

    //160. 相交链表
    //给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != tempB){
            tempA = null == tempA ? headB : tempA.next;
            tempB = null == tempB ? headA : tempB.next;
        }
        return tempA;
    }

    //迭代
    //206. 反转链表
    //给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
    public ListNode reverseList(ListNode head) {
         ListNode listNode = null;
         while (null != head){
             ListNode temp = new ListNode(head.val);
             temp.next = listNode;
             listNode = temp;
             head = head.next;
         }
         return listNode;
    }

    //234. 回文链表
    //请判断一个链表是否为回文链表。
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (null != head){
            list.add(head.val);
            head = head.next;
        }
        for (int i = 0; i < list.size() >> 1; i++) {
            if (!list.get(i).equals(list.get(list.size() - i - 1))){
                return false;
            }
        }
        return true;
    }

    //237. 删除链表中的节点
    //请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    //328. 奇偶链表
    public static ListNode oddEvenList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode cur = head;
        ListNode even = head;
        int index = 0;
        ListNode next = head.next;
        while (null != cur.next) {
            if (index % 2 == 0) {
                even = cur;
            }
            ListNode temp = cur.next;
            cur.next = cur.next.next;
            cur = temp;
            ++index;
        }
        if (index % 2 == 0) {
            even.next = cur;
            even = even.next;
        }
        even.next = next;
        return head;
    }

    //876. 链表的中间结点
    //给定一个头结点为 head 的非空单链表，返回链表的中间结点。
    //如果有两个中间结点，则返回第二个中间结点。
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (null != fast && null != fast.next){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //1290. 二进制链表转整数
    public int getDecimalValue(ListNode head) {
        int sum = 0;
        while (null != head) {
            sum = (sum << 1) + head.val;
            head = head.next;
        }
        return sum;
    }


}
