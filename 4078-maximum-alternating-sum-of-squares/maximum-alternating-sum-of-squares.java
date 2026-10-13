import java.util.*;

class Solution {
    public long maxAlternatingSum(int[] nums) {

        Long[] sq = new Long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            long x = nums[i];
            sq[i] = x * x;
        }

        Arrays.sort(sq, Collections.reverseOrder());

        int positive = (nums.length + 1) / 2;

        long ans = 0;

        for (int i = 0; i < sq.length; i++) {
            if (i < positive)
                ans += sq[i];
            else
                ans -= sq[i];
        }

        return ans;
    }
}