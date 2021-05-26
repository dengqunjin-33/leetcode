package leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

//225. 用队列实现栈
public class LeetCode225 {


    class MyStack {

        Deque<Integer> queue;

        /** Initialize your data structure here. */
        public MyStack() {
            queue = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue.addFirst(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue.remove();
        }

        /** Get the top element. */
        public int top() {
            return empty() ? -1 : queue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
