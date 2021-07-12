package leetcode.Strings;

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

    public static void main(String[] args) {
        multiply("98","8");
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
            sb.append(' ');
            while (!stack.isEmpty()){
                sb.append(stack.pop());
            }
        }
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
        for (int i = 0; i < list.size(); i++) {
            Map.Entry<Character, StringBuffer> entry = list.get(i);
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
        StringBuffer res = new StringBuffer();
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
        StringBuffer sb = new StringBuffer();
        while (iterator.hasNext()){
            sb.append(iterator.next());
        }
        return sb.toString();
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

        StringBuffer res = new StringBuffer();
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
}
