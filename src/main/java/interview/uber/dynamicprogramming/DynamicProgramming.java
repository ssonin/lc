package interview.uber.dynamicprogramming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class DynamicProgramming {

    public static void main(String[] args) {
        new DynamicProgramming().run();
    }

    private void run() {
        System.out.println(numSquares(13));
        System.out.println(numSquares(12));
    }

    /**
     * Word Break
     */
    public boolean wordBreak(String s, List<String> words) {
        return dp(s, words, new HashMap<>());
    }

    private boolean dp(String s, List<String> words, Map<String, Boolean> acc) {
        if (s.isEmpty()) return true;
        else {
            if (acc.containsKey(s)) {
                return acc.get(s);
            }
            final var result = words.stream()
                .filter(s::startsWith)
                .anyMatch(word -> dp(s.substring(word.length()), words, acc));
            acc.put(s, result);
            return result;
        }
    }

    /**
     * Perfect Squares
     */
    public int numSquares(int n) {
        if (n < 4) {
            return n;
        }
        final var dp = IntStream.iterate(0, i -> i + 1)
            .limit(n + 1)
            .toArray();
        final var ceil = (int) Math.sqrt(n) + 1;
        final var squares = IntStream.iterate(0, i -> i + 1)
            .limit(ceil)
            .map(i -> i * i)
            .toArray();
        for (var i = 1; i <= n; i++) {
            for (var s = 1; s < ceil && i >= squares[s]; s++) {
                dp[i] = Math.min(dp[i], dp[i - squares[s]] + 1);
            }
        }
        return dp[n];
    }
}
