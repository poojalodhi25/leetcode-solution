class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] result = new long[k];

        long[] dp = new long[k];

        for (int num : nums) {

            long[] next = new long[k];

            int rem = num % k;

            // Sirf current element ka subarray
            next[rem]++;

            // Purane subarrays ke saath current num multiply karo
            for (int r = 0; r < k; r++) {

                if (dp[r] > 0) {
                    int newRem = (int)((r * (long)rem) % k);
                    next[newRem] += dp[r];
                }
            }

            // Current ending wale saare subarrays
            for (int r = 0; r < k; r++) {
                result[r] += next[r];
            }

            dp = next;
        }

        return result;
    }
}