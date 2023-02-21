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
public final class Part1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Part1() {
    }

    /**
     * Returns the {@code r}-th root of {@code n}.
     *
     * @param n
     *            the number to which we want to apply the root
     * @param r
     *            the root
     * @return the root of the number
     * @requires n >= 0 and r > 0
     * @ensures root ^ (r) <= n < (root + 1) ^ (r)
     */
    private static int root(int n, int r) {
        int lowerBounds = 0;
        int upperBounds = n;

        int middle = (lowerBounds + upperBounds) / 2;

        while (lowerBounds != middle) {
            int risen = (int) Math.pow(middle, r);

            if (risen < n) {
                lowerBounds = middle;
            } else {
                upperBounds = middle;
            }

            middle = (lowerBounds + upperBounds) / 2;
        }

        return lowerBounds;
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

        final int n = 654651111;
        final int r = 2;

        out.println((int) (Math.log(n) / Math.log(2)));

        out.println(root(n, r));

        in.close();
        out.close();
    }

}
