package demo;

public class demo {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE * 2);
        int max = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE*2;
        StringBuffer maxStr = new StringBuffer();
        StringBuffer minStr = new StringBuffer();
        for (int i = 31; i >= 0 ; i--) {
            maxStr.append(max & 1);
            minStr.append(min & 1);
            min = min >> 1;
            max = max >> 1;
        }
        System.out.println(Integer.MIN_VALUE * 2);
        System.out.println(maxStr);
        System.out.println(minStr);
    }
}
