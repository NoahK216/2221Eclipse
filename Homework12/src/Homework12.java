import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Code Snippets for homework 12.
 *
 * @author Noah Klein
 *
 */
public final class Homework12 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Homework12() {
    }

    /**
     * Returns the number of digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures numberOfDigits = [number of digits of n]
     */
    private static int numberOfDigits(NaturalNumber n) {
        final NaturalNumber ten = new NaturalNumber2(10);
        int numDigits = 1;

        if (n.compareTo(ten) > 0) {
            n.divideBy10();
            numDigits += numberOfDigits(n);
        }

        return numDigits;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static int sumOfDigits(NaturalNumber n) {
        int digitSum = 0;

        if (!n.isZero()) {
            digitSum += n.divideBy10();
            digitSum += sumOfDigits(n);
        }

        return digitSum;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures nnSumOfDigits = [sum of the digits of n]
     */
    private static NaturalNumber nnSumOfDigits(NaturalNumber n) {
        NaturalNumber digitSum = new NaturalNumber2();

        if (!n.isZero()) {
            NaturalNumber remainder = new NaturalNumber2(n.divideBy10());
            digitSum.add(remainder);
            digitSum.add(nnSumOfDigits(n));
        }

        return digitSum;
    }

    /**
     * Divides {@code n} by 2.
     *
     * @param n
     *            {@code NaturalNumber} to be divided
     * @updates n
     * @ensures 2 * n <= #n < 2 * (n + 1)
     */
    private static void divideBy2(NaturalNumber n) {
        if (!n.isZero()) {
            int remainder = n.divideBy10();
            divideBy2(n);
            n.multiplyBy10(remainder / 2);
        }
    }

    /**
     * Checks whether a {@code String} is a palindrome.
     *
     * @param s
     *            {@code String} to be checked
     * @return true if {@code s} is a palindrome, false otherwise
     * @ensures isPalindrome = (s = rev(s))
     */
    private static boolean isPalindrome(String s) {
        return isPalindromeHelper(s, 0);
    }

    /**
     * Helper method for {@link #isPalindrome(String)}.
     *
     * @return true if index blah blah blah
     *
     * @param s
     *            who cares
     * @param index
     *            im not sure
     *
     */
    private static boolean isPalindromeHelper(String s, int index) {
        char start = s.charAt(index), end = s.charAt(s.length() - 1 - index);
        if (index >= s.length() / 2) {
            return true;
        }
        if (start != end) {
            return false;
        }
        return isPalindromeHelper(s, index + 1);
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

        NaturalNumber test = new NaturalNumber2(151);
        String sTest = new String("heh");
        out.println(isPalindrome(sTest));

        in.close();
        out.close();
    }

}
