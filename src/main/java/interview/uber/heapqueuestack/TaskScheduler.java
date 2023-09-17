package interview.uber.heapqueuestack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Task Scheduler
 */
public class TaskScheduler {

    public static void main(String[] args) {
        new TaskScheduler().run();
    }

    private void run() {
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
//        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 1));
//        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));
//        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'}, 2));
    }

    public int leastInterval(char[] tasks, int n) {
        final var frequences = new int[26];
        for (final var ch : tasks) {
            frequences[ch - 'A']++;
        }
        Arrays.sort(frequences);
        final var maxFrequency = frequences[frequences.length - 1];
        var idle = (maxFrequency - 1) * n;
        for (var i = frequences.length - 2; i >= 0 && idle >= 0; i--) {
            idle -= Math.min(frequences[i], maxFrequency - 1);
        }
        return tasks.length + Math.max(0, idle);
    }
}
