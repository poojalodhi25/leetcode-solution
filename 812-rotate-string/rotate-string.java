class Solution {
    public boolean rotateString(String s, String goal) {

        // Length must be same
        if (s.length() != goal.length()) {
            return false;
        }

        // All rotations of s exist inside s + s
        String doubled = s + s;

        return doubled.contains(goal);
    }
}