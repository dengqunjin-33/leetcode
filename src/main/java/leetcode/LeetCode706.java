package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode706 {

    //706. 设计哈希映射
    class MyHashMap {

        List<Integer> keyList;
        List<Integer> valueList;

        /** Initialize your data structure here. */
        public MyHashMap() {
            keyList = new ArrayList<>();
            valueList = new ArrayList<>();
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            if (!keyList.contains(key)){
                keyList.add(key);
                valueList.add(value);
            }else {
                int indexOf = keyList.indexOf(key);
                valueList.add(indexOf, value);
                valueList.remove(indexOf + 1);
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            if (!keyList.contains(key)){
                return -1;
            }else {
                return valueList.get(keyList.indexOf(key));
            }
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            if (keyList.contains(key)){
                int index = keyList.indexOf(key);
                keyList.remove(Integer.valueOf(key));
                valueList.remove(index);
            }
        }
    }
}
