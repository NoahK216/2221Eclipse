import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Continually prompts user to enter number to calculate the square root of,
 * programs quits if the user enters a negative number. If input is nonnegative
 * uses Newton's iterative square root calculating formula to calculate the
 * given number's square root to within a user defined tolerance. Properly
 * handles user input of 0.
 *
 * @author Noah Klein
 *
 */
public final class Newton4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param tolerance
     *            relative error
     * @return estimate of square root
     */
    private static double sqrt(double x, double tolerance) {
        double root = x;
        double lastRoot = 0;
        if (x != 0) {
            while (Math.abs(root - lastRoot) > tolerance) {
                lastRoot = root;
                root = (lastRoot + (x / lastRoot)) / 2;
            }
        }
        return root;
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
         * Prompt for user defined calculation tolerance and store it
         */
        out.println("What error would you like to calculate to:");
        double tolerance = in.nextDouble();
        out.println("");

        /*
         * Prompt for number to be used in sqrt calculation and store it
         */
        out.print("What number would you like to find the square root of ");
        out.println("(Negative number to quit):");
        double x = in.nextDouble();

        /*
         * Main loop, executes until user inputs a negative number
         */
        while (x > 0) {
            /*
             * Calculate sqrt of given user input and display it
             */
            out.println(sqrt(x, tolerance));
            out.println();

            /*
             * Prompt for number to be used in sqrt calculation and store it
             */
            out.print("What number would you like to find the square root of ");
            out.println("(Negative number to quit):");
            x = in.nextDouble();
            out.println();
        }

        /*
         * Close in and out streams
         */
        in.close();
        out.close();
    }
}
