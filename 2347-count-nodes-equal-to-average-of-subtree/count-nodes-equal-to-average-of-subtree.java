class Solution {

    int answer = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return answer;
    }

    private int[] dfs(TreeNode node) {

        // Base case
        if (node == null) {
            return new int[]{0, 0};
        }

        // Get left subtree sum and count
        int[] left = dfs(node.left);

        // Get right subtree sum and count
        int[] right = dfs(node.right);

        // Current subtree sum
        int sum = node.val + left[0] + right[0];

        // Current subtree node count
        int count = 1 + left[1] + right[1];

        // Average
        int average = sum / count;

        // Check condition
        if (node.val == average) {
            answer++;
        }

        return new int[]{sum, count};
    }
}