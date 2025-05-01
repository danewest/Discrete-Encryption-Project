package encryptionNew;

import static encryptionNew.EncryptionHelper.*;
import static encryptionNew.VariablePicker.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class EncryptionApp {
    public static BigInteger p, q, n, phi, e, d;
    public static void main (String[] args) throws InterruptedException, ExecutionException {
        // establishing necessary variables, *these will not change!*
        //final BigInteger p, q, one, n, phi, e, d;
        //p = new BigInteger("61");
        //q = new BigInteger("53");
        //one = new BigInteger("1");

        //n = p.multiply(q); //multiply
        //phi = (p.subtract(one)).multiply(q.subtract(one)); // Euler's totient

        //e = new BigInteger("1993"); // public
        //d = e.modInverse(phi);
        //d = 291481949; // private key

        Scanner in = new Scanner(System.in);

        // prompt user to choose default or custom variables
        System.out.println("Would you like to use default or custom values?");
        String response = in.nextLine();

        int userP, userQ;

        if (response.toLowerCase().equals("default")) { // use default values
            userP = 61;
            userQ = 53;
        } else { // take input for p and q
            System.out.println("Input values for p and q formatted p q. \n" +
                    "An input of 0 0 will result in the use of randomized numbers.");
            userP = in.nextInt();
            userQ = in.nextInt();

            // clear user input
            in.nextLine();
        }

        // test p and q for validity and set n, phi, e, and d
        calculateVariables(userP, userQ);

        System.out.println("Variables have been calculated. \n" +
                           "p=" + p + " q=" + q + " n=" + n + " phi=" + phi + " e=" + e + " d=" + d + "\n");

        System.out.println("What is your message?");
        //in.nextLine();
        String input = in.nextLine();

        String[] encrypted = encrypt(input, e, n);

        System.out.println("Do you want to decrypt the message?");
        response = (in.nextLine());

        if (response.toLowerCase().equals("yes")) decrypt(encrypted, d, n);
    }
}
