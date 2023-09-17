package interview.uber.heapqueuestack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapsQueuesStacks {

    private static final Comparator<int[]> COMPARING_START = Comparator.comparingInt(i -> i[0]);
    private static final Comparator<int[]> COMPARING_END = Comparator.comparingInt(i -> i[1]);

    public static void main(String[] args) {
        new HeapsQueuesStacks().run();
    }

    private void run() {
        final var intervals = new int[][]{
            {9, 16}, {6, 16}, {3, 15}, {1, 9}
        };
        System.out.println(minMeetingRooms(intervals));
    }

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, COMPARING_START);
        final var heap = new PriorityQueue<>(COMPARING_END);
        var result = 1;
        heap.offer(intervals[0]);
        for (var i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= heap.peek()[1]) {
                heap.poll();
            } else {
                result++;
            }
            heap.offer(intervals[i]);
        }
        return result;
    }

}
