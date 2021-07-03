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

    public static void main(String[] args) {
        frequencySort3("eert");
    }
}
