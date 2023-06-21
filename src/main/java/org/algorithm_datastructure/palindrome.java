package org.algorithm_datastructure;

// the word or sentence converted to lowercase and it reads the same backward and forwards,
// without any non-alphanumeric characters
public class palindrome {

    public static void main(String[] args) {
        String sentence = "A man, a plan, a canal, Panama!";
        System.out.println(isPlindrome(sentence)); // true

        String sentence1 = "Was it a car or a cat I saw?";
        System.out.println(isPlindrome(sentence1)); // true

        String sentence2 = "Waz it a car 5 saw?";
        System.out.println(isPlindrome(sentence2));  // false
    }

    private static boolean isPlindrome(String text) {
        int left = 0, right = text.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(text.charAt(left))){
                left++;
            }
            
            while (left < right && !Character.isLetterOrDigit(text.charAt(right))) {
                right--;
            }
            
            if(Character.toLowerCase(text.charAt(right)) != Character.toLowerCase(text.charAt(left))) {
                return false;
            }
            
            left++;
            right--;
        }

        return true;
    }

}