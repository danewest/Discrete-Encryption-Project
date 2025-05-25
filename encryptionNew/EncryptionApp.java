package encryptionNew;

import static encryptionNew.EncryptionHelper.*;
import static encryptionNew.VariablePicker.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class EncryptionApp {
    public static BigInteger p, q, n, phi, e, d;
    public static void main (String[] args) throws InterruptedException, ExecutionException {
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
        String input = in.nextLine();

        String[] encrypted = encrypt(input, e, n);

        System.out.println("Do you want to decrypt the message?");
        response = (in.nextLine());

        if (response.toLowerCase().equals("yes")) decrypt(encrypted, d, n);
    }
}
