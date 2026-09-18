import java.util.*;

class Solution {

    public List<String> maxNumOfSubstrings(String s) {

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Step 1: Find first and last occurrence
        for (int i = 0; i < s.length(); i++) {

            int index = s.charAt(i) - 'a';

            if (first[index] == -1) {
                first[index] = i;
            }

            last[index] = i;
        }

        // Store valid intervals
        List<int[]> intervals = new ArrayList<>();

        // Step 2: Create valid intervals
        for (int i = 0; i < s.length(); i++) {

            int chIndex = s.charAt(i) - 'a';

            // Only start from first occurrence
            if (i != first[chIndex]) {
                continue;
            }

            int end = last[chIndex];
            boolean valid = true;

            for (int j = i; j <= end; j++) {

                int current = s.charAt(j) - 'a';

                // This character appeared before our start
                if (first[current] < i) {
                    valid = false;
                    break;
                }

                // Expand interval if necessary
                end = Math.max(end, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{i, end});
            }
        }

        // Step 3: Sort intervals by ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        // Step 4: Greedy selection
        List<String> answer = new ArrayList<>();

        int previousEnd = -1;

        for (int[] interval : intervals) {

            int start = interval[0];
            int end = interval[1];

            if (start > previousEnd) {

                answer.add(s.substring(start, end + 1));

                previousEnd = end;
            }
        }

        return answer;
    }
}