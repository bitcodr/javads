package org.algorithm_datastructure;


import java.util.HashMap;

// Determine if an input string containing only the characters '(', ')', '{', '}', '[', and ']' is valid.
// A string is considered valid if:
// 1. Open brackets must be closed by the same type of brackets.
// 2. Open brackets must be closed in the correct order.
// 3. Each close bracket has a corresponding open bracket of the same type.
public class valid_parentheses {

    public static void main(String[] args) {
        HashMap<Character, Character> validPairs = new HashMap<>();
        validPairs.put('(', ')');
        validPairs.put('[', ']');
        validPairs.put('{', '}');

        String s = "(hi how are you doing]";
        System.out.println(isValid(validPairs, s));

        String ss = "(hi how are you doing)";
        System.out.println(isValid(validPairs, ss));

        String sss = "a{hi how are you doing)";
        System.out.println(isValid(validPairs, sss));
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // where n is the length of the input string.
    private static boolean isValid(HashMap<Character, Character> validChars,String s) {
        char validCloseChar = 0;

        for (char i : s.toCharArray()) {
            if (i == validCloseChar) {
                return true;
            }

            if (validChars.containsKey(i)) {
                validCloseChar = validChars.get(i);
            }
        }

        return false;
    }
}

