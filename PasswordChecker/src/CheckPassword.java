import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class CheckPassword {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CheckPassword() {
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String str) {
        boolean containsUpper = false;
        int i = 0;
        while (i < str.length() && !containsUpper) {
            containsUpper = containsUpper
                    || Character.isUpperCase(str.charAt(i));
            i++;
        }
        return containsUpper;
    }

    /**
     * Checks if the given String contains a lower case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains a lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String str) {
        boolean containsLower = false;
        int i = 0;
        while (i < str.length() && !containsLower) {
            containsLower = containsLower
                    || Character.isLowerCase(str.charAt(i));
            i++;
        }
        return containsLower;
    }

    /**
     * Checks if the given String contains a digit.
     *
     * @param str
     *            the String to check
     * @return true if str contains a digit, false otherwise
     */
    private static boolean containsDigit(String str) {
        boolean containsDigit = false;
        int i = 0;
        while (i < str.length() && !containsDigit) {
            containsDigit = containsDigit || Character.isDigit(str.charAt(i));
            i++;
        }
        return containsDigit;
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param passwordCandidate
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String passwordCandidate,
            SimpleWriter out) {
        final int lengthRequired = 8;
        final int checksRequired = 3;

        int checksPassed = 0;

        if (passwordCandidate.length() >= lengthRequired) {
            checksPassed++;
        }
        if (containsUpperCaseLetter(passwordCandidate)) {
            checksPassed++;
        }
        if (containsLowerCaseLetter(passwordCandidate)) {
            checksPassed++;
        }
        if (containsDigit(passwordCandidate)) {
            checksPassed++;
        }

        if (checksPassed >= checksRequired) {
            out.println("Password is valid");
        } else {
            out.println("Password meets " + checksPassed + " out of "
                    + checksRequired + " requirements");
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        checkPassword("12345678", out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
