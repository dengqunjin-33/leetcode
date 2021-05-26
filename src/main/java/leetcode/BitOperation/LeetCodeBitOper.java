package leetcode.BitOperation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCodeBitOper {

    //187. 重复的DNA序列
    //所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，
    //识别 DNA 中的重复序列有时会对研究非常有帮助。
    //编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
    public static List<String> findRepeatedDnaSequences(String s) {
        int len = 10;
        Set<String> list = new HashSet<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= s.length() - len; i++) {
            String temp = s.substring(i, i + len);
            if (set.contains(temp)){
                list.add(temp);
            }else {
                set.add(temp);
            }
        }
        return new ArrayList<>(list);
    }


}
