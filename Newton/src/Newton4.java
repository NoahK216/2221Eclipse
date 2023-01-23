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

        out.println("What relative tolerance would you like to calculate at:");
        double tolerance = in.nextDouble();
        out.println("");

        out.println("What number would you like to find the square root of:");
        double x = in.nextDouble();

        while (x > 0) {

            out.println(sqrt(x, tolerance));
            out.println();

            out.println(
                    "What number would you like to calculate (Negative number to quit)");
            x = in.nextDouble();
            out.println("");
        }

        in.close();
        out.close();
    }
}
