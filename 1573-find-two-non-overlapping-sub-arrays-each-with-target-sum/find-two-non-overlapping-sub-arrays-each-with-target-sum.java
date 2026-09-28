class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = n + 1;

        int[] best = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            best[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int answer = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // Previous best remains available
            best[right + 1] = best[right];

            // Current subarray found
            if (sum == target) {

                int length = right - left + 1;

                // Find a non-overlapping previous subarray
                if (best[left] != INF) {
                    answer = Math.min(answer,
                                      length + best[left]);
                }

                // Store current subarray if it is shorter
                best[right + 1] =
                    Math.min(best[right + 1], length);
            }
        }

        return answer == INF ? -1 : answer;
    }
}