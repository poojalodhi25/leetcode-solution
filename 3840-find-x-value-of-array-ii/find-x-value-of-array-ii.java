class Solution {

    class Node {
        int prod;
        int[] pref;

        Node(int k) {
            pref = new int[k];
        }
    }

    int[] nums;
    int k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.nums = nums;
        this.k = k;

        int n = nums.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Update
            nums[index] = value;
            update(1, 0, n - 1, index, value);

            // Query [start, n-1]
            Node res = query(1, 0, n - 1, start, n - 1);

            ans[q] = res.pref[x];
        }

        return ans;
    }

    void build(int node, int left, int right) {

        if (left == right) {

            tree[node] = new Node(k);

            int rem = nums[left] % k;

            tree[node].prod = rem;
            tree[node].pref[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2],
                           tree[node * 2 + 1]);
    }

    void update(int node, int left, int right,
                int index, int value) {

        if (left == right) {

            tree[node] = new Node(k);

            int rem = value % k;

            tree[node].prod = rem;
            tree[node].pref[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2],
                           tree[node * 2 + 1]);
    }

    Node query(int node, int left, int right,
               int ql, int qr) {

        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        if (qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }

        Node leftNode =
            query(node * 2, left, mid, ql, qr);

        Node rightNode =
            query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(leftNode, rightNode);
    }

    Node merge(Node left, Node right) {

        Node parent = new Node(k);

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            parent.pref[r] += left.pref[r];
        }

        // Prefixes that go through left and then into right
        for (int r = 0; r < k; r++) {

            int newRem = (left.prod * r) % k;

            parent.pref[newRem] += right.pref[r];
        }

        // Product of the complete segment
        parent.prod = (left.prod * right.prod) % k;

        return parent;
    }
}