package org.algorithm_datastructure;

import java.util.HashMap;

// An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
// typically using all the original letters exactly once.
public class anagram {
    public static void main(String[] args) {
        String a = "listen";
        String b = "silent";
        System.out.println(isAnagram1(a, b)); // true
        System.out.println(isAnagram2(a, b)); // true

        String c = "rat";
        String d = "car";
        System.out.println(isAnagram1(c, d)); // false
        System.out.println(isAnagram2(c, d)); // false

        String e = "hello";
        String f = "world";
        System.out.println(isAnagram1(e, f));  // false
        System.out.println(isAnagram2(e, f));  // false

    }


    // solution 1
    // time complexity is: O(n)
    // space complexity is: O(1)
    private static boolean isAnagram1(String a, String b) {
        int result = 0;

        for (char ch : a.toCharArray()) {
            if (b.indexOf(ch) != -1) {
                result++;
            }
        }

        return result == a.length() && a.length() == b.length();
    }

    // solution 2
    // time complexity is: O(n)
    // space complexity is: O(1) However, in most cases, the number of unique characters is much smaller than 26,
    // so the space complexity is closer to , where  is the number of unique characters in the strings.
    private static boolean isAnagram2(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        HashMap<Character, Integer> kv = new HashMap<>(a.length());

        for (int i = 0; i < a.length(); i++) {
            kv.put(a.charAt(i), kv.getOrDefault(a.charAt(i), 0) + 1);

            kv.put(b.charAt(i), kv.getOrDefault(b.charAt(i), 0) - 1);
        }

        for (char k : kv.keySet()) {
            if (kv.get(k) != 0) {
                return false;
            }
        }

        return true;
    }
}