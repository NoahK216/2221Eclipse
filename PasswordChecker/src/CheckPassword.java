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
        for (int i = 0; i < str.length(); i++) {
            containsUpper = containsUpper
                    || Character.isUpperCase(str.charAt(i));
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
        for (int i = 0; i < str.length(); i++) {
            containsLower = containsLower
                    || Character.isLowerCase(str.charAt(i));
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
        for (int i = 0; i < str.length(); i++) {
            containsDigit = containsDigit || Character.isDigit(str.charAt(i));
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
        final int lengthRequirement = 8;
        if (passwordCandidate.length() < lengthRequirement) {
            out.println("Password must be at least 8 characters long");
        } else if (!containsUpperCaseLetter(passwordCandidate)) {
            out.println("Password must contain at least one upper case letter");
        } else if (!containsLowerCaseLetter(passwordCandidate)) {
            out.println("Password must contain at least one lower case letter");
        } else if (!containsDigit(passwordCandidate)) {
            out.println("Password must contain at least one digit");
        } else {
            out.println("Password is valid");
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
        checkPassword("nnnnnnnnN9", out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
