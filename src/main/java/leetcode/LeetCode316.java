package leetcode;

public class LeetCode316 {

    public static String removeDuplicateLetters(String s) {
        if(s.length() < 2){
            return s;
        }

        boolean[] flag = new boolean[26];
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (!flag[s.charAt(i) - 'a']){
                flag[s.charAt(i) - 'a'] = true;
                sb.append(s.charAt(i));
            }else {
                int indexOf = sb.indexOf(String.valueOf(s.charAt(i)));
                if ((((indexOf + 1) <= (sb.length()-1)) && s.charAt(i) > sb.charAt(indexOf + 1)) || s.charAt(i) > sb.charAt(sb.length() - 1))
                {
                    sb.deleteCharAt(indexOf);
                    sb.append(s.charAt(i));
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(0xfffd);
    }
}
