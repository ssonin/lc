package topquestions;

import java.util.HashSet;

public class TwoPointers {

    public static void main(String[] args) {
        final var app = new TwoPointers();
        System.out.println(app.lengthOfLongestSubstring("what a wonder"));
        System.out.println(app.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(app.lengthOfLongestSubstring("aaa"));
        System.out.println(app.lengthOfLongestSubstring("123"));
        System.out.println(app.lengthOfLongestSubstring("1"));
        System.out.println(app.lengthOfLongestSubstring(""));
    }

    public int lengthOfLongestSubstring(String s) {
        int lo = 0;
        int hi = 0;
        int result = 0;
        final var seen = new HashSet<Character>();
        while (hi < s.length()) {
            while (seen.contains(s.charAt(hi))) {
                seen.remove(s.charAt(lo));
                lo++;
            }
            seen.add(s.charAt(hi));
            hi++;
            result = Math.max(result, hi - lo);
        }
        return result;
    }
}
