package algorithms.leetcode;

/**
 * 141. Linked List Cycle
 *
 * @author MelloChan
 * @date 2018/3/19
 */
public class LinkedListCycle {
    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode n1 = head, n2 = head.next;
        while (n1 != null && n2 != null) {
            if (n1 == n2) {
                return true;
            }
            n1 = n1.next;
            if(n2.next==null){
                break;
            }
            n2 = n2.next.next;
        }
        return false;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
