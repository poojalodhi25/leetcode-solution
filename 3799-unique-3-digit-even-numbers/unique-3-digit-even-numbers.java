class Solution {

    public int totalNumbers(int[] digits) {

        boolean[][][] used = new boolean[10][10][10];

        int count = 0;

        for (int i = 0; i < digits.length; i++) {

            // First digit cannot be 0
            if (digits[i] == 0) {
                continue;
            }

            for (int j = 0; j < digits.length; j++) {

                // Same digit copy cannot be reused
                if (i == j) {
                    continue;
                }

                for (int k = 0; k < digits.length; k++) {

                    // Same digit copy cannot be reused
                    if (i == k || j == k) {
                        continue;
                    }

                    // Last digit must be even
                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int a = digits[i];
                    int b = digits[j];
                    int c = digits[k];

                    if (!used[a][b][c]) {

                        used[a][b][c] = true;
                        count++;
                    }
                }
            }
        }

        return count;
    }
}