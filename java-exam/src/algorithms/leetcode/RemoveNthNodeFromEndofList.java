package algorithms.leetcode;

/**
 * 19. Remove Nth Node From End of List
 *
 * @author MelloChan
 * @date 2018/4/23
 */
public class RemoveNthNodeFromEndofList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode n1 = newHead, n2 = newHead;
        for (; n2 != null; n2 = n2.next, n--) {
            if (n < 0) {
                n1 = n1.next;
            }
        }
        n1.next = n1.next.next;
        return newHead.next;
    }
}
