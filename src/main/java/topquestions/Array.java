package topquestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Array {

    public static void main(String[] args) {
        new Array().run();
    }

    private void run() {
//        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(permute(new int[]{1, 2, 3}));
    }

    /**
     * 3Sum
     */
    public List<List<Integer>> threeSum(int[] nums) {
        final var result = new HashSet<List<Integer>>();
        for (var i = 0; i < nums.length; i++) {
            final var twos = twoSum(nums, i);
            for (final var list : twos) {
                list.add(nums[i]);
                Collections.sort(list);
                result.add(list);
            }
        }
        return new ArrayList<>(result);
    }

    private List<List<Integer>> twoSum(int[] nums, int targetIdx) {
        final var target = -nums[targetIdx];
        final var dict = new HashMap<Integer, List<Integer>>();
        final var result = new ArrayList<List<Integer>>();
        for (var i = targetIdx + 1; i < nums.length; i++) {
            if (dict.containsKey(nums[i])) {
                for (final var j : dict.get(nums[i])) {
                    final var combination = new ArrayList<Integer>(2);
                    combination.add(nums[j]);
                    combination.add(nums[i]);
                    result.add(combination);
                }
                final var entry = new ArrayList<Integer>();
                entry.add(i);
                dict.merge(target - nums[i], entry, (oldVal, newVal) -> {
                    oldVal.addAll(newVal);
                    return oldVal;
                });
            } else {
                final var entry = new ArrayList<Integer>();
                entry.add(i);
                dict.put(target - nums[i], entry);
            }
        }
        return result;
    }

    /**
     * Permutations
     */
    public List<List<Integer>> permute(int[] nums) {
        return null;
    }

    public List<List<Integer>> permutations(List<Integer> nums) {
        if (nums.size() == 1) {
            final var base = new ArrayList<Integer>();
            base.add(nums.get(0));
            final var result = new ArrayList<List<Integer>>();
            result.add(base);
            return result;
        } else if (nums.size() == 2) {
            final var base0 = permutations(Arrays.asList(nums.get(0)));

        }
        return null;
    }

}
