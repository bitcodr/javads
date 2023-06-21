package org.algorithm_datastructure;

import java.util.HashSet;
import java.util.Set;

// a sentence is a pangram if it has all the letters of the English language
public class pangram {

    public static void main(String[] args) {

        String sentence = "TheQuickBrownFoxJumpsOverTheLazyDog";

        Set<Character> keys = new HashSet<>();

        for (char ch : sentence.toLowerCase().toCharArray()) {
            keys.add(ch);
        }

        System.out.println(keys.size() == 26);
    }
}
