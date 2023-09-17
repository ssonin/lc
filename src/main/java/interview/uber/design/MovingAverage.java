package interview.uber.design;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Moving Average from Data Stream
 */
public class MovingAverage {

    private final int capacity;
    private final Queue<Integer> queue;
    private double sma;

    public static void main(String[] args) {
        new MovingAverage(3).run();
    }

    private void run() {
        System.out.println(next(1));
        System.out.println(next(10));
        System.out.println(next(3));
        System.out.println(next(5));
    }

    public MovingAverage(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayDeque<>();
        this.sma = 0;
    }

    public double next(int n) {
        if (queue.size() < capacity) {
            sma = sma + (n - sma) / (queue.size() + 1);
            queue.offer(n);
        } else {
            final var previousHead = queue.poll();
            queue.offer(n);
            sma = sma + (n - previousHead.doubleValue()) / capacity;
        }
        return sma;
    }
}
