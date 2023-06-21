package org.algorithm_datastructure;

// Given two strings containing backspaces (identified by the character ‘#’), check if the two strings are equal.
public class compare_string_contains_char {
    public static void main(String[] args) {
        System.out.println(compare("xy#z", "xzz#")); // true
        System.out.println(compare("xy#z", "xyz#")); // false
        System.out.println(compare("xp#", "xyz##")); // true
        System.out.println(compare("xywrrmp", "xywrrmu#p")); // true
    }

    // Time: O(M + N)
    // Space: O(1)
    private static boolean compare(String str1, String str2) {
        char space = '#';

        int str1First = 0;
        int str1Last = str1.length();

        while (str1.indexOf(space) != -1) {

            String first = str1.substring(str1First, str1.indexOf(space) - 1);
            String last = str1.substring(str1.indexOf(space) + 1, str1Last);
            
            str1 = first + last;
            
            str1Last = str1.length();
        }


        int str2First = 0;
        int str2Last = str2.length();

        while (str2.indexOf(space) != -1) {
            String first = str2.substring(str2First, str2.indexOf(space) - 1);
            String last = str2.substring(str2.indexOf(space) + 1, str2Last);

            str2 = first + last;

            str2Last = str2.length();
        }

        return str1.equalsIgnoreCase(str2);
    }
}
