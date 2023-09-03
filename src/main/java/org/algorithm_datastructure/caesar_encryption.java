package org.algorithm_datastructure;

public class caesar_encryption {

    public static void main(String[] args) {
        String s = "!m-rB`-oN!.W`cLAcVbN/CqSoolII!SImji.!w/`Xu`uZa1TWPRq`uRBtok`xPT`lL-zPTc.BSRIhu..-!.!tcl!-U";
        System.out.println(caesarCipher(s, 62)); // !w-bL`-yX!.G`mVKmFlX/MaCyyvSS!CSwts.!g/`He`eJk1DGZBa`eBLdyu`hZD`vV-jZDm.LCBSre..-!.!dmv!-E

        String ss = "middle-Outz";
        System.out.println(caesarCipher(ss, 2)); // okffng-Qwvb

        String sss = "www.abc.xy";
        System.out.println(caesarCipher(sss, 87));  // fff.jkl.gh
    }

    private static String caesarCipher(String s, int k) {

        String alphabet = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";

        StringBuilder encrypted = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            char encryptedChar = s.charAt(i);

            int indexOfChar = alphabet.indexOf(encryptedChar);

            if(indexOfChar != -1) {
                int indexSize = indexOfChar + (k * 2);

                if (indexSize >= alphabet.length()) {
                    encrypted.append(alphabet.charAt(remainedIndex(indexSize, alphabet.length())));
                } else {
                    encrypted.append(alphabet.charAt(indexSize));
                }

            } else {
                encrypted.append(encryptedChar);
            }
        }

        return encrypted.toString();
    }

    private static int remainedIndex(int length, int a) {
        if(length < a) {
            return length;
        }

        int remainedIndex = length - a;

        return remainedIndex(remainedIndex, a);
    }
}
