package leetcode.Strings;

import java.text.SimpleDateFormat;
import java.util.*;

public class LeetCodeStrings {

    //3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        boolean[] flag = new boolean[256];
        int maxLen = 0;
        for (int i = 0; i < chars.length; i++) {
            int temp = 0;
            for (int j = i; j < chars.length; j++) {
                if (flag[chars[j]]){
                    break;
                }
                else {
                    flag[chars[j]] = true;
                    ++temp;
                }
            }
            maxLen = Math.max(maxLen,temp);
            Arrays.fill(flag,false);
        }
        return maxLen;
    }

    //6. Z 字形变换
    //将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
    public String convert(String s, int numRows) {
        if(numRows == 1 || s.length() <= numRows){
            return s;
        }
        StringBuffer[] list = new StringBuffer[numRows];
        for (int i = 0; i < numRows; i++) {
            list[i] = new StringBuffer();
        }

        char[] chars = s.toCharArray();
        boolean flag = false;
        int index = 0;
        for (char c : chars) {
            list[index].append(c);

            if (index == 0 || index == numRows - 1){
                flag = !flag;
            }

            if (flag){
                ++index;
            }else {
                --index;
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            res.append(list[i]);
        }
        return res.toString();
    }

    //8. 字符串转换整数 (atoi)
    //请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
    //函数 myAtoi(string s) 的算法如下：
    //读入字符串并丢弃无用的前导空格
    //检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
    //读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
    //将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
    //如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
    //返回整数作为最终结果
    public static int myAtoi(String s) {
        char[] chars = s.toCharArray();
        //判断正负
        boolean flag = true;
        //判断是否开始计数
        boolean count = false;
        //判断是否是前导空格
        boolean space = true;
        int res = 0;
        for (int index = 0;index < chars.length;index ++) {
            if (chars[index] == ' ' && space){
            }else if (chars[index] == '-' && !count) {
                space = false;
                flag = false;
                count = true;
            }else if (chars[index] == '+' && !count){
                space = false;
                count = true;
            }else {
                space = false;
                if (chars[index] == '0'){
                    count = true;
                    continue;
                }
                if (chars[index] > '0' && chars[index] <= '9'){
                    int end = index;
                    for (int i = index + 1; i < chars.length; i++) {
                        if (chars[i] >= '0' && chars[i] <= '9'){
                            ++end;
                        }else {
                            break;
                        }
                    }
                    if (end - index > 9){
                        if (flag){
                            res = Integer.MAX_VALUE;
                        }else {
                            res = Integer.MIN_VALUE;
                        }
                    }else {
                        long temp = Long.parseLong(s.substring(index,end + 1));
                        if (flag){
                            res = temp > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) temp;
                        }else {
                            res = temp > Integer.MAX_VALUE ? Integer.MIN_VALUE : (int) -temp;
                        }
                    }
                    break;
                }
                break;
            }
        }

        return res;
    }

    //13. 罗马数字转整数
    //罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
    public static int romanToInt(String s) {
        String rome = "IVXLCDM";
        int[] nums = {1,5,10,50,100,500,1000};
        char[] chars = s.toCharArray();
        int res = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            int prefix = nums[rome.indexOf(chars[i])];
            int suffix = nums[rome.indexOf(chars[i + 1]) ];
            if (suffix > prefix){
                res -= prefix;
            }else {
                res += prefix;
            }
        }
        res += nums[rome.indexOf(chars[chars.length - 1])];
        return res;
    }

    //14. 最长公共前缀
    //编写一个函数来查找字符串数组中的最长公共前缀。
    //如果不存在公共前缀，返回空字符串 ""。
    public String longestCommonPrefix(String[] strs) {
        StringBuffer sb = new StringBuffer();
        if (strs.length == 0){
            return sb.toString();
        }
        if (strs.length == 1){
            return strs[0];
        }

        int len = strs.length;
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(str.length(), minLen);
        }
        for (int i = 0; i < minLen; i++) {
            char temp = strs[0].charAt(i);
            for (int j = 1; j < len; j++) {
                if (temp != strs[j].charAt(i)){
                    return sb.toString();
                }
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    //32. 最长有效括号
    //给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
    public static int longestValidParentheses(String s) {
        if (s.length() < 2){
            return 0;
        }
        int res = 0;
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        boolean[] flag = new boolean[chars.length];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '('){
                stack.push('(');
            }else if (!stack.isEmpty()){
                stack.pop();
                flag[i] = true;
                for (int j = i - 1; j >= 0; j--) {
                    if (!flag[j]){
                        flag[j] = true;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < flag.length; i++) {
            if (flag[i]){
                for (int j = i + 1; j < flag.length + 1; j++) {
                    if (j == flag.length || !flag[j]){
                        res = Math.max(j - i , res);
                        i = j;
                        break;
                    }
                }
            }
        }
        return res;
    }

    //执行用时：
    //25 ms, 在所有 Java 提交中击败了19.08%的用户
    //内存消耗：38.8 MB, 在所有 Java 提交中击败了30.95%的用户
    //43. 字符串相乘
    //给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)){
            return String.valueOf(0);
        }

        int[][] tableMul = {
                {0,0,0,0,0,0,0,0,0,0},
                {0,1,2,3,4,5,6,7,8,9},
                {0,2,4,6,8,10,12,14,16,18},
                {0,3,6,9,12,15,18,21,24,27},
                {0,4,8,12,16,20,24,28,32,36},
                {0,5,10,15,20,25,30,35,40,45},
                {0,6,12,18,24,30,36,42,48,54},
                {0,7,14,21,28,35,42,49,56,63},
                {0,8,16,24,32,40,48,56,64,72},
                {0,9,18,27,36,45,54,63,72,81}};

        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int c1Len = chars1.length;
        int c2Len = chars2.length;

        StringBuffer res = new StringBuffer();

        for (int i = c1Len - 1; i >= 0; i--) {
            StringBuffer sb = new StringBuffer();
            for (int j = c1Len - 1 - i; j > 0; j--) {
                sb.append('0');
            }
            byte temp = 0;
            for (int j = c2Len - 1; j >= 0; j--) {
                temp += tableMul[chars1[i] - '0'][chars2[j] - '0'];
                sb.append(temp % 10);
                temp /= 10;
            }
            while (0 != temp){
                sb.append(temp % 10);
                temp /= 10;
            }


            int resLen = res.length();
            int sbLen = sb.length();
            int maxLen = Math.max(res.length(),sb.length());
            for (int j = 0; j < maxLen; j++) {
                int tempResNum = j < resLen ? res.charAt(j) - '0' : 0;
                int sbNum = j < sbLen ? sb.charAt(j) - '0' : 0;
                temp += tempResNum + sbNum;
                int appendNum = temp % 10;
                if (j < resLen ){
                    res.replace(j,j + 1,String.valueOf(appendNum));
                }else {
                    res.append(appendNum);
                }

                temp /= 10;
            }

            while (0 != temp){
                res.append(temp % 10);
                temp /= 10;
            }
        }
        return res.reverse().toString();
    }

    //97. 交错字符串
    //给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
    //两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
    //s = s1 + s2 + ... + sn
    //t = t1 + t2 + ... + tm
    //|n - m| <= 1
    //交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
    //提示：a + b 意味着字符串 a 和 b 连接。
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[][] f = new boolean[n + 1][m + 1];

        f[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[i][j] = f[i][j] || (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[n][m];
    }


    //139. 单词拆分
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    //150. 逆波兰表达式求值
    //执行用时：5 ms, 在所有 Java 提交中击败了94.63%的用户
    //内存消耗：38.3 MB, 在所有 Java 提交中击败了20.58%
    //的用户
    //根据 逆波兰表示法，求表达式的值。
    //有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack  = new Stack<>();
        for (String s : tokens) {
            switch (s){
                case "+":
                    Integer first = stack.pop();
                    Integer second = stack.pop();
                    stack.push(second + first);
                    break;
                case "-":
                    first = stack.pop();
                    second = stack.pop();
                    stack.push(second - first);
                    break;
                case "*":
                    first = stack.pop();
                    second = stack.pop();
                    stack.push(second * first);
                    break;
                case "/":
                    first = stack.pop();
                    second = stack.pop();
                    stack.push(second / first);
                    break;
                default:
                    stack.push(Integer.parseInt(s));
            }
        }
        return stack.peek();
    }

    //151. 翻转字符串里的单词
    //栈操作 执行用时：
    //6 ms, 在所有 Java 提交中击败了77.72%的用户
    //内存消耗：39.2 MB, 在所有 Java 提交中击败了5.04%的用户
    //给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
    //单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
    //请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
    //说明：
    //输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
    //翻转后单词间应当仅用一个空格分隔。
    //翻转后的字符串中不应包含额外的空格。
    public String reverseWords(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuffer sb = new StringBuffer();
        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == ' '){
                stackPop(stack,sb);
            }else {
                stack.push(chars[i]);
            }
        }

        stackPop(stack,sb);
        return sb.toString();
    }

    private void stackPop(Stack<Character> stack,StringBuffer sb){
        if (!stack.isEmpty()){
            if (0 != sb.length()){
                sb.append(' ');
            }
            while (!stack.isEmpty()){
                sb.append(stack.pop());
            }
        }
    }

    //执行用时：1 ms, 在所有 Java 提交中击败了80.56%的用户
    //内存消耗：36.5 MB, 在所有 Java 提交中击败了50.42%的用户
    //165. 比较版本号
    //给你两个版本号 version1 和 version2 ，请你比较它们。
    //版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
    //比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
    //返回规则如下：
    //如果 version1 > version2 返回 1，
    //如果 version1 < version2 返回 -1，
    //除此之外返回 0。
    public static int compareVersion(String version1, String version2) {
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");
        int len = Math.max(split1.length,split2.length);
        int maxIndex1 = split1.length - 1;
        int maxIndex2 = split2.length - 1;
        for (int i = 0; i < len; i++) {
            int x1 = maxIndex1 < i ? 0 : Integer.parseInt(split1[i]);
            int x2 = maxIndex2 < i ? 0 : Integer.parseInt(split2[i]);
            if (x1 > x2){
                return 1;
            }
            if (x1 < x2){
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        compareVersion("7.5.2.4","7.5.3");
    }

    //171. Excel表列序号
    //给定一个Excel表格中的列名称，返回其相应的列序号。
    public int titleToNumber(String columnTitle) {
        char[] chars = columnTitle.toCharArray();
        int base = 1;
        int res = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            res += (chars[i] - 'A' + 1) * base;
            base *= 26;
        }
        return res;
    }

    //执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：38.3 MB, 在所有 Java 提交中击败了78.24%的用户
    //345. 反转字符串中的元音字母
    //给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
    //元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int right = chars.length - 1;
        for (int i = 0; i < right; i++) {
            if (isVowels(chars[i])){
                for (; right > i; right--) {
                    if (isVowels(chars[right])){
                        chars[i] ^= chars[right];
                        chars[right] ^= chars[i];
                        chars[i] ^= chars[right];
                        right--;
                        break;
                    }
                }
            }
        }
        return new String(chars);
    }

    public boolean isVowels(char ch){
        return ch == 'a' ||
                ch == 'e' ||
                ch == 'i' ||
                ch == 'o' ||
                ch == 'u' ||
                ch == 'A' ||
                ch == 'E' ||
                ch == 'I' ||
                ch == 'O' ||
                ch == 'U';
    }

    //402. 移掉K位数字
    //给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
    //注意:
    //num 的长度小于 10002 且 ≥ k。
    //num 不会包含任何前导零。
    public static String removeKdigits(String num, int k) {
        if (num.length() <= k){
            return String.valueOf(0);
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while(k > 0 && !stack.isEmpty() && c < stack.peek()){
                stack.pop();
                k--;
            }
            if( c != '0' || !stack.isEmpty()){
                stack.push(c);
            }
        }

        while( k > 0 && !stack.isEmpty()){
            stack.pop();
            k--;
        }

        if (stack.size() == 0){
            return String.valueOf(0);
        }

        StringBuffer buffer = new StringBuffer();
        while(!stack.isEmpty()){
            buffer.append(stack.pop());
        }

        return buffer.reverse().toString();
    }

    //451. 根据字符出现频率排序
    //给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
    public static String frequencySort(String s) {
        Map<Character,StringBuffer> map = new TreeMap();
        StringBuffer sb = new StringBuffer();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            StringBuffer orDefault = map.getOrDefault(c, new StringBuffer());
            map.put(c,orDefault.append(c));
        }
        List<Map.Entry<Character,StringBuffer>> list = new ArrayList<Map.Entry<Character,StringBuffer>>(map.entrySet());

        //升序排序
        list.sort((o1, o2) -> o2.getValue().length() - o1.getValue().length());
        for (Map.Entry<Character, StringBuffer> entry : list) {
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

    //使用数组优化
    //451. 根据字符出现频率排序
    //给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
    public static String frequencySort2(String s) {
        StringBuffer[] sbs = new StringBuffer[256];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            sbs[c] = null == sbs[c] ? new StringBuffer().append(c) : sbs[c].append(c);
        }
        Arrays.sort(sbs, (o1, o2) -> {
            if (null == o1 && null == o2){
                return 0;
            }
            if (null == o1) {
                return 1;
            }
            if (null == o2){
                return -1;
            }
            return o2.length() - o1.length();
        });
        StringBuilder res = new StringBuilder();
        for (StringBuffer sb : sbs) {
            if (null == sb) {
                break;
            }
            res.append(sb);
        }
        return res.toString();
    }

    //继续优化代码
    //451. 根据字符出现频率排序
    //给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
    public static String frequencySort3(String s) {
        StringBuffer[] sbs = new StringBuffer[256];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            sbs[c] = null == sbs[c] ? new StringBuffer().append(c) : sbs[c].append(c);
        }
        Set<StringBuffer> set = new TreeSet<>((o1, o2) -> o2.length() > o1.length() ? 1 : -1);
        for (StringBuffer sb : sbs) {
            if (null != sb) {
                set.add(sb);
            }
        }
        Iterator<StringBuffer> iterator = set.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()){
            sb.append(iterator.next());
        }
        return sb.toString();
    }

    //516. 最长回文子序列
    //给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
    //子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char c1 = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                char c2 = s.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    //执行用时：2 ms, 在所有 Java 提交中击败了29.02%的用户
    //内存消耗：38.6 MB, 在所有 Java 提交中击败了30.23%的用户
    //541. 反转字符串 II
    //给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
    //如果剩余字符少于 k 个，则将剩余字符全部反转。
    //如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
    public static String reverseStr(String s, int k) {
        int len = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i += 2 * k) {
            int left = i;
            int right = left + (Math.min(len - i, k)) - 1;
            while (left < right) {
                chars[left] ^= chars[right];
                chars[right] ^= chars[left];
                chars[left] ^= chars[right];
                left++;
                right--;
            }
        }
        return new String(chars);
    }

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：36.8 MB, 在所有 Java 提交中击败了7.40%的用户
    //551. 学生出勤记录 I
    //给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
    //'A'：Absent，缺勤
    //'L'：Late，迟到
    //'P'：Present，到场
    //如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
    //按 总出勤 计，学生缺勤（'A'）严格 少于两天。
    //学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
    //如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。
    public boolean checkRecord(String s) {
        int aLen = 0;
        int lLen = 0;
        char[] chars = s.toCharArray();
        for(char ch : chars){
            if ('L' == ch){
                if (++lLen == 3){
                    return false;
                }
            }else {
                if ('A' == ch && ++aLen == 2){
                    return false;
                }
                lLen = 0;
            }
        }
        return lLen < 3 || aLen < 2;
    }

    //726. 原子的数量
    //给定一个化学式formula（作为字符串），返回每种原子的数量。
    //原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
    //如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
    //两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
    //一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
    //给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
    public static String countOfAtoms(String formula) {
        StringBuffer sb = new StringBuffer().append('(').append(formula).append(')');
        dealingString(sb);
        Map<String,Integer> map = new TreeMap<>();
        int start = 0;
        xx:for (int i = start; i < sb.length(); i++) {
            if (sb.charAt(i) >= '0' && sb.charAt(i) <= '9'){
                for (int j = i; j < sb.length(); j++) {
                    if (j == sb.length() - 1){
                        String sub = sb.substring(start, i);
                        map.put(sub,map.getOrDefault(sub,0) + Integer.parseInt(sb.substring(i,sb.length())));
                        break xx;
                    }
                    if (sb.charAt(j) >= '0' && sb.charAt(j) <= '9'){
                        continue;
                    }
                    else {
                        String sub = sb.substring(start, i);
                        map.put(sub,map.getOrDefault(sub,0) + Integer.parseInt(sb.substring(i,j)));
                        start = j;
                        i = j - 1;
                        break;
                    }
                }
            }
        }

        StringBuilder res = new StringBuilder();
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            res.append(entry.getKey());
            if (1 < entry.getValue()){
                res.append(entry.getValue());
            }
        }
        return res.toString();
    }

    public static void dealingString(StringBuffer sb){
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < sb.length(); i++) {
            if ('(' == sb.charAt(i)){
                stack.push(i);
            }
            if (')' == sb.charAt(i)){
                Integer pop = stack.pop();
                int end = i;
                if (i == sb.length() - 1){
                    end = sb.length();
                }
                for (int j = i + 1; j < sb.length(); j++) {
                    if (sb.charAt(j) >= '0' && sb.charAt(j) <= '9'){
                        end = j;
                    }else {
                        end = j;
                        break;
                    }
                }

                int temp = 1;
                if (end == sb.length() - 1){
                    try {
                        temp = Integer.parseInt(sb.substring(i + 1,sb.length()));
                        end = sb.length();
                    }catch (Exception ignore){}
                }
                if (end - 1 != i){
                    try {
                        temp = Integer.parseInt(sb.substring(i + 1,end));
                    }catch (Exception ignore){}
                }

                StringBuffer stringBuffer = dealingSb(sb.substring(pop + 1, i), temp);
                sb.delete(pop,end).insert(pop,stringBuffer);
                dealingString(sb);
                return;
            }
        }
    }

    //括号处理
    public static StringBuffer dealingSb(String data, int k){
        StringBuffer res = new StringBuffer();
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        boolean flag = false;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) >= '0' && data.charAt(i) <= '9'){
                if (!flag){
                    index = i;
                    flag = true;
                    Integer pop = stack.pop();
                    res.append(data, pop, i);
                }
            }
            if (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'){
                try {
                    int count = Integer.parseInt(data.substring(index,i));
                    res.append(count * k);
                    flag = false;
                }catch (Exception ignored){}
                if (!stack.isEmpty()){
                    res.append(data,stack.pop(),i).append(k);
                }
                stack.push(i);
            }
        }

        if (stack.isEmpty()){
            try {
                int count = Integer.parseInt(data.substring(index));
                res.append(count * k);
            }catch (Exception ignored){}
        }else {
            res.append(data, stack.pop(),data.length()).append(k);
        }
        return res;
    }

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：36.1 MB, 在所有 Java 提交中击败了75.65%的用户
    //1221. 分割平衡字符串
    //在一个 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。
    //给你一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
    //注意：分割得到的每个字符串都必须是平衡字符串。
    //返回可以通过分割得到的平衡字符串的 最大数量 。
    public int balancedStringSplit(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        int flag = 0;
        for(char ch : chars){
            if (ch == 'L'){
                --flag;
            }else {
                ++flag;
            }
            if (0 == flag){
                ++count;
            }
        }
        return count;
    }

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：36.9 MB, 在所有 Java 提交中击败了17.45%的用户
    //1844. 将所有数字用字符替换
    //给你一个下标从 0 开始的字符串 s ，它的 偶数 下标处为小写英文字母，奇数 下标处为数字。
    //定义一个函数 shift(c, x) ，其中 c 是一个字符且 x 是一个数字，函数返回字母表中 c 后面第 x 个字符。
    //比方说，shift('a', 5) = 'f' 和 shift('x', 0) = 'x' 。
    //对于每个 奇数 下标 i ，你需要将数字 s[i] 用 shift(s[i-1], s[i]) 替换。
    //请你替换所有数字以后，将字符串 s 返回。题目 保证 shift(s[i-1], s[i]) 不会超过 'z' 。
    public String replaceDigits(String s) {
        StringBuffer sb = new StringBuffer();
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i+=2) {
            chars[i] = (char)(chars[i - 1] + chars[i] - '0');
        }
        sb.append(chars);
        return sb.toString();
    }

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：36.5 MB, 在所有 Java 提交中击败了63.18%的用户
    //1859. 将句子排序
    //一个 句子 指的是一个序列的单词用单个空格连接起来，且开头和结尾没有任何空格。每个单词都只包含小写或大写英文字母。
    //我们可以给一个句子添加 从 1 开始的单词位置索引 ，并且将句子中所有单词 打乱顺序 。
    //比方说，句子 "This is a sentence" 可以被打乱顺序得到 "sentence4 a3 is2 This1" 或者 "is2 sentence4 This1 a3" 。
    //给你一个 打乱顺序 的句子 s ，它包含的单词不超过 9 个，请你重新构造并得到原本顺序的句子。
    public String sortSentence(String s) {
        StringBuffer[] sb = new StringBuffer[10];
        char[] chars = s.toCharArray();
        StringBuffer temp = new StringBuffer();
        for (char ch : chars) {
            if (ch == ' '){
                continue;
            }
            if (ch > '0' && ch <= '9'){
                sb[ch - '0'] = temp;
                temp = new StringBuffer();
            }else {
                temp.append(ch);
            }
        }
        StringBuffer res = new StringBuffer();
        for (int i = 1; i < 9; i++) {
            if (null != sb[i]){
                res.append(sb[i]).append(' ');
            }
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }


    //执行用时：12 ms, 在所有 Java 提交中击败了75.35%的用户
    //内存消耗：39.2 MB, 在所有 Java 提交中击败了75.63%的用户
    //1881. 插入后的最大值
    //给你一个非常大的整数 n 和一个整数数字 x ，大整数 n 用一个字符串表示。n 中每一位数字和数字 x 都处于闭区间 [1, 9] 中，且 n 可能表示一个 负数 。
    //你打算通过在 n 的十进制表示的任意位置插入 x 来 最大化 n 的 数值。但 不能 在负号的左边插入 x 。
    //例如，如果 n = 73 且 x = 6 ，那么最佳方案是将 6 插入 7 和 3 之间，使 n = 763 。
    //如果 n = -55 且 x = 2 ，那么最佳方案是将 2 插在第一个 5 之前，使 n = -255 。
    //返回插入操作后，用字符串表示的 n 的最大值。
    public static String maxValue(String n, int x) {
        StringBuffer sb = new StringBuffer();
        char[] chars = n.toCharArray();
        boolean flag = false;
        if (chars[0] == '-'){
            sb.append('-');
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] - '0' > x){
                    sb.append(x);
                    flag = true;
                    sb.append(n.substring(i));
                    break;
                }
                sb.append(chars[i]);
            }
        }else {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] - '0' < x){
                    sb.append(x);
                    flag = true;
                    sb.append(n.substring(i));
                    break;
                }
                sb.append(chars[i]);
            }
        }
        if (!flag){
            sb.append(x);
        }
        return sb.toString();
    }

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：36.1 MB, 在所有 Java 提交中击败了97.65%的用户
    //1904. 你完成的完整对局数
    //一款新的在线电子游戏在近期发布，在该电子游戏中，以 刻钟 为周期规划若干时长为 15 分钟 的游戏对局。这意味着，在 HH:00、HH:15、HH:30 和 HH:45 ，将会开始一个新的对局，其中 HH 用一个从 00 到 23 的整数表示。游戏中使用 24 小时制的时钟 ，所以一天中最早的时间是 00:00 ，最晚的时间是 23:59 。
    //给你两个字符串 startTime 和 finishTime ，均符合 "HH:MM" 格式，分别表示你 进入 和 退出 游戏的确切时间，请计算在整个游戏会话期间，你完成的 完整对局的对局数 。
    //例如，如果 startTime = "05:20" 且 finishTime = "05:59" ，这意味着你仅仅完成从 05:30 到 05:45 这一个完整对局。而你没有完成从 05:15 到 05:30 的完整对局，因为你是在对局开始后进入的游戏；同时，你也没有完成从 05:45 到 06:00 的完整对局，因为你是在对局结束前退出的游戏。
    //如果 finishTime 早于 startTime ，这表示你玩了个通宵（也就是从 startTime 到午夜，再从午夜到 finishTime）。
    //假设你是从 startTime 进入游戏，并在 finishTime 退出游戏，请计算并返回你完成的 完整对局的对局数 。
    public static int numberOfRounds(String startTime, String finishTime) {
        int start = getTimeParseMin(startTime);
        int end = getTimeParseMin(finishTime);
        int allTime = 0;
        int count = 0;

        if (start >= end){
            allTime += 1440;
        }

        if (start % 15 != 0){
            ++count;
            start -= start % 15;
        }
        end -= end % 15;

        allTime += end - start;


        return Math.max(allTime / 15 - count, 0);
    }

    public static int getTimeParseMin(String time) {
        return (time.charAt(0) - '0') * 600 +
                (time.charAt(1) - '0') * 60 +
                (time.charAt(3) - '0') * 10 +
                (time.charAt(4) - '0');
    }

    public static List<String> dateFormat(String start, String end){
        String hengGang = "-";
        String[] split = start.split(hengGang);
        //设置初始时间
        //获取 2014.03.01.0.0.0
        Calendar startCal = Calendar.getInstance();
        startCal.set(Calendar.YEAR,Integer.parseInt(split[0]));
        startCal.set(Calendar.MONTH,Integer.parseInt(split[1]) - 1);
        startCal.set(Calendar.DAY_OF_MONTH,1);
        startCal.set(Calendar.HOUR_OF_DAY,0);
        startCal.set(Calendar.MINUTE,0);
        startCal.set(Calendar.SECOND,0);
        startCal.set(Calendar.MILLISECOND,0);

        //获取2015.06.01，0.0.0
        split = end.split(hengGang);
        Calendar endCal = (Calendar) startCal.clone();
        endCal.set(Calendar.YEAR,Integer.parseInt(split[0]));
        endCal.set(Calendar.MONTH,Integer.parseInt(split[1]) - 1);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        List<String> res = new ArrayList<>();
        Calendar cur = (Calendar) startCal.clone();
        while (cur.getTime().compareTo(endCal.getTime()) <= 0 && cur.getTime().compareTo(startCal.getTime()) >= 0){
            System.out.println(cur.getTime().toString());
            String dayStr = sdf.format(cur.getTime());
            res.add(dayStr);
            cur.add(Calendar.MONTH, 1);
        }
        return res;
    }
}
