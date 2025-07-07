package problems;

import java.util.*;

/**
 * Ref: https://leetcode.com/problems/group-anagrams/description/?envType=problem-list-v2&envId=hash-table
 * */
public class GroupAnagrams {

    public static class Anagram {
        String word;
        public Anagram(String word) {
            this.word = word;
            standardize();
        }
        void standardize() {
            this.word = word.toLowerCase();
            this.word = this.word.trim();
            char[] charArray = this.word.toCharArray();
            Arrays.sort(charArray);
            this.word = new String(charArray);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Anagram anagram)) return false;
            return Objects.equals(word, anagram.word);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(word);
        }
    }

    public static void run() {
        String[] strs = new String[] { "eat", "tea", "tan", "ate", "nat", "bat" };
        List<List<String>> collect = new GroupAnagrams().groupAnagrams(strs);
        System.out.println(collect);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Anagram, List<String>> map = new HashMap<>();
        for (String str : strs) {
            if (map.containsKey(new Anagram(str))) {
                map.computeIfPresent(new Anagram(str), (k, v) -> {
                    v.add(str);
                    return v;
                });
            } else {
                map.computeIfAbsent(new Anagram(str), k -> {
                    List<String> list = new ArrayList<>();
                    list.add(str);
                    return list;
                });
            }
        }
        return map.values().stream().toList();
    }

}
