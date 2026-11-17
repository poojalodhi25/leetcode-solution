class Solution {

    private static final int INF = 1_000_000_000;

    public int minimumChanges(String s, int k) {
        int n = s.length();

        // cost[i][j] = minimum changes to make s[i..j] semi-palindrome
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                cost[l][r] = INF;

                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int cur = 0;

                    for (int start = 0; start < d; start++) {

                        ArrayList<Character> group = new ArrayList<>();

                        for (int pos = l + start; pos <= r; pos += d)
                            group.add(s.charAt(pos));

                        int i = 0;
                        int j = group.size() - 1;

                        while (i < j) {
                            if (!group.get(i).equals(group.get(j)))
                                cur++;
                            i++;
                            j--;
                        }
                    }

                    cost[l][r] = Math.min(cost[l][r], cur);
                }
            }
        }

        int[][] dp = new int[k + 1][n + 1];

        for (int i = 0; i <= k; i++)
            Arrays.fill(dp[i], INF);

        dp[0][0] = 0;

        for (int part = 1; part <= k; part++) {

            for (int end = 2 * part; end <= n; end++) {

                for (int start = 2 * (part - 1); start <= end - 2; start++) {

                    dp[part][end] = Math.min(
                            dp[part][end],
                            dp[part - 1][start] + cost[start][end - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}