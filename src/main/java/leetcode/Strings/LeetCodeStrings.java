package leetcode.Strings;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
