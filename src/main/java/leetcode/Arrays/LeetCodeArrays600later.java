package leetcode.Arrays;

import java.util.*;

public class LeetCodeArrays600later {

    //排序 + 暴力
    //执行用时：1281 ms, 在所有 Java 提交中击败了11.21%的用户
    //内存消耗：38.3 MB, 在所有 Java 提交中击败了13.27%的用户
    //611. 有效三角形的个数
    //给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
    public int triangleNumber(int[] nums) {
        if (nums.length < 3){
            return 0;
        }
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length -1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] <= nums[k]){
                        break;
                    }
                    ++count;
                }
            }
        }
        return count;
    }

    //排序 + 双指针
    //执行用时：30 ms, 在所有 Java 提交中击败了91.22%的用户
    //内存消耗：38.2 MB, 在所有 Java 提交中击败了18.54%的用户
    //611. 有效三角形的个数
    //给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
    public int triangleNumber2(int[] nums) {
        int len = nums.length;
        if (len < 3){
            return 0;
        }
        int count = 0;
        Arrays.sort(nums);
        for (int i = len - 1; i >= 2; i--) {
            int left = 0;
            int right = i - 1;
            while (left < right){
                if (nums[left] + nums[right] > nums[i]) {
                    count += right - left;
                    --right;
                } else {
                    ++left;
                }
            }
        }
        return count;
    }//1 3 4 5 6 8 9 11 12 13 14

    //645. 错误的集合
    //集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
    //给定一个数组 nums 代表了集合 S 发生错误后的结果。
    //请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
    public static int[] findErrorNums(int[] nums) {
        int n = nums.length;
        boolean[] cnts = new boolean[n + 1];
        int sum = nums.length * (nums.length + 1) >> 1;
        int[] ans = new int[2];
        for (int x : nums) {
            if(cnts[x]){
                ans[0] = x;
            }else{
                sum -= x;
                cnts[x] = true;
            }
        }
        ans[1] = sum;
        return ans;
    }

    //645. 错误的集合
    //集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
    //给定一个数组 nums 代表了集合 S 发生错误后的结果。
    //请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
    public static int[] findErrorNums2(int[] nums) {
        int n = nums.length;
        boolean[] cnts = new boolean[n + 1];
        int[] ans = new int[2];
        for (int x : nums) {
            if(cnts[x]){
                ans[0] = x;
            }else{
                cnts[x] = true;
            }
        }
        for (int i = 1; i < n + 1; i++) {
            if (!cnts[i]){
                ans[1] = i;
            }
        }
        return ans;
    }

    //单调栈
    //执行用时：36 ms, 在所有 Java 提交中击败了38.16%的用户
    //内存消耗：47.5 MB, 在所有 Java 提交中击败了44.92%的用户
    //739. 每日温度
    //请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。
    public static int[] dailyTemperatures(int[] temperatures) {
        int [] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < temperatures.length;++i){
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                Integer peek = stack.pop();
                res[peek] = i - peek;
            }
            stack.push(i);
        }
        return res;
    }

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：38.1 MB, 在所有 Java 提交中击败了44.71%的用户
    //789. 逃脱阻碍者
    //你在进行一个简化版的吃豆人游戏。你从 [0, 0] 点开始出发，你的目的地是 target = [xtarget, ytarget] 。地图上有一些阻碍者，以数组 ghosts 给出，第 i 个阻碍者从 ghosts[i] = [xi, yi] 出发。所有输入均为 整数坐标 。
    //每一回合，你和阻碍者们可以同时向东，西，南，北四个方向移动，每次可以移动到距离原位置 1 个单位 的新位置。当然，也可以选择 不动 。所有动作 同时 发生。
    //如果你可以在任何阻碍者抓住你 之前 到达目的地（阻碍者可以采取任意行动方式），则被视为逃脱成功。如果你和阻碍者同时到达了一个位置（包括目的地）都不算是逃脱成功。
    //只有在你有可能成功逃脱时，输出 true ；否则，输出 false 。
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        // 计算玩家和鬼谁先到达终点
        int playerDistance = Math.abs(target[0]) + Math.abs(target[1]);
        for (int[] ghost : ghosts) {
            int ghostDistance = Math.abs(ghost[0] - target[0]) + Math.abs(ghost[1] - target[1]);
            if (ghostDistance <= playerDistance) {
                return false;
            }
        }
        return true;
    }

    //超时了
    //802. 找到最终的安全状态
    //在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。
    //对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
    //返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。
    //该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，满足 (i, j) 是图的一条有向边。
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int len = graph.length;
        List<Integer> res = new ArrayList<>();
        //0-->初始话 1-->正在遍历 2-->循环体
        byte[] exclude = new byte[len];
        for (int i = 0; i < len; i++) {
            if ((0 == exclude[i]) && dfs(graph,exclude,i,0,len)){
                res.add(i);
            }
        }
        return res;
    }

    public static boolean dfs(int[][] graph, byte[] exclude, int index, int count, int n){
        if (count == n || 2 == exclude[index]){
            return false;
        }
        if (1 == exclude[index]){
            exclude[index] = 2;
            return false;
        }
        if (0 != graph[index].length){
            exclude[index] = 1;
            for (int arr : graph[index]) {
                if (!dfs(graph,exclude,arr,count + 1,n)){
                    exclude[index] = 2;
                    return false;
                }
            }
            exclude[index] = 0;
        }
        return true;
    }

    //大佬的
    //执行用时：4 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：48.3 MB, 在所有 Java 提交中击败了38.34%的用户
    //802. 找到最终的安全状态
    //在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。
    //对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
    //返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。
    //该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，满足 (i, j) 是图的一条有向边。
    public List<Integer> eventualSafeNodes2(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (safe(graph, color, i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public boolean safe(int[][] graph, int[] color, int x) {
        if (color[x] > 0) {
            return color[x] == 2;
        }
        color[x] = 1;
        for (int y : graph[x]) {
            if (!safe(graph, color, y)) {
                return false;
            }
        }
        color[x] = 2;
        return true;
    }



    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：38.7 MB, 在所有 Java 提交中击败了38.27%的用户
    //832. 翻转图像
    //给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
    //水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
    //反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
    public int[][] flipAndInvertImage(int[][] image) {
        int row = image.length;
        int col = image[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (image[i][col - 1 - j] == 0){
                    res[i][j] = 1;
                }
            }
        }
        return res;
    }

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：35.6 MB, 在所有 Java 提交中击败了67.72%的用户
    //836. 矩形重叠
    //矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。矩形的上下边平行于 x 轴，左右边平行于 y 轴。
    //如果相交的面积为 正 ，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
    //给出两个矩形 rec1 和 rec2 。如果它们重叠，返回 true；否则，返回 false 。
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if (rec1[0] == rec1[2] || rec1[1] == rec1[3] || rec2[0] == rec2[2] || rec2[1] == rec2[3]) {
            return false;
        }
        return !(rec1[2] <= rec2[0] ||
                rec1[3] <= rec2[1] ||
                rec1[0] >= rec2[2] ||
                rec1[1] >= rec2[3]);
    }

    //执行用时：17 ms, 在所有 Java 提交中击败了64.29%的用户
    //内存消耗：47.4 MB, 在所有 Java 提交中击败了38.59%的用户
    //881. 救生艇
    //第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
    //每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
    //返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
    public static int numRescueBoats(int[] people, int limit) {
        int res = 0;
        int left = 0;
        int right = people.length - 1;
        Arrays.sort(people);
        while (left <= right){
            ++res;
            if (people[left] + people[right--] <= limit){
                ++left;
            }
        }
        return res;
    }

    //912. 排序数组
    //给你一个整数数组 nums，请你将该数组升序排列。
    public int[] sortArray(int[] nums) {
        quickNums(nums,0,nums.length - 1);
        return nums;
    }

    private static void quickNums(int[] nums, int start, int end){
        if (start < end){
            int base = nums[start];
            int i = start;
            int j = end;
            while (i < j){
                while (i < j && base <= nums[j]){
                    --j;
                }
                nums[i] = nums[j];
                while (i < j && base >= nums[i]){
                    i++;
                }
                nums[j] = nums[i];
            }
            nums[i] = base;
            quickNums(nums, start, i);
            quickNums(nums, i + 1, end);
        }
    }

    //暴力
    //执行用时：1121 ms, 在所有 Java 提交中击败了24.49%的用户
    //内存消耗：53.7 MB, 在所有 Java 提交中击败了15.93%的用户
    //1109. 航班预订统计
    //这里有 n 个航班，它们分别从 1 到 n 进行编号。
    //有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
    //请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int[] booking : bookings) {
            int start = booking[0];
            int end = booking[1];
            for (; start <= end; start++) {
                res[start - 1] += booking[2];
            }
        }
        return res;
    }

    //大佬的拆分
    //执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：53.8 MB, 在所有 Java 提交中击败了5.18%的用户
    //1109. 航班预订统计
    //这里有 n 个航班，它们分别从 1 到 n 进行编号。
    //有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
    //请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。
    public static int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] res = new int[n];
        for(int [] booking : bookings){
            res[--booking[0]] += booking[2];
            if (booking[1] < n){
                res[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < res.length; i++) {
            res[i--] += res[i++];
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] x = {{1,2,10},{2,3,20},{2,5,25}};
        corpFlightBookings2(x,5);
    }

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：38.5 MB, 在所有 Java 提交中击败了62.71%的用户
    //1480. 一维数组的动态和
    //给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
    //请返回 nums 的动态和。
    public int[] runningSum(int[] nums) {
        int len = nums.length;
        for(int i = 1;i < len;){
            nums[i] += nums[i++-1];
        }
        return nums;
    }

    //1292. 元素和小于等于阈值的正方形的最大边长
    //给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
    //请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
    public static int maxSideLength(int[][] mat, int threshold) {
        int res = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                res = Math.max(res,maxSideLength(mat,i,j,i,j,threshold,0));
            }
        }
        return res;
    }

    public static int maxSideLength(int[][] mat, int x1, int y1, int x2, int y2, int threshold, int sum) {
        if (x2 == mat.length || y2 == mat[0].length){
            return x2 - x1;
        }

        for (int i = x1; i <= x2; i++) {
            sum += mat[i][y2];
            if (sum > threshold){
                return x2 - x1;
            }
        }

        for (int i = y1; i < y2; i++) {
            sum += mat[x2][i];
            if (sum > threshold){
                return x2 - x1;
            }
        }

        return maxSideLength(mat,x1,y1,x2 + 1,y2 + 1,threshold,sum);
    }

    //执行用时：3 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：39.3 MB, 在所有 Java 提交中击败了14.15%的用户
    //1513. 仅含 1 的子串数
    //给你一个二进制字符串 s（仅由 '0' 和 '1' 组成的字符串）。
    //返回所有字符都为 1 的子字符串的数目。
    //由于答案可能很大，请你将它对 10^9 + 7 取模后返回。
    public static int numSub(String s) {
        int MOD = 1000000007;
        char[] chars = s.toCharArray();
        int len = chars.length;
        int sum = 0;

        int left = -1;
        int right = -1;

        for (int i = 0; i < len; i++) {
            if (chars[i] == '0') {
                if (right == left) {
                    ++left;
                } else {
                    int dist = (right - left);
                    dist = calNumSub(dist,MOD);
                    sum += dist;
                    sum %= MOD;
                    left = i;
                }
            }
            ++right;
        }
        if (right > left){
            int dist = (right - left);
            dist = calNumSub(dist,MOD);
            sum += dist;
            sum %= MOD;
        }
        return sum;
    }

    public static int calNumSub(int len,int MOD) {
        long res = (long) len * (len + 1) / 2;
        return (int) (res % MOD);
    }

    //执行用时：136 ms, 在所有 Java 提交中击败了23.48%的用户
    //内存消耗：39.6 MB, 在所有 Java 提交中击败了33.33%的用户
    //1545. 找出第 N 个二进制字符串中的第 K 位
    //给你两个正整数 n 和 k，二进制字符串  Sn 的形成规则如下：
    //S1 = "0"
    //当 i > 1 时，Si = Si-1 + "1" + reverse(invert(Si-1))
    //其中 + 表示串联操作，reverse(x) 返回反转 x 后得到的字符串，而 invert(x) 则会翻转 x 中的每一位（0 变为 1，而 1 变为 0）。
    //例如，符合上述描述的序列的前 4 个字符串依次是：
    //S1 = "0"
    //S2 = "011"
    //S3 = "0111001"
    //S4 = "011100110110001"
    //请你返回  Sn 的 第 k 位字符 ，题目数据保证 k 一定在 Sn 长度范围以内。
    public static char findKthBit(int n, int k) {
        if (k == 1){
            return '0';
        }
        StringBuffer sb = new StringBuffer().append('0');
        for (int i = 1; i < n; i++) {
            if (k <= sb.length()){
                return sb.charAt(k - 1);
            }
            int len = sb.length();
            sb.append('1');
            for (int j = len - 1; j >= 0; j--) {
                if ('0' == sb.charAt(j)){
                    sb.append('1');
                }else {
                    sb.append('0');
                }
            }
        }
        return sb.charAt(k - 1);
    }

    //执行用时：12 ms, 在所有 Java 提交中击败了34.09%的用户
    //内存消耗：42.3 MB, 在所有 Java 提交中击败了32.57%的用户
    //1545. 找出第 N 个二进制字符串中的第 K 位
    //给你两个正整数 n 和 k，二进制字符串  Sn 的形成规则如下：
    //S1 = "0"
    //当 i > 1 时，Si = Si-1 + "1" + reverse(invert(Si-1))
    //其中 + 表示串联操作，reverse(x) 返回反转 x 后得到的字符串，而 invert(x) 则会翻转 x 中的每一位（0 变为 1，而 1 变为 0）。
    //例如，符合上述描述的序列的前 4 个字符串依次是：
    //S1 = "0"
    //S2 = "011"
    //S3 = "0111001"
    //S4 = "011100110110001"
    //请你返回  Sn 的 第 k 位字符 ，题目数据保证 k 一定在 Sn 长度范围以内。
    public static char findKthBit2(int n, int k) {
        if (k == 1){
            return '0';
        }
        char [] res = new char[(2 << n) - 1];
        res[0] = '0';
        int len = 1;
        for (int i = 1; i < n; i++) {
            if (k <= len){
                return res[k - 1];
            }
            int tempLen = len;
            res[len] = '1';
            ++len;
            for (int j = tempLen - 1; j >= 0; j--) {
                res[len] = '0' == res[j] ? '1' : '0';
                ++len;
            }
        }
        return res[k - 1];
    }

    //执行用时：2 ms, 在所有 Java 提交中击败了44.33%的用户
    //内存消耗：36.1 MB, 在所有 Java 提交中击败了46.36%的用户
    //1588. 所有奇数长度子数组的和
    //给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
    //子数组 定义为原数组中的一个连续子序列。
    //请你返回 arr 中 所有奇数长度子数组的和 。
    public int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        int n = arr.length;
        for (int start = 0; start < n; start++) {
            for (int length = 1; start + length <= n; length += 2) {
                int end = start + length - 1;
                for (int i = start; i <= end; i++) {
                    sum += arr[i];
                }
            }
        }
        return sum;
    }

    //执行用时：5 ms, 在所有 Java 提交中击败了39.45%的用户
    //内存消耗：50.1 MB, 在所有 Java 提交中击败了88.07%的用户
    //1685. 有序数组中差绝对值之和
    //给你一个 非递减 有序整数数组 nums 。
    //请你建立并返回一个整数数组 result，它跟 nums 长度相同，且result[i] 等于 nums[i] 与数组中所有其他元素差的绝对值之和。
    //换句话说， result[i] 等于 sum(|nums[i]-nums[j]|) ，其中 0 <= j < nums.length 且 j != i （下标从 0 开始）。
    public static int[] getSumAbsoluteDifferences(int[] nums) {
        int len = nums.length;
        int [] pre = new int[len];
        pre[0] = nums[0];
        for (int i = 1; i < len; i++) {
            pre[i] = nums[i] + pre[i - 1];
        }

        int [] suf = new int[nums.length];
        suf[len - 1] = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] + nums[i];
        }
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            int left = i * nums[i] - suf[0] + suf[i];
            int right = pre[len - 1] - pre[i] - (len - i - 1) * nums[i];
            res[i] = right + left;
        }
        return res;
    }

    //暴力法 超时
    //1711. 大餐计数
    //大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
    //你可以搭配 任意 两道餐品做一顿大餐。
    //给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。
    //注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
    public int countPairs(int[] deliciousness) {
        long count = 0;
        for (int i = 0; i < deliciousness.length - 1; i++) {
            for (int j = i + 1; j < deliciousness.length; j++) {
                long temp = deliciousness[i] + deliciousness[j];
                if (Long.bitCount(temp) == 1){
                    ++count;
                }
            }
        }
        return (int) (count % 1000000007);
    }

    //优化  使用hash表
    //1711. 大餐计数
    //大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
    //你可以搭配 任意 两道餐品做一顿大餐。
    //给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。
    //注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
    public static int countPairs2(int[] deliciousness) {
        final int MOD = 1000000007;
        int maxVal = 0;
        for (int val : deliciousness) {
            maxVal = Math.max(maxVal, val);
        }
        int maxSum = maxVal * 2;
        maxSum = tableSizeFor(maxSum);
        int pairs = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int val : deliciousness) {
            for (int sum = maxSum; sum > 0; sum >>= 1) {
                if (sum - val < 0) {
                    break;
                }
                int count = map.getOrDefault(sum - val, 0);
                pairs = (pairs + count) % MOD;
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return pairs;
    }

    public static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

    //二分法
    //执行用时：47 ms, 在所有 Java 提交中击败了88.87%的用户
    //内存消耗：55.9 MB, 在所有 Java 提交中击败了56.62%的用户
    //1818. 绝对差值和
    //给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
    //数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。
    //你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
    //在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。
    //|x| 定义为：
    //如果 x >= 0 ，值为 x ，或者
    //如果 x <= 0 ，值为 -x
    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n =nums1.length;
        if (1 == n){
            return Math.abs(nums1[0] - nums2[0]);
        }

        int[] sortNum1 = Arrays.copyOf(nums1, n);
        Arrays.sort(sortNum1);

        //计算能减去的最大差值
        int maxDiff = 0;
        int sum = 0;
        int MOD = 1000000007;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);

            sum += diff;
            sum %= MOD;

            int nearDiff = Integer.MAX_VALUE;
            int left = 0, right = n - 1;
            while (left <= right) {
                int mid = (left + right) >> 1;
                if (sortNum1[mid] < nums2[i]) {
                    nearDiff = Math.min( nearDiff,nums2[i] - sortNum1[mid]);
                    left = mid + 1;
                } else if (sortNum1[mid] > nums2[i]) {
                    nearDiff = Math.min(nearDiff,sortNum1[mid] - nums2[i]);
                    right = mid - 1;
                }else {
                    nearDiff = 0;
                    break;
                }
            }

            if (nearDiff < diff) {
                nearDiff %= MOD;
                maxDiff =  Math.max(maxDiff, diff - nearDiff);
            }
        }

        return (sum + MOD - maxDiff) % MOD;
    }

    //TreeSet
    //执行用时：156 ms, 在所有 Java 提交中击败了29.29%的用户
    //内存消耗：52.6 MB, 在所有 Java 提交中击败了90.34%的用户
    //1818. 绝对差值和
    //给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
    //数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。
    //你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
    //在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。
    //|x| 定义为：
    //如果 x >= 0 ，值为 x ，或者
    //如果 x <= 0 ，值为 -x
    public static int minAbsoluteSumDiff2(int[] nums1, int[] nums2) {
        int n =nums1.length;
        if (1 == n){
            return Math.abs(nums1[0] - nums2[0]);
        }

        TreeSet<Integer> set = new TreeSet<>();
        for (int k : nums1) {
            set.add(k);
        }

        //计算能减去的最大差值
        int maxDiff = 0;
        int sum = 0;
        int MOD = 1000000007;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);

            sum += diff;
            sum %= MOD;

            int nearDiff = Integer.MAX_VALUE;
            for (int j = nums2[i]; j <= nums2[i] + diff; j++) {
                if (set.contains(j)){
                    nearDiff = j - nums2[i];
                    break;
                }
            }

            for (int j = nums2[i]; j > nums2[i] - Math.min(nearDiff, diff); j--) {
                if (set.contains(j)){
                    nearDiff = nums2[i] - j;
                    break;
                }
            }

            if (nearDiff < diff) {
                maxDiff =  Math.max(maxDiff, diff - nearDiff);
            }
        }

        return (sum + 1000000007 - maxDiff) % 1000000007;
    }

    //暴力法 超时了
    //1838. 最高频元素的频数
    //元素的 频数 是该元素在一个数组中出现的次数。
    //给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
    //执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
    public int maxFrequency(int[] nums, int k) {
        if (1 == nums.length){
            return 1;
        }
        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int index = 1;
            int tempk = k;
            for (int j = i + 1; j < nums.length; j++) {
                int diff = nums[j] - nums[j - 1];
                tempk -= diff * index;
                if (tempk < 0){
                    max = Math.max(max,j - i);
                    break;
                }else if (j == nums.length - 1){
                    max = Math.max(max,j - i + 1);
                    break;
                }else {
                    ++index;
                }
            }
        }
        return max;
    }

    //大佬的  双指针
    //1838. 最高频元素的频数
    //元素的 频数 是该元素在一个数组中出现的次数。
    //给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
    //执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
    public int maxFrequency2(int[] nums, int k) {
        // 先升序
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        int cnt = 0;
        // 双指针遍历数组
        for(int i = 0, j = 0; i < n; i++) {
            // 指针区间数都变成当前位的数，加上所需的增加次数
            if(i > 0) {
                cnt += (i - j) * (nums[i] - nums[i - 1]);
            }
            // 如果所需的次数超过最大允许次数，则左侧边界开始向右，区间变小，所需次数也会变少
            while(cnt > k && i > j) {
                cnt -= (nums[i] - nums[j]);
                j ++;
            }
            // 取最大值
            res = Math.max(i - j + 1, res);
        }

        return res;
    }

    //执行用时：
    //3 ms, 在所有 Java 提交中击败了99.35%的用户
    //内存消耗：55.2 MB, 在所有 Java 提交中击败了82.88%的用户
    //1846. 减小和重新排列数组后的最大元素
    //给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：
    //arr 中 第一个 元素必须为 1 。
    //任意相邻两个元素的差的绝对值 小于等于 1 ，也就是说，对于任意的 1 <= i < arr.length （数组下标从 0 开始），都满足 abs(arr[i] - arr[i - 1]) <= 1 。abs(x) 为 x 的绝对值。
    //你可以执行以下 2 种操作任意次：
    //减小 arr 中任意元素的值，使其变为一个 更小的正整数 。
    //重新排列 arr 中的元素，你可以以任意顺序重新排列。
    //请你返回执行以上操作后，在满足前文所述的条件下，arr 中可能的 最大值 。
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int index = 1;
        for (int j : arr) {
            if (j > index){
                ++index;
            }
        }
        return index - 1;
    }

    //执行用时：3 ms, 在所有 Java 提交中击败了94.51%的用户
    //内存消耗：51.2 MB, 在所有 Java 提交中击败了44.51%的用户
    //1855. 下标对中的最大距离
    //给你两个 非递增 的整数数组 nums1 和 nums2 ，数组下标均 从 0 开始 计数。
    //下标对 (i, j) 中 0 <= i < nums1.length 且 0 <= j < nums2.length 。如果该下标对同时满足 i <= j 且 nums1[i] <= nums2[j] ，则称之为 有效 下标对，该下标对的 距离 为 j - i​​ 。​​
    //返回所有 有效 下标对 (i, j) 中的 最大距离 。如果不存在有效下标对，返回 0 。
    //一个数组 arr ，如果每个 1 <= i < arr.length 均有 arr[i-1] >= arr[i] 成立，那么该数组是一个 非递增 数组。
    public int maxDistance(int[] nums1, int[] nums2) {
        int count = 0;
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length){
            if (nums1[i] > nums2[j]){
                ++i;
            }else {
                count = Math.max(count,j - i);
                ++j;
            }
        }
        return count;
    }

    //1877. 数组中最大数对和的最小值
    //一个数对 (a,b) 的 数对和 等于 a + b 。最大数对和 是一个数对数组中最大的 数对和 。
    //比方说，如果我们有数对 (1,5) ，(2,3) 和 (4,4)，最大数对和 为 max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8 。
    //给你一个长度为 偶数 n 的数组 nums ，请你将 nums 中的元素分成 n / 2 个数对，使得：
    //nums 中每个元素 恰好 在 一个 数对中，且
    //最大数对和 的值 最小 。
    //请你在最优数对划分的方案下，返回最小的 最大数对和 。
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        int times = nums.length >> 1;
        int len = nums.length;
        for (int i = 0; i < times; i++) {
            res = Math.max(res,nums[i] + nums[len - 1 - i]);
        }
        return res;
    }

    //暴力
    //执行用时：1 ms, 在所有 Java 提交中击败了67.54%的用户
    //内存消耗：38 MB, 在所有 Java 提交中击败了5.15%的用户
    //1893. 检查是否区域内所有整数都被覆盖
    //给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 starti 到 endi 的 闭区间 。
    //如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。
    //已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了。
    public static boolean isCovered(int[][] ranges, int left, int right) {
        int len = right - left + 1;
        boolean[] flag = new boolean[len];
        for(int[] nums : ranges){
            for (int i = nums[0]; i <= nums[1]; i++) {
                if (i - left >= 0 && i - left < len && !flag[i - left]){
                    flag[i - left] = true;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            if (!flag[i]){
                return false;
            }
        }
        return true;
    }

    //优化: 暴力 + 剪枝
    //执行用时：
    //0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：37.7 MB, 在所有 Java 提交中击败了51.16%的用户
    //1893. 检查是否区域内所有整数都被覆盖
    //给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 starti 到 endi 的 闭区间 。
    //如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。
    //已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了。
    public static boolean isCovered2(int[][] ranges, int left, int right) {
        int len = right - left + 1;
        int L = left;
        int R = right;
        //防止数组全是点的情况
        boolean[] flag = new boolean[len];
        for(int[] nums : ranges){
            if (left >= nums[0] && right <= nums[1]){
                return true;
            }
            if (left >= nums[0] && left <= nums[1]){
                left = nums[1] + 1;
            }
            if (right >= nums[0] && right <= nums[1]){
                right = nums[0] - 1;
            }
            if (left > right){
                return true;
            }

            int max = Math.max(L, nums[0]) - L;
            int min = Math.min(nums[1], R) - L;
            for (int i = max; i <= min; i++) {
                flag[i] = true;
            }
        }

        for (int i = 0; i < len; i++) {
            if (!flag[i]){
                return false;
            }
        }
        return true;
    }

    //执行用时：1 ms, 在所有 Java 提交中击败了99.88%的用户
    //内存消耗：54.1 MB, 在所有 Java 提交中击败了77.50%的用户
    //1894. 找到需要补充粉笔的学生编号
    //一个班级里有 n 个学生，编号为 0 到 n - 1 。每个学生会依次回答问题，编号为 0 的学生先回答，然后是编号为 1 的学生，以此类推，直到编号为 n - 1 的学生，然后老师会重复这个过程，重新从编号为 0 的学生开始回答问题。
    //给你一个长度为 n 且下标从 0 开始的整数数组 chalk 和一个整数 k 。一开始粉笔盒里总共有 k 支粉笔。当编号为 i 的学生回答问题时，他会消耗 chalk[i] 支粉笔。如果剩余粉笔数量 严格小于 chalk[i] ，那么学生 i 需要 补充 粉笔。
    //请你返回需要 补充 粉笔的学生 编号 。
    public static int chalkReplacer(int[] chalk, int k) {
        int sum = 0;
        int len = chalk.length;
        for (int i = 0; i < len; i++) {
            if ((sum += chalk[i]) > k){
                return i;
            }
        }

        k = k % sum;

        for (int i = 0; i < len; i++) {
            if (k >= chalk[i]){
                k -= chalk[i];
            }else {
                return i;
            }
        }

        return -1;
    }

    //执行用时：10 ms, 在所有 Java 提交中击败了9.18%的用户
    //内存消耗：83.2 MB, 在所有 Java 提交中击败了31.63%的用户
    //1901. 找出顶峰元素 II
    //一个 2D 网格中的 顶峰元素 是指那些 严格大于 其相邻格子(上、下、左、右)的元素。
    //给你一个 从 0 开始编号 的 m x n 矩阵 mat ，其中任意两个相邻格子的值都 不相同 。找出 任意一个 顶峰元素 mat[i][j] 并 返回其位置 [i,j] 。
    //你可以假设整个矩阵周边环绕着一圈值为 -1 的格子。
    //要求必须写出时间复杂度为 O(m log(n)) 或 O(n log(m)) 的算法
    public static int[] findPeakGrid(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        boolean[][] flag = new boolean[row][col];
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (!flag[x][y]){
                    boolean up = false;
                    //上
                    if (x > 0 && mat[x - 1][y] < mat[x][y]){
                        flag[x - 1][y] = true;
                        up = true;
                    }else if (x == 0){
                        up = true;
                    }

                    //下
                    boolean down = false;
                    if (x < mat.length - 1 && mat[x + 1][y] < mat[x][y]){
                        flag[x + 1][y] = true;
                        down = true;
                    }else if (x == mat.length - 1){
                        down = true;
                    }

                    //左
                    boolean left = false;
                    if (y > 0 && mat[x][y - 1] < mat[x][y]){
                        flag[x][y - 1] = true;
                        left = true;
                    }else if(y == 0){
                        left = true;
                    }

                    //右
                    boolean right = false;
                    if (y < mat[0].length - 1 && mat[x][y + 1] < mat[x][y]){
                        flag[x][y + 1] = true;
                        right = true;
                    }else if (y == mat[0].length - 1){
                        right = true;
                    }

                    if (up && down && left && right){
                        return new int[]{x,y};
                    }
                }
            }
        }
        return new int[]{-1,-1};
    }

    //执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：51.1 MB, 在所有 Java 提交中击败了92.85%的用户
    //1936. 新增的最少台阶数
    //给你一个 严格递增 的整数数组 rungs ，用于表示梯子上每一台阶的 高度 。当前你正站在高度为 0 的地板上，并打算爬到最后一个台阶。
    //另给你一个整数 dist 。每次移动中，你可以到达下一个距离你当前位置（地板或台阶）不超过 dist 高度的台阶。当然，你也可以在任何正 整数 高度处插入尚不存在的新台阶。
    //返回爬到最后一阶时必须添加到梯子上的 最少 台阶数。
    public static int addRungs(int[] rungs, int dist) {
        int last = 0;
        int sum = 0;
        for (int num : rungs) {
            if (num - last > dist){
                int distance = (num - last) % dist == 0 ? (num - last - dist) : (num - last);
                sum +=  distance /  dist;
            }
            last = num;
        }
        return sum;
    }

}