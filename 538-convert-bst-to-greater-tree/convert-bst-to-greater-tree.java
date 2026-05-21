class Solution {

    int sum = 0;

    public TreeNode convertBST(TreeNode root) {

        reverseInorder(root);

        return root;
    }

    public void reverseInorder(TreeNode node) {

        if(node == null) {
            return;
        }

        // Visit right subtree first
        reverseInorder(node.right);

        // Update sum
        sum += node.val;

        // Update node value
        node.val = sum;

        // Visit left subtree
        reverseInorder(node.left);
    }
}