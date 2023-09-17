package interview.uber.other;

import java.util.HashMap;
import java.util.Map;

class Logger {

    private static final int THRESHOLD = 10;

    private final Map<String, Integer> cache = new HashMap<>();

    public Logger() {}

    public static void main(String[] args) {
        final var logger = new Logger();
        System.out.println(logger.shouldPrintMessage(100, "bug"));
        System.out.println(logger.shouldPrintMessage(111, "bug"));
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (cache.containsKey(message) && timestamp - cache.get(message) < THRESHOLD) {
            return false;
        } else {
            cache.put(message, timestamp);
            return true;
        }
    }
}
