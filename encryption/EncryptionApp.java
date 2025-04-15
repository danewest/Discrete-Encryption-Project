package encryption;

import com.mathworks.engine.*;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import encryption.EncryptionHelper;

public class EncryptionApp {
    public static void main (String[] args) throws InterruptedException, ExecutionException {
        Scanner in = new Scanner(System.in);

        // establishing necessary variables, *these will not change!*
        final int p, q, n, phi, b, a, chunkSize;
        p = 20287;
        q = 20323;
        n = p*q;
        phi = (p-1) * (q-1);
        b = 3533;
        a = 291481949;
        chunkSize = 3;

        // Take input
        System.out.println("What is your message?");
        String input = in.nextLine();

        //start MATLAB
        System.out.println("Starting MATLAB engine...");
        MatlabEngine engine = EncryptionHelper.engineStart();
        System.out.println("MATLAB engine started.");

        // encrypt message M
        System.out.println("Calling textToDecimal...");
        String M = EncryptionHelper.textToDecimal(input, chunkSize, engine);
        System.out.println("Message into decimal: " + M);
        String C = EncryptionHelper.modExp(M, b, n, engine); // message is encrypted
        System.out.println("Encrypted message: " + C);
        // System.out.println(decimalToText(C, chunkSize, engine)); // prints encrypted message
        System.out.println("");

        //decrypt message C
        String F = EncryptionHelper.modExp(C, a, n, engine);
        System.out.println("Decrypted message: " + F);
        // System.out.println(decimalToText(F, chunkSize, engine));
    }
}
