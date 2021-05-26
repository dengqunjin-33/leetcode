package leetcode.BitOperation;

public class LeetCode1734 {

    //1734. 解码异或后的排列
    //给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
    //它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，
    //那么 encoded = [2,1] 。
    //给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
    public static int[] decode(int[] encoded) {
        int [] decoded = new int[encoded.length + 1];
        int total = 0;
        for (int i = 1; i <= decoded.length; i++) {
            total ^= i;
        }
        int extFirst = 0;
        for (int i = 1; i < encoded.length; i += 2) {
            extFirst ^= encoded[i];
        }
        int first = total ^ extFirst;
        decoded[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            decoded[i + 1] = encoded[i] ^ decoded[i];
        }
        return decoded;
    }

    public static void main(String[] args) {
        decode(new int[]{3,1});
    }
}
