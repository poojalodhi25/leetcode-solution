import java.util.*;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        HashMap<Integer, Integer> dp = new HashMap<>();
        int ans = 1;

        for (int x : nums) {
            int oldX = dp.getOrDefault(x, 0);

            // Use x+1
            dp.put(x + 1,
                    Math.max(dp.getOrDefault(x + 1, 0), oldX + 1));

            // Use x
            int keep = dp.getOrDefault(x - 1, 0) + 1;
            dp.put(x,
                    Math.max(dp.getOrDefault(x, 0), keep));

            ans = Math.max(ans,
                    Math.max(dp.get(x), dp.get(x + 1)));
        }

        return ans;
    }
}