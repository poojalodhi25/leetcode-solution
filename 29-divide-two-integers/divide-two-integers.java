class Solution {
    public int divide(int dividend, int divisor) {

        // Special overflow case
        if (dividend == Integer.MIN_VALUE &&
            divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Check whether answer should be negative
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Convert to long to safely handle -2^31
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        long quotient = 0;

        while (a >= b) {

            long value = b;
            long multiple = 1;

            while (a >= (value << 1)) {
                value = value << 1;
                multiple = multiple << 1;
            }

            a = a - value;
            quotient = quotient + multiple;
        }

        if (negative) {
            quotient = -quotient;
        }

        return (int) quotient;
    }
}