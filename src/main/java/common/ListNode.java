package common;

public class ListNode {

    public int val;
    public ListNode next;

    public static void main(String[] args) {
        final var l = new ListNode(5, new ListNode(6, new ListNode(7)));
        System.out.println(l);
        System.out.println(new ListNode());
    }

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        final var acc = new StringBuilder(Integer.toString(val));
        var next = this.next;
        while (next != null) {
            acc.append(" -> ");
            acc.append(next.val);
            next = next.next;
        }
        return acc.toString();
    }
}
