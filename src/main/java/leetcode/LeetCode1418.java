package leetcode;

import java.util.*;

public class LeetCode1418 {

    //1418. 点菜展示表
    //给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，
    //其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
    //请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。
    //接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
    //注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
    public static List<List<String>> displayTable(List<List<String>> orders) {
        Map<String,List<String>> map = new TreeMap<>(Comparator.comparingInt(Integer::parseInt));
        List<String> foodList = new ArrayList<>();
        for (List<String> temp : orders) {
            String fool = temp.get(2);
            if (!foodList.contains(fool)){
                foodList.add(fool);
            }
            String tableNumber = temp.get(1);
            List<String> list = map.computeIfAbsent(tableNumber, k -> new ArrayList<>());
            list.add(fool);
        }

        foodList.sort(((o1, o2) -> {
            if (o1.equals(o2)){
                return 0;
            }
            int len = Math.min(o1.length(),o2.length());
            for (int k = 0; k < len; k++) {
                if (o1.charAt(k) > o2.charAt(k)){
                    return 1;
                }
                if (o1.charAt(k) < o2.charAt(k)){
                    return -1;
                }
            }
            return o1.length() - o2.length();
        }));

        foodList.add(0,"Table");

        List<List<String>> res = new ArrayList<>();
        res.add(foodList);
        for (Map.Entry<String,List<String>> entry : map.entrySet()){
            List<String> values = entry.getValue();
            List<String> temp = new ArrayList<>(foodList.size());
            temp.add(entry.getKey());
            for (int i = 1; i < foodList.size(); i++) {
                temp.add("0");
            }
            for (String value : values){
                int index = foodList.indexOf(value);
                temp.set(index,String.valueOf(Integer.parseInt(temp.get(index)) + 1));
            }
            res.add(temp);
        }
        return res;
    }

    //优化一点 空间换时间
    //1418. 点菜展示表
    //给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，
    //其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
    //请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。
    //接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
    //注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
    public static List<List<String>> displayTable2(List<List<String>> orders) {
        Map<String,Map<String,Integer>> map = new TreeMap<>(Comparator.comparingInt(Integer::parseInt));
        List<String> foodList = new ArrayList<>();
        for (List<String> temp : orders) {
            String fool = temp.get(2);
            if (!foodList.contains(fool)){
                foodList.add(fool);
            }
            String tableNumber = temp.get(1);
            Map<String,Integer> tempMap = map.computeIfAbsent(tableNumber, k -> new HashMap<>());
            tempMap.put(fool,tempMap.getOrDefault(fool,0) + 1);
        }

        foodList.sort(((o1, o2) -> {
            if (o1.equals(o2)){
                return 0;
            }
            int len = Math.min(o1.length(),o2.length());
            for (int k = 0; k < len; k++) {
                if (o1.charAt(k) > o2.charAt(k)){
                    return 1;
                }
                if (o1.charAt(k) < o2.charAt(k)){
                    return -1;
                }
            }
            return o1.length() - o2.length();
        }));
        foodList.add(0,"Table");
        List<List<String>> res = new ArrayList<>();
        res.add(foodList);

        for (Map.Entry<String,Map<String,Integer>> entry : map.entrySet()){
            Map<String, Integer> valueMap = entry.getValue();
            List<String> temp = new ArrayList<>(foodList.size());
            temp.add(entry.getKey());
            for (int i = 1; i < foodList.size(); i++) {
                String s = foodList.get(i);
                temp.add(String.valueOf(valueMap.getOrDefault(s,0)));
            }
            res.add(temp);
        }
        return res;
    }

    public static void main(String[] args) {
        String[][] str = new String[][]{{"David","3","Ceviche"},
                {"Corina","10","Beef Burrito"},
                {"David","3","Fried Chicken"},
                {"Carla","5","Water"},
                {"Carla","5","Ceviche"},
                {"Rous","3","Ceviche"}};
        List<List<String>> listTest = new ArrayList<List<String>>();
        for (int i = 0; i < str.length; i++) {
            List<String> columnList = new ArrayList<String>();
            for (int j = 0; j < str[i].length; j++) {

                columnList.add(j, str[i][j]);

            }
            listTest.add(i, columnList);
        }
        displayTable(listTest);
    }
}
