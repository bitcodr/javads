package org.algorithm_datastructure;


// Given an array of strings words and two different strings that already exist in the array word1 and word2, return the shortest distance between these two words in the list.
public class shortest_distance {
    public static void main(String[] args) {
        String[] words = {"the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};
        String word1 = "fox";
        String word2 = "dog";

        System.out.print(findDistance(words, word1, word2));
    }

    public static int findDistance(String[] words, String word1, String word2) {
        int word1Index = 0;
        int word2Index = 0;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1) && word1Index <= i) {
                word1Index = i;
            }

            if (words[i].equals(word2) && word2Index <= i) {
                word2Index = i;
            }
        }

        if (word1Index > word2Index) {
            return word1Index - word2Index;
        }

        return word2Index - word1Index;
    }

}