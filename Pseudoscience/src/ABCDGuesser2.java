import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * ABCDGuesser implements Cornelis de Jager's algorithm for personalized
 * physical constants, then returns the best fitted exponents, the final value,
 * and the relative error of the final value.
 *
 * @author Noah Klein
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
        // no code needed here
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the in stream
     * @param out
     *            the out stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        String userIn = "";
        double result = 0;

        /*
         * Continue loop until Format userIn can be parsed as a double and that
         * double is larger than 0.
         */
        while (!FormatChecker.canParseDouble(userIn) || 0 > result) {
            /*
             * Request and store user input
             */
            out.println("Please enter a positive real number:");
            userIn = in.nextLine();
            out.println();

            /*
             * Inform user if requested input cannot be parsed as a double,
             * otherwise parse it and store it's value.
             */
            if (!FormatChecker.canParseDouble(userIn)) {
                out.println(userIn + " not recognized as real number");
            } else {
                result = Double.parseDouble(userIn);
            }
        }

        return result;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the in stream
     * @param out
     *            the out stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        String userIn = "";
        double result = 1;

        /*
         * Continue loop until Format userIn can be parsed as a double and that
         * double is positive and non-real.
         */
        final double tolerance = 0.000001;
        while (!FormatChecker.canParseDouble(userIn)
                || Math.abs(result - 1.0) < tolerance) {
            /*
             * Request and store user input
             */
            out.println("Please enter a positive real number not equal to 1:");
            userIn = in.nextLine();
            out.println();

            /*
             * Inform user if requested input cannot be parsed as a double,
             * otherwise parse it and store it's value.
             */
            if (!FormatChecker.canParseDouble(userIn)) {
                out.println(userIn + " not recognized as real number");
            } else {
                result = Double.parseDouble(userIn);
            }
        }

        return result;
    }

    /**
     * Accepts two candidates and a target, outputs a true or false to indicate
     * which of the candidates is closer.
     *
     * @param num1
     *            first number to compare to target
     * @param num2
     *            second number to compare to target
     * @param target
     *            value num1 and num2 will be compared to
     * @return true if difference between num1 and target is <= the difference
     *         between num2 and target
     */
    private static boolean isCloser(double num1, double num2, double target) {
        /*
         * Calculate the errors of the two values from target
         */
        final double error1 = Math.abs(target - num1);
        final double error2 = Math.abs(target - num2);

        return (error1 <= error2);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Request and store the given physical constant.
         */
        final double targetConstant = getPositiveDouble(in, out);

        /*
         * Request and store 4 personal real numbers
         */
        final double w = getPositiveDoubleNotOne(in, out);
        final double x = getPositiveDoubleNotOne(in, out);
        final double y = getPositiveDoubleNotOne(in, out);
        final double z = getPositiveDoubleNotOne(in, out);

        /*
         * Declare array of possible exponents and store it's size.
         */
        final double[] constants = { -5.0, -4.0, -3.0, -2.0, -1.0, -0.5,
                -1.0 / 3, -0.25, 0, 0.25, 1.0 / 3, 0.5, 1.0, 2.0, 3.0, 4.0,
                5.0 };
        final int arraySize = constants.length;

        /*
         * Declare variables used to keep track of the best estimation as well
         * as the individual exponents.
         */
        double closest = 0;

        double bestA = 0;
        double bestB = 0;
        double bestC = 0;
        double bestD = 0;

        /*
         * Iterate through every possible 17^4 combinations of the exponent list
         * on the given personal numbers.
         */
        for (int iterA = 0; iterA < arraySize; iterA++) {

            for (int iterB = 0; iterB < arraySize; iterB++) {

                for (int iterC = 0; iterC < arraySize; iterC++) {

                    for (int iterD = 0; iterD < arraySize; iterD++) {

                        /*
                         * Calculate the value of the current exponents using
                         * Jager's formula.
                         */
                        double currentApprox = (Math.pow(w, constants[iterA])
                                * Math.pow(x, constants[iterB])
                                * Math.pow(y, constants[iterC])
                                * Math.pow(z, constants[iterD]));

                        /*
                         * If the current approximation is closest yet to the
                         * given physical constant update the variables that
                         * keep values.
                         */
                        if (isCloser(currentApprox, closest, targetConstant)) {
                            closest = currentApprox;

                            bestA = constants[iterA];
                            bestB = constants[iterB];
                            bestC = constants[iterC];
                            bestD = constants[iterD];

                        }

                    }

                }
            }

        }

        /*
         * Calculate relative error and then multiply it by a power of 10, then
         * round it to the nearest int and divide it by a smaller power of 10 to
         * return that number rounded to the nearest specify digit.
         */
        double relativeError = Math.abs(targetConstant - closest)
                / targetConstant;

        final double roundConstant = 10000.0;
        final double percentConstant = 100.0;

        relativeError = Math.round(relativeError * roundConstant)
                / percentConstant;

        /*
         * Print best result along with it's exponents
         */
        out.println("Most accurate value of A: " + bestA);
        out.println("Most accurate value of B: " + bestB);
        out.println("Most accurate value of C: " + bestC);
        out.println("Most accurate value of D: " + bestD);

        out.println("Most accurate Jager Formula Approximation: " + closest);

        out.println("Relative error: " + relativeError + "%");

        /*
         * Close in and out streams
         */
        in.close();
        out.close();
    }
}
