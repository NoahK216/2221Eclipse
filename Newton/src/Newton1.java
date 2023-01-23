import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Noah Klein
 *
 */
public final class Newton1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        final double tolerance = 0.0001;
        double root = x;
        double lastRoot = 0;

        while (Math.abs(root - lastRoot) > tolerance) {
            lastRoot = root;
            root = (lastRoot + (x / lastRoot)) / 2;
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

        while (userContinue) {
            out.println(
                    "What number would you like to find the square root of:");
            double x = in.nextDouble();
            out.println(sqrt(x));
            out.println();

            out.println("Would you like to compute another square root?");
            userContinue = in.nextLine().equalsIgnoreCase("y");
            out.println("");
        }

        in.close();
        out.close();
    }

}
