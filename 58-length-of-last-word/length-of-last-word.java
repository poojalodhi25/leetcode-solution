class Solution {
    public int lengthOfLastWord(String s) {

        int i = s.length() - 1;

        // Remove spaces from the end
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        int count = 0;

        // Count last word
        while (i >= 0 && s.charAt(i) != ' ') {
            count++;
            i--;
        }

        return count;
    }
}