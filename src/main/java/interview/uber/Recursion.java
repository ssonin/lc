package interview.uber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Recursion {

    public static void main(String[] args) {
        new Recursion().run();
    }

    private void run() {
        System.out.println(subsets(new int[]{1, 2, 3}));
        System.out.println(letterCombinations("23"));
    }

    /**
     * Subsets
     */
    public List<List<Integer>> subsets(int[] nums) {
        return walk(nums, nums.length - 1);
    }

    private List<List<Integer>> walk(int[] nums, int start) {
        if (start == -1) {
            final var result = new ArrayList<List<Integer>>();
            result.add(new ArrayList<>());
            return result;
        } else {
            final var previous = walk(nums, start - 1);
            final var result = clone(previous);
            previous.forEach(subset -> subset.add(nums[start]));
            result.addAll(previous);
            return result;
        }
    }

    private List<List<Integer>> clone(List<List<Integer>> origin) {
        final var result = new ArrayList<List<Integer>>(origin.size());
        for (final var list : origin) {
            final var copy = new ArrayList<>(list);
            result.add(copy);
        }
        return result;
    }

    private static Map<Character, List<String>> dial = Map.of(
        '2', List.of("a", "b", "c"),
        '3', List.of("d", "e", "f"),
        '4', List.of("g", "h", "i"),
        '5', List.of("j", "k", "l"),
        '6', List.of("m", "n", "o"),
        '7', List.of("p", "q", "r", "s"),
        '8', List.of("t", "u", "v"),
        '9', List.of("w", "x", "y", "z")
    );

    /**
     * Letter Combinations of a Phone Number
     */
    public List<String> letterCombinations(String digits) {
        return recur(digits, digits.length() - 1);
    }

    private List<String> recur(String digits, int start) {
        switch (start) {
            case -1:
                return Collections.emptyList();
            case 0:
                return dial.get(digits.charAt(0));
            default:
                final var previous = recur(digits, start - 1);
                final var current = dial.get(digits.charAt(start));
                final var result = new ArrayList<String>(previous.size() * current.size());
                for (final var letter : current) {
                    for (final var combination : previous) {
                        result.add(combination + letter);
                    }
                }
                return result;
        }
    }

}
