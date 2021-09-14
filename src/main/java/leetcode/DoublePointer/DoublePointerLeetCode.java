package leetcode.DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DoublePointerLeetCode {


    //15. 三数之和
    //给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
    //注意：答案中不可以包含重复的三元组。
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3){
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0){
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int L = i + 1;
            int R = nums.length - 1;
            while (L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if (sum < 0){
                    ++L;
                }else if (sum > 0){
                    --R;
                }else {
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) {
                        L++; // 去重
                    }
                    while (L<R && nums[R] == nums[R-1]) {
                        R--; // 去重
                    }
                    ++L;
                    --R;
                }
            }
        }
        return ans;
    }

    //27. 移除元素
    //给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
    //不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
    //元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        for (; right >= 0; right--) {
            if (nums[right] != val){
                break;
            }
        }

        while (left < right){
            if (nums[left] == val){
                nums[left] ^= nums[right];
                nums[right] ^= nums[left];
                nums[left] ^= nums[right];
                --right;
                for (; right >= 0; right--) {
                    if (nums[right] != val){
                        break;
                    }
                }
            }
            ++left;
        }

        return nums[left] == val ? left : left + 1;
    }

    //88. 合并两个有序数组
    //给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
    //初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，
    // 这样它就有足够的空间保存来自 nums2 的元素。
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0){
            return;
        }
        int left = 0;
        int right = m;
        for (int temp : nums2) {
            for (int j = left; j <= right; j++) {
                if (j == right){
                    nums1[right] = temp;
                    ++left;
                    ++right;
                    break;
                }
                if (nums1[j] > temp) {
                    System.arraycopy(nums1, j, nums1, j + 1, right - j);
                    nums1[j] = temp;
                    left = j+1;
                    ++right;
                    break;
                }
            }
        }
    }

    //125. 验证回文串
    //给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
    //说明：本题中，我们将空字符串定义为有效的回文串。
    public static boolean isPalindrome(String s) {
        if (s.length() < 2){
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right){
            if (!judgeChar(s.charAt(left))){
                ++left;
                continue;
            }
            if (!judgeChar(s.charAt(right))){
                --right;
                continue;
            }
            int tempL = s.charAt(left) > 90 ? s.charAt(left) - 32 : s.charAt(left);
            int tempR = s.charAt(right) > 90 ? s.charAt(right) - 32 : s.charAt(right);
            if (tempL != tempR){
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    public static boolean judgeChar(char c){
        return (c >= 97 && c <= 122) || (c >= 48 && c <= 57) || (c >= 65 && c <= 90);
    }

    //283. 移动零
    //给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    public static void moveZeroes(int[] nums) {
        if (nums.length < 2){
            return;
        }
        int slow = 0;
        int fast = 0;
        while (fast < nums.length){
            if (nums[slow] == 0 && nums[fast] !=0){
                nums[slow] ^= nums[fast];
                nums[fast] ^= nums[slow];
                nums[slow] ^= nums[fast];
                ++slow;
            }
            if (nums[slow] != 0 && nums[fast] !=0){
                ++slow;
            }
            ++fast;
        }
    }

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：38.1 MB, 在所有 Java 提交中击败了52.85%的用户
    //443. 压缩字符串
    //给你一个字符数组 chars ，请使用下述算法压缩：
    //从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
    //如果这一组长度为 1 ，则将字符追加到 s 中。
    //否则，需要向 s 追加字符，后跟这一组的长度。
    //压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
    //请在 修改完输入数组后 ，返回该数组的新长度。
    //你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
    public static int compress(char[] chars) {
        int res = 1;
        int left = 0;
        int right = 1;
        for (; right < chars.length; right++) {
            if (chars[right] != chars[right - 1]){
                if (right - left != 1) {
                    int index = res;
                    int temp = right - left;
                    while (temp != 0) {
                        chars[res++] = (char) (temp % 10 + '0');
                        temp /= 10;
                    }
                    int end = res - 1;
                    while (index < end) {
                        chars[index] ^= chars[end];
                        chars[end] ^= chars[index];
                        chars[index] ^= chars[end];
                        ++index;
                        --end;
                    }
                }
                chars[res++] = chars[right];
                left = right;
            }else if (right == chars.length - 1){
                int index = res;
                int temp = right - left + 1;
                while (temp != 0) {
                    chars[res++] = (char) (temp % 10 + '0');
                    temp /= 10;
                }
                int end = res - 1;
                while (index < end) {
                    chars[index] ^= chars[end];
                    chars[end] ^= chars[index];
                    chars[index] ^= chars[end];
                    ++index;
                    --end;
                }
            }
        }
        return res;
    }


    //执行用时：22 ms, 在所有 Java 提交中击败了49.47%的用户
    //内存消耗：38.7 MB, 在所有 Java 提交中击败了
    //99.54%
    //的用户
    //524. 通过删除字母匹配到字典里最长单词
    //给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
    //如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
    public static String findLongestWord(String s, List<String> dictionary) {
        String res = "";
        int dIndex;
        int sIndex;
        int sLen = s.length();
        for (String dict : dictionary) {
            dIndex = 0;
            sIndex = 0;
            int tLen = dict.length();
            while (dIndex < tLen && sIndex < sLen) {
                if (dict.charAt(dIndex) == s.charAt(sIndex)) {
                    ++dIndex;
                }
                ++sIndex;
            }
            if (dIndex == tLen) {
                if (tLen > res.length() || (tLen == res.length() && dict.compareTo(res) < 0)){
                    res = dict;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strings = {"apple","ewaf","awefawfwaf","awef","awefe","ewafeffewafewf"};
        findLongestWord("aewfafwafjlwajflwajflwafj", Arrays.asList(strings));
    }
}
