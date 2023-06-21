package org.algorithm_datastructure;

public class replace_vowels {

    public static void main(String[] args) {
        System.out.println(replaceVowels("hello")); // holle

        System.out.println(replaceVowels("DesignGUrus")); // DusUgnGires

        System.out.println(replaceVowels("AEIOU")); // UOIEA

        System.out.println(replaceVowels("aA")); // Aa

        System.out.println(replaceVowels("")); // ""
    }

    private static String replaceVowels(String s) {
        int left = 0, right = s.length() - 1;
        char[] text = s.toCharArray();
        String vowels = "aeiouAEIOU";

        while (left < right) {
            while (left < right && vowels.indexOf(text[left]) == -1) {
                left++;
            }

            while (right > left && vowels.indexOf(text[right]) == -1) {
                right--;
            }

            char temp = text[left];
            text[left] = text[right];
            text[right] = temp;

            left++;
            right--;
        }

        return new String(text);
    }
}
