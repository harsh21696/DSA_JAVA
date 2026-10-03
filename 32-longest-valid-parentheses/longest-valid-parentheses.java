public class Solution {
    public static int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();

        stack.push(-1);

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            if(ch == '(') {
                stack.push(i);
            }
            else {
                stack.pop();

                if(stack.isEmpty()) {
                    stack.push(i);
                }
                 else {
                    int len = i - stack.peek();
                    maxLen = Math.max(maxLen, len);
                }
            }
        }

        return maxLen;
    }
}
