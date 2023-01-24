import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Continually prompts user if they would like to calculate a square. If so,
 * uses Newton's iterative square root calculating formula to calculate the
 * given number's square root to within %0.01. Properly handles user input of 0.
 *
 * @author Noah Klein
 *
 */
public final class Newton2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     * Returns 0 explicitly if input is 0.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        final double tolerance = 0.0001;
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

        boolean userContinue = true;

        out.println("Would you like to compute a square root?");
        userContinue = in.nextLine().equalsIgnoreCase("y");
        out.println();

        while (userContinue) {
            out.println(
                    "What number would you like to find the square root of:");
            double x = in.nextDouble();
            out.println(sqrt(x));
            out.println();

            out.println("Would you like to compute another square root?");
            userContinue = in.nextLine().equalsIgnoreCase("y");
            out.println();
        }

        in.close();
        out.close();
    }

}
