class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode left = head;
        ListNode mid = head.next;
        ListNode right = head.next.next;
        int idx = 1, first = -1, last = -1;
        int minDistance = Integer.MAX_VALUE;
        while (right != null) {
            if(left.val<mid.val && right.val<mid.val || left.val>mid.val && right.val>mid.val) {
                if (first == -1) {
                    first = idx;
                }
                if (last != -1) {
                    int dist = idx - last;
                    minDistance = Math.min(minDistance, dist);
                }
                last = idx;
            }
            left = left.next;
            mid = mid.next;
            right = right.next;
            idx++;
        }        
        int[] arr = {-1, -1};
        if (first == last) return arr;
        arr[0] = minDistance;
        arr[1] = last - first;
        return arr;
    }
}