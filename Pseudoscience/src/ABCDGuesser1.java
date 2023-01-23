import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points inside a
 * unit circle on a 2 by 2 plane.
 */
public final class ABCDGuesser1 {

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
        String result = "";

        while (!FormatChecker.canParseDouble(result)) {
            out.println("Please enter a positive real number:");
            result = in.nextLine();
            out.println();
            if (!FormatChecker.canParseDouble(result)) {
                out.println(result + " not recognized as real number");
            }
        }

        return Double.parseDouble(result);
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

        while (!FormatChecker.canParseDouble(userIn) || result == 1) {
            out.println("Please enter a positive real number not equal to 1:");
            userIn = in.nextLine();
            out.println();
            if (!FormatChecker.canParseDouble(userIn)) {
                out.println(userIn + " not recognized as real number");
            } else {
                result = Double.parseDouble(userIn);
            }
        }

        return result;
    }

    private static double calculateJager(double w, double x, double y, double z,
            double a, double b, double c, double d) {
        return (Math.pow(w, a) * Math.pow(x, b) * Math.pow(y, c)
                * Math.pow(z, d));
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

        final double targetConstant = getPositiveDouble(in, out);

        final double w = getPositiveDoubleNotOne(in, out);
        final double x = getPositiveDoubleNotOne(in, out);
        final double y = getPositiveDoubleNotOne(in, out);
        final double z = getPositiveDoubleNotOne(in, out);

        final int arraySize = 17;
        final double[] constants = { -5.0, -4.0, -3.0, -2.0, -1.0, -0.5,
                -1.0 / 3, -0.25, 0, 0.25, 1.0 / 3, 0.5, 1.0, 2.0, 3.0, 4.0,
                5.0 };

        double closest = 0;
        double closestError = targetConstant;

        double bestA = 0;
        double bestB = 0;
        double bestC = 0;
        double bestD = 0;

        for (int iterA = 0; iterA < arraySize; iterA++) {

            for (int iterB = 0; iterB < arraySize; iterB++) {

                for (int iterC = 0; iterC < arraySize; iterC++) {

                    for (int iterD = 0; iterD < arraySize; iterD++) {

                        double currentApprox = calculateJager(w, x, y, z,
                                constants[iterA], constants[iterB],
                                constants[iterC], constants[iterD]);

                        if (Math.abs(targetConstant
                                - currentApprox) < closestError) {

                            closest = currentApprox;
                            closestError = Math
                                    .abs(currentApprox - targetConstant);

                            bestA = constants[iterA];
                            bestB = constants[iterB];
                            bestC = constants[iterC];
                            bestD = constants[iterD];

                        }

                    }

                }
            }

        }

        out.println("Most accurate value of A: " + bestA);
        out.println("Most accurate value of B: " + bestB);
        out.println("Most accurate value of C: " + bestC);
        out.println("Most accurate value of D: " + bestD);

        out.println("Most accurate Jager Formula Approximation: " + closest);

        /*
         * Close in and out streams
         */
        in.close();
        out.close();
    }
}
