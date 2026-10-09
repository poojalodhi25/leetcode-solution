class Solution {
    public int minInsertions(String s) {

        int ans = 0;
        int need = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {

                // If need is odd, complete one ')' first
                if ((need & 1) == 1) {
                    ans++;
                    need--;
                }

                // New '(' needs two ')'
                need += 2;

            } else {

                // One ')' is matched
                need--;

                // More ')' than needed
                if (need == -1) {
                    ans++;      // Insert '('
                    need = 1;   // One ')' already used, one more needed
                }
            }
        }

        return ans + need;
    }
}