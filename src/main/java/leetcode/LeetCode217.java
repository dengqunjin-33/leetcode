package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeetCode217 {
    //217. 存在重复元素 先排序再找
    public boolean containsDuplicate(int[] nums) {
        if (nums.length < 2) {
            return true;
        } else {
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] == nums[i + 1]) {
                    return true;
                }
            }
            return false;
        }
    }

    //217. 存在重复元素 set集合查重 比排序再找还慢
    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return nums.length > set.size();
    }
}
