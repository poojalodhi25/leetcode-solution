class Solution {
    public int longestValidParentheses(String s) {

        int left = 0;
        int right = 0;
        int maxLength = 0;

        // Left to Right
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * right);
            }

            if (right > left) {
                left = 0;
                right = 0;
            }
        }

        // Right to Left
        left = 0;
        right = 0;

        for (int i = s.length() - 1; i >= 0; i--) {

            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            }

            if (left > right) {
                left = 0;
                right = 0;
            }
        }

        return maxLength;
    }
}