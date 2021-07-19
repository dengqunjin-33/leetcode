package leetcode.InterviewQuestions;


import java.util.*;

public class InterviewQuestionsAll {

    //面试题 01.01. 判定字符是否唯一
    //实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
    public boolean isUnique(String astr) {
        int len = astr.length();
        boolean[] flag = new boolean[256];
        for (int i = 0; i < len; i++) {
            if (flag[astr.charAt(i)]){
                return false;
            }
            flag[astr.charAt(i)] = true;
        }
        return true;
    }

    //面试题 01.02. 判定是否互为字符重排
    //给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()){
            return false;
        }
        int[] array = new int[256];
        for (int i = 0; i < s2.length(); i++) {
            array[s1.charAt(i)]--;
            array[s2.charAt(i)]++;
        }
        return Arrays.stream(array).allMatch(key -> key == 0);
    }

    //面试题 01.03. URL化
    //URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
    public String replaceSpaces(String S, int length) {
        return S.substring(0, length).replaceAll(" ", "%20");
    }

    //面试题 01.04. 回文排列
    //给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
    //回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
    //回文串不一定是字典当中的单词。
    public boolean canPermutePalindrome(String s) {
        boolean[] flag = new boolean[256];
        int one = 0;
        for (int i = 0; i < s.length(); i++) {
            one = flag[s.charAt(i)] ? --one : ++one;
            flag[s.charAt(i)] = !flag[s.charAt(i)];
        }
        return one <= 1;
    }

    //面试题 01.05. 一次编辑
    //字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
    public boolean oneEditAway(String first, String second) {

        int f = first.length();
        int s = second.length();

        if (Math.abs(f - s) > 1) {
            return false;
        }
        if (first.equals(second)) {
            return true;
        }

        // 记录不匹配的次数。
        int count = 0;
        // 用来指向两字符串的指针
        int i = 0, j = 0;
        while (i < f && j < s) {

            // 两个字符串从前往后开始遍历，如果当前两字符串字符相同就继续比较下一个。
            if (first.charAt(i++) == second.charAt(j++)) {
                continue;
            }
            count++;
            if (count > 1) {
                return false;
            }

            // 若不相等，则将短字符串指针前移一位
            if (f != s) {
                if (f > s) {
                    j--;
                } else {
                    i--;
                }
            }
        }
        return true;
    }

    //面试题 01.06. 字符串压缩
    //字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）
    public String compressString(String S) {
        if (S.length() <= 1){
            return S;
        }
        char[] chars = S.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(chars[0]);
        char pre = chars[0];
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == pre){
                count++;
            }else {
                stringBuffer.append(count).append(chars[i]);
                pre = chars[i];
                count = 1;
            }
        }
        stringBuffer.append(count);
        return stringBuffer.length() < S.length() ? stringBuffer.toString() : S;
    }

    //面试题 01.07. 旋转矩阵
    //给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
    //不占用额外内存空间能否做到？
    public static void rotate(int[][] matrix) {
        int length = matrix.length;
        int mid = length >> 1;
        // 控制行
        for (int i = 0; i < mid; i++) {
            int right = length - i -1;
            for (int j = i; j < right; j++) {
                //控制列
                swap(matrix,j,right,right,length-j-1);
                swap(matrix,i,j,j,right);
                swap(matrix,length-j-1,i,i,j);
            }
        }

    }

    public static void swap(int[][] matrix, int x1, int y1, int x2, int y2){
        matrix[x1][y1] ^=  matrix[x2][y2];
        matrix[x2][y2] ^=  matrix[x1][y1];
        matrix[x1][y1] ^=  matrix[x2][y2];
    }

    //面试题 01.08. 零矩阵
    //编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[] rowFlag = new boolean[row];
        boolean[] colFlag = new boolean[col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0){
                    rowFlag[i] = true;
                    colFlag[j] = true;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rowFlag[i] || colFlag[j]){
                    matrix[i][j] = 0;
                }
            }
        }

    }

    //面试题 01.09. 字符串轮转
    //字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()){
            return false;
        }
        return (s2 + s2).contains(s1);
    }

    //面试题 02.01. 移除重复节点
    //编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
    public ListNode removeDuplicateNodes(ListNode head) {
        if (null == head || null == head.next){
            return head;
        }
        Set<Integer> set = new HashSet();
        ListNode curNode = head;
        set.add(curNode.val);
        ListNode nextNode = head.next;

        while (nextNode != null){
            if (set.contains(nextNode.val)){
                curNode.next = nextNode.next;
            }else {
                set.add(nextNode.val);
                curNode = curNode.next;
            }
            nextNode = curNode.next;

        }
        return head;
    }

    //面试题 02.02. 返回倒数第 k 个节点
    public int kthToLast(ListNode head, int k) {
        List<Integer> list = new ArrayList<>();
        list.add(head.val);
        while (null != head.next) {
            head = head.next;
            list.add(head.val);
        }
        return list.get(list.size() - k);
    }

    //面试题 02.03. 删除中间节点
    //实现一种算法，删除单向链表中间的某个节点（即不是第一个或最后一个节点），假定你只能访问该节点。
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }

    //面试题 02.04. 分割链表
    //编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。如果链表中包含 x，
    //x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
    public ListNode partition(ListNode head, int x) {
        if (null == head || null == head.next){
            return head;
        }
        ListNode small = new ListNode(0);
        ListNode curSmall = small;
        ListNode big = new ListNode(0);
        ListNode curBig = big;
        while (null != head){
            if (head.val < x){
                curSmall.next = new ListNode(head.val);
                curSmall = curSmall.next;
            }else {
                curBig.next = new ListNode(head.val);
                curBig = curBig.next;
            }
            head = head.next;
        }
        curSmall.next = big.next;
        return small.next;
    }

    //面试题 02.05. 链表求和
    //给定两个用链表表示的整数，每个节点包含一个数位。
    //这些数位是反向存放的，也就是个位排在链表首部。
    //编写函数对这两个整数求和，并用链表形式返回结果。
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean flag = false;
        ListNode resultNode = new ListNode(-1);
        ListNode curNode = resultNode;
        while (null != l1 || null != l2){
            int value = 0;
            if (null == l1){
                value = l2.val;
                l2 = l2.next;
            }else if (null == l2){
                value = l1.val;
                l1 = l1.next;
            }else {
                value = l1.val + l2.val;
                l1 = l1.next;
                l2 = l2.next;
            }

            if (flag){
                value += 1;
            }

            if (value >= 10){
                curNode.next = new ListNode(value - 10);
                flag = true;
            }else {
                curNode.next = new ListNode(value);
                flag = false;
            }
            curNode = curNode.next;
        }

        if (flag){
            curNode.next = new ListNode(1);
        }

        return resultNode.next;
    }

    //面试题 02.06. 回文链表
    public boolean isPalindrome(ListNode head) {
        if (null == head) {
            return true;
        }
        if (null == head.next) {
            return true;
        }
        ListNode temp = head;
        Stack<Integer> stack = new Stack<>();
        while (null != temp) {
            stack.push(temp.val);
            temp = temp.next;
        }
        while (!stack.isEmpty()) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //面试题 02.07. 链表相交
    //给定两个（单向）链表，判定它们是否相交并返回交点。请注意相交的定义基于节点的引用，而不是基于节点的值。
    //换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != tempB){
            tempA = tempA == null ? headB : tempA.next;
            tempB = tempB == null ? headA : tempB.next;
        }
        return tempA;
    }

    //面试题 02.08. 环路检测
    //给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。
    //如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
    //为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
    //如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
    public ListNode detectCycle(ListNode head) {
        if (null == head || null == head.next || null == head.next.next){
            return null;
        }
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        //快慢相遇
        while (fast != slow){
            if (null == fast.next || null == fast.next.next){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        //计算环长度
        boolean flag = true;
        int len = 0;
        while (fast != slow || flag){
            fast = fast.next.next;
            slow = slow.next;
            ++len;
            if (fast == slow){
                flag = false;
            }
        }

        fast = head;
        slow = head;
        for (int i = 0; i < len; i++) {
            fast = fast.next;
        }

        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    //面试题 04.02. 最小高度树
    //给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
    public TreeNode sortedArrayToBST(int[] num) {
        if (num.length == 0) {
            return null;
        }
        return sortedArrayToBST(num, 0, num.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] num, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) >> 1;
        TreeNode root = new TreeNode(num[mid]);
        root.left = sortedArrayToBST(num, start, mid - 1);
        root.right = sortedArrayToBST(num, mid + 1, end);
        return root;
    }

    //面试题 04.05. 合法二叉搜索树
    //实现一个函数，检查一棵二叉树是否为二叉搜索树。
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root,new ArrayList<>());
    }

    public static boolean isValidBST(TreeNode root, List<Integer> list) {
        if (null == root){
            return true;
        }
        boolean left = isValidBST(root.left, list);
        if (left){
            if (list.isEmpty()){
                list.add(root.val);
            }else {
                if (root.val > list.get(list.size() - 1)){
                    list.add(root.val);
                }else {
                    return false;
                }
            }
            return isValidBST(root.right, list);
        }
        return false;
    }

    //执行用时：9 ms, 在所有 Java 提交中击败了62.32%的用户
    //内存消耗：38.3 MB, 在所有 Java 提交中击败了63.95%的用户
    //面试题 04.12. 求和路径
    //给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。
    //设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。
    //注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，
    //但是其方向必须向下(只能从父节点指向子节点方向)。
    public static int pathSum(TreeNode root, int sum) {
        int count = 0;
        if (null == root){
            return count;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.push(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pop();
                count += getRootPathSum(node, sum);
                if (null != node.left){
                    deque.push(node.left);
                }
                if (null != node.right){
                    deque.push(node.right);
                }
            }
        }
        return count;
    }

    public static int getRootPathSum(TreeNode root, int sum) {
        int count = 0;
        if (null == root){
            return count;
        }
        if (root.val == sum){
            count += 1;
        }
        count += getRootPathSum(root.left, sum - root.val);
        count += getRootPathSum(root.right,sum - root.val);
        return count;
    }

    //面试题 05.01. 插入
    //给定两个整型数字 N 与 M，以及表示比特位置的 i 与 j（i <= j，且从 0 位开始计算）。
    //编写一种方法，使 M 对应的二进制数字插入 N 对应的二进制数字的第 i ~ j 位区域，不足之处用 0 补齐。具体插入过程如图所示。
    public static int insertBits(int N, int M, int i, int j) {
        int mask = ((1<<(j-i+1))-1) << i;
        mask = ~mask;
        N &= mask;
        M = M<<(31 - j + i) >>> (31 - j);
        return M|N;
    }

    public String printBin(double num) {
        StringBuffer ans = new StringBuffer("0.");
        while (num != 0) {
            num *= 2;
            if (num >= 1) { //乘2后num>=1,说明此时整数部分为1，取完该整数部分1后，num接着利用的还是其小数部分，所以要减掉整数部分（即1）
                ans.append(1);
                num -= 1;
            } else { //小于1说明整数部分为0，取该整数部分0
                ans.append(0);
            }
            if (ans.length() > 32) {
                return "ERROR";
            }
        }
        return ans.toString();
    }




    //面试题 05.04. 下一个数
    public static int[] findClosedNumbers(int num) {
        int[] target = {-1, -1};
        boolean minFlag = false;//标志位，用于标志最近小的数有没有计算过
        boolean maxFlag = false;//标志位，用于标志最近大的数有没有计算过
        int sum = 0;//1的个数
        int ans = 0;//num前n位二进制转换的值
        if ((num & 1) == 1) {
            ++sum;
            ans = 1;
        }
        for (int i = 1; i < 32; i++) {
            //如果最近小的数和最近大的数都计算过了，返回所得到的最近小和最近大的数
            if (minFlag && maxFlag) {
                return target;
            }

            int first = 1 << (i - 1);//用于计算num二进制第i-1位是0还是1
            int second = 1 << i;//用于计算num二进制第i位是0还是1

            if ((num & second) == second) {//第i位是1
                ++sum;
                ans += second;

                if (!minFlag && (num & first) == 0) { //判断第i位和第i-1位是不是10 是的话获取最近小的数
                    //清除前i位的1
                    target[1] = num - ans;

                    //n-1 到 n-1-sum位补1;
                    int temp = second >> 1;
                    int tempSum = 0;
                    for (int j = 0; j < sum; j++) {
                        tempSum += temp;
                        temp = temp >> 1;
                    }
                    target[1] = target[1] + tempSum;
                    minFlag = true;
                }
            } else {//第i位是0

                if (!maxFlag && (num & first) == first) {//判断第i位和第i-1位是不是01 是的话获取最近大的数
                    //清除前n位的1
                    target[0] = num - ans;

                    //给第i位补1，此时还有sum-1个1，给第0位到sum-1补1
                    int tempSum = second;
                    for (int j = 0; j < sum - 1; j++) {
                        tempSum += 1 << j;
                    }
                    target[0] = target[0] + tempSum;
                    maxFlag = true;
                }
            }
        }
        return target;
    }

    //面试题 05.06. 整数转换
    //整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
    public static int convertInteger(int A, int B) {
        if (A == B){
            return 0;
        }
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((A >> i & 1) != (B >> i & 1)) {
                count++;
            }
        }
        return count;
    }

    //面试题 05.07. 配对交换
    //配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。
    public static int exchangeBits(int num) {
        int leftMask = 1;
        int rightMask = 2;
        for (int i = 0; i < 15; i++) {
            leftMask = (leftMask << 2) + 1;
            rightMask = (rightMask << 2) + 2;
        }
        int left = (num >> 1) & leftMask;
        int right = (num << 1) & rightMask;
        return left | right;
    }

    //面试题 08.01. 三步问题
    //三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，
    //小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
    public static int waysToStep(int n) {
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        if (n == 3){
            return 4;
        }
        int x1 = 1;
        int x2 = 2;
        int x3 = 4;
        int result = 0;
        for (int i = 4; i <= n; i++) {
            result = (x1 + x2) % 1000000007 + x3;
            result %= 1000000007;
            x1 = x2;
            x2 = x3;
            x3 = result;
        }
        return result;
    }

    //执行用时：1 ms, 在所有 Java 提交中击败了99.68%的用户
    //内存消耗：39.8 MB, 在所有 Java 提交中击败了19.30%的用户
    //面试题 08.02. 迷路的机器人
    //设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。
    //设计一种算法，寻找机器人从左上角移动到右下角的路径。
    public static List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1 || obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] ==1) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        long timeMillis = System.currentTimeMillis();
        pathWithObstacles(obstacleGrid,0,0, new ArrayList<>(),res);
        System.out.println("时间:" + (System.currentTimeMillis() - timeMillis));
        return res;
    }

    public static boolean pathWithObstacles(int[][] obstacleGrid,int x,int y,List<List<Integer>> list,List<List<Integer>> res) {
        if (!res.isEmpty() || x == obstacleGrid.length || y == obstacleGrid[0].length || obstacleGrid[x][y] == 1){
            return false;
        }
        if (x == obstacleGrid.length - 1 && y == obstacleGrid[0].length - 1){
            list.add(Arrays.asList(x,y));
            res.addAll(list);
            return true;
        }
        list.add(Arrays.asList(x,y));
        if (pathWithObstacles(obstacleGrid,x + 1,y,list,res) || pathWithObstacles(obstacleGrid,x,y + 1,list,res)){
            return true;
        }else {
            //这个很关键 使用obstacleGrid[x][y] = 1这样的话就不用重复计算了  节省了大量的时间
            obstacleGrid[x][y] = 1;
            list.remove(list.size() - 1);
            return false;
        }
    }

    //面试题 08.03. 魔术索引
    //魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，
    //满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，
    //若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i){
                return i;
            }
        }
        return -1;
    }

    //面试题 08.04. 幂集
    //幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
    //说明：解集不能包含重复的子集。
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>(1 << nums.length);
        //先添加一个空的集合
        res.add(new ArrayList<>());
        for (int num : nums) {
            //每遍历一个元素就在之前子集中的每个集合追加这个元素，让他变成新的子集
            for (int i = 0, j = res.size(); i < j; i++) {
                //遍历之前的子集，重新封装成一个新的子集
                List<Integer> list = new ArrayList<>(res.get(i));
                //然后在新的子集后面追加这个元素
                list.add(num);
                //把这个新的子集添加到集合中
                res.add(list);
            }
        }
        return res;
    }

    //面试题 08.05. 递归乘法
    //递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
    public int multiply(int A, int B) {
        if(A == 0 || B == 0){
            return 0;
        }
        // 如果B大的话，就将A减少
        if(A < B){
            return B + multiply(A-1,B);
        }
        return A + multiply(A,B-1);
    }

    //执行用时：8 ms, 在所有 Java 提交中击败了88.78%的用户
    //内存消耗：42 MB, 在所有 Java 提交中击败了21.11%的用户
    //面试题 10.02. 变位词组
    //编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            StringBuffer sb = new StringBuffer();
            sb.append(chars);
            List<String> list = map.computeIfAbsent(sb.toString(), k -> new ArrayList<>());
            list.add(str);
        }
        Collection<List<String>> values = map.values();
        List<List<String>> res = new ArrayList<>(values);
        return res;
    }

    public static void main(String[] args) {
        groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
    }

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：39.1 MB, 在所有 Java 提交中击败了59.74%的用户
    //面试题 10.03. 搜索旋转数组
    //搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。
    //请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
    public int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (target == arr[i]){
                return i;
            }
        }
        return -1;
    }

    //执行用时：5 ms, 在所有 Java 提交中击败了99.07%的用户
    //内存消耗：43.9 MB, 在所有 Java 提交中击败了66.84%的用户
    //面试题 10.09. 排序矩阵查找
    //给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
    public boolean searchMatrix(int[][] matrix, int target) {
        int x = matrix.length - 1;
        int y = 0;
        while (x >= 0 && y < matrix[0].length){
            if (matrix[x][y] == target){
                return true;
            }
            if (matrix[x][y] < target){
                ++y;
            }
            if (matrix[x][y] > target){
                --x;
            }
        }
        return false;
    }

    //面试题 16.01. 交换数字
    //编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
    public int[] swapNumbers(int[] numbers) {
        numbers[0] ^= numbers[1];
        numbers[1] ^= numbers[0];
        numbers[0] ^= numbers[1];
        return numbers;
    }

    //面试题 16.02. 单词频率
    //设计一个方法，找出任意指定单词在一本书中的出现频率。
    //你的实现应该支持如下操作：
    //WordsFrequency(book)构造函数，参数为字符串数组构成的一本书
    //get(word)查询指定单词在书中出现的频率
    class WordsFrequency {
        Map<String,Integer> map;
        public WordsFrequency(String[] book) {
            map = new HashMap<>(book.length);
            for (int i = 0; i < book.length; i++) {
                Integer sum = get(book[i]);
                map.put(book[i],sum + 1);
            }
        }

        public int get(String word) {
            return map.getOrDefault(word, 0);
        }
    }

    //面试题 16.05. 阶乘尾数
    //设计一个算法，算出 n 阶乘有多少个尾随零。
    public int trailingZeroes(int n) {
        int res = 0;
        while (n >= 5){
            res += n / 5;
            n /= 5;
        }
        return res;
    }

    //面试题 16.06. 最小差
    //给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0,j = 0;
        long ans = Long.MAX_VALUE;
        while(i < a.length && j<b.length){
            if(a[i] == b[j]) {
                return 0;
            } else if(a[i]>b[j]){
                ans = Math.min((long)a[i] - (long)b[j],ans);
                j++;
            }else{
                ans = Math.min((long)b[j] - (long)a[i],ans);
                i++;
            }
        }
        return (int)ans;
    }

    //面试题 16.07. 最大数值
    //编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
    public int maximum(int a, int b) {
        long x = (long) a - (long) b;
        int k = (int) (x >> 63);
        return (1 + k) * a - b * k;
    }

    //面试题 16.10. 生存人数
    //给定 N 个人的出生年份和死亡年份，第 i 个人的出生年份为 birth[i]，死亡年份为 death[i]，实现一个方法以计算生存人数最多的年份。
    //你可以假设所有人都出生于 1900 年至 2000 年（含 1900 和 2000 ）之间。
    //如果一个人在某一年的任意时期处于生存状态，那么他应该被纳入那一年的统计中。
    //例如，生于 1908 年、死于 1909 年的人应当被列入 1908 年和 1909 年的计数。
    //如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。
    public static int maxAliveYear(int[] birth, int[] death) {
        int[] ints = new int[101];
        for (int i = 0; i < birth.length; i++) {
            int serStart = birth[i];
            int serEnd = death[i];
            for (int j = serStart; j <= serEnd; j++) {
                ++ints[j - 1900];
            }
        }

        int max = ints[0];
        int index = 0;
        for (int i = 1; i < 101; i++) {
            if (ints[i] > max){
                index = i;
                max = ints[i];
            }
        }
        return index + 1900;
    }

    //面试题 16.11. 跳水板
    //你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。
    //你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
    //返回的长度需要从小到大排列。
    public static int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0){
            int[] ints = {};
            return ints;
        }
        if (shorter == longer){
            int[] ints = {k*shorter};
            return ints;
        }
        int[] res = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            res[i] = shorter * (k - i) + longer * i;
        }
        return res;
    }



    //面试题 17.04. 消失的数字
    //数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
    //注意：本题相对书上原题稍作改动
    public static int missingNumber(int[] nums) {
        int len = nums.length;
        int temp = (len * (len +1)) >> 1;
        for (int i = 0; i < nums.length; i++) {
            temp -= nums[i];
        }
        return temp;
    }

    //面试题 17.01. 不用加号的加法
    //设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
    public int add(int a, int b) {
        //当(a&b)!=0时，表示需要进位
        while((a&b) != 0){
            int tmp = a;
            //(a&b)<<1表示进位信息
            a = (a&b) << 1;
            //b^tmp表示非进位操作
            b ^= tmp;
        }
        //退出while循环表示a,b之间不存在进位了，通过a^b求得结果
        return a^b;
    }

    //面试题 17.10. 主要元素
    //数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
    public static int majorityElement(int[] nums) {
        // prev 用来记录上一个有效的投票人，count用来记录他的有效投票数
        int prev = -1, count = 0;
        for (int i : nums) {
            // 如果 count == 0，说明之前的都抵消掉了，从新开始
            if (count == 0) {
                count++;
                prev = i;
            } else if (prev == i) {
                // 说明遇到了相同的投票，直接相加就好了
                count++;
            } else {
                // 说明遇到了不同的，那么就抵消掉一票
                --count;
            }
        }
        // 检查个数是不是真的超过了 1/2
        if (count > 0) {
            int n = 0;
            for (int i : nums) {
                if (i == prev) {
                    n++;
                }
            }
            if (n > nums.length / 2) {
                return prev;
            }
        }
        return -1;
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

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
