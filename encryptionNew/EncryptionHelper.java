package encryptionNew;

import java.math.BigInteger;

public class EncryptionHelper {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz ";
    private static final int BASE = ALPHABET.length(); // 27

    // Checks for the first occurrence of target char in string. If it doesn't appear the result is -1/false
    private static int isCharInString(char target, String sourceString) {
        return sourceString.indexOf(target);
    }

    // Translates given strings into decimal format
    private static String textToDecimal(String inText) {
        String text = inText.toLowerCase();
        String resString = "";
        
        // Checks if the character is in the alphabet. If it is
        for (int i = 0; i < text.length(); i++) {
            int curr = isCharInString(text.charAt(i), ALPHABET);
            if (curr != -1) {
                //resString += curr;
                resString += String.format("%02d", curr);
            } else {
                break;
            }
        }
        System.out.println(inText + " -> " + resString);
        // Converts the converted text into an integer and returns it
        return resString;
    }

    // Translates decrypted numbers back into english (for Strings)
    private static String decimalToText(String num) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < num.length(); i += 2) {
            String chunk = num.substring(i, i + 2);
            int index = Integer.parseInt(chunk);
            res.append(ALPHABET.charAt(index));
        }

        System.out.println(num + " -> " + res.toString());
        return res.toString();
    }

    // Converts encrypted numbers into gibberish text
    private static String numberToFakeText(BigInteger num) {
        StringBuilder res = new StringBuilder();
        BigInteger base = BigInteger.valueOf(BASE);

        int temp = num.intValue();

        if (num.equals(BigInteger.ZERO)) {
            return String.valueOf(ALPHABET.charAt(0));
        }

        while (num.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divRem = num.divideAndRemainder(base);
            res.insert(0, ALPHABET.charAt(divRem[1].intValue()));
            num = divRem[0];
        }

        return res.toString();
    }

    // Converts gibberish text back into a number to be decrypted
    private static BigInteger fakeTextToNumber(String text) {
        BigInteger result = BigInteger.ZERO;
        BigInteger base = BigInteger.valueOf(BASE);

        for (int i = 0; i < text.length(); i++) {
            int index = isCharInString(text.charAt(i), ALPHABET);
            if (index == -1) {
                throw new IllegalArgumentException("Invalid character in encrypted text: " + text.charAt(i));
            }
            result = result.multiply(base).add(BigInteger.valueOf(index));
        }

        //System.out.println(text + " to " + result);
        return result;
    }

    // Encrypts given string
    public static String[] encrypt(String inText, BigInteger exponent, BigInteger mult) {
        // If the length of the text is not divisible by 3 add spaces until it does
        if (inText.length() % 2 != 0) {
            inText += " ";
        }

        // String array for the chunks & counter for the loop
        String[] res = new String[inText.length() / 2];
        int resCount = 0;

        for (int i = 0; i < inText.length(); i += 2) {
            String chunk = inText.substring(i, i + 2);
            String decimal = textToDecimal(chunk);

            BigInteger plainNum = new BigInteger(decimal);
            BigInteger encrypted = plainNum.modPow(exponent, mult);
            res[resCount++] = numberToFakeText(encrypted);
        }

        arrayPrint(res);
        return res;
    }

    // Decrypts the number given by fakeTextToNumber
    public static String decrypt(String[] cipher, BigInteger exponent, BigInteger mult) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < cipher.length; i++) {
            BigInteger encodedNum = fakeTextToNumber(cipher[i]);
            BigInteger decryptedNum = encodedNum.modPow(exponent, mult);
            String numStr = decryptedNum.toString();

            // Each chunk was 2 letters → 2 digits each = 4-digit total
            while (numStr.length() < 4) numStr = "0" + numStr;

            res.append(decimalToText(numStr));
        }

        System.out.println("Decrypted text: " + res.toString());
        return res.toString();
    }

    // Prints chunked messages. For testing
    private static void arrayPrint(String[] message) {
        System.out.print("\nMessage: ");
        for (int i = 0; i < message.length; i++) {
            System.out.print(message[i]);
        }
        System.out.println("\n");
    }
}
