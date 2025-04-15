import com.mathworks.engine.*;
import java.util.concurrent.ExecutionException;

public class EncryptionHelper {
  public static MatlabEngine engineStart() {
        try {
            //create and initialize a MATLAB engine object
            MatlabEngine mEngine = MatlabEngine.startMatlab();
            String path = System.getProperty("user.dir");
            mEngine.eval("cd('" + path.replace("\\", "/") + "')");

            return mEngine;
            // Error handling for different problems
        } catch (Exception e) {
            System.err.println("Engine start failed: " + e.getMessage());
        }
        return null;
    }
    public static String modExp(String base, int exponent, int modulus, MatlabEngine eng) {
        try {
            return eng.feval("modExp", base, exponent, modulus);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("modExp failed: " + e.getMessage());
        }
        return "";
    }
    public static String textToDecimal(String text, int chunkSize, MatlabEngine eng) {
        try {
            return eng.feval("textToDecimal", text, chunkSize);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("textToDecimal failed: " + e.getMessage());
        }
        return "";
    }
    public static String decimalToText (String num, int chunkSize, MatlabEngine eng) {
        try {
            return eng.feval("decimalToText", num, chunkSize);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("decimalToText failed: " + e.getMessage());
        }
        return "";
    }
}
