package leetcode.BitOperation;

public class LeetCode1310 {

    //1310. 子数组异或查询
    //有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
    //对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
    //并返回一个包含给定查询 queries 所有结果的数组。
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] temp = queries[i];
            if (temp[0] == temp[1]){
                result[i] = arr[temp[0]];
                continue;
            }
            int xor = 0;
            for (int j = temp[0]; j <= temp[1] ; j++) {
                xor ^= arr[j];
            }
            result[i] = xor;
        }
        return result;
    }

    //1310. 子数组异或查询
    //有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
    //对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
    //并返回一个包含给定查询 queries 所有结果的数组。
    public int[] xorQueries2(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] xors = new int[n + 1];
        for (int i = 0; i < n; i++) {
            xors[i + 1] = xors[i] ^ arr[i];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = xors[queries[i][0]] ^ xors[queries[i][1] + 1];
        }
        return ans;
    }
}
