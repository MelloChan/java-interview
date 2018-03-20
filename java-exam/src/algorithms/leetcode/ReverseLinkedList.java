package algorithms.leetcode;

/**
 * Leetcode : 206. Reverse Linked List (Easy)
 * 反转链表
 *
 * @author MelloChan
 * @date 2018/3/19
 */
public class ReverseLinkedList {
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = null;
        while (head!=null){
            ListNode next=head.next;
            head.next=newHead;
            newHead=head;
            head=next;
        }
        return newHead;
    }
}
