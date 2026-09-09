class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // nums1 ko chhota array rakhenge
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;

        int left = 0;
        int right = m;

        int total = m + n;
        int half = (total + 1) / 2;

        while (left <= right) {

            int i = (left + right) / 2;

            int j = half - i;

            int left1;
            int right1;
            int left2;
            int right2;

            if (i == 0) {
                left1 = Integer.MIN_VALUE;
            } else {
                left1 = nums1[i - 1];
            }

            if (i == m) {
                right1 = Integer.MAX_VALUE;
            } else {
                right1 = nums1[i];
            }

            if (j == 0) {
                left2 = Integer.MIN_VALUE;
            } else {
                left2 = nums2[j - 1];
            }

            if (j == n) {
                right2 = Integer.MAX_VALUE;
            } else {
                right2 = nums2[j];
            }

            // Correct partition mil gaya
            if (left1 <= right2 && left2 <= right1) {

                // Total elements odd hain
                if (total % 2 != 0) {

                    return Math.max(left1, left2);

                } else {

                    int maxLeft = Math.max(left1, left2);

                    int minRight = Math.min(right1, right2);

                    return (maxLeft + minRight) / 2.0;
                }
            }

            // nums1 se bahut kam elements left side me liye
            else if (left1 > right2) {

                right = i - 1;

            }

            // nums1 se bahut zyada elements left side me liye
            else {

                left = i + 1;
            }
        }

        return 0.0;
    }
}