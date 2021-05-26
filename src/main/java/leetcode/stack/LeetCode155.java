package leetcode.stack;

import java.util.Stack;

public class LeetCode155 {
    //155. 最小栈
    class MinStack {

        Stack<Integer> stack;
        Stack<Integer> minStack;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (minStack.empty() || x <= minStack.peek()){
                minStack.push(x);
            }
        }

        public void pop() {
            Integer pop = stack.pop();
            if (pop.equals(minStack.peek())){
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
