package leetcode.weekgame;

import java.util.Arrays;

public class LeetCode237w {


    //1832. 判断句子是否为全字母句
    //全字母句 指包含英语字母表中每个字母至少一次的句子。
    //给你一个仅由小写英文字母组成的字符串 sentence ，请你判断 sentence 是否为 全字母句 。
    //如果是，返回 true ；否则，返回 false 。
    public boolean checkIfPangram(String sentence) {
        boolean[] ch = new boolean[26];
        for (int i = 0; i < sentence.length(); i++) {
            ch[sentence.charAt(i) - 'a'] = true;
        }

        boolean flag = true;
        for (boolean b : ch) {
            flag &= b;
        }
        return flag;
    }

    //大佬的位运算
    //1832. 判断句子是否为全字母句
    //全字母句 指包含英语字母表中每个字母至少一次的句子。
    //给你一个仅由小写英文字母组成的字符串 sentence ，请你判断 sentence 是否为 全字母句 。
    //如果是，返回 true ；否则，返回 false 。
    public boolean checkIfPangram2(String sentence) {
        int x = 0;
        int target = 0;
        for (int i = 0; i < 26; i++) {
            target |= 1 << i;
        }
        for (int i = 0; i < sentence.length(); i++) {
            x |= 1 << (sentence.charAt(i) - 'a');
            if (x == target){
                return true;
            }
        }
        return false;
    }

    //1833. 雪糕的最大数量
    //夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
    //商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
    //给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
    //注意：Tony 可以按任意顺序购买雪糕。
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0;
        for (int cost : costs) {
            if (cost <= coins) {
                ++count;
                coins -= cost;
            } else {
                return count;
            }
        }
        return count;
    }

}
