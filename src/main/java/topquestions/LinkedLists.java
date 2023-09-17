package topquestions;

import common.ListNode;

import java.util.ArrayDeque;

public class LinkedLists {

    public static void main(String[] args) {
        new LinkedLists().run();
    }

    private void run() {
        final var l1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        final var l2 = new ListNode(1, new ListNode(2, new ListNode(3)));
        System.out.println(addTwoNumbers(l1, l2));
    }

    /**
     * Add Two Numbers
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        final var head = new ListNode(0);
        var current = head;
        var h1 = l1;
        var h2 = l2;
        while (h1 != null && h2 != null) {
            current.next = add(h1, h2, current.next);
            h1 = h1.next;
            h2 = h2.next;
            current = current.next;
        }
        if (h1 != null) {
            if (current.next == null) {
                current.next = h1;
            } else {
                current.next = addTwoNumbers(current.next, h1);
            }
        }
        if (h2 != null) {
            if (current.next == null) {
                current.next = h2;
            } else {
                current.next = addTwoNumbers(current.next, h2);
            }
        }
        return head.next;
    }

    private ListNode add(ListNode l1, ListNode l2, ListNode carry) {
        final var sum = carry == null ? l1.val + l2.val : l1.val + l2.val + carry.val;
        if (sum > 9) {
            return new ListNode(sum % 10, new ListNode(1));
        }
        return new ListNode(sum);
    }

    /**
     * Remove Nth Node From End of List
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        final var stack = new ArrayDeque<ListNode>();
        var current = head;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        var count = 0;
        while (count < n) {
            current = stack.pop();
            count++;
        }
        if (stack.isEmpty()) {
            head = current.next;
        } else {
            final var previous = stack.pop();
            previous.next = current.next;
        }
        return head;
    }

}
