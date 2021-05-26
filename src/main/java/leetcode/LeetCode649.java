package leetcode;

import java.util.LinkedList;
import java.util.List;

public class LeetCode649 {
    public static String predictPartyVictory(String senate) {
        List<Integer> RList = new LinkedList<>();
        List<Integer> DList = new LinkedList<>();
        int flag = senate.length();
        for (int i = 0; i < flag; i++) {
            if ('R' == senate.charAt(i)) {
                RList.add(i);
            } else {
                DList.add(i);
            }
        }

        for (; ; ) {
            if (0 == DList.size()) {
                return "Radiant";
            }
            if (0 == RList.size()) {
                return "Dire";
            }

            if (DList.get(0) > RList.get(0)) {
                RList.remove(0);
                DList.remove(0);
                RList.add(flag);
            } else {
                RList.remove(0);
                DList.remove(0);
                DList.add(flag);
            }
            flag++;
        }
    }

    public static void main(String[] args) {
        predictPartyVictory("DDRRR");
    }
}
