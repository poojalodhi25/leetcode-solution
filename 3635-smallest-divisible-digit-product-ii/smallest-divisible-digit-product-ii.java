class Solution {

    static int[][] factor = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    int A, B, C, D;
    int[] dp;

    int index(int a, int b, int c, int d) {
        return (((a * (B + 1) + b) * (C + 1) + c) * (D + 1) + d);
    }

    void buildDP() {

        int size = (A + 1) * (B + 1) * (C + 1) * (D + 1);

        dp = new int[size];

        for (int i = 0; i < size; i++) {
            dp[i] = 1_000_000;
        }

        dp[index(0, 0, 0, 0)] = 0;

        for (int a = 0; a <= A; a++) {
            for (int b = 0; b <= B; b++) {
                for (int c = 0; c <= C; c++) {
                    for (int d = 0; d <= D; d++) {

                        if (a == 0 && b == 0 &&
                            c == 0 && d == 0) {
                            continue;
                        }

                        for (int digit = 2; digit <= 9; digit++) {

                            int na = Math.max(0,
                                    a - factor[digit][0]);

                            int nb = Math.max(0,
                                    b - factor[digit][1]);

                            int nc = Math.max(0,
                                    c - factor[digit][2]);

                            int nd = Math.max(0,
                                    d - factor[digit][3]);

                            int previous =
                                dp[index(na, nb, nc, nd)];

                            if (previous != 1_000_000) {

                                dp[index(a, b, c, d)] =
                                    Math.min(
                                        dp[index(a, b, c, d)],
                                        previous + 1
                                    );
                            }
                        }
                    }
                }
            }
        }
    }

    String buildSmallest(int[] req, int length) {

        if (dp[index(
                req[0],
                req[1],
                req[2],
                req[3]
            )] > length) {
            return null;
        }

        StringBuilder ans = new StringBuilder();

        for (int pos = 0; pos < length; pos++) {

            int remaining = length - pos - 1;

            for (int digit = 1; digit <= 9; digit++) {

                int na = Math.max(
                    0,
                    req[0] - factor[digit][0]
                );

                int nb = Math.max(
                    0,
                    req[1] - factor[digit][1]
                );

                int nc = Math.max(
                    0,
                    req[2] - factor[digit][2]
                );

                int nd = Math.max(
                    0,
                    req[3] - factor[digit][3]
                );

                int needed =
                    dp[index(na, nb, nc, nd)];

                if (needed <= remaining) {

                    ans.append((char)('0' + digit));

                    req[0] = na;
                    req[1] = nb;
                    req[2] = nc;
                    req[3] = nd;

                    break;
                }
            }
        }

        return ans.toString();
    }

    public String smallestNumber(String num, long t) {

        // 1. Factorize t
        int[] need = new int[4];

        int[] primes = {2, 3, 5, 7};

        long x = t;

        for (int i = 0; i < 4; i++) {

            while (x % primes[i] == 0) {
                need[i]++;
                x /= primes[i];
            }
        }

        // Another prime exists
        if (x != 1) {
            return "-1";
        }

        A = need[0];
        B = need[1];
        C = need[2];
        D = need[3];

        // 2. DP
        buildDP();

        int n = num.length();

        // 3. Count factors of num
        int[] total = new int[4];

        int firstZero = -1;

        for (int i = 0; i < n; i++) {

            int digit = num.charAt(i) - '0';

            if (digit == 0 && firstZero == -1) {
                firstZero = i;
            }

            if (digit != 0) {
                total[0] += factor[digit][0];
                total[1] += factor[digit][1];
                total[2] += factor[digit][2];
                total[3] += factor[digit][3];
            }
        }

        // 4. num itself
        if (firstZero == -1 &&
            total[0] >= need[0] &&
            total[1] >= need[1] &&
            total[2] >= need[2] &&
            total[3] >= need[3]) {

            return num;
        }

        // 5. Same length
        int[] suffix = new int[4];

        for (int i = n - 1; i >= 0; i--) {

            int current = num.charAt(i) - '0';

            if (firstZero == -1 || i <= firstZero) {

                int[] prefix = new int[4];

                prefix[0] =
                    total[0] - suffix[0] - factor[current][0];

                prefix[1] =
                    total[1] - suffix[1] - factor[current][1];

                prefix[2] =
                    total[2] - suffix[2] - factor[current][2];

                prefix[3] =
                    total[3] - suffix[3] - factor[current][3];

                int start = Math.max(1, current + 1);

                for (int digit = start; digit <= 9; digit++) {

                    int[] req = new int[4];

                    req[0] = Math.max(
                        0,
                        need[0] - prefix[0] - factor[digit][0]
                    );

                    req[1] = Math.max(
                        0,
                        need[1] - prefix[1] - factor[digit][1]
                    );

                    req[2] = Math.max(
                        0,
                        need[2] - prefix[2] - factor[digit][2]
                    );

                    req[3] = Math.max(
                        0,
                        need[3] - prefix[3] - factor[digit][3]
                    );

                    int remainingLength = n - i - 1;

                    if (dp[index(
                            req[0],
                            req[1],
                            req[2],
                            req[3]
                        )] <= remainingLength) {

                        String suffixAnswer =
                            buildSmallest(
                                req,
                                remainingLength
                            );

                        return num.substring(0, i)
                                + digit
                                + suffixAnswer;
                    }
                }
            }

            if (current != 0) {

                suffix[0] += factor[current][0];
                suffix[1] += factor[current][1];
                suffix[2] += factor[current][2];
                suffix[3] += factor[current][3];
            }
        }

        // 6. Longer answer

        int minDigits = dp[index(
            need[0],
            need[1],
            need[2],
            need[3]
        )];

        if (minDigits == 1_000_000) {
            return "-1";
        }

        int length = Math.max(n + 1, minDigits);

        int[] req = need.clone();

        return buildSmallest(req, length);
    }
}