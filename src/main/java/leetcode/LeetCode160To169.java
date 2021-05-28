package leetcode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode160To169 {
    //164.最大间距
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int[] temp = nums;
        //快速排序
        quick(temp, 0, nums.length - 1);

        int max = 0;
        for (int i = 0; i < temp.length - 1; i++) {
            int tempMax = temp[i + 1] - temp[i];
            max = Math.max(max, tempMax);
        }
        return max;
    }

    //快速排序
    private static void quick(int[] arr, int start, int end) {
        if (start < end) {
            //基准位置
            int stand = arr[start];
            int low = start;
            int high = end;
            while (low < high) {
                while (low < high && arr[high] >= stand) {
                    high--;
                }
                arr[low] = arr[high];
                while (low < high && arr[low] <= stand) {
                    low++;
                }
                arr[high] = arr[low];
            }
            arr[low] = stand;
            quick(arr, start, low);
            quick(arr, low + 1, end);
        }
    }

    //165.比较版本号
    public static int compareVersion(String version1, String version2) {
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");
        int maxLength = Math.max(split1.length, split2.length);
        int[] v1 = getVersion(split1, split1.length, maxLength);
        int[] v2 = getVersion(split2, split2.length, maxLength);
        for (int i = 0; i < maxLength; i++) {
            if (v1[i] > v2[i]) {
                return 1;
            } else if (v1[i] < v2[i]) {
                return -1;
            }
        }
        return 0;
    }

    private static int[] getVersion(String[] split, int length, int maxLength) {
        int[] k = new int[maxLength];
        for (int i = 0; i < maxLength; i++) {
            if (i >= length) {
                k[i] = 0;
            } else {
                k[i] = Integer.valueOf(split[i]);
            }
        }
        return k;
    }

    //fixme 这个是错误的 166.分数转小数
    public static String fractionToDecimal(int numerator, int denominator) {
        StringBuffer sb = new StringBuffer();
        String decimal = getDecimal(new StringBuffer(), Math.abs(numerator), Math.abs(denominator), 0);
        if ((numerator >>> 31) != (denominator >>> 31)) {
            sb.append("-");
        }
        if (decimal.contains(".")) {
            sb.append(0).append(decimal);
        } else {
            sb.append(decimal);
        }
        return sb.toString();
    }

    private static String getDecimal(StringBuffer sb, int numerator, int denominator, int index) {
        if (0 == denominator || index > 5) {
            return sb.toString();
        }
        int flag = index;//小数位标记
        if (numerator / denominator == 0) {
            if (".".contains(sb)) {
                sb.append(".");
            }
            flag++;
            numerator = numerator * 10;
        } else {
            sb.append(numerator / denominator);
            numerator = numerator - (denominator * (numerator / denominator));
        }
        String decimal = getDecimal(sb, numerator, denominator, flag);
        return decimal;
    }

    //167. 两数之和
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0, j = numbers.length - 1; i < j; ) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            } else if (sum > target) {
                j--;
            } else {
                i++;
            }
        }
        return null;
    }

    //168. Excel表列名称
    public static String convertToTitle(int n) {
        if (n <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.append((char) (n % 26 + 'A'));
            n = n / 26;
        }
        return sb.reverse().toString();
    }

    //169. 多数元素 解法1
    public static int majorityElement(int[] nums) {
        if (1 == nums.length) {
            return nums[0];
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (null == map.get(nums[i])) {
                map.put(nums[i], nums[i]);
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] == nums[j]) {
                        ++count;
                    }
                }
                if (count > nums.length / 2) {
                    return nums[i];
                } else {
                    count = 1;
                }
            } else {
                continue;
            }
        }
        return 0;
    }

    //169. 多数元素 大神java解法
    public static int majorityElement2(int[] nums) {
        if (1 == nums.length) {
            return nums[0];
        }
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }

}
