class Solution {
    public int maxSum(List<Integer> nums, int k) {

        int MOD = 1_000_000_007;

        // Count set bits at every position
        int[] cnt = new int[32];

        for (int num : nums) {
            for (int bit = 0; bit < 32; bit++) {
                if ((num & (1 << bit)) != 0) {
                    cnt[bit]++;
                }
            }
        }

        long ans = 0;

        // Build k numbers greedily
        while (k-- > 0) {

            long cur = 0;

            for (int bit = 0; bit < 32; bit++) {

                if (cnt[bit] > 0) {
                    cur |= (1L << bit);
                    cnt[bit]--;
                }

            }

            ans = (ans + (cur * cur) % MOD) % MOD;
        }

        return (int) ans;
    }
}