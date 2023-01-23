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
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        final int n = 100;
        int i = 0;
        while (i < n) {
            if (Math.sqrt(i) % 1 == 0) {
                out.print(i + " ");
            }
            i++;
        }

        out.println();

        int j = 0;
        while (j < n) {
            if (j % 10 == 0) {
                out.print(j + " ");
            }
            j++;
        }

        out.println();

        int k = 0;
        while (Math.pow(2, k) < n) {
            out.print((int) Math.pow(2, k) + " ");
            k++;
        }

        in.close();
        out.close();
    }

}
