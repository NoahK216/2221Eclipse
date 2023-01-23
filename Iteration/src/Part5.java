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
public final class Part5 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Part5() {
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

        final int areaBound = 5;
        int sum = 0;

        for (int n = 1; n * n < areaBound; n++) {
            for (int m = 1; m * m < areaBound; m++) {
                sum += (n * n) + (m * m);
            }
        }

        out.println(sum);

        in.close();
        out.close();
    }

}
