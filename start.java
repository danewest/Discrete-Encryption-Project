import com.mathworks.engine.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class start {
    public static void main (String[] args) throws InterruptedException, ExecutionException {
        Scanner in = new Scanner(System.in);

        // establishing necessary variables
        int p, q, n, phi, b, a, chunkSize;
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
        MatlabEngine engine = engineStart();

        // encrypt message M
        System.out.println("Calling textToDecimal...");
        String M = textToDecimal(input, chunkSize, engine);
        System.out.println("Message into decimal: " + M);
        String C = modExp(M, b, n, engine); // message is encrypted
        System.out.println("Encrypted message: " + C);
        // System.out.println(decimalToText(C, chunkSize, engine)); // prints encrypted message
        System.out.println("");

        //decrypt message C
        String F = modExp(C, a, n, engine);
        System.out.println("Decrypted message: " + F);
        // System.out.println(decimalToText(F, chunkSize, engine));
    }

    public static MatlabEngine engineStart() {
        try {
            //create and initialize a MATLAB engine object
            MatlabEngine mEngine = MatlabEngine.startMatlab();
            String path = System.getProperty("user.dir");
            mEngine.eval("cd('" + path.replace("\\", "/") + "')");

            return mEngine;
            // Error handling for different problems
        } catch (EngineException e) {
            System.err.println("MATLAB Engine Error: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Execution Interrupted: " + e.getMessage());
        } catch (MatlabExecutionException e) {
            System.err.println("Matlab Execution Error: " + e.getMessage());
        } catch (ExecutionException e) {
            System.err.println("Execution Error: " + e.getMessage());
        }
        return null;
    }
    public static String modExp(String base, int exponent, int modulus, MatlabEngine eng) {
        try {
            return eng.feval("modExp", base, exponent, modulus);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Execution Interrupted: " + e.getMessage());
        }
        return "";
    }
    public static String textToDecimal(String text, int chunkSize, MatlabEngine eng) {
        try {
            return eng.feval("textToDecimal", text, chunkSize);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Execution Interrupted: " + e.getMessage());
        }
        return "";
    }
    public static String decimalToText (String num, int chunkSize, MatlabEngine eng) {
        try {
            return eng.feval("decimalToText", num, chunkSize);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Execution Interrupted: " + e.getMessage());
        }
        return "";
    }
}