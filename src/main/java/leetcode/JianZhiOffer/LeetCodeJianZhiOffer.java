package leetcode.JianZhiOffer;

import java.util.*;

public class LeetCodeJianZhiOffer {

    //剑指 Offer 03. 数组中重复的数字
    //找出数组中重复的数字。
    //在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
    //但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            if (set.contains(num)){
                return num;
            }
            set.add(num);
        }
        return -1;
    }

    //原地交换
    //剑指 Offer 03. 数组中重复的数字
    //找出数组中重复的数字。
    //在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
    //但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
    public int findRepeatNumber1(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) {
                i++;
                continue;
            }
            if(nums[nums[i]] == nums[i]) {
                return nums[i];
            }
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }

    //额外空间
    //剑指 Offer 03. 数组中重复的数字
    //找出数组中重复的数字。
    //在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
    //但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
    public int findRepeatNumber2(int[] nums) {
        boolean[] flag = new boolean[nums.length];
        for (int num : nums) {
            if (flag[num]) {
                return num;
            }
            flag[num] = !flag[num];
        }
        return -1;
    }

    //剑指 Offer 04. 二维数组中的查找
    //在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
    //请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int row = matrix.length-1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length){
            if (target == matrix[row][col]){
                return true;
            }else if (target > matrix[row][col]){
                col++;
            }else {
                row--;
            }
        }
        return false;
    }

    //剑指 Offer 06. 从尾到头打印链表
    //输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (null != head){
            stack.add(head.val);
            head = head.next;
        }
        int[] result = new int[stack.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.pop();
        }
        return result;
    }

    //剑指 Offer 10- I. 斐波那契数列
    //写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
    public static int fib(int n) {
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        int first = 0;
        int second = 1;

        for (int i = 2; i <= n; i++) {
            int temp = (first + second) % 1000000007;
            first = second;
            second = temp;
        }
        return second;
    }

    //剑指 Offer 10- II. 青蛙跳台阶问题
    //一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
    //答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
    public int numWays(int n) {
        if (n == 0){
            return 1;
        }
        if (n == 1){
            return 1;
        }
        int first = 1;
        int second = 1;

        for (int i = 2; i <= n; i++) {
            int temp = (first + second) % 1000000007;
            first = second;
            second = temp;
        }
        return second;
    }

    //剑指 Offer 11. 旋转数组的最小数字
    //把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
    //输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，
    //该数组的最小值为1。
    public int minArray(int[] numbers) {
        int pre = numbers[numbers.length - 1];
        for (int i = numbers.length - 2; i >= 0; i--) {
            if (numbers[i] > pre){
                return pre;
            }
            pre = numbers[i];
        }
        return numbers[0];
    }

    //剑指 Offer 12. 矩阵中的路径
    //给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
    //单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(board,words,i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean exist(char[][] board,char[] words,int x,int y,int index) {
        if (index == words.length){
            return true;
        }
        if (x < 0 || x == board.length || y < 0 || y == board[0].length){
            return false;
        }
        if (words[index] != board[x][y]){
            return false;
        }
        board[x][y] = '\0';
        boolean res = exist(board,words,x + 1,y,index + 1)
                || exist(board,words,x,y + 1,index + 1)
                || exist(board,words,x - 1,y,index + 1)
                || exist(board,words,x,y - 1,index + 1);
        board[x][y] = words[index];
        return res;
    }

    //剑指 Offer 15. 二进制中1的个数
    //请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。
    //例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
    public int hammingWeight(int n) {
        int sum = 0;
        for (int i = 31; i >= 0; i--) {
            if ((n >> 1 & 1) == 1){
                sum++;
            }
        }
        return sum;
    }

    //剑指 Offer 16. 数值的整数次方
    //实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
    public double myPow(double x, int n) {
        if(x == 0) {
            return 0;
        }
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }

    //剑指 Offer 17. 打印从1到最大的n位数
    //输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
    public int[] printNumbers(int n) {
        int end = (int)Math.pow(10, n) - 1;
        int[] res = new int[end];
        for(int i = 0; i < end; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    //剑指 Offer 18. 删除链表的节点
    public static ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        if (val == cur.val) {
            return cur;
        }
        for (; ; ) {
            if (null == cur.next) {
                return null;
            } else {
                ListNode next = cur.next;
                if (val == next.val) {
                    cur.next = next.next;
                    break;
                } else {
                    cur = cur.next;
                }
            }
        }
        return head;
    }

    //剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
    //输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
    public int[] exchange(int[] nums) {
        if (nums.length < 2){
            return nums;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start < end){
            if (nums[start] % 2 == 1){
                start++;
                continue;
            }
            if (nums[end] % 2 == 0){
                end--;
                continue;
            }
            nums[start] ^= nums[end];
            nums[end] ^= nums[start];
            nums[start] ^= nums[end];
        }
        return nums;
    }

    //剑指 Offer 22. 返回倒数第 k 个节点
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        int instance = 1;
        while (null != fast.next) {
            fast = fast.next;
            if (k == instance) {
                slow = slow.next;
            } else {
                instance++;
            }
        }
        return slow;
    }

    //剑指 Offer 24. 反转链表
    public ListNode reverseList(ListNode head) {
        ListNode target = null;
        while (null != head) {
            ListNode temp = new ListNode(head.val);
            temp.next = target;
            target = temp;
            head = head.next;
        }

        return target;
    }

    //剑指 Offer 25. 合并两个排序的链表
    //输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode resultNode = new ListNode(-1);
        ListNode curNode = resultNode;
        while (null != l1 || null != l2){
            if (null == l1){
                curNode.next = new ListNode(l2.val);
                curNode = curNode.next;
                l2 = l2.next;
            }else if(null == l2){
                curNode.next = new ListNode(l1.val);
                curNode = curNode.next;
                l1 = l1.next;
            }else {
                if(l1.val > l2.val){
                    curNode.next = new ListNode(l2.val);
                    curNode = curNode.next;
                    l2 = l2.next;
                }else {
                    curNode.next = new ListNode(l1.val);
                    curNode = curNode.next;
                    l1 = l1.next;
                }
            }
        }
        return resultNode.next;
    }

    //剑指 Offer 25. 合并两个排序的链表
    //输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0), cur = dum;
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            }
            else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dum.next;
    }

    //剑指 Offer 34. 二叉树中和为某一值的路径
    //输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == root){
            return res;
        }
        pathSum(res,new ArrayList<>(),root,target);
        return res;
    }

    public void pathSum(List<List<Integer>> res, List<Integer> list,TreeNode node, int target) {
        int val = node.val;
        list.add(val);
        if (null == node.right && null == node.left && 0 == target - val){
            res.add(new ArrayList<>(list));
        }
        if (null != node.right){
            pathSum(res,list,node.right,target - val);
        }
        if (null != node.left){
            pathSum(res,list,node.left,target - val);
        }
        list.remove(list.size() - 1);
    }

    //剑指 Offer 37. 序列化二叉树
    //请实现两个函数，分别用来序列化和反序列化二叉树。
    //你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
    //你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
    //提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
    public class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuffer sb = new StringBuffer();
            sb.append('[');
            if (null == root){
                return sb.append(']').toString();
            }
            Deque<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()){
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (null == node){
                        sb.append("null,");
                    }else {
                        sb.append(node.val).append(',');
                        queue.offer(node.left);
                        queue.offer(node.right);
                    }
                }
            }
            sb.setCharAt(sb.length() - 1,']');
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if ("[]".equals(data)){
                return null;
            }
            if (data.startsWith("[") && data.endsWith("]")){
                String subData = data.substring(1, data.length() - 1);
                String[] dataStr = subData.split(",");
                TreeNode root = new TreeNode(Integer.parseInt(dataStr[0]));
                Deque<TreeNode> queue = new LinkedList<>();
                queue.offer(root);
                int index = 1;
                while (!queue.isEmpty()){
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        TreeNode node = queue.pop();
                        if (index < dataStr.length && !"null".equals(dataStr[index])){
                            TreeNode left = new TreeNode(Integer.parseInt(dataStr[index]));
                            node.left = left;
                            queue.offer(left);
                        }
                        ++index;
                        if (index < dataStr.length && !"null".equals(dataStr[index])){
                            TreeNode right = new TreeNode(Integer.parseInt(dataStr[index]));
                            node.right = right;
                            queue.offer(right);
                        }
                        ++index;
                    }
                }
                return root;
            }
            return null;
        }
    }

    //剑指 Offer 38. 字符串的排列
    //输入一个字符串，打印出该字符串中字符的所有排列。
    //你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
    public String[] permutation(String s) {
        Set<String> set = new HashSet<>();
        permutation(set,new boolean[s.length()],new StringBuffer(),s,0);
        return  set.toArray(new String[0]);
    }

    public void permutation(Set<String>set,boolean[] flag,StringBuffer temp,String s,int index) {
        if (index == s.length()){
            set.add(temp.toString());
            return ;
        }

        for (int i = 0; i < s.length(); i++) {
            if (flag[i]){
                continue;
            }
            flag[i] = true;
            temp.append(s.charAt(i));
            permutation(set,flag,temp,s,index + 1);
            temp.deleteCharAt(temp.length() - 1);
            flag[i] = false;
        }
    }

    //剑指 Offer 39. 数组中出现次数超过一半的数字
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[(nums.length + 1) >> 1];
    }

    //剑指 Offer 40. 最小的k个数
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0){
            return null;
        }
        Arrays.sort(arr);
        int[] target = new int[k];
        for (int i = 0; i < k; i++) {
            target[i] = arr[i];
        }
        return target;
    }

    //剑指 Offer 42. 连续子数组的最大和
    //输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
    //要求时间复杂度为O(n)。
    public static int maxSubArray(int[] nums) {
        int res = nums[0];
        for(int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    //剑指 Offer 43. 1～n 整数中 1 出现的次数
    //输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
    //例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
    public static int countDigitOne(int n) {
        long tempn = n;
        long count  = 0;
        long bit = 10;
        int index = 0;
        while (tempn != 0){
            long temp = tempn % bit;
            tempn = tempn - temp;
            long base = bit / 10;
            if (temp / base == 1){
                count +=  (n % base) + 1 + (temp / base) * ((long) index * base / 10);
            }else if (temp / base > 1){
                count += base + (temp / base) * ((long) index * base / 10);
            }
            bit *= 10;
            ++index;
        }
        return (int) count;
    }

    //剑指 Offer 44. 数字序列中某一位的数字
    //数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
    //请写一个函数，求任意第n位对应的数字。
    public static int findNthDigit(int n) {
        // 0   1
        // 1   9 * 1 * 10^0
        // 2   9 * 2 * 10^1
        // 3   9 * 3 * 10^2
        if (n < 10){
            return n;
        }
        int digit = 1;//表示位数
        long start = 1;//表示10的digit - 1 次幂
        long count = 9;
        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit;//求出n在哪个整数里面
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }

    //剑指 Offer 46. 把数字翻译成字符串
    //给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，
    //……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int a = 1, b = 1;
        for(int i = 2; i <= s.length(); i++) {
            String tmp = s.substring(i - 2, i);
            int c = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? a + b : a;
            b = a;
            a = c;
        }
        return a;
    }

    //剑指 Offer 47. 礼物的最大价值
    //在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
    //你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
    //给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
    public int maxValue(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] maxArr = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int prei = i - 1;
                int prej = j - 1;
                int top = grid[i][j];
                int left = grid[i][j];
                if (prei >= 0){
                    left += maxArr[prei][j];
                }
                if (prej >= 0){
                    top += maxArr[i][prej];
                }
                maxArr[i][j] =Math.max(top,left);
            }
        }
        return maxArr[row - 1][col - 1];
    }

    //剑指 Offer 48. 最长不含重复字符的子字符串
    //请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        char[] chars = s.toCharArray();
        boolean[] flag = new boolean[256];//这里很慢
        for (int i = 0; i < chars.length; i++) {
            int tempMax = 1;
            flag[chars[i]] = true;
            for (int j = i + 1; j < chars.length; j++) {
                if (flag[chars[j]]){
                    break;
                }
                flag[chars[j]] = true;
                ++ tempMax;
            }
            max = Math.max(max,tempMax);
            flag = new boolean[256];
        }
        return max;
    }

    //剑指 Offer 50. 第一个只出现一次的字符
    public char firstUniqChar(String s) {
        if(s.length() == 0){
            return ' ';
        }
        if(s.length() == 1){
            return s.charAt(0);
        }
        boolean[] charFlag = new boolean[26];//判断26字符有没有出现过
        for (int i = 0; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            if (!charFlag[c - 'a']){//出现过直接跳过
                boolean flag = false;
                for (int j = i + 1 ; j < s.length(); j++) {
                    if (c == s.charAt(j)){//有重复的，跳出循坏
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    return c;
                }else {
                    charFlag[c - 'a'] = true;
                }
            }
        }
        return charFlag[s.charAt(s.length() - 1) - 'a'] ? ' ' : s.charAt(s.length() - 1);
    }

    //剑指 Offer 52. 两个链表的第一个公共节点 解法一：很慢
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        List<ListNode> list = new ArrayList<>();
        while (null != headA) {
            list.add(headA);
            headA = headA.next;
        }
        while (null != headB) {
            if (list.contains(headB)) {
                return headB;
            } else {
                headB = headB.next;
            }
        }
        return null;
    }

    //剑指 Offer 52. 两个链表的第一个公共节点 解法二：set集合
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (null != headA) {
            set.add(headA);
            headA = headA.next;
        }
        while (null != headB) {
            if (set.contains(headB)) {
                return headB;
            } else {
                headB = headB.next;
            }
        }
        return null;
    }

    //剑指 Offer 52. 两个链表的第一个公共节点 解法三：双指针
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        //tempA和tempB我们可以认为是A,B两个指针
        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != tempB) {
            //如果指针tempA不为空，tempA就往后移一步。
            //如果指针tempA为空，就让指针tempA指向headB（注意这里是headB不是tempB）
            tempA = tempA == null ? headB : tempA.next;
            //指针tempB同上
            tempB = tempB == null ? headA : tempB.next;
        }
        //tempA要么是空，要么是两链表的交点
        return tempA;
    }

    //剑指 Offer 53 - I. 在排序数组中查找数字 I
    //统计一个数字在排序数组中出现的次数。
    public static int search(int[] nums, int target) {
       int left = 0;
       int right = nums.length - 1;
       int index = -1;
       //双指针
       while (left <= right){
           int mid  = (left + right) >> 1;
           if (nums[mid] == target){
               index = mid;
               break;
           }else if (nums[mid] > target){
               right = mid - 1;
           }else {
               left = mid + 1;
           }
       }
       //判断是否寻找到
       if (index == -1){
           return 0;
       }

       int count = 1;

       //向左延伸
       for (int i = index - 1; i >= 0; i--) {
           if (nums[i] != nums[index]){
               break;
           }
           ++count;
       }
       //向右延伸
       for (int i = index + 1; i < nums.length; i++) {
           if (nums[i] != nums[index]){
               break;
           }
           ++count;
       }
       return count;
    }

    //剑指 Offer 53 - II. 0～n-1中缺失的数字
    //一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
    //在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]){
                return i - 1;
            }
        }
        return nums.length;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //剑指 Offer 55 - I. 二叉树的深度
    //输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
    public int maxDepth(TreeNode root) {
        if (null == root){
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            ++depth;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                assert poll != null;
                if (null != poll.left){
                    queue.offer(poll.left);
                }
                if (null != poll.right){
                    queue.offer(poll.right);
                }
            }
        }
        return depth;
    }



   //剑指 Offer 56 - II. 数组中数字出现的次数 II
   //在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
   public int singleNumber(int[] nums) {
        int ones = 0; int twos = 0;
       for(int num : nums){
           ones = ones ^ num & ~twos;
           twos = twos ^ num & ~ones;
       }
       return twos;
   }

    //剑指 Offer 57. 和为s的两个数字
    //输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] + nums[j] > target){
                    break;
                }
                else if (nums[i] + nums[j] == target){
                    int[] result = {nums[i],nums[j]};
                    return result;
                }
            }
        }
        int[] result = {-1,-1};
        return result;
    }

    //双指针
    //剑指 Offer 57. 和为s的两个数字
    //输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
    public int[] twoSum1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int sum = nums[left] + nums[right];
            if (sum == target){
                int[] result = {nums[left],nums[right]};
                return result;
            }else if (sum < target){
                left++;
            }else {
                right--;
            }
        }
        int[] result = {-1,-1};
        return result;
    }

    //剑指 Offer 57 - II. 和为s的连续正数序列
    //输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
    //序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
    public static int[][] findContinuousSequence(int target) {
        int mid = (target + 1) >> 1;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 1; i < mid; i++) {
            int temp = target - i;
            List<Integer> tempList = new ArrayList<>();
            tempList.add(i);
            for (int j = i + 1; j <= mid; j++) {
                tempList.add(j);
                if (temp == j){
                    list.add(tempList);
                    break;
                }else if (temp < j){
                    break;
                }else {
                    temp -= j;
                }
            }
        }

        int[][] result = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> tempList = list.get(i);
            int [] tempArray = new int[tempList.size()];
            for (int j = 0; j < tempList.size(); j++) {
                tempArray[i] = tempList.get(j);
            }
            result[i] = tempArray;
        }
        return result;
    }

    //滑动窗口法
    //剑指 Offer 57 - II. 和为s的连续正数序列
    //输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
    //序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
    public int[][] findContinuousSequence1(int target) {
        List<int[]> vec = new ArrayList<>();
        for (int l = 1, r = 2; l < r;) {
            int sum = (l + r) * (r - l + 1) / 2;
            if (sum == target) {
                int[] res = new int[r - l + 1];
                for (int i = l; i <= r; ++i) {
                    res[i - l] = i;
                }
                vec.add(res);
                l++;
            } else if (sum < target) {
                r++;
            } else {
                l++;
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }

    //求和公式法
    //剑指 Offer 57 - II. 和为s的连续正数序列
    //输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
    //序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
    public int[][] findContinuousSequence2(int target) {
        int i = 1;
        double j = 2.0;
        List<int[]> res = new ArrayList<>();
        while(i < j) {
            j = (-1 + Math.sqrt(1 + 4 * (2 * target + (long) i * i - i))) / 2;
            if(i < j && j == (int)j) {
                int[] ans = new int[(int)j - i + 1];
                for(int k = i; k <= (int)j; k++) {
                    ans[k - i] = k;
                }
                res.add(ans);
            }
            i++;
        }
        return res.toArray(new int[0][]);
    }

    //剑指 Offer 58 - I. 翻转单词顺序
    //输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
    //为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
    public static String reverseWords(String s) {
        s = s.trim();
        String [] arr = s.split("\\s+");
        StringBuffer sb = new StringBuffer();
        for (int i = arr.length - 1; i > 0; i--) {
            sb.append(arr[i] + " ");
        }
        return sb.append(arr[0]).toString();
    }

    //剑指 Offer 58 - II. 左旋转字符串
    //字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
    //比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
    public String reverseLeftWords(String s, int n) {
        s = s + s;
        return s.substring(n , n + (s.length() >> 1));
        //return s.substring(n) + s.substring(0,n);
    }

    //剑指 Offer 61. 扑克牌中的顺子
    //从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，
    //可以看成任意数字。A 不能视为 14。
    public static boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int w = 0;
        int target = 0;
        for (int i = 4; i > 0; i--) {
            if (nums[i] == 0){
                w++;
            }
            else if (nums[i-1] == 0)
            {}
            else{
                if (nums[i] - nums[i-1] <= 0){
                    return false;
                }
                target += nums[i] - nums[i-1] - 1;
                if (target > 2){
                    return false;
                }
            }
        }

        if (nums[0] == 0){
            ++w;
        }else {
            target += nums[1] - nums[0] - 1;
        }

        return w >= target;
    }

    //剑指 Offer 62. 圆圈中最后剩下的数字
    //0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
    //例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
    public static int lastRemaining(int n, int m) {
        if (n == 1){
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int temp = 0;
        while (list.size() != 1){
            temp = (temp + m - 1) % list.size();
            list.remove(temp);
        }
        return list.get(0);
    }

    //剑指 Offer 62. 圆圈中最后剩下的数字
    //0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
    //例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
    public static int lastRemaining1(int n, int m) {
        int x = 0;
        for (int i = 2; i <= n; i++) {
            x = (x + m) % i;
        }
        return x;
    }

    //剑指 Offer 64. 求1+2+…+n
    //n的n次方 + n
    //求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
    public int sumNums(int n) {
        boolean x = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    static class ListNode {
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

}




