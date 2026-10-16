class Solution {

    static final long MOD = 1000000007L;

    class SegmentTree {
        int n;
        long[] sum;
        long[] sq;
        long[] lazy;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            sq = new long[4 * n];
            lazy = new long[4 * n];
        }

        void apply(int node, int l, int r, long val) {
            long len = r - l + 1;

            sq[node] = (sq[node] + (2 * val % MOD) * sum[node] % MOD
                    + val * val % MOD * len) % MOD;

            sum[node] = (sum[node] + val * len) % MOD;

            lazy[node] += val;
        }

        void push(int node, int l, int r) {
            if (lazy[node] == 0) return;

            int mid = (l + r) / 2;

            apply(node * 2, l, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, r, lazy[node]);

            lazy[node] = 0;
        }

        void update(int node, int l, int r, int ql, int qr) {

            if (ql > r || qr < l)
                return;

            if (ql <= l && r <= qr) {
                apply(node, l, r, 1);
                return;
            }

            push(node, l, r);

            int mid = (l + r) / 2;

            update(node * 2, l, mid, ql, qr);
            update(node * 2 + 1, mid + 1, r, ql, qr);

            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
            sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
        }

        long query() {
            return sq[1];
        }
    }

    public int sumCounts(int[] nums) {

        int n = nums.length;

        SegmentTree st = new SegmentTree(n);

        HashMap<Integer, Integer> last = new HashMap<>();

        long ans = 0;

        for (int i = 0; i < n; i++) {

            int prev = last.getOrDefault(nums[i], -1);

            st.update(1, 0, n - 1, prev + 1, i);

            ans = (ans + st.query()) % MOD;

            last.put(nums[i], i);
        }

        return (int) ans;
    }
}