/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int postIndex;
    java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        postIndex = n - 1;
        
        // Store inorder indices
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        
        return build(inorder, postorder, 0, n - 1);
    }
    
    private TreeNode build(int[] inorder, int[] postorder, int left, int right) {
        if (left > right) return null;
        
        // Root from postorder
        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);
        
        // Find root index in inorder
        int index = map.get(rootVal);
        
        // Build RIGHT first, then LEFT
        root.right = build(inorder, postorder, index + 1, right);
        root.left = build(inorder, postorder, left, index - 1);
        
        return root;
    }
}