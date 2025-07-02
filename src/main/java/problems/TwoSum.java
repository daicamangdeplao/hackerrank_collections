package problems;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Ref: https://leetcode.com/problems/two-sum/description/?envType=problem-list-v2&envId=hash-table
 * */
public class TwoSum {

    public static void run() {
        int[] ints = twoSum(new int[]{3, 3}, 6);
        System.out.println(Arrays.toString(ints));
    }

    private static int[] twoSum(int[] nums, int target) {
        int[] tmp = new int[nums.length];
        Map<Integer, Integer> map = createMap(nums);

        System.arraycopy(nums, 0, tmp, 0, nums.length);
        Arrays.sort(tmp);
        for (int i = 0; i < tmp.length; i++) {
            int j = Arrays.binarySearch(tmp, target - tmp[i]);
            if (j >= 0) {
                System.out.println();
                int finalI = i;
                List<Integer> indices = map.entrySet().stream()
                        .filter(entry -> entry.getValue() == tmp[finalI] || entry.getValue() == tmp[j])
                        .map(Map.Entry::getKey)
                        .toList();
                return new int[] { indices.get(0), indices.get(1) };
            }
        }
        return null;
    }

    private static Map<Integer, Integer> createMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        IntStream.range(0, nums.length).forEach(i -> {
            if (map.containsKey(i)) {
                map.put(i + 1, null);
            } else {
                map.put(i, nums[i]);
            }
        });

        return map;
    }
}
