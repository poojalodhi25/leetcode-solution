class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < num; i++) {
            int remainingPos = num - i - 1;

            for (int d = 9; d >= 0; d--) {
                int remainingSum = sum - d;

                if (remainingSum < 0) continue;

                if (remainingSum <= remainingPos * 9) {
                    ans.append(d);
                    sum = remainingSum;
                    break;
                }
            }
        }

        return ans.toString();
    }
}