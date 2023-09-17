package interview.uber.arraysandstrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class ArraysAndStrings {

    public static final Pattern IPv4 = Pattern.compile(
        "(1\\d\\d|2[0-4][0-9]|25[0-5]|[1-9]\\d|\\d)\\." +
            "(1\\d\\d|2[0-4][0-9]|25[0-5]|[1-9]\\d|\\d)\\." +
            "(1\\d\\d|2[0-4][0-9]|25[0-5]|[1-9]\\d|\\d)\\." +
            "(1\\d\\d|2[0-4][0-9]|25[0-5]|[1-9]\\d|\\d)");

    public static final Pattern IPv6 = Pattern.compile(
        "[0-9a-fA-F]{1,4}:[0-9a-fA-F]{1,4}:[0-9a-fA-F]{1,4}:[0-9a-fA-F]{1,4}:" +
            "[0-9a-fA-F]{1,4}:[0-9a-fA-F]{1,4}:[0-9a-fA-F]{1,4}:[0-9a-fA-F]{1,4}");

    public static void main(String[] args) {
        final var app = new ArraysAndStrings();
    }

    /**
     * Two Sum
     */
    public int[] twoSum(int[] nums, int target) {
        final var acc = new HashMap<Integer, Integer>();
        var result = new int[] {-1, -1};
        for (var i = 0; i < nums.length; i++) {
            if (acc.containsKey(nums[i])) {
                result = new int[] {acc.get(nums[i]), i};
                break;
            } else {
                acc.put(target - nums[i], i);
            }
        }
        return result;
    }

    /**
     * Group Anagrams
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        return new ArrayList<>(
            Arrays.stream(strs)
                .collect(groupingBy(s -> Arrays.hashCode(occurrences(s))))
                .values());
    }

    private int[] occurrences(String s) {
        final var result = new int[26];
        for (final var ch : s.toCharArray()) {
            result[ch - 'a']++;
        }
        return result;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        else {
            final var occurrences = new int[26];
            for (final var ch : s.toCharArray()) {
                occurrences[ch - 'a']++;
            }
            var mismatch = false;
            for (final var ch : t.toCharArray()) {
                if (--occurrences[ch - 'a'] < 0) {
                    mismatch = true;
                    break;
                }
            }
            return !mismatch;
        }
    }

    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        else {
            final var occurrences = s.chars().boxed().collect(toMap(i -> i, i -> 1, Integer::sum));
            var mismatchFound = false;
            for (final var ch : t.toCharArray()) {
                if (occurrences.containsKey((int) ch)) {
                    occurrences.compute((int) ch, (key, old) -> (old == null || old == 1) ? null : old - 1);
                } else {
                    mismatchFound = true;
                }
            }
            return !mismatchFound && occurrences.isEmpty();
        }
    }

    /**
     * Validate IP Address
     */
    public String validIPAddress(String queryIp) {
        if (isIPv4(queryIp)) {
            return "IPv4";
        } else if (isIPv6(queryIp)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }

    private static boolean isIPv4(String queryIp) {
        final var matcher = IPv4.matcher(queryIp);
        return matcher.matches();
    }

    private static boolean isIPv6(String queryIp) {
        final var matcher = IPv6.matcher(queryIp);
        return matcher.matches();
    }

}
