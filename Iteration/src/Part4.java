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
public final class Part4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Part4() {
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

        final int n = 1000000;
        double pi = 0;

        for (int i = 0; i <= n; i++) {
            if (i % 2 == 0) {
                pi += (1.0 / ((2 * i) + 1));
            } else {
                pi -= (1.0 / ((2 * i) + 1));
            }
        }
        final double piConstant = 4.0;
        pi = pi * piConstant;

        out.println(pi);

        in.close();
        out.close();
    }

}
