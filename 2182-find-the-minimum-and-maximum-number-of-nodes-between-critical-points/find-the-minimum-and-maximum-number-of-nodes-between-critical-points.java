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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int firstCritical = -1;
        int prevCritical = -1;
        int minDistance = Integer.MAX_VALUE;
        ListNode prev = head;
        ListNode curr = head.next;
        int position = 1;

        while(curr != null && curr.next != null){
            ListNode next = curr.next;
            boolean isLocalMax = curr.val > prev.val && curr.val > next.val;
            boolean isLocalMin = curr.val < prev.val && curr.val < next.val;

            if(isLocalMax || isLocalMin){
                if(firstCritical == -1){
                    firstCritical = position;
                } 
                else{
                    minDistance = Math.min(minDistance, position - prevCritical);
                }

                prevCritical = position;
            }
            
            prev = curr;
            curr = curr.next;
            position++;
        }

        if(firstCritical == -1 || firstCritical == prevCritical){
            return new int[]{-1, -1};
        }

        int maxDistance = prevCritical - firstCritical;

        return new int[]{minDistance, maxDistance};
    }
}