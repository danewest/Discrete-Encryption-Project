package encryptionNew;

import static encryptionNew.EncryptionHelper.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class EncryptionApp {
    public static void main (String[] args) throws InterruptedException, ExecutionException {
        // establishing necessary variables, *these will not change!*
        final BigInteger p, q, one, n, phi, e, d;
        p = new BigInteger("61");
        q = new BigInteger("53");
        one = new BigInteger("1");

        n = p.multiply(q); //multiply
        phi = (p.subtract(one)).multiply(q.subtract(one)); // Euler's totient

        e = new BigInteger("1993"); // public
        d = e.modInverse(phi);
        //d = 291481949; // private key

        Scanner in = new Scanner(System.in);

        // Take input
        System.out.println("What is your message?");
        String input = in.nextLine();

        String[] encrypted = encrypt(input, e, n);

        String decrypted = decrypt(encrypted, d, n);
    }
}