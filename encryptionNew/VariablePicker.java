package encryptionNew;

import java.math.BigInteger;
import java.security.SecureRandom;

// p, q, n, phi, e, d;
public class VariablePicker {
    public static void calculateVariables(int x, int y) {
        SecureRandom secureRandom = new SecureRandom();
        int bound = 200; // upper bound for picking variables

        if (checkValid(x) && checkValid(y)) { // both x and y are valid
            EncryptionApp.p = new BigInteger(Integer.toString(x));
            EncryptionApp.q = new BigInteger(Integer.toString(y));
        } else if (checkValid(x)) { // x is valid
            EncryptionApp.p = new BigInteger(Integer.toString(x));
            // calculate y
            y = secureRandom.nextInt(bound);
            while (!checkValid(y) || y == x) y = secureRandom.nextInt(bound);
            System.out.println("Y Randomized");
            EncryptionApp.q = new BigInteger(Integer.toString(y));
        } else if (checkValid(y)) { // y is valid
            EncryptionApp.q = new BigInteger(Integer.toString(y));
            // calculate x
            x = secureRandom.nextInt(bound);
            while (!checkValid(x) || x == y) x = secureRandom.nextInt(bound);
            System.out.println("X Randomized");
            EncryptionApp.p = new BigInteger(Integer.toString(x));
        } else { // neither are valid
            // calculate random prime variables to assign to p & q
            x = secureRandom.nextInt(bound);
            y = secureRandom.nextInt(bound);

            while (!checkValid(x)) x = secureRandom.nextInt(bound);
            while (!checkValid(y) || y == x) y = secureRandom.nextInt(bound);

            System.out.println("Randomized");

            EncryptionApp.p = new BigInteger(Integer.toString(x));
            EncryptionApp.q = new BigInteger(Integer.toString(y));
        }
        System.out.println("P and Q Values " + EncryptionApp.p + " " + EncryptionApp.q);

        // calculate and assign n, phi, e, and d
        BigInteger one = new BigInteger("1");

        EncryptionApp.n = EncryptionApp.p.multiply(EncryptionApp.q);
        EncryptionApp.phi = (EncryptionApp.p.subtract(one)).multiply(EncryptionApp.q.subtract(one));

        BigInteger eCandidate;
        for (int i = EncryptionApp.phi.intValue() - 1; i > 1; i--) {
            eCandidate = BigInteger.valueOf(i);
            if (EncryptionApp.phi.gcd(eCandidate).equals(one)) {
                EncryptionApp.e = eCandidate;
                break;
            }
        }

        EncryptionApp.d = EncryptionApp.e.modInverse(EncryptionApp.phi);
    }

    // return true is num is prime and greater than 20
    private static boolean checkValid(int num) {
        boolean isValid = true;

        // additional check so variables can not equal 0
        if (num < 50) return false;

        for (int i = 2; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    // return the gcd of two numbers
    private static int gcd(int x, int y) {
        if (x < y) return gcd(y, x);
        int remainder = x % y;

        if (remainder == 0) return y;
        return gcd(y, remainder);
    }
}
