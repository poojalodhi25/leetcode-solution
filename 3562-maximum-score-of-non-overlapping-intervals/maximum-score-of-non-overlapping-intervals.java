import java.util.*;

class Solution {

    static class Interval {
        int start;
        int end;
        int weight;
        int index;

        Interval(int start, int end, int weight, int index) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.index = index;
        }
    }

    static class Result {
        long score;
        int[] indices;

        Result(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    Interval[] arr;
    int n;

    Result[][] dp;
    boolean[][] visited;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        n = intervals.size();

        arr = new Interval[n];

        // Create Interval objects
        for (int i = 0; i < n; i++) {

            int start = intervals.get(i).get(0);
            int end = intervals.get(i).get(1);
            int weight = intervals.get(i).get(2);

            arr[i] = new Interval(start, end, weight, i);
        }

        // Sort by start time
        Arrays.sort(arr, (a, b) -> {

            if (a.start != b.start) {
                return Integer.compare(a.start, b.start);
            }

            return Integer.compare(a.end, b.end);
        });

        dp = new Result[n + 1][5];
        visited = new boolean[n + 1][5];

        Result answer = solve(0, 4);

        return answer.indices;
    }

    private Result solve(int pos, int count) {

        // No intervals left or cannot select more
        if (pos >= n || count == 0) {
            return new Result(0, new int[0]);
        }

        // Already calculated
        if (visited[pos][count]) {
            return dp[pos][count];
        }

        visited[pos][count] = true;

        // -------------------------
        // OPTION 1: Skip
        // -------------------------

        Result skip = solve(pos + 1, count);

        // -------------------------
        // OPTION 2: Take
        // -------------------------

        int next = findNext(pos);

        Result nextResult = solve(next, count - 1);

        long takeScore = arr[pos].weight + nextResult.score;

        int[] takeIndices = addIndex(
                arr[pos].index,
                nextResult.indices
        );

        Result take = new Result(
                takeScore,
                takeIndices
        );

        // -------------------------
        // Choose better result
        // -------------------------

        if (isBetter(take, skip)) {
            dp[pos][count] = take;
        } else {
            dp[pos][count] = skip;
        }

        return dp[pos][count];
    }

    /*
     * Returns true if 'a' is better than 'b'
     *
     * First:
     *     larger score wins
     *
     * If score is same:
     *     lexicographically smaller index array wins
     */
    private boolean isBetter(Result a, Result b) {

        // Higher score is better
        if (a.score != b.score) {
            return a.score > b.score;
        }

        // Same score
        return compareLexicographically(
                a.indices,
                b.indices
        ) < 0;
    }

    /*
     * Add current original index to the answer.
     *
     * We sort because final answer must be in
     * increasing order of original indices.
     */
    private int[] addIndex(int index, int[] existing) {

        int[] result = new int[existing.length + 1];

        result[0] = index;

        for (int i = 0; i < existing.length; i++) {
            result[i + 1] = existing[i];
        }

        Arrays.sort(result);

        return result;
    }

    /*
     * Lexicographical comparison.
     *
     * Example:
     *
     * [3,4] < [4,7]
     *
     * because 3 < 4.
     */
    private int compareLexicographically(int[] a, int[] b) {

        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {

            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }

        // If one is prefix of another,
        // shorter array is lexicographically smaller.
        return Integer.compare(a.length, b.length);
    }

    /*
     * Find first interval whose start > current end.
     */
    private int findNext(int pos) {

        int target = arr[pos].end;

        int left = pos + 1;
        int right = n;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid].start > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}