package leetcode.LinkList;
import java.util.*;

public class LeetCodeListNode {

    //2. 两数相加
    //执行用时：2 ms, 在所有 Java 提交中击败了99.24%的用户
    //内存消耗：38.4 MB, 在所有 Java 提交中击败了91.94%的用户
    //给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
    //请你将两个数相加，并以相同形式返回一个表示和的链表。
    //你可以假设除了数字 0 之外，这两个数都不会以 0开头。
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0;
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        while (null != l1 || null != l2){
            if (null != l1){
                sum += l1.val;
                l1 = l1.next;
            }
            if (null != l2){
                sum += l2.val;
                l2 = l2.next;
            }
            ListNode temp = new ListNode(sum % 10);
            sum /= 10;
            cur.next = temp;
            cur = temp;
        }
        if (sum != 0){
            cur.next = new ListNode(sum);
        }
        return res.next;
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

    //23. 合并K个升序链表
    //给你一个链表数组，每个链表都已经按升序排列。
    //请你将所有链表合并到一个升序链表中，返回合并后的链表。
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0){
            return null;
        }
        if (lists.length == 1){
            return lists[0];
        }
        TreeSet<Integer> treeSet = new TreeSet<>(((o1, o2) -> o1 > o2 ? 1 : -1));
        ListNode res = new ListNode(0);
        for (ListNode temp : lists) {
            while (null != temp) {
                treeSet.add(temp.val);
                temp = temp.next;
            }
        }
        ListNode cur = res;
        for (Integer integer : treeSet) {
            cur.next = new ListNode(integer);
            cur = cur.next;
        }
        return res.next;
    }

    public static void main(String[] args) {
        int[][] x = {{1,4,5},{1,3,4},{2,6}};
        List<ListNode> list = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            ListNode l2 = null;
            for (int j = 0; j < x[i].length; j++) {
                ListNode temp = new ListNode(x[i][j]);
                temp.next = l2;
                l2 = temp;
            }
            list.add(l2);
        }
        mergeKLists(list.toArray(new ListNode[list.size()]));
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

    //自己的  空间和时间都要很多
    //25. K 个一组翻转链表
    //给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
    //k 是一个正整数，它的值小于或等于链表的长度。
    //如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
    //进阶：
    //你可以设计一个只使用常数额外空间的算法来解决此问题吗？
    //你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1){
            return head;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode temp = head;
        while (null != temp){
            list.add(temp);
            temp = temp.next;
        }
        int step = list.size() / k;
        for (int i = 1; i <= step; i++) {
            int end = i * k - 1;
            int start = (i - 1) * k;
            while (start < end){
                ListNode tempNode = list.get(end);
                list.set(end,list.get(start));
                list.set(start,tempNode);
                ++start;
                --end;
            }
        }
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).next = list.get(i + 1);
        }
        list.get(list.size() - 1) .next = null;
        return list.get(0);
    }

    //大佬写的
    //25. K 个一组翻转链表
    //给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
    //k 是一个正整数，它的值小于或等于链表的长度。
    //如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
    //进阶：
    //你可以设计一个只使用常数额外空间的算法来解决此问题吗？
    //你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
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


    //使用冒泡排序
    //执行用时：2671 ms, 在所有 Java 提交中击败了5.00%的用户
    //内存消耗：42.2 MB, 在所有 Java 提交中击败了99.98%
    //的用户
    //148. 排序链表
    //给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
    public ListNode sortList(ListNode head) {
        if (null == head){
            return head;
        }
        ListNode cur = head;
        while (null != cur){
            ListNode temp = cur.next;
            while (null != temp){
                if (cur.val > temp.val){
                    int tempVal = temp.val;
                    temp.val = cur.val;
                    cur.val = tempVal;
                }
                temp = temp.next;
            }
            cur = cur.next;
        }
        return head;
    }

    //使用TreeSet 优化
    //执行用时：20 ms, 在所有 Java 提交中击败了12.99%的用户
    //内存消耗：49.3 MB, 在所有 Java 提交中击败了5.05%的用户
    //148. 排序链表
    //给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
    public ListNode sortList2(ListNode head) {
        if (null == head){
            return head;
        }
        Set<Integer> set = new TreeSet<>((o1, o2)-> o1 > o2 ? 1 : -1);
        while (null != head){
            set.add(head.val);
            head = head.next;
        }
        Iterator<Integer> iterator = set.iterator();
        ListNode res = new ListNode(-1);
        ListNode temp = res;
        while (iterator.hasNext()){
            temp.next = new ListNode(iterator.next());
            temp = temp.next;
        }
        return res.next;
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

    //执行用时：4 ms, 在所有 Java 提交中击败了77.61%的用户
    //内存消耗：39 MB, 在所有 Java 提交中击败了12.65%的用户
    //445. 两数相加 II
    //给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
    //你可以假设除了数字 0 之外，这两个数字都不会以零开头。
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (null != l1){
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (null != l2){
            s2.push(l2.val);
            l2 = l2.next;
        }

        ListNode res = null;

        int temp = 0;
        while (!s1.isEmpty() || s2.isEmpty()){
            if (!s1.isEmpty()){
                temp += s1.pop();
            }
            if (!s2.isEmpty()){
                temp += s2.pop();
            }

            ListNode tempNode = new ListNode(temp % 10);
            if (null != res) {
                tempNode.next = res;
            }
            res = tempNode;

            temp /= 10;
        }

        if (0 != temp){
            ListNode tempNode = new ListNode(temp % 10);
            tempNode.next = res;
            res = tempNode;
        }
        return res;
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

    //1171. 从链表中删去总和值为零的连续节点
    //给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
    //删除完毕后，请你返回最终结果链表的头节点。
    public static ListNode removeZeroSumSublists(ListNode head) {
        ListNode temp = head;
        ListNode pre = new ListNode(-1);
        pre.next = head;
        Map<Integer,ListNode> map = new HashMap<>();
        map.put(0,pre);
        int sum = 0;

        int index = 0;
        for (; null != temp; temp = temp.next,index ++) {
            sum += temp.val;
            if (map.containsKey(sum)){
                ListNode node = map.get(sum).next;
                int val = sum + node.val;
                //把中间的sum去掉
                while (val != sum){
                    map.remove(val);
                    node = node.next;
                    val += node.val;
                }
                map.get(sum).next = temp.next;
            }else {
                map.put(sum,temp);
            }
        }

        return pre.next;
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

    //1669. 合并两个链表
    //给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
    //请你将 list1 中第 a 个节点到第 b 个节点删除，并将list2 接在被删除节点的位置。
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        int index = 0;
        ListNode temp = list1;
        ListNode start = null;
        ListNode end = null;
        while (null != temp){
            if (index == a - 1){
                start = temp;
            }
            if (index == b + 1){
                end = temp;
            }
            temp = temp.next;
            ++index;
        }
        start.next = list2;
        while (null != list2 && null != list2.next){
            list2 = list2.next;
        }
        list2.next = end;
        return list1;
    }

    //1721. 交换链表中的节点
    //给你链表的头节点 head 和一个整数 k 。
    //交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。
    public ListNode swapNodes(ListNode head, int k) {
        ListNode left = head;// 第k个节点
        ListNode right = head;// 倒数第k个节点
        for(int i = 1; i < k; i++){left = left.next;}
        ListNode cur = left;
        while(cur.next != null){
            right = right.next;
            cur = cur.next;
        }
        // 交换左右两个节点的值
        int m = right.val;
        right.val = left.val;
        left.val = m;
        return head;
    }
}
