/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
    private ListNode head;

    public TreeNode sortedListToBST(ListNode head) {
        this.head = head;
        int size = getSize(head);
        return buildBST(0, size - 1);
    }

    private int getSize(ListNode node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    private TreeNode buildBST(int left, int right) {
        if (left > right) return null;

        int mid = (left + right) / 2;

        // Build left subtree
        TreeNode leftChild = buildBST(left, mid - 1);

        // Root node
        TreeNode root = new TreeNode(head.val);
        root.left = leftChild;

        // Move list pointer
        head = head.next;

        // Build right subtree
        root.right = buildBST(mid + 1, right);

        return root;
    }
}
