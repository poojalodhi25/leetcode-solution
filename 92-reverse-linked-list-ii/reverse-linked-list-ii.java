class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {

        // Agar reverse karne ki zarurat nahi hai
        if (left == right) {
            return head;
        }

        // Dummy node
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // left se ek node pehle tak jao
        ListNode prev = dummy;

        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // Reverse start hone wala first node
        ListNode curr = prev.next;

        // Reverse process
        for (int i = 0; i < right - left; i++) {

            ListNode next = curr.next;

            curr.next = next.next;

            next.next = prev.next;

            prev.next = next;
        }

        return dummy.next;
    }
}