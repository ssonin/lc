package interview.uber;

import common.ListNode;

public class LinkedLists {

    public static void main(String[] args) {
        System.out.println(Integer.parseInt("01"));
    }

    /**
     * Merge Two Sorted Lists
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        final var head = new ListNode();
        var h1 = list1;
        var h2 = list2;
        var curr = head;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                curr.next = h1;
                h1 = h1.next;
            } else {
                curr.next = h2;
                h2 = h2.next;
            }
            curr = curr.next;
        }
        if (h1 != null) {
            curr.next = h1;
        } else if (h2 != null) {
            curr.next = h2;
        }
        return head.next;
    }

    /**
     * Merge k Sorted Lists
     */
    public ListNode mergeKLists(ListNode[] lists) {
        final var headIdx = min(lists);
        ListNode head;
        if (headIdx < 0) {
            return null;
        } else {
            head = lists[headIdx];
            lists[headIdx] = lists[headIdx].next;
        }
        var curr = head;
        var nextIdx = min(lists);
        while (nextIdx > -1) {
            curr.next = lists[nextIdx];
            lists[nextIdx] = lists[nextIdx].next;
            curr = curr.next;
            nextIdx = min(lists);
        }

        return head;
    }

    private int min(ListNode[] lists) {
        if (lists.length == 0) return -1;
        var min = Integer.MAX_VALUE;
        var result = -1;
        for (var i = 0; i < lists.length; i++) {
            if (lists[i] == null) {
                continue;
            } else if (lists[i].val < min) {
                min = lists[i].val;
                result = i;
            }
        }
        return result;
    }

}
