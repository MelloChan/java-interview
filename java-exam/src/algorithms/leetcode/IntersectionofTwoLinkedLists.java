package algorithms.leetcode;

/**
 * Leetcode : 160. Intersection of Two Linked Lists (Easy)
 * 判断两个链表的交点
 *
 * @author MelloChan
 * @date 2018/3/19
 */
public class IntersectionofTwoLinkedLists {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null){
            return null;
        }

        int aLength=getLinkedListLength(headA);
        int bLength=getLinkedListLength(headB);

        ListNode longNode=headA;
        ListNode shortNode=headB;
        int length=aLength-bLength;
        if(aLength<bLength){
            longNode=headB;
            shortNode=headA;
            length=bLength-aLength;
        }

        for (int i = 0; i < length; i++) {
            longNode=longNode.next;
        }

        while (longNode!=null&&shortNode!=null&&longNode.val!=shortNode.val){
            longNode=longNode.next;
            shortNode=shortNode.next;
        }
        return longNode;
    }

    public static int getLinkedListLength(ListNode node) {
        int length=0;
        ListNode pNode=node;
        while (pNode!=null){
            pNode=pNode.next;
            ++length;
        }
        return length;
    }
}
