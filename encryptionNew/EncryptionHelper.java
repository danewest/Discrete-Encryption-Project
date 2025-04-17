package encryptionNew;

import java.math.BigInteger;

public class EncryptionHelper {
    // establishing necessary variables, *these will not change!*

    // Checks for the first occurrence of target char in string. If it doesn't appear the result is -1/false
    public static int isCharInString(char target, String sourceString) {
        return sourceString.indexOf(target);
    }
    // Translates given strings into decimal format
    public static String textToDecimal(String inText) {
        String text = inText.toLowerCase();
        String alphabet = "abcdefghijklmnopqrstuvwxyz ";
        String resString = "";
        
        // Checks if the character is in the alphabet. If it is
        for (int i = 0; i < text.length(); i++) {
            int curr = isCharInString(text.charAt(i), alphabet);
            if (curr != -1) {
                resString += String.format("%02d", curr);
            } else {
                break;
            }
        }

        System.out.println(inText + " -> " + resString);
        // Converts the converted text into an integer and returns it
        return resString;
    }

    // Translates decrypted numbers back into english
    public static String decimalToText(String num) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz ";
        String resString = "";

        // Looks for the index that each character is associated with
        for (int i = 0; i < num.length(); i++) {
            char index = isCharInString();
            resString += alphabet.charAt(index);
        }

        System.out.println(num + "-> " + resString);
        return resString;
    }

    public static String encrypt(String inText, BigInteger exponent, BigInteger mult) {
        String res = "";

        // If the length of the text is not divisible by 3 add spaces until it does
        if (inText.length() % 3 != 0) {
            for (int i = 0; i < inText.length() % 3; i++) {
                inText += " ";
            }
        }

        // Convert the text with spaces into numbers
        String num = textToDecimal(inText);

        // Encrypt the numbered text in 3 part chunks, then put those chunks together in the result
        for (int i = 0; i < num.length(); i+=3) {
            String sub = num.substring(i, i + 3);

            int textNum = Integer.parseInt(sub);

            BigInteger text = BigInteger.valueOf(textNum);
            BigInteger cipher = text.modPow(exponent, mult);

            res += cipher.toString();
        }
        System.out.println(res);
        res = decimalToText(res);
        System.out.println(inText + " -> " + res);
        return res;
    }

    public static String decrypt(String cipher, BigInteger exponent, BigInteger mult) {
        cipher = textToDecimal(cipher);
        int textNum = Integer.parseInt(cipher);

        BigInteger text = BigInteger.valueOf(textNum);
        BigInteger decryptedNum = text.modPow(exponent, mult);

        String decrypted = decimalToText(decryptedNum.toString());

        System.out.println(cipher + " -> " + decrypted);
        return decrypted.toString();
    }
}
