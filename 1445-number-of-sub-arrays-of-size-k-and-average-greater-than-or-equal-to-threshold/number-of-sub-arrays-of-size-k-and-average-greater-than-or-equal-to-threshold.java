class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {

        int i = 0;
        int j = k - 1;
        int sum = 0;
        int count = 0;
        int n = arr.length;

        // First window
        for (int a = 0; a <= k - 1; a++) {
            sum += arr[a];
        }

        // Check first window
        if (sum >= k * threshold) {
            count++;
        }

        i++;
        j++;

        // Remaining windows
        while (j < n) {

            // Remove left element and add new right element
            sum = sum - arr[i - 1] + arr[j];

            if (sum >= k * threshold) {
                count++;
            }

            i++;
            j++;
        }

        return count;
    }
}