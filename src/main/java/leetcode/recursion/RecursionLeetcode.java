package leetcode.recursion;

import java.util.*;

public class RecursionLeetcode {

    //15. 三数之和
    //给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
    //注意：答案中不可以包含重复的三元组。
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3){
            return ans;
        }
        threeSum(ans,new ArrayList<>(),nums,0,0,0);
        return ans;
    }

    public static void threeSum(List<List<Integer>> ans,
                                List<Integer> list,
                                int[] nums,
                                int side,
                                int index,
                                int sum) {
        if (side == 3){
           if (sum == 0){
               ArrayList<Integer> nodeList = new ArrayList<>(list);
               nodeList.sort(Comparator.comparingInt(o -> o));
               if (!ans.contains(list)){
                   ans.add(nodeList);
               }
           }
           return ;
       }
        if (index == nums.length){
            return;
        }
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            side += 1;
            sum += nums[i];
            System.out.println("回溯前----->" + list);
            threeSum(ans,list,nums,side, i + 1,sum);
            side -= 1;
            sum -= nums[i];
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        threeSum(new int[]{1,-1,-1,0});
    }

    //17. 电话号码的字母组合
    //给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
    //给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0){
            return res;
        }
        char[][] c = {{},
                {},
                {'a','b','c'},
                {'d','e','f'},
                {'g','h','i'},
                {'j','k','l'},
                {'m','n','o'},
                {'p','q','r','s'},
                {'t','u','v'},
                {'w','x','y','z'}};

        backtrack(res,c,digits,0,new StringBuffer());
        return res;
    }

    public void backtrack(List<String> combinations, char[][] c, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            char[] letters = c[Integer.parseInt(String.valueOf(digit))];
            int lettersCount = letters.length;
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters[i]);
                backtrack(combinations, c, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    //22. 括号生成
    //数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    //39. 组合总和
    //给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
    //candidates 中的数字可以无限制重复被选取。
    //说明：
    //所有数字（包括 target）都是正整数。
    //解集不能包含重复的组合。
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public static void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }

    //46. 全排列
    //给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans,new ArrayList<>(),nums,0);
        return ans;
    }

    public void backtrack(List<List<Integer>> ans,List<Integer> list,int[] nums,int side) {
        if (nums.length == side){
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])){
                continue;
            }
            list.add(nums[i]);
            backtrack(ans,list,nums,side + 1);
            list.remove(list.size() - 1);
        }
    }

    //47. 全排列 II
    //给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        Set<Integer> indexSet = new HashSet<>();
        permuteUnique(ans,new ArrayList<>(),nums,0,indexSet);
        return new ArrayList<>(ans);
    }

    public void permuteUnique(Set<List<Integer>> ans,List<Integer> list,int[] nums,int side,Set<Integer> indexSet) {
        if (nums.length == side){
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (indexSet.contains(i)){
                continue;
            }
            list.add(nums[i]);
            indexSet.add(i);
            permuteUnique(ans,list,nums,side + 1,indexSet);
            list.remove(list.size() - 1);
            indexSet.remove(i);
        }
    }

    //77. 组合
    //给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
    public List<List<Integer>> combine(int n, int k) {
        int nx = Math.min(n - k, k);
        int nj = 1;
        for (int i = n; i > n - nx; i--) {
            nj *= i;
        }

        int kj = 1;
        for (int i = 1; i <= nx; i++) {
            kj *= i;
        }

        List<List<Integer>> ans = new ArrayList<>(nj/kj);
        List<Integer> list = new ArrayList<>(k);
        combine(ans,list,1,n,k);
        return ans;
    }

    public void combine(List<List<Integer>> ans, List<Integer> list,int index,int n, int k) {
        if (list.size() == k){
            ans.add(new ArrayList<>(list));
        }
        for (int i = index; i <= n; i++) {
            list.add(i);
            combine(ans,list,i + 1,n,k);
            list.remove(list.size() - 1);
        }
    }

    //78. 子集
    //给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
    //解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        List<Integer> list = new ArrayList<>();
        subsets(ans,list,nums,0);
        return ans;
    }

    public void subsets(List<List<Integer>> ans, List<Integer> list,int[] nums,int index) {
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            ans.add(new ArrayList<>(list));
            subsets(ans,list,nums,i + 1);
            list.remove(list.size() - 1);
        }
    }

    //79. 单词搜索
    //给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
    //单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
    //同一个单元格内的字母不允许被重复使用。
    public static boolean exist(char[][] board, String word) {
        if ( board.length * board[0].length < word.length()){
            return false;
        }
        boolean[][] falg = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(falg,board,word,i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean exist(boolean[][] falg, char[][] board, String word, int i, int j, int k) {
        if (k == word.length()){
            return true;
        }
        if (i < 0 || i == board.length || j < 0 || j == board[0].length){
            return false;
        }
        if (falg[i][j] || word.charAt(k) != board[i][j]){
            return false;
        }
        falg[i][j] = true;
        boolean ans = exist(falg,board,word,i - 1,j,k + 1)
                || exist(falg,board,word,i ,j+1,k + 1)
                ||  exist(falg,board,word,i +1,j,k + 1)
                || exist(falg,board,word,i,j-1,k + 1);
        falg[i][j] = false;
        return ans;
    }

    //90. 子集 II
    //给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
    //解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        ans.add(new ArrayList<>());
        subsetsWithDup(ans,new ArrayList<>(),nums,0);
        return new ArrayList<>(ans);
    }

    public static void subsetsWithDup(Set<List<Integer>> ans, List<Integer> list, int[] nums, int index) {
        if (index == nums.length){
            return;
        }
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            list.sort(Comparator.comparingInt(o -> o));
            ans.add(new ArrayList<>(list));
            subsetsWithDup(ans,list,nums,i + 1);
            list.remove(Integer.valueOf(nums[i]));
        }
    }

    //216. 组合总和 III
    //找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
    //说明：
    //所有数字都是正整数。
    //解集不能包含重复的组合。
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combinationSum3(res,list,k,n,1);
        return res;
    }

    public void combinationSum3(List<List<Integer>> res,List<Integer> list,int k, int target,int begin) {
        if (k == 0 || target < 0){
            if (target == 0){
                res.add(new ArrayList<>(list));
            }
            return;
        }

        for (int i = begin; i <= 9; i++) {
            list.add(i);
            combinationSum3(res,list,k-1, target-i,i + 1);
            list.remove(list.size() - 1);
        }
    }

    //357. 计算各个位数不同的数字个数
    //给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n
    public static int countNumbersWithUniqueDigits(int n) {
        boolean[] flag = new boolean[10];
        n = Math.min(10,n);
        int count =  countNumbersWithUniqueDigits(0,n,flag);
        return count;
    }

    public static int countNumbersWithUniqueDigits(int index, int n, boolean[] flag) {
        int count = 0;
        if (index == n){
            return count;
        }

        for (int i = 0; i < 10; i++) {
            if (i == 0 && n > 1 && index == 1) {
                continue;
            }
            if (flag[i]){
                continue;
            }
            flag[i] = true;
            count += countNumbersWithUniqueDigits(index + 1,n,flag) + 1;
            flag[i] = false;
        }
        return count;
    }

    //784. 字母大小写全排列
    //给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
    public static List<String> letterCasePermutation(String s) {
        Set<String> set = new HashSet<>();
        letterCasePermutation(set,s,0);
        return new ArrayList<>(set);
    }

    public static void letterCasePermutation(Set<String> set, String s, int begin) {
        if (begin == s.length()){
            set.add(s);
            return;
        }
        for (int i = begin; i < s.length(); i++) {
            letterCasePermutation(set,s,i + 1);
            if (Character.isUpperCase(s.charAt(i))){
                String so = s.substring(0,i) + Character.toLowerCase(s.charAt(i)) + s.substring(i + 1);
                letterCasePermutation(set,so,i + 1);
            }else {
                String so = s.substring(0,i) + Character.toUpperCase(s.charAt(i)) + s.substring(i + 1);
                letterCasePermutation(set,so,i + 1);
            }
        }
    }


}
