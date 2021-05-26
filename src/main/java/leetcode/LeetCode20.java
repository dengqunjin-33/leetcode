package leetcode;

import java.util.Stack;

public class LeetCode20 {

    //20有效的括号
    public boolean isValid(String s) {
        if(s.length() % 2 == 1){
            return false;
        }
        Stack stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)){
                case '(':
                    stack.push('(');
                    break;
                case '[':
                    stack.push('[');
                    break;
                case '{':
                    stack.push('{');
                    break;
                case ')':
                    if (stack.isEmpty()){
                        return false;
                    }
                    if ((char)stack.peek() == '('){
                        stack.pop();
                    }else {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty()){
                        return false;
                    }
                    if ((char)stack.peek() == '['){
                        stack.pop();
                    }else {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty()){
                        return false;
                    }
                    if ((char)stack.peek() == '{'){
                        stack.pop();
                    }else {
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }
}
