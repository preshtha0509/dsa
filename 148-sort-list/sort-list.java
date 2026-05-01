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
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null) return head;

        // Step 1: Store values in list
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }

        // Step 2: Sort the list
        Collections.sort(list);

        // Step 3: Put values back into linked list
        temp = head;
        int i = 0;
        
        while (temp != null) {
            temp.val = list.get(i++);
            temp = temp.next;
        }

        return head;
    }
}