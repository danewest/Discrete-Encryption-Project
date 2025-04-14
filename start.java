import com.mathworks.engine.*;
import jdk.jshell.EvalException;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class start {
    public static void main (String[] args) {
        MatlabEngine engine = engineStart();
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

        String input = in.nextLine();
        // encrypt message M
        String M = textToDecimal(input, chunkSize, engine);
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
    public static float textToDecimal(String text, int chunkSize, MatlabEngine eng) {
        try {
            float dec = eng.feval("textToDecimal", text, chunkSize);
            return dec;
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Execution Interrupted: " + e.getMessage());
        }
        return 0;
    }
    public static String decimalToText (int num, int chunkSize, MatlabEngine eng) {
        try {
            String str = eng.feval("decimalToText", num, chunkSize);
            return str;
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Execution Interrupted: " + e.getMessage());
        }
        return "";
    }
}