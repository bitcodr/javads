package org.algorithm_datastructure;

public class reverse_string {

    public static void main(String[] args) {
        char[] text = {'h', 'e', 'l', 'l', 'o'};

        int left = 0, right = text.length - 1;

        while (left < right) {
            char temp = text[left];
            text[left] = text[right];
            text[right] = temp;

            right--;
            left++;
        }

        System.out.println(text);
    }
}
